package proyecto_recursoshumanos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class frm_Mantpermisolicencia extends javax.swing.JFrame {

    private Connection cn;

    private final String URL = "jdbc:mysql://localhost:3306/proyecto_recursoshumanos";
    private final String USER = "root";
    private final String PASSWORD = "";

    private void conectar() {
        try {
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error de conexión: " + e.getMessage());
        }
    }
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_Mantpermisolicencia.class.getName());

    public frm_Mantpermisolicencia() {
        initComponents();

        conectar();
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
    }

    private void cargarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) JTABLE_Mantpermisolicencia.getModel();
        modelo.setRowCount(0);

        String sql = "SELECT id_permiso_licencia, id_empleado, "
                + "id_tipo_permiso, id_estado_solicitud, "
                + "fecha_inicio, fecha_fin, motivo_detalle "
                + "FROM permiso_licencia";

        try (PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                modelo.addRow(new Object[]{
                    rs.getInt("id_permiso_licencia"),
                    rs.getInt("id_empleado"),
                    rs.getInt("id_tipo_permiso"),
                    rs.getInt("id_estado_solicitud"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getString("motivo_detalle")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los permisos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtidpermisolicencia = new javax.swing.JTextField();
        BTN_verpermisos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtidempleado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtidestadosolicitud = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtidtipopermiso = new javax.swing.JTextField();
        jDatefechainicio = new com.toedter.calendar.JDateChooser();
        jDatefechafin = new com.toedter.calendar.JDateChooser();
        txtmotivodetalle = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_Buscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mantpermisolicencia = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Id Permiso Licecia:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Motivo Detalle:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        txtidpermisolicencia.setEditable(false);
        txtidpermisolicencia.setBackground(new java.awt.Color(255, 255, 255));
        txtidpermisolicencia.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtidpermisolicencia.setForeground(new java.awt.Color(0, 0, 204));
        txtidpermisolicencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidpermisolicencia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(txtidpermisolicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 370, 30));

        BTN_verpermisos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_verpermisos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Buscar.png"))); // NOI18N
        BTN_verpermisos.setText("VER PERMISOS");
        BTN_verpermisos.setActionCommand("VER Permiso Licencia");
        BTN_verpermisos.addActionListener(this::BTN_verpermisosActionPerformed);
        jPanel2.add(BTN_verpermisos, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 280, 50));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Id Empleado:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Id Estado Solicitud:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Fecha Inicio:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        txtidempleado.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtidempleado.setForeground(new java.awt.Color(0, 0, 204));
        txtidempleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidempleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtidempleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidempleadoKeyTyped(evt);
            }
        });
        jPanel2.add(txtidempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 370, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Fecha Fin:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        txtidestadosolicitud.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtidestadosolicitud.setForeground(new java.awt.Color(0, 0, 204));
        txtidestadosolicitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidestadosolicitud.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtidestadosolicitud.addActionListener(this::txtidestadosolicitudActionPerformed);
        txtidestadosolicitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidestadosolicitudKeyTyped(evt);
            }
        });
        jPanel2.add(txtidestadosolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 370, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Id Tipo Permiso:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        txtidtipopermiso.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtidtipopermiso.setForeground(new java.awt.Color(0, 0, 204));
        txtidtipopermiso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidtipopermiso.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtidtipopermiso.addActionListener(this::txtidtipopermisoActionPerformed);
        txtidtipopermiso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidtipopermisoKeyTyped(evt);
            }
        });
        jPanel2.add(txtidtipopermiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 370, 30));
        jPanel2.add(jDatefechainicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 370, -1));
        jPanel2.add(jDatefechafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 370, -1));

        txtmotivodetalle.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtmotivodetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtmotivodetalle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmotivodetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtmotivodetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmotivodetalleKeyTyped(evt);
            }
        });
        jPanel2.add(txtmotivodetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 370, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 870, 310));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("PERMISO LICENCIA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 370, 30));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Nuevo.png"))); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel1.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Guardar.png"))); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel1.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Modificar.png"))); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel1.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, 200, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/serv.png"))); // NOI18N
        BTN_Desactivar.setText("DAR DE BAJA");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        jPanel1.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, 180, 50));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Buscar Permiso:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, 30));

        TXT_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarKeyTyped(evt);
            }
        });
        jPanel3.add(TXT_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BUSCAR");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 890, 50));

        JTABLE_Mantpermisolicencia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mantpermisolicencia.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mantpermisolicencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Permiso Licencia", "Id Empleado", "Id Tipo Permiso", "Id Estado Solucitud", "Fecha Inicio", "Fecha Fin ", "Motivo Detalle"
            }
        ));
        JTABLE_Mantpermisolicencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_MantpermisolicenciaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mantpermisolicencia);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 890, 260));

        BTN_PDF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        BTN_PDF.setText("Exportar");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        jPanel1.add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 730, 120, 40));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        jPanel1.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 730, 120, 40));

        BTN_Cerrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Salir.png"))); // NOI18N
        BTN_Cerrar.setText("Cerrar");
        BTN_Cerrar.addActionListener(this::BTN_CerrarActionPerformed);
        jPanel1.add(BTN_Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 730, 130, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmotivodetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmotivodetalleKeyTyped

    }//GEN-LAST:event_txtmotivodetalleKeyTyped

    private void BTN_verpermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_verpermisosActionPerformed
