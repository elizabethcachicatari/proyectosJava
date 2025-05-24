
package pe.escuelaconductores.presentacion.escritorio;
 
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pe.escuelaconductores.bean.Entidad;
import pe.escuelaconductores.bean.Ubigeo;
import pe.escuelaconductores.persistence.*;

public class FrmBuscarUbigeo extends javax.swing.JInternalFrame {
    
    private Long varID;
    
    public FrmBuscarUbigeo() {
        initComponents();
        mostrarDepartamentos();
       // listarEntidades();
    }

    public void listarEntidades() {
  
        String cols[] = {"N°","RUC", "Nombre", "Departamento", "Provincia", "Distrito", "Tipo", "Estado"};
        
        DefaultTableModel dtm = new DefaultTableModel(cols, 0);
        tblEntidades.setModel(dtm);
        
        EntidadDB entidadDB = new EntidadDBImpl();
        
         try {
                List<Entidad> entidades = entidadDB.list();
                AtomicReference<Long> i = new AtomicReference<>(1L);
                entidades.forEach(p -> {
                    Vector fila = new Vector<>();
                    fila.add(i.getAndSet(i.get() + 1));
                    fila.add(p.getRuc());
                    fila.add(p.getNombre());
                    fila.add(p.getUbigeo().getNomDepartamento());
                    fila.add(p.getUbigeo().getNomProvincia());
                    fila.add(p.getUbigeo().getNomDistrito());
                    fila.add(p.getTipoEntidad().getNombreTipoEntidad());
                    fila.add(p.getEstadoEntidad().getNombreEstadoEntidad());

            dtm.addRow(fila);
        });
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }

    private void mostrarDepartamentos() {
        
        cmbDepartamento.removeAllItems();
        cmbDepartamento.addItem("- Seleccione el departamento -");    
        cmbProvincia.removeAllItems();
        cmbProvincia.addItem("- Seleccione la provincia -");
        cmbDistrito.removeAllItems();
        cmbDistrito.addItem("- Seleccione el distrito -");
              
        UbigeoDB ubigeoDB = new UbigeoDBImpl();
                
        try {
            List<Ubigeo> departamentos = ubigeoDB.obtenerDepartamentos();
            
            departamentos.forEach( p ->{
                cmbDepartamento.addItem(p.getNomDepartamento());
            });
           
        } catch (Exception e) {
            e.printStackTrace(); 
        }

    }    
    
    private void mostrarProvincias() {
        
        cmbProvincia.removeAllItems(); 
        cmbProvincia.addItem("- Seleccione la provincia -");
        cmbDistrito.removeAllItems();       
        cmbDistrito.addItem("- Seleccione el distrito -");
        
        UbigeoDB ubigeoDB = new UbigeoDBImpl();
                
        try {
            
            String departamento = cmbDepartamento.getSelectedItem().toString();
            List<Ubigeo> provincias = ubigeoDB.obtenerProvinciasPorDepartamento(departamento);
            
            provincias.forEach( p ->{
                cmbProvincia.addItem(p.getNomProvincia());
            });
           
        } catch (Exception e) {
            e.printStackTrace(); 
        }

    }
    
    private void mostrarDistritos() {        
        cmbDistrito.removeAllItems();
        cmbDistrito.addItem("- Seleccione el distrito -");        
        UbigeoDB ubigeoDB = new UbigeoDBImpl();                
        try {
            
            String departamento = cmbDepartamento.getSelectedItem().toString();
            String provincia = cmbProvincia.getSelectedItem().toString();
            
            List<Ubigeo> distritos = ubigeoDB.obtenerDistritosPorProvincia(departamento, provincia);
            
            distritos.forEach( p ->{
                cmbDistrito.addItem(p.getNomDistrito());
            });
           
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
    private void BuscarIdUbigeo() {        
        UbigeoDB ubigeoDB = new UbigeoDBImpl();                
        try {            
            String departamento = cmbDepartamento.getSelectedItem().toString();
            String provincia = cmbProvincia.getSelectedItem().toString();
            String distrito = cmbDistrito.getSelectedItem().toString();
            
            varID = ubigeoDB.ObtenerIdUbigeo(departamento, provincia, distrito);
           
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
    
    private void buscarEstablecimiento() {
        
        String cols[] = {"N°","RUC", "Nombre", "Departamento", "Provincia", "Distrito", "Tipo", "Estado"};
        
        DefaultTableModel dtm= new DefaultTableModel(cols, 0);
        tblEntidades.setModel(dtm);      
        
        EntidadDB entidadDB = new EntidadDBImpl();
        try {            
            String departamento = cmbDepartamento.getSelectedItem().toString();
            String provincia = cmbProvincia.getSelectedItem().toString();
            String distrito = cmbDistrito.getSelectedItem().toString();

            List<Entidad> entidades = entidadDB.buscarEntidadPorUbigeo(departamento, provincia, distrito);
            
            if (entidades.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "No se encontraron resultados para la búsqueda.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
                    
            AtomicReference<Long> contador = new AtomicReference<>(1L);
            
            entidades.forEach( p ->{
                Vector fila= new Vector<>();
                fila.add(contador.getAndSet(contador.get() + 1));
                fila.add(p.getRuc());
                fila.add(p.getNombre());                
                fila.add(p.getUbigeo().getNomDepartamento());
                fila.add(p.getUbigeo().getNomProvincia());
                fila.add(p.getUbigeo().getNomDistrito());
                fila.add(p.getTipoEntidad().getNombreTipoEntidad());
                fila.add(p.getEstadoEntidad().getNombreEstadoEntidad());
                dtm.addRow(fila);
            });
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
 /*---------------------------------------------------------------------------------------------------*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbProvincia = new javax.swing.JComboBox<>();
        cmbDepartamento = new javax.swing.JComboBox<>();
        cmbDistrito = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntidades = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Buscar por ubigeo");
        setName("BuscarUbigeo"); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del establecimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Departamento");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Provincia");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Distrito");

        cmbProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProvinciaItemStateChanged(evt);
            }
        });
        cmbProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaActionPerformed(evt);
            }
        });

        cmbDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepartamentoItemStateChanged(evt);
            }
        });
        cmbDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDepartamentoActionPerformed(evt);
            }
        });

        cmbDistrito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDistritoItemStateChanged(evt);
            }
        });
        cmbDistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDistritoActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(0, 51, 153));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar)
                        .addGap(31, 31, 31)
                        .addComponent(btnLimpiar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbDepartamento, 0, 815, Short.MAX_VALUE)
                            .addComponent(cmbProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbDistrito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnBuscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblEntidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblEntidades);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Resultados de la búsqueda:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("Seleccione los filtros: ");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       
        cmbDepartamento.removeAllItems();
        cmbProvincia.removeAllItems();
        cmbDistrito.removeAllItems();
        cmbDepartamento.addItem("- Seleccione el Departamento -");        
        cmbProvincia.addItem("- Seleccione la Provincia -");
        cmbDistrito.addItem("- Seleccione el Distrito -");
        mostrarDepartamentos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
           buscarEstablecimiento();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cmbDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDistritoActionPerformed

    }//GEN-LAST:event_cmbDistritoActionPerformed

    private void cmbDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDepartamentoActionPerformed
        
    }//GEN-LAST:event_cmbDepartamentoActionPerformed

    private void cmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvinciaActionPerformed
 
    }//GEN-LAST:event_cmbProvinciaActionPerformed

    private void cmbDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepartamentoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED && cmbDepartamento.getSelectedIndex() > 0) {
            mostrarProvincias();
        }
    }//GEN-LAST:event_cmbDepartamentoItemStateChanged

    private void cmbProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProvinciaItemStateChanged
         if (evt.getStateChange() == ItemEvent.SELECTED && cmbProvincia.getSelectedIndex() > 0) {
            mostrarDistritos();
        } 
    }//GEN-LAST:event_cmbProvinciaItemStateChanged

    private void cmbDistritoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDistritoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED && cmbDistrito.getSelectedIndex() > 0) {
            BuscarIdUbigeo();
            System.out.println("ID Ubigeo: " + varID);
        }
    }//GEN-LAST:event_cmbDistritoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbDepartamento;
    private javax.swing.JComboBox<String> cmbDistrito;
    private javax.swing.JComboBox<String> cmbProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEntidades;
    // End of variables declaration//GEN-END:variables
}
