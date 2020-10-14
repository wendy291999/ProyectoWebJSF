/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import utils.FacesUtil;
import model.Person;
import utils.SessionUtils;
import model.User;
import persistence.PersonDAO;

/**
 *
 * @author wenkary
 */
@ManagedBean
@ViewScoped
public class PersonBean {
    
    private PersonDAO personDAO;
    private Person person;
    private User user;
    
    public PersonBean() {
        this.person = new Person();
        this.personDAO = new PersonDAO();
        String username = SessionUtils.getUsername();
        String userId = SessionUtils.getUserId();
        this.user = new User();
        this.user.setUsername(username);
        this.user.setId(Long.parseLong(userId));
    }
    // setters and getters
    
    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    // actions
    
    public void create() {
        personDAO.create(person);
        this.resetForm();
    }
    
    public void delete(ActionEvent event) {
        Long personId = FacesUtil.getActionAttributeLong(event, "personId");
        Person  person = new Person();
        person.setId(personId);
        personDAO.delete(person);
    }
    
    public Person getById() {
        Person person = this.personDAO.getById(this.person.getId());
        return person;
    }
    
    public void update() {
        personDAO.update(person);
     
    }
    
    public List<Person> getAll() {
        List<Person> persons = this.personDAO.getAll();
        return persons;
    }
    
    // navigations
    
    public void goToDetails(ActionEvent event) {
        Long personId = FacesUtil.getActionAttributeLong(event, "personId");
        Person  person = this.personDAO.getById(personId);
        this.person = person;
    }
    
    public void resetForm() {
        this.person = new Person();
    }
    
}
