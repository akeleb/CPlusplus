package Elements.Project.Our;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class 1 {                                      //testing class
    
    final   static String BUYER_TYPE  = "Buyer", SELLER_TYPE = "Seller";
    
    private static ArrayList<Users> buyersList = new ArrayList<>();
    private static ArrayList<Users> sellersList = new ArrayList<>();
    public  static ArrayList<Store> ItemsList = new ArrayList<>();
    public  static ArrayList<PaySlip> usersBills = new ArrayList<>();
    
    static Users currentBuyerOrSeller;
    
    public static void main(String[] args) {
        
        int option = Users.homePage();                     //SegnoGebeya can access login b/c they R in D same package;
        
        while(true){
            
            switch(option){
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    login(false);
                    break;
                case 3:
                    displayAllSellers();
                    break;
                case 4:
                    shareUs();
                    break;                
                case 5:
                    seeProgrammers();
                    break;                
                case 0:
                    if(!(AskToconfirm().equalsIgnoreCase("yes")))              //if the user doesn't want to exit the program; 
                        option = 3;                                             //change the value of option to whatever value other than 0;
                    break;
                default:
                    System.out.println("\nIncorrect Input. Please Enter A valid Menu Option.");
            }
            if(option == 0)
                break;
            option = Users.homePage();
        }    
    }
    
    public static String AskToconfirm(){
        Scanner cin = new Scanner(System.in);
        String yesNo;
        
        System.out.print("\n\t\tAre you Sure?? (yes / no)  > ");
        yesNo = cin.next();
        
        return yesNo;
    }
    
    public static void createNewAccount(){
        
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              New Accounts Page");
        System.out.println(    "--------------------------------------------------------------------------------------------------------------\n\n");
        
        Scanner input = new Scanner(System.in);
                
        String  name, accountType, password;
        int     creditNum, option; 
        double  initialAmount;
        Address addr = new Address();
        
        System.out.print("\t\tUser Type  > \t\t1 --- A Buyer  Account\n\t\t\t\t\t2 --- A Seller Account\n\t\t\t\t\t0 --- Cancel");    
        do{
            System.out.print("\n\n\t\tEnter the appropriate Number  > ");
            option =  validateNumber();
        }while(option < 0 || option > 2);
            
        if(option == 0)
            return;
        if(option == 1) accountType = BUYER_TYPE;
        else    accountType = SELLER_TYPE;
        
        System.out.print("\nYour Name                 > ");
        name = validateString();
        
        
        System.out.print("\nYour Pasword              > ");
        password = input.nextLine();

        if(validateDuplicateNameAndTypeAndPassword(name, password, accountType)){       //we need this to limit duplicate users having the same name, password and userType;
            System.out.println("\nAnother User Exits With Such A Name, PASSWORD and UserType.\n");
            return;
        }
        
        System.out.print("\nYour Credit Card Number   > ");
        creditNum = validateNumber();

        System.out.print("\nYour House Number         > ");
        addr.houseNum = validateNumber();

        addr.subCity = subCity();
        
        if(accountType.equalsIgnoreCase(BUYER_TYPE)){
            System.out.print("\nyour Initial Balance In $ > ");
            initialAmount = validateNumber();

            currentBuyerOrSeller = new Buyer(name, creditNum, addr, accountType, initialAmount, password);
            buyersList.add(currentBuyerOrSeller);
            sleep(500);
            System.out.println("\n\n\tCongrats Buyer " + currentBuyerOrSeller.name + ", From now on You Are A Member of SegnoGebeya.com!");
            System.out.println("\n\tYour Buyer Id is  > " + currentBuyerOrSeller.id);
            sleep(500);
            login(true);
        }else{
            currentBuyerOrSeller = new Seller(name, creditNum, addr, accountType, password);
            sellersList.add(currentBuyerOrSeller);
            sleep(500);
            System.out.println("\n\n\tCongrats Seller " + currentBuyerOrSeller.name + ", From now on You Are An Employee In SegnoGebeya.com!");
            System.out.println("\n\tYour Seller Id is  > " + currentBuyerOrSeller.id + "\n");
            sleep(500);
            login(true);
        }
    }
    
    public static String subCity(){                         //choosing a residence.... to limit any mistakes we made the user choose by numbers;
        int subCityInt;
        
        do{
            System.out.println("\n\tChoose Your SubCity\n");
            System.out.println("\t\t1  -------------  Addis Ketema");
            System.out.println("\t\t2  -------------  Akaki Kaliti");
            System.out.println("\t\t3  -------------  Arada");
            System.out.println("\t\t4  -------------  Bole");
            System.out.println("\t\t5  -------------  Gulele");
            System.out.println("\t\t6  -------------  Kirkos");
            System.out.println("\t\t7  -------------  Kolfe Keranio");
            System.out.println("\t\t8  -------------  Lideta");
            System.out.println("\t\t9  -------------  Nifas Silk Lafto");
            System.out.println("\t\t10 -------------  Yeka");

            System.out.print("\n\t\t\t# > ");
            subCityInt = validateNumber();
        }while(subCityInt < 1 || subCityInt > 10);
        
        switch(subCityInt){
            case 1:     return "Addis Ketema";
            case 2:     return "Akaki Kaliti";
            case 3:     return "Arada";
            case 4:     return "Bole";
            case 5:     return "Gulele";
            case 6:     return "Kirkos";
            case 7:     return "Kolfe Keranio";
            case 8:     return "Lideta";
            case 9:     return "Nifas Silk Lafto";
            case 10:    return "Yeka";
            default:    System.out.println("Please choose the Appropriate number.");
        }
        return null;
    }
    
    public static boolean validateDuplicateNameAndTypeAndPassword(String name, String password, String accountType){        //we need this b/c No two users can have the same name, password and userType
        for(int r=0; r < buyersList.size(); r++)
            if(buyersList.get(r).name.equals(name) && buyersList.get(r).password.equals(password) && buyersList.get(r).userType.equalsIgnoreCase(accountType))
                return true;
        
        for(int r=0; r < sellersList.size(); r++)            
            if(sellersList.get(r).name.equals(name) && sellersList.get(r).password.equals(password) && sellersList.get(r).userType.equalsIgnoreCase(accountType))
                return true;
        
        return false;
    }
    
    public static void login(boolean now){
        
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                Account LogIn Page");
        System.out.println(    "--------------------------------------------------------------------------------------------------------------\n\n");
        
        boolean loginSucesss = false;
        
        if(now == false)
            loginSucesss = askUsernameAndPassword();
        
        if(now || loginSucesss){
            
            int option = currentBuyerOrSeller.menu();

            while(true){

                switch(option){
                    case 1:
                        if(currentBuyerOrSeller.userType.equals(BUYER_TYPE))
                            ((Buyer)currentBuyerOrSeller).buyItems(currentBuyerOrSeller, false);
                        else
                            ((Seller)currentBuyerOrSeller).addYourItems();
                        break;
                    case 2:                      
                        if(currentBuyerOrSeller.userType.equals(BUYER_TYPE))
                            ((Buyer)currentBuyerOrSeller).seeItems(true);
                        else
                            Seller.seeYourItemsOnly(currentBuyerOrSeller, true);
                        break;
                    case 3:
                        if(currentBuyerOrSeller.userType.equals(BUYER_TYPE))
                            ((Buyer)currentBuyerOrSeller).seeAboutItemDescription();
                        else
                            ((Seller)currentBuyerOrSeller).deleteYourItems();
                        break;
                    case 4:
                        if(currentBuyerOrSeller.userType.equals(BUYER_TYPE))
                            ((Buyer)currentBuyerOrSeller).sortItems();
                        else
                            Seller.sortItems();
                        break;
                    case 5:
                        if(currentBuyerOrSeller.userType.equals(BUYER_TYPE))
                            ((Buyer)currentBuyerOrSeller).searchItems();                        
                        else
                            Seller.SearchForItems();
                        break;
                    case 6:
                        if(currentBuyerOrSeller.userType.equals(BUYER_TYPE))
                            ((Buyer)currentBuyerOrSeller).seeHighestDiscounts();
                        else
                            ((Seller)currentBuyerOrSeller).editYourItems();                        
                        break;
                    case 7: viewGeneratedSlip(currentBuyerOrSeller);
                        break;
                    case 8:
                        if(currentBuyerOrSeller.userType.equals(BUYER_TYPE))
                            ((Buyer)currentBuyerOrSeller).SeeNewArrivalItems();
                        else
                            ((Seller)currentBuyerOrSeller).offerDiscount();
                        break;
                    case 9: employee_Of_The_Day();
                        break;
                    case 10:
                        if(currentBuyerOrSeller.userType.equals(BUYER_TYPE))
                            ((Buyer)currentBuyerOrSeller).Profile();
                        else
                            ((Seller)currentBuyerOrSeller).Profile();
                        break;
                    case 0:
                        if(!(AskToconfirm().equalsIgnoreCase("yes")))                                           //if the user doesn't want to Logout; 
                            option = 3;                                                                         //change the value of option to whatever value other than 0;
                        break;
                    default:
                        System.out.println("\nIncorrect Input. Please Enter A valid Menu Option.");
                }
                if(option == 0)
                    break;
                option = currentBuyerOrSeller.menu();
            }
        }
    }
    
    public static boolean askUsernameAndPassword(){
        
        Scanner input = new Scanner(System.in);
        
        String userType, name, password;
        int id, userTypeInt, errorCounter = 0;
        boolean error = false;
        
        do{
            if(error == true)
                System.out.println("\n\t\tSorry! Your UserName and Password Doesn't match! \n");

            System.out.print("\t\tUser Type  > \t\t1 --- Buyer\n\t\t\t\t\t2 --- Seller\n\t\t\t\t\t0 --- Cancel");
            
            do{
                System.out.print("\n\n\t\tEnter the appropriate Number  > ");
                userTypeInt = validateNumber();
            }while(userTypeInt != 1 && userTypeInt != 2 && userTypeInt != 0);
            
            if(userTypeInt == 0)
                return false;
            
            if(userTypeInt == 1)
                userType = BUYER_TYPE;
            else
                userType = SELLER_TYPE;
            
            System.out.print("\n\t\tName   > ");
            name = validateString();
            System.out.print("\n\t\tPassword     > ");
            password = input.nextLine();

            error = true;
            errorCounter++;
            
            if(checkLogin(name, password, userType) == true){
                currentBuyerOrSeller = retunrLoggedInUser(name, password, userType);
                return true;
            }
            
        }while(errorCounter < 3);
        
        System.out.println("\nYou tried a lot... Please take some rest! Or create a new Account through following the Start Page\n");
        return false;
        
    }
    
    public static boolean checkLogin(String name, String password, String userType){
        
        for(int r=0; r < buyersList.size(); r++)
            if(buyersList.get(r).name.equals(name) && buyersList.get(r).password.equals(password) && buyersList.get(r).userType.equalsIgnoreCase(userType))
                return true;
        
        for(int r=0; r < sellersList.size(); r++)            
            if(sellersList.get(r).name.equals(name) && sellersList.get(r).password.equals(password) && sellersList.get(r).userType.equalsIgnoreCase(userType))
                return true;
        
        return false;
    }
    
    public static void deleteSoldItems(int allBoughtProdNum){
        for (int r = ItemsList.size()-1; r >= 0; r--) {                            //D best way 2 iterate n remove, b/c there is no skipping of elements;
                Store st = ItemsList.get(r);
                if(st.productCode == allBoughtProdNum){
                    ((Seller)retunrSpecificSeller(ItemsList.get(r).employeeId)).AddedItems--;
                    ItemsList.remove(r);
                    break;
                }
            }
    }
    
    public static Users retunrLoggedInUser(String name, String password, String userType){
        
        for(int r=0; r < buyersList.size(); r++)            
            if(buyersList.get(r).name.equals(name) && buyersList.get(r).password.equals(password) && buyersList.get(r).userType.equalsIgnoreCase(userType))
                return buyersList.get(r);
                
        for(int r=0; r < sellersList.size(); r++)
            if(sellersList.get(r).name.equals(name) && sellersList.get(r).password.equals(password) && sellersList.get(r).userType.equalsIgnoreCase(userType))
                return sellersList.get(r);
        
        return null;
    }
    
    public static Users retunrSpecificSeller(int id){
        
        for(int r=0; r < sellersList.size(); r++)
            if(sellersList.get(r).id == id)
                return sellersList.get(r);
        
        return null;                    //if a seller with such id is not found;
    }
    
    public static void SearchForASingleItem(int prodCode){
        
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s\t%-25s\t%-12s\t%-9s\t%-10s\t%-25s%-12s%-12s\n", "ItemName", "ItemType", "ProductCode", "Quantity", "UnitPrice", "EmployeeName", "OffPrice", "OffQuantity");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        for(int r=0; r < ItemsList.size(); r++){            
            if(ItemsList.get(r).productCode == prodCode){
                ItemsList.get(r).display();
                break;
            }
        }
    }
    
    public static void viewGeneratedSlip(Users current){
        
        boolean anyBought = false;
        
        if(usersBills.isEmpty())
            System.out.println("\nSorry, You didn't Buy Or Sell Any Item.\n");
        else{
            for(int r=0; r<usersBills.size(); r++)
                if(usersBills.get(r).name.equals(current.name) && usersBills.get(r).id == current.id){
                    System.out.println(usersBills.get(r).toString());
                    anyBought = true;
                }
            if(!anyBought)
                System.err.println("\nSorry, You didn't Buy Or Sell Any Item.\n");
        } 
    }
    
    public static void employee_Of_The_Day(){
        
        System.out.println("\n\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                               Employee Of The Day");
        System.out.println(    "-------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        Users employeeOfTheDay = (Seller)sellersList.get(0);
        
        for (int r = 0; r < sellersList.size(); r++) 
            if( ((Seller)employeeOfTheDay).soldItems < ((Seller)sellersList.get(r)).soldItems )
                employeeOfTheDay = (Seller)sellersList.get(r);                                  //we will get the employee of the day here!
        
        if(((Seller)employeeOfTheDay).soldItems != 0){
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-25s\t%-15s\t%-9s\t%-12s\t%-20s\t%-12s\n", "Name", "User Type", "Id", "House Number", "Subcity", "Items Sold");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            employeeOfTheDay.display();
        }else
            System.out.println("Untill Now, There Is No Employee Who Sold Even A Single Item.");
        
        System.out.println("-------------------------------------------------------------------------------------------------------------------");

    }
    
    public static void displayAllSellers(){
        System.out.println("\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                       Showing All Registered Sellers In Our Site");
        System.out.println(    "---------------------------------------------------------------------------------------------------------------------------------------------------------");
        if(sellersList.isEmpty()){
            System.out.println("Sorry, There Are No Sellers.");
            return;
        }
        else{    
            System.out.println("\t\t\t\t We have " + sellersList.size() + " Sellers.\n");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-25s\t%-15s\t%-9s\t%-12s\t%-20s\t%-12s\n", "Name", "User Type", "Id", "House Number", "Subcity", "Items Sold");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");

            for(Users u : sellersList){
                u.display();
            }

            System.out.println("-------------------------------------------------------------------------------------------------------------------");
        }
    }
    
    public static int getProfileEditType(){
        int choose;
        
        do{
            System.out.println("\n\tChoose What To Change : \n");
            System.out.println("\t\t1   -------------  Name");
            System.out.println("\t\t2   -------------  Password");
            System.out.println("\t\t0   -------------  Cancel");

            System.out.print("\n\t\t\t# > ");
            choose = SegnoGebeya.validateNumber();
        }while(choose < 0 || choose > 2);
        
        return choose;
    }
    
    public static void shareUs(){
        
        boolean shared = true;
        
        File path = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        File path_1 = new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        File path_2 = new File("C:\\Program Files (x86)\\baidu\\Baidu Browser\\spark.exe");
        File path_3 = new File("C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe");
        
        System.out.println("\n\tOpening Browser... ");
        
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
            System.err.println("\n\tSorry, Your browser Was Not Installed.");
            shared = false;
        }
        if(shared)
            System.out.println("\n\tThank You For Sharing SegnoGebeya.com To Your Friends.");
    }
    
    public static void seeProgrammers(){
        System.out.println("______________________________________________________________________________________________________________");
            System.out.println("\t                          ()                                          ()         ");
            System.out.println("\t                          ()        Programmers of this project       ()         ");
            System.out.println("\t                   _______()__________________________________________()_______ "); 	
            System.out.println("\t                  /[                                                           ]");
            System.out.println("\t                 [ [           ID              NAME                            ]");
            System.out.println("\t                 [ [_____                                                 _____]");
            System.out.println("\t                 [ [ ]___      ETS 0850        MULUKEN   GETACHEW         ___[ ]");
            System.out.println("\t                 [ [ ]___      ETS 0843        MULUGETA  BERIHUN          ___[ ]");
            System.out.println("\t                 [ [ ]___      ETS 0915        ODDA      KUSSA            ___[ ]");
            System.out.println("\t                 [ [ ]___      ETS 0870        NAOD      AKLILU           ___[ ]");
            System.out.println("\t                 [ [ ]___      ETS 1064        TESFA     TESHOME          ___[ ]");
            System.out.println("\t                 [ [ ]___      ETS 1159        YABSIRA   TEDLA            ___[ ]");
            System.out.println("\t                 [ [ ]___      ETS 1030        Surrawak  Tariku           ___[ ]");
            System.out.println("\t                 [ [ ]___      ETS 1077        Tessema   Keno             ___[ ]");
            System.out.println("\t                  \\[___________________________________________________________]\n");
            System.out.println("                                                                   ");
            System.out.println("                                                                   ");
	System.out.println("______________________________________________________________________________________________________________");
    }
    
    public static void sleep(int x){
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {}
    }
    
    static int validateNumber() {
        int x = 0;
        boolean error = false;
        
        Scanner input_2 = new Scanner(System.in);
        
        do{
            if(input_2.hasNextInt()){
                x = input_2.nextInt();
                error = false;
            }else{
                System.err.print("\n\terror enter a # again (numbers only) > ");
                error = true;
                input_2.nextLine();                             //vry vry impt as it prevents infinity loop; n it's (I think) not excuted;
            }
        }while(error);
        
        return x;
    }
    
    static double validateDouble() {
        double x = 0;
        boolean error = false;
        
        Scanner input_2 = new Scanner(System.in);
        
        do{
            if(input_2.hasNextDouble()){
                x = input_2.nextDouble();
                error = false;
            }else{
                System.err.print("\n\terror enter a # again (numbers only) > ");
                error = true;
                input_2.nextLine();                             //vry vry impt as it prevents infinity loop; n it's (I think) not excuted;
            }
        }while(error);
        
        return x;
    }

    static String validateString() {
        String name_1 = "";
        boolean error = false;
        Scanner input_2 = new Scanner(System.in);

        do{
            if(error)
                System.err.print("\n\tError, Please Enter in (words and a space only) > ");
            name_1  = input_2.nextLine();
            error = true;
        }while(!name_1.matches("[a-zA-Z ]+"));         //A name should only contain UPPERCASES, lowercases and a space; if not, error;
        
        return name_1;
    }   
}