package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * show user buy orderNumber
 */
public class OrderUUIDView extends JFrame{

    private JPanel contentPane;
    private JTextField textField;

    public OrderUUIDView(String uuid){
        setBounds(100, 100, 493, 187);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(55, 75, 370, 36);
        textField.setText(uuid);
        contentPane.add(textField);
        textField.setFont(new Font("SimSun", Font.BOLD, 16));
        textField.setColumns(50);
        textField.setEnabled(false);



        JButton copy = new JButton("copy");
        copy.setBounds(300, 25, 90, 40);
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stsel = new StringSelection(uuid);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
                JOptionPane.showMessageDialog(null,"copy successful");
            }
        });
        contentPane.add(copy);


        JLabel lblYourOrderNumber = new JLabel("Your Order Number:");
        lblYourOrderNumber.setFont(new Font("SimSun", Font.PLAIN, 23));
        lblYourOrderNumber.setBounds(10, 20, 287, 35);
        contentPane.add(lblYourOrderNumber);
    }
}
