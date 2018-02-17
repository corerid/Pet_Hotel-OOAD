/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Customer.Customer;
import Pet.Pet;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
public class PopupShowCustomerListController implements Initializable {
    
    @FXML
    private TableView<Pet> table;
    
    @FXML
    private TableColumn<Pet, String> type;

    @FXML
    private TableColumn age;

    @FXML
    private TableColumn<Pet, String> extra;

    @FXML
    private TableColumn<Pet, String> nameT;
    
    @FXML
    private Button back;

    @FXML
    private Label name;

    @FXML
    private Label plan;

    @FXML
    private Label tel;

    @FXML
    private Label email;

    @FXML
    private Label idCardNumber;

    public void back(ActionEvent event) throws IOException{
        
        Stage stage;
        Parent root;
        
        stage = (Stage)back.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Customer s;
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Database.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
                
        Query q1 = em.createQuery("SELECT name FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        Query q2 = em.createQuery("SELECT email FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        Query q3 = em.createQuery("SELECT tel FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        Query q4 = em.createQuery("SELECT idcardNumber FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        Query q5 = em.createQuery("SELECT plan FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        

        name.setText(q1.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        String tmp = q1.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString();
        email.setText(q2.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        tel.setText(q3.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        idCardNumber.setText(q4.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        plan.setText(q5.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult().toString());
        
        ////////////////// Table //////////////////////////////////
        List<Pet> myList = new ArrayList<Pet>();
        
        Query q7 = em.createQuery("SELECT s FROM Customer s WHERE s.primaryKey = :PrimaryKey", Customer.class);
        

        Customer cus2 = (Customer)q7.setParameter("PrimaryKey", ShowCustomerListController.primaryKeyFromshowmore).getSingleResult();
        myList = cus2.getPets();
        System.out.println(cus2.getPets());
            
        type.setCellValueFactory(new PropertyValueFactory<Pet, String>("animal"));
        nameT.setCellValueFactory(new PropertyValueFactory<Pet, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        extra.setCellValueFactory(new PropertyValueFactory<Pet, String>("extra"));
        
        ObservableList<Pet> myList2 = FXCollections.<Pet>observableArrayList(myList);
        table.setItems(myList2);
       
        for(Pet i: myList){
            System.out.println(i);
        }
    }    
}
