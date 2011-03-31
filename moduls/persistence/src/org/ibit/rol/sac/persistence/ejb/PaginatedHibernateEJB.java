package org.ibit.rol.sac.persistence.ejb;

import java.security.Principal;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import net.sf.hibernate.FlushMode;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.util.HibernateLocator;



/**
 * Bean con la funcionalidad b�sica para interactuar con HIBERNATE de forma paginada.
 *
 * @ejb.bean
 *  view-type="remote"
 *  generate="false"
 * @ejb.security-role-ref role-name="sacsystem" role-link="${role.system}"
 * @ejb.security-role-ref role-name="sacadmin" role-link="${role.admin}"
 * @ejb.security-role-ref role-name="sacsuper" role-link="${role.super}"
 * @ejb.security-role-ref role-name="sacoper" role-link="${role.oper}"
 */

public abstract class PaginatedHibernateEJB implements SessionBean {

    protected static Log log = LogFactory.getLog(PaginatedHibernateEJB.class);

    protected SessionFactory sf = null;
    protected SessionContext ctx = null;
    
    //  Par�metros de consulta paginada
    protected int tampagina=0;   // tama�o de la p�gina
    protected int pagina=0;      // p�gina actual
   
    protected String select="";
    protected String from="";
    protected String where="";
    protected String whereini="";
    protected String orderby="";  // ordenaci�n de la consulta

    protected String[] camposfiltro;
    protected int cursor=0;
    protected int nreg=0;
    protected int npags=0;

    protected List langs;  // Lista de idiomas
    
    
    
    public void setSessionContext(SessionContext ctx) {
        this.ctx = ctx;
    }

    public void ejbCreate() throws CreateException {
        //log.info("ejbCreate: " + this.getClass() + this.getClass().hashCode());
        sf = HibernateLocator.getSessionFactory();
    }

    public void ejbRemove() {
        //log.info("ejbRemove: " + this.getClass());
        sf = null;
    }

    protected Session getSession() {
        try {
            Session session = sf.openSession();
            session.setFlushMode(FlushMode.COMMIT);
            return session;
        } catch (HibernateException e) {
            throw new EJBException(e);
        }
    }

