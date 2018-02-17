/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import Pet.Pet;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
public class CheckOutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<CheckOutTable> table;

    @FXML
    private TableColumn roomNumber;

    @FXML
    private TableColumn name;

    @FXML
    private TableColumn petName;

    @FXML
    private TableColumn checkIn;

    @FXML
    private TableColumn<CheckOutTable, String> checkOut;

    @FXML
    private TableColumn price;
    
    @FXML
    private JFXTextField roomNoSearch;

    @FXML
    private JFXTextField nameSearch;

    @FXML
    private Button back;
    
    static public int roomNumber_checkout;
    static public String customerName_checkout;
    static public String petName_checkout;
    static public String checkIn_checkout;
    static public String checkOut_checkout;
    static public String price_checkout;
    
    @FXML
    private void checkOut(MouseEvent event) throws IOException
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            
            roomNumber_checkout = table.getSelectionModel().getSelectedItem().getRoomNumber();
            customerName_checkout = table.getSelectionModel().getSelectedItem().getName();
            petName_checkout = table.getSelectionModel().getSelectedItem().getPetName();
            checkIn_checkout = table.getSelectionModel().getSelectedItem().getCheckIn();
            checkOut_checkout = table.getSelectionModel().getSelectedItem().getCheckOut();
            price_checkout = String.valueOf(table.getSelectionModel().getSelectedItem().getPrice());
            
            Parent staffDashBoard = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
            Scene StaffDashBoard = new Scene(staffDashBoard);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            
            //popup 
            Stage stage2 = new Stage();
            Parent root2 = FXMLLoader.load(getClass().getResource("PopupCheckOutSuccess.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.initOwner(table.getScene().getWindow());
            stage2.showAndWait();
            
            //wait for return and change scene
            
            window.setScene(StaffDashBoard);
            window.show();

        }
        
    }
    
    public void back(ActionEvent event) throws IOException{
        
        Parent staffDashBoard = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
        Scene StaffDashBoard = new Scene(staffDashBoard);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(StaffDashBoard);
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

    @FXML
    private void searchRoomNo(KeyEvent event){
     	
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Pet s WHERE s.isStay = true", Pet.class);


        List<Pet> myList = new ArrayList<Pet>();
        List<CheckOutTable> CheckoutTbl = new ArrayList<CheckOutTable>();
        CheckOutTable x ;
        myList = q1.getResultList();
        
       for(Pet i : myList){
            x = new CheckOutTable(i);
            CheckoutTbl.add(x);
        }
        

        ObservableList<CheckOutTable> results2 = FXCollections.<CheckOutTable>observableArrayList(CheckoutTbl);
        
        FilteredList filter = new FilteredList(results2, e->true);
        
        roomNoSearch.textProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate((Predicate<? super CheckOutTable>)(CheckOutTable std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(String.valueOf(std.getRoomNumber()).contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter);
    }
    
    @FXML
    private void searchName(KeyEvent event){

        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Pet s WHERE s.isStay = true", Pet.class);


        List<Pet> myList = new ArrayList<Pet>();
        List<CheckOutTable> CheckoutTbl = new ArrayList<CheckOutTable>();
        CheckOutTable x ;
        myList = q1.getResultList();
        
        for(Pet i : myList){
            x = new CheckOutTable(i);
            CheckoutTbl.add(x);
        }
        

        ObservableList<CheckOutTable> results2 = FXCollections.<CheckOutTable>observableArrayList(CheckoutTbl);
        
        FilteredList filter = new FilteredList(results2, e->true);
        
        nameSearch.textProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate((Predicate<? super CheckOutTable>)(CheckOutTable std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(String.valueOf(std.getName()).contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter);
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        name.setCellValueFactory(new PropertyValueFactory<CheckOutTable , String>("name"));
        petName.setCellValueFactory(new PropertyValueFactory<CheckOutTable , String>("petName"));
        checkIn.setCellValueFactory(new PropertyValueFactory<CheckOutTable, String>("checkIn"));
        checkOut.setCellValueFactory(new PropertyValueFactory<CheckOutTable, String>("checkOut"));
        price.setCellValueFactory(new PropertyValueFactory<CheckOutTable, String>("price"));
        
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Pet s WHERE s.isStay = true", Pet.class);


        List<Pet> myList = new ArrayList<Pet>();
        List<CheckOutTable> CheckoutTbl = new ArrayList<CheckOutTable>();
        CheckOutTable x ;
        myList = q1.getResultList();
        
       for(Pet i : myList){
            x = new CheckOutTable(i);
            CheckoutTbl.add(x);
        }
        

        ObservableList<CheckOutTable> results2 = FXCollections.<CheckOutTable>observableArrayList(CheckoutTbl);
        table.setItems(results2);


    } 
    
    
}

