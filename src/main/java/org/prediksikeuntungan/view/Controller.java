
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import org.prediksikeuntungan.Main;
import org.prediksikeuntungan.database.DataAccess;
import org.prediksikeuntungan.database.ReportModel;
import org.prediksikeuntungan.database.TrainDetailModel;
import org.prediksikeuntungan.database.TrainModel;
import org.prediksikeuntungan.machinelearning.decisiontree.DecisionTree;
import org.prediksikeuntungan.machinelearning.decisiontree.data.DataSample;
import org.prediksikeuntungan.machinelearning.decisiontree.data.SimpleDataSample;
import org.prediksikeuntungan.machinelearning.decisiontree.feature.Feature;
import org.prediksikeuntungan.machinelearning.decisiontree.feature.PredicateFeature;
import org.prediksikeuntungan.machinelearning.decisiontree.label.ThreeLabel;
import static org.prediksikeuntungan.view.Frame.MENU_ICON_PATH;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.util.CsvContext;

/**
 *
 * @author thread009
 */
public class Controller extends Frame {

    private DecisionTree tree;
    private List<DecisionTree> trees;
    private List<DataSample> trainingData;
    private DataAccess data;
    private String reportPath = System.getProperty("user.dir")+File.separator+"src"
            +File.separator+"main"+File.separator+"resources"+File.separator+"report"+File.separator;

    public Controller() {
        super();
        init();
    }

