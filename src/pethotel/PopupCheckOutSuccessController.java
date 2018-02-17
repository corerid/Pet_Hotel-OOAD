/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import Pet.Pet;
import Room.Room;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static pethotel.CheckOutController.checkIn_checkout;
import static pethotel.CheckOutController.checkOut_checkout;
import static pethotel.CheckOutController.customerName_checkout;
import static pethotel.CheckOutController.petName_checkout;
import static pethotel.CheckOutController.price_checkout;
import static pethotel.CheckOutController.roomNumber_checkout;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class PopupCheckOutSuccessController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button ok;

    @FXML
    private Label roomNo;

    @FXML
    private Label CustomerName;

    @FXML
    private Label petName;

    @FXML
    private Label checkIn;

    @FXML
    private Label checkOut;

    @FXML
    private Label price;
    
    @FXML
    private Label kuy;
    
    public void back(ActionEvent event) throws IOException{
        
        Stage stage;
        Parent root;
        
        stage = (Stage)ok.getScene().getWindow();
        stage.close();
        
    }
    
    public void submit(ActionEvent event){
        
            EntityManagerFactory emf0 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
            EntityManager em0 = emf0.createEntityManager();  
            
            em0.getTransaction().begin();
            em0.getMetamodel().entity(Room.class);

            Room r;
            r = em0.find(Room.class, roomNumber_checkout);
            
            Pet p = r.getPet();
            Customer c = p.getOwner();
            System.out.println("Old cost: "+c.getCost());
            System.out.println("Now cost: "+p.getPrice());
            
            c.setCost(c.getCost()-p.getPrice());
            em0.persist(c);

            
            p.setIsStay(false);
            p.setRoom(null);
            p.setTotalDay(0);
            p.setPrice(0);
            p.setCheckIn(null);
            p.setCheckOut(null);
            em0.persist(p);

            r.setPet(null);
            r.setStat("Available");
            em0.persist(r);

            em0.getTransaction().commit();
            
            System.out.println("Check Out Success!");
            
            Stage stage;
            Parent root;

            stage = (Stage)ok.getScene().getWindow();
            stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        roomNo.setText(String.valueOf(roomNumber_checkout));
        CustomerName.setText(customerName_checkout);
        petName.setText(petName_checkout);
        checkIn.setText(checkIn_checkout);
        checkOut.setText(checkOut_checkout);
        price.setText(price_checkout);
        
    }    
    
}
