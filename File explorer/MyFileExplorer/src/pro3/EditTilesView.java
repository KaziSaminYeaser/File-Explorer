/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro3;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 *
 * @author KsyP
 */
public class EditTilesView implements myViewInterface {
    public static TilePane T;
    public static ScrollPane S;
    private static EditTilesView myETV= null;
    public static TextField TF;
    
    private EditTilesView(){
        setTilePane() ;
    }
    
    private void setTilePane(){
        T.setVgap(5);
        T.setHgap(20);
        T.setPrefColumns(5);
        T.setTileAlignment(Pos.CENTER);
        S.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        S.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        S.setFitToHeight(true);
        S.setFitToWidth(true);
        S.setContent(T); 
    
    }
    
    public VBox creatMyVbox(File f, String filename){
        VBox mybox = new myVbox(f,filename);
        mybox.setOnMouseClicked((MouseEvent event) -> {
            if(event.getClickCount()==2){
                FXMLDocumentController.backStack.addElement(FXMLDocumentController.directory);
                FXMLDocumentController.directory = mybox.toString();
                TF.setText(FXMLDocumentController.directory);
                //alterView();
                //FXMLDocumentController.backStack.addElement(directory);
                EditTilesView nn = EditTilesView.getCNG();
                nn.alterView();
            }
        });
        return mybox;
    
    }
    
    @Override
    public void alterView(){

        File F = new File(FXMLDocumentController.directory);
        if(!FXMLDocumentController.detailsView &&(!F.isFile() || FXMLDocumentController.directory.equals(FXMLDocumentController.home)) ){
            File[] myFiles;
            
            if(FXMLDocumentController.directory.equals(FXMLDocumentController.home) ) myFiles = File.listRoots();
            else myFiles = F.listFiles();
            
            T.getChildren().clear();
            if(myFiles!=null){            
                for ( File f : myFiles ) {
                    if ( myFiles!=null ) { 
                        String t;                 
                        if(FXMLDocumentController.directory.equals(FXMLDocumentController.home)) t = f.getAbsolutePath();
                        else t = f.getName();
                        VBox box= creatMyVbox(f,t);
                        T.getChildren().add(box);
                    }
                }
            }
        }
    }
    static public EditTilesView getCNG(){
        T.setDisable(FXMLDocumentController.detailsView);
        T.setVisible(!FXMLDocumentController.detailsView);
        if(myETV==null){myETV = new EditTilesView();}
        return myETV;
    
    }
}
