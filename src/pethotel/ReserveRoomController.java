/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author natht
 */
public class ReserveRoomController implements Initializable {
    
      @FXML
    private TableView<Customer> tbl;

    @FXML
    private TableColumn<Customer, String> id_tbl;

    @FXML
    private TableColumn<Customer, String> name_tbl;

    @FXML
    private TableColumn<Customer, String> plan_tbl;

    @FXML
    private TableColumn<Customer, String> tel_tbl;

    @FXML
    private TableColumn<Customer, String> email_tbl;

    @FXML
    private TableColumn<Customer, String> idcardNumber_tbl;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField plan;

    @FXML
    private JFXTextField tel;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField idcardNumber;
    
    ///////////////////////////////////
    
    @FXML
    private JFXTextField nameS;

    @FXML
    private JFXTextField planS;

    @FXML
    private JFXTextField telS;

    @FXML
    private JFXTextField emailS;

    @FXML
    private JFXTextField idCardNumberS;
    

    
     
    static public int primaryKey_tbl;
    static public String customerName_tbl;
    static public String customerPlan_tbl;

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
    public void Back(ActionEvent event) throws IOException{
         Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
        Scene LoginCustomer = new Scene(loginCustomerParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(LoginCustomer);
        window.show();

    }      //BACK BUTTON

    @FXML
    private void search(KeyEvent event){
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        
        em.getMetamodel().entity(Customer.class); // Query empty database
                
        Query q1 = em.createQuery("SELECT s FROM Customer s");
        
        List<Customer> results = q1.getResultList();
        ObservableList<Customer> results2 = FXCollections.<Customer>observableArrayList(results);
        
        FilteredList filter = new FilteredList(results2, e->true);
        
        nameS.textProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate((Predicate<? super Customer>)(Customer std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(std.getName().contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(filter);
    }
    
    @FXML
    private void searchPlan(KeyEvent event){

        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Customer s");
        
        List<Customer> results = q1.getResultList();
        ObservableList<Customer> results2 = FXCollections.<Customer>observableArrayList(results);
        
        FilteredList filter2 = new FilteredList(results2, e->true);
        
        planS.textProperty().addListener((observable, oldValue, newValue)->{
            filter2.setPredicate((Predicate<? super Customer>)(Customer std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(std.getPlan().contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort2 = new SortedList(filter2);
        sort2.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(filter2);
    }
    
    @FXML
    private void searchTel(KeyEvent event){
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Customer s");
        
        List<Customer> results = q1.getResultList();
        ObservableList<Customer> results2 = FXCollections.<Customer>observableArrayList(results);
        
        FilteredList filter = new FilteredList(results2, e->true);
        
        telS.textProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate((Predicate<? super Customer>)(Customer std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(std.getTel().contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(filter);
    }
    
    @FXML
    private void searchEmail(KeyEvent event){
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Customer s");
        
        List<Customer> results = q1.getResultList();
        ObservableList<Customer> results2 = FXCollections.<Customer>observableArrayList(results);
        
        FilteredList filter = new FilteredList(results2, e->true);
        
        emailS.textProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate((Predicate<? super Customer>)(Customer std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(std.getEmail().contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(filter);
    }
    
    @FXML
    private void searchIdCard(KeyEvent event){
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Customer s");

        List<Customer> results = q1.getResultList();
        ObservableList<Customer> results2 = FXCollections.<Customer>observableArrayList(results);
        
        FilteredList filter = new FilteredList(results2, e->true);
        
        idCardNumberS.textProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate((Predicate<? super Customer>)(Customer std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(std.getIdcardNumber().contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(filter);
    }    
    
    @FXML
    private void SelectRoom(MouseEvent event) throws IOException
    {
        if (event.getClickCount() == 2) //Checking double click
        {

            //getUsername when click at table
            primaryKey_tbl = tbl.getSelectionModel().getSelectedItem().getPrimaryKey();
            System.out.println("Customwe Key "+primaryKey_tbl);
            
            customerName_tbl = tbl.getSelectionModel().getSelectedItem().getName();
            System.out.println(primaryKey_tbl);
            
            customerPlan_tbl = tbl.getSelectionModel().getSelectedItem().getPlan();
            

            Parent loginCustomerParent = FXMLLoader.load(getClass().getResource("SelectPetAndDate.fxml"));
            Scene LoginCustomer = new Scene(loginCustomerParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(LoginCustomer);
            window.show();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id_tbl.setCellValueFactory(new PropertyValueFactory<>("primaryKey"));
        name_tbl.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        plan_tbl.setCellValueFactory(new PropertyValueFactory<Customer, String>("plan"));
        tel_tbl.setCellValueFactory(new PropertyValueFactory<Customer, String>("tel"));
        email_tbl.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        idcardNumber_tbl.setCellValueFactory(new PropertyValueFactory<Customer, String>("idcardNumber"));
        
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT s FROM Customer s order by s.primaryKey asc");
        
        List<Customer> results = q1.getResultList();
        ObservableList<Customer> results2 = FXCollections.<Customer>observableArrayList(results);
        tbl.setItems(results2);
        
        TableView<Customer> table = new TableView<>();
        
    }    
    
}
