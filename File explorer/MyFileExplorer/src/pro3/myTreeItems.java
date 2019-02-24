/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro3;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

/**
 *
 * @author KsyP
 */
public class myTreeItems extends TreeItem<String>{
    private boolean isLeaf;
            private String path;
            private String name;
            public File myFile;
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;
           
            public myTreeItems(File f, String name, ImageView im){
                super(name,im);
                this.path = f.getAbsolutePath();
                this.name = name;
                this.myFile = f;
            
            
            }
            @Override public ObservableList<TreeItem<String>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;
                    ObservableList<TreeItem<String>> children = buildChildren(this,this.myFile);
                    super.getChildren().setAll(children);
                }
                return super.getChildren();
            }

            @Override public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    isLeaf = myFile.isFile();
                }
                return isLeaf;              
            }
            @Override public String toString(){
                return path;
            }
            public final File fff(){
                return myFile;
            }
            private ObservableList<TreeItem<String>> buildChildren(TreeItem<String> TreeItem,File f) {
                if (f != null && f.isDirectory()) {
                    File[] files = f.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<String>> children = FXCollections.observableArrayList();
                        for (File childFile : files) {
                            File[] my = childFile.listFiles();
                            if(my != null && ! childFile.isFile()){
                                children.add(new myTreeItems(childFile,childFile.getName(),myIconView.getView(childFile,"TreeItem")));
                            }
                        }
                        return children;
                    }
                }              
                return FXCollections.emptyObservableList();
            }
}
