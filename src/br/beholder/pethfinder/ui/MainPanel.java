/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.ui;

import br.beholder.pethfinder.control.NormalPathController;
import br.beholder.pethfinder.ui.swing.webLaf.PSOutTabbedPaneUI;
import br.beholder.pethfinder.ui.swing.webLaf.WeblafUtils;
import br.beholder.pethfinder.ui.utils.ColorController;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author lite
 */
public class MainPanel extends javax.swing.JPanel {
    
    int pX, pY;
    NormalPathController controller;
    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        initComponents();
        setIcon();
        configureTheme();
        controller = new NormalPathController(this);
    }
    private void setIcon(){
        
    }
    
    private void configureTheme(){
        WeblafUtils.instalaWeblaf();
        WeblafUtils.configuraWebLaf(webComboBox1);
        WeblafUtils.configuraWeblaf(jPanel2);
        WeblafUtils.configuraWebLaf(jTextField1);
        WeblafUtils.configuraWebLaf(jTextArea1);
        
        WeblafUtils.configuraWebLaf(jScrollPane1);
        
        jTabbedPane1.setUI(new PSOutTabbedPaneUI());
        
        WeblafUtils.configurarBotao(webButton5, ColorController.COR_DESTAQUE, ColorController.COR_LETRA);
        WeblafUtils.configurarBotao(webButton4, ColorController.COR_DESTAQUE, ColorController.COR_LETRA);
        WeblafUtils.configurarBotao(webButton1, ColorController.COR_DESTAQUE, ColorController.COR_LETRA);
        WeblafUtils.configurarBotao(webButton8, ColorController.COR_DESTAQUE, ColorController.COR_LETRA);
        
//        WeblafUtils.configurarBotao(webButton3, ColorController.COR_PRINCIPAL, ColorController.COR_LETRA,ColorController.FUNDO_CLARO, Color.orange, 5);
        
        jPanel2.setBackground(ColorController.COR_PRINCIPAL);
        jPanel7.setBackground(ColorController.COR_PRINCIPAL);
        jTextArea1.setBackground(ColorController.COR_PRINCIPAL);
        setBackground(ColorController.COR_PRINCIPAL);
        jTextArea1.setForeground(ColorController.COR_LETRA);
//        jPanel3.setBackground(ColorController.COR_PRINCIPAL); 
        jTabbedPane1.setForeground(ColorController.COR_LETRA);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        webButton8 = new com.alee.laf.button.WebButton();
        webComboBox1 = new com.alee.laf.combobox.WebComboBox();
        webButton1 = new com.alee.laf.button.WebButton();
        webButton4 = new com.alee.laf.button.WebButton();
        webButton5 = new com.alee.laf.button.WebButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel2.setMinimumSize(new java.awt.Dimension(640, 480));
        jPanel2.setPreferredSize(new java.awt.Dimension(640, 480));
        jPanel2.setLayout(new java.awt.BorderLayout(20, 20));

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.FlowLayout(2));

        jTextField1.setText("100");
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 35));
        jPanel5.add(jTextField1);

        webButton8.setText("Passo a Passo");
        webButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(webButton8);

        webComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Raio", "Gulosa" }));
        webComboBox1.setMinimumSize(new java.awt.Dimension(56, 30));
        jPanel5.add(webComboBox1);

        webButton1.setText("Carregar PF");
        webButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(webButton1);

        webButton4.setText("Carregar XML");
        webButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(webButton4);

        webButton5.setText("Calcular");
        webButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(webButton5);

        jPanel2.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setDividerLocation(450);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane1.addTab("Console", jScrollPane1);

        jSplitPane1.setBottomComponent(jTabbedPane1);

        jPanel7.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setTopComponent(jPanel7);

        jPanel2.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    public JPanel getImagePane(){
        return jPanel7;
    }
    
    public JTextArea getConsoleArea(){
        return jTextArea1;
    }
    
    public Integer getTime(){
        return Integer.parseInt(jTextField1.getText());
    }
    
    public String getCalcType(){
        return webComboBox1.getSelectedItem().toString();
    }
    
    private void webButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1ActionPerformed
        controller.readPF();
    }//GEN-LAST:event_webButton1ActionPerformed

    private void webButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton4ActionPerformed
        controller.readXML();
    }//GEN-LAST:event_webButton4ActionPerformed

    private void webButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton5ActionPerformed
        controller.calculate();
    }//GEN-LAST:event_webButton5ActionPerformed

    private void webButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton8ActionPerformed
        controller.setTipo(webComboBox1.getSelectedItem().toString()); 
        controller.calculateStep();
    }//GEN-LAST:event_webButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private com.alee.laf.button.WebButton webButton1;
    private com.alee.laf.button.WebButton webButton4;
    private com.alee.laf.button.WebButton webButton5;
    private com.alee.laf.button.WebButton webButton8;
    private com.alee.laf.combobox.WebComboBox webComboBox1;
    // End of variables declaration//GEN-END:variables
}
