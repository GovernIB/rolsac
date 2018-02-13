import * as selectors from "./selectors"
import * as actions from "./actions"
import traverse from "traverse"
import {createSelector} from "reselect"
import debounce from "lodash/debounce"

import * as formDataValidateActions from "./validators/form-data"
import * as schemaValidateActions from "./validators/schema"
import * as pathsValidateActions from "./validators/paths"
import * as securityValidateActions from "./validators/security"
import * as refsValidateActions from "./validators/refs"
import * as parametersValidateActions from "./validators/parameters"
import * as operationsValidateActions from "./validators/operations"

export default function SemanticValidatorsPlugin({getSystem}) {

  const debAll = debounce((system) => system.validateActions.all(), 300)
  const traverseOnce = makeTraverseOnce(getSystem)

  return {
    fn: {
      traverse,
      traverseOnce,
    },
    statePlugins: {
      spec: {
        selectors: {
          jsonAsJS: createSelector(
            state => state.get("resolved"),
            (spec) => spec ? spec.toJS() : null
          )
        },
        wrapActions: {
          validateSpec: (ori, system) => (...args) => {
            ori(...args)
            debAll(system)
          }
        }
      },
      validate: {
        selectors,
        actions: {
          ...actions,
          ...formDataValidateActions,
          ...schemaValidateActions,
          ...pathsValidateActions,
          ...securityValidateActions,
          ...refsValidateActions,
          ...parametersValidateActions,
          ...operationsValidateActions
        }
      },
    }
  }
}

function makeTraverseOnce(getSystem) {
  let traversers = {}
  let results = {}
  let deferred = null

  const debTraverse = debounce(() => {
    // Setup collections
    for(let name in traversers) {
      results[name] = []
    }

    const system = getSystem()

    const json = system.specSelectors.jsonAsJS()

    const isSwagger2 = system.specSelectors.isSwagger2 || null
    const isOAS3 = system.specSelectors.isOAS3 || null

    if((isSwagger2 && !isSwagger2()) || (isOAS3 && isOAS3())) {
      return
    }

    getSystem().fn.traverse(json)
      .forEach(function() { // Remember: this cannot be a fat-arrow function, because we need to read "this"
        for(let name in traversers) {
          const fn = traversers[name]
          const fnRes = fn(this)
          if(fnRes) {
            results[name].push(fnRes)
          }
        }
      })

    deferred.resolve(results)
    deferred = null

    traversers = {}
    results = {}
  }, 20) // 20ms might be more than enough, since most of these are called immediately (within a tick)

  const defer = () => {
    let d = {}
    d.promise = new Promise((resolve, reject) => {
      d.resolve = resolve
      d.reject = reject
    })
    return d
  }

  return ({fn, name}) => {
    traversers[name] = fn
    deferred = deferred || defer()
    debTraverse()
    return deferred.promise.then( a => a[name] )
  }
}
