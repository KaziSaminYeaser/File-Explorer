/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro3;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author KsyP
 */
public class MyTableCell extends TableCell<myClass,ImageView>{
    private ImageView iv;
    public MyTableCell(){
        iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setFitWidth(20);       
    }
    @Override
    public void updateItem(ImageView im, boolean empty){
        if(im != null){
            HBox box= new HBox();
            box.setAlignment(Pos.CENTER);
            iv = im;
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setFitWidth(18);
            box.getChildren().add(im);
            setGraphic(box);
            
        }   
    }

}
