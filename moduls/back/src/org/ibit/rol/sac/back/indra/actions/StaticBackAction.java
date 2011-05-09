package org.ibit.rol.sac.back.indra.actions;

import java.util.Vector;

public class StaticBackAction {
	
	private Vector mCollection;
    
    private static StaticBackAction cInstance;
    
    public final int MAX_COLLECTION=100;
    
    /** Creates a new instance of TheSingletonClass */
    private StaticBackAction() {
        mCollection = new Vector();
    }
    
    public static synchronized StaticBackAction getInstance(){
        if(cInstance == null)
            cInstance = new StaticBackAction();
        return cInstance;
    }
    
    public void addElementToCollection(RingBuffer sElem){
        mCollection.addElement(sElem);
    }
    
    
    public Vector getCollection(){
        return mCollection;
    }

}
