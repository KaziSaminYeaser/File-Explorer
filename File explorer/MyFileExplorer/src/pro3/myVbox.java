/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro3;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author KsyP
 */
public class myVbox extends VBox{
            public File myFile;
            public String fileName;
            public String path;
            
            public myVbox(File f, String name){
                super();
                myFile = f;
                fileName = name;
                path = f.getAbsolutePath();
                super.getChildren().addAll(myIconView.getView(f, "TileView"),new Label(name));
                super.setAlignment(Pos.CENTER);
                super.setMaxSize(120, 80);
                super.setPrefSize(60, 60);
                
            }
            
            @Override public String toString(){
               return path;
            }  
      
}
