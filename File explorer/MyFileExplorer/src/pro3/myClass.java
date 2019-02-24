/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro3;

import java.io.File;
import java.text.SimpleDateFormat;
import javafx.scene.image.ImageView;

/**
 *
 * @author KsyP
 */
public class myClass {
    
    public String fileName;
    private File myFile;
    private ImageView myView;
    private String lastModified;
    private long size;
    public  static  SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");;
    
    public myClass(File f){
       lastModified = String.valueOf(sdf.format(f.lastModified()));
       if(lastModified == null) lastModified = "Apr 29, 2017";
        fileName = f.getName();
        myFile = f;
        myView = myIconView.getView(f, "TableView");
        size = f.length();
    }    
    public myClass(File f,String name){
        fileName = name;
        myFile = f;
        myView = myIconView.getView(f,"TableView");
        size = f.length();
       lastModified = String.valueOf(sdf.format(f.lastModified()));
       if(lastModified == null) lastModified = "Apr 29, 2017";;
    }    
    public String getFileName(){return fileName;}
    public File getMyFile(){return myFile;}
    public ImageView getMyView(){return myView;}
    public String getLastModified(){return lastModified;}
    public long getSize(){return size;}
    
}
