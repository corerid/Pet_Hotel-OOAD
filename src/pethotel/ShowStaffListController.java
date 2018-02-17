/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import Staff.Staff;
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
public class ShowStaffListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField usernameS;

    @FXML
    private TableView<Staff> table;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn<Staff, String> username;   
    
    @FXML
    private void searchPlan(KeyEvent event){

		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Staff.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        em.getMetamodel().entity(Staff.class);        
        Query q1 = em.createQuery("SELECT s FROM Staff s");
        
        List<Staff> results = q1.getResultList();
        ObservableList<Staff> results2 = FXCollections.<Staff>observableArrayList(results);
        
        FilteredList filter2 = new FilteredList(results2, e->true);
        
        usernameS.textProperty().addListener((observable, oldValue, newValue)->{
            filter2.setPredicate((Predicate<? super Staff>)(Staff std)->{

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                else if(std.getUsername().contains(newValue)){
                    return true;
                }

                return false;
            });
        });
        
        SortedList sort2 = new SortedList(filter2);
        sort2.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filter2);
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
        
        id.setCellValueFactory(new PropertyValueFactory<>("PrimaryKey"));
        username.setCellValueFactory(new PropertyValueFactory<Staff, String>("Username"));
   	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Staff.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        
        em.getMetamodel().entity(Staff.class); // Query empty database
                
        Query q1 = em.createQuery("SELECT s FROM Staff s");
        
        List<Staff> results = q1.getResultList();
        ObservableList<Staff> results2 = FXCollections.<Staff>observableArrayList(results);
        table.setItems(results2);

        System.out.println(results);
        for (Object p : results2) {
			System.out.println(p);
	}
        TableView<Staff> table = new TableView<>();
    }    
    
}
