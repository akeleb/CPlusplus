package project_11_design;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class BuyersMenu extends JFrame{
    
    private Container    mainContainer_5;
    private JMenuBar     menuBar;
    private JMenu        file, accountManagement, help;
    private JMenuItem    aboutUs, exit, logout, profile, share;
    private JTable       allItemsInStoreTable;
    private JScrollPane  allItemsInStoreScrollPane;
    private JComboBox    sortCombo, itemTypeCombo;
    private JButton      sortButton, addToCartButton, searchButton, seeYourPaySlipButton, todaysDiscountButton, newArrivalItemsButton, employeeOfTheDayButton;
    private ButtonGroup  sortButtonGroup;
    private JLabel       productCodeLabel, productCodeLabel_2, ItemNameLabel, itemTypeLabel, itemQuantityLabel, itemQuantityLabel_2, itemPriceLabel, offPriceLabel, 
                         validateInputLabel, validateInputLabel_2;
    private JTextField   productCodeTextField, productCodeTextField_2, ItemNameTextField, itemQuantityTextField, itemQuantityTextField_2, itemPriceTextField, offPriceTextField;
    
    private JRadioButton ascendinglyRadioButton, descendinglyRadioButton;
    
    private JPanel       allItemsInStorePanel, allItemsInStoreInnerPanel, buyHerePanel, buyHereInnerPanel, searchHerePanel, searchHereInnerPanel_1, searchHereInnerPanel_2, utilitiesPanel, mixerPanel1, mixerPanel2, mixerPanel3;
    
    private final static String[] ALL_ITEM_TYPES = {"Clothings", "Construction & Home", "Cosmetics & Beauty", "Educationals", "Electronics", "Food & Drink", "Furniture", "Jewelry", "Kitchen Materials", "Others"};
    private final String[] SORT_STRING = {"By Name", "By Type", "By Product Code", "By Quantity", "By Unit Price", "By Off Price", "By Off Quantity"};
    private String[] COLUMNS_STRING = {"Name", "Type", "Product Code", "Quantity", "Unit Price", "Employee Name", "Off Price", "Off Quantity"};
    private Object[][] Cell_Object = { {"laptops under 500$", "Laptop", 1233, 20, 499.00, "Muluken", 450.99, 10}, {null, null, null, null, null, null, null, null},  
                                       {null, null, null, null, null, null, null, null}, {null, null, null, null, null, null, null, null}, 
                                       {null, null, null, null, null, null, null, null}, {null, null, null, null, null, null, null, null}, 
                                       {null, null, null, null, null, null, null, null}, {null, null, null, null, null, null, null, null} };
    
    public BuyersMenu() {
        
        super("Buyers Menu");
        
        mainContainer_5 = getContentPane();
        mainContainer_5.setLayout(new BorderLayout());          //menuBar(NORTH) + mixerPanel4(CENTER);
        
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
        
        //------------------------Creating A JTable--------------------------
        
        allItemsInStoreTable = new JTable(Cell_Object, COLUMNS_STRING){
            @Override
            public boolean isCellEditable(int data, int columns){
                return false;
            }
        };
        
        allItemsInStoreTable.setFillsViewportHeight(true);                            //fills the whole scrollPane;
        
        allItemsInStoreScrollPane = new JScrollPane(allItemsInStoreTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //-----------------------------------------All JComboBoxes-----------------------------------------
        
        sortCombo = new JComboBox(SORT_STRING);
        itemTypeCombo = new JComboBox(ALL_ITEM_TYPES);
        
        //-----------------------------------------All JRadioButtons-----------------------------------------
        
        ascendinglyRadioButton = new JRadioButton("Ascendingly", true);
        descendinglyRadioButton = new JRadioButton("Descendingly", false);
        
        ascendinglyRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        descendinglyRadioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        //-----------------------------------------All ButtonGrups-----------------------------------------
        
        sortButtonGroup = new ButtonGroup();
        
        sortButtonGroup.add(ascendinglyRadioButton);
        sortButtonGroup.add(descendinglyRadioButton);
        
        //-----------------------------------------All JButtons-----------------------------------------
        
        sortButton = new JButton("Sort");
        addToCartButton = new JButton("Add To Cart");
        searchButton = new JButton("Search");
        seeYourPaySlipButton = new JButton("See Your Payslip");
        todaysDiscountButton = new JButton("Todays Discount");
        newArrivalItemsButton = new JButton("New Arrival Items");
        employeeOfTheDayButton = new JButton("Employee Of The Day");
        
        sortButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        addToCartButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        seeYourPaySlipButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        todaysDiscountButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        newArrivalItemsButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        employeeOfTheDayButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        //-----------------------------------------All JLabels-----------------------------------------
        
        productCodeLabel = new JLabel("Product Code : ");
        productCodeLabel_2 = new JLabel("Product Code : ");
        ItemNameLabel = new JLabel("Item Name : ");
        itemTypeLabel = new JLabel("Item Type : ");
        itemQuantityLabel = new JLabel("Item Quantity : ");
        itemQuantityLabel_2 = new JLabel("Item Quantity : ");
        itemPriceLabel = new JLabel("Item Price : ");
        offPriceLabel = new JLabel("Off Price : ");
        validateInputLabel = new JLabel("Validate Input Here.");
        validateInputLabel_2 = new JLabel("Validate Input Here.");
        
        productCodeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        productCodeLabel.setHorizontalAlignment(JLabel.TRAILING);
        productCodeLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        productCodeLabel_2.setHorizontalAlignment(JLabel.TRAILING);
        ItemNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        ItemNameLabel.setHorizontalAlignment(JLabel.TRAILING);
        itemTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        itemTypeLabel.setHorizontalAlignment(JLabel.TRAILING);
        itemQuantityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        itemQuantityLabel.setHorizontalAlignment(JLabel.TRAILING);
        itemQuantityLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        itemQuantityLabel_2.setHorizontalAlignment(JLabel.TRAILING);
        itemPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        itemPriceLabel.setHorizontalAlignment(JLabel.TRAILING);
        offPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        offPriceLabel.setHorizontalAlignment(JLabel.TRAILING);
        validateInputLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        validateInputLabel.setForeground(Color.red);
        validateInputLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        validateInputLabel_2.setForeground(Color.red);
        
        //-----------------------------------------All JTextFields-----------------------------------------
        
        productCodeTextField = new JTextField(20);
        productCodeTextField_2 = new JTextField(32);
        ItemNameTextField = new JTextField();
        itemQuantityTextField = new JTextField(20);
        itemQuantityTextField_2 = new JTextField();
        itemPriceTextField = new JTextField();
        offPriceTextField = new JTextField();
        
        //-----------------------------------------All JPanels-----------------------------------------
        
        allItemsInStoreInnerPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 75, 0));          //ItemTypeCombo + Ascending/Descending RadioButtons + sortButton
        allItemsInStorePanel = new JPanel(new BorderLayout(5, 10));                                 //JTable(CENTER) + allItemsInStoreInnerPanel(SOUTH)
        
        buyHerePanel = new JPanel(new GridLayout(2, 1, 10, 2));                                    //buyHereInnerPanel + validateInputLabel
        buyHereInnerPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 2));                 //productCodeLabel/TextField + itemQuantityLabel/TextField + addToCartButton
        
        searchHereInnerPanel_1 = new JPanel(new GridLayout(2, 6, 5, 15));                           //
        searchHereInnerPanel_2 = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 29));              //validateInputLabel + searchButton
        searchHerePanel = new JPanel(new BorderLayout(0, 0));                                       //searchHereInnerPanel_1(CENTER) + searchHereInnerPanel_2(SOUTH)
        
        utilitiesPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 10));                     //seeYourPaySlipButton + todaysDiscountButton + newArrivalItemsButton + employeeOfTheDayButton
        
        mixerPanel1 = new JPanel(new BorderLayout(10, 10));                                         //allItemsInStorePanel(CENTER) + buyHerePanel(SOUTH)
        mixerPanel2 = new JPanel(new BorderLayout(10, 10));                                         //searchHerePanel + utilitiesPanel
        mixerPanel3 = new JPanel(new GridLayout(2, 1, 10, 10));
        
        //-----------------------------------------Setting Panel Border -----------------------------------------
        
        mixerPanel3.setBorder(BorderFactory.createTitledBorder(null, null, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        allItemsInStorePanel.setBorder(BorderFactory.createTitledBorder(null, " All Items In Store ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        buyHerePanel.setBorder(BorderFactory.createTitledBorder(null, " Buy Here ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        searchHerePanel.setBorder(BorderFactory.createTitledBorder(null, " Search Here ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        utilitiesPanel.setBorder(BorderFactory.createTitledBorder(null, " Utilities ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        
        menuBar.setBorderPainted(true);
//        menuBar.setBackground(Color.red);
//        menuBar.setForeground(Color.red);
        menuBar.setBorder(BorderFactory.createTitledBorder(null, null, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 13), Color.BLUE));
        
        //-----------------------------------------Adding All Components To Panels-----------------------------------------
        
        allItemsInStoreInnerPanel.add(sortCombo);
        allItemsInStoreInnerPanel.add(ascendinglyRadioButton);
        allItemsInStoreInnerPanel.add(descendinglyRadioButton);
        allItemsInStoreInnerPanel.add(sortButton);
        
        allItemsInStorePanel.add(allItemsInStoreScrollPane, BorderLayout.CENTER);
        allItemsInStorePanel.add(allItemsInStoreInnerPanel, BorderLayout.SOUTH);
        
        buyHereInnerPanel.add(productCodeLabel);
        buyHereInnerPanel.add(productCodeTextField);
        buyHereInnerPanel.add(itemQuantityLabel);
        buyHereInnerPanel.add(itemQuantityTextField);
        buyHereInnerPanel.add(addToCartButton);
        
        buyHerePanel.add(buyHereInnerPanel);
        buyHerePanel.add(validateInputLabel);
        
        searchHereInnerPanel_1.add(productCodeLabel_2);
        searchHereInnerPanel_1.add(productCodeTextField_2);
        searchHereInnerPanel_1.add(itemQuantityLabel_2);
        searchHereInnerPanel_1.add(itemQuantityTextField_2);
        searchHereInnerPanel_1.add(itemTypeLabel);
        searchHereInnerPanel_1.add(itemTypeCombo);
        searchHereInnerPanel_1.add(ItemNameLabel);
        searchHereInnerPanel_1.add(ItemNameTextField);
        searchHereInnerPanel_1.add(itemPriceLabel);
        searchHereInnerPanel_1.add(itemPriceTextField);
        searchHereInnerPanel_1.add(offPriceLabel);
        searchHereInnerPanel_1.add(offPriceTextField);
        
        searchHereInnerPanel_2.add(validateInputLabel_2);
        searchHereInnerPanel_2.add(new JLabel("                                       "));          //Empty Labels, Just for Layout Adjustment purpose;
        searchHereInnerPanel_2.add(new JLabel("                                       "));
        searchHereInnerPanel_2.add(new JLabel("                                       "));
        searchHereInnerPanel_2.add(searchButton);
        
        searchHerePanel.add(new JLabel(" "), BorderLayout.NORTH);
        searchHerePanel.add(searchHereInnerPanel_1, BorderLayout.CENTER);
        searchHerePanel.add(searchHereInnerPanel_2, BorderLayout.SOUTH);
        
        utilitiesPanel.add(seeYourPaySlipButton);
        utilitiesPanel.add(todaysDiscountButton);
        utilitiesPanel.add(newArrivalItemsButton);
        utilitiesPanel.add(employeeOfTheDayButton);
        
        mixerPanel1.add(allItemsInStorePanel, BorderLayout.CENTER);
        mixerPanel1.add(buyHerePanel, BorderLayout.SOUTH);
        
        mixerPanel2.add(searchHerePanel, BorderLayout.CENTER);
        mixerPanel2.add(utilitiesPanel, BorderLayout.SOUTH);
        
        mixerPanel3.add(mixerPanel1);
        mixerPanel3.add(mixerPanel2);
        
        mainContainer_5.add(menuBar, BorderLayout.NORTH);
        mainContainer_5.add(mixerPanel3, BorderLayout.CENTER);
        
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
