/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import utils.SessionUtils;
import model.User;
import persistence.UserDAO;
/**
 *
 * @author wenkary
 */
@ManagedBean
@ViewScoped
public class UserBean {
    
    private UserDAO userDAO;
    private User user;
    
    public UserBean() {
        this.userDAO = new UserDAO();
        this.user = new User();
    }
    
    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public UserDAO getLoginDAO() {
        return userDAO;
    }
    //  Exception while loading the app : java.lang.IllegalStateException: ContainerBase.addChild: start: org.apache.catalina.LifecycleException: java.lang.RuntimeException: com.sun.faces.config.ConfigurationException: java.util.concurrent.ExecutionException: com.sun.faces.config.ConfigurationException: Unable to parse document 'jndi:/server/persons-web-jsf/WEB-INF/faces-config.xml': null

    public void setLoginDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    // actions
    
    
    
    
    
    public String login() {
        User currentUser = this.userDAO.login(this.user.getUsername(), this.user.getPassword());
        
        if(currentUser == null)
            return "index?faces-redirect=true";
        
        SessionUtils.openSession(FacesContext.getCurrentInstance());
        SessionUtils.setSessionData(currentUser.getUsername(), currentUser.getId().toString());
        return "home?faces-redirect=true";
    }
    
    public String logout() {
        try {
            SessionUtils.openSession(FacesContext.getCurrentInstance());
            SessionUtils.closeSesion();
            return "index?faces-redirect=true";
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void register(){
    userDAO.register(this.user);
    this.user = new User();
    
    }
    
    
    
    
    
}
