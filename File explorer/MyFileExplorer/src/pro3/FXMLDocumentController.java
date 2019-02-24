/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro3;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 *
 * @author KsyP
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TreeView<String> myTreeView;
    @FXML
    private TableView<myClass> myTable;
    @FXML
    private TableColumn<myClass, ImageView> str;
    @FXML
    private TableColumn<myClass, String> str2;
    @FXML
    private TableColumn<myClass, Long> str3;
    @FXML
    private TableColumn<myClass, String> str4;
    @FXML
    public TextField myTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button tilesButton;
    @FXML
    private Button dButton;
    @FXML
    private Button forwardButton;
    
    public  static String directory = null; 
    public  static String home = null;
    @FXML
    private TilePane myTilePane;
    private File[] list;
    @FXML
    private SplitPane slpPane;
    public  static boolean detailsView=true;
    public ObservableList<myClass> list2=FXCollections.observableArrayList();
    @FXML
    private ScrollPane myscrollPane;
    public static Stack<String> backStack;
    public static Stack<String> forwardStack;
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EditDetailsView.M=myTable; 
        EditDetailsView.str=str;
        EditDetailsView.str2=str2;
        EditDetailsView.str3=str3;
        EditDetailsView.str4=str4;
        EditTilesView.T=myTilePane;
        EditTilesView.S=myscrollPane;
        EditTilesView.TF=myTextField;
        myTextField.setEditable(false);
        
        backStack = new Stack();
        forwardStack = new Stack();

        directory = Paths.get("").toAbsolutePath().toString();
        myTextField.setText(directory);

        //Setting up The abstruct root"ThisPC" 
        TreeItem <String> root;
        File x = new File("ThisPC");
        root = new TreeItem("ThisPC",myIconView.getView(x,"TreeItem"));
        home = x.getAbsolutePath();
        
        //Setting up drives
        list = File.listRoots();
        for ( File f : list ) {
            root.getChildren().add(new myTreeItems(f,f.getAbsolutePath(),myIconView.getView(f,"TreeItem")));
        }

        myTreeView.setRoot(root);

        FactoryVewChanger myFactory = FactoryVewChanger.getFactory();
        myViewInterface mvi = myFactory.getView(detailsView);
        mvi.alterView();
        mvi = myFactory.getView(!detailsView);
                
    }
    @FXML
    private void setForwardAction(ActionEvent event) {
        String s;
        if(!forwardStack.isEmpty()){
            backStack.push(directory);
            directory = forwardStack.pop();
            if(directory == null){directory = home;}
            
            FactoryVewChanger myFactory = FactoryVewChanger.getFactory();
            myViewInterface mvi = myFactory.getView(detailsView);
            mvi.alterView();

            String ss = directory;
            if(directory.equals(home)){ss = "ThisPC";}
            myTextField.setText(ss);
        }
        
    }

    @FXML
    private void handleButtonBackAction(ActionEvent event) {
        if(!backStack.isEmpty()){
            forwardStack.push(directory);
            directory = backStack.pop();
            if(directory == null){directory = home;}

            FactoryVewChanger myFactory = FactoryVewChanger.getFactory();
            myViewInterface mvi = myFactory.getView(detailsView);
            mvi.alterView();

            String s = directory;
            if(directory.equals(home)){s = "ThisPC";}
            myTextField.setText(s);}
        
    }

    @FXML
    private void handleButtonTilesViewAction(ActionEvent event) {
        detailsView = false;

        FactoryVewChanger myFactory = FactoryVewChanger.getFactory();
        myViewInterface mvi = myFactory.getView(detailsView);
        mvi.alterView();

        String s = directory;
        if(directory.equals(home)){s = "ThisPC";}
        myTextField.setText(s);
    }

    @FXML
    private void handleButtonDetailsViewAction(ActionEvent event) {
        detailsView = true;

        FactoryVewChanger myFactory = FactoryVewChanger.getFactory();
        myViewInterface mvi = myFactory.getView(detailsView);
        mvi.alterView();

        String s = directory;
        if(directory.equals(home)){s = "ThisPC";}
        myTextField.setText(s);    
    }

    @FXML
    private void mouseClick(javafx.scene.input.MouseEvent event) {
        if(event.getClickCount() == 2){
            backStack.push(directory);
            forwardStack.clear();
            TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
            item.setExpanded(false);
            myTextField.setText(item.toString());
            directory = item.toString();
            
            if("ThisPC".equals(item.getValue())){directory = home;myTextField.setPromptText("ThisPC");}

            FactoryVewChanger myFactory = FactoryVewChanger.getFactory();
            myViewInterface mvi = myFactory.getView(detailsView);
            mvi.alterView();
           
        }
    }

    @FXML
    private void onRowClick(javafx.scene.input.MouseEvent event) {
        if(event.getClickCount() == 2){
                
                String S = directory;
                S = directory;
                backStack.addElement(S);
                forwardStack.clear();
                File F = myTable.getSelectionModel().getSelectedItem().getMyFile();
                directory = F.getAbsolutePath();
                
                String s = directory;
                if(directory.equals(home)){s = "ThisPC";}
                myTextField.setText(s);

                FactoryVewChanger myFactory = FactoryVewChanger.getFactory();
                myViewInterface mvi = myFactory.getView(detailsView);
                mvi.alterView();
        }
    }
    
}
