/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pethotel;


import Pet.Dog;
import Pet.Pet;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import static pethotel.AddPetController.primaryKeyFromAddPet;

/**
 * FXML Controller class
 *
 * @author Corerid
 */
public class AddPetPopupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label customerName;

    @FXML
    private ComboBox type;

    @FXML
    private JFXTextField petName;

    
    @FXML
    private JFXTextField petAge;

    @FXML
    private JFXTextField extra;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton confirmButton;

    public void addPet(ActionEvent event)throws IOException{
        
        String name_s = petName.getText();
        int age_s = Integer.parseInt(petAge.getText());
        String extra_s = extra.getText();
        String type_s = type.getValue().toString();
        
        
        AddPetController obj;
        obj = new AddPetController();
        
        Pet b ;
        
            
        b = new Dog();
        b.addPet(primaryKeyFromAddPet,name_s, type_s, extra_s, age_s);
         
        System.out.println("Add Success!");
            
        Stage stage;
        Parent root;
        
        stage = (Stage)back.getScene().getWindow();
        stage.close();
        
    }
    
    public void back(ActionEvent event) throws IOException{
        
        Stage stage;
        Parent root;
        
        stage = (Stage)back.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AddPetController obj;
        obj = new AddPetController();
        
        customerName.setText(obj.customerNameFromAddPet);
        type.getItems().addAll("Dog", "Cat", "Rabbit");
    }    
    
}
