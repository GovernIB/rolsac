package es.caib.test.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;

public class LogSpy implements Log {

		List<String> infoMsgs=new ArrayList<String>();
		List<String> errorMsgs=new ArrayList<String>();
		List<String> debugMsgs=new ArrayList<String>();
		
		
		public List<String> getInfoMsgs() {
			return infoMsgs;
		}
		
		public List<String> getDebugMsgs() {
			return debugMsgs;
		}

		public void info(Object o) {
			infoMsgs.add((String)o);
		}

		
		public String printInfoMsgs() {
			return Arrays.toString(infoMsgs.toArray());
		}
		
		public String printDebugMsgs() {
			return Arrays.toString(debugMsgs.toArray());
		}
		
		public boolean containsInfoMsg(String msg) {
			return infoMsgs.contains(msg);
		}
		
		public boolean containsDebugMsg(String msg) {
			return debugMsgs.contains(msg);
		}
		
		public boolean containsErrorMsg(String msg) {
			return errorMsgs.contains(msg);
		}

		
		public void debug(Object o) {
			debugMsgs.add((String)o);
			
		}
		public void debug(Object arg0, Throwable arg1) {
			// TODO Auto-generated method stub
			
		}
		public void error(Object o) {
			errorMsgs.add((String)o);
			
		}
		public void error(Object arg0, Throwable arg1) {
			// TODO Auto-generated method stub
			
		}
		public void fatal(Object arg0) {
			// TODO Auto-generated method stub
			
		}
		public void fatal(Object arg0, Throwable arg1) {
			// TODO Auto-generated method stub
			
		}
		public void info(Object arg0, Throwable arg1) {
			// TODO Auto-generated method stub
			
		}
		public boolean isDebugEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		public boolean isErrorEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		public boolean isFatalEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		public boolean isInfoEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		public boolean isTraceEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		public boolean isWarnEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		public void trace(Object arg0) {
			// TODO Auto-generated method stub
			
		}
		public void trace(Object arg0, Throwable arg1) {
			// TODO Auto-generated method stub
			
		}
		public void warn(Object arg0) {
			// TODO Auto-generated method stub
			
		}
		public void warn(Object arg0, Throwable arg1) {
			// TODO Auto-generated method stub
			
		}
}
