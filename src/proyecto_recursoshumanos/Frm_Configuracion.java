package proyecto_recursoshumanos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

/**
 * Pantalla "Configuración del sistema". Es un JPanel para mostrarse dentro de
 * panelContenido del menú principal: mostrarPanel(new Frm_Configuracion());
 *
 * @author ASUS
 */
public class Frm_Configuracion extends JPanel {

    // ---------- Colores y fuentes (tomados del mockup) ----------
    private static final Color FONDO = new Color(0xF1F5FC);
    private static final Color AZUL = new Color(0x1560E0);
    private static final Color AZUL_OSCURO = new Color(0x0B1F7A);
    private static final Color AZUL_CLARO = new Color(0xE4EEFC);
    private static final Color AZUL_AVATAR = new Color(0x2F7BEA);
    private static final Color BORDE = new Color(0xDCE5F3);
    private static final Color TEXTO_SUAVE = new Color(0x5F6F8C);
    private static final Color GRIS_OFF = new Color(0xB8C4D8);

    private static final String FUENTE = "Segoe UI";
    private static final Font F_TITULO = new Font(FUENTE, Font.BOLD, 28);
    private static final Font F_SECCION = new Font(FUENTE, Font.BOLD, 20);
    private static final Font F_FILA = new Font(FUENTE, Font.BOLD, 15);
    private static final Font F_DESC = new Font(FUENTE, Font.PLAIN, 13);
    private static final Font F_TEXTO = new Font(FUENTE, Font.PLAIN, 15);

    // ---------- Valores por defecto de las preferencias ----------
    private static final String DEF_TEMA = "Claro";
    private static final String DEF_IDIOMA = "Español";
    private static final String DEF_FECHA = "DD/MM/AAAA";

    // ---------- Componentes con los que se interactúa ----------
    private final JLabel lblUsuario = new JLabel("admin");
    private final JLabel lblNombre = new JLabel("Administrador del Sistema");
    private final JLabel lblCorreo = new JLabel("admin@senati.edu.pe");
    private final Pildora lblRol = new Pildora("Administrador");

    private final JComboBox<String> cboTema = new JComboBox<>(new String[]{"Claro", "Oscuro"});
    private final JComboBox<String> cboIdioma = new JComboBox<>(new String[]{"Español", "English"});
    private final JComboBox<String> cboFecha = new JComboBox<>(new String[]{"DD/MM/AAAA", "MM/DD/AAAA", "AAAA-MM-DD"});
    private final Interruptor swNotificaciones = new Interruptor();

    private final Preferences prefs = Preferences.userNodeForPackage(Frm_Configuracion.class);

    // =====================================================================
    //  CONSTRUCTOR
    // =====================================================================
    public Frm_Configuracion() {
        setLayout(new BorderLayout(0, 16));
        setBackground(FONDO);
        setBorder(new EmptyBorder(16, 16, 16, 16));

        add(crearEncabezado(), BorderLayout.NORTH);
        add(crearCuerpo(), BorderLayout.CENTER);

        cargarPreferencias();
    }

    /**
     * Para mostrar los datos del usuario que inició sesión.
     */
    public void setUsuario(String usuario, String nombreCompleto, String rol, String correo) {
        lblUsuario.setText(usuario);
        lblNombre.setText(nombreCompleto);
        lblRol.setText(rol);
        lblCorreo.setText(correo);
    }

    // =====================================================================
    //  ENCABEZADO
    // =====================================================================
    private JPanel crearEncabezado() {
        Tarjeta cab = new Tarjeta(new BorderLayout(20, 0), true);
        cab.setBorder(new EmptyBorder(16, 22, 16, 22));

        cab.add(new IconoDecorado(FontAwesomeSolid.COG, 84, 42, AZUL, Color.WHITE, 84), BorderLayout.WEST);

        JLabel titulo = new JLabel("Configuración del sistema");
        titulo.setFont(F_TITULO);
        titulo.setForeground(AZUL_OSCURO);

        JLabel sub = new JLabel("Administra las preferencias y parámetros del sistema.");
        sub.setFont(new Font(FUENTE, Font.PLAIN, 17));
        sub.setForeground(TEXTO_SUAVE);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(Box_glue());
        textos.add(titulo);
        textos.add(javax.swing.Box.createVerticalStrut(4));
        textos.add(sub);
        textos.add(Box_glue());
        cab.add(textos, BorderLayout.CENTER);
        return cab;
    }

