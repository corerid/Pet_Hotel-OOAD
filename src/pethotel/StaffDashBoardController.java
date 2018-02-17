/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class StaffDashBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton reserveRoom;

    @FXML
    private JFXButton checkOut;

    @FXML
    private JFXButton manageCustomer;

    @FXML
    private JFXButton logOut;
    

    public void changeToManageCustomer(ActionEvent event) throws IOException{

            Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
            Scene LoginCustomer = new Scene(loginCustomerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(LoginCustomer);
            window.show();
    }
    
    @FXML
    public void changeToReserveRoom(ActionEvent event) throws IOException{
        
            Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("ReserveRoom.fxml"));
            Scene LoginCustomer = new Scene(loginCustomerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(LoginCustomer);
            window.show();

    }
    
    @FXML
    public void changeToCheckOut(ActionEvent event) throws IOException{
        
            Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("checkOut.fxml"));
            Scene LoginCustomer = new Scene(loginCustomerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(LoginCustomer);
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
    
    public void changeToAddStaff(ActionEvent event) throws IOException{
        if(!StaffLoginController.isAdmin){
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupNotAdmin.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(checkOut.getScene().getWindow());                
            stage2.showAndWait(); 
        }
        else{
            Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("AddStaff.fxml"));
            Scene LoginCustomer = new Scene(loginCustomerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(LoginCustomer);
            window.show();
        }
    }
    
    public void changeToDeleteStaff(ActionEvent event) throws IOException{
        if(!StaffLoginController.isAdmin){
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupNotAdmin.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(checkOut.getScene().getWindow());                
            stage2.showAndWait(); 
        }
        else{
            Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("DeleteStaff.fxml"));
            Scene LoginCustomer = new Scene(loginCustomerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(LoginCustomer);
            window.show();
        }
    }
    
    public void changeToShowStaff(ActionEvent event) throws IOException{
        if(!StaffLoginController.isAdmin){
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupNotAdmin.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(checkOut.getScene().getWindow());                
            stage2.showAndWait(); 
        }
        else{
            Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("ShowStaffList.fxml"));
            Scene LoginCustomer = new Scene(loginCustomerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(LoginCustomer);
            window.show();
        }
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
