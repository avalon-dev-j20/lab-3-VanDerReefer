package colorpicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class ColorPicker extends JFrame {
    private JPanel colorPanel = new JPanel();
    private JPanel rgbPanel = new JPanel();
    private JSlider redSlider = new JSlider(0, 255, 125);
    private JSlider greenSlider = new JSlider(0, 255, 125);
    private JSlider blueSlider = new JSlider(0, 255, 125);
    private JLabel redLabel = new JLabel(" Red:");
    private JLabel greenLabel = new JLabel(" Green:");
    private JLabel blueLabel = new JLabel(" Blue:");

    private static String TITLE = "Color picker";
    private static int START_HIGH = 300;
    private static int START_WIDE = 600;

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();

    public ColorPicker() {
        super(TITLE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(START_WIDE, START_HIGH);
        setResizable(true);
        setLayout(new GridLayout());

        add(buildColorPanel());
        add(buildRGBPanel());

        redSlider.addChangeListener(e -> setColor());
        greenSlider.addChangeListener(e -> setColor());
        blueSlider.addChangeListener(e -> setColor());

        colorPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changeToolTip();
                copyToClipboard(colorPanel.getToolTipText());
            }
        });
    }

    private JPanel buildColorPanel(){
        colorPanel.setLayout(new BorderLayout());
        Border border = BorderFactory.createRaisedBevelBorder();
        colorPanel.setBorder(border);
        setColor();
        return colorPanel;
    }

    private JPanel buildRGBPanel(){
        rgbPanel.setLayout(new BoxLayout(rgbPanel, BoxLayout.Y_AXIS));
        rgbPanel.add(buildPanelForSlider(redLabel, redSlider));
        rgbPanel.add(buildPanelForSlider(greenLabel, greenSlider));
        rgbPanel.add(buildPanelForSlider(blueLabel, blueSlider));
        return rgbPanel;
    }

    private JPanel buildPanelForSlider(JLabel label, JSlider slider){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(255);

        Dimension dimension = new Dimension(50, 0);
        label.setPreferredSize(dimension);

        panel.add(label, BorderLayout.LINE_START);
        panel.add(slider);

        return panel;
    }

    private void setColor(){
        colorPanel.setBackground(new Color(redSlider.getValue(), 
                                            greenSlider.getValue(), 
                                            blueSlider.getValue()));
    }

    private void changeToolTip() {
        String colorHexCode = '#' +
                Integer.toHexString(colorPanel.getBackground().getRed()) +
                Integer.toHexString(colorPanel.getBackground().getGreen()) +
                Integer.toHexString(colorPanel.getBackground().getBlue());
        colorPanel.setToolTipText(colorHexCode.toUpperCase());
    }

    private void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }

}
