/* Generated by Together */

package org.ibit.rol.sac.model;

import java.util.HashSet;
import java.util.Set;

public class ExcepcioDocumentacio extends Traducible {

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set getCatalegDocuments() {
    return catalegDocuments;
  }

  public void setCatalegDocuments(Set catalegDocuments) {
    this.catalegDocuments = catalegDocuments;
  }

  public Set getDocsRequerits() {
    return docsRequerits;
  }

  public void setDocsRequerits(Set docsRequerits) {
    this.docsRequerits = docsRequerits;
  }

  private Long id;
  private Set catalegDocuments;
  private Set docsRequerits; 
}