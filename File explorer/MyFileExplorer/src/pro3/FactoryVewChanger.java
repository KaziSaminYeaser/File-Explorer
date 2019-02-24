/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro3;

/**
 *
 * @author KsyP
 */
public class FactoryVewChanger {
    
    private static FactoryVewChanger FVC = null;
    private FactoryVewChanger(){

    }
    public static FactoryVewChanger getFactory(){
        if(FVC == null) FVC = new FactoryVewChanger();
        return FVC;
    }
    public myViewInterface getView(boolean bool){
          myViewInterface view, view2;
          view = EditDetailsView.getCNG();
          view2 = EditTilesView.getCNG();
          if(bool) return view;
          else return view2;
    }
    
}
    

