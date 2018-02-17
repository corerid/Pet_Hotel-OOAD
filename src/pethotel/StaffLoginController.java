/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;

import Staff.Staff;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.control.Label;
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
public class StaffLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public static boolean isAdmin = false;
    
    @FXML
    private JFXPasswordField staffPassword;

    @FXML
    private JFXTextField staffUsername;

    @FXML
    private Label status;

    @FXML
    public void makeLogin(ActionEvent event) throws IOException{
    
        String username = staffUsername.getText().toString();
        String password = staffPassword.getText().toString();
        
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("$dist/db/Staff.odb");		
	EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
        
        Query q2 = em.createQuery("select Username from Staff");
        Query q5 = em.createQuery("SELECT Password FROM Staff s WHERE s.Username = :username", Staff.class);
        Query q6 = em.createQuery("SELECT Status FROM Staff s WHERE s.Username = :username", Staff.class);
        
        List<Staff> results = q2.getResultList();
        
        
        if(results.contains(username)){
            if(q6.setParameter("username", username).getSingleResult().toString().contains("Admin")){
                isAdmin = true;
                q5.setParameter("username", username).getSingleResult();
                String temp = q5.setParameter("username", username).getSingleResult().toString();
                System.out.println(temp);
                System.out.println(password);

                if(password.equals(temp)){

                    Parent CustomerDetailParent = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
                    Scene CustomerDetaileScene = new Scene(CustomerDetailParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(CustomerDetaileScene);
                    window.show();
                }
                else{
                    status.setText("Wrong ID or Password!!");
                }  
            }else{
//            
                q5.setParameter("username", username).getSingleResult();
                String temp = q5.setParameter("username", username).getSingleResult().toString();
                System.out.println(temp);
                System.out.println(password);

                if(password.equals(temp)){

                    Parent CustomerDetailParent = FXMLLoader.load(getClass().getResource("StaffDashBoard.fxml"));
                    Scene CustomerDetaileScene = new Scene(CustomerDetailParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(CustomerDetaileScene);
                    window.show();
                }
                else{
                    status.setText("Wrong ID or Password!!");
                }
            }
        }
        
        
        else{
            status.setText("Wrong ID or Password!!");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        isAdmin = false;
    }    
    
}
