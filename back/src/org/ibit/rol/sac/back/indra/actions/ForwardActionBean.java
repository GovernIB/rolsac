package org.ibit.rol.sac.back.indra.actions;

/**
 * This class is a utility class that goes along with the
 * LongWaitRequestProcessor and helps in processing requests that require
 * long transaction support.
 * @author ATP
 * @version 1.0
 */
public class ForwardActionBean {

  private String actionPath;

  public void setActionPath(String v) {
    this.actionPath = v;
  }

  public String getActionPath() {
    return this.actionPath;
  }
}