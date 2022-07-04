package project_11_design;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SellersMenu extends JFrame {
    
    private Container    mainContainer_4;
    private JMenuBar     menuBar;
    private JMenu        file, accountManagement, help;
    private JMenuItem    aboutUs, exit, logout, profile, share;
    private JScrollPane  yourItemsScrollPane;
    private JTable       yourItemsTable;
    private JComboBox    sortCombo, itemTypeCombo;
    private JButton      sortButton, addItemsButton, seeYourPaySlipButton, employeeOfTheDayButton, searchOrModifyButton, deleteButton, offerButton;
    private ButtonGroup  sortButtonGroup, searchOrModifyButtonGroup;
    private JLabel       productCodeLabel, productCodeLabel_2, productCodeLabel_3, ItemNameLabel, itemTypeLabel, itemQuantityLabel, itemPriceLabel, offPriceLabel, 
                         actualPriceLabel, discountedPriceLabel, actualQuantityLabel, moreThanWhatCopiesLabel, validateInputLabel, validateInputLabel_2, validateInputLabel_3;
    private JTextField   productCodeTextField, productCodeTextField_2, productCodeTextField_3, ItemNameTextField, itemQuantityTextField, actualQuantityTextField, itemPriceTextField, 
                         actualPriceTextField, discountedPriceTextField, offPriceTextField, moreThanWhatCopiesTextField;
    
    private JRadioButton ascendinglyRadioButton, descendinglyRadioButton, searchRadioButton, modifyRadioButton;
    
    private JPanel       yourItemsPanel, yourItemsInnerPanel, utilitiesPanel, searchOrEditPanel, searchOrEditInnerPanel1, searchOrEditInnerPanel2, searchOrEditInnerPanel3, offerDiscountPanel, offerDiscountInnerPanel1, offerDiscountInnerPanel2, 
                         deletePanel, mixerPanel1, mixerPanel2, mixerPanel3, mixerPanel4;
    
    private final static String[] ALL_ITEM_TYPES = {"Clothings", "Construction & Home", "Cosmetics & Beauty", "Educationals", "Electronics", "Food & Drink", "Furniture", "Jewelry", "Kitchen Materials", "Others"};
    private final String[] SORT_STRING = {"By Name", "By Type", "By Product Code", "By Quantity", "By Unit Price", "By Off Price", "By Off Quantity"};
    private String[] COLUMNS_STRING = {"Name", "Type", "Product Code", "Quantity", "Unit Price", "Employee Name", "Off Price", "Off Quantity"};
    private Object[][] Cell_Object = { {"laptops under 500$", "Laptop", 1233, 20, 499.00, "Muluken", 450.99, 10}, {null, null, null, null, null, null, null, null},  
                                       {null, null, null, null, null, null, null, null}, {null, null, null, null, null, null, null, null}, 
                                       {null, null, null, null, null, null, null, null}, {null, null, null, null, null, null, null, null}, 
                                       {null, null, null, null, null, null, null, null}, {null, null, null, null, null, null, null, null} };
    
    public SellersMenu() {
        
        super("Sellers Menu");
        mainContainer_4 = getContentPane();
        mainContainer_4.setLayout(new BorderLayout());          //menuBar(NORTH) + mixerPanel4(CENTER);
        
        //------------------------Creating a MenuBar Menus and MenuItems--------------------------
        
        menuBar = new JMenuBar();
        
        file = new JMenu("File");
        accountManagement = new JMenu("Account Management");
        help = new JMenu("Help");
        
        aboutUs = new JMenuItem("About", new ImageIcon(getClass().getResource("about.png")));
        exit = new JMenuItem("Exit", new ImageIcon(getClass().getResource("exit.png")));
        logout = new JMenuItem("Logout", new ImageIcon(getClass().getResource("logout.png")));
        profile = new JMenuItem("Profile", new ImageIcon(getClass().getResource("profile.png")));
        share = new JMenuItem("Share", new ImageIcon(getClass().getResource("share.png")));
        
        //------------------------Adding ActionListener--------------------------
        
        exit.addActionListener((ActionEvent e) -> {
            int yesNo = JOptionPane.showConfirmDialog(null, "Are You Sure??", "Logging Out", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if(yesNo == 0)
                dispose();
        });
        
        aboutUs.addActionListener((ActionEvent e) -> {
            
            String programmers = "";
            
            programmers += "\tProgrammers of this project\n" +
                            "---------------------------------------\n" +
                            "ETS 0850        Muluken  Getachew\t\n" +
                            "ETS 0843        Mulugeta  Berihun\t\n" +
                            "ETS 0915        Odda  Kussa\t\n" +
                            "ETS 0870        Naod  Aklilu\t\n" +
                            "ETS 1064        Tesfa  Teshome\t\n" +
                            "ETS 1159        Yabsira  Tedla\t\n" +
                            "ETS 1030        Surrawak  Tariku\t\n" +
                            "ETS 1077        Tessema  Keno\t\n";
            
            JOptionPane.showMessageDialog(null, programmers, "About SegnoGebeya.com", JOptionPane.INFORMATION_MESSAGE);
        });
        
        logout.addActionListener((ActionEvent e) -> {
            int yesNo = JOptionPane.showConfirmDialog(null, "Are You Sure??", "Logging Out", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            
            if(yesNo == 0)
                dispose();
        });
        
        share.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shareUs();
            }
        });
        
        //------------------------Adding some MenuItems to Menus--------------------------
        
        file.add(aboutUs);
        file.add(exit);
        
        accountManagement.add(logout);
        accountManagement.add(profile);
        accountManagement.add(share);
        
        //------------------------Adding some Menus to the MenuBar--------------------------
        
        menuBar.add(file);
        menuBar.add(accountManagement);
        menuBar.add(help);
        
        //------------------------Creating A JTable-----------------------------------------
        
        yourItemsTable = new JTable(Cell_Object, COLUMNS_STRING){
            @Override
            public boolean isCellEditable(int data, int columns){
                return false;
            }
        };
        
        yourItemsTable.setFillsViewportHeight(true);                            //fills the whole scrollPane;
        
        yourItemsScrollPane = new JScrollPane(yourItemsTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //-----------------------------------------All JComboBoxes-----------------------------------------
        
        sortCombo = new JComboBox(SORT_STRING);
        itemTypeCombo = new JComboBox(ALL_ITEM_TYPES);
        
        //-----------------------------------------All JRadioButtons-----------------------------------------
        
        ascendinglyRadioButton = new JRadioButton("Ascendingly", true);
        descendinglyRadioButton = new JRadioButton("Descendingly", false);
        searchRadioButton = new JRadioButton("Search", true);
        modifyRadioButton = new JRadioButton("Modify", false);
        
        ascendinglyRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        descendinglyRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        searchRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        modifyRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        //-----------------------------------------All ButtonGrups-----------------------------------------
        
        sortButtonGroup = new ButtonGroup();
        searchOrModifyButtonGroup = new ButtonGroup();
        
        sortButtonGroup.add(ascendinglyRadioButton);
        sortButtonGroup.add(descendinglyRadioButton);
        searchOrModifyButtonGroup.add(searchRadioButton);
        searchOrModifyButtonGroup.add(modifyRadioButton);
        
        //-----------------------------------------All JButtons-----------------------------------------
        
        sortButton = new JButton("Sort");
        addItemsButton = new JButton("Add Your Items");
        seeYourPaySlipButton = new JButton("See Your Payslip");
        employeeOfTheDayButton = new JButton("Employee Of The Day");
        searchOrModifyButton = new JButton("Search");
        offerButton = new JButton("Offer");
        deleteButton = new JButton("Delete");
        
        addItemsButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        seeYourPaySlipButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        employeeOfTheDayButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        searchOrModifyButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        offerButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        sortButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        addItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                AddItems addItems = new AddItems();
                
                addItems.pack();
                addItems.setLocationRelativeTo(null);
                addItems.setVisible(true);
                
            }
        });
        
        //-----------------------------------------All JLabels-----------------------------------------
        
        productCodeLabel = new JLabel("Product Code : ");
        productCodeLabel_2 = new JLabel("Product Code : ");
        productCodeLabel_3 = new JLabel("Product Code : ");
        ItemNameLabel = new JLabel("Item Name : ");
        itemTypeLabel = new JLabel("Item Type : ");
        itemQuantityLabel = new JLabel("Item Quantity : ");
        actualQuantityLabel = new JLabel("Actual Quantity : ");
        moreThanWhatCopiesLabel = new JLabel("Discounter Quantity : ");
        itemPriceLabel = new JLabel("Item Price : ");
        actualPriceLabel = new JLabel("Actual Price : ");
        offPriceLabel = new JLabel("Off Price : ");
        discountedPriceLabel = new JLabel("Discounted Price : ");
        validateInputLabel = new JLabel("Validate Input Here.");
        validateInputLabel_2 = new JLabel("Validate Input Here.");
        validateInputLabel_3 = new JLabel("Validate Input Here.");
        
        productCodeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        productCodeLabel.setHorizontalAlignment(JLabel.TRAILING);
        productCodeLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        productCodeLabel_2.setHorizontalAlignment(JLabel.TRAILING);
        productCodeLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        productCodeLabel_3.setHorizontalAlignment(JLabel.TRAILING);
        ItemNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        ItemNameLabel.setHorizontalAlignment(JLabel.TRAILING);
        itemTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        itemTypeLabel.setHorizontalAlignment(JLabel.TRAILING);
        itemQuantityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        itemQuantityLabel.setHorizontalAlignment(JLabel.TRAILING);
        actualQuantityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        actualQuantityLabel.setHorizontalAlignment(JLabel.TRAILING);
        moreThanWhatCopiesLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        moreThanWhatCopiesLabel.setHorizontalAlignment(JLabel.TRAILING);
        itemPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        itemPriceLabel.setHorizontalAlignment(JLabel.TRAILING);
        offPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        offPriceLabel.setHorizontalAlignment(JLabel.TRAILING);
        actualPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        actualPriceLabel.setHorizontalAlignment(JLabel.TRAILING);
        discountedPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        discountedPriceLabel.setHorizontalAlignment(JLabel.TRAILING);
        validateInputLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        validateInputLabel.setForeground(Color.red);
        validateInputLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        validateInputLabel_2.setForeground(Color.red);
        validateInputLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        validateInputLabel_3.setForeground(Color.red);
        
        //-----------------------------------------All JTextFields-----------------------------------------
        
        productCodeTextField = new JTextField(20);
        productCodeTextField_2 = new JTextField(32);
        productCodeTextField_3 = new JTextField(20);
        ItemNameTextField = new JTextField(20);
        itemQuantityTextField = new JTextField(20);
        actualQuantityTextField = new JTextField(20);
        itemPriceTextField = new JTextField(20);
        actualPriceTextField = new JTextField(20);
        offPriceTextField = new JTextField(20);
        discountedPriceTextField = new JTextField(20);
        moreThanWhatCopiesTextField = new JTextField(20);
        
        actualQuantityTextField.setEditable(false);                             //Jst b/c this is not The right Place To change The ActualQuantity!
        actualPriceTextField.setEditable(false);                                //Jst b/c this is not The right Place To change The ActualPrice!
        
        //-----------------------------------------All JPanels-----------------------------------------
        
        yourItemsInnerPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 75, 0));        //ItemTypeCombo + Ascending/Descending RadioButtons + sortButton
        yourItemsPanel = new JPanel(new BorderLayout(5, 10));                               //JTable(CENTER) + yourItemsInnerPanel(SOUTH)
        utilitiesPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 66, 2));             //addItems Button + seeYourPayslipButton + employeeOfTheDay Button
        mixerPanel1 = new JPanel(new BorderLayout(5, 5));                                   //yourItemsPanel(CENTER) + utilitiesPanel(SOUTH)
        
        searchOrEditInnerPanel1 = new JPanel(new GridLayout(2, 6, 1, 2));                   //productCodeLabel&textField + quantityLabel&TextField + ... + etc
        searchOrEditInnerPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));        //ValidatorInput Jlable + searchOrModify Button
        searchOrEditInnerPanel3 = new JPanel(new GridLayout(2, 1, 0, 2));                   //ValidatorInput Jlable + searchOrModify Button
        searchOrEditPanel = new JPanel(new BorderLayout(5, 15));                            //searchOrEditInnerPanel1(CENTER) + searchOrEditInnerPanel2(SOUTH)
        
        offerDiscountInnerPanel1 = new JPanel(new GridLayout(2, 6, 5, 15));                  //productCodeLabel&textField + quantityLabel&TextField + ... + etc
        offerDiscountInnerPanel2 = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));    //ValidatorInput Jlable + offer Button
        offerDiscountPanel = new JPanel(new BorderLayout(5, 5));                            //offerDiscountInnerPanel1(CENTER) + offerDiscountInnerPanel2(SOUTH)
        mixerPanel2 = new JPanel(new GridLayout(2, 1, 5, 5));                               //searchOrEditPanel + offerDiscountPanel
        mixerPanel3 = new JPanel(new GridLayout(2, 1, 5, 5));                               //mixerPanel1 + mixerPanel2
        
        deletePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 0));
        mixerPanel4 = new JPanel(new BorderLayout(5, 5));                                   //mixerPanel3(CENTER) + deletePanel(SOUTH)
        
        //-----------------------------------------Setting Panel Border -----------------------------------------
        
        yourItemsPanel.setBorder(BorderFactory.createTitledBorder(null, " Your Items ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        utilitiesPanel.setBorder(BorderFactory.createTitledBorder(null, " Utilities ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        searchOrEditPanel.setBorder(BorderFactory.createTitledBorder(null, " Search/Edit ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        searchOrEditInnerPanel3.setBorder(BorderFactory.createTitledBorder(null, " Choose ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        offerDiscountPanel.setBorder(BorderFactory.createTitledBorder(null, " Offer Discount ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        deletePanel.setBorder(BorderFactory.createTitledBorder(null, " Delete ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        mixerPanel4.setBorder(BorderFactory.createTitledBorder(null, null, TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        
        //-----------------------------------------Adding All Components To Panels-----------------------------------------
        
        yourItemsInnerPanel.add(sortCombo);
        yourItemsInnerPanel.add(ascendinglyRadioButton);
        yourItemsInnerPanel.add(descendinglyRadioButton);
        yourItemsInnerPanel.add(sortButton);
        
        yourItemsPanel.add(yourItemsScrollPane, BorderLayout.CENTER);
        yourItemsPanel.add(yourItemsInnerPanel, BorderLayout.SOUTH);
        
        utilitiesPanel.add(addItemsButton);
        utilitiesPanel.add(seeYourPaySlipButton);
        utilitiesPanel.add(employeeOfTheDayButton);
        
        mixerPanel1.add(yourItemsPanel, BorderLayout.CENTER);
        mixerPanel1.add(utilitiesPanel, BorderLayout.SOUTH);
        
        searchOrEditInnerPanel1.add(productCodeLabel);
        searchOrEditInnerPanel1.add(productCodeTextField);
        searchOrEditInnerPanel1.add(ItemNameLabel);
        searchOrEditInnerPanel1.add(ItemNameTextField);
        searchOrEditInnerPanel1.add(itemQuantityLabel);
        searchOrEditInnerPanel1.add(itemQuantityTextField);
        searchOrEditInnerPanel1.add(itemPriceLabel);
        searchOrEditInnerPanel1.add(itemPriceTextField);
        searchOrEditInnerPanel1.add(itemTypeLabel);
        searchOrEditInnerPanel1.add(itemTypeCombo);
        searchOrEditInnerPanel1.add(offPriceLabel);
        searchOrEditInnerPanel1.add(offPriceTextField);        
        
        searchOrEditInnerPanel2.add(validateInputLabel);
        searchOrEditInnerPanel2.add(new JLabel("                                                                                              "));
        searchOrEditInnerPanel2.add(searchOrModifyButton);
        
        searchOrEditInnerPanel3.add(searchRadioButton);
        searchOrEditInnerPanel3.add(modifyRadioButton);
        
        searchOrEditPanel.add(searchOrEditInnerPanel3, BorderLayout.WEST);
        searchOrEditPanel.add(searchOrEditInnerPanel1, BorderLayout.CENTER);
        searchOrEditPanel.add(searchOrEditInnerPanel2, BorderLayout.SOUTH);
        
        offerDiscountInnerPanel1.add(productCodeLabel_3);
        offerDiscountInnerPanel1.add(productCodeTextField_3);
        offerDiscountInnerPanel1.add(actualQuantityLabel);
        offerDiscountInnerPanel1.add(actualQuantityTextField);
        offerDiscountInnerPanel1.add(actualPriceLabel);
        offerDiscountInnerPanel1.add(actualPriceTextField);
        offerDiscountInnerPanel1.add(discountedPriceLabel);
        offerDiscountInnerPanel1.add(discountedPriceTextField);
        offerDiscountInnerPanel1.add(moreThanWhatCopiesLabel);
        offerDiscountInnerPanel1.add(moreThanWhatCopiesTextField);
        offerDiscountInnerPanel1.add(new JLabel(" "));
        offerDiscountInnerPanel1.add(new JLabel(" "));
        
        offerDiscountInnerPanel2.add(validateInputLabel_3);
        offerDiscountInnerPanel2.add(new JLabel("                                                                 "));
        offerDiscountInnerPanel2.add(new JLabel("                                                                 "));
        offerDiscountInnerPanel2.add(offerButton);
        
        offerDiscountPanel.add(offerDiscountInnerPanel1, BorderLayout.CENTER);
        offerDiscountPanel.add(offerDiscountInnerPanel2, BorderLayout.SOUTH);
        
        mixerPanel2.add(searchOrEditPanel);
        mixerPanel2.add(offerDiscountPanel);
        
        mixerPanel3.add(mixerPanel1);
        mixerPanel3.add(mixerPanel2);
        
        deletePanel.add(productCodeLabel_2);
        deletePanel.add(productCodeTextField_2);
        deletePanel.add(deleteButton);
        deletePanel.add(new JLabel("                       "));
        deletePanel.add(validateInputLabel_2);
        
        mixerPanel4.add(mixerPanel3, BorderLayout.CENTER);
        mixerPanel4.add(deletePanel, BorderLayout.SOUTH);
        
        //-----------------------------------------Adding MenuBar And Main Panel To The Container-----------------------------------------
        
        mainContainer_4.add(menuBar, BorderLayout.NORTH);
        mainContainer_4.add(mixerPanel4, BorderLayout.CENTER);
        
        Toolkit toolkit = getToolkit();
        
        Dimension size = toolkit.getScreenSize();
        
        setSize(size.width - getWidth(), size.height - getHeight() - 35);
        
    }
    
    public static void shareUs(){
        
        boolean shared = true;
        
        File path = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        File path_1 = new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        File path_2 = new File("C:\\Program Files (x86)\\baidu\\Baidu Browser\\spark.exe");
        File path_3 = new File("C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe");
        
        if(path.exists())
            try {
                Process ppp = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            } catch (IOException e) {}
        else if(path_1.exists())
            try {
                Process ppp = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            } catch (IOException e) {}
        else if(path_2.exists())
            try {
                Process ppp = Runtime.getRuntime().exec("C:\\Program Files (x86)\\baidu\\Baidu Browser\\spark.exe");
            } catch (IOException e) {}
        else if(path.exists())
            try {
                Process ppp = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe");
            } catch (IOException e) {}
        else{
            JOptionPane.showMessageDialog(null, "\n\tSorry, Your browser Was Not Installed.", "Browser Opening Failed", JOptionPane.ERROR);
            shared = false;
        }
        if(shared)
            JOptionPane.showMessageDialog(null, "\n\tThank You For Sharing SegnoGebeya.com To Your Friends.", "Sharing Successful.", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
