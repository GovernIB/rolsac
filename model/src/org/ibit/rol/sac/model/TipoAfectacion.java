/**
 * Created by IntelliJ IDEA.
 * User: fsalas
 * Date: Nov 5, 2003
 * Time: 11:50:08 AM
 * To change this template use Options | File Templates.
 */
package org.ibit.rol.sac.model;

public class TipoAfectacion extends Traducible {
    private Long id = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TipoAfectacion that = (TipoAfectacion) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }
}
