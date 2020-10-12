package lesani.gui.layout;

import javax.swing.*;
import java.awt.*;


public class Sizer extends JComponent
{

    public static JComponent fixSize(JComponent component)
    {
//        System.out.println(component.getPreferredSize());
        component.setMaximumSize(component.getPreferredSize());
        component.setMinimumSize(component.getPreferredSize());
        return component;
    }

    public static JComponent fixWidth(JComponent component)
    {
        Dimension preferredSize = component.getPreferredSize();
        Dimension maximumSize = component.getMaximumSize();
        Dimension minimumSize = component.getMaximumSize();
        component.setMaximumSize(new Dimension((int)preferredSize.getWidth(), (int)maximumSize.getHeight()));
        component.setMinimumSize(new Dimension((int)preferredSize.getWidth(), (int)minimumSize.getHeight()));
        return component;
    }

    public static JComponent fixHeight(JComponent component)
    {
        Dimension preferredSize = component.getPreferredSize();
        Dimension maximumSize = component.getMaximumSize();
        Dimension minimumSize = component.getMaximumSize();
        component.setMaximumSize(new Dimension((int)maximumSize.getWidth(), (int)preferredSize.getHeight()));
        component.setMinimumSize(new Dimension((int)minimumSize.getWidth(), (int)preferredSize.getHeight()));
        return component;
    }

//    private JComponent aComponent = new JLabel();
//
//    public Sizer(JComponent aComponent) {
//        this.aComponent = aComponent;
//    }
//
//    public Dimension getMaximumSize() {
//        return aComponent.getPreferredSize();
//    }
//
//    //----------------------------------------------------------------------------
//
//    public ComponentOrientation getComponentOrientation() {
//        return aComponent.getComponentOrientation();
//    }
//
//    public void setInheritsPopupMenu(boolean value) {
//        aComponent.setInheritsPopupMenu(value);
//    }
//
//    public boolean getInheritsPopupMenu() {
//        return aComponent.getInheritsPopupMenu();
//    }
//
//    public void setComponentPopupMenu(JPopupMenu popup) {
//        aComponent.setComponentPopupMenu(popup);
//    }
//
//    public JPopupMenu getComponentPopupMenu() {
//        return aComponent.getComponentPopupMenu();
//    }
//
//    public void updateUI() {
//        aComponent.updateUI();
//    }
//
///*
//    public void setUI(ComponentUI newUI) {
//        aComponent.setUI(newUI);
//    }
//*/
//
//    public String getUIClassID() {
//        return aComponent.getUIClassID();
//    }
//
///*
//    public Graphics getComponentGraphics(Graphics g) {
//        return aComponent.getComponentGraphics(g);
//    }
//
//    public void paintComponent(Graphics g) {
//        aComponent.paintComponent(g);
//    }
//
//    public void paintChildren(Graphics g) {
//        aComponent.paintChildren(g);
//    }
//
//    public void paintBorder(Graphics g) {
//        aComponent.paintBorder(g);
//    }
//*/
//
//    public void update(Graphics g) {
//        aComponent.update(g);
//    }
//
//    public void paint(Graphics g) {
//        aComponent.paint(g);
//    }
//
//    public void printAll(Graphics g) {
//        aComponent.printAll(g);
//    }
//
//    public void print(Graphics g) {
//        aComponent.print(g);
//    }
//
///*
//    public void printComponent(Graphics g) {
//        aComponent.printComponent(g);
//    }
//
//    public void printChildren(Graphics g) {
//        aComponent.printChildren(g);
//    }
//
//    public void printBorder(Graphics g) {
//        aComponent.printBorder(g);
//    }
//*/
//
//    public boolean isPaintingTile() {
//        return aComponent.isPaintingTile();
//    }
//
///*
//    public boolean isPaintingForPrint() {
//        return aComponent.isPaintingForPrint();
//    }
//*/
//
//    public boolean isManagingFocus() {
//        System.out.println(aComponent);
//        return aComponent.isManagingFocus();
//    }
//
//    public void setNextFocusableComponent(Component aComponent) {
//        this.aComponent.setNextFocusableComponent(aComponent);
//    }
//
//    public Component getNextFocusableComponent() {
//        return aComponent.getNextFocusableComponent();
//    }
//
//    public void setRequestFocusEnabled(boolean requestFocusEnabled) {
//        aComponent.setRequestFocusEnabled(requestFocusEnabled);
//    }
//
//    public boolean isRequestFocusEnabled() {
//        return aComponent.isRequestFocusEnabled();
//    }
//
//    public void requestFocus() {
//        aComponent.requestFocus();
//    }
//
//    public boolean requestFocus(boolean temporary) {
//        return aComponent.requestFocus(temporary);
//    }
//
//    public boolean requestFocusInWindow() {
//        return aComponent.requestFocusInWindow();
//    }
//
///*
//    public boolean requestFocusInWindow(boolean temporary) {
//        return aComponent.requestFocusInWindow(temporary);
//    }
//*/
//
//    public void grabFocus() {
//        aComponent.grabFocus();
//    }
//
//    public void setVerifyInputWhenFocusTarget(boolean verifyInputWhenFocusTarget) {
//        aComponent.setVerifyInputWhenFocusTarget(verifyInputWhenFocusTarget);
//    }
//
//    public boolean getVerifyInputWhenFocusTarget() {
//        return aComponent.getVerifyInputWhenFocusTarget();
//    }
//
//    public FontMetrics getFontMetrics(Font font) {
//        return aComponent.getFontMetrics(font);
//    }
//
//    public void setPreferredSize(Dimension preferredSize) {
//        aComponent.setPreferredSize(preferredSize);
//    }
//
//    public Dimension getPreferredSize() {
//        return aComponent.getPreferredSize();
//    }
//
//    public void setMaximumSize(Dimension maximumSize) {
//        aComponent.setMaximumSize(maximumSize);
//    }
//
//    public void setMinimumSize(Dimension minimumSize) {
//        aComponent.setMinimumSize(minimumSize);
//    }
//
//    public Dimension getMinimumSize() {
//        return aComponent.getMinimumSize();
//    }
//
//    public boolean contains(int x, int y) {
//        return aComponent.contains(x, y);
//    }
//
//    public void setBorder(Border border) {
//        aComponent.setBorder(border);
//    }
//
//    public Border getBorder() {
//        return aComponent.getBorder();
//    }
//
//    public Insets getInsets() {
//        return aComponent.getInsets();
//    }
//
//    public Insets getInsets(Insets insets) {
//        return aComponent.getInsets(insets);
//    }
//
//    public float getAlignmentY() {
//        return aComponent.getAlignmentY();
//    }
//
//    public void setAlignmentY(float alignmentY) {
//        aComponent.setAlignmentY(alignmentY);
//    }
//
//    public float getAlignmentX() {
//        return aComponent.getAlignmentX();
//    }
//
//    public void setAlignmentX(float alignmentX) {
//        aComponent.setAlignmentX(alignmentX);
//    }
//
//    public void setInputVerifier(InputVerifier inputVerifier) {
//        aComponent.setInputVerifier(inputVerifier);
//    }
//
//    public InputVerifier getInputVerifier() {
//        return aComponent.getInputVerifier();
//    }
//
//    public Graphics getGraphics() {
//        return aComponent.getGraphics();
//    }
//
//    public void setDebugGraphicsOptions(int debugOptions) {
//        aComponent.setDebugGraphicsOptions(debugOptions);
//    }
//
//    public int getDebugGraphicsOptions() {
//        return aComponent.getDebugGraphicsOptions();
//    }
//
//    public void registerKeyboardAction(ActionListener anAction, String aCommand, KeyStroke aKeyStroke, int aCondition) {
//        aComponent.registerKeyboardAction(anAction, aCommand, aKeyStroke, aCondition);
//    }
//
//    public void registerKeyboardAction(ActionListener anAction, KeyStroke aKeyStroke, int aCondition) {
//        aComponent.registerKeyboardAction(anAction, aKeyStroke, aCondition);
//    }
//
//    public void unregisterKeyboardAction(KeyStroke aKeyStroke) {
//        aComponent.unregisterKeyboardAction(aKeyStroke);
//    }
//
//    public KeyStroke[] getRegisteredKeyStrokes() {
//        return aComponent.getRegisteredKeyStrokes();
//    }
//
//    public int getConditionForKeyStroke(KeyStroke aKeyStroke) {
//        return aComponent.getConditionForKeyStroke(aKeyStroke);
//    }
//
//    public ActionListener getActionForKeyStroke(KeyStroke aKeyStroke) {
//        return aComponent.getActionForKeyStroke(aKeyStroke);
//    }
//
//    public void resetKeyboardActions() {
//        aComponent.resetKeyboardActions();
//    }
//
///*
//    public void setInputMap(int condition, InputMap map) {
//        aComponent.setInputMap(condition, map);
//    }
//
//    public InputMap getInputMap(int condition) {
//        return aComponent.getInputMap(condition);
//    }
//
//    public InputMap getInputMap() {
//        return aComponent.getInputMap();
//    }
//
//    public void setActionMap(ActionMap am) {
//        aComponent.setActionMap(am);
//    }
//
//    public ActionMap getActionMap() {
//        return aComponent.getActionMap();
//    }
//*/
//
//    public int getBaseline(int width, int height) {
//        return aComponent.getBaseline(width, height);
//    }
//
//    public BaselineResizeBehavior getBaselineResizeBehavior() {
//        return aComponent.getBaselineResizeBehavior();
//    }
//
//    public boolean requestDefaultFocus() {
//        return aComponent.requestDefaultFocus();
//    }
//
//    public void setVisible(boolean aFlag) {
//        aComponent.setVisible(aFlag);
//    }
//
//    public void setEnabled(boolean enabled) {
//        aComponent.setEnabled(enabled);
//    }
//
//    public void setForeground(Color fg) {
//        aComponent.setForeground(fg);
//    }
//
//    public void setBackground(Color bg) {
//        aComponent.setBackground(bg);
//    }
//
//    public void setFont(Font font) {
//        aComponent.setFont(font);
//    }
//
///*
//    public Locale getDefaultLocale() {
//        return aComponent.getDefaultLocale();
//    }
//
//    public void setDefaultLocale(Locale l) {
//        aComponent.setDefaultLocale(l);
//    }
//
//    public void processComponentKeyEvent(KeyEvent e) {
//        aComponent.processComponentKeyEvent(e);
//    }
//
//    public void processKeyEvent(KeyEvent e) {
//        aComponent.processKeyEvent(e);
//    }
//
//    public boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed) {
//        return aComponent.processKeyBinding(ks, e, condition, pressed);
//    }
//*/
//
//    public void setToolTipText(String text) {
//        aComponent.setToolTipText(text);
//    }
//
//    public String getToolTipText() {
//        return aComponent.getToolTipText();
//    }
//
//    public String getToolTipText(MouseEvent event) {
//        return aComponent.getToolTipText(event);
//    }
//
//    public Point getToolTipLocation(MouseEvent event) {
//        return aComponent.getToolTipLocation(event);
//    }
//
//    public Point getPopupLocation(MouseEvent event) {
//        return aComponent.getPopupLocation(event);
//    }
//
//    public JToolTip createToolTip() {
//        return aComponent.createToolTip();
//    }
//
//    public void scrollRectToVisible(Rectangle aRect) {
//        aComponent.scrollRectToVisible(aRect);
//    }
//
//    public void setAutoscrolls(boolean autoscrolls) {
//        aComponent.setAutoscrolls(autoscrolls);
//    }
//
//    public boolean getAutoscrolls() {
//        return aComponent.getAutoscrolls();
//    }
//
//    public void setTransferHandler(TransferHandler newHandler) {
//        aComponent.setTransferHandler(newHandler);
//    }
//
//    public TransferHandler getTransferHandler() {
//        return aComponent.getTransferHandler();
//    }
//
///*
//    public void processMouseEvent(MouseEvent e) {
//        aComponent.processMouseEvent(e);
//    }
//*/
//
///*
//    public void processMouseMotionEvent(MouseEvent e) {
//        aComponent.processMouseMotionEvent(e);
//    }
//*/
//
//    public void enable() {
//        aComponent.enable();
//    }
//
//    public void disable() {
//        aComponent.disable();
//    }
//
//    public AccessibleContext getAccessibleContext() {
//        return aComponent.getAccessibleContext();
//    }
//
///*
//    public Object getClientProperty(Object key) {
//        return aComponent.getClientProperty(key);
//    }
//
//    public void putClientProperty(Object key, Object value) {
//        aComponent.putClientProperty(key, value);
//    }
//*/
//
//    public void setFocusTraversalKeys(int id, Set<? extends AWTKeyStroke> keystrokes) {
//        aComponent.setFocusTraversalKeys(id, keystrokes);
//    }
//
///*
//    public boolean isLightweightComponent(Component c) {
//        return aComponent.isLightweightComponent(c);
//    }
//*/
//
//    public void reshape(int x, int y, int w, int h) {
//        aComponent.reshape(x, y, w, h);
//    }
//
//    public Rectangle getBounds(Rectangle rv) {
//        return aComponent.getBounds(rv);
//    }
//
//    public Dimension getSize(Dimension rv) {
//        return aComponent.getSize(rv);
//    }
//
//    public Point getLocation(Point rv) {
//        return aComponent.getLocation(rv);
//    }
//
//    public int getX() {
//        return aComponent.getX();
//    }
//
//    public int getY() {
//        return aComponent.getY();
//    }
//
//    public int getWidth() {
//        return aComponent.getWidth();
//    }
//
//    public int getHeight() {
//        return aComponent.getHeight();
//    }
//
//    public boolean isOpaque() {
//        return aComponent.isOpaque();
//    }
//
//    public void setOpaque(boolean isOpaque) {
//        aComponent.setOpaque(isOpaque);
//    }
//
//    public void computeVisibleRect(Rectangle visibleRect) {
//        aComponent.computeVisibleRect(visibleRect);
//    }
//
//    public Rectangle getVisibleRect() {
//        return aComponent.getVisibleRect();
//    }
//
//    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
//        aComponent.firePropertyChange(propertyName, oldValue, newValue);
//    }
//
//    public void firePropertyChange(String propertyName, int oldValue, int newValue) {
//        aComponent.firePropertyChange(propertyName, oldValue, newValue);
//    }
//
//    public void firePropertyChange(String propertyName, char oldValue, char newValue) {
//        aComponent.firePropertyChange(propertyName, oldValue, newValue);
//    }
//
///*
//    public void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws PropertyVetoException {
//        aComponent.fireVetoableChange(propertyName, oldValue, newValue);
//    }
//*/
//
//    public void addVetoableChangeListener(VetoableChangeListener listener) {
//        aComponent.addVetoableChangeListener(listener);
//    }
//
//    public void removeVetoableChangeListener(VetoableChangeListener listener) {
//        aComponent.removeVetoableChangeListener(listener);
//    }
//
//    public VetoableChangeListener[] getVetoableChangeListeners() {
//        return aComponent.getVetoableChangeListeners();
//    }
//
//    public Container getTopLevelAncestor() {
//        return aComponent.getTopLevelAncestor();
//    }
//
//    public void addAncestorListener(AncestorListener listener) {
//        aComponent.addAncestorListener(listener);
//    }
//
//    public void removeAncestorListener(AncestorListener listener) {
//        aComponent.removeAncestorListener(listener);
//    }
//
//    public AncestorListener[] getAncestorListeners() {
//        return aComponent.getAncestorListeners();
//    }
//
//    public <T extends EventListener> T[] getListeners(Class<T> listenerType) {
//        return aComponent.getListeners(listenerType);
//    }
//
//    public void addNotify() {
//        aComponent.addNotify();
//    }
//
//    public void removeNotify() {
//        aComponent.removeNotify();
//    }
//
//    public void repaint(long tm, int x, int y, int width, int height) {
//        aComponent.repaint(tm, x, y, width, height);
//    }
//
//    public void repaint(Rectangle r) {
//        aComponent.repaint(r);
//    }
//
//    public void revalidate() {
//        aComponent.revalidate();
//    }
//
//    public boolean isValidateRoot() {
//        return aComponent.isValidateRoot();
//    }
//
//    public boolean isOptimizedDrawingEnabled() {
//        return aComponent.isOptimizedDrawingEnabled();
//    }
//
//    public void paintImmediately(int x, int y, int w, int h) {
//        aComponent.paintImmediately(x, y, w, h);
//    }
//
//    public void paintImmediately(Rectangle r) {
//        aComponent.paintImmediately(r);
//    }
//
//    public void setDoubleBuffered(boolean aFlag) {
//        aComponent.setDoubleBuffered(aFlag);
//    }
//
//    public boolean isDoubleBuffered() {
//        return aComponent.isDoubleBuffered();
//    }
//
//    public JRootPane getRootPane() {
//        return aComponent.getRootPane();
//    }
//
///*
//    public String paramString() {
//        return aComponent.paramString();
//    }
//*/
//
//    public int getComponentCount() {
//        return aComponent.getComponentCount();
//    }
//
//    public int countComponents() {
//        return aComponent.countComponents();
//    }
//
//    public Component getComponent(int n) {
//        return aComponent.getComponent(n);
//    }
//
//    public Component[] getComponents() {
//        return aComponent.getComponents();
//    }
//
//    public Insets insets() {
//        return aComponent.insets();
//    }
//
//    public Component add(Component comp) {
//        return aComponent.add(comp);
//    }
//
//    public Component add(String name, Component comp) {
//        return aComponent.add(name, comp);
//    }
//
//    public Component add(Component comp, int index) {
//        return aComponent.add(comp, index);
//    }
//
//    public void setComponentZOrder(Component comp, int index) {
//        aComponent.setComponentZOrder(comp, index);
//    }
//
//    public int getComponentZOrder(Component comp) {
//        return aComponent.getComponentZOrder(comp);
//    }
//
//    public void add(Component comp, Object constraints) {
//        aComponent.add(comp, constraints);
//    }
//
//    public void add(Component comp, Object constraints, int index) {
//        aComponent.add(comp, constraints, index);
//    }
//
///*
//    public void addImpl(Component comp, Object constraints, int index) {
//        aComponent.addImpl(comp, constraints, index);
//    }
//*/
//
//    public void remove(int index) {
//        aComponent.remove(index);
//    }
//
//    public void remove(Component comp) {
//        aComponent.remove(comp);
//    }
//
//    public void removeAll() {
//        aComponent.removeAll();
//    }
//
//    public LayoutManager getLayout() {
//        return aComponent.getLayout();
//    }
//
//    public void setLayout(LayoutManager mgr) {
//        aComponent.setLayout(mgr);
//    }
//
//    public void doLayout() {
//        aComponent.doLayout();
//    }
//
//    public void layout() {
//        aComponent.layout();
//    }
//
//    public void invalidate() {
//        aComponent.invalidate();
//    }
//
//    public void validate() {
//        aComponent.validate();
//    }
//
///*
//    public void validateTree() {
//        aComponent.validateTree();
//    }
//*/
//
//    public Dimension preferredSize() {
//        return aComponent.preferredSize();
//    }
//
//    public Dimension minimumSize() {
//        return aComponent.minimumSize();
//    }
//
//    public void paintComponents(Graphics g) {
//        aComponent.paintComponents(g);
//    }
//
//    public void printComponents(Graphics g) {
//        aComponent.printComponents(g);
//    }
//
//    public void addContainerListener(ContainerListener l) {
//        aComponent.addContainerListener(l);
//    }
//
//    public void removeContainerListener(ContainerListener l) {
//        aComponent.removeContainerListener(l);
//    }
//
//    public ContainerListener[] getContainerListeners() {
//        return aComponent.getContainerListeners();
//    }
//
///*
//    public void processEvent(AWTEvent e) {
//        aComponent.processEvent(e);
//    }
//
//    public void processContainerEvent(ContainerEvent e) {
//        aComponent.processContainerEvent(e);
//    }
//*/
//    public void deliverEvent(Event e) {
//        aComponent.deliverEvent(e);
//    }
//
//    public Component getComponentAt(int x, int y) {
//        return aComponent.getComponentAt(x, y);
//    }
//
//    public Component locate(int x, int y) {
//        return aComponent.locate(x, y);
//    }
//
//    public Component getComponentAt(Point p) {
//        return aComponent.getComponentAt(p);
//    }
//
//    public Point getMousePosition(boolean allowChildren) throws HeadlessException {
//        return aComponent.getMousePosition(allowChildren);
//    }
//
//    public Component findComponentAt(int x, int y) {
//        return aComponent.findComponentAt(x, y);
//    }
//
//    public Component findComponentAt(Point p) {
//        return aComponent.findComponentAt(p);
//    }
//
//    public boolean isAncestorOf(Component c) {
//        return aComponent.isAncestorOf(c);
//    }
//
//    public void list(PrintStream out, int indent) {
//        aComponent.list(out, indent);
//    }
//
//    public void list(PrintWriter out, int indent) {
//        aComponent.list(out, indent);
//    }
//
//    public Set<AWTKeyStroke> getFocusTraversalKeys(int id) {
//        return aComponent.getFocusTraversalKeys(id);
//    }
//
//    public boolean areFocusTraversalKeysSet(int id) {
//        return aComponent.areFocusTraversalKeysSet(id);
//    }
//
//    public boolean isFocusCycleRoot(Container container) {
//        return aComponent.isFocusCycleRoot(container);
//    }
//
//    public void transferFocusBackward() {
//        aComponent.transferFocusBackward();
//    }
//
//    public void setFocusTraversalPolicy(FocusTraversalPolicy policy) {
//        aComponent.setFocusTraversalPolicy(policy);
//    }
//
//    public FocusTraversalPolicy getFocusTraversalPolicy() {
//        return aComponent.getFocusTraversalPolicy();
//    }
//
//    public boolean isFocusTraversalPolicySet() {
//        return aComponent.isFocusTraversalPolicySet();
//    }
//
//    public void setFocusCycleRoot(boolean focusCycleRoot) {
//        aComponent.setFocusCycleRoot(focusCycleRoot);
//    }
//
//    public boolean isFocusCycleRoot() {
//        return aComponent.isFocusCycleRoot();
//    }
//
///*
//    public void setFocusTraversalPolicyProvider(boolean provider) {
//        aComponent.setFocusTraversalPolicyProvider(provider);
//    }
//
//    public boolean isFocusTraversalPolicyProvider() {
//        return aComponent.isFocusTraversalPolicyProvider();
//    }
//*/
//
//    public void transferFocusDownCycle() {
//        aComponent.transferFocusDownCycle();
//    }
//
//    public void applyComponentOrientation(ComponentOrientation o) {
//        aComponent.applyComponentOrientation(o);
//    }
//
//    public void addPropertyChangeListener(PropertyChangeListener listener) {
//        aComponent.addPropertyChangeListener(listener);
//    }
//
//    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
//        aComponent.addPropertyChangeListener(propertyName, listener);
//    }
//
//    public String getName() {
//        return aComponent.getName();
//    }
//
//    public void setName(String name) {
//        aComponent.setName(name);
//    }
//
//    public Container getParent() {
//        return aComponent.getParent();
//    }
//
//    public ComponentPeer getPeer() {
//        return aComponent.getPeer();
//    }
//
//    public void setDropTarget(DropTarget dt) {
//        aComponent.setDropTarget(dt);
//    }
//
//    public DropTarget getDropTarget() {
//        return aComponent.getDropTarget();
//    }
//
//    public GraphicsConfiguration getGraphicsConfiguration() {
//        return aComponent.getGraphicsConfiguration();
//    }
//
///*
//    public Object getTreeLock() {
//        return aComponent.getTreeLock();
//    }
//*/
//
//    public Toolkit getToolkit() {
//        return aComponent.getToolkit();
//    }
//
//    public boolean isValid() {
//        return aComponent.isValid();
//    }
//
//    public boolean isDisplayable() {
//        return aComponent.isDisplayable();
//    }
//
//    public boolean isVisible() {
//        return aComponent.isVisible();
//    }
//
//    public Point getMousePosition() throws HeadlessException {
//        return aComponent.getMousePosition();
//    }
//
//    public boolean isShowing() {
//        return aComponent.isShowing();
//    }
//
//    public boolean isEnabled() {
//        return aComponent.isEnabled();
//    }
//
//    public void enable(boolean b) {
//        aComponent.enable(b);
//    }
//
//    public void enableInputMethods(boolean enable) {
//        aComponent.enableInputMethods(enable);
//    }
//
//    public void show() {
//        aComponent.show();
//    }
//
//    public void show(boolean b) {
//        aComponent.show(b);
//    }
//
//    public void hide() {
//        aComponent.hide();
//    }
//
//    public Color getForeground() {
//        return aComponent.getForeground();
//    }
//
//    public boolean isForegroundSet() {
//        return aComponent.isForegroundSet();
//    }
//
//    public Color getBackground() {
//        return aComponent.getBackground();
//    }
//
//    public boolean isBackgroundSet() {
//        return aComponent.isBackgroundSet();
//    }
//
//    public Font getFont() {
//        return aComponent.getFont();
//    }
//
//    public boolean isFontSet() {
//        return aComponent.isFontSet();
//    }
//
//    public Locale getLocale() {
//        return aComponent.getLocale();
//    }
//
//    public void setLocale(Locale l) {
//        aComponent.setLocale(l);
//    }
//
//    public ColorModel getColorModel() {
//        return aComponent.getColorModel();
//    }
//
//    public Point getLocation() {
//        return aComponent.getLocation();
//    }
//
//    public Point getLocationOnScreen() {
//        return aComponent.getLocationOnScreen();
//    }
//
//    public Point location() {
//        return aComponent.location();
//    }
//
//    public void setLocation(int x, int y) {
//        aComponent.setLocation(x, y);
//    }
//
//    public void move(int x, int y) {
//        aComponent.move(x, y);
//    }
//
//    public void setLocation(Point p) {
//        aComponent.setLocation(p);
//    }
//
//    public Dimension getSize() {
//        return aComponent.getSize();
//    }
//
//    public Dimension size() {
//        return aComponent.size();
//    }
//
//    public void setSize(int width, int height) {
//        aComponent.setSize(width, height);
//    }
//
//    public void resize(int width, int height) {
//        aComponent.resize(width, height);
//    }
//
//    public void setSize(Dimension d) {
//        aComponent.setSize(d);
//    }
//
//    public void resize(Dimension d) {
//        aComponent.resize(d);
//    }
//
//    public Rectangle getBounds() {
//        return aComponent.getBounds();
//    }
//
//    public Rectangle bounds() {
//        return aComponent.bounds();
//    }
//
//    public void setBounds(int x, int y, int width, int height) {
//        aComponent.setBounds(x, y, width, height);
//    }
//
//    public void setBounds(Rectangle r) {
//        aComponent.setBounds(r);
//    }
//
//    public boolean isLightweight() {
//        return aComponent.isLightweight();
//    }
//
//    public boolean isPreferredSizeSet() {
//        return aComponent.isPreferredSizeSet();
//    }
//
//    public boolean isMinimumSizeSet() {
//        return aComponent.isMinimumSizeSet();
//    }
//
//    public boolean isMaximumSizeSet() {
//        return aComponent.isMaximumSizeSet();
//    }
//
//    public void setCursor(Cursor cursor) {
//        aComponent.setCursor(cursor);
//    }
//
//    public Cursor getCursor() {
//        return aComponent.getCursor();
//    }
//
//    public boolean isCursorSet() {
//        return aComponent.isCursorSet();
//    }
//
//    public void paintAll(Graphics g) {
//        aComponent.paintAll(g);
//    }
//
//    public void repaint() {
//        aComponent.repaint();
//    }
//
//    public void repaint(long tm) {
//        aComponent.repaint(tm);
//    }
//
//    public void repaint(int x, int y, int width, int height) {
//        aComponent.repaint(x, y, width, height);
//    }
//
//    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
//        return aComponent.imageUpdate(img, infoflags, x, y, w, h);
//    }
//
//    public Image createImage(ImageProducer producer) {
//        return aComponent.createImage(producer);
//    }
//
//    public Image createImage(int width, int height) {
//        return aComponent.createImage(width, height);
//    }
//
//    public VolatileImage createVolatileImage(int width, int height) {
//        return aComponent.createVolatileImage(width, height);
//    }
//
//    public VolatileImage createVolatileImage(int width, int height, ImageCapabilities caps) throws AWTException {
//        return aComponent.createVolatileImage(width, height, caps);
//    }
//
//    public boolean prepareImage(Image image, ImageObserver observer) {
//        return aComponent.prepareImage(image, observer);
//    }
//
//    public boolean prepareImage(Image image, int width, int height, ImageObserver observer) {
//        return aComponent.prepareImage(image, width, height, observer);
//    }
//
//    public int checkImage(Image image, ImageObserver observer) {
//        return aComponent.checkImage(image, observer);
//    }
//
//    public int checkImage(Image image, int width, int height, ImageObserver observer) {
//        return aComponent.checkImage(image, width, height, observer);
//    }
//
//    public void setIgnoreRepaint(boolean ignoreRepaint) {
//        aComponent.setIgnoreRepaint(ignoreRepaint);
//    }
//
//    public boolean getIgnoreRepaint() {
//        return aComponent.getIgnoreRepaint();
//    }
//
//    public boolean inside(int x, int y) {
//        return aComponent.inside(x, y);
//    }
//
//    public boolean contains(Point p) {
//        return aComponent.contains(p);
//    }
//
///*
//    public void dispatchEvent(AWTEvent e) {
//        aComponent.dispatchEvent(e);
//    }
//*/
//
//    public boolean postEvent(Event e) {
//        return aComponent.postEvent(e);
//    }
//
//    public void addComponentListener(ComponentListener l) {
//        aComponent.addComponentListener(l);
//    }
//
//    public void removeComponentListener(ComponentListener l) {
//        aComponent.removeComponentListener(l);
//    }
//
//    public ComponentListener[] getComponentListeners() {
//        return aComponent.getComponentListeners();
//    }
//
//    public void addFocusListener(FocusListener l) {
//        aComponent.addFocusListener(l);
//    }
//
//    public void removeFocusListener(FocusListener l) {
//        aComponent.removeFocusListener(l);
//    }
//
//    public FocusListener[] getFocusListeners() {
//        return aComponent.getFocusListeners();
//    }
//
//    public void addHierarchyListener(HierarchyListener l) {
//        aComponent.addHierarchyListener(l);
//    }
//
//    public void removeHierarchyListener(HierarchyListener l) {
//        aComponent.removeHierarchyListener(l);
//    }
//
//    public HierarchyListener[] getHierarchyListeners() {
//        return aComponent.getHierarchyListeners();
//    }
//
//    public void addHierarchyBoundsListener(HierarchyBoundsListener l) {
//        aComponent.addHierarchyBoundsListener(l);
//    }
//
//    public void removeHierarchyBoundsListener(HierarchyBoundsListener l) {
//        aComponent.removeHierarchyBoundsListener(l);
//    }
//
//    public HierarchyBoundsListener[] getHierarchyBoundsListeners() {
//        return aComponent.getHierarchyBoundsListeners();
//    }
//
//    public void addKeyListener(KeyListener l) {
//        aComponent.addKeyListener(l);
//    }
//
//    public void removeKeyListener(KeyListener l) {
//        aComponent.removeKeyListener(l);
//    }
//
//    public KeyListener[] getKeyListeners() {
//        return aComponent.getKeyListeners();
//    }
//
//    public void addMouseListener(MouseListener l) {
//        aComponent.addMouseListener(l);
//    }
//
//    public void removeMouseListener(MouseListener l) {
//        aComponent.removeMouseListener(l);
//    }
//
//    public MouseListener[] getMouseListeners() {
//        return aComponent.getMouseListeners();
//    }
//
//    public void addMouseMotionListener(MouseMotionListener l) {
//        aComponent.addMouseMotionListener(l);
//    }
//
//    public void removeMouseMotionListener(MouseMotionListener l) {
//        aComponent.removeMouseMotionListener(l);
//    }
//
//    public MouseMotionListener[] getMouseMotionListeners() {
//        return aComponent.getMouseMotionListeners();
//    }
//
//    public void addMouseWheelListener(MouseWheelListener l) {
//        aComponent.addMouseWheelListener(l);
//    }
//
//    public void removeMouseWheelListener(MouseWheelListener l) {
//        aComponent.removeMouseWheelListener(l);
//    }
//
//    public MouseWheelListener[] getMouseWheelListeners() {
//        return aComponent.getMouseWheelListeners();
//    }
//
//    public void addInputMethodListener(InputMethodListener l) {
//        aComponent.addInputMethodListener(l);
//    }
//
//    public void removeInputMethodListener(InputMethodListener l) {
//        aComponent.removeInputMethodListener(l);
//    }
//
//    public InputMethodListener[] getInputMethodListeners() {
//        return aComponent.getInputMethodListeners();
//    }
//
//    public InputMethodRequests getInputMethodRequests() {
//        return aComponent.getInputMethodRequests();
//    }
//
//    public InputContext getInputContext() {
//        return aComponent.getInputContext();
//    }
//
///*
//    public void enableEvents(long eventsToEnable) {
//        aComponent.enableEvents(eventsToEnable);
//    }
//
//    public void disableEvents(long eventsToDisable) {
//        aComponent.disableEvents(eventsToDisable);
//    }
//
//
//    public AWTEvent coalesceEvents(AWTEvent existingEvent, AWTEvent newEvent) {
//        return aComponent.coalesceEvents(existingEvent, newEvent);
//    }
//
//    public void processComponentEvent(ComponentEvent e) {
//        aComponent.processComponentEvent(e);
//    }
//
//    public void processFocusEvent(FocusEvent e) {
//        aComponent.processFocusEvent(e);
//    }
//
//    public void processMouseWheelEvent(MouseWheelEvent e) {
//        aComponent.processMouseWheelEvent(e);
//    }
//
//    public void processInputMethodEvent(InputMethodEvent e) {
//        aComponent.processInputMethodEvent(e);
//    }
//
//    public void processHierarchyEvent(HierarchyEvent e) {
//        aComponent.processHierarchyEvent(e);
//    }
//
//    public void processHierarchyBoundsEvent(HierarchyEvent e) {
//        aComponent.processHierarchyBoundsEvent(e);
//    }
//*/
//    public boolean handleEvent(Event evt) {
//        return aComponent.handleEvent(evt);
//    }
//
//    public boolean mouseDown(Event evt, int x, int y) {
//        return aComponent.mouseDown(evt, x, y);
//    }
//
//    public boolean mouseDrag(Event evt, int x, int y) {
//        return aComponent.mouseDrag(evt, x, y);
//    }
//
//    public boolean mouseUp(Event evt, int x, int y) {
//        return aComponent.mouseUp(evt, x, y);
//    }
//
//    public boolean mouseMove(Event evt, int x, int y) {
//        return aComponent.mouseMove(evt, x, y);
//    }
//
//    public boolean mouseEnter(Event evt, int x, int y) {
//        return aComponent.mouseEnter(evt, x, y);
//    }
//
//    public boolean mouseExit(Event evt, int x, int y) {
//        return aComponent.mouseExit(evt, x, y);
//    }
//
//    public boolean keyDown(Event evt, int key) {
//        return aComponent.keyDown(evt, key);
//    }
//
//    public boolean keyUp(Event evt, int key) {
//        return aComponent.keyUp(evt, key);
//    }
//
//    public boolean action(Event evt, Object what) {
//        return aComponent.action(evt, what);
//    }
//
//    public boolean gotFocus(Event evt, Object what) {
//        return aComponent.gotFocus(evt, what);
//    }
//
//    public boolean lostFocus(Event evt, Object what) {
//        return aComponent.lostFocus(evt, what);
//    }
//
//    public boolean isFocusTraversable() {
//        return aComponent.isFocusTraversable();
//    }
//
//    public boolean isFocusable() {
//        return aComponent.isFocusable();
//    }
//
//    public void setFocusable(boolean focusable) {
//        aComponent.setFocusable(focusable);
//    }
//
//    public void setFocusTraversalKeysEnabled(boolean focusTraversalKeysEnabled) {
//        aComponent.setFocusTraversalKeysEnabled(focusTraversalKeysEnabled);
//    }
//
//    public boolean getFocusTraversalKeysEnabled() {
//        return aComponent.getFocusTraversalKeysEnabled();
//    }
//
//    public void transferFocus() {
//        aComponent.transferFocus();
//    }
//
//    public Container getFocusCycleRootAncestor() {
//        return aComponent.getFocusCycleRootAncestor();
//    }
//
//    public void nextFocus() {
//        aComponent.nextFocus();
//    }
//
//    public void transferFocusUpCycle() {
//        aComponent.transferFocusUpCycle();
//    }
//
//    public boolean hasFocus() {
//        return aComponent.hasFocus();
//    }
//
//    public boolean isFocusOwner() {
//        return aComponent.isFocusOwner();
//    }
//
//    public void add(PopupMenu popup) {
//        aComponent.add(popup);
//    }
//
//    public void remove(MenuComponent popup) {
//        aComponent.remove(popup);
//    }
//
//    public String toString() {
//        return aComponent.toString();
//    }
//
//    public void list() {
//        aComponent.list();
//    }
//
//    public void list(PrintStream out) {
//        aComponent.list(out);
//    }
//
//    public void list(PrintWriter out) {
//        aComponent.list(out);
//    }
//
//    public void removePropertyChangeListener(PropertyChangeListener listener) {
//        aComponent.removePropertyChangeListener(listener);
//    }
//
//    public PropertyChangeListener[] getPropertyChangeListeners() {
//        return aComponent.getPropertyChangeListeners();
//    }
//
//    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
//        aComponent.removePropertyChangeListener(propertyName, listener);
//    }
//
//    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
//        return aComponent.getPropertyChangeListeners(propertyName);
//    }
//
///*
//    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
//        aComponent.firePropertyChange(propertyName, oldValue, newValue);
//    }
//*/
//
//    public void firePropertyChange(String propertyName, byte oldValue, byte newValue) {
//        aComponent.firePropertyChange(propertyName, oldValue, newValue);
//    }
//
//    public void firePropertyChange(String propertyName, short oldValue, short newValue) {
//        aComponent.firePropertyChange(propertyName, oldValue, newValue);
//    }
//
//    public void firePropertyChange(String propertyName, long oldValue, long newValue) {
//        aComponent.firePropertyChange(propertyName, oldValue, newValue);
//    }
//
//    public void firePropertyChange(String propertyName, float oldValue, float newValue) {
//        aComponent.firePropertyChange(propertyName, oldValue, newValue);
//    }
//
//    public void firePropertyChange(String propertyName, double oldValue, double newValue) {
//        aComponent.firePropertyChange(propertyName, oldValue, newValue);
//    }
//
//    public void setComponentOrientation(ComponentOrientation o) {
//        aComponent.setComponentOrientation(o);
//    }

}
