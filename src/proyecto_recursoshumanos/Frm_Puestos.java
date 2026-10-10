package proyecto_recursoshumanos;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

/**
 * Pantalla "Puestos" del sistema de Recursos Humanos.
 * Es un JPanel: se muestra DENTRO del menú principal con
 *     mostrarPanel(new Frm_Puestos());
 * (igual que Frm_Personal y Frm_Planillas).
 */
public class Frm_Puestos extends JPanel {

    // ===================== COLORES Y FUENTES (misma paleta del menú) =====================
    private static final Color FONDO = new Color(238, 243, 251);
    private static final Color AZUL_OSCURO = new Color(10, 44, 120);
    private static final Color AZUL = new Color(37, 99, 235);
    private static final Color VERDE = new Color(22, 163, 74);
    private static final Color AMARILLO = new Color(245, 158, 11);
    private static final Color ROJO = new Color(220, 38, 38);
    private static final Color GRIS = new Color(100, 116, 139);
    private static final Color TEXTO = new Color(30, 41, 59);
    private static final Color BORDE = new Color(214, 224, 240);
    private static final Color BORDE_CAMPO = new Color(203, 213, 225);
    private static final Color FONDO_TITULO = new Color(211, 228, 248);
    private static final Color FONDO_HEADER_TABLA = new Color(235, 241, 250);
    private static final Color GRID_TABLA = new Color(225, 231, 241);
    private static final Color FILA_SELECCION = new Color(219, 234, 254);

    private static final Font F_NORMAL = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font F_NEGRITA = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font F_SUBTITULO = new Font("Segoe UI", Font.BOLD, 19);

    private static final String[] COLUMNAS = {
        "ID", "Nombre del Puesto", "Departamento", "Área",
        "Nivel", "Tipo de Puesto", "Salario Base", "Estado"
    };

    // ===================== COMPONENTES =====================
    private final JTextField txtId = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JComboBox<String> cboDepartamento = new JComboBox<>(new String[]{
        "Seleccione un departamento", "Dirección", "Administración", "Sistemas", "Académico"});
    private final JComboBox<String> cboArea = new JComboBox<>(new String[]{
        "Seleccione un área", "Administrativa", "Recursos Humanos", "Tecnología", "Académica"});
    private final JComboBox<String> cboNivel = new JComboBox<>(new String[]{
        "Seleccione un nivel", "Directivo", "Jefatura", "Profesional", "Técnico"});
    private final JComboBox<String> cboTipo = new JComboBox<>(new String[]{
        "Seleccione un tipo", "Permanente", "Contratado"});
    private final JTextField txtSalario = new JTextField("0.00");
    private final JComboBox<String> cboEstado = new JComboBox<>(new String[]{"ACTIVO", "INACTIVO"});
    private final JTextArea txtDescripcion = new JTextArea();
    private final CampoConHint txtBuscar = new CampoConHint("Buscar por nombre, departamento o área...");

