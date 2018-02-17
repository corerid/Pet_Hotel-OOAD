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
import javafx.stage.Modality;
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


public class ShowCustomerListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    

    @FXML
    private JFXTextField kuy;
    
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
    
    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, String> id;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> plan;

    @FXML
    private TableColumn<Customer, String> tel;

    @FXML
    private TableColumn<Customer, String> email;

    @FXML
    private TableColumn<Customer, String> idCardNumber;
    
    @FXML
    private TableColumn<Customer, String> stayed;

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
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter);
    }
    
    @FXML
    private void searchPlan(KeyEvent event){

        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        em.getMetamodel().entity(Customer.class);        
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
        sort2.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter2);
    }
    
    @FXML
    private void searchTel(KeyEvent event){
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        em.getMetamodel().entity(Customer.class);        
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
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter);
    }
    
    @FXML
    private void searchEmail(KeyEvent event){
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        em.getMetamodel().entity(Customer.class);        
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
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter);
    }
    
    @FXML
    private void searchIdCard(KeyEvent event){
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        em.getMetamodel().entity(Customer.class);        
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
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter);
    }
    
    static public int primaryKeyFromshowmore;
    static public String customerNameFromshowmore;
     
    @FXML
    private void showMore(MouseEvent event) throws IOException
    {
        if (event.getClickCount() == 2) //Checking double click
        {

            //getUsername when click at table
            primaryKeyFromshowmore = table.getSelectionModel().getSelectedItem().getPrimaryKey();
            System.out.println(primaryKeyFromshowmore);
            
            customerNameFromshowmore = table.getSelectionModel().getSelectedItem().getName();
            System.out.println(customerNameFromshowmore);

            //popup more customer detail
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("PopupShowCustomerList.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(table.getScene().getWindow());
            stage.showAndWait();
           
        

        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<>("primaryKey"));
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        plan.setCellValueFactory(new PropertyValueFactory<Customer, String>("plan"));
        tel.setCellValueFactory(new PropertyValueFactory<Customer, String>("tel"));
        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        idCardNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("idcardNumber"));
        stayed.setCellValueFactory(new PropertyValueFactory<>("stayed"));
          
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        
        em.getMetamodel().entity(Customer.class); // Query empty database
                
        Query q1 = em.createQuery("SELECT s FROM Customer s");
        
        List<Customer> results = q1.getResultList();
        ObservableList<Customer> results2 = FXCollections.<Customer>observableArrayList(results);
        table.setItems(results2);

        System.out.println(results);
        for (Object p : results2) {
                        System.out.println("SADDD");
			System.out.println(p);
	}
        TableView<Customer> table = new TableView<>();
    }    
    
}
