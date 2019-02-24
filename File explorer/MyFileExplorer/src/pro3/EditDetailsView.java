/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro3;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 *
 * @author KsyP
 */
public class EditDetailsView implements myViewInterface {
    public static TableView<myClass> M;
    public static TableColumn<myClass, ImageView> str;
    public static TableColumn<myClass, String> str2;
    public static TableColumn<myClass, Long> str3;
    public static TableColumn<myClass, String> str4;
    public static String d;
    public static String n ;
    public static boolean b;
    private static EditDetailsView myEDV = null;

    private EditDetailsView(){
       d=FXMLDocumentController.directory;
       n=FXMLDocumentController.home;
       b=FXMLDocumentController.detailsView;
       setColumns();
    }
    public static void setColumns(){
            str.setCellValueFactory(new PropertyValueFactory("myView"));
                str.setCellFactory((TableColumn<myClass,ImageView> param) -> {
                    TableCell<myClass,ImageView> cell;
                    cell = new MyTableCell();
                    return cell;
                });
        str2.setCellValueFactory(new PropertyValueFactory("fileName"));
        str3.setCellValueFactory(new PropertyValueFactory("size"));
        str4.setCellValueFactory(new PropertyValueFactory("lastModified"));
    }
    
    
    @Override public void alterView(){
            File F = new File(FXMLDocumentController.directory);
            if(FXMLDocumentController.detailsView &&(!F.isFile() || FXMLDocumentController.directory.equals(FXMLDocumentController.home)) ){ 
                M.getItems().clear();
                File[] myFiles;
                ObservableList<myClass> List2 = FXCollections.observableArrayList();
                if(FXMLDocumentController.directory.equals(FXMLDocumentController.home)){myFiles = File.listRoots();for(File aa: myFiles){List2.add(new myClass(aa,aa.getAbsolutePath()));}}
                else{myFiles = F.listFiles();if(myFiles!=null){for(File aa: myFiles){List2.add(new myClass(aa));}}}
                M.refresh();                             
                M.setItems(List2);
            }
    }
    static public EditDetailsView getCNG(){
        M.setDisable(!FXMLDocumentController.detailsView);
        M.setVisible(FXMLDocumentController.detailsView);
        if(myEDV==null){myEDV = new EditDetailsView();}
        return myEDV;
    
    }
        
}
