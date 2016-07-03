package org.netsim.main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Netsim extends Application {
  private Stage primaryStage;
  private BorderPane rootLayout;
  private final String APP_NAME = "netsim";
  private Group root;

  @Override
  public void start(Stage stage) throws Exception {
    root = new Group();
    primaryStage = stage;
    primaryStage.setTitle(APP_NAME);
    primaryStage.setScene(new Scene(root, 300, 500));
    primaryStage.show();
  }

  public void initRootLayout() {

  }

  @Override
  public void init() throws Exception {
    // TODO Auto-generated method stub
    super.init();
  }

  @Override
  public void stop() throws Exception {
    // TODO Auto-generated method stub
    super.stop();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }

}
