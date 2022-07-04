package project_11_design;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class LoginPage extends JFrame{
    
    private Container      mainContainer;
    private JPanel         panel1, panel2, panel3, panel4, panel5;
    private JLabel         segnoGebeyaLabel, userNameLabel, passwordLabel, userTypeLabel, validateInputLabel;
    private JTextField     userNameTextField;
    private JPasswordField passwordPasswordfield;
    private JComboBox      userTypeCombo;
    private JButton        loginButton, createNewAccountButton;
    private final String[] USER_TYPE_STRING = {"Buyer", "Seller"};
    
    public LoginPage(){
        super("Login Page");
        mainContainer = getContentPane();
        
        panel1 = new JPanel(new GridLayout(3, 2, 16, 10));
        panel2 = new JPanel(new BorderLayout(280, 10));
        panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 35));
        panel4 = new JPanel(new BorderLayout(10, 10));
        panel5 = new JPanel(new BorderLayout(10, 70));
        
        panel4.setBorder(BorderFactory.createTitledBorder(null, " Login Or Create A New Account ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        panel5.setBorder(BorderFactory.createTitledBorder(""));
        
        segnoGebeyaLabel = new JLabel("SegnoGebeya.com");
        segnoGebeyaLabel.setHorizontalAlignment(JLabel.CENTER);
        segnoGebeyaLabel.setForeground(new Color(51, 73, 255));
        segnoGebeyaLabel.setFont(new Font("Chiller", Font.BOLD, 115));
        
        userNameLabel = new JLabel("User Name : ");
        passwordLabel = new JLabel("Password : ");
        userTypeLabel = new JLabel("User Type : ");
        
        userNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        userNameLabel.setHorizontalAlignment(JLabel.TRAILING);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        passwordLabel.setHorizontalAlignment(JLabel.TRAILING);
        userTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        userTypeLabel.setHorizontalAlignment(JLabel.TRAILING);
        
        userNameTextField = new JTextField(ALLBITS);
        passwordPasswordfield = new JPasswordField(ALLBITS);
        userTypeCombo = new JComboBox(USER_TYPE_STRING);
        
        loginButton = new JButton("Login");
        createNewAccountButton = new JButton("Create New Account");
        
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        createNewAccountButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateNewAccount createNewAccount = new CreateNewAccount();
                createNewAccount.pack();
                createNewAccount.setLocationRelativeTo(null);
                createNewAccount.setVisible(true);
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userTypeCombo.getSelectedItem().equals("Buyer")){
                    BuyersMenu buyersMenu = new BuyersMenu();
                    buyersMenu.setLocationRelativeTo(null);
                    buyersMenu.setVisible(true);
                }
                if(userTypeCombo.getSelectedItem().equals("Seller")){
                    SellersMenu sellersMenu = new SellersMenu();
                    sellersMenu.setLocationRelativeTo(null);
                    sellersMenu.setVisible(true);
                }
            }
        });
        
        validateInputLabel = new JLabel("Validate User Input Here");
        validateInputLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        validateInputLabel.setForeground(Color.red);
        
        panel1.add(userNameLabel);
        panel1.add(userNameTextField);
        panel1.add(passwordLabel);
        panel1.add(passwordPasswordfield);
        panel1.add(userTypeLabel);
        panel1.add(userTypeCombo);
        
        panel2.add(panel1, BorderLayout.CENTER);
        panel2.add(new JLabel(), BorderLayout.EAST);
        
        panel3.add(loginButton);
        panel3.add(createNewAccountButton);
        
        panel4.add(panel2, BorderLayout.CENTER);
        panel4.add(panel3, BorderLayout.SOUTH);
        
        panel5.add(segnoGebeyaLabel, BorderLayout.NORTH);
        panel5.add(panel4, BorderLayout.CENTER);
        panel5.add(validateInputLabel, BorderLayout.SOUTH);
        
//        panel1.setBackground(new Color(102, 102, 102));
//        panel2.setBackground(new Color(102, 102, 102));
//        panel3.setBackground(new Color(102, 102, 102));
//        panel4.setBackground(new Color(102, 102, 102));
//        panel5.setBackground(new Color(102, 102, 102));
//        
//        userTypeCombo.setBackground(new Color(102, 102, 102));
//        passwordPasswordfield.setBackground(new Color(102, 102, 102));
//        userNameTextField.setBackground(new Color(102, 102, 102));
//        loginButton.setBackground(new Color(102, 102, 102));
//        createNewAccountButton.setBackground(new Color(102, 102, 102));
//        loginButton.setForeground(Color.WHITE);
//        createNewAccountButton.setForeground(Color.WHITE);
//        
//        segnoGebeyaLabel.setForeground(Color.LIGHT_GRAY);
//        userNameLabel.setForeground(Color.LIGHT_GRAY);
//        passwordLabel.setForeground(Color.LIGHT_GRAY);
//        userTypeLabel.setForeground(Color.LIGHT_GRAY);
//        validateInputLabel.setForeground(new Color(255, 100, 91));
        
        mainContainer.add(panel5);
    }
    
    public static void main(String[] args) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        LoginPage loginPage = new LoginPage();
        
        loginPage.pack();
        loginPage.setResizable(false);
        loginPage.setLocationRelativeTo(null);
        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPage.setVisible(true);
        
    }
    
}