/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.UIManager;
import org.prediksikeuntungan.view.Controller;
import org.prediksikeuntungan.view.Frame;
import org.prediksikeuntungan.view.component.AlphaNumericUtility;

/**
 *
 * @author thread009
 */
public class Main {
    
    public static AlphaNumericUtility NUMBER_UTIL = new AlphaNumericUtility();
    
    static {

        Font globalFont = new Font("Tahoma", Font.PLAIN, 12);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.messageFont", globalFont);
            UIManager.put("OptionPane.buttonFont", globalFont);
            UIManager.put("ToolTip.font", globalFont);

            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Controller appStartup = new Controller();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.createImage(Frame.MENU_ICON_PATH+File.separator+"train-menu"+File.separator+"chart-small.png");
        appStartup.setIconImage(image);
        appStartup.setVisible(true);
    }
}
