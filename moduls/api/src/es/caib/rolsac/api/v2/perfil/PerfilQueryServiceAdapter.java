package es.caib.rolsac.api.v2.perfil;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.ejb.PerfilQueryServiceEJBStrategy;

public class PerfilQueryServiceAdapter extends PerfilDTO implements PerfilQueryService {

    private PerfilQueryServiceStrategy perfilQueryServiceStrategy;

    public void setPerfilQueryServiceStrategy(PerfilQueryServiceStrategy perfilQueryServiceStrategy) {
        this.perfilQueryServiceStrategy = perfilQueryServiceStrategy;
    }

    public PerfilQueryServiceAdapter(PerfilDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }
    
    public STRATEGY getStrategy() {
        return perfilQueryServiceStrategy instanceof PerfilQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public List<IconaFamiliaQueryServiceAdapter> llistarIconesFamilia(IconaFamiliaCriteria iconaFamiliaCriteria) {
        List<IconaFamiliaDTO> llistaDTO = perfilQueryServiceStrategy.llistarIconesFamilia(id, iconaFamiliaCriteria);
        List<IconaFamiliaQueryServiceAdapter> icones = new ArrayList<IconaFamiliaQueryServiceAdapter>();
        for (IconaFamiliaDTO iDTO : llistaDTO) {
            icones.add((IconaFamiliaQueryServiceAdapter) BeanUtils.getAdapter("iconaFamilia", getStrategy(), iDTO));
        }
        return icones;
    }

    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateria(IconaMateriaCriteria iconaMateriaCriteria) {
        List<IconaMateriaDTO> llistaDTO = perfilQueryServiceStrategy.llistarIconesMateria(id, iconaMateriaCriteria);
        List<IconaMateriaQueryServiceAdapter> icones = new ArrayList<IconaMateriaQueryServiceAdapter>();
        for (IconaMateriaDTO iDTO : llistaDTO) {
            icones.add((IconaMateriaQueryServiceAdapter) BeanUtils.getAdapter("iconaMateria", getStrategy(), iDTO));
        }
        return icones;
    }

    public int getNumIconesFamilia() {
        return perfilQueryServiceStrategy.getNumIconesFamilia(id);
    }

    public int getNumIconesMateria() {
        return perfilQueryServiceStrategy.getNumIconesMateria(id);
    }
}