    private final DefaultTableModel modelo = new DefaultTableModel(COLUMNAS, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int columna) {
            return columna == 0 ? Integer.class : String.class;
        }
    };
    private final JTable tabla = new JTable(modelo);
    private final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);

    // La descripción no se muestra en la tabla: se guarda aparte por ID
    private final Map<Integer, String> descripciones = new HashMap<>();

    // ===================== CONSTRUCTOR =====================
    public Frm_Puestos() {
        setLayout(new BorderLayout(0, 14));
        setBackground(FONDO);

        add(crearTitulo(), BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout(0, 14));
        contenido.setOpaque(false);
        contenido.add(crearCardDatos(), BorderLayout.NORTH);
        contenido.add(crearCardListado(), BorderLayout.CENTER);
        add(contenido, BorderLayout.CENTER);

        cargarDatosEjemplo();
        limpiar();
    }

    // ===================== TÍTULO "Puestos" =====================
    private JPanel crearTitulo() {
        Tarjeta barra = new Tarjeta(FONDO_TITULO);
        barra.setLayout(new BorderLayout());
        barra.setBorder(new EmptyBorder(12, 22, 12, 22));
        JLabel t = new JLabel("Puestos",
                FontIcon.of(FontAwesomeSolid.BRIEFCASE, 30, AZUL_OSCURO), SwingConstants.LEFT);
        t.setIconTextGap(16);
        t.setFont(new Font("Segoe UI", Font.BOLD, 28));
        t.setForeground(AZUL_OSCURO);
        barra.add(t, BorderLayout.WEST);
        return barra;
    }

    // ===================== CARD: DATOS DEL PUESTO =====================
    private JPanel crearCardDatos() {
        Tarjeta card = new Tarjeta(Color.WHITE);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(14, 20, 16, 20));

        JLabel titulo = new JLabel("Datos del Puesto");
        titulo.setFont(F_SUBTITULO);
        titulo.setForeground(AZUL_OSCURO);
        titulo.setBorder(new EmptyBorder(0, 0, 6, 0));
        card.add(titulo, BorderLayout.NORTH);

        // --- estilos de campos ---
        estilizarTexto(txtId);
        txtId.setEditable(false);
        txtId.setBackground(new Color(233, 236, 240));
        txtId.setForeground(GRIS);
        estilizarTexto(txtNombre);
        estilizarTexto(txtSalario);
        estilizarCombo(cboDepartamento);
        estilizarCombo(cboArea);
        estilizarCombo(cboNivel);
        estilizarCombo(cboTipo);
        estilizarCombo(cboEstado);

        // Salario: solo números y un punto decimal
        txtSalario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                boolean permitido = Character.isDigit(c) || c == '.'
                        || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE;
                if (!permitido || (c == '.' && txtSalario.getText().contains("."))) {
                    e.consume();
                }
            }
        });
        txtSalario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtSalario.selectAll();
            }
        });

        txtDescripcion.setFont(F_NORMAL);
        txtDescripcion.setForeground(TEXTO);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(new EmptyBorder(6, 10, 6, 10));
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setBorder(new LineBorder(BORDE_CAMPO, 1));
        scrollDesc.setPreferredSize(new Dimension(240, 80));

        // --- formulario ---
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);

        ponerEtiqueta(form, "ID Puesto:", 0, 0);
        ponerCampo(form, txtId, 1, 0, 1);
        ponerEtiqueta(form, "Nombre del Puesto:", 0, 1);
        ponerCampo(form, txtNombre, 1, 1, 1);
        ponerEtiqueta(form, "Departamento:", 0, 2);
        ponerCampo(form, cboDepartamento, 1, 2, 1);
        ponerEtiqueta(form, "Área:", 0, 3);
        ponerCampo(form, cboArea, 1, 3, 1);
        ponerEtiqueta(form, "Nivel del Puesto:", 0, 4);
        ponerCampo(form, cboNivel, 1, 4, 1);

        ponerEtiqueta(form, "Tipo de Puesto:", 2, 0);
        ponerCampo(form, cboTipo, 3, 0, 1);
        ponerEtiqueta(form, "Salario Base:", 2, 1);
        ponerCampo(form, txtSalario, 3, 1, 1);
        ponerEtiqueta(form, "Estado:", 2, 2);
        ponerCampo(form, cboEstado, 3, 2, 1);
        ponerEtiqueta(form, "Descripción:", 2, 3);
        ponerCampo(form, scrollDesc, 3, 3, 2);

        card.add(form, BorderLayout.CENTER);

        // --- botones de la derecha ---
        BotonColor btnNuevo = new BotonColor("Nuevo", AZUL_OSCURO, FontAwesomeSolid.PLUS);
        BotonColor btnGuardar = new BotonColor("Guardar", VERDE, FontAwesomeSolid.SAVE);
        BotonColor btnModificar = new BotonColor("Modificar", AMARILLO, FontAwesomeSolid.PEN);
        BotonColor btnDesactivar = new BotonColor("Desactivar", ROJO, FontAwesomeSolid.TRASH);
        BotonColor btnLimpiar = new BotonColor("Limpiar", GRIS, FontAwesomeSolid.BROOM);

        btnNuevo.addActionListener(e -> limpiar());
        btnGuardar.addActionListener(e -> guardar());
        btnModificar.addActionListener(e -> modificar());
        btnDesactivar.addActionListener(e -> desactivar());
        btnLimpiar.addActionListener(e -> limpiar());

        JPanel botones = new JPanel(new GridLayout(5, 1, 0, 10));
        botones.setOpaque(false);
        botones.setBorder(new EmptyBorder(0, 24, 0, 0));
        botones.add(btnNuevo);
        botones.add(btnGuardar);
        botones.add(btnModificar);
        botones.add(btnDesactivar);
        botones.add(btnLimpiar);
        card.add(botones, BorderLayout.EAST);

        return card;
    }

    private void ponerEtiqueta(JPanel p, String texto, int x, int y) {
        JLabel l = new JLabel(texto);
        l.setFont(F_NORMAL);
        l.setForeground(TEXTO);
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(5, x == 0 ? 0 : 28, 5, 12);
        p.add(l, gc);
    }

    private void ponerCampo(JPanel p, JComponent c, int x, int y, int alto) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridheight = alto;
        gc.weightx = 1.0;
        gc.fill = alto > 1 ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(5, 0, 5, 0);
        p.add(c, gc);
    }

    private void estilizarTexto(JTextField t) {
        t.setFont(F_NORMAL);
        t.setForeground(TEXTO);
        t.setBackground(Color.WHITE);
        t.setBorder(new CompoundBorder(new LineBorder(BORDE_CAMPO, 1), new EmptyBorder(3, 10, 3, 10)));
        t.setPreferredSize(new Dimension(240, 34));
    }

    private void estilizarCombo(JComboBox<String> c) {
        c.setFont(F_NORMAL);
        c.setBackground(Color.WHITE);
        c.setPreferredSize(new Dimension(240, 34));
    }

    // ===================== CARD: LISTADO =====================
    private JPanel crearCardListado() {
        Tarjeta card = new Tarjeta(Color.WHITE);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(14, 20, 16, 20));

        JPanel barra = new JPanel(new BorderLayout());
        barra.setOpaque(false);
        barra.setBorder(new EmptyBorder(0, 0, 10, 0));

        JLabel titulo = new JLabel("Listado de Puestos");
        titulo.setFont(F_SUBTITULO);
        titulo.setForeground(AZUL_OSCURO);
        barra.add(titulo, BorderLayout.WEST);

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        busqueda.setOpaque(false);
        JLabel lbBuscar = new JLabel("Buscar:");
        lbBuscar.setFont(F_NORMAL);
        lbBuscar.setForeground(TEXTO);
        estilizarTexto(txtBuscar);
        txtBuscar.setPreferredSize(new Dimension(320, 34));
        BotonColor btnBuscar = new BotonColor("Buscar", AZUL, FontAwesomeSolid.SEARCH);
        btnBuscar.setPreferredSize(new Dimension(120, 34));
        btnBuscar.addActionListener(e -> buscar());
        txtBuscar.addActionListener(e -> buscar());
        busqueda.add(lbBuscar);
        busqueda.add(txtBuscar);
        busqueda.add(btnBuscar);
        barra.add(busqueda, BorderLayout.EAST);

        card.add(barra, BorderLayout.NORTH);
        card.add(crearTabla(), BorderLayout.CENTER);
        return card;
    }

    private JScrollPane crearTabla() {
        tabla.setRowSorter(sorter);
        tabla.setRowHeight(30);
        tabla.setFont(F_NORMAL);
        tabla.setForeground(TEXTO);
        tabla.setBackground(Color.WHITE);
        tabla.setGridColor(GRID_TABLA);
        tabla.setShowGrid(true);
        tabla.setIntercellSpacing(new Dimension(1, 1));
        tabla.setSelectionBackground(FILA_SELECCION);
        tabla.setSelectionForeground(AZUL_OSCURO);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setFillsViewportHeight(true);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Encabezado
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 34));
        tabla.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object valor, boolean sel,
                    boolean foco, int fila, int col) {
                super.getTableCellRendererComponent(t, valor, false, false, fila, col);
                setHorizontalAlignment(SwingConstants.CENTER);
                setOpaque(true);
                setBackground(FONDO_HEADER_TABLA);
                setForeground(AZUL_OSCURO);
                setFont(F_NEGRITA);
                setBorder(new MatteBorder(0, 0, 1, 1, GRID_TABLA));
                return this;
            }
        });

        // Celdas
        CeldaRenderer centro = new CeldaRenderer(SwingConstants.CENTER);
        CeldaRenderer izquierda = new CeldaRenderer(SwingConstants.LEFT);
        TableColumnModel cm = tabla.getColumnModel();
        int[] anchos = {50, 240, 160, 150, 120, 130, 120, 120};
        for (int i = 0; i < anchos.length; i++) {
            cm.getColumn(i).setPreferredWidth(anchos[i]);
        }
        cm.getColumn(0).setCellRenderer(centro);
        for (int i = 1; i <= 5; i++) {
            cm.getColumn(i).setCellRenderer(izquierda);
        }
        cm.getColumn(6).setCellRenderer(centro);
        cm.getColumn(7).setCellRenderer(new EstadoRenderer());

        // Al elegir una fila se llena el formulario
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarFilaSeleccionada();
            }
        });

        JScrollPane sp = new JScrollPane(tabla);
        sp.setBorder(new LineBorder(GRID_TABLA, 1));
        sp.getViewport().setBackground(Color.WHITE);
        return sp;
    }

    // ===================== LÓGICA =====================
    private void cargarDatosEjemplo() {
        Object[][] datos = {
            {1, "Gerente General", "Dirección", "Administrativa", "Directivo", "Permanente", "8000.00", "ACTIVO"},
            {2, "Jefe de Recursos Humanos", "Administración", "Recursos Humanos", "Jefatura", "Permanente", "5000.00", "ACTIVO"},
            {3, "Analista de Personal", "Administración", "Recursos Humanos", "Profesional", "Permanente", "2500.00", "ACTIVO"},
            {4, "Asistente Administrativo", "Administración", "Administrativa", "Técnico", "Permanente", "1800.00", "ACTIVO"},
            {5, "Programador", "Sistemas", "Tecnología", "Profesional", "Permanente", "3200.00", "ACTIVO"},
            {6, "Soporte Técnico", "Sistemas", "Tecnología", "Técnico", "Permanente", "2000.00", "INACTIVO"},
            {7, "Coordinador Académico", "Académico", "Académica", "Jefatura", "Permanente", "3500.00", "ACTIVO"},
            {8, "Docente", "Académico", "Académica", "Profesional", "Contratado", "2200.00", "ACTIVO"}
        };
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
    }

    private void limpiar() {
        tabla.clearSelection();
        txtId.setText(String.valueOf(siguienteId()));
        txtNombre.setText("");
        cboDepartamento.setSelectedIndex(0);
        cboArea.setSelectedIndex(0);
        cboNivel.setSelectedIndex(0);
        cboTipo.setSelectedIndex(0);
        txtSalario.setText("0.00");
        cboEstado.setSelectedItem("ACTIVO");
        txtDescripcion.setText("");
        txtNombre.requestFocusInWindow();
    }

    private void guardar() {
        if (!validar()) {
            return;
        }
        int id = Integer.parseInt(txtId.getText().trim());
        if (buscarFilaPorId(id) >= 0) {
            avisar("Este puesto ya está registrado.\nUse \"Modificar\" para actualizarlo o \"Nuevo\" para crear otro.");
            return;
        }
        modelo.addRow(filaDesdeFormulario(id));
        descripciones.put(id, txtDescripcion.getText().trim());
        informar("Puesto guardado correctamente.");
        limpiar();
    }

    private void modificar() {
        int vista = tabla.getSelectedRow();
        if (vista < 0) {
            avisar("Seleccione un puesto de la tabla para modificarlo.");
            return;
        }
        if (!validar()) {
            return;
        }
        int fila = tabla.convertRowIndexToModel(vista);
        int id = (Integer) modelo.getValueAt(fila, 0);
        Object[] nueva = filaDesdeFormulario(id);
        for (int c = 1; c < nueva.length; c++) {
            modelo.setValueAt(nueva[c], fila, c);
        }
        descripciones.put(id, txtDescripcion.getText().trim());
        informar("Puesto modificado correctamente.");
    }

    private void desactivar() {
        int vista = tabla.getSelectedRow();
        if (vista < 0) {
            avisar("Seleccione un puesto de la tabla para desactivarlo.");
            return;
        }
        int fila = tabla.convertRowIndexToModel(vista);
        if ("INACTIVO".equals(modelo.getValueAt(fila, 7))) {
            informar("Este puesto ya está INACTIVO.");
            return;
        }
        int r = JOptionPane.showConfirmDialog(this,
                "¿Desea desactivar el puesto \"" + modelo.getValueAt(fila, 1) + "\"?",
                "Desactivar puesto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (r == JOptionPane.YES_OPTION) {
            modelo.setValueAt("INACTIVO", fila, 7);
            cboEstado.setSelectedItem("INACTIVO");
        }
    }

    private void buscar() {
        String texto = txtBuscar.getText().trim();
        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // Filtra por Nombre (1), Departamento (2) y Área (3), sin distinguir mayúsculas
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(texto), 1, 2, 3));
        }
    }

    private void cargarFilaSeleccionada() {
        int vista = tabla.getSelectedRow();
        if (vista < 0) {
            return;
        }
        int fila = tabla.convertRowIndexToModel(vista);
        int id = (Integer) modelo.getValueAt(fila, 0);
        txtId.setText(String.valueOf(id));
        txtNombre.setText(String.valueOf(modelo.getValueAt(fila, 1)));
        cboDepartamento.setSelectedItem(modelo.getValueAt(fila, 2));
        cboArea.setSelectedItem(modelo.getValueAt(fila, 3));
        cboNivel.setSelectedItem(modelo.getValueAt(fila, 4));
        cboTipo.setSelectedItem(modelo.getValueAt(fila, 5));
        txtSalario.setText(String.valueOf(modelo.getValueAt(fila, 6)));
        cboEstado.setSelectedItem(modelo.getValueAt(fila, 7));
        txtDescripcion.setText(descripciones.getOrDefault(id, ""));
        txtDescripcion.setCaretPosition(0);
    }

    private boolean validar() {
        if (txtNombre.getText().trim().isEmpty()) {
            avisar("Ingrese el nombre del puesto.");
            txtNombre.requestFocusInWindow();
            return false;
        }
        if (cboDepartamento.getSelectedIndex() <= 0) {
            avisar("Seleccione un departamento.");
            cboDepartamento.requestFocusInWindow();
            return false;
        }
        if (cboArea.getSelectedIndex() <= 0) {
            avisar("Seleccione un área.");
            cboArea.requestFocusInWindow();
            return false;
        }
        if (cboNivel.getSelectedIndex() <= 0) {
            avisar("Seleccione el nivel del puesto.");
            cboNivel.requestFocusInWindow();
            return false;
        }
        if (cboTipo.getSelectedIndex() <= 0) {
            avisar("Seleccione el tipo de puesto.");
            cboTipo.requestFocusInWindow();
            return false;
        }
        if (leerSalario() == null) {
            avisar("Ingrese un salario base válido (número mayor o igual a 0).");
            txtSalario.requestFocusInWindow();
            return false;
        }
        return true;
    }

    private Double leerSalario() {
        try {
            double v = Double.parseDouble(txtSalario.getText().trim().replace(',', '.'));
            if (Double.isNaN(v) || Double.isInfinite(v) || v < 0) {
                return null;
            }
            return v;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Object[] filaDesdeFormulario(int id) {
        return new Object[]{
            id,
            txtNombre.getText().trim(),
            cboDepartamento.getSelectedItem(),
            cboArea.getSelectedItem(),
            cboNivel.getSelectedItem(),
            cboTipo.getSelectedItem(),
            String.format(Locale.US, "%.2f", leerSalario()),
            cboEstado.getSelectedItem()
        };
    }

    private int siguienteId() {
        int max = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            max = Math.max(max, (Integer) modelo.getValueAt(i, 0));
        }
        return max + 1;
    }

    private int buscarFilaPorId(int id) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if ((Integer) modelo.getValueAt(i, 0) == id) {
                return i;
            }
        }
        return -1;
    }

    private void avisar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Puestos", JOptionPane.WARNING_MESSAGE);
    }

    private void informar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Puestos", JOptionPane.INFORMATION_MESSAGE);
    }

    // ===================== CLASES AUXILIARES =====================

    /** Panel con fondo de color y esquinas redondeadas (igual que las tarjetas del menú). */
    private static class Tarjeta extends JPanel {

        private final Color fondo;

        Tarjeta(Color fondo) {
            this.fondo = fondo;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fondo);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.setColor(BORDE);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            g2.dispose();
        }
    }

    /** Botón de color con esquinas redondeadas e ícono blanco (se ve igual con Nimbus). */
    private static class BotonColor extends JButton {

        private final Color base;

        BotonColor(String texto, Color base, FontAwesomeSolid ico) {
            super(texto);
            this.base = base;
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setRolloverEnabled(true);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setIconTextGap(12);
            setPreferredSize(new Dimension(160, 40));
            if (ico != null) {
                setIcon(FontIcon.of(ico, 16, Color.WHITE));
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color c = base;
            if (getModel().isPressed()) {
                c = base.darker();
            } else if (getModel().isRollover()) {
                c = base.brighter();
            }
            g2.setColor(c);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /** Campo de texto con texto de ayuda (placeholder). */
    private static class CampoConHint extends JTextField {

        private final String hint;

        CampoConHint(String hint) {
            this.hint = hint;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getText().isEmpty() && !isFocusOwner()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setColor(new Color(148, 163, 184));
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                Insets in = getInsets();
                g2.drawString(hint, in.left, (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                g2.dispose();
            }
        }
    }

    /** Celda de texto con alineación y margen. */
    private static class CeldaRenderer extends DefaultTableCellRenderer {

        private final int alineacion;

        CeldaRenderer(int alineacion) {
            this.alineacion = alineacion;
        }

        @Override
        public Component getTableCellRendererComponent(JTable t, Object valor, boolean sel,
                boolean foco, int fila, int col) {
            super.getTableCellRendererComponent(t, valor, sel, false, fila, col);
            setHorizontalAlignment(alineacion);
            setBorder(new EmptyBorder(0, 10, 0, 10));
            return this;
        }
    }

    /** Celda "Estado" dibujada como etiqueta verde (ACTIVO) o roja (INACTIVO). */
    private static class EstadoRenderer extends JPanel implements TableCellRenderer {

        private String texto = "";
        private Color fondo = VERDE;
        private final Font fuente = new Font("Segoe UI", Font.BOLD, 12);

        EstadoRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable t, Object valor, boolean sel,
                boolean foco, int fila, int col) {
            texto = valor == null ? "" : valor.toString();
            fondo = "ACTIVO".equalsIgnoreCase(texto) ? VERDE : ROJO;
            setBackground(sel ? t.getSelectionBackground() : t.getBackground());
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(fuente);
            FontMetrics fm = g2.getFontMetrics();
            int w = fm.stringWidth(texto) + 24;
            int h = 22;
            int x = (getWidth() - w) / 2;
            int y = (getHeight() - h) / 2;
            g2.setColor(fondo);
            g2.fillRoundRect(x, y, w, h, 10, 10);
            g2.setColor(Color.WHITE);
            g2.drawString(texto, x + 12, y + (h + fm.getAscent() - fm.getDescent()) / 2);
            g2.dispose();
        }
    }

    // ===================== MAIN (solo para probar este panel solo) =====================
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame f = new JFrame("Puestos - prueba");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel marco = new JPanel(new BorderLayout());
            marco.setBackground(FONDO);
            marco.setBorder(new EmptyBorder(12, 12, 12, 12));
            marco.add(new Frm_Puestos(), BorderLayout.CENTER);
            f.setContentPane(marco);
            f.setSize(1150, 740);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}