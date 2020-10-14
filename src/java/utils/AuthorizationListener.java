/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author chucho
 */
public class AuthorizationListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        try {
            String currentPage = phaseEvent.getFacesContext().getViewRoot().getViewId();
            SessionUtils.openSession(phaseEvent.getFacesContext());
            if(!currentPage.contains("home.xhtml") &&
                    SessionUtils.getStatusSession()) {
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect("home.xhtml?faces-redirect=true");
                
                NavigationHandler navigationHandler = phaseEvent.getFacesContext()
                        .getApplication()
                        .getNavigationHandler();
                navigationHandler.handleNavigation(phaseEvent.getFacesContext(), null, "home");
            }
        } catch (IOException ex) {
                    Logger.getLogger(AuthorizationListener.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    @Override
    public void beforePhase(PhaseEvent pe) {}

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
    
    
}
