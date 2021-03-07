package JavaFX;

import Minesweeper.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    Stage window;
    Scene startingScreen, gameScreen;
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

        startGame.setOnAction(e -> {
            if ((widthField.getText() != null && !widthField.getText().isEmpty())) {
                if((heightField.getText() != null && !heightField.getText().isEmpty())){
                    int width = Integer.parseInt(widthField.getText());
                    int height = Integer.parseInt(heightField.getText());
                    int nrBombs = Integer.parseInt(bombsField.getText());

                    if(width < 5 || width > 25){return;}
                    if(height < 5 || height > 25){return;}
                    if(nrBombs >= width * height){return;}

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

                    Game playingGame = new Game(width, height, nrBombs);

                    gameGrid.setOnMouseClicked(mouseEvent -> {
                        int mouseX = (int) mouseEvent.getSceneX();
                        int mouseY = (int) mouseEvent.getSceneY();

                        int xCell = mouseX / 40;
                        int yCell = mouseY / 40;

                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {

                            playingGame.makeMove(xCell, yCell);

                        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            playingGame.setFlag(xCell, yCell);
                        }

                        for (int xIndex = 0; xIndex < width; xIndex++) {
                            for (int yIndex = 0; yIndex < height; yIndex++) {

                                char content = playingGame.getCellContent(xIndex, yIndex);
                                ImageView cellImage = null;

                                if (content == 'b') {
                                    cellImage = new ImageView(bomb);
                                }
                                if (content == 'h') {
                                    cellImage = new ImageView(hidden);
                                }
                                if (content == 'f') {
                                    cellImage = new ImageView(flag);
                                }
                                if (content == '1') {
                                    cellImage = new ImageView(one);
                                }
                                if (content == '2') {
                                    cellImage = new ImageView(two);
                                }
                                if (content == '3') {
                                    cellImage = new ImageView(three);
                                }
                                if (content == '4') {
                                    cellImage = new ImageView(four);
                                }
                                if (content == '5') {
                                    cellImage = new ImageView(five);
                                }
                                if (content == '6') {
                                    cellImage = new ImageView(six);
                                }
                                if (content == '7') {
                                    cellImage = new ImageView(seven);
                                }
                                if (content == '8') {
                                    cellImage = new ImageView(eight);
                                }
                                if (content == '0') {
                                    cellImage = new ImageView(zero);
                                }

                                GridPane.setConstraints(cellImage, xIndex, yIndex);
                                gameGrid.getChildren().add(cellImage);
                            }
                        }

                        if (playingGame.hasLost()) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Game Lost");
                            alert.setHeaderText("You lost the game");
                            alert.setContentText("Oops, you clicked on a bomb! Restart the program to try again");

                            Optional<ButtonType> result = alert.showAndWait();
                            if(result.isEmpty()){
                                Platform.exit();
                                System.exit(0);
                            }else if(result.get() == ButtonType.OK){window.setScene(startingScreen);}
                        }

                        if (playingGame.hasWon()) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Game Won");
                            alert.setHeaderText("You Won the game!");
                            alert.setContentText("Great job! If you want a bigger challenge, restart the program and increase the size and number of bombs!");

                            Optional<ButtonType> result = alert.showAndWait();
                            if(result.isEmpty()){
                                Platform.exit();
                                System.exit(0);
                            }else if(result.get() == ButtonType.OK){window.setScene(startingScreen);}
                        }
                    });

                    gameScreen = new Scene(gameGrid, xDim, yDim);
                    window.setScene(gameScreen);
                    }
                }
            });

        startingScreen = new Scene(startingGrid, 300, 150);

        window.setScene(startingScreen);
        window.setTitle("Minesweeper");
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