    private static Component Box_glue() {
        return javax.swing.Box.createVerticalGlue();
    }

    // =====================================================================
    //  CUERPO: dos columnas
    // =====================================================================
    private JPanel crearCuerpo() {
        JPanel cuerpo = new JPanel(new GridBagLayout());
        cuerpo.setOpaque(false);

        // Columna izquierda: Perfil + Preferencias
        JPanel izq = new JPanel(new GridBagLayout());
        izq.setOpaque(false);
        agregar(izq, crearPerfil(), 0, 0.46);
        agregar(izq, crearPreferencias(), 1, 0.54);

        // Columna derecha: Seguridad + Información + botones
        JPanel der = new JPanel(new GridBagLayout());
        der.setOpaque(false);
        agregar(der, crearSeguridad(), 0, 0.50);
        agregar(der, crearInformacion(), 1, 0.50);
        agregar(der, crearBotonesInferiores(), 2, 0);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 1;
        c.gridy = 0;

        c.gridx = 0;
        c.insets = new Insets(0, 0, 0, 8);
        cuerpo.add(izq, c);

        c.gridx = 1;
        c.insets = new Insets(0, 8, 0, 0);
        cuerpo.add(der, c);
        return cuerpo;
    }

    /**
     * Agrega un componente a una columna (vertical) con 16 px de separación.
     */
    private static void agregar(JPanel columna, Component comp, int fila, double pesoY) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = fila;
        c.weightx = 1;
        c.weighty = pesoY;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(fila > 0 ? 16 : 0, 0, 0, 0);
        columna.add(comp, c);
    }

    // =====================================================================
    //  PERFIL DE USUARIO
    // =====================================================================
    private JPanel crearPerfil() {
        Tarjeta card = crearCard("Perfil de usuario", FontAwesomeSolid.USER);

        Tarjeta interior = new Tarjeta(new BorderLayout(0, 12), false);
        interior.setBorder(new EmptyBorder(16, 24, 14, 24));

        // Avatar + datos
        JPanel fila = new JPanel(new GridBagLayout());
        fila.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 4;
        c.insets = new Insets(0, 10, 0, 50);
        fila.add(new IconoDecorado(FontAwesomeSolid.USER, 124, 66, AZUL_AVATAR, Color.WHITE, 124), c);

        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 0, 5, 30);
        agregarDato(fila, c, 0, "Usuario:", lblUsuario);
        agregarDato(fila, c, 1, "Nombre completo:", lblNombre);
        agregarDato(fila, c, 2, "Rol:", lblRol);
        agregarDato(fila, c, 3, "Correo:", lblCorreo);

        // Filler para que los datos queden pegados a la izquierda
        GridBagConstraints relleno = new GridBagConstraints();
        relleno.gridx = 3;
        relleno.weightx = 1;
        fila.add(new JLabel(), relleno);

        interior.add(fila, BorderLayout.CENTER);

        // Línea + botón Editar perfil
        JPanel pie = new JPanel(new BorderLayout(0, 12));
        pie.setOpaque(false);
        JPanel linea = new JPanel();
        linea.setBackground(BORDE);
        linea.setPreferredSize(new Dimension(1, 1));
        pie.add(linea, BorderLayout.NORTH);

        Boton btnEditar = new Boton("Editar perfil", FontAwesomeSolid.PENCIL_ALT, true);
        btnEditar.setPreferredSize(new Dimension(192, 44));
        btnEditar.addActionListener(e -> editarPerfil());
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);
        centro.add(btnEditar);
        pie.add(centro, BorderLayout.CENTER);
        interior.add(pie, BorderLayout.SOUTH);

        card.add(interior, BorderLayout.CENTER);
        return card;
    }

    private void agregarDato(JPanel panel, GridBagConstraints c, int fila, String etiqueta, JComponent valor) {
        JLabel l = new JLabel(etiqueta);
        l.setFont(F_FILA);
        l.setForeground(AZUL_OSCURO);
        c.gridx = 1;
        c.gridy = fila;
        panel.add(l, c);

        valor.setFont(F_TEXTO);
        if (!(valor instanceof Pildora)) {
            valor.setForeground(new Color(0x2B3A5A));
        }
        c.gridx = 2;
        panel.add(valor, c);
    }

    // =====================================================================
    //  SEGURIDAD
    // =====================================================================
    private JPanel crearSeguridad() {
        Tarjeta card = crearCard("Seguridad", FontAwesomeSolid.SHIELD_ALT);

        JPanel filas = new JPanel(new GridLayout(3, 1, 0, 12));
        filas.setOpaque(false);
        filas.add(crearFilaSeguridad(FontAwesomeSolid.KEY, "Cambiar contraseña",
                "Actualiza tu contraseña de acceso al sistema.", "Cambiar", e -> cambiarContrasena()));
        filas.add(crearFilaSeguridad(FontAwesomeSolid.LOCK, "PIN de seguridad",
                "Configura o modifica tu PIN de seguridad.", "Configurar", e -> configurarPin()));
        filas.add(crearFilaSeguridad(FontAwesomeSolid.DESKTOP, "Sesiones activas",
                "Gestiona las sesiones activas en otros dispositivos.", "Ver sesiones", e -> verSesiones()));

        card.add(filas, BorderLayout.CENTER);
        return card;
    }

    private JPanel crearFilaSeguridad(Ikon icono, String titulo, String descripcion,
            String textoBoton, ActionListener accion) {
        Tarjeta fila = new Tarjeta(new BorderLayout(16, 0), false);
        fila.setBorder(new EmptyBorder(8, 12, 8, 16));

        fila.add(new IconoDecorado(icono, 54, 26, AZUL_CLARO, AZUL, 12), BorderLayout.WEST);
        fila.add(bloqueTexto(titulo, descripcion), BorderLayout.CENTER);

        Boton btn = new Boton(textoBoton, null, false);
        btn.setPreferredSize(new Dimension(130, 40));
        btn.addActionListener(accion);
        JPanel este = new JPanel(new GridBagLayout());
        este.setOpaque(false);
        este.add(btn);
        fila.add(este, BorderLayout.EAST);
        return fila;
    }

    // =====================================================================
    //  PREFERENCIAS DEL SISTEMA
    // =====================================================================
    private JPanel crearPreferencias() {
        Tarjeta card = crearCard("Preferencias del sistema", FontAwesomeSolid.COG);

        JPanel filas = new JPanel(new GridLayout(4, 1, 0, 6));
        filas.setOpaque(false);
        filas.add(crearFilaPreferencia(FontAwesomeSolid.PALETTE, "Tema del sistema",
                "Selecciona el tema de la interfaz.", cboTema));
        filas.add(crearFilaPreferencia(FontAwesomeSolid.GLOBE, "Idioma",
                "Selecciona el idioma del sistema.", cboIdioma));
        filas.add(crearFilaPreferencia(FontAwesomeSolid.CALENDAR_ALT, "Formato de fecha",
                "Selecciona el formato de visualización de fechas.", cboFecha));
        filas.add(crearFilaPreferencia(FontAwesomeSolid.BELL, "Notificaciones",
                "Recibe alertas y notificaciones del sistema.", swNotificaciones));

        card.add(filas, BorderLayout.CENTER);
        return card;
    }

    private JPanel crearFilaPreferencia(Ikon icono, String titulo, String descripcion, JComponent control) {
        Tarjeta fila = new Tarjeta(new BorderLayout(14, 0), false);
        fila.setBorder(new EmptyBorder(4, 12, 4, 12));

        JLabel ico = new JLabel(crearIcono(icono, 24, AZUL));
        ico.setPreferredSize(new Dimension(36, 36));
        ico.setHorizontalAlignment(JLabel.CENTER);
        fila.add(ico, BorderLayout.WEST);
        fila.add(bloqueTexto(titulo, descripcion), BorderLayout.CENTER);

        if (control instanceof JComboBox) {
            control.setFont(new Font(FUENTE, Font.PLAIN, 14));
            control.setPreferredSize(new Dimension(180, 36));
            control.setBackground(Color.WHITE);
            control.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        JPanel este = new JPanel(new GridBagLayout());
        este.setOpaque(false);
        este.add(control);
        fila.add(este, BorderLayout.EAST);
        return fila;
    }

    // =====================================================================
    //  INFORMACIÓN DEL SISTEMA
    // =====================================================================
    private JPanel crearInformacion() {
        Tarjeta card = crearCard("Información del sistema", FontAwesomeSolid.INFO_CIRCLE);

        String ahora = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a", Locale.forLanguageTag("es-PE")));

        JPanel tabla = new JPanel(new GridLayout(4, 1, 0, 0));
        tabla.setBackground(Color.WHITE);
        tabla.setBorder(BorderFactory.createLineBorder(BORDE));
        tabla.add(crearFilaInfo(FontAwesomeSolid.DESKTOP, "Aplicación", "Sistema de Recursos Humanos", true));
        tabla.add(crearFilaInfo(FontAwesomeSolid.TAG, "Versión", "1.0.0", true));
        tabla.add(crearFilaInfo(FontAwesomeSolid.DATABASE, "Base de datos", "MySQL", true));
        tabla.add(crearFilaInfo(FontAwesomeSolid.CLOCK, "Última actualización", ahora, false));

        card.add(tabla, BorderLayout.CENTER);
        return card;
    }

    private JPanel crearFilaInfo(Ikon icono, String etiqueta, String valor, boolean conLinea) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.setBackground(Color.WHITE);
        if (conLinea) {
            fila.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDE));
        }

        JLabel izq = new JLabel(etiqueta, crearIcono(icono, 22, AZUL), JLabel.LEFT);
        izq.setIconTextGap(14);
        izq.setFont(F_FILA);
        izq.setForeground(AZUL_OSCURO);
        JPanel celdaIzq = new JPanel(new GridBagLayout());
        celdaIzq.setBackground(new Color(0xF4F8FE));
        celdaIzq.setPreferredSize(new Dimension(260, 10));
        celdaIzq.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDE));
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 14, 0, 0);
        celdaIzq.add(izq, c);

        JLabel der = new JLabel(valor);
        der.setFont(F_TEXTO);
        der.setForeground(new Color(0x2B3A5A));
        JPanel celdaDer = new JPanel(new GridBagLayout());
        celdaDer.setOpaque(false);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.weightx = 1;
        c2.anchor = GridBagConstraints.WEST;
        c2.insets = new Insets(0, 20, 0, 0);
        celdaDer.add(der, c2);

        fila.add(celdaIzq, BorderLayout.WEST);
        fila.add(celdaDer, BorderLayout.CENTER);
        return fila;
    }

    // =====================================================================
    //  BOTONES INFERIORES
    // =====================================================================
    private JPanel crearBotonesInferiores() {
        JPanel p = new JPanel(new GridLayout(1, 2, 16, 0));
        p.setOpaque(false);
        p.setPreferredSize(new Dimension(10, 54));

        Boton btnRestablecer = new Boton("Restablecer", FontAwesomeSolid.UNDO, false);
        btnRestablecer.addActionListener(e -> restablecer());
        Boton btnGuardar = new Boton("Guardar cambios", FontAwesomeSolid.SAVE, true);
        btnGuardar.addActionListener(e -> guardarCambios());

        p.add(btnRestablecer);
        p.add(btnGuardar);
        return p;
    }

    // =====================================================================
    //  ACCIONES
    // =====================================================================
    private void cargarPreferencias() {
        cboTema.setSelectedItem(prefs.get("tema", DEF_TEMA));
        cboIdioma.setSelectedItem(prefs.get("idioma", DEF_IDIOMA));
        cboFecha.setSelectedItem(prefs.get("formatoFecha", DEF_FECHA));
        swNotificaciones.setActivo(prefs.getBoolean("notificaciones", true));
    }

    private void guardarCambios() {
        prefs.put("tema", (String) cboTema.getSelectedItem());
        prefs.put("idioma", (String) cboIdioma.getSelectedItem());
        prefs.put("formatoFecha", (String) cboFecha.getSelectedItem());
        prefs.putBoolean("notificaciones", swNotificaciones.isActivo());
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "Los cambios se guardaron correctamente.", "Configuración",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void restablecer() {
        cboTema.setSelectedItem(DEF_TEMA);
        cboIdioma.setSelectedItem(DEF_IDIOMA);
        cboFecha.setSelectedItem(DEF_FECHA);
        swNotificaciones.setActivo(true);
    }

    private void editarPerfil() {
        // TODO: abrir tu formulario de edición de perfil
        enConstruccion("Editar perfil");
    }

    private void cambiarContrasena() {
        // TODO: abrir tu formulario de cambio de contraseña
        enConstruccion("Cambiar contraseña");
    }

    private void configurarPin() {
        // TODO: aquí puedes abrir Frm_PinSeguridad, por ejemplo:
        // new Frm_PinSeguridad().setVisible(true);
        enConstruccion("PIN de seguridad");
    }

    private void verSesiones() {
        // TODO: abrir la lista de sesiones activas
        enConstruccion("Sesiones activas");
    }

    private void enConstruccion(String modulo) {
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "\"" + modulo + "\" estará disponible próximamente.", "En construcción",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // =====================================================================
    //  HELPERS DE CONSTRUCCIÓN
    // =====================================================================
    private static FontIcon crearIcono(Ikon ikon, int tam, Color color) {
        FontIcon fi = FontIcon.of(ikon);
        fi.setIconSize(tam);
        fi.setIconColor(color);
        return fi;
    }

    /**
     * Tarjeta blanca con título de sección (ícono + texto) arriba.
     */
    private static Tarjeta crearCard(String titulo, Ikon icono) {
        Tarjeta card = new Tarjeta(new BorderLayout(0, 12), false);
        card.setBorder(new EmptyBorder(16, 20, 18, 20));

        JLabel t = new JLabel(titulo, crearIcono(icono, 24, AZUL), JLabel.LEFT);
        t.setIconTextGap(12);
        t.setFont(F_SECCION);
        t.setForeground(AZUL_OSCURO);
        card.add(t, BorderLayout.NORTH);
        return card;
    }

    /**
     * Título en negrita + descripción gris, centrados verticalmente.
     */
    private static JPanel bloqueTexto(String titulo, String descripcion) {
        JLabel t = new JLabel(titulo);
        t.setFont(F_FILA);
        t.setForeground(AZUL_OSCURO);
        JLabel d = new JLabel(descripcion);
        d.setFont(F_DESC);
        d.setForeground(TEXTO_SUAVE);

        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(javax.swing.Box.createVerticalGlue());
        p.add(t);
        p.add(javax.swing.Box.createVerticalStrut(2));
        p.add(d);
        p.add(javax.swing.Box.createVerticalGlue());
        return p;
    }

    // =====================================================================
    //  COMPONENTES PERSONALIZADOS
    // =====================================================================
    /**
     * Panel con esquinas redondeadas, borde suave y (opcional) degradado.
     */
    private static class Tarjeta extends JPanel {

        private final boolean degradado;

        Tarjeta(LayoutManager layout, boolean degradado) {
            super(layout);
            this.degradado = degradado;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape forma = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            if (degradado) {
                g2.setPaint(new GradientPaint(0, 0, Color.WHITE, getWidth(), 0, new Color(0xD6E5FB)));
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.fill(forma);
            g2.setColor(BORDE);
            g2.draw(forma);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /**
     * Círculo o cuadrado redondeado con un ícono en el centro.
     */
    private static class IconoDecorado extends JComponent {

        private final Icon icono;
        private final Color fondo;
        private final int arco;

        IconoDecorado(Ikon ikon, int tamano, int tamIcono, Color fondo, Color colorIcono, int arco) {
            this.icono = crearIcono(ikon, tamIcono, colorIcono);
            this.fondo = fondo;
            this.arco = arco;
            Dimension d = new Dimension(tamano, tamano);
            setPreferredSize(d);
            setMinimumSize(d);
            setMaximumSize(d);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fondo);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arco, arco));
            icono.paintIcon(this, g2,
                    (getWidth() - icono.getIconWidth()) / 2,
                    (getHeight() - icono.getIconHeight()) / 2);
            g2.dispose();
        }
    }

    /**
     * Etiqueta tipo "píldora" (el rol Administrador).
     */
    private static class Pildora extends JLabel {

        Pildora(String texto) {
            super(texto, JLabel.CENTER);
            setOpaque(false);
            setForeground(Color.WHITE);
            setFont(new Font(FUENTE, Font.BOLD, 14));
            setBorder(new EmptyBorder(5, 20, 5, 20));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(AZUL);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), getHeight(), getHeight()));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /**
     * Botón redondeado: relleno azul o solo borde azul.
     */
    private static class Boton extends JButton {

        private final boolean relleno;
        private final Icon icono;
        private boolean encima = false;

        Boton(String texto, Ikon ikon, boolean relleno) {
            super(texto);
            this.relleno = relleno;
            Color colorTexto = relleno ? Color.WHITE : AZUL;
            this.icono = (ikon == null) ? null : crearIcono(ikon, 18, colorTexto);
            setFont(new Font(FUENTE, Font.BOLD, 15));
            setForeground(colorTexto);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    encima = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    encima = false;
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
            Shape forma = new RoundRectangle2D.Float(0.5f, 0.5f, w - 1, h - 1, 10, 10);
            if (relleno) {
                g2.setColor(encima ? AZUL.darker() : AZUL);
                g2.fill(forma);
            } else {
                g2.setColor(encima ? AZUL_CLARO : Color.WHITE);
                g2.fill(forma);
                g2.setColor(AZUL);
                g2.draw(forma);
            }

            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();
            int anchoTexto = fm.stringWidth(getText());
            int anchoIcono = (icono != null) ? icono.getIconWidth() + 10 : 0;
            int x = (w - (anchoIcono + anchoTexto)) / 2;
            if (icono != null) {
                icono.paintIcon(this, g2, x, (h - icono.getIconHeight()) / 2);
            }
            g2.setColor(getForeground());
            g2.drawString(getText(), x + anchoIcono, (h - fm.getHeight()) / 2 + fm.getAscent());
            g2.dispose();
        }
    }

    /**
     * Interruptor tipo "toggle" (Notificaciones).
     */
    private static class Interruptor extends JComponent {

        private boolean activo = true;

        Interruptor() {
            Dimension d = new Dimension(66, 34);
            setPreferredSize(d);
            setMinimumSize(d);
            setMaximumSize(d);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    activo = !activo;
                    repaint();
                }
            });
        }

        boolean isActivo() {
            return activo;
        }

        void setActivo(boolean activo) {
            this.activo = activo;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth();
            int h = getHeight();
            g2.setColor(activo ? AZUL : GRIS_OFF);
            g2.fill(new RoundRectangle2D.Float(0, 0, w, h, h, h));
            int d = h - 8;
            int x = activo ? w - d - 4 : 4;
            g2.setColor(Color.WHITE);
            g2.fillOval(x, 4, d, d);
            g2.dispose();
        }
    }
}
