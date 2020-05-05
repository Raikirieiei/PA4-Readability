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

/**
 * a GUI class for Flesch Index Readability application, this class handle all of the GUI include EventHandler, etc.
 * @author Thornthep Chomchuen
 */
public class GraphicalUI extends Application {
    // text field that user input file or url.
    private TextField fileField;
    // help menu that show author name.
    private MenuItem helpMenu;
    // text area that show result of counting and readability.
    private static TextArea showResult;
    // browse button to select a file.
    private Button browse;
    // calculate button to counting and calculate readability.
    private Button calculate;
    // clear button to clear textfield and textarea.
    private Button clear;
    // exit button to exit program.
    private Button exit;
    // file chooser for browse button.
    private FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane ui = initiate();
        Pane ui2 = initiate2();
        MenuBar menuBar = MakeMenuBar();
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, ui, ui2);
        Scene scene = new Scene(root, 550, 375);
        scene.getStylesheets().add("readability/UIStyle.css"); // able to design the program from css sheet.
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Flesch Readability by Thornthep Chomchuen");
        primaryStage.show();
    }

    // initiate pane1 for top area
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

    // initiate pane2 for bottom area
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
    /**
     * method to make menubar for help.
     * @return created menubar.
     */
    private MenuBar MakeMenuBar() {
        MenuBar menuBar = new MenuBar();
        helpMenu = new MenuItem("About");
        helpMenu.setOnAction(this::HelpHandler);
        Menu menu = new Menu("Help");
        menu.getItems().add(helpMenu);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    /**
     * Event handler for browse button, when user click this button it will pop up file chooser screen.
     * @param event
     */
    private void BrowseHandler(ActionEvent event) {
        try {
        showResult.setStyle("-fx-border-color: white;");
        fileField.setStyle("-fx-border-color: white");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"); // to only read TXT file.
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        fileField.setText(selectedFile.getPath());
        } catch (Exception e) {}
    }

    /**
     * Event handler for calculate button, when user click this button the program will counting and calculate readability
     * and show the result in text area.
     * @param event
     */
    private void CalculateHandler(ActionEvent event) {
        FleschReadability flesch = new FleschReadability();
        try {
            ReadAndCount read = new ReadAndCount();
            showResult.clear();
            showResult.setStyle("-fx-border-color: blue;");
            fileField.setStyle("-fx-border-color: blue");
            read.Read(fileField.getText());
            String readability = flesch.GradeCalculator(flesch.IndexCalculator(fileField.getText()));
            showResult.setText(PrintResult(fileField.getText(), read.getSyllable(), read.getWord(),
                    read.getSentence(), flesch.IndexCalculator(fileField.getText()), readability));
        } catch (Exception e) {
            fileField.clear();
            fileField.setPromptText("Please select a file");
            fileField.setStyle("-fx-border-color: red;");
        }
    }

    /**
     * Event handler for clear button, when use click this button the program will clear all text 
     * in text field and text area.
     * @param event
     */
    private void ClearHandler(ActionEvent event) {
        showResult.setStyle("-fx-border-color: white;");
        fileField.setStyle("-fx-border-color: white");
        fileField.clear();
        showResult.clear();
    }

    /**
     * Event handler for exit button, when user click this button the program will close.
     * @param event
     */
    private void ExitHandler(ActionEvent event) {
        System.exit(1);
    }

    /**
     * Event handler for about menu, when user click this menu it will pop up a box of text.
     * the text will show author name and version of program.
     * @param event
     */
    private void HelpHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.NONE, "Author : Thornthep Chomchuen\nVersion : 1.0", ButtonType.CLOSE);
        alert.setTitle("About");
        alert.show();
    }

    /**
     * the method to return result in String.
     * @param filename file or url from user input.
     * @param syl counted syllables.
     * @param word counted words.
     * @param sent counted sentences.
     * @param index calculated flesch readability index.
     * @param flesch result from flesch reability.
     * @return String of result
     */
    private String PrintResult(String filename, int syl, int word, int sent, double index, String flesch) {
        return "File :               " + filename + "\n" 
            + "Syllables :      " + syl + "\n" 
            + "Words :          " + word + "\n"
            + "Sentences :    " + sent + "\n" 
            + "Flesch Index : " + String.format("%.2f", index) + "\n" 
            + "Readability :   " + flesch;
    }

    /**
     * print prompt error from message.
     * @param message error message.
     */
    public void error(String message) {
        showResult.setPromptText(message);
        showResult.setStyle("-fx-border-color: red;");
    }

    /**
     * popup error from message.
     * @param message error message.
     */
    public void PopupError(String message){
        Alert alert = new Alert(Alert.AlertType.NONE, message, ButtonType.CLOSE);
        alert.setTitle("Error");
        alert.show();
    }
    
    /**
     * main method to run program.
     * @param args user input.
     */
    public void main(String[] args) {
        launch(args);
    }
}
