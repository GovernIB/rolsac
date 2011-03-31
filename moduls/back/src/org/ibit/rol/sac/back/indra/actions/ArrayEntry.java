package org.ibit.rol.sac.back.indra.actions;

/**
 * 
 * @since 1.0
 * @author ATP
 */
public class ArrayEntry {
    
    /**
     * Default Contructor.
     */
    public ArrayEntry() {
        super();
    }

    /**
     * Constructor with value.
     * @param value The value to be set.
     */
    public ArrayEntry(final Object value) {
        this.value = value;
    }
    
    /** The value of the entry. */
    private Object value;

    /**
     * Gets the value of the entry.
     * @return The value of the entry.
     */
    public final Object getValue() {
        return value;
    }

    /**
     * Sets the value of the entry.
     * @param value The value of the entry.
     */
    public final void setValue(final Object value) {
        this.value = value;
    }
}
