
package pe.escuelaconductores.presentacion.escritorio;

import pe.escuelaconductores.persistence.*;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class FrmMain extends javax.swing.JFrame {

    public FrmMain() {
        initComponents();
      //  listarEntidades();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMain().setVisible(true);
            }
        });
    }


  
    
    

 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpEscritorio = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmiSalir = new javax.swing.JMenu();
        jmi_salir = new javax.swing.JMenuItem();
        jm = new javax.swing.JMenu();
        MnAgregar = new javax.swing.JMenuItem();
        mnBuscarUbigeo = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jpEscritorioLayout = new javax.swing.GroupLayout(jpEscritorio);
        jpEscritorio.setLayout(jpEscritorioLayout);
        jpEscritorioLayout.setHorizontalGroup(
            jpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jpEscritorioLayout.setVerticalGroup(
            jpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jmiSalir.setText("Archivo");

        jmi_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, 0));
        jmi_salir.setText("Salir");
        jmi_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_salirActionPerformed(evt);
            }
        });
        jmiSalir.add(jmi_salir);

        jMenuBar1.add(jmiSalir);

        jm.setText("Establecimientos");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmActionPerformed(evt);
            }
        });

        MnAgregar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MnAgregar.setText("Agregar establecimiento");
        MnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAgregarActionPerformed(evt);
            }
        });
        jm.add(MnAgregar);

        mnBuscarUbigeo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnBuscarUbigeo.setText("Buscar por Ubigeo");
        mnBuscarUbigeo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBuscarUbigeoActionPerformed(evt);
            }
        });
        jm.add(mnBuscarUbigeo);

        jMenuBar1.add(jm);

        jMenu2.setText("Reportes");

        jMenuItem2.setText("Lista Escuelas de Manejo");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Lista de Centros Médicos");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Lista de Centros Evaluación");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpEscritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpEscritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAgregarActionPerformed
        FrmAgregarEstablecimiento frmAgregarEstablecimiento = new FrmAgregarEstablecimiento();
        jpEscritorio.add(frmAgregarEstablecimiento);
        frmAgregarEstablecimiento.show();
    }//GEN-LAST:event_MnAgregarActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jmi_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_salirActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jmi_salirActionPerformed

    private void jmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmActionPerformed

    }//GEN-LAST:event_jmActionPerformed

    private void mnBuscarUbigeoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBuscarUbigeoActionPerformed
        FrmBuscarUbigeo frmBuscarUbigeo = new FrmBuscarUbigeo();
        jpEscritorio.add(frmBuscarUbigeo);
        frmBuscarUbigeo.show();
    }//GEN-LAST:event_mnBuscarUbigeoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MnAgregar;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenu jm;
    private javax.swing.JMenu jmiSalir;
    private javax.swing.JMenuItem jmi_salir;
    private javax.swing.JPanel jpEscritorio;
    private javax.swing.JMenuItem mnBuscarUbigeo;
    // End of variables declaration//GEN-END:variables
}
