package proyecto_recursoshumanos;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

/**
 * Panel "Gestión de Planillas" (funcional).
 * Se muestra dentro de panelContenido de Frm_Menu_Principal al hacer clic en "Planillas".
 *
 * Los datos se guardan en memoria (lista "todas"). Para conectarlo a la base de datos,
 * revisa los métodos marcados con  >>> BD <<<  (cargar, guardar, eliminar, cambiar estado).
 */
public class Frm_Planillas extends JPanel {

    // ===================================================================
    // 1. COLORES Y FUENTES
    // ===================================================================
    private static final Color BG        = new Color(0xF4F7FB);
    private static final Color AZUL      = new Color(0x2563EB);
    private static final Color AZUL_OSC  = new Color(0x0F2A5C);
    private static final Color VERDE     = new Color(0x16A34A);
    private static final Color AMARILLO  = new Color(0xF59E0B);
    private static final Color ROJO      = new Color(0xEF4444);
    private static final Color GRIS_BTN  = new Color(0xE5E7EB);
    private static final Color GRIS_BTN2 = new Color(0x6B7280);
    private static final Color BORDE     = new Color(0xDDE5F0);
    private static final Color TEXTO_SEC = new Color(0x6B7280);

    private static final Font F_TITULO  = new Font("Segoe UI", Font.BOLD, 26);
    private static final Font F_SUB     = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font F_SECCION = new Font("Segoe UI", Font.BOLD, 15);
    private static final Font F_NORMAL  = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font F_BOLD    = new Font("Segoe UI", Font.BOLD, 13);

    private static final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private static final String[] DEPARTAMENTOS = {"Sistemas", "Administración",
        "Recursos Humanos", "Contabilidad", "Logística"};

    private static final String PENDIENTE = "Pendiente";
    private static final String CALCULADA = "Calculada";
    private static final String PAGADA    = "Pagada";

    // ===================================================================
    // 2. DATOS
    // ===================================================================
    /** Una planilla de un empleado en un periodo (mes + año). */
    private static class Planilla {
        int id, mes, anio;
        String empleado, dni, departamento, puesto, estado;
        double sueldo, bonif, desc;

        double bruto() { return sueldo + bonif; }
        double neto()  { return bruto() - desc; }
    }

    private final List<Planilla> todas = new ArrayList<>();      // todas las planillas
    private final List<Planilla> visibles = new ArrayList<>();   // las que se ven en la tabla
    private int siguienteId = 1;

    // ===================================================================
    // 3. COMPONENTES
    // ===================================================================
    private JComboBox<String> cboMes, cboAnio, cboDepartamento, cboEstado;
    private JTextField txtBuscar;
    private JTable tabla;
    private DefaultTableModel modelo;

    // Valores de las tarjetas de resumen
    private final JLabel lblEmpleados = new JLabel("0");
    private final JLabel lblPeriodo   = new JLabel("");
    private final JLabel lblGeneradas = new JLabel("0");
    private final JLabel lblTotal     = new JLabel("S/ 0.00");

    private static final String[] COLUMNAS = {
        "ID", "Empleado", "DNI", "Departamento", "Puesto", "Sueldo Básico",
        "Bonificaciones", "Descuentos", "Total Bruto", "Neto a Pagar", "Estado"
    };

    // ===================================================================
    // 4. CONSTRUCTOR
    // ===================================================================
    public Frm_Planillas() {
        setLayout(new BorderLayout(0, 14));
        setBackground(BG);
        setBorder(new EmptyBorder(16, 20, 16, 20));

        JPanel arriba = new JPanel();
        arriba.setOpaque(false);
        arriba.setLayout(new BoxLayout(arriba, BoxLayout.Y_AXIS));

        JPanel header = crearHeader();
        JPanel tarjetas = crearTarjetas();
        JPanel filtros = crearFiltros();
        header.setAlignmentX(LEFT_ALIGNMENT);
        tarjetas.setAlignmentX(LEFT_ALIGNMENT);
        filtros.setAlignmentX(LEFT_ALIGNMENT);

        arriba.add(header);
        arriba.add(Box.createVerticalStrut(14));
        arriba.add(tarjetas);
        arriba.add(Box.createVerticalStrut(14));
        arriba.add(filtros);

        add(arriba, BorderLayout.NORTH);
        add(crearListado(), BorderLayout.CENTER);
        add(crearAcciones(), BorderLayout.SOUTH);

        cargarDatos();
        refrescarTabla();
    }

    // ===================================================================
    // 5. HEADER
    // ===================================================================
    private JPanel crearHeader() {
        JPanel p = new JPanel(new BorderLayout(14, 0));
        p.setOpaque(false);

        RoundPanel icono = new RoundPanel(AZUL, null, 14);
        icono.setLayout(new GridBagLayout());
        icono.setPreferredSize(new Dimension(56, 56));
        icono.add(new JLabel(FontIcon.of(FontAwesomeSolid.FILE_INVOICE_DOLLAR, 28, Color.WHITE)));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        JLabel t = new JLabel("Gestión de Planillas");
        t.setFont(F_TITULO);
        t.setForeground(AZUL_OSC);
        JLabel s = new JLabel("Administración de remuneraciones del personal");
        s.setFont(F_SUB);
        s.setForeground(new Color(0x374151));
        textos.add(Box.createVerticalStrut(2));
        textos.add(t);
        textos.add(s);

        JPanel izq = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        izq.setOpaque(false);
        JPanel wrap = new JPanel(new BorderLayout(14, 0));
        wrap.setOpaque(false);
        wrap.add(icono, BorderLayout.WEST);
        wrap.add(textos, BorderLayout.CENTER);
        izq.add(wrap);

        p.add(izq, BorderLayout.WEST);
        return p;
    }

