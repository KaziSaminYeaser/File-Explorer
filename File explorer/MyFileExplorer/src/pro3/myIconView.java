/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro3;

import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author KsyP
 */
public class myIconView {
    private static ImageView iMm;
    private static myIconView iMn = null;
    private myIconView(){
        Image image = new Image("cmp.png",25,30,true,true);
        iMm = new ImageView(image);
        iMm.setPreserveRatio(true);
        iMm.setSmooth(true);   
    }
    static private void fileIconView(File f, int x){
        ImageIcon icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(f);
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
        Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
        iMm = new ImageView(fxImage);
        iMm.setPreserveRatio(true);
        iMm.setSmooth(true);
        iMm.setFitWidth(x);
    }
    
    static public ImageView getView(File f, String S){
        if(iMn == null) iMn = new myIconView();
        else if ("TreeItem".equals(S)) fileIconView(f,18);
        else if ("TableView".equals(S)) fileIconView(f,22);
        else if ("TileView".equals(S)) fileIconView(f,25);
        return iMn.get()
                ;
    }
    ImageView get(){return iMm;}
}
