package pl.B4GU5.TW;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import pl.B4GU5.Utils.settings;

public class Main extends Application {
	static Stage stage;
	@Override
	public void start(Stage primaryStage) throws IOException {
		stage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		
		Scene scene = new Scene(root, 853, 504);
		stage.setScene(scene);
		stage.setMinHeight(400);
		stage.setMinWidth(700);
		stage.centerOnScreen();
		stage.setTitle("TechnicWorld | Serwery na modach!");
		stage.show();
	}

	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
	}
	public static void changeStageName(String str) {
		stage.setTitle(str);
	}
}
