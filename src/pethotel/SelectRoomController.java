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
import Pet.Pet;
import Room.Room;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static pethotel.SelectPetAndDateController.checkIn_String;
import static pethotel.SelectPetAndDateController.checkOut_String;
import static pethotel.SelectPetAndDateController.petIDFromSelectPet;
import static pethotel.SelectPetAndDateController.totalDay;

/**
 * FXML Controller class
 *
 * @author natht
 */
public class SelectRoomController implements Initializable {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Room> tbl;
    
    @FXML
    private JFXComboBox  ChoosePet;
    
    @FXML
    private TableColumn<Room,String> roomNumber_tbl;

    @FXML
    private TableColumn<Room,String> roomClass_tbl;

    @FXML
    private TableColumn<Room,String> roomStat_tbl;

    @FXML
    private JFXComboBox roomClass;

    @FXML
    private JFXTextField name;
    
    @FXML
    private TextField class_ts;

    @FXML
    private JFXTextField id;
    
    @FXML
    private JFXDatePicker checkIn;

    @FXML
    private JFXDatePicker checkOut;
    
    @FXML
    private Label totalDay_S;
    
    @FXML
    private Label totalPrice;
    
    @FXML
    private JFXButton submitButton;
    
    @FXML
    private Label petName;

    @FXML
    private Label customerName;

    @FXML
    private Label roomNo;

    /**
     * Initializes the controller class.
     */

    static public String totalPrice_label;
    static public float totalPrice_DB;
    

    ReserveRoomController obj = new ReserveRoomController();
    SelectPetAndDateController selectPetObj = new SelectPetAndDateController();
    
    static public int selected_roomNum;
    static public String selected_pet;
    static public String selected_roomType;
    
    int statCP = 0;
       
    @FXML
    private void chooseRoom(MouseEvent event){
        
      if (event.getClickCount() == 2) //Checking double click
        {
            selected_roomNum = tbl.getSelectionModel().getSelectedItem().getRoomNumber();   //Choose  RoomNumber 
            System.out.println(selected_roomNum);
            
            String roomNumber_S = String.valueOf(tbl.getSelectionModel().getSelectedItem().getRoomNumber());
            
            Room obj;

            obj = new Room(); 
            
            float priceBefore = obj.Cost(selectPetObj.totalDay);
            
            ////////////////////
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
            EntityManager em2 = emf2.createEntityManager();
                
            em2.getTransaction().begin();
            em2.getMetamodel().entity(Customer.class);
        
            Query q4 = em2.createQuery("select plan from Customer where primaryKey = :PrimaryKey_s", Customer.class);
            String customer_plan = q4.setParameter("PrimaryKey_s",ReserveRoomController.primaryKey_tbl).getSingleResult().toString();
            
            Customer obj2;
            switch(customer_plan){
                case "Silver" : obj2 = new Customer_Silver();    break;
                case "Gold" : obj2 = new Customer_Gold();    break;
                case "Platinum" : obj2 = new Customer_Platinum();  break;
                default :   obj2 = new Customer_Silver(); break;  
            }
            
            obj2.setCost(priceBefore); //set cost
            totalPrice_DB = obj2.realCost();

            totalPrice_label = Float.toString(obj2.realCost());
            

            totalPrice.setText(totalPrice_label+" Bath");
            
            roomNo.setText(roomNumber_S);
         
        }
    
        
    }
 
