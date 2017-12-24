/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan.view.component;

import com.alee.extended.menu.DynamicMenuType;
import com.alee.extended.menu.WebDynamicMenu;
import com.alee.extended.menu.WebDynamicMenuItem;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author thread009
 */
public class TransparantButton extends JButton implements MouseListener{
    
    private BufferedImage image;
    private BufferedImage imageLost;
    private BufferedImage imageFocust;
    private WebDynamicMenu dynamicMenu;
    
    public TransparantButton(String imageURL){
        try{
            this.image = ImageIO.read(getClass().getResource(imageURL));
            this.dynamicMenu = new WebDynamicMenu();
        }catch(IOException e){
            e.getStackTrace();
        }
        setIcon(new ImageIcon(this.image));
    }
    
    public TransparantButton(String imageLost, String imageFocust){
        try{
            this.imageLost = ImageIO.read(getClass().getResource(imageLost));
            this.imageFocust = ImageIO.read(getClass().getResource(imageFocust));
            this.dynamicMenu = new WebDynamicMenu();
        }catch(IOException e){
            e.getStackTrace();
        }
        setIcon(new ImageIcon(this.imageLost));
        addMouseListener(this);
    }
    
    public void setMenuItems( final List<WebDynamicMenuItem> item){
        if(item.size() > 0){
            this.dynamicMenu.setType(DynamicMenuType.star);
            this.dynamicMenu.setHideType(DynamicMenuType.roll);
            this.dynamicMenu.setRadius(60);
            this.dynamicMenu.setStepProgress(0.06f);
            for(WebDynamicMenuItem menu : item){
                menu.setDrawBorder(false);
                this.dynamicMenu.addItem(menu);
            }
        }else return;
    }

    @Override
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(false); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setContentAreaFilled(boolean b) {
        super.setContentAreaFilled(false); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBorderPainted(boolean b) {
        super.setBorderPainted(false); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setIcon(new ImageIcon(this.imageLost));
        setIcon(new ImageIcon(this.imageFocust));
        
        if(this.dynamicMenu.getItems().size() > 0){
            this.dynamicMenu.showMenu(e.getComponent(), new Point(219, 50));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setIcon(new ImageIcon(this.imageLost));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setIcon(new ImageIcon(this.imageLost));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setIcon(new ImageIcon(this.imageFocust));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setIcon(new ImageIcon(this.imageLost));
    }


  
    
    
    
    
    
    
    
    
}
