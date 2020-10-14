/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.event.ActionEvent;

/**
 *
 * @author wenkary
 */
public class FacesUtil {
    
    public static String getActionAttribute(ActionEvent event, String name) {
        return (String) event.getComponent().getAttributes().get(name);
    }
    
    public static Long getActionAttributeLong(ActionEvent event, String name) {
        return (Long) event.getComponent().getAttributes().get(name);
    }
}
/*
 @Override
    public void afterPhase(PhaseEvent event) {
        try {
            String paginaActual = event.getFacesContext()
                .getViewRoot().getViewId();
            Sesion.iniciarSesion(event.getFacesContext());
             
            if (!paginaActual.contains("index.xhtml") &&
                    Sesion.getEstadoSesion() == false) {               
                FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("index.xhtml?faces-redirect=true");
     
                NavigationHandler nh = event.getFacesContext()
                    .getApplication()
                    .getNavigationHandler();
                nh.handleNavigation(event.getFacesContext(), 
    null, "index");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
*/