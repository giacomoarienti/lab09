package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String BROWSE_BTN = "Browse..";
    private static final String TITLE = "A very simple GUI application";
    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();

    /**
     * Creates a new BadIOGUI.
     * 
     * @throws IOException
     */
    public SimpleGUIWithFileChooser() {
        /* create main panel */
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* create text area with save button */
        final JTextArea text = new JTextArea(PROPORTION, PROPORTION);
        final JButton save = new JButton("Save");
        /* save action listener */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.save(text.getText());
                    JOptionPane.showMessageDialog(frame, "File saved");
                } catch (final Exception e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        /* browse */
        final JPanel browsePanel = new JPanel();
        browsePanel.setLayout(new BoxLayout(browsePanel, BoxLayout.X_AXIS));
        final JTextField field = new JTextField();
        field.setEditable(false);
        updateFilePath(field);
        final JButton browse = new JButton(BROWSE_BTN);
        /* browse handler */
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ev) {
                final JFileChooser fileChooser = new JFileChooser(controller.getFile());
                switch (fileChooser.showSaveDialog(frame)) {
                    case JFileChooser.APPROVE_OPTION -> {
                        controller.setFile(fileChooser.getSelectedFile());
                        updateFilePath(field);
                    }
                    case JFileChooser.CANCEL_OPTION -> {
                    }
                    default -> {
                        JOptionPane.showConfirmDialog(frame, "An error occured");
                    }
                }
            }

        });
        browsePanel.add(field);
        browsePanel.add(browse);
        /* add components to canvas */
        canvas.add(browsePanel, BorderLayout.NORTH);
        canvas.add(text, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
    }

    private void updateFilePath(final JTextField field) {
        try {
            field.setText(controller.getFilePath());
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        // 6. In ``display()``, use JFrame.pack() to resize the frame to the minimum
        // size prior to displaying
        frame.pack();
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
    }
}
