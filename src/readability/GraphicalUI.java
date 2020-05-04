package readability;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GraphicalUI extends Application {

    private TextField fileField;
    private MenuItem helpMenu;
    private static TextArea showResult;
    private Button browse;
    private Button calculate;
    private Button clear;
    private Button exit;
    private FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane ui = initiate();
        Pane ui2 = initiate2();
        MenuBar menuBar = MakeMenuBar();
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, ui, ui2);
        Scene scene = new Scene(root, 550, 375);
        scene.getStylesheets().add("readability/UIStyle.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Flesch Readability by Thornthep Chomchuen");
        primaryStage.show();
    }

    public FlowPane initiate() {
        FlowPane pane = new FlowPane();
        // set appearance
        pane.setPadding(new Insets(10.0));
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setHgap(10.0);
        pane.setVgap(10.0);
        // set component
        Text text = new Text("Insert file or URL :");
        text.setId("instructtext");
        fileField = new TextField();
        fileField.setId("filefield");
        browse = new Button("Browse");
        browse.setId("browse");
        calculate = new Button("Calculate");
        calculate.setId("calculate");
        clear = new Button("Clear");
        clear.setId("clear");
        // set action
        browse.setOnAction(this::BrowseHandler);
        calculate.setOnAction(this::CalculateHandler);
        clear.setOnAction(this::ClearHandler);
        // add to pane
        pane.getChildren().addAll(text, fileField, browse, calculate, clear);
        return pane;
    }

    // for bottom area
    public FlowPane initiate2() {
        FlowPane pane = new FlowPane();
        // set appearance
        pane.setPadding(new Insets(10.0));
        pane.setAlignment(Pos.BOTTOM_CENTER);
        pane.setHgap(1000.0);
        pane.setVgap(10.0);
        // set component
        showResult = new TextArea();
        showResult.setId("show");
        showResult.setEditable(false);
        exit = new Button("Exit");
        exit.setId("exitbutton");
        // set action
        exit.setOnAction(this::ExitHandler);
        // add to pane
        pane.getChildren().addAll(showResult, exit);
        return pane;
    }

    private MenuBar MakeMenuBar() {
        MenuBar menuBar = new MenuBar();
        helpMenu = new MenuItem("About");
        helpMenu.setOnAction(this::HelpHandler);
        Menu menu = new Menu("Help");
        menu.getItems().add(helpMenu);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void BrowseHandler(ActionEvent event) {
        try{
        showResult.setStyle("-fx-border-color: white;");
        fileField.setStyle("-fx-border-color: white");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        fileField.setText(selectedFile.getPath());
        }catch (Exception e){}
    }

    private void CalculateHandler(ActionEvent event) {
        try{
            showResult.clear();
            showResult.setStyle("-fx-border-color: blue;");
            fileField.setStyle("-fx-border-color: blue");
            String readability = FleschReadability.GradeCalculator(FleschReadability.IndexCalculator(fileField.getText()));
            showResult.setText(PrintResult(fileField.getText(), ReadAndCount.getSyl(), ReadAndCount.getWord(),
                    ReadAndCount.getSent(), FleschReadability.IndexCalculator(fileField.getText()), readability));
            ReadAndCount.ClearCount();
        }catch (Exception e){
            fileField.clear();
            fileField.setPromptText("Please select a file");
            fileField.setStyle("-fx-border-color: red;");
        }
    }

    private void ClearHandler(ActionEvent event) {
        showResult.setStyle("-fx-border-color: white;");
        fileField.setStyle("-fx-border-color: white");
        fileField.clear();
        showResult.clear();
    }

    private void ExitHandler(ActionEvent event){
        System.exit(1);
    }

    private void HelpHandler(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.NONE, "Author : Thornthep Chomchuen\nVersion : 1.0", ButtonType.CLOSE);
        alert.setTitle("About");
        alert.show();
    }

    private String PrintResult(String filename, int syl, int word, int sent, double index, String flesch) {
        return "File :               " + filename + "\n" 
            + "Syllables :      " + syl + "\n" 
            + "Words :          " + word + "\n"
            + "Sentences :    " + sent + "\n" 
            + "Flesch Index : " + index + "\n" 
            + "Readability :   " + flesch;
    }

    public static void error(String message) {
        showResult.setPromptText(message);
        showResult.setStyle("-fx-border-color: red;");
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
