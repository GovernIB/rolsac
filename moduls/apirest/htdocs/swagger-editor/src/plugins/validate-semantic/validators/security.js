export const validateSecurityRequirementHasDefinition = () => (system) => {
  const { allSecurityRequirements, allSecurityDefinitions } = system.validateSelectors

  return Promise.all([allSecurityRequirements(), allSecurityDefinitions()])
    .then(([requirementNodes, definitionNodes]) => {
      const definedSecuritySchemes = definitionNodes
        .map(node => node.key)

      return requirementNodes.reduce((acc, node) => {
        const value = node.node
        const requiredSecurityDefinitions = Object.keys(value) || []

        requiredSecurityDefinitions.forEach(name => {
          if(definedSecuritySchemes.indexOf(name) < 0) {
            acc.push({
              message: "Security requirements must match a security definition",
              path: [...node.path],
              level: "error",
            })
          }
        })
        return acc
      }, [])
    })
}

export const validateSecurityRequirementReferenceExistingScopes = () => (system) => {
  const { allSecurityRequirements, allSecurityDefinitions } = system.validateSelectors

  return Promise.all([allSecurityRequirements(), allSecurityDefinitions()])
    .then(([requirementNodes, definitionNodes]) => {
      const definedSecuritySchemes = definitionNodes
        .reduce((p, node) => Object.assign(p, { [node.key]: node.node }), {})

      return requirementNodes.reduce((acc, node) => {
        const value = node.node
        const requiredSecurityDefinitions = Object.keys(value) || []

        requiredSecurityDefinitions.forEach(name => {

          const scopes = value[name]
          const definition = definedSecuritySchemes[name]
          if(Array.isArray(scopes) && scopes.length && definition) {
            scopes.forEach((scope, i) => {
              if(!definition.scopes || !definition.scopes[scope]) {
                acc.push({
                  message: `Security scope definition ${scope} could not be resolved`,
                  path: [...node.path, i.toString()],
                  level: "error",
                })
              }
            })
          }
        })

        return acc
      }, [])
    })
}
