package proyecto_recursoshumanos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_capacitacion extends javax.swing.JFrame {

    private Connection cn;
    private PreparedStatement pst;
    private ResultSet rs;

    public frm_capacitacion() {
        initComponents();

        // No permitir que JTable reajuste automáticamente las columnas
        jTable_MantCapacitacion.setAutoResizeMode(
                javax.swing.JTable.AUTO_RESIZE_OFF
        );

        // Anchos fijos de las columnas
        jTable_MantCapacitacion.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable_MantCapacitacion.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable_MantCapacitacion.getColumnModel().getColumn(2).setPreferredWidth(234);
        jTable_MantCapacitacion.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable_MantCapacitacion.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable_MantCapacitacion.getColumnModel().getColumn(5).setPreferredWidth(120);

        // No permitir cambiar el ancho con el mouse
        jTable_MantCapacitacion.getTableHeader().setResizingAllowed(false);

        // Dejar la tabla vacía al iniciar
        DefaultTableModel modelo
                = (DefaultTableModel) jTable_MantCapacitacion.getModel();

        modelo.setRowCount(0);

        jTextidcapacitacion.setEnabled(false);

        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);

        jTable_MantCapacitacion.addMouseListener(
                new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cargarDatosTabla();
            }
        }
        );
    }

    // =====================================================
    // AQUÍ VA cargarTabla()
    // =====================================================
    private void cargarTabla() {

        DefaultTableModel modelo
                = (DefaultTableModel) jTable_MantCapacitacion.getModel();

        modelo.setRowCount(0);

        String sql = "SELECT id_capacitacion, "
                + "nombre_capacitacion, "
                + "institucion_organizadora, "
                + "fecha_inicio, "
                + "fecha_fin, "
                + "horas_academicas "
                + "FROM capacitacion "
                + "WHERE estado_capacitacion = 'ACTIVO'";

        try {

            cn = new Conexion_bd_Luis().conectar();
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {

                modelo.addRow(new Object[]{
                    rs.getInt("id_capacitacion"),
                    rs.getString("nombre_capacitacion"),
                    rs.getString("institucion_organizadora"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getInt("horas_academicas")
                });
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar: " + e.getMessage()
            );

        } finally {
            cerrarConexion();
        }
    }

    private void cargarDatosTabla() {

        int fila = jTable_MantCapacitacion.getSelectedRow();

        if (fila >= 0) {

            jTextidcapacitacion.setText(
                    jTable_MantCapacitacion.getValueAt(fila, 0).toString()
            );

            jComboBoxnombrecapacitacion.setSelectedItem(
                    jTable_MantCapacitacion.getValueAt(fila, 1).toString()
            );

            jTextinstitucionorganizadora.setText(
                    jTable_MantCapacitacion.getValueAt(fila, 2).toString()
            );

            jTextfechainicio.setText(
                    jTable_MantCapacitacion.getValueAt(fila, 3).toString()
            );

            jTextfechafin.setText(
                    jTable_MantCapacitacion.getValueAt(fila, 4).toString()
            );

            jTexthorasacademicas.setText(
                    jTable_MantCapacitacion.getValueAt(fila, 5).toString()
            );

            BTN_Guardar.setEnabled(false);
            BTN_Modificar.setEnabled(true);
            BTN_Desactivar.setEnabled(true);
        }
    }

    // =====================================================
    // AQUÍ VA limpiarCampos()
    // =====================================================
    private void limpiarCampos() {

        jTextidcapacitacion.setText("");
        jComboBoxnombrecapacitacion.setSelectedIndex(0);
        jTextinstitucionorganizadora.setText("");
        jTextfechainicio.setText("");
        jTextfechafin.setText("");
        jTexthorasacademicas.setText("");

        jTable_MantCapacitacion.clearSelection();
    }

    // =====================================================
    // AQUÍ VA cerrarConexion()
    // =====================================================
    private void cerrarConexion() {

        try {

            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al cerrar conexión: "
                    + e.getMessage()
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextidcapacitacion = new javax.swing.JTextField();
        jTextinstitucionorganizadora = new javax.swing.JTextField();
        jTextfechainicio = new javax.swing.JTextField();
        jTextfechafin = new javax.swing.JTextField();
        jTexthorasacademicas = new javax.swing.JTextField();
        BTN_Buscar = new javax.swing.JButton();
        jComboBoxnombrecapacitacion = new javax.swing.JComboBox<>();
        jLabelcapacitacion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_MantCapacitacion = new javax.swing.JTable();
        BTN_Cerrar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Id Capacitacion:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 14, 133, -1));

        jLabel3.setText("Nombre Capacitacion:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 39, 133, -1));

        jLabel4.setText("Institucion Organizadora:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 67, -1, -1));

        jLabel5.setText("Fecha Inicio:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 95, 133, -1));

        jLabel6.setText("Fecha Fin:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 123, 133, -1));

        jLabel7.setText("Hora Academicas:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 154, 133, -1));

        jTextidcapacitacion.addActionListener(this::jTextidcapacitacionActionPerformed);
        jPanel2.add(jTextidcapacitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 8, 380, -1));

        jTextinstitucionorganizadora.addActionListener(this::jTextinstitucionorganizadoraActionPerformed);
        jPanel2.add(jTextinstitucionorganizadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 64, 380, -1));

        jTextfechainicio.addActionListener(this::jTextfechainicioActionPerformed);
        jPanel2.add(jTextfechainicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 92, 380, -1));

        jTextfechafin.addActionListener(this::jTextfechafinActionPerformed);
        jPanel2.add(jTextfechafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 120, 380, -1));

        jTexthorasacademicas.addActionListener(this::jTexthorasacademicasActionPerformed);
        jPanel2.add(jTexthorasacademicas, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 148, 380, -1));

        BTN_Buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTN_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Buscar.png"))); // NOI18N
        BTN_Buscar.setText("Buscar");
        BTN_Buscar.addActionListener(this::BTN_BuscarActionPerformed);
        jPanel2.add(BTN_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 8, -1, 49));

        jComboBoxnombrecapacitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Excel Avanzado", "Gestion de Recursos Humanos", "Seguridad y Salud en el Trabajo", "Liderazgo Empresarial", "Trabajo en Equipo", "Atencion al Cliente", "Gestion de Proyectos", "Oracle SQL", "Redes y Soporte Tecnico", "Comunicacion Efectiva" }));
        jPanel2.add(jComboBoxnombrecapacitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 36, 380, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 60, 876, -1));

        jLabelcapacitacion.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelcapacitacion.setText("CAPACITACION");
        jLabelcapacitacion.setPreferredSize(new java.awt.Dimension(82, 16));
        jPanel1.add(jLabelcapacitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 17, 226, 31));

        jTable_MantCapacitacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre Capacitacion", "Institucion Organizadora", "Fecha Incio", "Fecha Fin", "Horas Academicas"
            }
        ));
        jScrollPane1.setViewportView(jTable_MantCapacitacion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 303, 870, 200));

        BTN_Cerrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTN_Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Salir.png"))); // NOI18N
        BTN_Cerrar.setText("Cerrar");
        BTN_Cerrar.addActionListener(this::BTN_CerrarActionPerformed);
        jPanel1.add(BTN_Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 509, -1, -1));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Nuevo.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel1.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 247, 180, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Guardar.png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel1.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 247, 180, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Modificar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel1.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 247, 180, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/serv.png"))); // NOI18N
        BTN_Desactivar.setText("DAR DE BAJA");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        jPanel1.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 247, 180, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextidcapacitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextidcapacitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextidcapacitacionActionPerformed

    private void jTextinstitucionorganizadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextinstitucionorganizadoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextinstitucionorganizadoraActionPerformed

    private void jTextfechainicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextfechainicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextfechainicioActionPerformed

    private void jTextfechafinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextfechafinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextfechafinActionPerformed

    private void jTexthorasacademicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTexthorasacademicasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTexthorasacademicasActionPerformed

    private void BTN_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BuscarActionPerformed
        cargarTabla();
    }//GEN-LAST:event_BTN_BuscarActionPerformed

    private void BTN_CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_BTN_CerrarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        limpiarCampos();

        jTextidcapacitacion.setEnabled(false);

        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);

        jComboBoxnombrecapacitacion.setSelectedIndex(0);

    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        limpiarCampos();
        BTN_Guardar.setEnabled(false);
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed

        if (jTextidcapacitacion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una capacitación.");
            return;
        }

        if (jComboBoxnombrecapacitacion.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione el nombre de la capacitación.");
            return;
        }

        String sql = "UPDATE capacitacion SET "
                + "nombre_capacitacion = ?, "
                + "institucion_organizadora = ?, "
                + "fecha_inicio = ?, "
                + "fecha_fin = ?, "
                + "horas_academicas = ? "
                + "WHERE id_capacitacion = ?";

        try {

            cn = new Conexion_bd_Luis().conectar();
            pst = cn.prepareStatement(sql);

            pst.setString(1,
                    jComboBoxnombrecapacitacion.getSelectedItem().toString());

            pst.setString(2,
                    jTextinstitucionorganizadora.getText().trim());

            pst.setDate(3,
                    java.sql.Date.valueOf(
                            jTextfechainicio.getText().trim()));

            pst.setDate(4,
                    java.sql.Date.valueOf(
                            jTextfechafin.getText().trim()));

            pst.setInt(5,
                    Integer.parseInt(
                            jTexthorasacademicas.getText().trim()));

            pst.setInt(6,
                    Integer.parseInt(
                            jTextidcapacitacion.getText().trim()));

            pst.executeUpdate();

            // ACTUALIZAR LA TABLA INMEDIATAMENTE
            cargarTabla();

            // LIMPIAR LOS CAMPOS
            limpiarCampos();

            BTN_Modificar.setEnabled(false);
            BTN_Desactivar.setEnabled(false);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al modificar: " + e.getMessage()
            );

        } finally {
            cerrarConexion();
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        if (jTextidcapacitacion.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Seleccione una capacitación.");
        return;
    }

    if (jComboBoxnombrecapacitacion.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(this,
                "Seleccione una capacitación.");
        return;
    }

    String sql = "UPDATE capacitacion SET "
            + "nombre_capacitacion = ?, "
            + "institucion_organizadora = ?, "
            + "fecha_inicio = ?, "
            + "fecha_fin = ?, "
            + "horas_academicas = ? "
            + "WHERE id_capacitacion = ?";
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (Exception ex) {
            System.out.println(
                    "Error al configurar la apariencia: "
                    + ex.getMessage()
            );
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frm_capacitacion().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Buscar;
    private javax.swing.JButton BTN_Cerrar;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JComboBox<String> jComboBoxnombrecapacitacion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelcapacitacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_MantCapacitacion;
    private javax.swing.JTextField jTextfechafin;
    private javax.swing.JTextField jTextfechainicio;
    private javax.swing.JTextField jTexthorasacademicas;
    private javax.swing.JTextField jTextidcapacitacion;
    private javax.swing.JTextField jTextinstitucionorganizadora;
    // End of variables declaration//GEN-END:variables
}
