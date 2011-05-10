package org.ibit.rol.sac.back.action.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;

/**
 * Action para reiniciar index lucene de una unidad organica
 *
 * @struts.action
 *  path="/index/ua/reiniciar"
 *  scope="request"
 *  validate="false"
 *
 * @struts.action-forward
 *  name="success" path=".index.finaliza"
*/
public class ReindexarIndexUAAction extends  Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();

        indexerDelegate.reindexarUOs();

		
        return mapping.findForward("success");

	}

}
