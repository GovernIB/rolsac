package org.ibit.rol.sac.model.webcaib;


public class Foto implements java.io.Serializable {
        
    private int id;
    private String mime;
    private int width;
    private int height;
    
    public Foto (int id, String mime, int width, int height ) {
       this.id = id;
       this.mime = mime;
       this.width = width;
       this.height = height;
    }
    
    public void setId (int id) { this.id = id; }
    public int getId () { return id; }
    
    public void setMime (String mime) { this.mime = mime; }
    public String getMime () { return mime; }
    
    public void setWidth (int width) { this.width = width; }
    public int getWidth () { return width; }
    
    public void setHeight (int height) { this.height = height; }
    public int getHeight () { return height; }
    
    public String toString() {
      return " [mime : " + mime + ", width : " + width + ", height : " + height + "]";   
    }
}