    protected void close(Session session) {
        if (session != null && session.isOpen()) {
            try {
                if (session.isDirty()) {
                    log.warn("Closing dirty session!!");
                }
                session.close();
            } catch (HibernateException e) {
                log.error(e);
            }
        }
    }

    
    /**
     * Devuelve los par�metros de la consulta en una Hash
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Hashtable getParametros() {
        Hashtable tabla=new Hashtable();
    
        tabla.put("cursor", new Integer(cursor));
        
        // Calculo del cursor final
        int cursor_final=cursor;
        if (getPagina()==npags) cursor_final=nreg;
        else                    cursor_final=cursor+tampagina-1;
        tabla.put("cursor_final", new Integer(cursor_final));
        
        tabla.put("nreg", new Integer(nreg));
        tabla.put("tampagina", new Integer(tampagina));
        tabla.put("actual", new Integer(getPagina()));
        
        if(getPagina()!=1)      tabla.put("inicio", new Integer(1));
        if(getPagina()>1)       tabla.put("anterior", new Integer(getPagina()-1));
        if(getPagina()<npags)   tabla.put("siguiente", new Integer(getPagina()+1));
        if(getPagina()!=npags)  tabla.put("final", new Integer(npags));
        
        return tabla;
    }        

    /**
     * Establece distintos par�metros para la paginaci�n tras realizar la
     * consulta
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void parametrosCons() {

    	boolean paginar=false;                      // hay paginaci�n o no
        
        Session ses=getSession();
        try {
        	nreg=((Integer)(ses.iterate("select count(*) "+from+where).next())).intValue();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(ses);
        }

        if (getPagina()==0) setPagina(1);
        cursor= ((getPagina()-1)*tampagina)+1;      // posici�n del cursor
        npags=1;                                    // n�mero total de p�ginas
        if (nreg>tampagina && tampagina>0) {
            paginar=true;
            npags=((nreg%tampagina)>0?(nreg/tampagina)+1:nreg/tampagina);
        }
        if (!paginar)   tampagina=nreg;

    }

    /**
     * @ejb.interface-method
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
     */
    public int getPagina()
    {
        return pagina;
    }
    /**
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void setPagina(int pagina)
    {
        this.pagina = pagina;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void setOrderby(String orderby)
    {
     	
        String tipo="";

        if (orderby.length()>0) {
            tipo=orderby.substring(0,1);
            if (tipo.equals("A"))
                tipo="ASC";
            else
                tipo="DESC";
            this.orderby = " ORDER BY " + orderby.substring(1) + " " + tipo;
        }

    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void setOrderby2(String orderby)
    {
           this.orderby = orderby;
    }

    /**
     * Ejecuta una consulta y devuelve el primer valor del primer registro
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public String getValorBD(String hql) 
    {
        Session ses=getSession();
        try {
        	return ((String)(ses.iterate(hql).next())).toString();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(ses);
        }
    }

    /**
     * Establece el filtro de la consulta
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void setFiltro(String valor) 
    {
        // Establece el filtro de la consulta
        
        if (valor.equals("")) {
            where=whereini;
            return;
        }

        String filtro=" ";        
        for (int i=0; i<camposfiltro.length;i++) 
            filtro+= camposfiltro[i]+" like '%"+valor+"%' OR "; 

        if (filtro.length()>0)
            filtro=filtro.substring(0,filtro.length()-3);

        if (where.length()>0)
            where=where+" AND ("+filtro+")";
        else
            where=" where "+filtro;

    }

    /**
     * Obtiene la condicion 'where' de de la consulta
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public String getWhere() {
		return where;
	}

    /**
     * Establece la condicion 'where' de la consulta
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public void setWhere(String where) {
		this.where = where;
	}    
    
	

    /**
     * Obtiene el tamano de la paginacion de de la consulta
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public int getTampagina() {
		return tampagina;
	}

    /**
     * Establece la paginaci�n de la consulta
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public void setTampagina(int tampagina) {
		this.tampagina = tampagina;
	}    	


    /**
     * Devuelve el usuario del EJB
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public String getUsuarioEJB() {
        Principal principal = ctx.getCallerPrincipal();
        return principal.getName();
	}
	
	
	/*
    Los roles de usuario son inclusivos.
    Los siguientes m�todos permiten saber si un usuario tiene los permisos
    de un determinado rol, bien porque sea su rol, o porque tenga uno superior. 
    */

	protected boolean userIsSystem() {
		return ctx.isCallerInRole("sacsystem");
	}

	protected boolean userIsAdmin() {
		return userIsSystem() || ctx.isCallerInRole("sacadmin");
	}

	protected boolean userIsSuper() {
		return userIsAdmin() || ctx.isCallerInRole("sacsuper");
	}

	protected boolean userIsOper() {
		return userIsSuper() || ctx.isCallerInRole("sacoper");
	}

	protected boolean userIs(String role) {
		if ("sacoper".equals(role)) {
			return userIsOper();
		} else if ("sacsuper".equals(role)) {
			return userIsSuper();
		} else if ("sacadmin".equals(role)) {
			return userIsAdmin();
		} else if ("sacsystem".equals(role)) {
			return userIsSystem();
		} else {
			return false;
		}
	}



    /**
     * Comprueba si un usuario puede acceder a un Microsite
     */
	/*
    protected boolean tieneAcceso(Usuario usuario, Microsite micro) {
        //return (userIsSystem() || userIs(seccion.getPerfil()));
    }
	
    protected Usuario getUsuario(Session session) throws HibernateException {
        Criteria criteriUsu = session.createCriteria(Usuario.class);
        criteriUsu.add(Expression.eq("username", ctx.getCallerPrincipal().getName()));
        List usuaris = criteriUsu.list();
        if (usuaris.isEmpty()) {
            throw new EJBException("El usuario actual no existe!");
        }
        return (Usuario) usuaris.get(0);
    }
    */
	
	
}