    private void init() {

        this.setLocationRelativeTo(null);
        this.setTitle("Data Mining");
        this.data = new DataAccess();
        /*TRAINING TAB*/
        this.TAB_TRAINING_ACTION();
        refreshDataTrain();
        dataPanel.getRemoveBtn().setEnabled(false);
        tableProperties(0);
        tableProperties(1);
        tableProperties(2);

        /*TESTING TAB*/
        this.getRemoveBtn().setEnabled(false);
        TAB_TESTING_ACTION();
        tableProperties(1);

        addRightClickMenu(MENU_ICON_PATH + File.separator
                + "right-click-menu" + File.separator
                + "refresh.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int trainRow = getDataTrain().getSelectedRow();
                if (getDataTrain().isRowSelected(trainRow)) {
                    try {
                        DefaultTableModel tmb = (DefaultTableModel) dataPanel.getTrainTable().getModel();
                        tmb.setNumRows(0);
                        int row = 0;
                        for (TrainDetailModel data : data.GET_TRAIN_DETAIL(getDataTrain().getValueAt(trainRow, 1).toString())) {
                            tmb.addRow(new Object[]{++row, data.getPekerjaan(), Main.NUMBER_UTIL.formatRp(data.getPendapatan()),
                                data.getStatusMenikah() == 0 ? "Menikah" : "Lajang",
                                Main.NUMBER_UTIL.formatRp(data.getKeuntungan())});
                        }
                    } catch (SQLException ex) {
                        getStatus().setText("Problem get train data: " + ex);
                        getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));
                    }
                } else {
                    getStatus().setText("Select Data First !!!");
                    getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));
                }
            }
        });

        getDataTrain().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showRightClickMenu(e);
                }
            }

        });

    }

    private void tableProperties(int index) {
        switch (index) {
            case 0:
                dataPanel.getTrainTable().getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
                dataPanel.getTrainTable().getColumnModel().getColumn(0).setPreferredWidth(30);
                dataPanel.getTrainTable().getColumnModel().getColumn(1).setPreferredWidth(428);
                dataPanel.getTrainTable().getColumnModel().getColumn(2).setPreferredWidth(229);
                dataPanel.getTrainTable().getColumnModel().getColumn(3).setPreferredWidth(229);
                break;
            case 1:
                this.getDataTable().getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
                break;
            case 2:
                this.getDataTrain().getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
                this.getDataTrain().getColumnModel().getColumn(0).setPreferredWidth(30);
                this.getDataTrain().getColumnModel().getColumn(1).setPreferredWidth(81);
                this.getDataTrain().getColumnModel().getColumn(2).setPreferredWidth(132);
                break;
        }
    }

    /*
    Controller untuk tab training
     */
    private void TAB_TRAINING_ACTION() {
        getBtnData().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sliderTrain.slideBottom(dataPanel);
            }
        });

        getBtnVisualiseTree().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizeTree();
            }
        });

        getBtnSaveTraining().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataTrain();
                refreshDataTrain();
            }
        });

        dataPanel.getTrainTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (dataPanel.getTrainTable().isRowSelected(dataPanel.getTrainTable().getSelectedRow())) {
                    dataPanel.getRemoveBtn().setEnabled(true);
                } else {
                    dataPanel.getRemoveBtn().setEnabled(false);
                }
            }
        });

        dataPanel.getAddBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DIALOG_ACTION(0);
            }
        });

        dataPanel.getRemoveBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tmb = (DefaultTableModel) dataPanel.getTrainTable().getModel();
                tmb.removeRow(dataPanel.getTrainTable().getSelectedRow());
                for (int i = 0; i < tmb.getRowCount(); i++) {
                    int row = i + 1;
                    tmb.setValueAt(row, i, 0);
                }
                getStatus().setText("Row Removed");
                getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));
            }
        });
    }

    /*
    *Controller untuk tab testing
     */
    private void TAB_TESTING_ACTION() {

        this.getDataTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (getDataTable().isRowSelected(getDataTable().getSelectedRow())) {
                    getRemoveBtn().setEnabled(true);
                } else {
                    getRemoveBtn().setEnabled(false);
                }
            }
        });

        this.getAddBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DIALOG_ACTION(1);
            }
        });

        this.getRemoveBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tmb = (DefaultTableModel) getDataTable().getModel();
                tmb.removeRow(getDataTable().getSelectedRow());
                for (int i = 0; i < tmb.getRowCount(); i++) {
                    int row = i + 1;
                    tmb.setValueAt(row, i, 0);
                }
                getStatus().setText("Row Removed");
                getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));

            }
        });

        this.getRefreshBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int trainRow = getDataTrain().getSelectedRow();
                if (getDataTrain().isRowSelected(trainRow)) {
                    String trainCode = getDataTrain().getValueAt(trainRow, 1).toString();
                    DecisionTree newTree = extractKnowledgeFromDB(trainCode);
                    if (getDataTable().getRowCount() > 0) {
                        for (int row = 0; row < getDataTable().getRowCount(); row++) {
                            final String[] header = {"No", "Pekerjaan", "Pendapatan", "Status Menikah", "Keuntungan"};
                            Object[] d = {
                                getDataTable().getValueAt(row, 0),
                                getDataTable().getValueAt(row, 1),
                                getDataTable().getValueAt(row, 2),
                                getDataTable().getValueAt(row, 3),
                                getDataTable().getValueAt(row, 4)};
                            DataSample data = SimpleDataSample.newSimpleDataSample("Keuntungan", header, d);
                            getDataTable().setValueAt(
                                    Main.NUMBER_UTIL.formatRp(newTree.classify(data).getPrintValue()), row, 4);
                        }
                        getStatus().setText("Data Tested with train code: " + trainCode);
                        getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/true.png"));
                    } else {
                        getStatus().setText("Input testing Data Train First !!!");
                        getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));
                    }
                } else {
                    getStatus().setText("Select Data Train First !!!");
                    getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));
                }
            }
        });

        this.getReportBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JasperReport jasper = (JasperReport) JRLoader.loadObject(new File(reportPath+"laporan-pdf.jasper"));
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, null, preparedDataSource());
                    JRViewer jRViewer = new JRViewer(jasperPrint);
                    final JDialog dialogView = new JDialog();
                    dialogView.setModal(true);
                    dialogView.setPreferredSize(new Dimension(1100, 700));
                    dialogView.setSize(1100, 700);
                    dialogView.getContentPane().add(jRViewer);
                    dialogView.pack();
                    final java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
                    final Dimension screenSize = toolkit.getScreenSize();
                    final int x = (screenSize.width - dialogView.getWidth()) / 2;
                    final int y = (screenSize.height - dialogView.getHeight()) / 2;
                    dialogView.setLocation(x, y);
                    dialogView.setVisible(true);
                }catch(JRException ex){
                    ex.printStackTrace();
                }
            }

            
        });

    }

    public void DIALOG_ACTION(int index) {
        InputDialog dialog = new InputDialog(this, true, this);
        dialog.getNominalPendapatan().setText(Main.NUMBER_UTIL.formatRp(0));
        dialog.getNominalKeuntungan().setText(Main.NUMBER_UTIL.formatRp(0));

        dialog.getTextPendapatan().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isAlphabetic(e.getKeyChar())) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = dialog.getTextPendapatan().getText();
                dialog.getNominalPendapatan().setText(Main.NUMBER_UTIL.formatRp(text));
            }

        });

        dialog.getTextKeuntungan().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isAlphabetic(e.getKeyChar())) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = dialog.getTextKeuntungan().getText();
                dialog.getNominalKeuntungan().setText(Main.NUMBER_UTIL.formatRp(text));
            }

        });

        dialog.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        switch (index) {
            case 0:
                dialog.getBtnSave().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String pekerjaan = "";
                        if (dialog.getTextPekerjaan().getText().equals("")) {
                            pekerjaan = "TIDAK ADA";
                        } else {
                            pekerjaan = dialog.getTextPekerjaan().getText();
                        }
                        String pendapatan = dialog.getNominalPendapatan().getText();
                        String status = dialog.getCbStatus().getSelectedItem().toString();
                        String keunntungan = dialog.getNominalKeuntungan().getText();
                        try {
                            DefaultTableModel tmb = (DefaultTableModel) dataPanel.getTrainTable().getModel();
                            int row = tmb.getRowCount();
                            tmb.addRow(new Object[]{++row, pekerjaan, pendapatan, status, keunntungan});
                            getStatus().setText("Row Added");
                            getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/true.png"));
                        } catch (Exception ex) {
                        }
                        dialog.dispose();

                    }
                });
                break;
            case 1:
                dialog.getTextKeuntungan().setEnabled(false);

                dialog.getBtnSave().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String pekerjaan = "";
                        if (dialog.getTextPekerjaan().getText().equals("")) {
                            pekerjaan = "TIDAK ADA";
                        } else {
                            pekerjaan = dialog.getTextPekerjaan().getText();
                        }
                        String pendapatan = dialog.getNominalPendapatan().getText();
                        String status = dialog.getCbStatus().getSelectedItem().toString();
                        try {
                            DefaultTableModel tmb = (DefaultTableModel) getDataTable().getModel();
                            int row = tmb.getRowCount();
                            tmb.addRow(new Object[]{++row, pekerjaan, pendapatan, status, ""});
                            getStatus().setText("Row Added");
                            getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/true.png"));
                        } catch (Exception ex) {
                        }
                        dialog.dispose();

                    }
                });
                break;
        }
        dialog.setVisible(true);

    }

    /*
     Method buat database
     */
    private void saveDataTrain() {
        String trainCode = Main.NUMBER_UTIL.randomNumericString(5);
        TrainModel train = new TrainModel(trainCode, new java.util.Date());
        try {
            this.data.connection.setAutoCommit(false);
            if (this.data.INSERT_TRAINING_DATA(train, getTrainDetailModel(trainCode))) {
                if (this.data.commitLocal()) {
                    getStatus().setText("Data Training is saved to Database with ID: " + trainCode);
                    getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/true.png"));
                }
            } else {
                getStatus().setText("Failed to saved data to database: ");
                getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));
            }
        } catch (SQLException e) {
            getStatus().setText("Failed to saved data to database: " + e);
            getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));
        }

    }

    private void refreshDataTrain() {
        DefaultTableModel tmb = (DefaultTableModel) getDataTrain().getModel();
        tmb.setNumRows(0);
        int row = 0;
        try {
            for (TrainModel data : data.GET_TRAIN_MODEL()) {
                tmb.addRow(new Object[]{++row, data.getTrainCode(), this.data.dateToIndoDate(data.getTrainDate())});
            }
        } catch (SQLException e) {
            getStatus().setText("Failed to retract data: " + e);
            getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));
        }

    }
    
    private JRBeanCollectionDataSource preparedDataSource() {
        DefaultTableModel tableModel = (DefaultTableModel) getDataTable().getModel();
        int row = tableModel.getRowCount();
        List<ReportModel> list = new ArrayList<>();
        int no = 0;
        for(int i = 0; i<row;i++){
            ReportModel model = new ReportModel();
            model.setNo(String.valueOf(++no));
            model.setPekerjaan(tableModel.getValueAt(i, 1).toString());
            model.setPendapatan(tableModel.getValueAt(i, 2).toString());
            model.setStatusMenikah(tableModel.getValueAt(i, 3).toString());
            model.setKeuntungan(tableModel.getValueAt(i, 4).toString());
            list.add(model);
        }
        return new JRBeanCollectionDataSource(list);
    }

    private void buildTree() {
        this.tree = new DecisionTree();
        this.trainingData = new ArrayList<DataSample>();

        final String[] header = {"Pekerjaan", "Pendapatan", "Status Menikah", "Keuntungan"};
        final List<Feature> features = getFeatures();

        for (int i = 0; i < dataPanel.getTrainTable().getRowCount(); i++) {
            Object[] array = {
                dataPanel.getTrainTable().getValueAt(i, 1),
                dataPanel.getTrainTable().getValueAt(i, 2),
                dataPanel.getTrainTable().getValueAt(i, 3),
                new ThreeLabel(Main.NUMBER_UTIL.extractRpInteger(dataPanel.getTrainTable().getValueAt(i, 4).toString()))
            };
            this.trainingData.add(SimpleDataSample.newSimpleDataSample("Keuntungan", header, array));

        }

        this.tree.train(trainingData, features);

    }

    private DecisionTree extractKnowledgeFromDB(String trainCode) {
        DecisionTree thisTree = new DecisionTree();
        try {
            List<TrainDetailModel> data = this.data.GET_TRAIN_DETAIL(trainCode);
            List<DataSample> trainData = new ArrayList<DataSample>();
            System.out.println(data.size());
            final String[] header = {"Pekerjaan", "Pendapatan", "Status Menikah", "Keuntungan"};
            final List<Feature> features = getFeaturesFromDb(trainCode);
            System.out.println(features.size());

            for (TrainDetailModel get : data) {
                Object[] array = {
                    get.getPekerjaan(),
                    get.getPendapatan(),
                    get.getStatusMenikah(),
                    new ThreeLabel(get.getKeuntungan())
                };
                trainData.add(SimpleDataSample.newSimpleDataSample("Keuntungan", header, array));

            }

            thisTree.train(trainData, features);

        } catch (Exception e) {

        }
        return thisTree;
    }

    private void visualizeTree() {
        if (dataPanel.getTrainTable().getRowCount() > 0) {

            buildTree();

            //treePanel.treeCanvas.getContentPane().remove(treePanel.treeCanvas.getJgraph());
            //treePanel.treeCanvas.init();
            treePanel.getTopLabel().setText("Tree Visualize: (" + tree.getRoot().getName() + ")");
            treePanel.treeCanvas.visualizeTree(tree.getRoot());
            sliderTrain.slideBottom(treePanel);

            getStatus().setText("Data Visualize");
            getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/true.png"));
        } else {
            getStatus().setText("Don't see data to visualize !!!");
            getIndicator().setIcon(new ImageIcon(MENU_ICON_PATH + "16px/false.png"));
        }

    }

    private List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<Feature>();
        List<String> valueTemp = new ArrayList<String>();
        for (int col = 1; col < dataPanel.getTrainTable().getColumnCount() - 1; col++) {
            String attr = dataPanel.getTrainTable().getColumnName(col);
            for (int row = 0; row < dataPanel.getTrainTable().getRowCount(); row++) {
                String value = dataPanel.getTrainTable().getValueAt(row, col).toString();
                if (!valueTemp.contains(value)) {
                    if (Main.NUMBER_UTIL.isRpFormat(value)) {
                        features.add(PredicateFeature.newFeature(attr, Main.NUMBER_UTIL.extractRpString(value)));
                        valueTemp.add(Main.NUMBER_UTIL.extractRpString(value));
                    } else {
                        features.add(PredicateFeature.newFeature(attr, value));
                        valueTemp.add(value);
                    }
                }
            }
            valueTemp.clear();
        }
        return features;
    }

    private List<Feature> getFeaturesFromDb(String trainCode) {
        List<Feature> features = new ArrayList<Feature>();
        List<String> valueTemp = new ArrayList<String>();
        try {
            List<TrainDetailModel> data = this.data.GET_TRAIN_DETAIL(trainCode);
            for (int col = 0; col < 3; col++) {

                String attr = TrainDetailModel.getColName(col);

                for (int row = 0; row < data.size(); row++) {

                    String value = data.get(row).get(col).toString();
                    if (!valueTemp.contains(value)) {

                        features.add(PredicateFeature.newFeature(attr, value));
                        valueTemp.add(value);
                    }
                }
                valueTemp.clear();
            }
        } catch (SQLException e) {

        }
        return features;
    }

    private List<TrainDetailModel> getTrainDetailModel(String trainCode) {
        List<TrainDetailModel> data = new ArrayList<TrainDetailModel>();
        for (int row = 0; row < dataPanel.getTrainTable().getRowCount(); row++) {
            String stMenikah = dataPanel.getTrainTable().getValueAt(row, 3).toString();
            data.add(new TrainDetailModel(
                    trainCode,
                    dataPanel.getTrainTable().getValueAt(row, 1).toString(),
                    Main.NUMBER_UTIL.extractRpInteger(dataPanel.getTrainTable().getValueAt(row, 2).toString()),
                    stMenikah.equals("Menikah") ? 0 : 1,
                    Main.NUMBER_UTIL.extractRpInteger(dataPanel.getTrainTable().getValueAt(row, 4).toString())
            ));
        }
        return data;
    }

    private static class ParseLabel extends ParseInt {

        private Integer value;

        public ParseLabel(Integer value) {
            super();
            this.value = value;
        }

        public Object execute(final Object value, final CsvContext context) {
            return ThreeLabel.newLabel(this.value);
        }
    }

}
