package proyecto_recursoshumanos;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

public class Frm_Menu_Principal extends javax.swing.JFrame {

    // =====================================================================
    // 1. VARIABLES DE LA CLASE
    // =====================================================================
    // 1.1 Sidebar
    private static final int ANCHO_SIDEBAR = 210;
    // Ruta del logo dentro del paquete "img" (si no existe, se muestra un logo de texto)
    private static final String RUTA_LOGO = "/img/logo_senati.png";
    private final List<ItemMenu> itemsMenu = new ArrayList<>();
    private JLabel iconoPie, lblLemaPie;   // bloque "Personas que impulsan el futuro"

    // 1.2 Header
    private JLabel lblFecha, lblHora, lblUsuario, lblRol;
    private javax.swing.Timer relojTimer;
    private Point mouseInicio, ventanaInicio;

    // 1.3 Contenido
    private JPanel panelContenido;
    private JLabel lblBienvenida;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Menu_Principal.class.getName());

    // =====================================================================
    // 2. CONSTRUCTOR
    // =====================================================================
    // Arma la ventana en orden: layout, sidebar, header y contenido
    public Frm_Menu_Principal() {
        setUndecorated(true); // Oculta la barra de título por completo
        initComponents();
        configurarLayout();         // 5.
        armarSidebar();             // 6.
        armarHeader();              // 7.
        armarContenido();           // 8.
        setLocationRelativeTo(null);
    }

    // =====================================================================
    // 3. CÓDIGO GENERADO POR NETBEANS (no editar)
    // =====================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSidebar_Izquierda = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelSidebar_Izquierda.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelSidebar_IzquierdaLayout = new javax.swing.GroupLayout(jPanelSidebar_Izquierda);
        jPanelSidebar_Izquierda.setLayout(jPanelSidebar_IzquierdaLayout);
        jPanelSidebar_IzquierdaLayout.setHorizontalGroup(
            jPanelSidebar_IzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelSidebar_IzquierdaLayout.setVerticalGroup(
            jPanelSidebar_IzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1396, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelSidebar_Izquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 1400));

        jPanelHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1496, Short.MAX_VALUE)
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 1500, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.awt.EventQueue.invokeLater(() -> new Frm_Menu_Principal().setVisible(true));
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelSidebar_Izquierda;
    // End of variables declaration//GEN-END:variables

// =====================================================================
    // 5. LAYOUT GENERAL DE LA VENTANA
    // =====================================================================
    // 5.1 Reemplaza el diseño del NetBeans por BorderLayout:
    //     sidebar a la izquierda, y a la derecha header arriba + contenido
    private void configurarLayout() {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(238, 243, 251));

        // Sidebar con degradado azul
        jPanelSidebar_Izquierda = new JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();
                g2.setPaint(new GradientPaint(0, 0, new Color(8, 26, 64),
                        0, h, new Color(10, 36, 90)));
                g2.fillRect(0, 0, w, h);

                // Ondas azules translúcidas en la parte baja (como en el mockup)
                java.awt.geom.Path2D onda1 = new java.awt.geom.Path2D.Float();
                onda1.moveTo(0, h - 150);
                onda1.curveTo(w * 0.35, h - 190, w * 0.70, h - 60, w, h - 110);
                onda1.lineTo(w, h);
                onda1.lineTo(0, h);
                onda1.closePath();
                g2.setColor(new Color(37, 99, 235, 45));
                g2.fill(onda1);

                java.awt.geom.Path2D onda2 = new java.awt.geom.Path2D.Float();
                onda2.moveTo(0, h - 70);
                onda2.curveTo(w * 0.40, h - 120, w * 0.65, h - 20, w, h - 50);
                onda2.lineTo(w, h);
                onda2.lineTo(0, h);
                onda2.closePath();
                g2.setColor(new Color(37, 99, 235, 70));
                g2.fill(onda2);
                g2.dispose();
            }

            @Override
            public void doLayout() {
                super.doLayout();
                ubicarInferiores();      // El lema "Personas que impulsan el futuro" siempre al fondo
            }
        };
        jPanelSidebar_Izquierda.setBorder(null);
        jPanelSidebar_Izquierda.setPreferredSize(new Dimension(ANCHO_SIDEBAR, 0));

        // Header
        jPanelHeader.setBorder(null);
        jPanelHeader.setBackground(new Color(10, 44, 120));
        jPanelHeader.setPreferredSize(new Dimension(0, 78));

        // Zona de contenido (aquí va el dashboard)
        panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBackground(new Color(238, 243, 251));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        JPanel derecha = new JPanel(new BorderLayout());
        derecha.add(jPanelHeader, BorderLayout.NORTH);
        derecha.add(panelContenido, BorderLayout.CENTER);

        getContentPane().add(jPanelSidebar_Izquierda, BorderLayout.WEST);
        getContentPane().add(derecha, BorderLayout.CENTER);

        // Opcional: evita que la ventana tape la barra de tareas
        //setMaximizedBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        setExtendedState(JFrame.MAXIMIZED_BOTH);   // ocupa toda la pantalla
    }
