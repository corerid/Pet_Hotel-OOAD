/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Pet.Pet;

/**
 *
 * @author Corerid
 */
public class CheckOutTable {
    
    private int roomNumber;
    private String name;
    private String petName;
    private String checkIn;
    private String checkOut;
    private float price;

    public CheckOutTable(Pet pet) {
        
        this.roomNumber = pet.getRoom().getRoomNumber();
        this.name = pet.getOwner().getName();
        this.petName = pet.getName();
        this.checkIn = pet.getCheckIn();
        this.checkOut = pet.getCheckOut();
        this.price = pet.getPrice();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    
    public String getName() {
        return name;
    }

    public String getPetName() {
        return petName;
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
    
}
