package proyecto_recursoshumanos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class frm_departamento_academico extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger
            = java.util.logging.Logger.getLogger(
                    frm_departamento_academico.class.getName());

    /**
     * Creates new form frm_departamento_academico
     */
    public frm_departamento_academico() {
        initComponents();

        setLocationRelativeTo(null);

        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);

    }

    /**
     * Cargar departamentos académicos
     */
    private void cargarDepartamentos() {

        javax.swing.table.DefaultTableModel modelo
                = (javax.swing.table.DefaultTableModel) jTable_Mantdepartamento.getModel();

        modelo.setRowCount(0);

        String sql = "SELECT id_departamento_academico, "
                + "nombre_departamento, "
                + "codigo_area, "
                + "descripcion, "
                + "estado "
                + "FROM departamento_academico "
                + "ORDER BY id_departamento_academico";

        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/proyecto_recursoshumanos",
                        "root",
                        ""
                ); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        modelo.addRow(new Object[]{
                            rs.getInt("id_departamento_academico"),
                            rs.getString("nombre_departamento"),
                            rs.getString("codigo_area"),
                            rs.getString("descripcion"),
                            rs.getString("estado")
                        });
                    }

                } catch (SQLException e) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Error al cargar los departamentos académicos:\n"
                            + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextnombredepartamento = new javax.swing.JTextField();
        jTextcodigoarea = new javax.swing.JTextField();
        jTextiddepartamento = new javax.swing.JTextField();
        jTextestado = new javax.swing.JTextField();
        BTN_Buscar = new javax.swing.JButton();
        jComboBoxdescripcion = new javax.swing.JComboBox<>();
        BTN_Nuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Mantdepartamento = new javax.swing.JTable();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        BTN_Cerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("DEPARTAMENTO ACADEMICO");
        jLabel1.setPreferredSize(new java.awt.Dimension(82, 16));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 11, 390, 31));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Id Departamento:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 14, 127, -1));

        jLabel3.setText("Nombre Departamento:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 39, -1, -1));

        jLabel4.setText("Codigo Area:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 67, 127, -1));

        jLabel5.setText("Descripcion:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 95, 127, -1));

        jLabel6.setText("Estado:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 123, 127, -1));

        jTextnombredepartamento.addActionListener(this::jTextnombredepartamentoActionPerformed);
        jPanel2.add(jTextnombredepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 36, 380, -1));

        jTextcodigoarea.addActionListener(this::jTextcodigoareaActionPerformed);
        jPanel2.add(jTextcodigoarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 64, 380, -1));

        jTextiddepartamento.addActionListener(this::jTextiddepartamentoActionPerformed);
        jPanel2.add(jTextiddepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 8, 380, -1));

        jTextestado.addActionListener(this::jTextestadoActionPerformed);
        jPanel2.add(jTextestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 120, 380, -1));

        BTN_Buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTN_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Buscar.png"))); // NOI18N
        BTN_Buscar.setText("Buscar");
        BTN_Buscar.addActionListener(this::BTN_BuscarActionPerformed);
        jPanel2.add(BTN_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 9, -1, 49));

        jComboBoxdescripcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Area de administracion y gestion", "Area de contabilidad y finanzas", "Area de gestion del talento humano", "Area de sistemas y tecnologia", "Area de procesos industriales", "Area de construccion e infraestructura", "Area de gestion ambiental", "Area de ciencias juridicas", "Area de psicologia y desarrollo humano", "Area de formacion educativa", "Area de marketing y publicidad", "Area de economia y gestion financiera", "Area de diseño y arquitectura", "Area de diseño y comunicacion visual", "Area de ciencias de la salud", "Area de medicina y salud", "Area de logistica y abastecimiento", "Area de seguridad y prevencion", "Area de redes y comunicaciones", "Area de datos e inteligencia de negocios" }));
        jPanel2.add(jComboBoxdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 92, 380, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 60, 940, -1));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Nuevo.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel1.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 220, 180, 50));

        jTable_Mantdepartamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre departamento", "Codigo area", "Descripcion", "Estado"
            }
        ));
        jTable_Mantdepartamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_MantdepartamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Mantdepartamento);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 276, 934, 200));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Guardar.png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel1.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 220, 180, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Modificar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel1.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 220, 180, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/serv.png"))); // NOI18N
        BTN_Desactivar.setText("DAR DE BAJA");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        jPanel1.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 220, 244, 50));

        BTN_Cerrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTN_Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Salir.png"))); // NOI18N
        BTN_Cerrar.setText("Cerrar");
        BTN_Cerrar.addActionListener(this::BTN_CerrarActionPerformed);
        jPanel1.add(BTN_Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(814, 482, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextnombredepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextnombredepartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextnombredepartamentoActionPerformed

    private void jTextcodigoareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextcodigoareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextcodigoareaActionPerformed

    private void jTextiddepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextiddepartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextiddepartamentoActionPerformed

    private void jTextestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextestadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextestadoActionPerformed

    private void BTN_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BuscarActionPerformed
        cargarDepartamentos();

        // Después de buscar, se habilita Nuevo
        BTN_Nuevo.setEnabled(true);

        // Guardar, Modificar y Dar de baja siguen desactivados
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
    }//GEN-LAST:event_BTN_BuscarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed

        jTextiddepartamento.setText("");
        jTextnombredepartamento.setText("");
        jTextcodigoarea.setText("");
        jTextestado.setText("");
        jComboBoxdescripcion.setSelectedIndex(0);

        jTextiddepartamento.setEnabled(false);

        // Nuevo habilita Guardar
        BTN_Guardar.setEnabled(true);

        // Estos siguen desactivados
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);

        jTextnombredepartamento.requestFocus();
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        String nombre = jTextnombredepartamento.getText().trim();
        String codigoArea = jTextcodigoarea.getText().trim();
        String descripcion = jComboBoxdescripcion.getSelectedItem().toString();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese el nombre del departamento.");
            jTextnombredepartamento.requestFocus();
            return;
        }

        if (codigoArea.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese el código de área.");
            jTextcodigoarea.requestFocus();
            return;
        }

        if (jComboBoxdescripcion.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una descripción.");
            return;
        }

        String sql = "INSERT INTO departamento_academico "
                + "(nombre_departamento, codigo_area, descripcion, estado) "
                + "VALUES (?, ?, ?, 'ACTIVO')";

        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/proyecto_recursoshumanos",
                        "root",
                        ""
                ); PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setString(1, nombre);
                    ps.setString(2, codigoArea);
                    ps.setString(3, descripcion);

                    int filas = ps.executeUpdate();

                    if (filas > 0) {

                        // Actualizar tabla inmediatamente
                        cargarDepartamentos();

                        // Limpiar campos
                        jTextiddepartamento.setText("");
                        jTextnombredepartamento.setText("");
                        jTextcodigoarea.setText("");
                        jTextestado.setText("");
                        jComboBoxdescripcion.setSelectedIndex(0);

                        // Botones
                        BTN_Guardar.setEnabled(false);
                        BTN_Modificar.setEnabled(false);
                        BTN_Desactivar.setEnabled(false);
                    }

                } catch (SQLException e) {

                    JOptionPane.showMessageDialog(this,
                            "Error al guardar:\n" + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed

        String idTexto = jTextiddepartamento.getText().trim();
        String nombre = jTextnombredepartamento.getText().trim();
        String codigoArea = jTextcodigoarea.getText().trim();
        String descripcion = jComboBoxdescripcion.getSelectedItem().toString();
        String estado = jTextestado.getText().trim();

        // Validar ID
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un departamento.");
            return;
        }

        // Validar nombre
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese el nombre del departamento.");
            jTextnombredepartamento.requestFocus();
            return;
        }

        // Validar código
        if (codigoArea.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese el código de área.");
            jTextcodigoarea.requestFocus();
            return;
        }

        // Validar descripción
        if (jComboBoxdescripcion.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una descripción.");
            return;
        }

        // Si el estado está vacío, mantener ACTIVO
        if (estado.isEmpty()) {
            estado = "ACTIVO";
        }

        String sql = "UPDATE departamento_academico "
                + "SET nombre_departamento = ?, "
                + "codigo_area = ?, "
                + "descripcion = ?, "
                + "estado = ? "
                + "WHERE id_departamento_academico = ?";

        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/proyecto_recursoshumanos",
                        "root",
                        ""
                ); PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setString(1, nombre);
                    ps.setString(2, codigoArea);
                    ps.setString(3, descripcion);
                    ps.setString(4, estado);
                    ps.setInt(5, Integer.parseInt(idTexto));

                    int filas = ps.executeUpdate();

                    if (filas > 0) {

                        // Actualizar la tabla
                        cargarDepartamentos();

                        // Limpiar campos
                        jTextiddepartamento.setText("");
                        jTextnombredepartamento.setText("");
                        jTextcodigoarea.setText("");
                        jTextestado.setText("");
                        jComboBoxdescripcion.setSelectedIndex(0);

                        // Desactivar botones después de modificar
                        BTN_Guardar.setEnabled(false);
                        BTN_Modificar.setEnabled(false);
                        BTN_Desactivar.setEnabled(false);

                    }

                } catch (SQLException e) {

                    JOptionPane.showMessageDialog(this,
                            "Error al modificar:\n" + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(this,
                            "El ID del departamento no es válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        if (jTextiddepartamento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un departamento.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de dar de baja este departamento?",
                "Confirmar baja",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "UPDATE departamento_academico "
                + "SET estado = 'INACTIVO' "
                + "WHERE id_departamento_academico = ?";

        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/proyecto_recursoshumanos",
                        "root",
                        ""
                ); PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setInt(1,
                            Integer.parseInt(
                                    jTextiddepartamento.getText().trim()
                            )
                    );

                    int filas = ps.executeUpdate();

                    if (filas > 0) {

                        // Actualizar tabla
                        cargarDepartamentos();

                        // Limpiar campos
                        jTextiddepartamento.setText("");
                        jTextnombredepartamento.setText("");
                        jTextcodigoarea.setText("");
                        jTextestado.setText("");
                        jComboBoxdescripcion.setSelectedIndex(0);

                        // Desactivar botones
                        BTN_Modificar.setEnabled(false);
                        BTN_Desactivar.setEnabled(false);
                        BTN_Guardar.setEnabled(false);
                    }

                } catch (SQLException e) {

                    JOptionPane.showMessageDialog(this,
                            "Error al dar de baja:\n" + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(this,
                            "El ID del departamento no es válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CerrarActionPerformed
        dispose();

    }//GEN-LAST:event_BTN_CerrarActionPerformed

    private void jTable_MantdepartamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_MantdepartamentoMouseClicked
        int fila = jTable_Mantdepartamento.getSelectedRow();

        if (fila >= 0) {

            // ID
            jTextiddepartamento.setText(
                    jTable_Mantdepartamento.getValueAt(fila, 0).toString()
            );

            // Nombre del departamento
            jTextnombredepartamento.setText(
                    jTable_Mantdepartamento.getValueAt(fila, 1).toString()
            );

            // Código de área
            jTextcodigoarea.setText(
                    jTable_Mantdepartamento.getValueAt(fila, 2).toString()
            );

            // Descripción en la lista desplegable
            jComboBoxdescripcion.setSelectedItem(
                    jTable_Mantdepartamento.getValueAt(fila, 3).toString()
            );

            // Estado
            jTextestado.setText(
                    jTable_Mantdepartamento.getValueAt(fila, 4).toString()
            );

            // ID no se puede modificar
            jTextiddepartamento.setEnabled(false);

            // Al seleccionar un registro
            BTN_Guardar.setEnabled(false);
            BTN_Modificar.setEnabled(true);
            BTN_Desactivar.setEnabled(true);
        }
    }//GEN-LAST:event_jTable_MantdepartamentoMouseClicked

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
                logger.log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(() -> new frm_departamento_academico().setVisible(true));
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Buscar;
    private javax.swing.JButton BTN_Cerrar;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JComboBox<String> jComboBoxdescripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Mantdepartamento;
    private javax.swing.JTextField jTextcodigoarea;
    private javax.swing.JTextField jTextestado;
    private javax.swing.JTextField jTextiddepartamento;
    private javax.swing.JTextField jTextnombredepartamento;
    // End of variables declaration//GEN-END:variables
}
