/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Corerid
 */
public class addRoom {
    public static void main(String[] args) {

        Room s;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        for(int i = 1; i<51; i++){
            s = new Room(i, "Available");
            em.persist(s);
        }
  
        em.getTransaction().commit();

    }

}
