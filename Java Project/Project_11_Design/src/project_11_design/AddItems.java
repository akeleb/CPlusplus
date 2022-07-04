package project_11_design;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AddItems extends JFrame{
    
    private Container   mainContainer_2;
    private JPanel      panel1, panel2, panel3, panel4, panel5, panel6;
    private JLabel      itemTypeLabel, itemNameLabel, quantityLabel, unitPriceLabel, discriptionLabel, validateInput_2_Label;
    private JComboBox   itemtypeCombo;
    private JTextField  itemNameTextField, quantitytextField, unitPriceTextField;
    private JTextArea   discriptionTextArea;
    private JScrollPane discriptionJScrollPane;
    private JButton     addTheItemButton, cancelButton;
    private final static String[] ALL_ITEM_TYPES = {"Clothings", "Construction & Home", "Cosmetics & Beauty", "Educationals", "Electronics", "Food & Drink", "Furniture", "Jewelry", "Kitchen Materials", "Others"};
    
    public AddItems(){
        
        super("Adding Items To Store");
        mainContainer_2 = getContentPane();
        
        panel1 = new JPanel(new GridLayout(4, 2, 5, 15));
        panel2 = new JPanel(new GridLayout(1, 2, 5, 5));
        panel3 = new JPanel(new GridLayout(2, 1, 5, 5));
        panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 15));
        panel5 = new JPanel(new BorderLayout(15, 5));
        panel6 = new JPanel(new BorderLayout(20, 10));
        
        panel5.setBorder(BorderFactory.createTitledBorder(null, " Add Your Items Here ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        
        panel6.setBorder(BorderFactory.createTitledBorder("       "));
        
        itemTypeLabel    = new JLabel("Item Type : ");
        itemNameLabel    = new JLabel("Item Name : ");
        quantityLabel    = new JLabel("Quantity : ");
        unitPriceLabel   = new JLabel("Unit Price : ");
        
        itemTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        itemTypeLabel.setHorizontalAlignment(JLabel.TRAILING);
        itemNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        itemNameLabel.setHorizontalAlignment(JLabel.TRAILING);
        quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        quantityLabel.setHorizontalAlignment(JLabel.TRAILING);
        unitPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        unitPriceLabel.setHorizontalAlignment(JLabel.TRAILING);
        
        itemtypeCombo = new JComboBox(ALL_ITEM_TYPES);
        
        itemNameTextField  = new JTextField(ALLBITS);
        quantitytextField  = new JTextField(ALLBITS);
        unitPriceTextField = new JTextField(ALLBITS);
        
        discriptionLabel = new JLabel("Discription : ");
        discriptionLabel.setHorizontalAlignment(JLabel.TRAILING);
        discriptionLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        discriptionTextArea = new JTextArea("Item Discriptions Here", 10, 4);
        discriptionTextArea.setLineWrap(true);
        discriptionTextArea.setWrapStyleWord(true);
        
        discriptionJScrollPane = new JScrollPane(discriptionTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        validateInput_2_Label = new JLabel("Validate Input Here");
        validateInput_2_Label.setFont(new Font("Tahoma", Font.BOLD, 15));
        validateInput_2_Label.setForeground(Color.red);
        
        addTheItemButton = new JButton("Add Item");
        cancelButton = new JButton("Cancel");
        
        addTheItemButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        addTheItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "An Item Was Added", "Items Added", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        panel1.add(itemTypeLabel);
        panel1.add(itemtypeCombo);
        panel1.add(itemNameLabel);
        panel1.add(itemNameTextField);
        panel1.add(quantityLabel);
        panel1.add(quantitytextField);
        panel1.add(unitPriceLabel);
        panel1.add(unitPriceTextField);
        
        panel2.add(discriptionLabel);
        panel2.add(discriptionJScrollPane);
        
        panel3.add(panel1);
        panel3.add(panel2);
        
        panel4.add(addTheItemButton);
        panel4.add(cancelButton);
        
        panel5.add(new JLabel(), BorderLayout.NORTH);
        panel5.add(panel3, BorderLayout.CENTER);
        panel5.add(panel4, BorderLayout.SOUTH);
        
        panel6.add(new JLabel(), BorderLayout.NORTH);
        panel6.add(panel5, BorderLayout.CENTER);
        panel6.add(new JLabel(), BorderLayout.EAST);
        panel6.add(new JLabel(), BorderLayout.WEST);
        panel6.add(validateInput_2_Label, BorderLayout.SOUTH);
        
        mainContainer_2.add(panel6);
        
        setResizable(false);
        
    }
    
}