    // ===================================================================
    // 6. TARJETAS DE RESUMEN (se actualizan solas)
    // ===================================================================
    private JPanel crearTarjetas() {
        JPanel p = new JPanel(new GridLayout(1, 4, 16, 0));
        p.setOpaque(false);
        p.add(crearTarjeta(new Color(0xEFF6FF), new Color(0xD6E4FA), new Color(0x3B82F6),
                FontAwesomeSolid.USERS, "Empleados en planilla", lblEmpleados, "Registrados en el periodo", 26));
        p.add(crearTarjeta(new Color(0xF0FDF4), new Color(0xCDEFD8), new Color(0x16A34A),
                FontAwesomeSolid.CALENDAR_ALT, "Periodo actual", lblPeriodo, "Mes de procesamiento", 22));
        p.add(crearTarjeta(new Color(0xFFF7ED), new Color(0xFBDDC2), new Color(0xF97316),
                FontAwesomeSolid.FILE_ALT, "Planillas generadas", lblGeneradas, "Calculadas o pagadas", 26));
        p.add(crearTarjeta(new Color(0xF5F3FF), new Color(0xE0D9FA), new Color(0x7C3AED),
                FontAwesomeSolid.COINS, "Total a pagar", lblTotal, "Calculadas sin pagar", 26));
        return p;
    }

    private RoundPanel crearTarjeta(Color fondo, Color borde, Color colorIcono,
                                    FontAwesomeSolid ico, String titulo, JLabel lv,
                                    String sub, int tamValor) {
        RoundPanel card = new RoundPanel(fondo, borde, 16);
        card.setLayout(new BorderLayout(14, 0));
        card.setBorder(new EmptyBorder(14, 14, 14, 14));

        RoundPanel caja = new RoundPanel(colorIcono, null, 12);
        caja.setLayout(new GridBagLayout());
        caja.setPreferredSize(new Dimension(52, 52));
        caja.add(new JLabel(FontIcon.of(ico, 26, Color.WHITE)));

        JPanel iconoWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        iconoWrap.setOpaque(false);
        iconoWrap.add(caja);

        JPanel txt = new JPanel();
        txt.setOpaque(false);
        txt.setLayout(new BoxLayout(txt, BoxLayout.Y_AXIS));
        JLabel lt = new JLabel(titulo);
        lt.setFont(F_NORMAL);
        lt.setForeground(AZUL_OSC);
        lv.setFont(new Font("Segoe UI", Font.BOLD, tamValor));
        lv.setForeground(AZUL_OSC);
        JLabel ls = new JLabel(sub);
        ls.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        ls.setForeground(TEXTO_SEC);
        txt.add(lt);
        txt.add(lv);
        txt.add(ls);

        card.add(iconoWrap, BorderLayout.WEST);
        card.add(txt, BorderLayout.CENTER);
        return card;
    }

