package es.caib.rolsac.api.v2.edifici;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.ejb.EdificiQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class EdificiQueryServiceAdapter extends EdificiDTO implements EdificiQueryService {

    private static final long serialVersionUID = -1487723379971783395L;

    EdificiQueryServiceStrategy edificiQueryServiceStrategy;
    
    public void setEdificiQueryServiceStrategy(EdificiQueryServiceStrategy edificiQueryServiceStrategy) {
        this.edificiQueryServiceStrategy = edificiQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return edificiQueryServiceStrategy instanceof EdificiQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public EdificiQueryServiceAdapter(EdificiDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public int getNumUnitatsAdministratives() throws QueryServiceException {
        try {
            return edificiQueryServiceStrategy.getNumUnitatsAdministratives(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de unidades administrativas.", e);
        }
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
        try {
            List<UnitatAdministrativaDTO> llistaDTO = edificiQueryServiceStrategy.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "unidades administrativas.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirFotoPequenya() throws QueryServiceException {
        if (this.getFotoPequenya() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), edificiQueryServiceStrategy.obtenirFotoPequenya(this.getFotoPequenya()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "foto pequenya.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirFotoGrande() throws QueryServiceException {
        if (this.getFotoGrande() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), edificiQueryServiceStrategy.obtenirFotoGrande(this.getFotoGrande()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "foto grande.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirPlano() throws QueryServiceException {
        if (this.getPlano() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), edificiQueryServiceStrategy.obtenirPlano(this.getPlano()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "plano.", e);
        }
    }

}