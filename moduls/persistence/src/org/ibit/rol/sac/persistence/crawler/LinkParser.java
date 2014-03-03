package org.ibit.rol.sac.persistence.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class LinkParser {
    
    String url;
    Parser parser;
    NodeFilter filter;
    NodeList list;
    LinkTag link;
    URL[] linkArray;
    Vector vector;
    
    public LinkParser(String Url)
    {
        url = Url;
    }
    
    public URL[] ExtractLinks()
    {
      filter = new NodeClassFilter(LinkTag.class);        
            
        try
        {
            parser = new Parser (url);
            list = parser.extractAllNodesThatMatch (filter);

              vector = new Vector();
                for (int i = 0; i < list.size (); i++)
                       try
                        {
                            link = (LinkTag)list.elementAt (i);
                            if (!comprobarFormato(link.getLink().toString())) {
                                vector.add(new URL (link.getLink ()));
                            }
                        }
                        catch (MalformedURLException murle)
                        {
                        }
                linkArray = new URL[vector.size ()];
                vector.copyInto (linkArray);
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
      
        return (linkArray);
    }
    private static boolean comprobarFormato(String  url)
    {
    	boolean validez = false;
    	//faltan añadir todos los formatos
    	String[] noValidos={"pdf","jpeg","jpg","zip","rar","gzip","gif","png"};
    	for (int i = 0; i < noValidos.length; i++) {
			if (url.toLowerCase().endsWith("."+noValidos[i])){
				validez=true;
				break;
			}
		}
    	return validez;

    }
 }

