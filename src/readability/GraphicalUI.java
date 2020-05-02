package readability;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private TextArea showResult;
    private Button browse;
    private Button calculate;
    private FileChooser fileChooser = new FileChooser();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane ui = initiate();
        Pane ui2 = initiate2();
        MenuBar menuBar = MakeMenuBar();
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, ui, ui2);
        Scene scene = new Scene(root, 550, 350);
        scene.getStylesheets().add("readability/UIStyle.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Flesch Readability");
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
        browse = new Button("Browse");
        calculate = new Button("Calculate");
        // set action
        browse.setOnAction(this::BrowseHandler);
        calculate.setOnAction(this::CalculateHandler);
        // add to pane
        pane.getChildren().addAll(text, fileField, browse ,calculate);
        return pane;
    }
    //for result area
    public FlowPane initiate2() {
        FlowPane pane = new FlowPane();
        // set appearance
        pane.setPadding(new Insets(10.0));
        pane.setAlignment(Pos.BOTTOM_CENTER);
        pane.setHgap(10.0);
        pane.setVgap(10.0);
        // set component
        showResult = new TextArea();
        showResult.setEditable(false);
        // set action
        // add to pane
        pane.getChildren().addAll(showResult);
        return pane;
    }

    private MenuBar MakeMenuBar(){
        MenuBar menuBar = new MenuBar();
        helpMenu = new MenuItem("About");
        Menu menu = new Menu("Help");
        menu.getItems().add(helpMenu);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void BrowseHandler(ActionEvent event){
        File selectedFile = fileChooser.showOpenDialog(null);
        fileField.setText(selectedFile.getPath());
    }
        
    private void CalculateHandler(ActionEvent event){
        showResult.clear();
        String readability = FleschReadability.GradeCalculator(FleschReadability.IndexCalculator(fileField.getText()));
        showResult.setText(PrintResult(fileField.getText(), ReadAndCount.getSyl(), ReadAndCount.getWord()
        , ReadAndCount.getSent(), FleschReadability.IndexCalculator(fileField.getText()), readability));
        ReadAndCount.ClearCount(); 
    }

    private String PrintResult(String filename, int syl, int word, int sent, double index, String flesch) {
        return "File :               " + filename + "\n"
             + "Syllables :      " + syl + "\n" 
             + "Words :          " + word + "\n" 
             + "Sentences :    " + sent + "\n" 
             + "Flesch Index : " + index + "\n" 
             + "Readability :   " + flesch;
    }
    

    public static void main(String[] args){
        launch(args);
    }
}