// =====================================================================
    // 6. SIDEBAR (MENÚ LATERAL IZQUIERDO)
    // =====================================================================
    // 6.1 Crea el logo, la línea separadora, los ítems del menú y el lema inferior

    private void armarSidebar() {
        JPanel p = jPanelSidebar_Izquierda;
        p.removeAll();
        itemsMenu.clear();
        int anchoItem = ANCHO_SIDEBAR - 24;

        // ---- Logo SENATI ----
        java.net.URL urlLogo = getClass().getResource(RUTA_LOGO);
        if (urlLogo != null) {
            // Logo real: se escala al ancho disponible manteniendo proporción
            ImageIcon original = new ImageIcon(urlLogo);
            int anchoLogo = anchoItem - 20;
            int altoLogo = original.getIconHeight() * anchoLogo / original.getIconWidth();
            Image escalada = original.getImage().getScaledInstance(anchoLogo, altoLogo, Image.SCALE_SMOOTH);
            p.add(new JLabel(new ImageIcon(escalada)),
                    new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 18, anchoLogo, altoLogo));
        } else {
            // Respaldo mientras no pongas la imagen: ícono + texto "SENATI"
            JLabel logo = new JLabel(FontIcon.of(FontAwesomeSolid.COGS, 34, Color.WHITE));
            p.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 16, 42, 42));

            JLabel titulo = new JLabel("SENATI");
            titulo.setForeground(Color.WHITE);
            titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
            p.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 20, 130, 34));
        }

        // ---- Línea separadora ----
        JPanel linea = new JPanel();
        linea.setBackground(new Color(40, 75, 150));
        p.add(linea, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 76, anchoItem, 1));

        // ---- Ítems del menú (texto, ícono) ----
        Object[][] datos = {
            {"Inicio", FontAwesomeSolid.HOME},
            {"Personal", FontAwesomeSolid.USER},
            {"Empleados", FontAwesomeSolid.USERS},
            {"Departamentos", FontAwesomeSolid.BUILDING},
            {"Puestos", FontAwesomeSolid.CLIPBOARD_LIST},
            {"Asistencias", FontAwesomeSolid.CLOCK},
            {"Planillas", FontAwesomeSolid.FILE_INVOICE_DOLLAR},
            {"Capacitaciones", FontAwesomeSolid.GRADUATION_CAP},
            {"Evaluaciones", FontAwesomeSolid.CLIPBOARD_CHECK},
            {"Reportes", FontAwesomeSolid.FILE_ALT},
            {"Configuración", FontAwesomeSolid.COG}
        };

        int y = 90;
        for (Object[] d : datos) {
            final String texto = (String) d[0];
            final ItemMenu it = new ItemMenu(texto, (FontAwesomeSolid) d[1]);
            it.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionar(it);
                    abrirModulo(texto);
                }
            });
            itemsMenu.add(it);
            p.add(it, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, y, anchoItem, 40));
            y += 44;
        }
        itemsMenu.get(0).setSeleccionado(true);   // "Inicio" activo por defecto

        // ---- Ítem "Cerrar sesión" (va debajo de Configuración) ----
        // No se agrega a itemsMenu: así no queda marcado como "seleccionado"
        // y si el usuario elige "No", el módulo activo sigue resaltado.
        ItemMenu itemCerrar = new ItemMenu("Cerrar sesión", FontAwesomeSolid.SIGN_OUT_ALT);
        itemCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarSesion();
            }
        });
        p.add(itemCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, y, anchoItem, 40));

        // ---- Parte inferior: ícono de personas + lema ----
        iconoPie = new JLabel(FontIcon.of(FontAwesomeSolid.USERS, 42, new Color(59, 130, 246)),
                SwingConstants.CENTER);
        lblLemaPie = new JLabel("Personas que impulsan el futuro", SwingConstants.CENTER);
        lblLemaPie.setForeground(Color.WHITE);
        lblLemaPie.setFont(new Font("Segoe UI", Font.ITALIC, 12));

        p.add(iconoPie, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 600, anchoItem, 50));
        p.add(lblLemaPie, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 654, anchoItem, 20));

        p.revalidate();
        p.repaint();
    }

    // 6.2 Mantiene el lema inferior pegado al borde de abajo
    private void ubicarInferiores() {
        if (iconoPie == null || lblLemaPie == null) {
            return;
        }
        int h = jPanelSidebar_Izquierda.getHeight();
        int ancho = ANCHO_SIDEBAR - 24;
        iconoPie.setBounds(12, h - 100, ancho, 50);
        lblLemaPie.setBounds(12, h - 46, ancho, 20);
    }

    // 6.3 Marca como activo el ítem elegido y desmarca los demás
    private void seleccionar(ItemMenu elegido) {
        for (ItemMenu it : itemsMenu) {
            it.setSeleccionado(it == elegido);
        }
    }

    // 6.4 Abre el formulario que corresponde al módulo (agrega aquí los demás cuando existan)
    private void abrirModulo(String nombre) {
        switch (nombre) {
            case "Inicio":
                break;                                   // ya estás en el inicio
            case "Empleados":
                new frm_Mantempleado().setVisible(true);
                break;
            case "Departamentos":
                new frm_departamento_academico().setVisible(true);
                break;
            case "Asistencias":
                new frm_Mantasistencia().setVisible(true);
                break;
            case "Capacitaciones":
                new frm_capacitacion().setVisible(true);
                break;
            default:
                // Personal, Puestos, Planillas, Evaluaciones, Reportes, Configuración
                JOptionPane.showMessageDialog(this, "El módulo \"" + nombre + "\" aún está en construcción.",
                        "Próximamente", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    // 6.5 Pide confirmación y cierra la sesión
    //     (la llama el ítem "Cerrar sesión" del sidebar)
    private void cerrarSesion() {
        int r = JOptionPane.showOptionDialog(this, "¿Deseas cerrar sesión?", "Cerrar sesión",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Sí", "No"}, "Sí");
        if (r != 0) {
            return;                                      // eligió "No" o cerró el cuadro
        }
        if (relojTimer != null) {
            relojTimer.stop();                           // detiene el reloj del header
        }
        try {
            // 1) Primero se abre el login (si falla, este menú sigue abierto y se muestra el error)
            Frm_LoginAcceso login = new Frm_LoginAcceso();
            login.setVisible(true);

            // 2) Luego se cierran este menú y los formularios abiertos (Empleados, Asistencias, etc.)
            for (Window w : Window.getWindows()) {
                if (w != login) {
                    w.dispose();
                }
            }
        } catch (Exception | Error ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error al abrir Frm_LoginAcceso", ex);
            if (relojTimer != null) {
                relojTimer.start();                      // el menú sigue abierto: reactiva el reloj
            }
            JOptionPane.showMessageDialog(this, "No se pudo abrir el login:\n" + ex,
                    "Error al cerrar sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 6.6 Clase: ítem del menú (ícono + texto, con hover y estado seleccionado)
    private class ItemMenu extends JComponent {

        private final String texto;
        private final Icon icono;
        private boolean seleccionado = false;
        private boolean hover = false;

        ItemMenu(String texto, FontAwesomeSolid ico) {
            this.texto = texto;
            this.icono = FontIcon.of(ico, 18, Color.WHITE);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
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

        void setSeleccionado(boolean s) {
            seleccionado = s;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Fondo
            if (seleccionado) {
                g2.setColor(new Color(37, 99, 235));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            } else if (hover) {
                g2.setColor(new Color(255, 255, 255, 28));   // blanco translúcido
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            }

            // Ícono en posición fija (todos alineados)
            int iy = (getHeight() - icono.getIconHeight()) / 2;
            icono.paintIcon(this, g2, 18, iy);

            // Texto en posición fija
            g2.setFont(new Font("Segoe UI", seleccionado ? Font.BOLD : Font.PLAIN, 12));
            g2.setColor(Color.WHITE);
            FontMetrics fm = g2.getFontMetrics();
            int ty = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(texto, 54, ty);

            g2.dispose();
        }
    }

    // =====================================================================
    // 7. HEADER (BARRA SUPERIOR)
    // =====================================================================
    // 7.1 Arma el título a la izquierda, y a la derecha fecha/hora, usuario y botones de ventana
    private void armarHeader() {
        JPanel h = jPanelHeader;
        h.removeAll();
        h.setLayout(new BorderLayout());
        h.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 6));
        h.setBackground(new Color(10, 44, 120));

        // ---------- IZQUIERDA: título ----------
        JPanel izq = new JPanel(new GridBagLayout());
        izq.setOpaque(false);

        JLabel titulo = new JLabel("Sistema de Recursos Humanos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel subtitulo = new JLabel("Gestiona el talento de tu organización de manera eficiente");
        subtitulo.setForeground(new Color(200, 215, 240));
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(titulo);
        textos.add(subtitulo);

        izq.add(textos);
        h.add(izq, BorderLayout.WEST);

        // ---------- DERECHA: fecha, usuario y botones ----------
        JPanel der = new JPanel(new GridBagLayout());
        der.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        // Fecha y hora
        JLabel icoCal = new JLabel(FontIcon.of(FontAwesomeSolid.CALENDAR_ALT, 24, Color.WHITE));
        lblFecha = new JLabel();
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblHora = new JLabel();
        lblHora.setForeground(new Color(200, 215, 240));
        lblHora.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JPanel fechaHora = new JPanel();
        fechaHora.setOpaque(false);
        fechaHora.setLayout(new BoxLayout(fechaHora, BoxLayout.Y_AXIS));
        fechaHora.add(lblFecha);
        fechaHora.add(lblHora);

        // Usuario
        JLabel avatar = new JLabel(FontIcon.of(FontAwesomeSolid.USER_CIRCLE, 38, Color.WHITE));
        lblUsuario = new JLabel("admin");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblRol = new JLabel("Administrador");
        lblRol.setForeground(new Color(200, 215, 240));
        lblRol.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JPanel datosUsuario = new JPanel();
        datosUsuario.setOpaque(false);
        datosUsuario.setLayout(new BoxLayout(datosUsuario, BoxLayout.Y_AXIS));
        datosUsuario.add(lblUsuario);
        datosUsuario.add(lblRol);

        // Botones de ventana
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        botones.setOpaque(false);
        botones.add(new BotonVentana(FontAwesomeSolid.MINUS, new Color(255, 255, 255, 40),
                () -> setExtendedState(JFrame.ICONIFIED)));
        botones.add(new BotonVentana(FontAwesomeSolid.WINDOW_MAXIMIZE, new Color(255, 255, 255, 40),
                this::alternarMaximizado));
        botones.add(new BotonVentana(FontAwesomeSolid.TIMES, new Color(220, 38, 38),
                () -> System.exit(0)));

        // Ensamblado con GridBagLayout
        c.gridx = 0;
        c.insets = new Insets(0, 0, 0, 10);
        der.add(icoCal, c);
        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 22);
        der.add(fechaHora, c);
        c.gridx = 2;
        c.insets = new Insets(0, 0, 0, 22);
        der.add(crearSeparadorVertical(), c);
        c.gridx = 3;
        c.insets = new Insets(0, 0, 0, 10);
        der.add(avatar, c);
        c.gridx = 4;
        c.insets = new Insets(0, 0, 0, 30);
        der.add(datosUsuario, c);
        c.gridx = 5;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.NORTHEAST;          // pegado arriba a la derecha
        der.add(botones, c);

        h.add(der, BorderLayout.EAST);

        habilitarArrastre(h);
        iniciarReloj();

        h.revalidate();
        h.repaint();
    }

    // 7.2 Línea vertical fina que separa la fecha del usuario
    private JComponent crearSeparadorVertical() {
        JPanel sep = new JPanel();
        sep.setBackground(new Color(70, 110, 190));
        sep.setPreferredSize(new Dimension(1, 38));
        return sep;
    }

    // 7.3 Reloj en vivo: actualiza fecha y hora cada segundo
    private void iniciarReloj() {
        final java.time.format.DateTimeFormatter fFecha
                = java.time.format.DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy",
                        new java.util.Locale("es", "PE"));
        final java.time.format.DateTimeFormatter fHora
                = java.time.format.DateTimeFormatter.ofPattern("hh:mm:ss a",
                        new java.util.Locale("es", "PE"));

        Runnable actualizar = () -> {
            java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
            lblFecha.setText(ahora.format(fFecha));
            lblHora.setText(ahora.format(fHora));
        };
        actualizar.run();
        relojTimer = new javax.swing.Timer(1000, e -> actualizar.run());
        relojTimer.start();
    }

    // 7.4 Muestra el usuario que inició sesión (se llama desde el login)
    public void setUsuarioActual(String nombre, String rol) {
        lblUsuario.setText(nombre);
        lblRol.setText(rol);
        if (lblBienvenida != null) {
            lblBienvenida.setText("¡Bienvenido, " + nombre + "!");
        }
    }

    // 7.5 Alterna entre ventana maximizada y tamaño normal
    private void alternarMaximizado() {
        if ((getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
            setExtendedState(JFrame.NORMAL);
            setSize(1200, 700);
            setLocationRelativeTo(null);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }

    // 7.6 Permite mover la ventana arrastrando el header (doble clic = maximizar)
    private void habilitarArrastre(JComponent zona) {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseInicio = e.getLocationOnScreen();
                ventanaInicio = getLocation();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if ((getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    return;
                }
                Point p = e.getLocationOnScreen();
                setLocation(ventanaInicio.x + p.x - mouseInicio.x,
                        ventanaInicio.y + p.y - mouseInicio.y);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    alternarMaximizado();
                }
            }
        };
        zona.addMouseListener(ma);
        zona.addMouseMotionListener(ma);
    }

    // 7.7 Clase: botón de ventana (minimizar / maximizar / cerrar)
    private class BotonVentana extends JComponent {

        private final Icon icono;
        private final Color colorHover;
        private boolean hover = false;

        BotonVentana(FontAwesomeSolid ico, Color colorHover, Runnable accion) {
            this.icono = FontIcon.of(ico, 12, Color.WHITE);
            this.colorHover = colorHover;
            setPreferredSize(new Dimension(42, 30));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
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

                @Override
                public void mouseClicked(MouseEvent e) {
                    accion.run();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            if (hover) {
                g2.setColor(colorHover);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
            icono.paintIcon(this, g2,
                    (getWidth() - icono.getIconWidth()) / 2,
                    (getHeight() - icono.getIconHeight()) / 2);
            g2.dispose();
        }
    }

    // =====================================================================
    // 8. CONTENIDO DEL DASHBOARD
    // =====================================================================
    // 8.1 Acomoda las piezas en 2 columnas: bienvenida + estadísticas (izq) y accesos rápidos (der)
    private void armarContenido() {
        panelContenido.removeAll();
        panelContenido.setLayout(new BorderLayout(14, 0));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Columna principal: bienvenida, indicadores, gráficos y tablas.
        JPanel columnaPrincipal = new JPanel();
        columnaPrincipal.setOpaque(false);
        columnaPrincipal.setLayout(new BoxLayout(columnaPrincipal, BoxLayout.Y_AXIS));

        JPanel bienvenida = crearTarjetaBienvenida();
        bienvenida.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        bienvenida.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnaPrincipal.add(bienvenida);
        columnaPrincipal.add(Box.createVerticalStrut(12));

        JPanel estadisticas = crearFilaEstadisticas();
        estadisticas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 124));
        estadisticas.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnaPrincipal.add(estadisticas);
        columnaPrincipal.add(Box.createVerticalStrut(12));

        JPanel graficos = new JPanel(new GridLayout(1, 2, 12, 0));
        graficos.setOpaque(false);
        graficos.add(crearGraficoDepartamentos());
        graficos.add(crearGraficoPuestos());
        graficos.setPreferredSize(new Dimension(900, 260));
        graficos.setMinimumSize(new Dimension(500, 260));
        graficos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));
        graficos.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnaPrincipal.add(graficos);
        columnaPrincipal.add(Box.createVerticalStrut(12));

        JPanel empleados = crearTablaEmpleados();
        empleados.setPreferredSize(new Dimension(900, 220));
        empleados.setMinimumSize(new Dimension(500, 220));
        empleados.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));
        empleados.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnaPrincipal.add(empleados);
        columnaPrincipal.add(Box.createVerticalStrut(12));

        JPanel capacitaciones = crearTablaCapacitaciones();
        capacitaciones.setPreferredSize(new Dimension(900, 170));
        capacitaciones.setMinimumSize(new Dimension(500, 170));
        capacitaciones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
        capacitaciones.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnaPrincipal.add(capacitaciones);
        columnaPrincipal.add(Box.createVerticalStrut(10));

        JScrollPane scrollPrincipal = new JScrollPane(columnaPrincipal);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(18);
        scrollPrincipal.getViewport().setBackground(new Color(238, 243, 251));

        // Columna derecha: accesos, movimientos y alertas. Cada sección usa el alto disponible.
        JPanel columnaDerecha = new JPanel();
        columnaDerecha.setOpaque(false);
        columnaDerecha.setLayout(new BoxLayout(columnaDerecha, BoxLayout.Y_AXIS));

        JPanel accesos = crearPanelAccesos();
        accesos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 270));
        accesos.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnaDerecha.add(accesos);
        columnaDerecha.add(Box.createVerticalStrut(12));

        JPanel movimientos = crearPanelMovimientos();
        movimientos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));
        movimientos.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnaDerecha.add(movimientos);
        columnaDerecha.add(Box.createVerticalStrut(12));

        JPanel alertas = crearPanelAlertas();
        alertas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        alertas.setAlignmentX(Component.LEFT_ALIGNMENT);
        columnaDerecha.add(alertas);
        columnaDerecha.add(Box.createVerticalGlue());

        JScrollPane scrollDerecha = new JScrollPane(columnaDerecha);
        scrollDerecha.setBorder(null);
        scrollDerecha.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDerecha.getVerticalScrollBar().setUnitIncrement(18);
        scrollDerecha.setPreferredSize(new Dimension(310, 600));
        scrollDerecha.getViewport().setBackground(new Color(238, 243, 251));

        panelContenido.add(scrollPrincipal, BorderLayout.CENTER);
        panelContenido.add(scrollDerecha, BorderLayout.EAST);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    // ---------------------------------------------------------------------
    // 8.2 TARJETA DE BIENVENIDA
    // ---------------------------------------------------------------------
    // 8.2.1 Crea la tarjeta: círculo con birrete + saludo + texto descriptivo
    private JPanel crearTarjetaBienvenida() {
        JPanel tarjeta = new TarjetaBienvenida();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBorder(BorderFactory.createEmptyBorder(14, 24, 14, 24));
        tarjeta.setPreferredSize(new Dimension(0, 120));

        // Ícono circular con birrete
        JPanel izq = new JPanel(new GridBagLayout());
        izq.setOpaque(false);
        izq.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        izq.add(new IconoCircular(FontAwesomeSolid.GRADUATION_CAP, 72, new Color(21, 82, 190)));
        tarjeta.add(izq, BorderLayout.WEST);

        // Textos
        lblBienvenida = new JLabel("¡Bienvenido, admin!");
        lblBienvenida.setForeground(new Color(10, 44, 120));
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JLabel linea1 = new JLabel("Gestiona la información académica de nuestra universidad");
        JLabel linea2 = new JLabel("de manera eficiente y segura.");
        for (JLabel l : new JLabel[]{linea1, linea2}) {
            l.setForeground(new Color(70, 85, 115));
            l.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(lblBienvenida);
        textos.add(Box.createVerticalStrut(6));
        textos.add(linea1);
        textos.add(linea2);

        JPanel centro = new JPanel(new GridBagLayout());   // centra los textos en vertical
        centro.setOpaque(false);
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 1;
        centro.add(textos, gc);
        tarjeta.add(centro, BorderLayout.CENTER);

        return tarjeta;
    }

    // 8.2.2 Clase: fondo blanco con degradado celeste a la derecha y borde redondeado
    private class TarjetaBienvenida extends JPanel {

        TarjetaBienvenida() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape forma = new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);

            g2.setColor(Color.WHITE);
            g2.fill(forma);

            g2.setClip(forma);
            g2.setPaint(new GradientPaint(getWidth() * 0.5f, 0, new Color(255, 255, 255, 0),
                    getWidth(), 0, new Color(205, 224, 250)));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setClip(null);

            g2.setColor(new Color(214, 224, 240));
            g2.draw(forma);
            g2.dispose();
        }
    }

    // ---------------------------------------------------------------------
    // 8.3 ACCESOS RÁPIDOS
    // ---------------------------------------------------------------------
    // 8.3.1 Crea el panel con los 4 botones de acceso rápido en cuadrícula 2x2
    private JPanel crearPanelAccesos() {
        PanelRedondeado panel = new PanelRedondeado();
        panel.setLayout(new BorderLayout(0, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        panel.setPreferredSize(new Dimension(310, 270));

        JLabel titulo = new JLabel("Accesos rápidos",
                FontIcon.of(FontAwesomeSolid.BOLT, 18, new Color(10, 44, 120)),
                SwingConstants.LEFT);
        titulo.setIconTextGap(8);
        titulo.setForeground(new Color(10, 44, 120));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel rejilla = new JPanel(new GridLayout(3, 2, 10, 10));
        rejilla.setOpaque(false);
        rejilla.add(new BotonAcceso("Registrar empleado", FontAwesomeSolid.USER_PLUS,
                new Color(37, 99, 235), () -> abrirModulo("Empleados")));
        rejilla.add(new BotonAcceso("Registrar asistencia", FontAwesomeSolid.CALENDAR_ALT,
                new Color(22, 163, 74), () -> abrirModulo("Asistencias")));
        rejilla.add(new BotonAcceso("Registrar planilla", FontAwesomeSolid.FILE_ALT,
                new Color(124, 58, 237), () -> abrirModulo("Planillas")));
        rejilla.add(new BotonAcceso("Registrar capacitación", FontAwesomeSolid.GRADUATION_CAP,
                new Color(249, 115, 22), () -> abrirModulo("Capacitaciones")));
        rejilla.add(new BotonAcceso("Generar reporte", FontAwesomeSolid.FILE_ALT,
                new Color(14, 165, 233), () -> abrirModulo("Reportes")));
        rejilla.add(new BotonAcceso("Ver todos", FontAwesomeSolid.ARROW_RIGHT,
                new Color(71, 85, 105), () -> abrirModulo("Empleados")));
        panel.add(rejilla, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelMovimientos() {
        PanelRedondeado panel = new PanelRedondeado();
        panel.setLayout(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        JLabel titulo = new JLabel("Movimientos recientes",
                FontIcon.of(FontAwesomeSolid.CLOCK, 17, new Color(10, 44, 120)),
                SwingConstants.LEFT);
        titulo.setIconTextGap(8);
        titulo.setForeground(new Color(10, 44, 120));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel lista = new JPanel();
        lista.setOpaque(false);
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
        lista.add(crearFilaInformativa(FontAwesomeSolid.USER_PLUS,
                new Color(22, 163, 74), "Ingreso de empleado",
                "Ana López Torres · Analista de Sistemas", "Hoy, 10:32"));
        lista.add(crearSeparadorHorizontal());
        lista.add(crearFilaInformativa(FontAwesomeSolid.CALENDAR_ALT,
                new Color(37, 99, 235), "Registro de asistencia",
                "Carlos Mendoza · Producción", "Hoy, 08:15"));
        lista.add(crearSeparadorHorizontal());
        lista.add(crearFilaInformativa(FontAwesomeSolid.GRADUATION_CAP,
                new Color(124, 58, 237), "Nueva capacitación",
                "Seguridad y Salud en el Trabajo", "Ayer, 16:45"));
        lista.add(crearSeparadorHorizontal());
        lista.add(crearFilaInformativa(FontAwesomeSolid.FILE_ALT,
                new Color(249, 115, 22), "Actualización de planilla",
                "Departamento de Producción", "Ayer, 15:20"));
        panel.add(lista, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelAlertas() {
        PanelRedondeado panel = new PanelRedondeado();
        panel.setLayout(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        JLabel titulo = new JLabel("Alertas y notificaciones",
                FontIcon.of(FontAwesomeSolid.BELL, 17, new Color(10, 44, 120)),
                SwingConstants.LEFT);
        titulo.setIconTextGap(8);
        titulo.setForeground(new Color(10, 44, 120));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel lista = new JPanel();
        lista.setOpaque(false);
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
        lista.add(crearFilaInformativa(FontAwesomeSolid.EXCLAMATION_TRIANGLE,
                new Color(220, 38, 38), "Cumpleaños hoy",
                "Revisar lista de cumpleaños", "Personal"));
        lista.add(crearSeparadorHorizontal());
        lista.add(crearFilaInformativa(FontAwesomeSolid.CLOCK,
                new Color(249, 115, 22), "Contrato por vencer",
                "Revisar contratos próximos a vencer", "Próximos 30 días"));
        lista.add(crearSeparadorHorizontal());
        lista.add(crearFilaInformativa(FontAwesomeSolid.CHECK_CIRCLE,
                new Color(22, 163, 74), "Capacitación próxima",
                "Liderazgo y Trabajo en Equipo", "Programada"));
        panel.add(lista, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearFilaInformativa(FontAwesomeSolid icono, Color color,
            String titulo, String detalle, String fecha) {
        JPanel fila = new JPanel(new BorderLayout(10, 0));
        fila.setOpaque(false);
        fila.setBorder(BorderFactory.createEmptyBorder(8, 2, 8, 2));
        JLabel ico = new JLabel(FontIcon.of(icono, 23, color));
        ico.setPreferredSize(new Dimension(30, 32));
        fila.add(ico, BorderLayout.WEST);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(new Color(10, 44, 120));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JLabel lblDetalle = new JLabel("<html><div style='width:190px'>"
                + detalle + "</div></html>");
        lblDetalle.setForeground(new Color(65, 80, 110));
        lblDetalle.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        JLabel lblFecha = new JLabel(fecha);
        lblFecha.setForeground(new Color(115, 130, 150));
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 10));

        textos.add(lblTitulo);
        textos.add(Box.createVerticalStrut(3));
        textos.add(lblDetalle);
        textos.add(Box.createVerticalStrut(2));
        textos.add(lblFecha);
        fila.add(textos, BorderLayout.CENTER);
        fila.setAlignmentX(Component.LEFT_ALIGNMENT);
        return fila;
    }

    private JComponent crearSeparadorHorizontal() {
        JPanel linea = new JPanel();
        linea.setBackground(new Color(225, 231, 241));
        linea.setPreferredSize(new Dimension(1, 1));
        linea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        return linea;
    }

    private JPanel crearGraficoDepartamentos() {
        PanelRedondeado panel = new PanelRedondeado();
        panel.setLayout(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
        JLabel titulo = new JLabel("Distribución por departamento",
                FontIcon.of(FontAwesomeSolid.CHART_PIE, 17, new Color(10, 44, 120)),
                SwingConstants.LEFT);
        titulo.setForeground(new Color(10, 44, 120));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout(10, 0));
        contenido.setOpaque(false);
        contenido.add(new GraficoDona(), BorderLayout.CENTER);

        JPanel leyenda = new JPanel();
        leyenda.setOpaque(false);
        leyenda.setLayout(new BoxLayout(leyenda, BoxLayout.Y_AXIS));
        String[] nombres = {"Administración", "Producción", "Ventas",
            "Sistemas", "Mantenimiento", "Otros"};
        Color[] colores = {new Color(37, 99, 235), new Color(22, 163, 74),
            new Color(249, 115, 22), new Color(124, 58, 237),
            new Color(14, 165, 233), new Color(148, 163, 184)};
        String[] valores = {"12", "9", "8", "6", "6", "7"};
        for (int i = 0; i < nombres.length; i++) {
            JPanel item = new JPanel(new BorderLayout(6, 0));
            item.setOpaque(false);
            JLabel nombre = new JLabel("●  " + nombres[i]);
            nombre.setForeground(colores[i]);
            nombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            JLabel valor = new JLabel(valores[i]);
            valor.setForeground(new Color(10, 44, 120));
            valor.setFont(new Font("Segoe UI", Font.BOLD, 11));
            item.add(nombre, BorderLayout.CENTER);
            item.add(valor, BorderLayout.EAST);
            item.setMaximumSize(new Dimension(180, 25));
            leyenda.add(item);
        }
        contenido.add(leyenda, BorderLayout.EAST);
        panel.add(contenido, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearGraficoPuestos() {
        PanelRedondeado panel = new PanelRedondeado();
        panel.setLayout(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
        JLabel titulo = new JLabel("Empleados por puesto",
                FontIcon.of(FontAwesomeSolid.CHART_BAR, 17, new Color(10, 44, 120)),
                SwingConstants.LEFT);
        titulo.setForeground(new Color(10, 44, 120));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(new GraficoBarras(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearTablaEmpleados() {
        String[] columnas = {"N°", "DNI", "Nombres y apellidos", "Puesto",
            "Departamento", "Ingreso", "Estado"};
        Object[][] datos = {
            {"1", "74256123", "Carlos Mendoza Rojas", "Técnico de Mantenimiento", "Mantenimiento", "15/04/2025", "Activo"},
            {"2", "71543210", "Ana López Torres", "Analista de Sistemas", "Sistemas", "14/04/2025", "Activo"},
            {"3", "73651987", "Luis Ramírez Díaz", "Operario de Producción", "Producción", "12/04/2025", "Activo"},
            {"4", "73485211", "Patricia Gómez Silva", "Asistente Administrativa", "Administración", "10/04/2025", "Activo"},
            {"5", "71236548", "Jorge Flores Quispe", "Supervisor de Planta", "Producción", "08/04/2025", "Activo"}
        };
        return crearPanelTabla("Últimos empleados registrados", columnas, datos);
    }

    private JPanel crearTablaCapacitaciones() {
        String[] columnas = {"N°", "Nombre de la capacitación", "Fecha",
            "Horario", "Lugar", "Participantes"};
        Object[][] datos = {
            {"1", "Seguridad y Salud en el Trabajo", "17/10/2026", "09:00 - 12:00", "Aula 1", "12"},
            {"2", "Liderazgo y Trabajo en Equipo", "22/10/2026", "14:00 - 17:00", "Aula 2", "15"},
            {"3", "Uso de Excel Avanzado", "25/10/2026", "09:00 - 12:00", "Laboratorio 3", "10"}
        };
        return crearPanelTabla("Próximas capacitaciones", columnas, datos);
    }

    private JPanel crearPanelTabla(String tituloTexto, String[] columnas, Object[][] datos) {
        PanelRedondeado panel = new PanelRedondeado();
        panel.setLayout(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

        JLabel titulo = new JLabel(tituloTexto + "  ›");
        titulo.setForeground(new Color(10, 44, 120));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(titulo, BorderLayout.NORTH);

        JTable tabla = new JTable(datos, columnas);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tabla.setRowHeight(25);
        tabla.setGridColor(new Color(225, 231, 241));
        tabla.setSelectionBackground(new Color(219, 234, 254));
        tabla.setSelectionForeground(new Color(10, 44, 120));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tabla.getTableHeader().setBackground(new Color(235, 241, 250));
        tabla.getTableHeader().setForeground(new Color(10, 44, 120));
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 231, 241)));
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private class GraficoDona extends JPanel {
        private final int[] valores = {12, 9, 8, 6, 6, 7};
        private final Color[] colores = {new Color(37, 99, 235), new Color(22, 163, 74),
            new Color(249, 115, 22), new Color(124, 58, 237),
            new Color(14, 165, 233), new Color(148, 163, 184)};

        GraficoDona() {
            setOpaque(false);
            setPreferredSize(new Dimension(190, 180));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int tam = Math.min(getHeight() - 20, getWidth() - 20);
            tam = Math.max(80, tam);
            int x = (getWidth() - tam) / 2;
            int y = (getHeight() - tam) / 2;
            int total = 0;
            for (int v : valores) total += v;
            int inicio = 90;
            for (int i = 0; i < valores.length; i++) {
                int angulo = (int) Math.round(valores[i] * 360.0 / total);
                g2.setColor(colores[i]);
                g2.fillArc(x, y, tam, tam, inicio, angulo);
                inicio += angulo;
            }
            int centro = tam / 2;
            g2.setColor(Color.WHITE);
            g2.fillOval(x + tam / 4, y + tam / 4, tam / 2, tam / 2);
            g2.setColor(new Color(10, 44, 120));
            g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
            String t = "Total";
            g2.drawString(t, x + centro - g2.getFontMetrics().stringWidth(t) / 2, y + centro - 2);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
            String n = "48";
            g2.drawString(n, x + centro - g2.getFontMetrics().stringWidth(n) / 2, y + centro + 17);
            g2.dispose();
        }
    }

    private class GraficoBarras extends JPanel {
        private final String[] etiquetas = {"Operario", "Técnico", "Analista", "Asistente", "Supervisor", "Otros"};
        private final int[] valores = {12, 10, 8, 6, 5, 7};

        GraficoBarras() {
            setOpaque(false);
            setPreferredSize(new Dimension(350, 180));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int left = 28, right = 8, top = 12, bottom = 32;
            int w = getWidth() - left - right;
            int h = getHeight() - top - bottom;
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 9));
            for (int tick = 0; tick <= 15; tick += 5) {
                int yy = top + h - (tick * h / 15);
                g2.setColor(new Color(222, 230, 242));
                g2.drawLine(left, yy, left + w, yy);
                g2.setColor(new Color(90, 105, 130));
                g2.drawString(String.valueOf(tick), 3, yy + 4);
            }
            int paso = w / etiquetas.length;
            for (int i = 0; i < valores.length; i++) {
                int bh = valores[i] * h / 15;
                int bx = left + i * paso + Math.max(2, paso / 8);
                int bw = Math.max(12, paso - Math.max(5, paso / 4));
                int by = top + h - bh;
                g2.setPaint(new GradientPaint(bx, by, new Color(59, 130, 246),
                        bx, by + bh, new Color(29, 78, 216)));
                g2.fillRoundRect(bx, by, bw, bh, 5, 5);
                g2.setColor(new Color(10, 44, 120));
                String v = String.valueOf(valores[i]);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                g2.drawString(v, bx + (bw - g2.getFontMetrics().stringWidth(v)) / 2, by - 4);
                g2.setFont(new Font("Segoe UI", Font.PLAIN, 9));
                String et = etiquetas[i];
                int tx = bx + (bw - g2.getFontMetrics().stringWidth(et)) / 2;
                g2.drawString(et, tx, top + h + 15);
            }
            g2.dispose();
        }
    }

    // ---------------------------------------------------------------------
    // 8.4 TARJETAS DE ESTADÍSTICAS
    // ---------------------------------------------------------------------
    // 8.4.1 Crea la fila con las 4 tarjetas (Docentes, Facultades, Escuelas, Cursos)
    private JPanel crearFilaEstadisticas() {
        JPanel fila = new JPanel(new GridLayout(1, 4, 12, 0));
        fila.setOpaque(false);
        fila.setPreferredSize(new Dimension(0, 124));

        fila.add(crearTarjetaEstadistica("Total Docentes", "48", "5%",
                "Docentes registrados en el sistema",
                FontAwesomeSolid.USERS, new Color(37, 99, 235)));
        fila.add(crearTarjetaEstadistica("Facultades", "6", "0%",
                "Total de facultades",
                FontAwesomeSolid.UNIVERSITY, new Color(22, 163, 74)));
        fila.add(crearTarjetaEstadistica("Escuelas Prof.", "12", "9%",
                "Total de escuelas profesionales",
                FontAwesomeSolid.GRADUATION_CAP, new Color(124, 58, 237)));
        fila.add(crearTarjetaEstadistica("Cursos", "86", "6%",
                "Total de cursos registrados",
                FontAwesomeSolid.BOOK_OPEN, new Color(249, 115, 22)));
        return fila;
    }

    // 8.4.2 Crea UNA tarjeta: ícono de color, título, número grande, porcentaje y descripción
    private JPanel crearTarjetaEstadistica(String titulo, String valor, String porcentaje,
            String descripcion, FontAwesomeSolid ico, Color color) {
        PanelRedondeado tarjeta = new PanelRedondeado();
        tarjeta.setLayout(new BorderLayout(0, 8));
        tarjeta.setBorder(BorderFactory.createEmptyBorder(12, 12, 10, 12));

        // ---- Arriba: ícono + título + número + porcentaje ----
        JPanel arriba = new JPanel(new BorderLayout(10, 0));
        arriba.setOpaque(false);
        JPanel contIcono = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));   // ícono pegado arriba
        contIcono.setOpaque(false);
        contIcono.add(new IconoCuadrado(ico, 44, color));
        arriba.add(contIcono, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(new Color(10, 44, 120));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(new Color(10, 44, 120));
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 30));

        JLabel lblPorc = new JLabel(porcentaje,
                FontIcon.of(FontAwesomeSolid.ARROW_UP, 10, new Color(22, 163, 74)), SwingConstants.LEFT);
        lblPorc.setIconTextGap(3);
        lblPorc.setForeground(new Color(22, 163, 74));
        lblPorc.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JPanel filaNumero = new JPanel(new BorderLayout());
        filaNumero.setOpaque(false);
        filaNumero.add(lblValor, BorderLayout.WEST);
        filaNumero.add(lblPorc, BorderLayout.EAST);

        JPanel textos = new JPanel(new BorderLayout());
        textos.setOpaque(false);
        textos.add(lblTitulo, BorderLayout.NORTH);
        textos.add(filaNumero, BorderLayout.CENTER);
        arriba.add(textos, BorderLayout.CENTER);

        tarjeta.add(arriba, BorderLayout.CENTER);

        // ---- Abajo: descripción ----
        JLabel lblDesc = new JLabel(descripcion);
        lblDesc.setForeground(new Color(110, 125, 150));
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        tarjeta.add(lblDesc, BorderLayout.SOUTH);

        return tarjeta;
    }

    // ---------------------------------------------------------------------
    // 8.4.1 BOTÓN DE ACCESO RÁPIDO
    // ---------------------------------------------------------------------
    // Componente que faltaba y provocaba el error "cannot find symbol BotonAcceso".
    private class BotonAcceso extends JPanel {

        private final Color colorIcono;
        private final Icon icono;
        private boolean hover = false;

        BotonAcceso(String texto, FontAwesomeSolid tipoIcono,
                    Color color, Runnable accion) {
            this.colorIcono = color;
            this.icono = FontIcon.of(tipoIcono, 22, color);

            setOpaque(false);
            setLayout(new BorderLayout(10, 0));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 8));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setToolTipText(texto);

            JLabel lblIcono = new JLabel(icono);
            lblIcono.setPreferredSize(new Dimension(30, 34));
            lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblIcono, BorderLayout.WEST);

            JLabel lblTexto = new JLabel("<html><div style='width:105px;'>"
                    + texto + "</div></html>");
            lblTexto.setForeground(new Color(10, 44, 120));
            lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblTexto.setHorizontalAlignment(SwingConstants.LEFT);
            add(lblTexto, BorderLayout.CENTER);

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

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        accion.run();
                    }
                }
            });

            // Hace que los clics sobre el icono o el texto también activen el botón.
            MouseAdapter reenviarClic = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        accion.run();
                    }
                }
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
            };
            lblIcono.addMouseListener(reenviarClic);
            lblTexto.addMouseListener(reenviarClic);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(hover ? new Color(235, 242, 255) : Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.setColor(hover ? colorIcono : new Color(214, 224, 240));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ---------------------------------------------------------------------
    // 8.5 COMPONENTES REUTILIZABLES (los usan varias tarjetas)
    // ---------------------------------------------------------------------
    // 8.5.1 Clase: panel blanco con bordes redondeados (base de todas las tarjetas)
    private class PanelRedondeado extends JPanel {

        PanelRedondeado() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.setColor(new Color(214, 224, 240));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.dispose();
        }
    }

    // 8.5.2 Clase: cuadro redondeado de color con ícono blanco (estadísticas y accesos)
    private class IconoCuadrado extends JComponent {

        private final Icon icono;
        private final Color color;

        IconoCuadrado(FontAwesomeSolid ico, int tamano, Color color) {
            this.icono = FontIcon.of(ico, tamano / 2, Color.WHITE);
            this.color = color;
            setPreferredSize(new Dimension(tamano, tamano));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            icono.paintIcon(this, g2,
                    (getWidth() - icono.getIconWidth()) / 2,
                    (getHeight() - icono.getIconHeight()) / 2);
            g2.dispose();
        }
    }

    // 8.5.3 Clase: círculo de color con ícono blanco (birrete de la bienvenida)
    private class IconoCircular extends JComponent {

        private final Icon icono;
        private final Color color;

        IconoCircular(FontAwesomeSolid ico, int tamano, Color color) {
            this.icono = FontIcon.of(ico, tamano / 2, Color.WHITE);
            this.color = color;
            setPreferredSize(new Dimension(tamano, tamano));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
            icono.paintIcon(this, g2,
                    (getWidth() - icono.getIconWidth()) / 2,
                    (getHeight() - icono.getIconHeight()) / 2);
            g2.dispose();
        }
    }
}
