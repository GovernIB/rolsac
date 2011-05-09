package org.ibit.rol.sac.back.action.index;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para reiniciar index lucene de una Ficha
 *
 * @struts.action
 *  path="/index/ficha/reiniciar"
 *  scope="request"
 *  validate="false"
 *
 * @struts.action-forward
 *  name="success" path=".index.finaliza"
*/
public class ReiniciarIndexFichaAction extends  Action {
     public ActionForward execute(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

         IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();

         indexerDelegate.reindexarFichas();


         return mapping.findForward("success");

     }

}
