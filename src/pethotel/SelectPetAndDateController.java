/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import Pet.Pet;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class SelectPetAndDateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    
    @FXML
    private JFXComboBox  ChoosePet;
    
    @FXML
    private JFXDatePicker checkIn;

    @FXML
    private JFXDatePicker checkOut;
    
    @FXML
    private Label totalDay_S;
    
    @FXML
    private Label plan;

    @FXML
    private Label name;
    
    //static publi
    int statCP = 0;
    List petList2 = new ArrayList(); //name of customer's pet
    List petList3 = new ArrayList(); //pet_id of customer's pet
    List<String> myList = new ArrayList<String>(); //name of customer's pet
    
    int len;
    static public int petIDFromSelectPet;
    static public String checkIn_String;
    static public String checkOut_String;
    static public int totalDay;
    
    ReserveRoomController obj = new ReserveRoomController();

    public void next(ActionEvent event) throws IOException{
        Object pet_s = ChoosePet.getValue();
        int pet_ID = 0;

        Object[] myArray = myList.toArray();

        len = myArray.length;

        for(int i=0;i<len; i++){
            if (pet_s == myList.get(i)){
                pet_ID = (int)petList3.get(i);
            }
        }

        System.out.println(pet_ID);
        
        ////////check pet isStay //////////////////
        Pet s;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        
        s = em.find(Pet.class, pet_ID);
        if(s.isStay() == true){
            System.out.println("This pet's already added!!");
            //popup
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupSamePet.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(checkOut.getScene().getWindow());                
            stage2.showAndWait();
        }
        else{
            petIDFromSelectPet = pet_ID;
            
            Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("SelectRoom.fxml"));
            Scene LoginCustomer = new Scene(loginCustomerParent);
        
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
            window.setScene(LoginCustomer);
            window.show();   
        }
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException{
        
        Parent reserveRoom = FXMLLoader.load(getClass().getResource("ReserveRoom.fxml"));
        Scene ReserveRoom = new Scene(reserveRoom);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(ReserveRoom);
        window.show();
        
    }
    
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        

        Query q7 = em.createQuery("SELECT s FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        
        Customer cus2 = (Customer)q7.setParameter("PrimaryKey", ReserveRoomController.primaryKey_tbl).getSingleResult();
        myList = cus2.getPetName();
        System.out.println(cus2.getPetName());
        
        ChoosePet.getItems().addAll(myList);
        
        name.setText(obj.customerName_tbl);
        plan.setText(obj.customerPlan_tbl);
        
        petList3 = cus2.getPetID();
        System.out.println(cus2.getPetID());
        
 ////////////// Format date ////////////////////
        checkIn.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate t) {
               if(t != null){
                   return formatter.format(t);
               }
               return null;
            }

            @Override
            public LocalDate fromString(String string) {
                if(string != null && !string.trim().isEmpty()){
                    return LocalDate.parse(string, formatter);
                }
                return null;
            }
        });

        checkOut.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate t) {
               if(t != null){
                   return formatter.format(t);
               }
               return null;
            }

            @Override
            public LocalDate fromString(String string) {
                if(string != null && !string.trim().isEmpty()){
                    return LocalDate.parse(string, formatter);
                }
                return null;
            }
        }); 
        ///////////////////////////////////////////////
        
        checkIn.setOnAction((ActionEvent event) -> {
            System.out.println(formatter.format(checkIn.getValue()));
        });
        
        checkOut.setOnAction((ActionEvent event) -> {
            System.out.println(formatter.format(checkOut.getValue()));
            
            LocalDate dateIn = checkIn.getValue();
            LocalDate dateOut = checkOut.getValue();
            
            checkIn_String = checkIn.getValue().toString();
            checkOut_String = checkOut.getValue().toString();

            long totalDay0 = (Duration.between(dateIn.atStartOfDay(), dateOut.atStartOfDay()).toDays())+1;
            
            totalDay = (int)totalDay0;
           
            String totalDay_String = String.valueOf(totalDay); 
            System.out.println(totalDay_String);
                        
            if(totalDay <= 0){
                try {
                    System.out.println("Wrong!!");
                    //popup
                    Stage stage2 = new Stage();
                    Parent root2 = FXMLLoader.load(getClass().getResource("PopupWrongDate.fxml"));
                    stage2.setScene(new Scene(root2));
                    stage2.initModality(Modality.APPLICATION_MODAL);
                    stage2.initOwner(checkOut.getScene().getWindow());                
                    stage2.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(SelectPetAndDateController.class.getName()).log(Level.SEVERE, null, ex);
                }
     
            }
            else{
                totalDay_S.setText(totalDay_String.toString());
            }
        });
        
    }
}
