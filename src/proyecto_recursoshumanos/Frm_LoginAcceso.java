package proyecto_recursoshumanos;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Frm_LoginAcceso extends javax.swing.JFrame {

    private int intentosFallidos = 0;

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Frm_LoginAcceso.class.getName());
public Frm_LoginAcceso() {
    this.setUndecorated(true);
    initComponents();
    this.setLocationRelativeTo(null);

    llenarComboCargo();
}

private void llenarComboCargo() {

    JComboBox_Cargos.removeAllItems();
    JComboBox_Cargos.addItem("<< Seleccione Cargo >>");

    Conexion_bd_Luis bd = new Conexion_bd_Luis();

    try {
        ResultSet rs = bd.ComboBox_ListarRoles();

        while (rs != null && rs.next()) {
            String nombreRol = rs.getString("Nombre_Rol");

            if (nombreRol != null) {
                JComboBox_Cargos.addItem(nombreRol.trim());
            }
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error al cargar los cargos desde la BD:\n"
                + e.getMessage(),
                "Error BD",
                JOptionPane.ERROR_MESSAGE
        );

    } finally {
        bd.cerrarConexion();
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        JComboBox_Cargos = new javax.swing.JComboBox<>();
        txtcodigousuario = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btningresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Acceso restringido - Solo para personal autorizado");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 300, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acceso al sistema..!", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JComboBox_Cargos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JComboBox_Cargos.setForeground(new java.awt.Color(0, 0, 204));
        JComboBox_Cargos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(JComboBox_Cargos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 260, -1));

        txtcodigousuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtcodigousuario.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(txtcodigousuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 260, -1));

        txtpassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtpassword.setForeground(new java.awt.Color(0, 0, 204));
        txtpassword.addActionListener(this::txtpasswordActionPerformed);
        jPanel1.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 260, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Ingrese Codigo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 170, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Ingrese contraseña:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 200, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Seleccione Cargo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, -1));

        btnsalir.setBackground(new java.awt.Color(0, 0, 204));
        btnsalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(255, 255, 255));
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(this::btnsalirActionPerformed);
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 120, -1));

        btningresar.setBackground(new java.awt.Color(0, 0, 204));
        btningresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btningresar.setForeground(new java.awt.Color(255, 255, 255));
        btningresar.setText("INGRESAR");
        btningresar.addActionListener(this::btningresarActionPerformed);
        jPanel1.add(btningresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 450, 180));

        jPanel3.setBackground(new java.awt.Color(0, 0, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Copyright © SENATI 2026");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 200, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Software empresarial, desarrollado en el tercer ciclo de Ingenieria de Software ");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 470, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 650, 60));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-security-94.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 150, 140));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // Muestra una ventana emergente de confirmación
        int opcion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea salir del sistema?",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Si el usuario hace clic en "Sí" (YES_OPTION)
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // Cierra la aplicación por completo
        }
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresarActionPerformed
    try {

        // ==============================
        // OBTENER DATOS
        // ==============================

        String codigo = txtcodigousuario.getText().trim();

        String password = new String(
                txtpassword.getPassword()
        ).trim();

        Object itemSeleccionado =
                JComboBox_Cargos.getSelectedItem();

        String cargo = "";

        if (itemSeleccionado != null) {
            cargo = itemSeleccionado.toString().trim();
        }


        // ==============================
        // VALIDAR CÓDIGO
        // ==============================

        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese su código de usuario.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            txtcodigousuario.requestFocus();
            return;
        }


        // ==============================
        // VALIDAR CONTRASEÑA
        // ==============================

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese su contraseña.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            txtpassword.requestFocus();
            return;
        }


        // ==============================
        // VALIDAR CARGO
        // ==============================

        if (cargo.isEmpty()
                || cargo.equals("<< Seleccione Cargo >>")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un cargo.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            JComboBox_Cargos.requestFocus();
            return;
        }


        // ==============================
        // CONECTAR CON BD
        // ==============================

        Conexion_bd_Luis conexionBD =
                new Conexion_bd_Luis();


        // ==============================
        // VALIDAR LOGIN
        // ==============================

        boolean acceso = conexionBD.validarLogin(
                codigo,
                password,
                cargo
        );


        // ==============================
        // ACCESO CORRECTO
        // ==============================

        if (acceso) {

            intentosFallidos = 0;

            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido al sistema.",
                    "Acceso concedido",
                    JOptionPane.INFORMATION_MESSAGE
            );

            Frm_Menu_Principal menu =
                    new Frm_Menu_Principal();

            menu.setLocationRelativeTo(null);
            menu.setVisible(true);

            this.dispose();

        } else {

            // ==============================
            // ACCESO INCORRECTO
            // ==============================

            intentosFallidos++;

            if (intentosFallidos >= 3) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ha superado el número máximo de intentos.\n"
                        + "El acceso ha sido bloqueado por seguridad.",
                        "Cuenta bloqueada",
                        JOptionPane.ERROR_MESSAGE
                );

                System.exit(0);
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Credenciales incorrectas o usuario inactivo.\n"
                    + "Intento " + intentosFallidos + " de 3.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE
            );

            txtpassword.setText("");
            txtpassword.requestFocus();
        }

        conexionBD.cerrarConexion();

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                "Error de conexión con la Base de Datos:\n"
                + ex.getMessage(),
                "Error crítico",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btningresarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Frm_LoginAcceso().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JComboBox_Cargos;
    private javax.swing.JButton btningresar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtcodigousuario;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
