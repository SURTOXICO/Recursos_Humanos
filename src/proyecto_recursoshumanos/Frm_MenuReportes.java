package proyecto_recursoshumanos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

public class Frm_MenuReportes extends javax.swing.JFrame {

    // ====== PALETA DE COLORES ======
    private static final Color FONDO = new Color(238, 242, 247);
    private static final Color HEADER_1 = new Color(15, 40, 100);
    private static final Color HEADER_2 = new Color(30, 75, 160);
    private static final Color TEXTO_SUAVE = new Color(200, 215, 240);

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_MenuReportes.class.getName());

    public Frm_MenuReportes() {
        this.setUndecorated(true);
        initComponents();
        this.setSize(750, 520);
        initCustomCardDesign();
        this.setLocationRelativeTo(null);
        // Borde fino alrededor de la ventana sin decoración
        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createLineBorder(new Color(15, 40, 100), 1));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanelEncabezado = new javax.swing.JPanel();
        jpanelMenuLateral = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanelEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpanelEncabezado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jpanelEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 90));

        jpanelMenuLateral.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jpanelMenuLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 200, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

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

        java.awt.EventQueue.invokeLater(() -> new Frm_MenuReportes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpanelEncabezado;
    private javax.swing.JPanel jpanelMenuLateral;
    // End of variables declaration//GEN-END:variables
private void initCustomCardDesign() {
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().setBackground(FONDO);

        // ====== ENCABEZADO CON DEGRADADO ======
        JPanel panelHeader = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, HEADER_1, getWidth(), 0, HEADER_2));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        panelHeader.setOpaque(false);

        // Título + subtítulo
        JPanel panelTextos = new JPanel(new GridLayout(2, 1));
        panelTextos.setOpaque(false);
        panelTextos.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 0));

        JLabel lblTitulo = new JLabel("Módulo de Reportes Académicos e Institucionales");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 17));

        JLabel lblSub = new JLabel("Seleccione el reporte que desea consultar");
        lblSub.setForeground(TEXTO_SUAVE);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        panelTextos.add(lblTitulo);
        panelTextos.add(lblSub);
        panelHeader.add(panelTextos, BorderLayout.CENTER);

        // Botón cerrar: transparente, rojo solo al pasar el mouse
        JButton btnCerrarVentana = new JButton(FontIcon.of(FontAwesomeSolid.TIMES, 16, Color.WHITE));
        btnCerrarVentana.setPreferredSize(new Dimension(55, 60));
        btnCerrarVentana.setContentAreaFilled(false);
        btnCerrarVentana.setOpaque(false);
        btnCerrarVentana.setBorderPainted(false);
        btnCerrarVentana.setFocusPainted(false);
        btnCerrarVentana.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCerrarVentana.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCerrarVentana.setContentAreaFilled(true);
                btnCerrarVentana.setBackground(new Color(220, 53, 69));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnCerrarVentana.setContentAreaFilled(false);
            }
        });
        btnCerrarVentana.addActionListener(e -> this.dispose());
        panelHeader.add(btnCerrarVentana, BorderLayout.EAST);

        getContentPane().add(panelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 60));

        // ====== TARJETAS ======
        // 1. Docentes - Azul marino
        JButton cardDocentes = crearCardGradiente("Listar Docentes", FontAwesomeSolid.CHALKBOARD_TEACHER,
                new Color(20, 50, 120), new Color(45, 110, 190));
        cardDocentes.addActionListener(e -> new Frm_Reporte_Docentes().setVisible(true));
        getContentPane().add(cardDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 290, 100));

        // 2. Cargas académicas - Teal
        JButton cardCarga = crearCardGradiente("Listar Cargas Académicas", FontAwesomeSolid.TASKS,
                new Color(13, 110, 110), new Color(32, 160, 140));
        cardCarga.addActionListener(e
                -> JOptionPane.showMessageDialog(this, "Abriendo Carga Académica..."));
        getContentPane().add(cardCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 290, 100));

        // 3. Planes de estudio - Índigo / violeta
        JButton cardPlanes = crearCardGradiente("Planes de Estudio", FontAwesomeSolid.BOOK_OPEN,
                new Color(65, 40, 130), new Color(110, 80, 190));
        cardPlanes.addActionListener(e -> new Frm_PlanEstudios().setVisible(true));
        getContentPane().add(cardPlanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 215, 290, 100));

        // 4. Pagos - Ámbar / cobre
        JButton cardPagos = crearCardGradiente("Reporte de Pagos", FontAwesomeSolid.FILE_INVOICE_DOLLAR,
                new Color(190, 90, 20), new Color(235, 150, 45));
        cardPagos.addActionListener(e -> new Frm_Reporte_Clientes().setVisible(true));
        getContentPane().add(cardPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 215, 290, 100));

        // 5. Horarios - Azul cielo (centrada)
        JButton cardHorario = crearCardGradiente("Reporte de Horarios", FontAwesomeSolid.CALENDAR_ALT,
                new Color(30, 120, 190), new Color(70, 170, 225));
        cardHorario.addActionListener(e
                -> JOptionPane.showMessageDialog(this, "Abriendo Reporte de Horarios..."));
        getContentPane().add(cardHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 290, 100));

        // ====== BOTÓN INFERIOR ======
        JButton btnSalir = crearBotonSecundario("Regresar / Cerrar");
        btnSalir.addActionListener(e -> this.dispose());
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 462, 200, 40));
    }

    // ====== TARJETA CON DEGRADADO, SOMBRA Y HOVER ======
    private JButton crearCardGradiente(String texto, FontAwesomeSolid iconoEnum, Color colorInicio, Color colorFin) {
        JButton boton = new JButton("<html><center><b>" + texto + "</b></center></html>") {
            private boolean hover = false;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hover = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                int arc = 22;

                // Sombra suave (3 capas translúcidas)
                for (int i = 0; i < 4; i++) {
                    g2.setColor(new Color(0, 0, 0, 14 - i * 3));
                    g2.fillRoundRect(i, i + 3, w - 2 * i, h - 4 - i, arc, arc);
                }

                // Cuerpo con degradado (más claro en hover)
                Color c1 = hover ? colorInicio.brighter() : colorInicio;
                Color c2 = hover ? colorFin.brighter() : colorFin;
                g2.setPaint(new GradientPaint(0, 0, c1, w, h, c2));
                g2.fillRoundRect(0, 0, w - 4, h - 6, arc, arc);

                // Brillo sutil en la mitad superior
                g2.setPaint(new GradientPaint(0, 0, new Color(255, 255, 255, 40),
                        0, (h - 6) / 2, new Color(255, 255, 255, 0)));
                g2.fillRoundRect(0, 0, w - 4, (h - 6) / 2, arc, arc);

                g2.dispose();
                super.paintComponent(g);
            }
        };

        boton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setOpaque(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 15, 16, 19));

        boton.setIcon(FontIcon.of(iconoEnum, 34, Color.WHITE));
        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        boton.setIconTextGap(16);

        return boton;
    }

    // ====== BOTÓN SECUNDARIO PLANO ======
    private JButton crearBotonSecundario(String texto) {
        JButton boton = new JButton(texto) {
            private boolean hover = false;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hover = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(hover ? new Color(70, 80, 95) : new Color(95, 105, 120));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        boton.setIcon(FontIcon.of(FontAwesomeSolid.ARROW_LEFT, 14, Color.WHITE));
        boton.setIconTextGap(10);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setOpaque(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return boton;
    }

}
