package JavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage StartingScreen) {
        StartingScreen.setTitle("Minesweeper start");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome to MineSweeper!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label boardWidth = new Label("Width");
        Label boardHeight = new Label("Height");
        Label nrOfBombs = new Label("Amount of bombs");

        grid.add(boardWidth, 0, 1);
        grid.add(boardHeight, 0, 2);
        grid.add(nrOfBombs, 0, 3);

        TextField widthField = new TextField();
        TextField heightField = new TextField();
        TextField howManyBombs = new TextField();
        grid.add(widthField, 1, 1);
        grid.add(heightField, 1, 2);
        grid.add(howManyBombs, 1, 3);

        Button btn = new Button("Start game");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        Scene scene = new Scene(grid, 300, 275);
        StartingScreen.setScene(scene);
        StartingScreen.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