    // ===================================================================
    // 7. FILTROS DE BÚSQUEDA
    // ===================================================================
    private JPanel crearFiltros() {
        RoundPanel card = new RoundPanel(Color.WHITE, BORDE, 14);
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(new EmptyBorder(12, 16, 14, 16));

        card.add(tituloSeccion(FontAwesomeSolid.SEARCH, "Filtros de búsqueda"), BorderLayout.NORTH);

        cboMes = new JComboBox<>(MESES);
        cboMes.setSelectedItem("Octubre");
        cboAnio = new JComboBox<>(new String[]{"2024", "2025", "2026", "2027"});
        cboAnio.setSelectedItem("2026");

        String[] deps = new String[DEPARTAMENTOS.length + 1];
        deps[0] = "Todos";
        System.arraycopy(DEPARTAMENTOS, 0, deps, 1, DEPARTAMENTOS.length);
        cboDepartamento = new JComboBox<>(deps);
        cboEstado = new JComboBox<>(new String[]{"Todos", CALCULADA, PENDIENTE, PAGADA});

        txtBuscar = new JTextField();
        txtBuscar.setFont(F_NORMAL);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0xCBD5E1), 1, true), new EmptyBorder(0, 8, 0, 8)));
        txtBuscar.addActionListener(e -> refrescarTabla());

        for (JComboBox<String> c : List.of(cboMes, cboAnio, cboDepartamento, cboEstado)) {
            c.setFont(F_NORMAL);
            c.setBackground(Color.WHITE);
        }

        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        fila.setOpaque(false);

        JPanel periodo = new JPanel(new BorderLayout(0, 4));
        periodo.setOpaque(false);
        periodo.add(etiqueta("Periodo:"), BorderLayout.NORTH);
        JPanel combos = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        combos.setOpaque(false);
        cboMes.setPreferredSize(new Dimension(130, 36));
        cboAnio.setPreferredSize(new Dimension(80, 36));
        combos.add(cboMes);
        combos.add(cboAnio);
        periodo.add(combos, BorderLayout.CENTER);

        fila.add(periodo);
        fila.add(campo("Departamento:", cboDepartamento, 170));
        fila.add(campo("Estado:", cboEstado, 130));
        fila.add(campo("Buscar empleado:", txtBuscar, 220));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        botones.setOpaque(false);
        RoundButton btnBuscar = new RoundButton("Buscar", FontAwesomeSolid.SEARCH, AZUL, Color.WHITE);
        RoundButton btnLimpiar = new RoundButton("Limpiar", FontAwesomeSolid.BROOM, GRIS_BTN2, Color.WHITE);
        btnBuscar.setPreferredSize(new Dimension(115, 36));
        btnLimpiar.setPreferredSize(new Dimension(125, 36));
        btnBuscar.addActionListener(e -> refrescarTabla());
        btnLimpiar.addActionListener(e -> limpiarFiltros());
        botones.add(btnBuscar);
        botones.add(btnLimpiar);

        JPanel botonesWrap = new JPanel(new BorderLayout());
        botonesWrap.setOpaque(false);
        botonesWrap.setBorder(new EmptyBorder(20, 0, 0, 0));
        botonesWrap.add(botones, BorderLayout.CENTER);
        fila.add(botonesWrap);

        card.add(fila, BorderLayout.CENTER);
        return card;
    }

    private JPanel campo(String texto, JComponent comp, int ancho) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setOpaque(false);
        comp.setPreferredSize(new Dimension(ancho, 36));
        p.add(etiqueta(texto), BorderLayout.NORTH);
        p.add(comp, BorderLayout.CENTER);
        return p;
    }

    private JLabel etiqueta(String t) {
        JLabel l = new JLabel(t);
        l.setFont(F_NORMAL);
        l.setForeground(AZUL_OSC);
        return l;
    }

    private JPanel tituloSeccion(FontAwesomeSolid ico, String texto) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        p.setOpaque(false);
        p.add(new JLabel(FontIcon.of(ico, 18, AZUL_OSC)));
        JLabel l = new JLabel(texto);
        l.setFont(F_SECCION);
        l.setForeground(AZUL_OSC);
        p.add(l);
        return p;
    }

    // ===================================================================
    // 8. LISTA DE PLANILLAS (TABLA)
    // ===================================================================
    private JPanel crearListado() {
        RoundPanel card = new RoundPanel(Color.WHITE, BORDE, 14);
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(new EmptyBorder(12, 16, 14, 16));
        card.add(tituloSeccion(FontAwesomeSolid.FILE_ALT, "Lista de planillas"), BorderLayout.NORTH);

        modelo = new DefaultTableModel(COLUMNAS, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabla = new JTable(modelo);
        tabla.setFont(F_NORMAL);
        tabla.setRowHeight(34);
        tabla.setShowVerticalLines(true);
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(new Color(0xE2E8F0));
        tabla.setSelectionBackground(new Color(0xDBEAFE));
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setToolTipText("Doble clic: modificar  |  Clic derecho: cambiar estado");

        // Encabezado
        JTableHeader th = tabla.getTableHeader();
        th.setReorderingAllowed(false);
        th.setPreferredSize(new Dimension(0, 34));
        th.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, s, f, r, c);
                l.setBackground(new Color(0xDCE6F5));
                l.setForeground(AZUL_OSC);
                l.setFont(F_BOLD);
                l.setHorizontalAlignment(CENTER);
                l.setBorder(new MatteBorder(0, 0, 1, 1, new Color(0xC3D1E8)));
                return l;
            }
        });

        // Celdas con filas alternadas
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, s, f, r, c);
                if (!s) l.setBackground(r % 2 == 0 ? Color.WHITE : new Color(0xF8FAFD));
                l.setHorizontalAlignment(c == 1 || c == 3 || c == 4 ? LEFT : CENTER);
                l.setBorder(new EmptyBorder(0, 8, 0, 8));
                return l;
            }
        };
        for (int i = 0; i < COLUMNAS.length - 1; i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        tabla.getColumnModel().getColumn(COLUMNAS.length - 1).setCellRenderer(new EstadoRenderer());

        int[] anchos = {40, 150, 90, 130, 100, 110, 115, 100, 110, 110, 100};
        for (int i = 0; i < anchos.length; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        // Doble clic = modificar | Clic derecho = menú de estado
        final JPopupMenu menu = new JPopupMenu();
        JMenuItem miPagada = new JMenuItem("Marcar como pagada");
        miPagada.addActionListener(e -> cambiarEstadoSeleccionada(PAGADA));
        JMenuItem miCalculada = new JMenuItem("Marcar como calculada");
        miCalculada.addActionListener(e -> cambiarEstadoSeleccionada(CALCULADA));
        JMenuItem miPendiente = new JMenuItem("Marcar como pendiente");
        miPendiente.addActionListener(e -> cambiarEstadoSeleccionada(PENDIENTE));
        menu.add(miCalculada);
        menu.add(miPagada);
        menu.add(miPendiente);

        tabla.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e)  { popup(e); }
            @Override public void mouseReleased(MouseEvent e) { popup(e); }

            private void popup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int f = tabla.rowAtPoint(e.getPoint());
                    if (f >= 0) {
                        tabla.setRowSelectionInterval(f, f);
                        menu.show(tabla, e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    modificar();
                }
            }
        });

        JScrollPane sp = new JScrollPane(tabla);
        sp.setBorder(new LineBorder(new Color(0xC3D1E8)));
        sp.getViewport().setBackground(Color.WHITE);
        card.add(sp, BorderLayout.CENTER);
        return card;
    }

    /** Dibuja el estado como una "píldora" de color. */
    private static class EstadoRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
            final String estado = v == null ? "" : v.toString();
            final boolean sel = s;
            final int fila = r;
            JPanel p = new JPanel(new GridBagLayout()) {
                @Override protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Color bg, fg, bd;
                    switch (estado) {
                        case PENDIENTE: bg = new Color(0xFFE0B2); fg = new Color(0x9A5B00); bd = new Color(0xF5B85C); break;
                        case PAGADA:    bg = new Color(0xCDE6FB); fg = new Color(0x1E5A99); bd = new Color(0x8CC3F0); break;
                        default:        bg = new Color(0xC8F0D2); fg = new Color(0x1B6B34); bd = new Color(0x7FD39A);
                    }
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    int w = 84, h = 22;
                    int x = (getWidth() - w) / 2, y = (getHeight() - h) / 2;
                    g2.setColor(bg);
                    g2.fillRoundRect(x, y, w, h, h, h);
                    g2.setColor(bd);
                    g2.drawRoundRect(x, y, w, h, h, h);
                    g2.setColor(fg);
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    FontMetrics fm = g2.getFontMetrics();
                    g2.drawString(estado, x + (w - fm.stringWidth(estado)) / 2, y + (h + fm.getAscent() - fm.getDescent()) / 2);
                    g2.dispose();
                }
            };
            p.setBackground(sel ? new Color(0xDBEAFE) : (fila % 2 == 0 ? Color.WHITE : new Color(0xF8FAFD)));
            return p;
        }
    }

    // ===================================================================
    // 9. ACCIONES (BOTONES)
    // ===================================================================
    private JPanel crearAcciones() {
        RoundPanel card = new RoundPanel(Color.WHITE, BORDE, 14);
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(new EmptyBorder(12, 16, 14, 16));
        card.add(tituloSeccion(FontAwesomeSolid.COG, "Acciones"), BorderLayout.NORTH);

        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        fila.setOpaque(false);

        Color txtGris = new Color(0x4B5563);
        RoundButton nueva     = new RoundButton("Nueva Planilla",    FontAwesomeSolid.PLUS_CIRCLE, VERDE, Color.WHITE);
        RoundButton calcular  = new RoundButton("Calcular Planilla", FontAwesomeSolid.CALCULATOR,  AZUL, Color.WHITE);
        RoundButton modificar = new RoundButton("Modificar",         FontAwesomeSolid.PENCIL_ALT,  AMARILLO, Color.WHITE);
        RoundButton eliminar  = new RoundButton("Eliminar",          FontAwesomeSolid.TRASH,       ROJO, Color.WHITE);
        RoundButton boleta    = new RoundButton("Generar Boleta",    FontAwesomeSolid.FILE_ALT,    GRIS_BTN, txtGris);
        RoundButton excel     = new RoundButton("Exportar Excel",    FontAwesomeSolid.FILE_EXCEL,  GRIS_BTN, txtGris);
        RoundButton pdf       = new RoundButton("Exportar PDF",      FontAwesomeSolid.FILE_PDF,    GRIS_BTN, txtGris);
        RoundButton imprimir  = new RoundButton("Imprimir",          FontAwesomeSolid.PRINT,       GRIS_BTN, txtGris);

        nueva.addActionListener(e -> abrirFormulario(null));
        calcular.addActionListener(e -> calcularPlanilla());
        modificar.addActionListener(e -> modificar());
        eliminar.addActionListener(e -> eliminar());
        boleta.addActionListener(e -> generarBoleta());
        excel.addActionListener(e -> exportarExcel());
        pdf.addActionListener(e -> exportarPdf());
        imprimir.addActionListener(e -> imprimir());

        fila.add(nueva);
        fila.add(calcular);
        fila.add(modificar);
        fila.add(eliminar);

        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setPreferredSize(new Dimension(2, 40));
        sep.setForeground(new Color(0xD1D5DB));
        fila.add(sep);

        fila.add(boleta);
        fila.add(excel);
        fila.add(pdf);
        fila.add(imprimir);

        card.add(fila, BorderLayout.CENTER);
        return card;
    }

    // ===================================================================
    // 10. DATOS (>>> BD <<< aquí se conecta a la base de datos)
    // ===================================================================
    /** >>> BD <<< Aquí iría el SELECT a la vista/tabla de planillas. Por ahora son datos de ejemplo. */
    private void cargarDatos() {
        agregarDemo(9, 2026, "Juan Pérez García",   "12345678", "Sistemas",         "Analista",     2500, 300, 250, CALCULADA);
        agregarDemo(9, 2026, "María López Torres",  "87654321", "Administración",   "Asistente",    1800, 200, 180, CALCULADA);
        agregarDemo(9, 2026, "Carlos Ruiz Mendoza", "11223344", "Recursos Humanos", "Especialista", 2200, 300, 220, PENDIENTE);
        agregarDemo(9, 2026, "Ana Castro Vargas",   "99887766", "Contabilidad",     "Analista",     2000, 250, 200, CALCULADA);
        agregarDemo(9, 2026, "Luis Torres Rojas",   "44332211", "Logística",        "Asistente",    1700, 150, 170, PAGADA);
    }

    private void agregarDemo(int mes, int anio, String emp, String dni, String dep, String puesto,
                             double sueldo, double bonif, double desc, String estado) {
        Planilla p = new Planilla();
        p.id = siguienteId++;
        p.mes = mes;
        p.anio = anio;
        p.empleado = emp;
        p.dni = dni;
        p.departamento = dep;
        p.puesto = puesto;
        p.sueldo = sueldo;
        p.bonif = bonif;
        p.desc = desc;
        p.estado = estado;
        todas.add(p);
    }

    // ===================================================================
    // 11. REFRESCAR TABLA + FILTROS + TARJETAS
    // ===================================================================
    private int mesSeleccionado()  { return cboMes.getSelectedIndex(); }
    private int anioSeleccionado() { return Integer.parseInt((String) cboAnio.getSelectedItem()); }
    private String periodoTexto()  { return MESES[mesSeleccionado()] + " " + anioSeleccionado(); }

    private void refrescarTabla() {
        int mes = mesSeleccionado();
        int anio = anioSeleccionado();
        String dep = (String) cboDepartamento.getSelectedItem();
        String est = (String) cboEstado.getSelectedItem();
        String txt = txtBuscar.getText().trim().toLowerCase();

        modelo.setRowCount(0);
        visibles.clear();

        int empleadosPeriodo = 0, generadas = 0;
        double totalPagar = 0;

        for (Planilla p : todas) {
            if (p.mes != mes || p.anio != anio) continue;

            // Tarjetas: cuentan todo el periodo, sin importar los otros filtros
            empleadosPeriodo++;
            if (!p.estado.equals(PENDIENTE)) generadas++;
            if (p.estado.equals(CALCULADA)) totalPagar += p.neto();

            // Filtros de la tabla
            if (!"Todos".equals(dep) && !p.departamento.equals(dep)) continue;
            if (!"Todos".equals(est) && !p.estado.equals(est)) continue;
            if (!txt.isEmpty() && !p.dni.contains(txt) && !p.empleado.toLowerCase().contains(txt)) continue;

            visibles.add(p);
            modelo.addRow(new Object[]{
                p.id, p.empleado, p.dni, p.departamento, p.puesto,
                dinero(p.sueldo), dinero(p.bonif), dinero(p.desc),
                dinero(p.bruto()), dinero(p.neto()), p.estado
            });
        }

        lblEmpleados.setText(String.valueOf(empleadosPeriodo));
        lblPeriodo.setText(periodoTexto());
        lblGeneradas.setText(String.valueOf(generadas));
        lblTotal.setText(dinero(totalPagar));
    }

    private void limpiarFiltros() {
        cboMes.setSelectedItem("Octubre");
        cboAnio.setSelectedItem("2026");
        cboDepartamento.setSelectedIndex(0);
        cboEstado.setSelectedIndex(0);
        txtBuscar.setText("");
        refrescarTabla();
    }

    // ===================================================================
    // 12. NUEVA / MODIFICAR (formulario)
    // ===================================================================
    private void modificar() {
        Planilla p = seleccionada(true);
        if (p == null) return;
        if (p.estado.equals(PAGADA)) {
            aviso("Una planilla pagada no se puede modificar.");
            return;
        }
        abrirFormulario(p);
    }

    /** Si "editar" es null crea una planilla nueva; si no, modifica la que llega. */
    private void abrirFormulario(final Planilla editar) {
        final JDialog d = new JDialog(SwingUtilities.getWindowAncestor(this),
                editar == null ? "Nueva planilla" : "Modificar planilla",
                Dialog.ModalityType.APPLICATION_MODAL);

        final JTextField txtEmp = new JTextField(editar != null ? editar.empleado : "", 22);
        final JTextField txtDni = new JTextField(editar != null ? editar.dni : "", 22);
        final JComboBox<String> cboDep = new JComboBox<>(DEPARTAMENTOS);
        final JTextField txtPuesto = new JTextField(editar != null ? editar.puesto : "", 22);
        final JTextField txtSueldo = new JTextField(editar != null ? formato(editar.sueldo) : "", 22);
        final JTextField txtBonif = new JTextField(editar != null ? formato(editar.bonif) : "0.00", 22);
        final JTextField txtDesc = new JTextField(editar != null ? formato(editar.desc) : "0.00", 22);
        if (editar != null) cboDep.setSelectedItem(editar.departamento);

        final JLabel lblBruto = new JLabel("S/ 0.00");
        final JLabel lblNeto = new JLabel("S/ 0.00");
        lblBruto.setFont(F_BOLD);
        lblNeto.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNeto.setForeground(VERDE);

        // Vista previa del total bruto y neto mientras se escribe
        final Runnable calc = () -> {
            double s = numSeguro(txtSueldo.getText());
            double b = numSeguro(txtBonif.getText());
            double ds = numSeguro(txtDesc.getText());
            lblBruto.setText(dinero(s + b));
            lblNeto.setText(dinero(s + b - ds));
        };
        DocumentListener dl = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e)  { calc.run(); }
            @Override public void removeUpdate(DocumentEvent e)  { calc.run(); }
            @Override public void changedUpdate(DocumentEvent e) { calc.run(); }
        };
        txtSueldo.getDocument().addDocumentListener(dl);
        txtBonif.getDocument().addDocumentListener(dl);
        txtDesc.getDocument().addDocumentListener(dl);
        calc.run();

        JPanel cont = new JPanel(new GridBagLayout());
        cont.setBackground(Color.WHITE);
        cont.setBorder(new EmptyBorder(18, 22, 8, 22));

        String periodo = editar != null ? MESES[editar.mes] + " " + editar.anio : periodoTexto();
        JLabel lblPer = new JLabel("Periodo: " + periodo);
        lblPer.setFont(F_BOLD);
        lblPer.setForeground(AZUL_OSC);
        GridBagConstraints g0 = new GridBagConstraints();
        g0.gridx = 0; g0.gridy = 0; g0.gridwidth = 2; g0.anchor = GridBagConstraints.WEST;
        g0.insets = new Insets(0, 0, 10, 0);
        cont.add(lblPer, g0);

        String[] etiquetas = {"Empleado:", "DNI:", "Departamento:", "Puesto:",
            "Sueldo básico (S/):", "Bonificaciones (S/):", "Descuentos (S/):",
            "Total bruto:", "Neto a pagar:"};
        JComponent[] campos = {txtEmp, txtDni, cboDep, txtPuesto, txtSueldo, txtBonif, txtDesc, lblBruto, lblNeto};
        for (int i = 0; i < etiquetas.length; i++) {
            GridBagConstraints gc = new GridBagConstraints();
            gc.gridy = i + 1;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(5, 0, 5, 14);
            gc.gridx = 0;
            cont.add(etiqueta(etiquetas[i]), gc);
            gc.gridx = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.weightx = 1;
            gc.insets = new Insets(5, 0, 5, 0);
            if (campos[i] instanceof JTextField || campos[i] instanceof JComboBox) {
                campos[i].setFont(F_NORMAL);
                campos[i].setPreferredSize(new Dimension(230, 32));
            }
            cont.add(campos[i], gc);
        }

        RoundButton btnGuardar = new RoundButton("Guardar", FontAwesomeSolid.SAVE, VERDE, Color.WHITE);
        RoundButton btnCancelar = new RoundButton("Cancelar", FontAwesomeSolid.TIMES, GRIS_BTN2, Color.WHITE);
        btnCancelar.addActionListener(e -> d.dispose());

        btnGuardar.addActionListener(e -> {
            String emp = txtEmp.getText().trim();
            String dni = txtDni.getText().trim();
            String pue = txtPuesto.getText().trim();

            if (emp.isEmpty()) { error(d, "Ingrese el nombre del empleado."); txtEmp.requestFocus(); return; }
            if (!dni.matches("\\d{8}")) { error(d, "El DNI debe tener 8 dígitos."); txtDni.requestFocus(); return; }
            if (pue.isEmpty()) { error(d, "Ingrese el puesto."); txtPuesto.requestFocus(); return; }

            double s, b, ds;
            try {
                s = num(txtSueldo.getText());
                b = num(txtBonif.getText());
                ds = num(txtDesc.getText());
            } catch (NumberFormatException ex) {
                error(d, "Sueldo, bonificaciones y descuentos deben ser números válidos (ej: 2500.00).");
                return;
            }
            if (s <= 0) { error(d, "El sueldo básico debe ser mayor que cero."); txtSueldo.requestFocus(); return; }
            if (b < 0 || ds < 0) { error(d, "Los montos no pueden ser negativos."); return; }
            if (ds > s + b) { error(d, "Los descuentos no pueden superar el total bruto."); txtDesc.requestFocus(); return; }

            int mes = editar != null ? editar.mes : mesSeleccionado();
            int anio = editar != null ? editar.anio : anioSeleccionado();
            for (Planilla o : todas) {
                if (o != editar && o.mes == mes && o.anio == anio && o.dni.equals(dni)) {
                    error(d, "Ese empleado ya tiene una planilla en " + MESES[mes] + " " + anio + ".");
                    return;
                }
            }

            // >>> BD <<< aquí va el INSERT (nueva) o el UPDATE (modificar)
            Planilla p = editar != null ? editar : new Planilla();
            if (editar == null) {
                p.id = siguienteId++;
                p.mes = mes;
                p.anio = anio;
                todas.add(p);
            }
            p.empleado = emp;
            p.dni = dni;
            p.departamento = (String) cboDep.getSelectedItem();
            p.puesto = pue;
            p.sueldo = s;
            p.bonif = b;
            p.desc = ds;
            p.estado = PENDIENTE;      // al guardar, hay que volver a calcularla

            d.dispose();
            refrescarTabla();
            seleccionarFilaDe(p);
        });

        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pie.setBackground(Color.WHITE);
        pie.setBorder(new EmptyBorder(0, 12, 8, 12));
        btnGuardar.setPreferredSize(new Dimension(115, 36));
        btnCancelar.setPreferredSize(new Dimension(115, 36));
        pie.add(btnGuardar);
        pie.add(btnCancelar);

        d.getContentPane().setLayout(new BorderLayout());
        d.getContentPane().add(cont, BorderLayout.CENTER);
        d.getContentPane().add(pie, BorderLayout.SOUTH);
        d.getRootPane().setDefaultButton(btnGuardar);
        d.pack();
        d.setResizable(false);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }

    // ===================================================================
    // 13. CALCULAR / ELIMINAR / CAMBIAR ESTADO
    // ===================================================================
    /** Pasa a "Calculada" la planilla seleccionada; si no hay ninguna, todas las pendientes de la lista. */
    private void calcularPlanilla() {
        List<Planilla> objetivo = new ArrayList<>();
        Planilla sel = seleccionada(false);
        if (sel != null) objetivo.add(sel);
        else objetivo.addAll(visibles);

        int n = 0;
        for (Planilla p : objetivo) {
            if (p.estado.equals(PENDIENTE)) {
                // >>> BD <<< aquí iría el UPDATE del estado (o el procedimiento almacenado de cálculo)
                p.estado = CALCULADA;
                n++;
            }
        }
        if (n == 0) {
            aviso("No hay planillas pendientes para calcular.");
            return;
        }
        refrescarTabla();
        aviso("Se calcularon " + n + " planilla(s).\n"
                + "Clic derecho sobre una fila para marcarla como pagada.");
    }

    private void cambiarEstadoSeleccionada(String nuevo) {
        Planilla p = seleccionada(true);
        if (p == null) return;
        if (nuevo.equals(PAGADA) && p.estado.equals(PENDIENTE)) {
            aviso("Primero calcule la planilla antes de marcarla como pagada.");
            return;
        }
        // >>> BD <<< aquí iría el UPDATE del estado
        p.estado = nuevo;
        refrescarTabla();
        seleccionarFilaDe(p);
    }

    private void eliminar() {
        Planilla p = seleccionada(true);
        if (p == null) return;
        if (p.estado.equals(PAGADA)) {
            aviso("No se puede eliminar una planilla pagada.");
            return;
        }
        int op = JOptionPane.showConfirmDialog(this,
                "¿Eliminar la planilla de " + p.empleado + " (" + MESES[p.mes] + " " + p.anio + ")?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {
            // >>> BD <<< aquí iría el DELETE
            todas.remove(p);
            refrescarTabla();
        }
    }

    // ===================================================================
    // 14. BOLETA, EXCEL, PDF E IMPRESIÓN
    // ===================================================================
    private void generarBoleta() {
        Planilla p = seleccionada(true);
        if (p == null) return;
        if (p.estado.equals(PENDIENTE)) {
            aviso("Calcule la planilla antes de generar la boleta.");
            return;
        }

        String html = "<html><body style='font-family:Segoe UI; font-size:12px; width:430px;'>"
                + "<h2 style='text-align:center; color:#0F2A5C;'>SENATI</h2>"
                + "<h3 style='text-align:center;'>BOLETA DE PAGO - " + MESES[p.mes].toUpperCase() + " " + p.anio + "</h3>"
                + "<hr>"
                + "<table cellpadding='3'>"
                + "<tr><td><b>Empleado:</b></td><td>" + p.empleado + "</td></tr>"
                + "<tr><td><b>DNI:</b></td><td>" + p.dni + "</td></tr>"
                + "<tr><td><b>Departamento:</b></td><td>" + p.departamento + "</td></tr>"
                + "<tr><td><b>Puesto:</b></td><td>" + p.puesto + "</td></tr>"
                + "</table><br>"
                + "<table border='1' cellpadding='5' cellspacing='0' width='100%'>"
                + "<tr bgcolor='#DCE6F5'><td colspan='2'><b>INGRESOS</b></td></tr>"
                + "<tr><td>Sueldo básico</td><td align='right'>" + dinero(p.sueldo) + "</td></tr>"
                + "<tr><td>Bonificaciones</td><td align='right'>" + dinero(p.bonif) + "</td></tr>"
                + "<tr><td><b>Total bruto</b></td><td align='right'><b>" + dinero(p.bruto()) + "</b></td></tr>"
                + "<tr bgcolor='#DCE6F5'><td colspan='2'><b>DESCUENTOS</b></td></tr>"
                + "<tr><td>Descuentos de ley</td><td align='right'>" + dinero(p.desc) + "</td></tr>"
                + "<tr bgcolor='#C8F0D2'><td><b>NETO A PAGAR</b></td><td align='right'><b>" + dinero(p.neto()) + "</b></td></tr>"
                + "</table><br><br>"
                + "<p style='text-align:center;'>Estado: " + p.estado + "</p>"
                + "</body></html>";

        final JEditorPane ep = new JEditorPane("text/html", html);
        ep.setEditable(false);

        final JDialog d = new JDialog(SwingUtilities.getWindowAncestor(this),
                "Boleta de pago", Dialog.ModalityType.APPLICATION_MODAL);
        RoundButton btnImp = new RoundButton("Imprimir", FontAwesomeSolid.PRINT, AZUL, Color.WHITE);
        RoundButton btnCerrar = new RoundButton("Cerrar", FontAwesomeSolid.TIMES, GRIS_BTN2, Color.WHITE);
        btnImp.setPreferredSize(new Dimension(115, 36));
        btnCerrar.setPreferredSize(new Dimension(115, 36));
        btnImp.addActionListener(e -> {
            try {
                ep.print();
            } catch (Exception ex) {
                aviso("No se pudo imprimir: " + ex.getMessage());
            }
        });
        btnCerrar.addActionListener(e -> d.dispose());

        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pie.add(btnImp);
        pie.add(btnCerrar);

        d.getContentPane().setLayout(new BorderLayout());
        d.getContentPane().add(new JScrollPane(ep), BorderLayout.CENTER);
        d.getContentPane().add(pie, BorderLayout.SOUTH);
        d.setSize(500, 520);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }

    /** Guarda un archivo .csv que se abre directo en Excel. */
    private void exportarExcel() {
        if (visibles.isEmpty()) {
            aviso("No hay planillas en la lista para exportar.");
            return;
        }
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Guardar para Excel");
        fc.setFileFilter(new FileNameExtensionFilter("Archivo para Excel (*.csv)", "csv"));
        fc.setSelectedFile(new File("Planillas_" + MESES[mesSeleccionado()] + "_" + anioSeleccionado() + ".csv"));
        if (fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File f = fc.getSelectedFile();
        if (!f.getName().toLowerCase().endsWith(".csv")) {
            f = new File(f.getParentFile(), f.getName() + ".csv");
        }
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8))) {
            pw.print('\uFEFF');                       // para que Excel lea bien las tildes
            pw.println("sep=,");
            pw.println("ID,Empleado,DNI,Departamento,Puesto,Sueldo Básico,Bonificaciones,Descuentos,Total Bruto,Neto a Pagar,Estado");
            for (Planilla p : visibles) {
                pw.println(p.id + "," + csv(p.empleado) + "," + csv(p.dni) + "," + csv(p.departamento) + ","
                        + csv(p.puesto) + "," + formato(p.sueldo) + "," + formato(p.bonif) + ","
                        + formato(p.desc) + "," + formato(p.bruto()) + "," + formato(p.neto()) + "," + p.estado);
            }
            aviso("Archivo guardado en:\n" + f.getAbsolutePath());
        } catch (Exception ex) {
            error(this, "No se pudo guardar el archivo: " + ex.getMessage());
        }
    }

    /** Sin librerías extra: se usa la impresión de Windows con la impresora "Microsoft Print to PDF". */
    private void exportarPdf() {
        if (visibles.isEmpty()) {
            aviso("No hay planillas en la lista para exportar.");
            return;
        }
        aviso("En la ventana que sigue, elija la impresora \"Microsoft Print to PDF\"\n"
                + "y pulse Imprimir para guardar el archivo PDF.");
        imprimir();
    }

    private void imprimir() {
        if (visibles.isEmpty()) {
            aviso("No hay planillas en la lista para imprimir.");
            return;
        }
        try {
            tabla.print(JTable.PrintMode.FIT_WIDTH,
                    new MessageFormat("Planillas - " + periodoTexto()),
                    new MessageFormat("Página {0}"));
        } catch (java.awt.print.PrinterException ex) {
            error(this, "No se pudo imprimir: " + ex.getMessage());
        }
    }

    // ===================================================================
    // 15. UTILIDADES
    // ===================================================================
    /** Devuelve la planilla de la fila elegida (o null). */
    private Planilla seleccionada(boolean avisar) {
        int f = tabla.getSelectedRow();
        if (f < 0 || f >= visibles.size()) {
            if (avisar) aviso("Seleccione una planilla de la lista.");
            return null;
        }
        return visibles.get(f);
    }

    private void seleccionarFilaDe(Planilla p) {
        int i = visibles.indexOf(p);
        if (i >= 0) {
            tabla.setRowSelectionInterval(i, i);
            tabla.scrollRectToVisible(tabla.getCellRect(i, 0, true));
        }
    }

    private static String dinero(double v) {
        return String.format(Locale.US, "S/ %,.2f", v);
    }

    private static String formato(double v) {
        return String.format(Locale.US, "%.2f", v);
    }

    private static double num(String s) {
        String t = s.trim().replace("S/", "").replace(",", "").trim();
        if (t.isEmpty()) return 0;
        return Double.parseDouble(t);
    }

    private static double numSeguro(String s) {
        try { return num(s); } catch (NumberFormatException e) { return 0; }
    }

    private static String csv(String s) {
        return "\"" + s.replace("\"", "\"\"") + "\"";
    }

    private void aviso(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Planillas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void error(Component padre, String msg) {
        JOptionPane.showMessageDialog(padre, msg, "Validación", JOptionPane.WARNING_MESSAGE);
    }

    // ===================================================================
    // 16. COMPONENTES AUXILIARES (panel y botón redondeados)
    // ===================================================================
    private static class RoundPanel extends JPanel {
        private final Color fill, line;
        private final int arc;

        RoundPanel(Color fill, Color line, int arc) {
            this.fill = fill;
            this.line = line;
            this.arc = arc;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
            if (line != null) {
                g2.setColor(line);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class RoundButton extends JButton {
        private final Color base;
        private boolean hover;

        RoundButton(String texto, FontAwesomeSolid ico, Color base, Color fg) {
            super(texto);
            this.base = base;
            setIcon(FontIcon.of(ico, 16, fg));
            setForeground(fg);
            setFont(F_BOLD);
            setIconTextGap(8);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(8, 14, 8, 14));
            addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { hover = true; repaint(); }
                @Override public void mouseExited(MouseEvent e)  { hover = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(hover ? base.darker() : base);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}