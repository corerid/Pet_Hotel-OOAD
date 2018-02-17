/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Staff.Staff;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class AddStaffController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXPasswordField password;
    
    public void changeToManageCustomer(ActionEvent event) throws IOException{
        
        Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
        Scene LoginCustomer = new Scene(loginCustomerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(LoginCustomer);
        window.show();
    }
    
    @FXML
    public void changeToReserveRoom(ActionEvent event) throws IOException{
        
        Parent reserveRoom = FXMLLoader.load(getClass().getResource("ReserveRoom.fxml"));
        Scene ReserveRoom = new Scene(reserveRoom);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ReserveRoom);
        window.show();  

    }
    
    @FXML
    public void changeToCheckOut(ActionEvent event) throws IOException{
        
        Parent checkOut = FXMLLoader.load(getClass().getResource("checkOut.fxml"));
        Scene CheckOut = new Scene(checkOut);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(CheckOut);
        window.show();
    }
    
    @FXML
    public void logOut(ActionEvent event) throws IOException{
        
        Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene LoginCustomer = new Scene(loginCustomerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(LoginCustomer);
        window.show();
    }
    
    public void addStaff(ActionEvent event) throws IOException{
              
            String name_s = username.getText();
            String email_s = password.getText();
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Staff.odb");		
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            Query q2 = em.createQuery("select Username from Staff");
            
            List<Staff> results = q2.getResultList();
            

            if(name_s.contains("admin")){
                System.out.println("No!");
            }else{
                
                if(results.contains(name_s)){
                    System.out.println("Same Username");
                    
                    Stage stage2 = new Stage();
                    Parent root2 = FXMLLoader.load(getClass().getResource("PopupSameUsername.fxml"));
                    stage2.setScene(new Scene(root2));
                    stage2.initModality(Modality.APPLICATION_MODAL);
                    stage2.initOwner(submit.getScene().getWindow());                
                    stage2.showAndWait();
                    
                }
                else{
            
                    Staff s;
                    s = new Staff();
                    s.addStaff(name_s, email_s);

                    Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
                    Scene manageCustomer = new Scene(reserveLockerParent);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();


                    //popup 
                    Stage stage2 = new Stage();
                    Parent root2 = FXMLLoader.load(getClass().getResource("PopupSuccessAddStaff.fxml"));
                    stage2.setScene(new Scene(root2));
                    stage2.initModality(Modality.APPLICATION_MODAL);
                    stage2.initOwner(submit.getScene().getWindow());
                    stage2.showAndWait();

                    //wait for return and change scene

                    window.setScene(manageCustomer);
                    window.show();
                }
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
