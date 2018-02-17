/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author natht
 */
@MappedSuperclass
public class Customer_Silver extends Customer {

    public Customer_Silver(int PrimaryKey, String Name, String Tel, String Email, String IdCardNumber, String Plan) {
        super(PrimaryKey, Name, Tel, Email, IdCardNumber, Plan);
    }

    public Customer_Silver(){
        
    }    
    
    @Override
    public float discount() {
         return (float) (super.cost * .1);
    }
    
    
    
}

    
