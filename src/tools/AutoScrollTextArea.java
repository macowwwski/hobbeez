package tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class AutoScrollTextArea extends JFrame {

    static int counter = 0;
    JPanel panel = null;
    JTextArea tArea = null;
    JScrollPane sp = null;
    JButton button = null;
    Timer timer = null;
  

    public AutoScrollTextArea() {
        inicialize();
    }

    private void inicialize() {
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    button = new JButton("Stop!");
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            if (timer.isRunning()) {
                                button.setText("Start");
                                timer.stop();
                            } else {
                                button.setText("Stop!");
                                timer.start();
                            }
                        }
                    });

                    tArea = new JTextArea();
                    tArea.setAutoscrolls(true);
                    tArea.setWrapStyleWord(true);
                    tArea.setLineWrap(true);
                    tArea.setFont(new Font("Serif", Font.ITALIC, 20));
                    tArea.setEditable(false);
                    tArea.setBackground(Color.WHITE);
                    tArea.setForeground(Color.BLACK);

                    sp = new JScrollPane(tArea);
                    sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    panel = new JPanel();
                    panel.setLayout(new BorderLayout());
                    panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    panel.setPreferredSize(new Dimension(300, 300));
                    panel.add(sp, BorderLayout.CENTER);
                    panel.add(button, BorderLayout.SOUTH);
                    getContentPane().add(panel);

                   
                    setLocationRelativeTo(null);

                    //Set the JFrame properties
                    setResizable(true);
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   
                    pack();
                    setVisible(true);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            timer.stop();
        }

//Start the timer in the main thread
        timer = new Timer(1000, actionToPerform());
        timer.start();
    }
//This code runs in EDT

    private ActionListener actionToPerform() {
        return (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (counter < 100) {
                    /*In actual scenarios, this is where we will read the next line of the file/socket in a separate thread.
                     * Following the reading on one line, we need to update the UI in EDT. In this example, I have
                     * combined both in EDT, since the task is not time consuming
                     */
                    tArea.append("\n   This is line number: " + counter++);
                    
                    tArea.setCaretPosition(tArea.getCaretPosition());
                }
            }
        });
    }

    public static void main(String[] args) {
        new AutoScrollTextArea();
    }
}