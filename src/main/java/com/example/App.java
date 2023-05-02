package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {
    Stage window;
    Scene scene1, scene2, sceneBonus;
    VBox root, layout2, layout4;

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        primaryStage.setTitle("Weather App - KLOCK Rony");

        Button viewDataButton = new Button("View data");
        viewDataButton.setOnAction(e -> {
            try {
                URL url = new URL(
                        "http://api.weatherstack.com/current?access_key=8c062f723e6a3f9c916a4dbfd87a77e7&query=New%20York");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                Label dataLabel = new Label(response.toString());
                layout2.getChildren().add(dataLabel);
                window.setScene(scene1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button downloadFileButton = new Button("Download file");
        downloadFileButton.setOnAction(e -> {
            try {
                URL url = new URL(
                        "http://api.weatherstack.com/current?access_key=8c062f723e6a3f9c916a4dbfd87a77e7&query=New%20York");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showSaveDialog(window);
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button updateWeatherButton = new Button(" Update weather");
        updateWeatherButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

            }

        });

        Button bonusButton = new Button("Bonus");
        bonusButton.setOnAction(e -> {
            Stage bonusStage = new Stage();
            bonusStage.setTitle("Bonus - Tic Tac Toe");

            VBox bonusLayout = new VBox(20);
            bonusLayout.setAlignment(Pos.CENTER);
            bonusLayout.setPadding(new Insets(25));

            Label bonusLabel = new Label("Welcome to the tic tac toe game !");
            Button closeButton = new Button("Close window");
            closeButton.setOnAction(ev -> bonusStage.close());

            bonusLayout.getChildren().addAll(bonusLabel, closeButton);
            Scene bonusScene = new Scene(bonusLayout, 300, 200);

            bonusStage.setScene(bonusScene);
            bonusStage.show();
        });

        // Config scène1
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.getChildren().addAll(viewDataButton, downloadFileButton, updateWeatherButton, bonusButton);

        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.setPadding(new Insets(25));

        Label label2 = new Label("This is the second scene");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> window.setScene(scene1));

        scene1 = new Scene(layout2, 300, 200);
        layout2.getChildren().addAll(backButton, label2);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}