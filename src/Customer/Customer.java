/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import Pet.Pet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static pethotel.AddCustomerController.planFromAddCustomerController;

/**
 *
 * @author natht
 */
@Entity
public abstract class Customer {
       
        @Id
        private int primaryKey;        //Primary Key
	private String name;
        private String tel;
        private String email;
        private String idcardNumber;
        private String plan;
        
        private int stayed = 0;
        protected float cost = 0;   //cost
        
        @OneToMany(cascade = CascadeType.ALL)
        private List<Pet> pets;

    public Customer(int primaryKey, String name, String tel, String email, String idcardNumber, String plan) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.idcardNumber = idcardNumber;
        this.plan = plan;
        this.pets = new ArrayList<>();
    }
    
    public Customer(){
        
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public String getPlan() {
        return plan;
    }
    
    
    public void addPet(Pet pet) {
        pet.setCustomer(this);
        this.pets.add(pet);

    }
    
    public List<Pet> getPets() {
        return pets;
    }
    
   public List<String> getPetName(){
      List<String> petName ;
      petName = new ArrayList<>();
        for(Pet i:pets){
            petName.add(i.getName());
            
        }
      return petName; 
   }
   
   public List<Integer> getPetID(){
      List<Integer> petID ;
      petID = new ArrayList<>();
        for(Pet i:pets){
            petID.add(i.getPet_ID());
            
        }
      return petID; 
   }

    public float getCost() {
        return cost;
    }

    public int getStayed() {
        return stayed;
    }

    public void setStayed(int stayed) {
        this.stayed = stayed;
    }
    
    
        public void setCost(float cost) {
            this.cost = cost;
        }  //cost
        
        public float discount(){            //calculate discount
            return this.cost * 0;
        }
        
        public float realCost(){
            return this.cost - discount(); 
        }  // Calculate realcost(cost - discount)
        
        
        public void addCustomer(String name, String tel, String email, String idcardNumber, String plan){
            Customer s;

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
            EntityManager em = emf.createEntityManager();
            

            em.getTransaction().begin();
            em.getMetamodel().entity(Customer.class);
            
            Query q3 = em.createQuery("select max(primaryKey) from Customer ");

            System.out.println(q3.getSingleResult());
     
            if( q3.getSingleResult() == null){
                
            switch(planFromAddCustomerController){
                
                case "Silver" : s = new Customer_Silver(1, name, tel, email, idcardNumber, plan);    break;
                case "Gold" : s = new Customer_Gold(1, name, tel, email, idcardNumber, plan);    break;
                case "Platinum" : s = new Customer_Platinum(1, name, tel, email, idcardNumber, plan);  break;
                default :   s = new Customer_Silver(1, name, tel, email, idcardNumber, plan);
                
                }
                em.persist(s);
            }
            else{
                int maxPrimaryKey = (int)q3.getSingleResult();
                   System.out.println(maxPrimaryKey);
                
                switch(planFromAddCustomerController){
                
                case "Silver" : s = new Customer_Silver(maxPrimaryKey+1, name, tel, email, idcardNumber, plan);    break;
                case "Gold" : s = new Customer_Gold(maxPrimaryKey+1, name, tel, email, idcardNumber, plan);    break;
                case "Platinum" : s = new Customer_Platinum(maxPrimaryKey+1, name, tel, email, idcardNumber, plan);  break;
                default :   s = new Customer_Silver(maxPrimaryKey+1, name, tel, email, idcardNumber, plan);
                
                }
                em.persist(s);
            }


            em.getTransaction().commit();
        }
}
