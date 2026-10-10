package proyecto_recursoshumanos;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Frm_PinSeguridad extends javax.swing.JFrame {

    private int intentoFallidos = 0;

    public Frm_PinSeguridad() {
        this.setUndecorated(true);

        initComponents();

        this.setLocationRelativeTo(null);

        BTN_Ingresar.setEnabled(false);

        TXTPASS.requestFocus();

        JButton[] botones = {
            btn1,
            btn2,
            btn3,
            btn4,
            btn5,
            btn6,
            btn7,
            btn8,
            btn9,
            btn0,
            BTN_Ingresar,
            BTN_Salir
        };

        for (JButton b : botones) {

            b.setBackground(Color.WHITE);

            b.setOpaque(true);

            b.setBorderPainted(false);

            b.setContentAreaFilled(true);
        }

        TXTPASS.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {

            public void changedUpdate(javax.swing.event.DocumentEvent e) {

                validarLongitud();

            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {

                validarLongitud();

            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {

                validarLongitud();

            }

        }
        );
    }

    private void validarLongitud() {

        String pass = new String(TXTPASS.getPassword());

        if (pass.length() >= 4) {

            BTN_Ingresar.setEnabled(true);

        } else {

            BTN_Ingresar.setEnabled(false);

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TXTPASS = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        BTN_Ingresar = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        BTN_Salir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SEGURIDAD");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bienvenido al sistema Recursos Humanos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 260, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Ingrese pin de seguridad !.");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        TXTPASS.setEditable(false);
        TXTPASS.setBackground(new java.awt.Color(255, 255, 255));
        TXTPASS.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        TXTPASS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXTPASS.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TXTPASS.addActionListener(this::TXTPASSActionPerformed);
        TXTPASS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTPASSKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTPASSKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPASSKeyTyped(evt);
            }
        });
        getContentPane().add(TXTPASS, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 290, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Olvide el pin ?.");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        jRadioButton1.addActionListener(this::jRadioButton1ActionPerformed);
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        btn1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn1.setText("1");
        btn1.addActionListener(this::btn1ActionPerformed);
        getContentPane().add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 110, 60));

        btn2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn2.setText("2");
        btn2.addActionListener(this::btn2ActionPerformed);
        getContentPane().add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 110, 60));

        btn3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn3.setText("3");
        btn3.addActionListener(this::btn3ActionPerformed);
        getContentPane().add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 110, 60));

        btn4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn4.setText("4");
        btn4.addActionListener(this::btn4ActionPerformed);
        getContentPane().add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 110, 60));

        btn5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn5.setText("5");
        btn5.addActionListener(this::btn5ActionPerformed);
        getContentPane().add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 110, 60));

        btn6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn6.setText("6");
        btn6.addActionListener(this::btn6ActionPerformed);
        getContentPane().add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 110, 60));

        btn7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn7.setText("7");
        btn7.addActionListener(this::btn7ActionPerformed);
        getContentPane().add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 110, 60));

        btn8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn8.setText("8");
        btn8.addActionListener(this::btn8ActionPerformed);
        getContentPane().add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 110, 60));

        btn9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn9.setText("9");
        btn9.addActionListener(this::btn9ActionPerformed);
        getContentPane().add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 110, 60));

        BTN_Ingresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BTN_Ingresar.setText("Ingresar");
        BTN_Ingresar.addActionListener(this::BTN_IngresarActionPerformed);
        getContentPane().add(BTN_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 110, 60));

        btn0.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn0.setText("0");
        btn0.addActionListener(this::btn0ActionPerformed);
        getContentPane().add(btn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 110, 60));

        BTN_Salir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BTN_Salir.setText("Salir");
        BTN_Salir.addActionListener(this::BTN_SalirActionPerformed);
        getContentPane().add(BTN_Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, 110, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 370, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTPASSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTPASSActionPerformed

    }//GEN-LAST:event_TXTPASSActionPerformed

    private void TXTPASSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPASSKeyPressed

    }//GEN-LAST:event_TXTPASSKeyPressed

    private void TXTPASSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPASSKeyReleased

    }//GEN-LAST:event_TXTPASSKeyReleased

    private void TXTPASSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPASSKeyTyped

    }//GEN-LAST:event_TXTPASSKeyTyped

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            /* Mostrar Contrasseña */
            TXTPASS.setEchoChar((char) 0);
        } else {
            /* Ocultar la contraseña */
            TXTPASS.setEchoChar('*');
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void BTN_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_IngresarActionPerformed
        String pass = new String(TXTPASS.getPassword());

        String pinCorrecto = "123456";

        if (pass.equals(pinCorrecto)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido al sistema Recursos Humanos.\nAcceso concedido correctamente.",
                    "Acceso permitido",
                    JOptionPane.INFORMATION_MESSAGE
            );

            Frm_LoginAcceso menu = new Frm_LoginAcceso();

            menu.setVisible(true);

            this.dispose();

        } else {

            intentoFallidos++;

            int restantes = 3 - intentoFallidos;

            if (restantes > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "PIN incorrecto.\nLe quedan " + restantes + " intento(s).",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE
                );

                TXTPASS.setText("");

                BTN_Ingresar.setEnabled(false);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Ha superado el número máximo de intentos permitidos.\n"
                        + "El sistema academico se cerrara por seguridad.",
                        "Acceso denegado",
                        JOptionPane.ERROR_MESSAGE
                );

                System.exit(0);
            }
        }
    }//GEN-LAST:event_BTN_IngresarActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        TXTPASS.setText(TXTPASS.getText() + "0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void BTN_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SalirActionPerformed
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
    }//GEN-LAST:event_BTN_SalirActionPerformed

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
           
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Frm_PinSeguridad().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Ingresar;
    private javax.swing.JButton BTN_Salir;
    private javax.swing.JPasswordField TXTPASS;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    // End of variables declaration//GEN-END:variables

}
