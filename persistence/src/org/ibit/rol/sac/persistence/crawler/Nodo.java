package org.ibit.rol.sac.persistence.crawler;

import org.apache.lucene.document.Document;
import org.htmlparser.beans.StringBean;

public class Nodo {

	private String URL;
	private int profundidad;
	private Document doc;
	private String md5;
	public Nodo(String URL,int profundidad,Document doc,String md5){
	this.URL=URL;
	this.profundidad=profundidad;
	this.doc=doc;
	this.md5=md5;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String url) {
		URL = url;
	}
	public int getProfundidad() {
		return profundidad;
	}
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	
}
