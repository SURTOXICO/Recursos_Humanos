package proyecto_recursoshumanos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 * Pantalla "Personal" del Sistema de Recursos Humanos SENATI.
 *
 * Es un JPanel para incrustarlo en el área de contenido de Frm_Menu_Principal
 * (a la derecha del sidebar). También se puede probar sola con Shift+F6.
 */
public class Frm_Personal extends JPanel {

    // ---- Si los iconos de /img no se ven bien sobre los botones de color, ponlo en false
    private static final boolean USAR_ICONOS = true;

    // ---- Colores (tomados del mockup)
    private static final Color AZUL_OSCURO = new Color(0x0B2E7A);
    private static final Color AZUL = new Color(0x1F6FEB);
    private static final Color AZUL_BUSCAR = new Color(0x2563EB);
    private static final Color VERDE = new Color(0x28A745);
    private static final Color ROJO = new Color(0xDC3545);
    private static final Color GRIS = new Color(0x6B7280);
    private static final Color FONDO = new Color(0xF1F5FB);
    private static final Color BORDE = new Color(0xD9E2F0);
    private static final Color ENCABEZADO_TABLA = new Color(0xDCE8F8);
    private static final Color FILA_ALTERNA = new Color(0xF4F7FC);

    private static final Font FUENTE = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FUENTE_NEGRITA = new Font("Segoe UI", Font.BOLD, 13);

    private static final DateTimeFormatter FORMATO_FECHA
            = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private static final String[] COLUMNAS = {
        "ID", "DNI", "Nombres", "Apellidos", "Cargo", "Departamento", "Fecha Ingreso", "Estado"
    };

    private static final String[] DEPARTAMENTOS = {
        "Mantenimiento", "Sistemas", "Producción", "Administración", "Recursos Humanos", "Capacitaciones"
    };

    // ---- Componentes
    private JTextField txtBuscar;
    private JComboBox<String> cboDepartamento;
    private JComboBox<String> cboEstado;
    private JTable tabla;
    private DefaultTableModel modelo;
    private TableRowSorter<DefaultTableModel> sorter;

