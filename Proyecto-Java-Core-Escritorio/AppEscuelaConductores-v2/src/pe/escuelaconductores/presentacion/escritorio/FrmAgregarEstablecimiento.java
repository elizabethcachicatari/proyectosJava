package pe.escuelaconductores.presentacion.escritorio;

import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pe.escuelaconductores.bean.Entidad;
import pe.escuelaconductores.bean.EstadoEntidad;
import pe.escuelaconductores.bean.TipoEntidad;
import pe.escuelaconductores.bean.Ubigeo;
import pe.escuelaconductores.persistence.EntidadDB;
import pe.escuelaconductores.persistence.EntidadDBImpl;
import pe.escuelaconductores.persistence.EstadoEntidadDB;
import pe.escuelaconductores.persistence.EstadoEntidadDBImpl;
import pe.escuelaconductores.persistence.TipoEntidadDB;
import pe.escuelaconductores.persistence.TipoEntidadDBImpl;
import pe.escuelaconductores.persistence.UbigeoDB;
import pe.escuelaconductores.persistence.UbigeoDBImpl;

public class FrmAgregarEstablecimiento extends javax.swing.JInternalFrame {

    private Long varID;
    private Map<String, Long> tipoEntidadMap = new HashMap<>();
    private Map<String, Long> estadoEntidadMap = new HashMap<>();
       
    public FrmAgregarEstablecimiento() {
        initComponents();
        mostrarDepartamentos();
        mostrarTipoEntidad();
        mostrarEstadoEntidad();
        ImageIcon icoCancelar = new ImageIcon(getClass().getResource("/pe/escuelaconductores/img/cancel.png"));
        ImageIcon icoGuardar = new ImageIcon(getClass().getResource("/pe/escuelaconductores/img/save2.png"));
        btnCancelar.setIcon(icoCancelar);
        btnGuardar.setIcon(icoGuardar);
    }

    private void mostrarTipoEntidad() {
        
        cmbTipoEntidad.removeAllItems();
        cmbTipoEntidad.addItem(null);    

        TipoEntidadDB tipoEntidadDB = new TipoEntidadDBImpl();
                
        try {
            List<TipoEntidad> tipos = tipoEntidadDB.listado();            
            for (TipoEntidad tipo : tipos) {
            cmbTipoEntidad.addItem(tipo.getNombreTipoEntidad());
            tipoEntidadMap.put(tipo.getNombreTipoEntidad(), tipo.getIdTipoEntidad());
            }

            
           
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    } 
    
     private void mostrarEstadoEntidad() {
        
        cmbEstadoEntidad.removeAllItems();
        cmbEstadoEntidad.addItem(null);       

        EstadoEntidadDB estadoEntidadDB = new EstadoEntidadDBImpl();
                
        try {
            List<EstadoEntidad> estados = estadoEntidadDB.listado();
            for (EstadoEntidad estado : estados) {
            cmbEstadoEntidad.addItem(estado.getNombreEstadoEntidad());
            estadoEntidadMap.put(estado.getNombreEstadoEntidad(), estado.getIdEstadoEntidad());
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
   
    
    private void mostrarDepartamentos() {
        
        cmbDepartamento.removeAllItems();
        cmbDepartamento.addItem("- Seleccione una opción -");    
        cmbProvincia.removeAllItems();
        cmbProvincia.addItem("- Seleccione una opción -");
        cmbDistrito.removeAllItems();
        cmbDistrito.addItem("- Seleccione una opción -");
              
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
        cmbProvincia.addItem("- Seleccione una opción -");
        cmbDistrito.removeAllItems();       
        cmbDistrito.addItem("- Seleccione una opción -");
        
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
        cmbDistrito.addItem("- Seleccione una opción -");        
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
    
    private boolean guardarEstablecimiento() {
                        
        try {

            String ruc = txtRuc.getText();
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String correo = txtRuc.getText();
            String telefono = txtTelefono.getText();
           // Long idTipoEntidad = cmbTipoEntidad.get;
            String tipoSeleccionado = cmbTipoEntidad.getSelectedItem().toString();
            String estadoSeleccionado = cmbEstadoEntidad.getSelectedItem().toString();
            Long idTipoEntidad = tipoEntidadMap.get(tipoSeleccionado);
            Long idEstadoEntidad = estadoEntidadMap.get(estadoSeleccionado);

            System.out.println("ID tipo entidad: " + idTipoEntidad);
             System.out.println("ID estado entidad: " + idEstadoEntidad);
            
           // Long idTipoEntidad = 1L;
           // Long idEstadoEntidad = 1L;
            
            EntidadDB entidadDB = new EntidadDBImpl();
            Entidad entidad = new Entidad(ruc, nombre, direccion, correo, telefono, varID, idTipoEntidad, idEstadoEntidad);
            return entidadDB.insertar_SP(entidad);
 
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }    
      
  
/* ------------------------------------------------------------------------------------------------------------------     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmbTipoEntidad = new javax.swing.JComboBox<>();
        cmbEstadoEntidad = new javax.swing.JComboBox<>();
        txtRuc = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        cmbProvincia = new javax.swing.JComboBox<>();
        cmbDepartamento = new javax.swing.JComboBox<>();
        cmbDistrito = new javax.swing.JComboBox<>();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Nuevo Establecimiento");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del establecimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("RUC");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Dirección");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Departamento");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Correo");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Télefono");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Provincia");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Tipo ");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Distrito");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Estado");

        cmbEstadoEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoEntidadActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

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

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setText("Guadar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 204, 204));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbDepartamento, javax.swing.GroupLayout.Alignment.LEADING, 0, 244, Short.MAX_VALUE)
                                .addComponent(cmbTipoEntidad, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addComponent(txtRuc, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtNombre))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmbEstadoEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cmbProvincia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEstadoEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTipoEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEstadoEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoEntidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoEntidadActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed

    }//GEN-LAST:event_txtNombreActionPerformed

    private void cmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvinciaActionPerformed

    }//GEN-LAST:event_cmbProvinciaActionPerformed

    private void cmbDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDepartamentoActionPerformed
 
    }//GEN-LAST:event_cmbDepartamentoActionPerformed

    private void cmbDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDistritoActionPerformed

    }//GEN-LAST:event_cmbDistritoActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Boolean rpta;
        rpta = guardarEstablecimiento();
        
        if(rpta)
        {
            JOptionPane.showMessageDialog(null, "Se ha guardado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se pudo guardar.", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
           
        }
    }//GEN-LAST:event_cmbDistritoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbDepartamento;
    private javax.swing.JComboBox<String> cmbDistrito;
    private javax.swing.JComboBox<String> cmbEstadoEntidad;
    private javax.swing.JComboBox<String> cmbProvincia;
    private javax.swing.JComboBox<String> cmbTipoEntidad;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
