/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pet;

import Customer.Customer;
import Room.Room;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;
import pethotel.AddPetController;

/**
 *
 * @author natht
 */
@Entity
public abstract class Pet implements Serializable {
    
    @Id
    private int pet_ID;  // Primary Key
    //private String Owner;   //Customer
    private String name;
    private String animal;
    private String extra;  // extra thing
    private int age;
    
    private String checkIn;
    private String checkOut;
    private int totalDay = 0;
    private float price;
    
    private boolean isStay = false;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Customer Owner;
    
    @OneToOne
    private Room room;

    public Pet(int pet_ID, String name, String animal, String extra, int age, Customer owner) {
        this.pet_ID = pet_ID;
        this.name = name;
        this.animal = animal;
        this.extra = extra;
        this.age = age;
        
        owner.addPet(this);
        //this.room = null;
    }
    
    public Pet(){
        
    }

    public int getPet_ID() {
        return pet_ID;
    }

//    public String getOwner() {
//        return Owner;
//    }

    public String getName() {
        return name;
    }

    public String getAnimal() {
        return animal;
    }

    public String getExtra() {
        return extra;
    }

    public int getAge() {
        return age;
    }
    
    public void setCustomer(Customer customer){
        this.Owner = customer;
    }

    public boolean isStay() {
        return isStay;
    }

    public void setIsStay(boolean isStay) {
        this.isStay = isStay;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public float getPrice() {
        return price;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Customer getOwner() {
        return Owner;
    }

    public Room getRoom() {
        return room;
    }

    public void setTotalDay(int totalDay) {
        this.totalDay = totalDay;
    }
    

    public void show_info(){
        System.out.println("Pet ID : " + pet_ID + "\nOwner : " + Owner + "\n Name : " + name + "\nAnimal : " + animal + "\nAge : " + age);
    } 
   
    public void how_to(){ //how to take care description deaw overwrite//
        System.out.println("DEAW KOI KID  OverrideXD ");
    }
    
    public void addPet(int owner_ID, String name, String type, String extra, int age){
        Customer c;
        
        
        
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        c = em.find(Customer.class, owner_ID);

            Pet s;
            
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
            EntityManager em2 = emf2.createEntityManager();


            em2.getTransaction().begin();
            Query q3 = em2.createQuery("select max(pet_ID) from Pet ");

            System.out.println(q3.getSingleResult());
            int maxpet_ID = 0;
            
            if(q3.getSingleResult()==null){
                 switch(type){
                
                    case "Cat" : s = new Cat(1, name, type, extra, age, c);    break;
                    case "Dog" : s = new Dog(1, name, type, extra, age, c);    break;
                    case "Rabbit" : s = new Rabbit(1, name, type, extra, age, c);  break;
                    default :   s = new Dog(1 , name, type, extra, age, c);

                }
                
                em.persist(s);
                em.getTransaction().commit();
            }
            else{
                maxpet_ID = (int)q3.getSingleResult();
                System.out.println(maxpet_ID);
                
                switch(type){
                
                    case "Cat" : s = new Cat(maxpet_ID+1, name, type, extra, age, c);    break;
                    case "Dog" : s = new Dog(maxpet_ID+1, name, type, extra, age, c);    break;
                    case "Rabbit" : s = new Rabbit(maxpet_ID+1, name, type, extra, age, c);  break;
                    default :   s = new Dog(maxpet_ID+1 , name, type, extra, age, c);

                }
                
                em.persist(s);
                em.getTransaction().commit();
            }
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
 
}
