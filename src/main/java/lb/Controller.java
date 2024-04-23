package lb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    
    @FXML
    private Label myLabel;
    @FXML
    private Label myLabel1;
    @FXML
    private TextField myTextField;
    @FXML
    private Button myButton;

    @FXML
    private void handleButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Ответ");
        alert.setHeaderText(null);

        Calculate Calc = new Calculate(myTextField.getText());
        Calc.main(null);

        alert.setContentText(Calc.getRet());

        alert.showAndWait();
    }

}
