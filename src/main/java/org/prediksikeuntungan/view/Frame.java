/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan.view;

import com.alee.extended.menu.DynamicMenuType;
import com.alee.extended.menu.WebDynamicMenu;
import com.alee.extended.menu.WebDynamicMenuItem;
import com.alee.laf.combobox.WebComboBox;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.prediksikeuntungan.view.component.PanelSlider42;
import org.prediksikeuntungan.view.component.PlasticTabbedPaneUI;
import org.prediksikeuntungan.view.component.TransparantButton;


/**
 *
 * @author thread009
 */
public abstract class Frame extends javax.swing.JFrame {
    
    final protected PanelSlider42<JFrame> sliderTrain = new PanelSlider42<JFrame>(this);
    
    final protected DataPanel dataPanel = new DataPanel();
    final protected TreePanel  treePanel = new TreePanel();
    final protected ChartPanel chartPanel = new ChartPanel();
    

    protected List<WebDynamicMenuItem> rightClickMenutItems;
    protected WebComboBox hidingType;
    public static String MENU_ICON_PATH = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator;

    public Frame() {
        initComponents();
        initFrame();
         
        
    }

    protected void initFrame() {
        Dimension screensz = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screensz);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.hidingType = new WebComboBox(DynamicMenuType.values(), DynamicMenuType.star);
        this.rightClickMenutItems = new ArrayList<WebDynamicMenuItem>();
        this.sliderTrain.addComponent(dataPanel);
        this.sliderTrain.addComponent(chartPanel);
        this.sliderTrain.addComponent(treePanel);
        this.mainPane.setUI(new PlasticTabbedPaneUI());
        
        
        
    }

    protected void addRightClickMenu(Object icon, ActionListener listener) {
        WebDynamicMenuItem menu;
        ImageIcon iconImg = new ImageIcon(icon.toString());

        if (iconImg.getImage() != null) {
            menu = new WebDynamicMenuItem(iconImg);
            menu.setAction(listener);
        } else {
            menu = new WebDynamicMenuItem(new ImageIcon(MENU_ICON_PATH + "default.png"));
            menu.setAction(listener);
        }

        rightClickMenutItems.add(menu);
    }

    protected void showRightClickMenu(MouseEvent e) {
        WebDynamicMenu rightClickMenu = this.newDynamicMenu();
        for (WebDynamicMenuItem menu : rightClickMenutItems) {
            rightClickMenu.addItem(menu);
        }
        rightClickMenu.showMenu(e.getComponent(), e.getPoint());
    }

    protected void showRightClickMenu(MouseEvent event, int... index) {
        WebDynamicMenu rightClickMenu = this.newDynamicMenu();
        for (int inX : index) {
            rightClickMenu.addItem(rightClickMenutItems.get(inX));
        }
        rightClickMenu.showMenu(event.getComponent(), event.getPoint());
    }

    private WebDynamicMenu newDynamicMenu() {
        WebDynamicMenu dynamicMenu = new WebDynamicMenu();
        dynamicMenu.setType(DynamicMenuType.star);
        dynamicMenu.setHideType((DynamicMenuType) this.hidingType.getSelectedItem());
        dynamicMenu.setRadius(55);
        dynamicMenu.setStepProgress(0.06f);

        return dynamicMenu;
    }
    
    public JLabel getIndicator() {
        return indicator;
    }

    public JLabel getStatus() {
        return status;
    }

    public JTable getDataTrain() {
        return dataTrain;
    }
    
    

    // TRAIN TAB

    public JButton getBtnData() {
        return btnData;
    }
    
    public JButton getBtnVisualiseTree() {
        return btnVisualiseTree;
    }

    public JButton getBtnSaveTraining() {
        return btnSaveTraining;
    }
    
    
    
    //TEST TAB

    public JButton getAddBtn() {
        return addBtn;
    }

    public JTable getDataTable() {
        return dataTable;
    }

    public JButton getRemoveBtn() {
        return removeBtn;
    }

    public JButton getRefreshBtn() {
        return refreshBtn;
    }

    public JButton getReportBtn() {
        return reportBtn;
        
    }
    

    public JLabel getTestingLabel() {
        return testingLabel;
    }
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        mainPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnData = new TransparantButton("/train-menu"+File.separator+"data-small.png"
            , "/train-menu"+File.separator+"data-big.png");
        btnVisualiseTree = new TransparantButton("/train-menu"+File.separator+"chart-small.png"
            , "/train-menu"+File.separator+"chart-big.png");
        btnSaveTraining = new TransparantButton("/train-menu"+File.separator+"save-small.png"
            , "/train-menu"+File.separator+"save-big.png");
        train = sliderTrain.getBasePanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        testingLabel = new javax.swing.JLabel();
        trainPane = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        reportBtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        indicator = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        trainPane1 = new javax.swing.JScrollPane();
        dataTrain = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPane.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel4.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnData, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVisualiseTree, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 566, Short.MAX_VALUE)
                .addComponent(btnSaveTraining, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVisualiseTree, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(btnData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSaveTraining, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout trainLayout = new javax.swing.GroupLayout(train);
        train.setLayout(trainLayout);
        trainLayout.setHorizontalGroup(
            trainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        trainLayout.setVerticalGroup(
            trainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(train, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(train, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPane.addTab("Training", jPanel1);

        jPanel18.setBackground(new java.awt.Color(36, 203, 130));
        jPanel18.setBorder(null);

        testingLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        testingLabel.setForeground(new java.awt.Color(254, 254, 254));
        testingLabel.setText("Testing Data");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testingLabel)
                .addContainerGap(767, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testingLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dataTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Pekerjaan", "Pendapatan", "Status Menikah", "Keuntungan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        trainPane.setViewportView(dataTable);

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        addBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-big.png"))); // NOI18N

        removeBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        removeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove-big.png"))); // NOI18N

        refreshBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        refreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refresh.png"))); // NOI18N

        reportBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pdf.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reportBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshBtn))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reportBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(trainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
        );

        mainPane.addTab("Testing", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        indicator.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        indicator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16px/error.png"))); // NOI18N

        status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indicator)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(indicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        dataTrain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dataTrain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Train No", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        trainPane1.setViewportView(dataTrain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(trainPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPane))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPane)
                    .addComponent(trainPane1))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton btnData;
    private javax.swing.JButton btnSaveTraining;
    private javax.swing.JButton btnVisualiseTree;
    private javax.swing.JTable dataTable;
    private javax.swing.JTable dataTrain;
    private javax.swing.JLabel indicator;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane mainPane;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton reportBtn;
    private javax.swing.JLabel status;
    private javax.swing.JLabel testingLabel;
    private javax.swing.JPanel train;
    private javax.swing.JScrollPane trainPane;
    private javax.swing.JScrollPane trainPane1;
    // End of variables declaration//GEN-END:variables
}