    @FXML
    private void selectType(ActionEvent event){

        roomClass.getValue().toString();
        
        if(roomClass.getValue().toString().contains("All")){
            Room s;

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/RoomDB.odb");		
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            em.getMetamodel().entity(Room.class); // Query empty database

            Query q1 = em.createQuery("SELECT s FROM Room s");

            List<Room> results = q1.getResultList();
            ObservableList<Room> results2 = FXCollections.<Room>observableArrayList(results);

            tbl.setItems(results2);
        }
        
        else{
            Room s;

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/RoomDB.odb");		
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            em.getMetamodel().entity(Room.class); // Query empty database

            Query q1 = em.createQuery("SELECT s FROM Room s where roomClass = :p");

            List<Room> results = q1.setParameter("p", roomClass.getValue().toString()).getResultList();
            ObservableList<Room> results2 = FXCollections.<Room>observableArrayList(results);

            tbl.setItems(results2);
        }
    }
    
    
    @FXML
    private void submit(ActionEvent event) throws IOException{

        System.out.println("SUBMIT!");

        EntityManagerFactory emf6 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em6 = emf6.createEntityManager();
        
        em6.getTransaction().begin();
        
        Query q12 = em6.createQuery("select stat from Room where roomNumber = :RoomNumber",Room.class);
        String status_s = q12.setParameter("RoomNumber",selected_roomNum).getSingleResult().toString();
        System.out.println(status_s);
        em6.close();
        emf6.close();
        //////////////////////////////////////////////////
        
        if(status_s.contains("Full")){
            System.out.println("FULL!!");
            //popup
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupFullRoom.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(submitButton.getScene().getWindow());                
            stage2.showAndWait();  
        }
        else{
                     ///////////////////////////////////////////
            Customer checkStayed;
            
            EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
            EntityManager em3 = emf3.createEntityManager();
            em3.getTransaction().begin();
            checkStayed = em3.find(Customer.class, ReserveRoomController.primaryKey_tbl);
             System.out.println(checkStayed.getStayed());
             
            if(checkStayed.getStayed() == 3 || checkStayed.getStayed() == 5){

            ///////////////////////////////////////////
     
            Room s0;
            Pet a0, pet_before;
            Customer b0;
            int preCustomerPrimaryKey, preCustomerStayed;
            String preCustomerName, preCustomerTel, preCustomerEmail, preCustomerIdCardNumber, preCustomerPlan;
            
            EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
            EntityManager em1 = emf1.createEntityManager();

            /////// process ////////
            
            List<Pet> petBeforeList = new ArrayList<>();
            List<Integer> petIdBeforeList = new ArrayList<>();
            
            b0 = em1.find(Customer.class, ReserveRoomController.primaryKey_tbl);
            petBeforeList = b0.getPets();
            preCustomerPrimaryKey = b0.getPrimaryKey();
            preCustomerName = b0.getName();
            preCustomerTel = b0.getTel();
            preCustomerEmail = b0.getEmail();
            preCustomerIdCardNumber = b0.getIdcardNumber();
            preCustomerPlan = b0.getPlan();
            preCustomerStayed = b0.getStayed();
            
            em1.getTransaction().begin();
            for(Pet i: petBeforeList){
                System.out.println(i.getOwner().getName());
                i.setCustomer(null);
                em1.persist(i);
            }
            em1.getTransaction().commit();
            
            EntityManagerFactory emf4 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
            EntityManager em4 = emf4.createEntityManager();
            
            em4.getTransaction().begin();
            
            Query q1 = em4.createQuery("DELETE FROM Customer s WHERE s.primaryKey =: pm");
            q1.setParameter("pm", ReserveRoomController.primaryKey_tbl).executeUpdate();
            
            em4.getTransaction().commit();

            //-------------------------------------
            
            Customer newCustomer;

            em1.getTransaction().begin();
            
            switch(preCustomerPlan){
                
                case "Silver" : newCustomer = new Customer_Gold(preCustomerPrimaryKey, preCustomerName, preCustomerTel, preCustomerEmail, preCustomerIdCardNumber, "Gold");    break;
                case "Gold" : newCustomer = new Customer_Platinum(preCustomerPrimaryKey, preCustomerName, preCustomerTel, preCustomerEmail, preCustomerIdCardNumber, "Platinum");    break;
                case "Platinum" : newCustomer = new Customer_Platinum(preCustomerPrimaryKey, preCustomerName, preCustomerTel, preCustomerEmail, preCustomerIdCardNumber, "Platinum");  break;
                default :   newCustomer = new Customer_Silver(preCustomerPrimaryKey, preCustomerName, preCustomerTel, preCustomerEmail, preCustomerIdCardNumber, "-");
                
            }
            em1.persist(newCustomer);
            em1.getTransaction().commit();
            
            
            //-------------------------------------
            em1.getTransaction().begin();
            newCustomer.setStayed(preCustomerStayed);
            Customer newCus;
            newCus = em1.find(Customer.class, preCustomerPrimaryKey);
            for(Pet i: petBeforeList){
                i.setCustomer(newCus);
                em1.persist(i);
                newCus.addPet(i);
            }
            
            em1.getTransaction().commit();
            //////////////////////
            
            Room s;
            Pet a;
            Customer b;
           
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
            EntityManager em2 = emf2.createEntityManager();
          
            em2.getTransaction().begin();
          
            a = em2.find(Pet.class, SelectPetAndDateController.petIDFromSelectPet);
            System.out.println(a.getName());           
            s = em2.find(Room.class, selected_roomNum);
            System.out.println(s.getRoomNumber());

            a.setCheckIn(checkIn_String);
            a.setCheckOut(checkOut_String);
            a.setPrice(totalPrice_DB);
            a.setTotalDay(totalDay);
            
            b = em2.find(Customer.class, ReserveRoomController.primaryKey_tbl);
            float customerCost = b.getCost();
            b.setCost(totalPrice_DB + customerCost);
            b.setStayed(b.getStayed()+1);
            em2.persist(b);
            System.out.println("Cost :"+totalPrice_DB);
   

            s.addPet(a);
            em2.persist(s);
            
            s.setStat("Full");
            em2.persist(s);
            
            a.setIsStay(true);
            em2.persist(a);
                       
            em2.getTransaction().commit();

            //// back to dash board ////

            Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
            Scene StaffDashBoard = new Scene(reserveLockerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            //popup 
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupSuccessReserveRoom.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(submitButton.getScene().getWindow());
            stage2.showAndWait();

            //wait for return and change scene

            window.setScene(StaffDashBoard);
            window.show();
            } //close if
            else{
            Room s;
            Pet a;
            Customer b;
           
            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
            EntityManager em2 = emf2.createEntityManager();
          
            em2.getTransaction().begin();
          
            a = em2.find(Pet.class, SelectPetAndDateController.petIDFromSelectPet);
            System.out.println(a.getName());           
            s = em2.find(Room.class, selected_roomNum);
            System.out.println(s.getRoomNumber());

            a.setCheckIn(checkIn_String);
            a.setCheckOut(checkOut_String);
            a.setPrice(totalPrice_DB);
            a.setTotalDay(totalDay);
            
            b = em2.find(Customer.class, ReserveRoomController.primaryKey_tbl);
            float customerCost = b.getCost();
            b.setCost(totalPrice_DB + customerCost);
            b.setStayed(b.getStayed()+1);
            em2.persist(b);
            System.out.println("Cost :"+totalPrice_DB);
   

            s.addPet(a);
            em2.persist(s);
            
            s.setStat("Full");
            em2.persist(s);
            
            a.setIsStay(true);
            em2.persist(a);
                       
            em2.getTransaction().commit();

            //// back to dash board ////

            Parent reserveLockerParent = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
            Scene StaffDashBoard = new Scene(reserveLockerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            //popup 
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupSuccessReserveRoom.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(submitButton.getScene().getWindow());
            stage2.showAndWait();

            //wait for return and change scene

            window.setScene(StaffDashBoard);
            window.show();
            }

        } // close if
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException{
        
        Parent selectPet = FXMLLoader.load(getClass().getResource("SelectPetAndDate.fxml"));
        Scene SelectPet = new Scene(selectPet);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(SelectPet);
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
              
        roomNumber_tbl.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomStat_tbl.setCellValueFactory(new PropertyValueFactory<Room,String>("stat"));
        
        Room s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Room s");
       
        List<Room> results = q1.getResultList();
        ObservableList<Room> results2 = FXCollections.<Room>observableArrayList(results);
        tbl.setItems(results2);
        
        TableView<Room> table = new TableView<>();
        
        /////// set label //////////////////////////////////////////////////////////////////
        
        customerName.setText(obj.customerName_tbl);
        
        	
	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em2 = emf2.createEntityManager();
                
        em2.getTransaction().begin();
                
        Query q3 = em2.createQuery("SELECT name FROM Pet s where pet_ID = :p");
        petName.setText(q3.setParameter("p", petIDFromSelectPet).getSingleResult().toString());
        totalDay_S.setText(String.valueOf(totalDay));
 
    }    
}
