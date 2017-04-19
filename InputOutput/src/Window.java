/**
 * Created by AlexanderKarakulev on 17.04.2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Window extends Application {
    String numbers;

    private void numbersInUserText(String userText) throws RuntimeException{
        boolean errorExists = false;
        for(int i = 0; i < 10; i++) {
            if (userText.contains(String.valueOf(i))) {
                errorExists = true;
                numbers += String.valueOf(i) + "; ";
            }
        }
        if (errorExists) {
            throw new RuntimeException("there are numbers in input: " + numbers);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    final Text actionTarget = new Text();
    final Text userText = new Text();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Input/Output");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        Label input = new Label("Input:");
        grid.add(input, 0, 1);

        Label output = new Label("Output:");
        grid.add(output, 0, 2);

        TextField inputBox = new TextField();
        grid.add(inputBox, 1, 1);

        inputBox.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -40%);");
        inputBox.setPromptText("No numbers, please");//просто для красивости сделал подсказку для пользователя

        grid.add(userText, 1, 2);

        Button btn = new Button("Enter");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    numbers = "";
                    numbersInUserText(inputBox.getText());
                    userText.setText(inputBox.getText());
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText(numbers);
                    grid.add(actionTarget, 1, 6);
                }
            }
        });
        primaryStage.show();
    }
}
