package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Controller controller = new SimpleController();

    public SimpleGUI() {
        final JPanel jpanel = new JPanel(new BorderLayout());
        frame.setContentPane(jpanel);
        final JTextField textField = new JTextField();
        jpanel.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        jpanel.add(textArea, BorderLayout.CENTER);
        /* buttons */
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        final JButton print = new JButton("Print");
        final JButton showHistory = new JButton("Show History");
        buttonPanel.add(print);
        buttonPanel.add(showHistory);
        jpanel.add(buttonPanel, BorderLayout.SOUTH);
        /* handlers */
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                controller.setNextOutput(textField.getText());
                try {
                    controller.output();
                    JOptionPane.showMessageDialog(frame, "Message displayed");
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final List<String> printedStrings = controller.getPrintedStrings();
                String res = "";
                for (final String string : printedStrings) {
                    res += string + "\n";
                }
                textArea.setText(res);
            }
        });
    }

    public void display() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI().display();
    }
}
