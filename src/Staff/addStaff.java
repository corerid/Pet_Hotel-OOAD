/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Staff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Corerid
 */
public class addStaff {
    public static void main(String[] args) {
        
        Staff s;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Staff.odb");		
	EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Query q10 = em.createQuery("DELETE FROM Staff s WHERE s.Password =: pass");
        q10.setParameter("pass", "v").executeUpdate();
//        
//        s = new Staff();
//        s.addStaff("admin", "admin", "Admin");
//	em.persist(s);
//        
//        s = new Staff("gagna", "test1234", "Nattapat Kuanpan", "085214532", "gag@hotmail.com");
//	em.persist(s);
//        
//        s = new Staff("pum", "test1234", "Chutiwat", "0856324152", "pum@hotmail.com");
//	em.persist(s);
//        
//        s = new Staff("tay", "test1234", "Tay Tay", "0123658541", "tay@hotmail.com");
//	em.persist(s);
//        
//        s = new Staff("pungpond", "test1234", "Pung Pond", "0524126368", "pungpond@hotmail.com");
//	em.persist(s);
//
//
	em.getTransaction().commit();
    }
    
}
