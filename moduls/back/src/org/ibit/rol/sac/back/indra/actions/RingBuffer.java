package org.ibit.rol.sac.back.indra.actions;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A Ringbuffer is a buffer to store something (in this case ArrayEntries). 
 * This buffer is made for the stuts back mechanism. Its supports also a 
 * wizzard manner to go back and forward more steps. This implementation 
 * simulates a ringbuffer. The Buffer has the following invariants:
 * <ul>
 * <li>The actIndex pointer shows to the act index to be shown.</li>
 * <li>All Entries from startPoint to endPoint are valid entries if both 
 *     pointers are >= 0.</li>
 * <li>If maxindex is reached the first index will be overide and the overflow 
 *     flag is set.</li>
 * </ul>
 * Example: startPoint = 0; endPoint = 5; actIndex = 4;
 * <ol>
 * <li>There are 6 valid entries from 0 to 5.</li>
 * <li>The actal index ist 4. So you can go forward 1 entry and you can go 
 *     backword 4 entries.</li>
 * <li>A New Entry will be inserted on index 6, the endpoint will be increased
 * </ol>
 * 
 * @since 1.0
 * @author ATP
 */
public class RingBuffer {

    /** The maximum length of the ringbuffer */
    private int maxEntries = 40;
    /** The ringbuffer itselfs */
    private final ArrayList ringBuffer;
    /** The Startpoint of the ringbuffer */
    // private int startPoint = -1;
    /** The Endpoint of the ringbuffer */
    private int endPoint = -1;
    /** The pointer of the act index */
    private int actIndex = -1;
    /** Sign that ringbuffer is overflowed */
    private boolean overflow;
    /** Sign that back or forward is set */
    private boolean noPush;
    /** Sign that back or forwardaction were involved */
    private boolean wereInvolved;

    /**
     * <p>
     * Commons Logging instance.
     * </p>
     */
    protected static Log log = LogFactory.getLog(RingBuffer.class);

    /**
     * Constructor with default entries (40).
     */
    public RingBuffer() {
        super();
        ringBuffer = new ArrayList();
    }

    /**
     * Constructor with an amount of entries.
     * 
     * @param maxEntries Entries of the ringbuffer.
     */
    public RingBuffer(int maxEntries) {
        super();
        this.maxEntries = maxEntries;
        ringBuffer = new ArrayList();
    }

    public boolean isForwardPossible() {
        return (actIndex < endPoint);
    }
    
    public boolean isBackPossible() {
        return (actIndex > 0);
    }
    
    public void print() {
    	/*
        if (log.isInfoEnabled()) {
            log.info("========= Status de la cola de urls ================");
            int index = 0;
            for (Iterator it = ringBuffer.iterator(); it.hasNext();) {
                ArrayEntry entry = (ArrayEntry) it.next();
                if (log.isInfoEnabled()) {
                    log.info("Id " + index++ + " = " + entry.getValue());
                }
            }
            log.info("El actual index es : " + actIndex);
            log.info("El ultimo index es : " + endPoint);
            log.info("======== END Status de la cola de urls =============");
        }
        */
    }

    /**
     * Clears the buffer
     */
    public void clear() {
        ringBuffer.clear();
        // startPoint = -1;
        endPoint = -1;
        actIndex = -1;
    }

    /**
     * @return The maximal entries of the buffer.
     */
    public int size() {
        return ringBuffer.size();
    }

    /**
     * Pushes an entry into the ringbuffer.
     * 
     * @param entry The entry to be pushed
     * @return The actual index of the ringbuffer
     */
    public int push(ArrayEntry entry) {

        // Removes the first entry if necessary (overflow). If the actindex is 
        // the last entry, correct it.
        if (ringBuffer.size() == this.maxEntries) {
        	/*
            if (log.isInfoEnabled()) {
                log.info("Overflow, borrando la última entrada.");
            }
            */
            ringBuffer.remove(0);
            if (actIndex == (ringBuffer.size())) {
                actIndex = (ringBuffer.size() - 1);
            }
            overflow = true;
        }

        // If the index pointer is not the last entry, delete all
        // entries above the index pointer !
        if ((ringBuffer.size() > 0) && (actIndex != (ringBuffer.size() - 1))) {
            /*
        	if (log.isInfoEnabled()) {
                log.info("Corrigiendo entradas entre el indice y el final del buffer.");
            }
            */
            while (actIndex != ringBuffer.size() - 1) {
                ringBuffer.remove(ringBuffer.size() - 1);
            }
        }

        // Adds the entry at the end of the buffer
        ringBuffer.add(entry);
        /*
        if (log.isInfoEnabled()) {
            log.info("Guardada url: " + entry.getValue() + ".");
        }
        */
        if (actIndex == -1) {
            actIndex = 0;
            // startPoint = 0;
        } else {
            actIndex = ringBuffer.size() - 1;
        }

        endPoint = actIndex;
        return actIndex;
    }

    /**
     * Goes one entry back into the ringbuffer.
     * 
     * @return The entry back
     */
    public ArrayEntry back() {

        // There must be entries in the buffer
        if (actIndex == 0) { return null; }

        // the index might not be 0
        if (actIndex > 0) {
            actIndex--;
            setNoPush(true);
        }

        return (ArrayEntry) ringBuffer.get(actIndex);
    }

    /**
     * Goes one entry forward in the ringbuffer.
     * 
     * @return The entry forward
     */
    public ArrayEntry forward() {

        // There must exists an endpoint
        if (endPoint == -1) { return null; }

        // Forward only to the endpoint
        if (actIndex == endPoint) { return null; }

        // the index might not be 0
        if (actIndex < endPoint) {
            actIndex++;
            setNoPush(true);
        }

        return (ArrayEntry) ringBuffer.get(actIndex);
    }

    /**
     * Returns the last pushed entry.
     * 
     * @return The last pushed entry
     */
    public ArrayEntry getLastPushed() {
        // size > 0
        if (ringBuffer.size() > 0) { return (ArrayEntry) ringBuffer
            .get(ringBuffer.size() - 1); }
        return null;
    }
    
    public ArrayEntry getActIndex() {
        if (ringBuffer.size() > 0) { return (ArrayEntry) ringBuffer
            .get(actIndex); }
        return null;
    }

    /**
     * Returns the overflow bit.
     * 
     * @return the overflow bit.
     */
    public boolean isOverflow() {
        return overflow;
    }

    /**
     * Returns the NoPush Flag.
     * 
     * @return The NoPush Flag.
     */
    public boolean isNoPush() {
        return noPush;
    }

    /**
     * Sets the NoPush Flag.
     * 
     * @param noPush The NoPush Flag.
     */
    public void setNoPush(boolean noPush) {
        this.noPush = noPush;
    }

    /**
     * Returns the WereInvolved Flag.
     * 
     * @return The WereInvolved Flag.
     */
    public boolean isWereInvolved() {
        return wereInvolved;
    }

    /**
     * Sets the WereInvolved Flag.
     * 
     * @param wereInvolved The WereInvolved Flag.
     */
    public void setWereInvolved(boolean wereInvolved) {
        this.wereInvolved = wereInvolved;
    }
}
