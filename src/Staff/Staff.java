/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Staff;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Corerid
 */
@Entity
public class Staff {
        
        
        private long PrimaryKey;
        private String Username;
	private String Password;
        private String Status;
   
	public Staff(long PrimaryKey, String Username, String Password, String Status){
                this.PrimaryKey = PrimaryKey;
		this.Username = Username;
                this.Password = Password;
                this.Status = Status;
 
	}
        
        public Staff(){
            
        }
        public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}
        
        public String getStatus() {
		return Status;
	}
        
        public long getPrimaryKey(){
            return PrimaryKey;
        }
        
        public void addStaff(String username, String password){
            Staff s;

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Staff.odb");		
            EntityManager em = emf.createEntityManager();
            

            em.getTransaction().begin();
            em.getMetamodel().entity(Staff.class);
            
            Query q1 = em.createQuery("select max(PrimaryKey) from Staff ");

            System.out.println(q1.getSingleResult());
     
            if( q1.getSingleResult() == null){
                s = new Staff(1, username, password, "Staff");
                em.persist(s);
            }
            else{
                long maxPrimaryKey = (long)q1.getSingleResult();
                System.out.println(maxPrimaryKey);

                s = new Staff(maxPrimaryKey+1, username, password, "Staff");
                em.persist(s);
            }


            em.getTransaction().commit();
        }        

        @Override
	public String toString() {
		return String.format("(Username: %s, Password: %s, Status: %s)", 
                        this.Username, this.Password, this.Status);
	}
}
