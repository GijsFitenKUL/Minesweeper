package JavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene startingscreen, gamescreen;
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        GridPane startingGrid = new GridPane();
        startingGrid.setPadding(new Insets(10, 10, 10, 10));
        startingGrid.setVgap(5);
        startingGrid.setHgap(5);

        Label gameWidth = new Label("Set width:");
        TextField widthField = new TextField();
        GridPane.setConstraints(gameWidth, 0, 0);
        GridPane.setConstraints(widthField, 1, 0);

        Label gameHeight = new Label("Set height:");
        TextField heightField = new TextField();
        GridPane.setConstraints(gameHeight, 0, 1);
        GridPane.setConstraints(heightField, 1, 1);

        Label gameBombs = new Label("Set Number of bombs:");
        TextField bombsField = new TextField();
        GridPane.setConstraints(gameBombs, 0, 2);
        GridPane.setConstraints(bombsField, 1, 2);

        Button startGame = new Button("Start game");
        GridPane.setConstraints(startGame,1, 3);

        startingGrid.getChildren().addAll(gameWidth, widthField, gameHeight, heightField, gameBombs, bombsField, startGame);

        startGame.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((widthField.getText() != null && !widthField.getText().isEmpty())) {
                    if((heightField.getText() != null && !heightField.getText().isEmpty())){
                        int width = Integer.parseInt(widthField.getText());
                        int height = Integer.parseInt(heightField.getText());

                        int xDim = width * 40;
                        int yDim = height * 40;

                        GridPane gameGrid = new GridPane();
                        gameGrid.setPadding(new Insets(0, 0, 0, 0));
                        gameGrid.setVgap(0);
                        gameGrid.setHgap(0);

                        Image hidden = new Image("Images/Hidden.png");
                        Image zero = new Image("Images/0.png");
                        Image one = new Image("Images/1.png");
                        Image two = new Image("Images/2.png");
                        Image three = new Image("Images/3.png");
                        Image four = new Image("Images/4.png");
                        Image five = new Image("Images/5.png");
                        Image six = new Image("Images/6.png");
                        Image seven = new Image("Images/7.png");
                        Image eight = new Image("Images/8.png");
                        Image flag = new Image("Images/flagged.png");
                        Image bomb = new Image("Images/bomb.png");

                        for(int xIndex = 0 ;xIndex < width; xIndex++){
                            for(int yIndex = 0; yIndex < height; yIndex++){
                                ImageView i = new ImageView(hidden);
                                GridPane.setConstraints(i, xIndex, yIndex);
                                gameGrid.getChildren().add(i);
                            }
                        }
                        gamescreen = new Scene(gameGrid, xDim, yDim);
                        window.setScene(gamescreen);
                    }
                }
            }
        });

        //layout startingscreen
        startingscreen = new Scene(startingGrid, 300, 150);


        window.setScene(startingscreen);
        window.setTitle("Minesweeper");
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
