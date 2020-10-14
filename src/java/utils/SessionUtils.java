/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Enumeration;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wenkary
 */
public class SessionUtils {
    
    public static HttpSession httpSession;
    public static boolean isActiveSession;
    
    public static void openSession(FacesContext facesContext){ 
        httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest(FacesContext facesContext) {
        return (HttpServletRequest) facesContext
                .getExternalContext().getRequest();
    }
    
    public static String getUsername()  {
        if(httpSession == null)
            return null;
        return httpSession.getAttribute("username").toString();
    }
    
    public static String getUserId() {
        if(httpSession == null)
            return null;
        
        return (String) httpSession.getAttribute("userId");
    }
    
    public static void setSessionData(String username, String userId) {
        try {
            if(httpSession.getId() != null && !httpSession.getId().isEmpty()) {
                isActiveSession = true;
                httpSession.setAttribute("username", username);
                httpSession.setAttribute("userId", userId);
                httpSession.setAttribute("isActiveSession", isActiveSession);
            } else {
                isActiveSession = false;
                httpSession.setAttribute("isActiveSession", isActiveSession);
                throw new Exception("Error en el inicio de sesión");
            }
        }
        catch(Exception e) {
            httpSession.invalidate();
            e.printStackTrace();
        }
    }
    
    public static void closeSesion() {
        Enumeration<String> attributes = null;
        try{ 
            if(httpSession != null &&
                    httpSession.getId() != null &&
                    !httpSession.getId().isEmpty()) {
               isActiveSession = false;
               attributes = httpSession.getAttributeNames();
               while(attributes.hasMoreElements()) {
                   String attribute = attributes.nextElement();
                   httpSession.removeAttribute(attribute);
               }
               httpSession.invalidate();
            } else {
                throw new Exception("Error en el cierre de sesión");
            }
        }
        catch(Exception  e) {
            throw new RuntimeException(e);
        }
    }
    
    public static boolean getStatusSession() {
        if(httpSession != null 
            && httpSession.getId() != null
            && !httpSession.getId().isEmpty()
            && httpSession.getAttribute("isActiveSession") != null) {
            return Boolean.parseBoolean(httpSession.getAttribute("isActiveSession").toString());
        }
        else {
            return false;
        }
    }
}
