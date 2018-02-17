/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;


import Pet.Pet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;

/**
 *
 * @author natht
*/
@Entity
public class Room {
    
    @Id
    private int roomNumber;      //Primary Key 
    private String stat;            //Empty,Full
    
    @OneToOne
    private Pet pet;
    

    public Room(int roomNumber, String stat) {
        this.roomNumber = roomNumber;
        this.stat = stat;
        
        
    }
    
    public Room(){
        
    }
    
    public Room(int roomNumber, String roomClass, String stat, Pet pet){
        this.roomNumber = roomNumber;
        this.stat = stat;
        this.pet = pet;
        this.addPet(pet);
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
    
    public float Cost(int day){  //Hour
        return 1000*day;
    }
        
    public void AddRoom(){
       
        /////////////////////
        
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }


    public String getStat() {
        return stat;
    }
    
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
    
   public void addPet(Pet pet){
       this.pet = pet;
       this.pet.setRoom(this);
   } 
    
    
}
