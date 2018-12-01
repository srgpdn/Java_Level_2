package lesson4;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class MyWindow extends JFrame {
    private JPanel jpn;
    private JTextField jtf;
    private JButton jbt;
    private JScrollPane jsp;
    private JTextArea jta;
    private final String TEXT_MSG = "Input message here";
    private boolean flagMsg = true;

    public MyWindow() {

        setTitle("MyWindow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 200, 400, 400);

        jpn = new JPanel();
        jpn.setLayout(new BoxLayout(jpn, BoxLayout.X_AXIS));

        jtf = new JTextField(TEXT_MSG,50);
        jtf.addActionListener(e -> sendMsg());
        jtf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (flagMsg) {
                    jtf.setText("");
                    flagMsg = false;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        jbt = new JButton("Send");
        jbt.addActionListener(e -> sendMsg());

        jpn.add(jtf);
        jpn.add(jbt);

        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        jsp = new JScrollPane(jta);

        add(jsp, BorderLayout.CENTER);
        add(jpn, BorderLayout.SOUTH);


        setVisible(true);


    }

    private void sendMsg() {
       if (!flagMsg) {
           jta.append(jtf.getText() + '\n');
           jtf.setText(TEXT_MSG);
           jtf.grabFocus();
           flagMsg = true;
       }
    }
}
