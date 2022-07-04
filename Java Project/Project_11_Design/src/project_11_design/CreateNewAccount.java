package project_11_design;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class CreateNewAccount extends JFrame{
    
    private Container      mainContainer_3;
    private JPanel         panel1, panel2, panel3, panel4;
    private JLabel         userTypeLabel, nameLabel, passwordLabel, creditNumLabel, houseNumLabel, subcityLabel, initialBalanceLabel, validateInput_3_Label;
    private JComboBox      userTypeCombo, subcityCombo;
    private JTextField     nameTextField, creditNumTextField, houseNumTextField, initialBalanceTextField;
    private JPasswordField passwordPasswordField;
    private JButton        createAccountButton, cancelButton;
    private static int buyerId = 0;
    private static int sellerId = 0;
    
    private final String[] SUBCITIES_STRING = {"Addis Ketema", "Akaki Kaliti", "Arada", "Bole", "Gulele", 
                                               "Kirkos", "Kolfe Keranio", "Lideta", "Nifas Silk Lafto", "Yeka"};
    private final String[] USER_TYPE_STRING = {"Buyer", "Seller"};
    
    public CreateNewAccount(){
        
        super("Creating A New Account");
        mainContainer_3 = getContentPane();
        
        panel1 = new JPanel(new GridLayout(7, 2, 15, 10));
        panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 60, 35));
        panel3 = new JPanel(new BorderLayout(5, 5));
        panel4 = new JPanel(new BorderLayout(10, 10));
        
        panel4.setBorder(BorderFactory.createTitledBorder(""));
        panel3.setBorder(BorderFactory.createTitledBorder(null, " Create Your Account Here ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        
        userTypeLabel = new JLabel("User Type : ");
        nameLabel = new JLabel("Name : ");
        passwordLabel = new JLabel("Password : ");
        creditNumLabel = new JLabel("Credit Card Number : ");
        houseNumLabel = new JLabel("House Number : ");
        subcityLabel = new JLabel("Subcity : ");
        initialBalanceLabel = new JLabel("Initial Balance : ");
        
        userTypeLabel.setHorizontalAlignment(JLabel.TRAILING);
        userTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        nameLabel.setHorizontalAlignment(JLabel.TRAILING);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        passwordLabel.setHorizontalAlignment(JLabel.TRAILING);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        creditNumLabel.setHorizontalAlignment(JLabel.TRAILING);
        creditNumLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        houseNumLabel.setHorizontalAlignment(JLabel.TRAILING);
        houseNumLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        subcityLabel.setHorizontalAlignment(JLabel.TRAILING);
        subcityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        initialBalanceLabel.setHorizontalAlignment(JLabel.TRAILING);
        initialBalanceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        userTypeCombo = new JComboBox(USER_TYPE_STRING);
        subcityCombo = new JComboBox(SUBCITIES_STRING);
        
        nameTextField = new JTextField(ALLBITS);
        creditNumTextField = new JTextField(ALLBITS);
        houseNumTextField = new JTextField(ALLBITS);
        initialBalanceTextField = new JTextField(ALLBITS);
        
        passwordPasswordField = new JPasswordField(ALLBITS);
        
        createAccountButton = new JButton("Create Account");
        cancelButton = new JButton("Cancel");
        
        createAccountButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        validateInput_3_Label = new JLabel("Validate Input Here");
        validateInput_3_Label.setFont(new Font("Tahoma", Font.BOLD, 15));
        validateInput_3_Label.setForeground(Color.red);
        
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userTypeCombo.getSelectedItem().equals("Buyer"))
                    JOptionPane.showMessageDialog(null, "Welcome Buyer " + nameLabel.getText() + " Your Buyer Id Is " + ++buyerId, "Done Creating The Account", JOptionPane.INFORMATION_MESSAGE);
                if(userTypeCombo.getSelectedItem().equals("Seller"))
                    JOptionPane.showMessageDialog(null, "Welcome Seller " + nameLabel.getText() + " Your Seller Id Is " + ++sellerId, "Done Creating The Account", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        panel1.add(userTypeLabel);
        panel1.add(userTypeCombo);
        panel1.add(nameLabel);
        panel1.add(nameTextField);
        panel1.add(passwordLabel);
        panel1.add(passwordPasswordField);
        panel1.add(creditNumLabel);
        panel1.add(creditNumTextField);
        panel1.add(houseNumLabel);
        panel1.add(houseNumTextField);
        panel1.add(subcityLabel);
        panel1.add(subcityCombo);
        panel1.add(initialBalanceLabel);
        panel1.add(initialBalanceTextField);
        
        panel2.add(createAccountButton);
        panel2.add(cancelButton);
        
        panel3.add(panel1, BorderLayout.CENTER);
        panel3.add(panel2, BorderLayout.SOUTH);
        
        panel4.add(new JLabel(), BorderLayout.NORTH);
        panel4.add(panel3, BorderLayout.CENTER);
        panel4.add(new JLabel(), BorderLayout.EAST);
        panel4.add(new JPanel(), BorderLayout.WEST);
        panel4.add(validateInput_3_Label, BorderLayout.SOUTH);
        
        mainContainer_3.add(panel4);
        
        setResizable(false);
        
    }
    
}