    public Frm_Personal() {
        setLayout(new BorderLayout(15, 0));
        setBackground(FONDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        add(crearPanelCentral(), BorderLayout.CENTER);
        add(crearPanelAcciones(), BorderLayout.EAST);

        cargarDatos();
    }

    // =====================================================================
    //  CONSTRUCCIÓN DE LA INTERFAZ
    // =====================================================================
    private JPanel crearPanelCentral() {
        JPanel centro = new JPanel(new BorderLayout(0, 12));
        centro.setOpaque(false);

        JPanel superior = new JPanel(new BorderLayout(0, 12));
        superior.setOpaque(false);
        superior.add(crearEncabezado(), BorderLayout.NORTH);
        superior.add(crearBusqueda(), BorderLayout.CENTER);

        centro.add(superior, BorderLayout.NORTH);
        centro.add(crearPanelTabla(), BorderLayout.CENTER);
        return centro;
    }

    private JPanel crearEncabezado() {
        PanelRedondeado p = new PanelRedondeado(new BorderLayout(18, 0), true);
        p.setBorder(new EmptyBorder(14, 18, 14, 18));
        p.add(new IconoPersonal(), BorderLayout.WEST);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Personal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(AZUL_OSCURO);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitulo = new JLabel("Gestiona la información del personal de la institución.");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitulo.setForeground(GRIS);
        subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        textos.add(Box.createVerticalGlue());
        textos.add(titulo);
        textos.add(subtitulo);
        textos.add(Box.createVerticalGlue());

        p.add(textos, BorderLayout.CENTER);
        return p;
    }

    private JPanel crearBusqueda() {
        PanelRedondeado p = new PanelRedondeado(new GridBagLayout(), false);

        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(0, 0, 4, 15);

        // Etiquetas
        gc.gridy = 0;
        gc.weightx = 0;
        gc.gridx = 0;
        p.add(etiqueta("Buscar:"), gc);
        gc.gridx = 1;
        p.add(etiqueta("Departamento:"), gc);
        gc.gridx = 2;
        p.add(etiqueta("Estado:"), gc);

        // Campo de búsqueda con texto de ayuda
        txtBuscar = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && !isFocusOwner()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2.setColor(new Color(0x9CA3AF));
                    g2.setFont(getFont());
                    int y = (getHeight() - g2.getFontMetrics().getHeight()) / 2 + g2.getFontMetrics().getAscent();
                    g2.drawString("Nombre, DNI o cargo...", getInsets().left, y);
                    g2.dispose();
                }
            }
        };
        txtBuscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtBuscar.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtBuscar.repaint();
            }
        });
        estilizarCampo(txtBuscar);
        txtBuscar.addActionListener(e -> aplicarFiltros()); // Enter busca

        cboDepartamento = new JComboBox<>();
        cboDepartamento.addItem("Todos");
        for (String d : DEPARTAMENTOS) {
            cboDepartamento.addItem(d);
        }
        estilizarCombo(cboDepartamento);

        cboEstado = new JComboBox<>(new String[]{"Todos", "Activo", "Inactivo"});
        estilizarCombo(cboEstado);

        BotonRedondeado btnBuscar = new BotonRedondeado("Buscar", AZUL_BUSCAR, icono("Buscar.png"));
        btnBuscar.setPreferredSize(new Dimension(130, 38));
        btnBuscar.addActionListener(e -> aplicarFiltros());

        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 15);
        gc.gridx = 0;
        gc.weightx = 0.45;
        p.add(txtBuscar, gc);
        gc.gridx = 1;
        gc.weightx = 0.30;
        p.add(cboDepartamento, gc);
        gc.gridx = 2;
        gc.weightx = 0.25;
        p.add(cboEstado, gc);
        gc.gridx = 3;
        gc.weightx = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        p.add(btnBuscar, gc);

        return p;
    }

    private JPanel crearPanelTabla() {
        PanelRedondeado p = new PanelRedondeado(new BorderLayout(0, 10), false);

        JLabel titulo = new JLabel("Lista de Personal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(AZUL_OSCURO);
        p.add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel(COLUMNAS, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        configurarTabla();

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(BORDE));
        scroll.getViewport().setBackground(Color.WHITE);
        p.add(scroll, BorderLayout.CENTER);
        return p;
    }

    private void configurarTabla() {
        tabla.setFont(FUENTE);
        tabla.setRowHeight(30);
        tabla.setShowGrid(true);
        tabla.setGridColor(BORDE);
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setSelectionBackground(new Color(0xC7DBF7));
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        // Encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(0, 34));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object valor,
                    boolean sel, boolean foco, int fila, int col) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, valor, false, false, fila, col);
                l.setOpaque(true);
                l.setBackground(ENCABEZADO_TABLA);
                l.setForeground(AZUL_OSCURO);
                l.setFont(FUENTE_NEGRITA);
                l.setHorizontalAlignment(SwingConstants.LEFT);
                l.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 1, BORDE),
                        new EmptyBorder(0, 8, 0, 8)));
                return l;
            }
        });

        // Celdas con filas alternadas
        DefaultTableCellRenderer celda = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object valor,
                    boolean sel, boolean foco, int fila, int col) {
                super.getTableCellRendererComponent(t, valor, sel, false, fila, col);
                setHorizontalAlignment(SwingConstants.LEFT);
                setBorder(new EmptyBorder(0, 8, 0, 8));
                if (!sel) {
                    setBackground(fila % 2 == 0 ? Color.WHITE : FILA_ALTERNA);
                }
                return this;
            }
        };

        TableColumnModel cm = tabla.getColumnModel();
        int[] anchos = {40, 90, 100, 130, 190, 130, 110, 90};
        for (int i = 0; i < cm.getColumnCount(); i++) {
            cm.getColumn(i).setPreferredWidth(anchos[i]);
            cm.getColumn(i).setCellRenderer(i == 7 ? new EstadoRenderer() : celda);
        }

        // Doble clic = Modificar
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tabla.getSelectedRow() != -1) {
                    modificar();
                }
            }
        });
    }

    private JPanel crearPanelAcciones() {
        PanelRedondeado card = new PanelRedondeado(new BorderLayout(), false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel titulo = new JLabel("Acciones");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(AZUL_OSCURO);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titulo);
        card.add(Box.createVerticalStrut(12));
        card.add(crearBoton("Nuevo", AZUL, icono("Nuevo.png"), e -> nuevo()));
        card.add(Box.createVerticalStrut(10));
        card.add(crearBoton("Modificar", VERDE, icono("Modificar.png"), e -> modificar()));
        card.add(Box.createVerticalStrut(10));
        card.add(crearBoton("Desactivar", ROJO, null, e -> desactivar()));
        card.add(Box.createVerticalStrut(10));
        card.add(crearBoton("Limpiar", GRIS, null, e -> limpiar()));

        JPanel envoltura = new JPanel(new BorderLayout());
        envoltura.setOpaque(false);
        envoltura.setPreferredSize(new Dimension(190, 0));
        envoltura.add(card, BorderLayout.NORTH);
        return envoltura;
    }

    // =====================================================================
    //  DATOS  (por ahora de ejemplo; aquí se conectará a la base de datos)
    // =====================================================================
    private void cargarDatos() {
        modelo.setRowCount(0);
        Object[][] datos = {
            {1, "74256123", "Carlos", "Mendoza Rojas", "Técnico de Mantenimiento", "Mantenimiento", "15/04/2025", "Activo"},
            {2, "71543210", "Ana", "López Torres", "Analista de Sistemas", "Sistemas", "14/04/2025", "Activo"},
            {3, "73651987", "Luis", "Ramírez Díaz", "Operario de Producción", "Producción", "12/04/2025", "Activo"},
            {4, "73485211", "Patricia", "Gómez Silva", "Asistente Administrativa", "Administración", "10/04/2025", "Activo"},
            {5, "71236548", "Jorge", "Flores Quispe", "Supervisor de Planta", "Producción", "08/04/2025", "Activo"},
            {6, "70321456", "María", "Torres Vargas", "Recursos Humanos", "Recursos Humanos", "05/04/2025", "Activo"},
            {7, "76543210", "José", "Cruz Mendoza", "Técnico de Soporte", "Sistemas", "02/04/2025", "Inactivo"},
            {8, "70123456", "Claudia", "Pérez Herrera", "Asistente de RR.HH.", "Recursos Humanos", "01/04/2025", "Activo"},
            {9, "78965412", "Fernando", "Quispe Ayala", "Operario de Producción", "Producción", "28/03/2025", "Activo"},
            {10, "72784561", "Gabriela", "Navarro Ruiz", "Analista de Capacitación", "Capacitaciones", "25/03/2025", "Activo"}
        };
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
    }

    // =====================================================================
    //  ACCIONES
    // =====================================================================
    private void aplicarFiltros() {
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        String texto = txtBuscar.getText().trim();
        if (!texto.isEmpty()) {
            // Busca en DNI (1), Nombres (2), Apellidos (3) y Cargo (4)
            filtros.add(RowFilter.regexFilter("(?i)" + Pattern.quote(texto), 1, 2, 3, 4));
        }

        String depto = (String) cboDepartamento.getSelectedItem();
        if (depto != null && !"Todos".equals(depto)) {
            filtros.add(RowFilter.regexFilter("^" + Pattern.quote(depto) + "$", 5));
        }

        String estado = (String) cboEstado.getSelectedItem();
        if (estado != null && !"Todos".equals(estado)) {
            filtros.add(RowFilter.regexFilter("^" + Pattern.quote(estado) + "$", 7));
        }

        sorter.setRowFilter(filtros.isEmpty() ? null : RowFilter.andFilter(filtros));
    }

    private void limpiar() {
        txtBuscar.setText("");
        cboDepartamento.setSelectedIndex(0);
        cboEstado.setSelectedIndex(0);
        sorter.setRowFilter(null);
        tabla.clearSelection();
    }

    private void nuevo() {
        String[] d = pedirDatos("Nuevo personal", null, -1);
        if (d == null) {
            return;
        }
        modelo.addRow(new Object[]{siguienteId(), d[0], d[1], d[2], d[3], d[4], d[5], d[6]});
    }

    private void modificar() {
        int vista = tabla.getSelectedRow();
        if (vista == -1) {
            aviso("Seleccione un registro de la lista.");
            return;
        }
        int fila = tabla.convertRowIndexToModel(vista);

        String[] actual = new String[7];
        for (int i = 0; i < 7; i++) {
            actual[i] = String.valueOf(modelo.getValueAt(fila, i + 1));
        }

        String[] d = pedirDatos("Modificar personal", actual, fila);
        if (d == null) {
            return;
        }
        for (int i = 0; i < 7; i++) {
            modelo.setValueAt(d[i], fila, i + 1);
        }
    }

    private void desactivar() {
        int vista = tabla.getSelectedRow();
        if (vista == -1) {
            aviso("Seleccione un registro de la lista.");
            return;
        }
        int fila = tabla.convertRowIndexToModel(vista);

        if ("Inactivo".equals(modelo.getValueAt(fila, 7))) {
            aviso("Esta persona ya está inactiva.");
            return;
        }
        String nombre = modelo.getValueAt(fila, 2) + " " + modelo.getValueAt(fila, 3);
        int r = JOptionPane.showConfirmDialog(this, "¿Desea desactivar a " + nombre + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (r == JOptionPane.YES_OPTION) {
            modelo.setValueAt("Inactivo", fila, 7);
        }
    }

    /**
     * Muestra el formulario de alta/edición.
     * Devuelve {dni, nombres, apellidos, cargo, departamento, fecha, estado} o null si se cancela.
     */
    private String[] pedirDatos(String titulo, String[] actual, int filaEditando) {
        JTextField txtDni = new JTextField(actual == null ? "" : actual[0]);
        JTextField txtNombres = new JTextField(actual == null ? "" : actual[1]);
        JTextField txtApellidos = new JTextField(actual == null ? "" : actual[2]);
        JTextField txtCargo = new JTextField(actual == null ? "" : actual[3]);
        JComboBox<String> cboDep = new JComboBox<>(DEPARTAMENTOS);
        if (actual != null) {
            cboDep.setSelectedItem(actual[4]);
        }
        JTextField txtFecha = new JTextField(actual == null ? LocalDate.now().format(FORMATO_FECHA) : actual[5]);
        JComboBox<String> cboEst = new JComboBox<>(new String[]{"Activo", "Inactivo"});
        if (actual != null) {
            cboEst.setSelectedItem(actual[6]);
        }

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("DNI:"));
        form.add(txtDni);
        form.add(new JLabel("Nombres:"));
        form.add(txtNombres);
        form.add(new JLabel("Apellidos:"));
        form.add(txtApellidos);
        form.add(new JLabel("Cargo:"));
        form.add(txtCargo);
        form.add(new JLabel("Departamento:"));
        form.add(cboDep);
        form.add(new JLabel("Fecha ingreso (dd/MM/aaaa):"));
        form.add(txtFecha);
        form.add(new JLabel("Estado:"));
        form.add(cboEst);

        while (true) {
            int op = JOptionPane.showConfirmDialog(this, form, titulo,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (op != JOptionPane.OK_OPTION) {
                return null;
            }

            String dni = txtDni.getText().trim();
            String nombres = txtNombres.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            String cargo = txtCargo.getText().trim();
            String fecha = txtFecha.getText().trim();

            String error = null;
            if (!dni.matches("\\d{8}")) {
                error = "El DNI debe tener 8 dígitos.";
            } else if (nombres.isEmpty() || apellidos.isEmpty()) {
                error = "Ingrese nombres y apellidos.";
            } else if (cargo.isEmpty()) {
                error = "Ingrese el cargo.";
            } else if (!fechaValida(fecha)) {
                error = "La fecha debe tener el formato dd/MM/aaaa (ej. 15/04/2025).";
            } else if (dniDuplicado(dni, filaEditando)) {
                error = "Ya existe una persona registrada con ese DNI.";
            }

            if (error == null) {
                return new String[]{dni, nombres, apellidos, cargo,
                    (String) cboDep.getSelectedItem(), fecha, (String) cboEst.getSelectedItem()};
            }
            JOptionPane.showMessageDialog(this, error, "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean fechaValida(String texto) {
        try {
            LocalDate.parse(texto, FORMATO_FECHA);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    private boolean dniDuplicado(String dni, int filaEditando) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (i != filaEditando && dni.equals(String.valueOf(modelo.getValueAt(i, 1)))) {
                return true;
            }
        }
        return false;
    }

    private int siguienteId() {
        int max = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            max = Math.max(max, Integer.parseInt(String.valueOf(modelo.getValueAt(i, 0))));
        }
        return max + 1;
    }

    private void aviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    // =====================================================================
    //  AYUDAS DE ESTILO
    // =====================================================================
    private JLabel etiqueta(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(FUENTE_NEGRITA);
        l.setForeground(AZUL_OSCURO);
        return l;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(FUENTE);
        campo.setPreferredSize(new Dimension(220, 38));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                new EmptyBorder(4, 10, 4, 10)));
    }

    private void estilizarCombo(JComboBox<String> combo) {
        combo.setFont(FUENTE);
        combo.setBackground(Color.WHITE);
        combo.setPreferredSize(new Dimension(160, 38));
    }

    private BotonRedondeado crearBoton(String texto, Color color, Icon icono, ActionListener accion) {
        BotonRedondeado b = new BotonRedondeado(texto, color, icono);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setPreferredSize(new Dimension(150, 42));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        b.addActionListener(accion);
        return b;
    }

    /** Carga un icono desde el paquete img (Source Packages > img). Devuelve null si no existe. */
    private static Icon icono(String nombre) {
        if (!USAR_ICONOS) {
            return null;
        }
        URL url = Frm_Personal.class.getResource("/img/" + nombre);
        if (url == null) {
            return null;
        }
        Image img = new ImageIcon(url).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    // =====================================================================
    //  COMPONENTES PERSONALIZADOS
    // =====================================================================

    /** Tarjeta blanca con esquinas redondeadas (opcionalmente con degradado). */
    private static class PanelRedondeado extends JPanel {

        private final boolean degradado;

        PanelRedondeado(LayoutManager layout, boolean degradado) {
            super(layout);
            this.degradado = degradado;
            setOpaque(false);
            setBorder(new EmptyBorder(14, 16, 14, 16));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (degradado) {
                g2.setPaint(new GradientPaint(0, 0, Color.WHITE, getWidth(), 0, new Color(0xDDEAFB)));
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.setColor(BORDE);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /** Botón de color con esquinas redondeadas y efecto al pasar el mouse. */
    private static class BotonRedondeado extends JButton {

        private final Color base;
        private boolean encima;

        BotonRedondeado(String texto, Color base, Icon icono) {
            super(texto, icono);
            this.base = base;
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setIconTextGap(10);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            g2.setColor(encima ? base.darker() : base);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /** Etiqueta tipo "píldora" para el estado (verde = Activo, rojo = Inactivo). */
    private static class Pildora extends JLabel {

        private final Color fondo;

        Pildora(String texto, Color fondo, Color letra) {
            super(texto, SwingConstants.CENTER);
            this.fondo = fondo;
            setForeground(letra);
            setFont(new Font("Segoe UI", Font.PLAIN, 12));
            setOpaque(false);
            setBorder(new EmptyBorder(2, 14, 2, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fondo);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /** Renderer de la columna Estado. */
    private static class EstadoRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable t, Object valor,
                boolean sel, boolean foco, int fila, int col) {
            String estado = String.valueOf(valor);
            boolean activo = "Activo".equals(estado);

            JPanel contenedor = new JPanel(new GridBagLayout());
            contenedor.setBackground(sel ? t.getSelectionBackground()
                    : (fila % 2 == 0 ? Color.WHITE : FILA_ALTERNA));
            contenedor.add(new Pildora(estado,
                    activo ? new Color(0xD4EDDA) : new Color(0xF8D7DA),
                    activo ? new Color(0x1E7E34) : new Color(0xB02A37)));
            return contenedor;
        }
    }

    /** Círculo azul con el icono de personas del encabezado. */
    private static class IconoPersonal extends JComponent {

        IconoPersonal() {
            setPreferredSize(new Dimension(66, 66));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(AZUL);
            g2.fillOval(0, 0, 66, 66);

            int cx = 33;
            g2.setColor(new Color(255, 255, 255, 190));
            // persona izquierda
            g2.fillOval(cx - 22, 26, 10, 10);
            g2.fillArc(cx - 28, 38, 20, 20, 0, 180);
            // persona derecha
            g2.fillOval(cx + 12, 26, 10, 10);
            g2.fillArc(cx + 8, 38, 20, 20, 0, 180);

            g2.setColor(Color.WHITE);
            // persona central
            g2.fillOval(cx - 7, 17, 14, 14);
            g2.fillArc(cx - 14, 34, 28, 28, 0, 180);
            g2.dispose();
        }
    }

    // =====================================================================
    //  PRUEBA RÁPIDA (Shift+F6 sobre este archivo)
    // =====================================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Personal");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setContentPane(new Frm_Personal());
            f.setSize(1100, 650);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}