package org.prediksikeuntungan.view.component;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

import org.jgraph.*;
import org.jgraph.graph.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
// resolve ambiguity
import org.jgrapht.graph.DefaultEdge;
import org.prediksikeuntungan.Main;
import org.prediksikeuntungan.machinelearning.decisiontree.Node;

public class VisualisationTree extends JApplet {

    private static final long serialVersionUID = 3256444702936019250L;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
    private int width;
    private int height;
    private int defaultX;
    private int defaultY;
    
    private ListenableGraph<String, DefaultEdge> g;
    private JGraphModelAdapter<String, DefaultEdge> jgAdapter;
    private JGraph jgraph;

    public VisualisationTree(int width, int height) {
        this.width = width;
        this.height = height;
        this.defaultX = width/2;
        this.defaultY = height/2;
        

    }
    
    public void visualizeTree(Node node){
        printSubtree(node);
    }
    
    public void printSubtree(Node node) {
        if (!node.getChildren().isEmpty() && node.getChildren().get(0) != null) {
            //treeModel.add(new VisualizeModel(node, node.getChildren().get(0), true));
            printNodeValue(node, 0);
            printTree(node.getChildren().get(0));
        }
        if (node.getChildren().size() > 1 && node.getChildren().get(1) != null) {
            //treeModel.add(new VisualizeModel(node, node.getChildren().get(1), false));
            printNodeValue(node, 1);
            printTree(node.getChildren().get(1));
        }
    }
    
    private void printNodeValue(Node node, int index){
        String parentNode = node.isLeaf() 
                ? "("+node.getLabel().getPrintValue()+")"+Main.NUMBER_UTIL.randomNumericString(1)
                :node.getName()+Main.NUMBER_UTIL.randomNumericString(1);
        String childNode = node.getChildren().get(index).isLeaf() 
                ? "("+node.getChildren().get(index).getLabel().getPrintValue()+")"+Main.NUMBER_UTIL.randomNumericString(1)
                :node.getChildren().get(index).getName()+Main.NUMBER_UTIL.randomNumericString(1);
        g.addVertex(parentNode);
        g.addVertex(childNode);
        g.addEdge(parentNode, childNode);
        
        positionVertexAt(parentNode, defaultX, defaultY);
        
        
    }
    
    

    private void printTree(Node node) {
        try {
            if (!node.getChildren().isEmpty() && node.getChildren().get(0) != null) {
                printNodeValue(node, 0);
                printTree(node.getChildren().get(0));
            }
            
            
            if (node.getChildren().size() > 1 && node.getChildren().get(1) != null) {
                printNodeValue(node, 1);
                printTree(node.getChildren().get(1));
            }
        } catch (Exception e) {
        }
        
    }
    
    
     

    @Override
    public void init() {

        //ListenableGraph<String, DefaultEdge> g = new ListenableDirectedMultigraph<>(DefaultEdge.class);
        g = new ListenableDirectedMultigraph<>(DefaultEdge.class);

        jgAdapter = new JGraphModelAdapter<>(g);

        jgraph = new JGraph(jgAdapter);

        adjustDisplaySettings(jgraph);
        getContentPane().add(jgraph);

        if (this.width == 0 && this.height == 0) {
            resize(1009, 374);
        } else {
            resize(this.width, this.height);
        }

    }

    public JGraph getJgraph() {
        return jgraph;
    }

    private void adjustDisplaySettings(JGraph jg) {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter("bgcolor");
        } catch (Exception e) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }

    @SuppressWarnings("unchecked")
    private void positionVertexAt(Object vertex, int x, int y) {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }

    /**
     * A listenable directed multigraph that allows loops and parallel edges.
     */
    private static class ListenableDirectedMultigraph<V, E>
            extends DefaultListenableGraph<V, E> {

        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass) {
            super(new DirectedMultigraph<>(edgeClass));
        }
    }

}
