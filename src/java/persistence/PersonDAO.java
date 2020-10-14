/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import model.Person;

/**
 *
 * @author wenkary
 */
public class PersonDAO {
    
    public void create(Person person){
        Transaction  transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        }
        catch(Exception  e) {
            System.out.println(e.getMessage());
        }
        finally{
           session.close();
 
        }
    }
    
    public void delete(Person person){
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(person);
            transaction.commit();
        }
        catch(Exception  e) {
            System.out.println(e.getMessage());
        }
        finally{
           session.close();
 
        }
    }
    
    public void update(Person person){
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(person);
            transaction.commit();
        }
        catch(Exception  e) {
            System.out.println(e.getMessage());
        }
        finally{
           session.close();
 
        }
    }
    
    public Person getById(Long id) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Person person = null;
        try {
            Query query = session.createQuery("from Person where id = :id");
            query.setParameter("id", id);
            person = (Person) query.uniqueResult();
            transaction.commit();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
           session.close();
 
        }
        return person;
    }
    
    
    public List<Person> getAll(){
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Person> persons = new ArrayList<>();
        try {
             Query query = session.createQuery("from Person");
             persons = (List<Person>) query.list();
        }
        catch(Exception e) {
             System.out.println(e.getMessage());
        }
        finally{
           session.close();
 
        }
        return persons;    
    }
   
}