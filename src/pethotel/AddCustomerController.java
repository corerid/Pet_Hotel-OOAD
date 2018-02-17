/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import Customer.Customer_Gold;
import Customer.Customer_Platinum;
import Customer.Customer_Silver;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ComboBox;
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
public class AddCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */

    
    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField tel;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField idCardNumber;

    @FXML
    private ComboBox plan;

    @FXML
    private JFXButton confirmButton;

    @FXML
    private JFXButton back;
    
    static public String planFromAddCustomerController;
    
    
    @FXML
    public void changToShowCustomerList(ActionEvent event) throws IOException{
        
        Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("ShowCustomerList.fxml"));
        Scene ReserveLocker = new Scene(reserveLockerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ReserveLocker);
        window.show();
    }     
    
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
    
    @FXML
    public void addData(ActionEvent event) throws IOException{
        
        Stage stage;
        Parent root;
        
        String name_s = name.getText();
        String email_s = email.getText();
        String tel_s = tel.getText();
        String idCardNumber_s = idCardNumber.getText();
        String plan_s = plan.getValue().toString();
        planFromAddCustomerController = plan_s;
 
        Customer b ;
        
        int dummyPrime = 1;
            
        switch(plan_s){
                
            case "Silver" : b = new Customer_Silver(dummyPrime, name_s, tel_s, email_s, idCardNumber_s, plan_s);    break;
            case "Gold" : b = new Customer_Gold(dummyPrime, name_s, tel_s, email_s, idCardNumber_s, plan_s);    break;
            case "Platinum" : b = new Customer_Platinum(dummyPrime, name_s, tel_s, email_s, idCardNumber_s, plan_s);  break;
            default :   b = new Customer_Silver(dummyPrime ,name_s, tel_s, email_s, idCardNumber_s, plan_s);
                
        }
            
            
            b.addCustomer(name_s, tel_s, email_s, idCardNumber_s, plan_s);
         
            System.out.println("Yeah!");
            
            Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("ManageCustomer.fxml"));
            Scene manageCustomer = new Scene(reserveLockerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            
            //popup 
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupSuccessAddCustomer.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(confirmButton.getScene().getWindow());
            stage2.showAndWait();
            
            //wait for return and change scene
            
            window.setScene(manageCustomer);
            window.show();
            

    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        plan.getItems().addAll("Silver", "Gold", "Platinum");
    }    
    
}