cargarTabla();
    }//GEN-LAST:event_BTN_verpermisosActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
    txtidpermisolicencia.setText("");
    txtidempleado.setText("");
    txtidestadosolicitud.setText("");
    txtmotivodetalle.setText("");

    jDatefechainicio.setDate(null);
    jDatefechafin.setDate(null);

    txtidempleado.requestFocus();

    BTN_Guardar.setEnabled(true);
    BTN_Modificar.setEnabled(false);
    BTN_Desactivar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
    if (txtidempleado.getText().trim().isEmpty()
            || txtidestadosolicitud.getText().trim().isEmpty()
            || txtmotivodetalle.getText().trim().isEmpty()
            || jDatefechainicio.getDate() == null
            || jDatefechafin.getDate() == null) {

        JOptionPane.showMessageDialog(this,
                "Complete todos los campos obligatorios.");

        return;
    }

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    String fechaInicio = formato.format(jDatefechainicio.getDate());
    String fechaFin = formato.format(jDatefechafin.getDate());

    String sql = "INSERT INTO permiso_licencia "
            + "(id_empleado, id_tipo_permiso, id_estado_solicitud, "
            + "fecha_inicio, fecha_fin, motivo_detalle) "
            + "VALUES (?, ?, 1, ?, ?, ?)";

    try (PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, Integer.parseInt(txtidempleado.getText()));
        ps.setInt(2, Integer.parseInt(txtidestadosolicitud.getText()));
        ps.setString(3, fechaInicio);
        ps.setString(4, fechaFin);
        ps.setString(5, txtmotivodetalle.getText());

        ps.executeUpdate();

        JOptionPane.showMessageDialog(this,
                "Permiso registrado correctamente.");

        cargarTabla();

        txtidempleado.setText("");
        txtidestadosolicitud.setText("");
        txtmotivodetalle.setText("");
        jDatefechainicio.setDate(null);
        jDatefechafin.setDate(null);

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Los IDs deben contener solamente números.");

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Error al guardar: " + e.getMessage());
    }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
    if (txtidpermisolicencia.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Seleccione un permiso de la tabla.");

        return;
    }

    if (txtidempleado.getText().trim().isEmpty()
            || txtidestadosolicitud.getText().trim().isEmpty()
            || txtmotivodetalle.getText().trim().isEmpty()
            || jDatefechainicio.getDate() == null
            || jDatefechafin.getDate() == null) {

        JOptionPane.showMessageDialog(this,
                "Complete todos los campos.");

        return;
    }

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    String fechaInicio = formato.format(jDatefechainicio.getDate());
    String fechaFin = formato.format(jDatefechafin.getDate());

    String sql = "UPDATE permiso_licencia SET "
            + "id_empleado = ?, "
            + "id_tipo_permiso = ?, "
            + "fecha_inicio = ?, "
            + "fecha_fin = ?, "
            + "motivo_detalle = ? "
            + "WHERE id_permiso_licencia = ?";

    try (PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, Integer.parseInt(txtidempleado.getText()));
        ps.setInt(2, Integer.parseInt(txtidestadosolicitud.getText()));
        ps.setString(3, fechaInicio);
        ps.setString(4, fechaFin);
        ps.setString(5, txtmotivodetalle.getText());
        ps.setInt(6, Integer.parseInt(txtidpermisolicencia.getText()));

        ps.executeUpdate();

        JOptionPane.showMessageDialog(this,
                "Permiso modificado correctamente.");

        cargarTabla();

        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_Guardar.setEnabled(true);

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Los IDs deben contener solamente números.");

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Error al modificar: " + e.getMessage());
    }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed

    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void TXT_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarKeyReleased
    String buscar = TXT_Buscar.getText().trim();

    DefaultTableModel modelo =
            (DefaultTableModel) JTABLE_Mantpermisolicencia.getModel();

    modelo.setRowCount(0);

    String sql = "SELECT id_permiso_licencia, id_empleado, "
            + "id_tipo_permiso, id_estado_solicitud, "
            + "fecha_inicio, fecha_fin, motivo_detalle "
            + "FROM permiso_licencia "
            + "WHERE CAST(id_permiso_licencia AS CHAR) LIKE ?";

    try (PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, "%" + buscar + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            modelo.addRow(new Object[]{
                rs.getInt("id_permiso_licencia"),
                rs.getInt("id_empleado"),
                rs.getInt("id_tipo_permiso"),
                rs.getInt("id_estado_solicitud"),
                rs.getDate("fecha_inicio"),
                rs.getDate("fecha_fin"),
                rs.getString("motivo_detalle")
            });
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Error al buscar: " + e.getMessage());
    }
    }//GEN-LAST:event_TXT_BuscarKeyReleased

    private void TXT_BuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarKeyTyped

    }//GEN-LAST:event_TXT_BuscarKeyTyped

    private void JTABLE_MantpermisolicenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_MantpermisolicenciaMouseClicked
    int fila = JTABLE_Mantpermisolicencia.getSelectedRow();

    if (fila >= 0) {

        txtidpermisolicencia.setText(
                JTABLE_Mantpermisolicencia.getValueAt(fila, 0).toString());

        txtidempleado.setText(
                JTABLE_Mantpermisolicencia.getValueAt(fila, 1).toString());

        txtidtipopermiso.setText(
                JTABLE_Mantpermisolicencia.getValueAt(fila, 2).toString());

        txtidestadosolicitud.setText(
                JTABLE_Mantpermisolicencia.getValueAt(fila, 3).toString());

        txtmotivodetalle.setText(
                JTABLE_Mantpermisolicencia.getValueAt(fila, 6).toString());

        try {

            java.util.Date fechaInicio =
                    (java.util.Date) JTABLE_Mantpermisolicencia
                            .getValueAt(fila, 4);

            java.util.Date fechaFin =
                    (java.util.Date) JTABLE_Mantpermisolicencia
                            .getValueAt(fila, 5);

            jDatefechainicio.setDate(fechaInicio);
            jDatefechafin.setDate(fechaFin);

        } catch (Exception e) {

            System.out.println(
                    "Error con las fechas: " + e.getMessage());
        }

        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(true);
        BTN_Desactivar.setEnabled(true);
    }
    }//GEN-LAST:event_JTABLE_MantpermisolicenciaMouseClicked

    private void BTN_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PDFActionPerformed

    }//GEN-LAST:event_BTN_PDFActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed

    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void BTN_CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CerrarActionPerformed
    dispose();
    }//GEN-LAST:event_BTN_CerrarActionPerformed

    private void txtidempleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidempleadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidempleadoKeyTyped

    private void txtidestadosolicitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidestadosolicitudKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidestadosolicitudKeyTyped

    private void txtidtipopermisoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidtipopermisoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidtipopermisoKeyTyped

    private void txtidestadosolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidestadosolicitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidestadosolicitudActionPerformed

    private void txtidtipopermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidtipopermisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidtipopermisoActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new frm_Mantpermisolicencia().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_verpermisos;
    private javax.swing.JTable JTABLE_Mantpermisolicencia;
    private javax.swing.JTextField TXT_Buscar;
    private com.toedter.calendar.JDateChooser jDatefechafin;
    private com.toedter.calendar.JDateChooser jDatefechainicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtidempleado;
    private javax.swing.JTextField txtidestadosolicitud;
    private javax.swing.JTextField txtidpermisolicencia;
    private javax.swing.JTextField txtidtipopermiso;
    private javax.swing.JTextField txtmotivodetalle;
    // End of variables declaration//GEN-END:variables
}
