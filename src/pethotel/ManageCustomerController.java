/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class ManageCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void changeToAddCustomer(ActionEvent event) throws IOException{
        
        Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        Scene ReserveLocker = new Scene(reserveLockerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ReserveLocker);
        window.show();
    }
    
    @FXML
    public void changeToAddPet(ActionEvent event) throws IOException{
        
        Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("AddPet.fxml"));
        Scene ReserveLocker = new Scene(reserveLockerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ReserveLocker);
        window.show();
    }
    
    @FXML
    public void back(ActionEvent event) throws IOException{
        
        Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
        Scene ReserveLocker = new Scene(reserveLockerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ReserveLocker);
        window.show();
    }
    
    public void changToShowCustomerList(ActionEvent event) throws IOException{
        
        Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("ShowCustomerList.fxml"));
        Scene ReserveLocker = new Scene(reserveLockerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ReserveLocker);
        window.show();
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
