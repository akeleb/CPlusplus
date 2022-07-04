package Elements.Project.Our;

import java.util.Collections;                       //4 sorting
import java.util.Comparator;                        //4 sorting
import java.util.Scanner;
import static Elements.Project.Our.SegnoGebeya.ItemsList;
import static Elements.Project.Our.SegnoGebeya.SearchForASingleItem;

public class Seller extends Users{
    
    Scanner input = new Scanner(System.in);
    
    Store tempItem;                                                       //for Editing stuff
    
    public static int noofSellers; 
    public static int noofGoods;
    
    int soldItems  = 0;                                                          //4 employee of d day
    int AddedItems = 0;                                                          //4 employee of d day
    
    Seller(String name, int creditNum, Address addr, String userType, String password) {
        super(name, creditNum, ++noofSellers, addr, userType, 0.0, password);              //unique seller Id by ++noofSellers
    }

    public void addYourItems(){
        
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              Adding An Item To Store");
        System.out.println(    "--------------------------------------------------------------------------------------------------------------\n\n");
        
        Scanner input_1 = new Scanner(System.in);
        Scanner input_2 = new Scanner(System.in);
        Scanner input_3 = new Scanner(System.in);
        
        String goodName, goodType, goodDescription;
        int    goodTypeInt, itemQuantity;
        double unitPrice;
        
        goodType = Store.ALL_ITEM_TYPES[getItemType() - 1];
        
        System.out.print("\nName Of The Item         > ");
        goodName = input_2.nextLine();
        System.out.print("\nHow Many " + goodName + "s (Quantity) > ");
        itemQuantity = SegnoGebeya.validateNumber();
        System.out.print("\nUnit Price Of " + goodName + " > ");
        unitPrice = SegnoGebeya.validateDouble();
        System.out.print("\nAny Description About The Item " + "\n\n\t>");
        goodDescription = input_3.nextLine();
        
        Store items = new Store(goodName, goodType, goodDescription, ++noofGoods, itemQuantity, unitPrice, super.name, super.id);     //++noofGoods is the best way to give a unique product code to all items;
        ItemsList.add(items);
        AddedItems++;
        
        System.out.println("\nThe Following items were Sucessfully Added to store by seller " + super.name);
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.printf("  > %d %ss with Unit Price of %.2f\n", itemQuantity, goodName, unitPrice);
        System.out.println("--------------------------------------------------------------------------------------------------------------\n");
        
    }
    
    public int getItemType(){
        
        int goodTypeInt;
        
        do{
            System.out.println("\n\tChoose Type Of The Item\n");
            System.out.println("\t\t1  -------------  Clothings");
            System.out.println("\t\t2  -------------  Construction & Home Designing Materials");
            System.out.println("\t\t3  -------------  Cosmetics & Beauty");
            System.out.println("\t\t4  -------------  Educational Materials");
            System.out.println("\t\t5  -------------  Electronics");
            System.out.println("\t\t6  -------------  Food Items");
            System.out.println("\t\t7  -------------  Furniture");
            System.out.println("\t\t8  -------------  Jewelry");
            System.out.println("\t\t9  -------------  Kitchen Materials");
            System.out.println("\t\t10 -------------  Others");

            System.out.print("\n\t\t\t # > ");
            goodTypeInt = SegnoGebeya.validateNumber();
        }while(goodTypeInt > 10 || goodTypeInt < 1);
        
        return goodTypeInt;
    }
    
    public static void seeAllTheItems(boolean yours){
        
        if(ItemsList.isEmpty()){
            System.out.println("\n\t\t\t\t\t\tEmpty List Of Items!!\n");
            return;
        }
        
        Scanner cin = new Scanner(System.in);
        
        String viewYours;
        
        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                       Showing All The Items In Store");
        System.out.println(    "----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s\t%-25s\t%-12s\t%-9s\t%-10s\t%-25s%-12s%-12s\n", "ItemName", "ItemType", "ProductCode", "Quantity", "UnitPrice", "EmployeeName", "OffPrice", "OffQuantity");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for(Store st : ItemsList)
            st.display();

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        if(yours == true){
            System.out.print("\n\t\tWant To See Your Items Only? (Yes / No)  > ");
            viewYours = cin.next();
            if(viewYours.equalsIgnoreCase("yes"))
                seeYourItemsOnly(SegnoGebeya.currentBuyerOrSeller, false);
        }
        
    }
    
    public static void seeYourItemsOnly(Users current, boolean all){
        
        if(ItemsList.isEmpty()){
            System.out.println("\n\t\t\t\t\t\tEmpty List Of Items!!\n");
            return;
        }
        
        Scanner cin = new Scanner(System.in);

        String viewAll;
        
        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                       Showing Your Items Only");
        System.out.println(    "----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s\t%-25s\t%-12s\t%-9s\t%-10s\t%-25s%-12s%-12s\n", "ItemName", "ItemType", "ProductCode", "Quantity", "UnitPrice", "EmployeeName", "OffPrice", "OffQuantity");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        for(int r=0; r<ItemsList.size(); r++)
            if(ItemsList.get(r).employeeName.equals(current.name) )
                ItemsList.get(r).display();
        
        if(((Seller)current).AddedItems == 0)
            System.out.println("\n\t\t\t\t\t\tEmpty List Of Items!!\n");
        
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        if(all == true){
            System.out.print("\n\t\tWant To See All Items? (Yes / No)  > ");
            viewAll = cin.next();
            if(viewAll.equalsIgnoreCase("yes"))
                seeAllTheItems(false);
        }
        
    }
    
    public void deleteYourItems(){
        
        if(ItemsList.isEmpty() || ((Seller)SegnoGebeya.currentBuyerOrSeller).AddedItems == 0){
            System.out.println("\nSorry... Your Item List is Empty");
            return ;
        }
        
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                Deleting Your Items");
        System.out.println(    "--------------------------------------------------------------------------------------------------------------\n\n");
        
        Scanner cin = new Scanner(System.in);
        boolean deleteSuccessfull = false;
        int to_Be_Removed_Product_Num = 0;
        
        System.out.print("Want To See Your Items List (yes / no)  > ");
            
        if(cin.next().equalsIgnoreCase("yes"))
            seeYourItemsOnly(this, false);          //seeYourItemsOnly(SegnoGebeya.currentBuyerOrSeller, false);

        System.out.print("\nEnter in the Product Number Of D Item  > ");
        to_Be_Removed_Product_Num = SegnoGebeya.validateNumber();          
        tempItem = isValidProductNum(to_Be_Removed_Product_Num);
        
        if(tempItem != null){
            SearchForASingleItem(to_Be_Removed_Product_Num);
        
            if(SegnoGebeya.AskToconfirm().equalsIgnoreCase("yes")){
                for (int r = ItemsList.size()-1; r >= 0; r--) {                            //D best way 2 iterate n remove, b/c there is no skipping of elements;
                    Store st = ItemsList.get(r);
                    if(st.productCode == to_Be_Removed_Product_Num && st.employeeName.equals(SegnoGebeya.currentBuyerOrSeller.name)){
                        ItemsList.remove(r);
                        AddedItems--;
                        deleteSuccessfull = true;
                        break;
                    }
                }
            }
        }
        else
            System.out.println("\nSorry, The product Number " + to_Be_Removed_Product_Num + " was not found in the items list. OR You DON'T HAVE THE PREVILEDGE to delete it.\n");
        if(deleteSuccessfull)
            System.out.println("\nDeletion Sucessful.\n");
        else
            System.out.println("\nDeletion Was Not Sucessful.\n");            
    }
    
    public static void sortItems(){
        
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                Sorting Items");
        System.out.println(    "--------------------------------------------------------------------------------------------------------------\n\n");
        
        int choose, asceOrDesce;
        
        if(ItemsList.isEmpty()){
            System.out.println("Sorry... The Item List is Empty");
            return ;
        }        
        if(SegnoGebeya.currentBuyerOrSeller.userType.equals(SegnoGebeya.SELLER_TYPE) && ((Seller)SegnoGebeya.currentBuyerOrSeller).AddedItems == 0){
            System.out.println("Sorry... Your Item List is Empty.");
            return ;
        }
        
        choose = getSortType();
        
        switch(choose){
            case 1:
                asceOrDesce = AscendinglyOrDescendingly();
                if(asceOrDesce == 0) break;
                
                Collections.sort(ItemsList, (Store o1, Store o2) -> {
                    if(asceOrDesce == 1)
                        return String.valueOf(o1.itemName).compareTo(o2.itemName);
                    else
                        return String.valueOf(o2.itemName).compareTo(o1.itemName);
                });
                break;
            case 2:
                asceOrDesce = AscendinglyOrDescendingly();
                if(asceOrDesce == 0) break;
                
                Collections.sort(ItemsList, (Store o1, Store o2) -> {
                    if(asceOrDesce == 1)
                        return String.valueOf(o1.itemType).compareTo(o2.itemType);
                    else
                        return String.valueOf(o2.itemType).compareTo(o1.itemType);
                });
                break;
            case 3:
                asceOrDesce = AscendinglyOrDescendingly();
                if(asceOrDesce == 0) break;
                
                Collections.sort(ItemsList, (Store o1, Store o2) -> {
                    if(asceOrDesce == 1)
                        return Integer.valueOf(o1.productCode).compareTo(o2.productCode);
                    else
                        return Integer.valueOf(o2.productCode).compareTo(o1.productCode);
        });
                break;
            case 4:
                asceOrDesce = AscendinglyOrDescendingly();
                if(asceOrDesce == 0) break;
                
                Collections.sort(ItemsList, (Store o1, Store o2) -> {
                    if(asceOrDesce == 1)
                        return Integer.valueOf(o1.quantity).compareTo(o2.quantity);
                    else
                        return Integer.valueOf(o2.quantity).compareTo(o1.quantity);
        });
                break;
            case 5:
                asceOrDesce = AscendinglyOrDescendingly();
                if(asceOrDesce == 0) break;
                
                Collections.sort(ItemsList, (Store o1, Store o2) -> {
                    if(asceOrDesce == 1)
                        return Double.valueOf(o1.price).compareTo(o2.price);
                    else
                        return Double.valueOf(o2.price).compareTo(o1.price);
        });
                break;
            case 6:
                asceOrDesce = AscendinglyOrDescendingly();
                if(asceOrDesce == 0) break;
                
                Collections.sort(ItemsList, new Comparator<Store>(){
                    public int compare(Store o1, Store o2){
                        if(asceOrDesce == 1)
                            return String.valueOf(o1.employeeName).compareTo(o2.employeeName);
                        else
                            return String.valueOf(o2.employeeName).compareTo(o1.employeeName);
                    }
                });
                break;
            case 0:
                break;
            default:
                System.out.println("\nPlease Type A Relevant Input.");
        }
    }
    
    public static int getSortType(){        
        Scanner inp = new Scanner(System.in);
        int choose;
        
        do{
            System.out.println("\n\tChoose How To Sort : \n");
            System.out.println("\t\t1   -------------  By Item Name");
            System.out.println("\t\t2   -------------  By Item Type");
            System.out.println("\t\t3   -------------  By Product Number");
            System.out.println("\t\t4   -------------  By Item Quantity");
            System.out.println("\t\t5   -------------  By Item Price");
            System.out.println("\t\t6   -------------  By Employee Name");
            System.out.println("\t\t0   -------------  Cancel");

            System.out.print("\n\t\t\t# > ");
            choose = SegnoGebeya.validateNumber();
        }while(choose < 0 || choose > 6);
        
        return choose;
    }
    
    public static int AscendinglyOrDescendingly(){
        Scanner cin = new Scanner(System.in);
        int option;
        
        do{
            System.out.println("\t\t1   -------------  Ascendingly");
            System.out.println("\t\t2   -------------  Descendingly");
            System.out.println("\t\t0   -------------  Cancel");
            System.out.print("\t\t\t\t  > ");

            option = SegnoGebeya.validateNumber();
        }while(option < 0 || option > 2);
        
        return option;
    }
    
    public void editYourItems(){
        
        if(ItemsList.isEmpty() || ((Seller)SegnoGebeya.currentBuyerOrSeller).AddedItems == 0){
            System.out.println("Sorry... Your Item List is Empty");
            return ;
        }
        
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                Editing An Item");
        System.out.println(    "--------------------------------------------------------------------------------------------------------------\n\n");
        
        Scanner cin = new Scanner(System.in);
        Scanner input_1 = new Scanner(System.in);
        Scanner input_2 = new Scanner(System.in);
        Scanner input_3 = new Scanner(System.in);
        
        System.out.print("Want To See your items List (yes / no)  > ");
        if(cin.next().equalsIgnoreCase("yes"))
            seeYourItemsOnly(this, false);
        
        System.out.print("Enter in Product Code  > ");
        int prodCode = SegnoGebeya.validateNumber();
        
        tempItem = isValidProductNum(prodCode);                                                                 //assigned the retured object to temp, w/c is a reference;
        
        if(tempItem != null && tempItem.employeeName.equals(SegnoGebeya.currentBuyerOrSeller.name)){            //if the item 2 B modified is Found
            
            SearchForASingleItem(prodCode);
            
            int choose = getEditType();
            
            switch(choose){
                case 1:
                    System.out.print("\nEnter in new Item Name  > ");
                    tempItem.itemName = input_1.nextLine();
                    System.out.println("\n\n Name Was Successfully Modified.\n");
                    break;
                case 2:
                    tempItem.itemType = Store.ALL_ITEM_TYPES[getItemType() - 1];
                    System.out.println("\n\n Type Was Successfully Modified.\n");
                    break;
                case 3:
                    System.out.print("\nEnter in new Item Quantity  > ");
                    tempItem.quantity = SegnoGebeya.validateNumber();
                    System.out.println("\n\n Quantitiy Was Successfully Modified.\n");
                    break;
                case 4:
                    System.out.print("\nEnter in new Item Price  > ");
                    tempItem.price = SegnoGebeya.validateDouble();
                    System.out.println("\n\n Price Was Successfully Modified.\n");
                    break;
                default:
                    System.out.println("\nPlease Choose The Appropriate Numbers Only!");
            }
        }else                                               //The item was not found
            System.out.println("\nThe Product Code " + prodCode + " was not Found in The Items List.\n");
        
    }
    
    public int getEditType(){
        
        int choose;
        
        do{
            System.out.println("\n\tChoose What To Edit: \n");
            System.out.println("\t\t1   -------------  Edit Item Name");
            System.out.println("\t\t2   -------------  Edit Item Type");
            System.out.println("\t\t3   -------------  Edit Item Quantity");
            System.out.println("\t\t4   -------------  Edit Item Price");
            System.out.println("\t\t0   -------------  C A N C E L");

            System.out.print("\n\t\t\t# > ");
            choose = SegnoGebeya.validateNumber();
        }while(choose < 0 || choose > 4);
        
        return choose;
    }
    
    public static Store isValidProductNum(int prodNum){
        
        for(int r=0; r < ItemsList.size(); r++)
            if(ItemsList.get(r).productCode == prodNum)
                return ItemsList.get(r);
        
        return null;                    //if the item with such product code is not found!!!
    }
    
    public static void SearchForItems(){
        
        if(ItemsList.isEmpty()){
            System.out.println("Sorry... The Item List is Empty");
            return ;
        }
        
        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                              Searching An Item");
        System.out.println(    "----------------------------------------------------------------------------------------------------------------------------------------------");        
        
        Scanner input_1 = new Scanner(System.in);
        
        System.out.print("\nEnter any word you wanted to search  > ");
        String toBeSearched = input_1.nextLine();
        
        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s\t%-25s\t%-12s\t%-9s\t%-10s\t%-25s%-12s%-12s\n", "ItemName", "ItemType", "ProductCode", "Quantity", "UnitPrice", "EmployeeName", "OffPrice", "OffQuantity");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        for(int r=0; r<ItemsList.size(); r++)
            if(ItemsList.get(r).employeeName.contains(toBeSearched) || ItemsList.get(r).itemName.contains(toBeSearched) 
                || ItemsList.get(r).itemType.contains(toBeSearched) || String.valueOf(ItemsList.get(r).productCode).contains(toBeSearched) 
                || String.valueOf(ItemsList.get(r).price).contains(toBeSearched) 
                || String.valueOf(ItemsList.get(r).quantity).contains(toBeSearched) )
                ItemsList.get(r).display();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }
    
    public void offerDiscount(){
        
        if(ItemsList.isEmpty() || ((Seller)SegnoGebeya.currentBuyerOrSeller).AddedItems == 0){
            System.out.println("The Items List Is Empty.");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                              Offering A Discount");
        System.out.println(    "----------------------------------------------------------------------------------------------------------------------------------------------");
        
        int    discountQuantity, prodNum;
        double loweredPrice;
        
        seeYourItemsOnly(SegnoGebeya.currentBuyerOrSeller, false);
        
        System.out.print("\nEnter In the product code of the item you want to offer a discount: ");
        prodNum = SegnoGebeya.validateNumber();
        
        tempItem = isValidProductNum(prodNum);
        
        if(tempItem != null && tempItem.employeeName.equals(SegnoGebeya.currentBuyerOrSeller.name)){        //one can only edit his items.
            
            System.out.print("\nOffer this discount for any user who buys more than WHAT copy?? \n\t\t\t#  > ");
            discountQuantity = SegnoGebeya.validateNumber();
            
            if(tempItem.quantity < discountQuantity)
                System.out.println("\nIncorrect!! Less Actual Quantity than The Specified Discount Quantity.");
            else{
                
                System.out.printf("\nNormal unit Price  > %.2f\n", tempItem.price);
                System.out.print("New Lowered Price  > ");
                loweredPrice = SegnoGebeya.validateDouble();
                
                if(loweredPrice >= tempItem.price)
                    System.out.println("\nError, you didn't providede a lower Price.");
                else{
                    tempItem.persentOfDiscount = calculatePercentageOff(tempItem.price, loweredPrice );
                    tempItem.discountQuantity = discountQuantity;
                    tempItem.discountPrice = loweredPrice;
                    System.out.printf("\nYou provided a %.2f%s\n", tempItem.persentOfDiscount, "% Discount!!");
                }
            }
        }else
            System.out.println("\nSorry, The Product Number " + prodNum + " Was Not Found In The Items List. OR You DON'T HAVE THE PREVILEDGE To Offer A Discount For It.\n");
    }
    
    double calculatePercentageOff(double olderValue, double newValue){
        return (100.00 - ((newValue / olderValue) * 100));
    }
    
    void PrivateDisplay(){
        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                       Showing Your Profile");
        System.out.println(    "----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s\t%-15s\t%-9s\t%-9s\t%-18s\t%-9s\t%-15s\t%-10s\t%-15s\n", "Name", "UserType", "Id", "HouseNum", "Subcity", "CreditNum", "InitialAmount", "ItemsSold", "password");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s\t%-15s\t%-9d\t%-9d\t%-18s\t%-9d\t%-15.2f\t%-10d\t%-15s\n", super.name, super.userType, super.id, super.addr.houseNum, super.addr.subCity, super.creditNum, super.initialAmount, soldItems, super.password);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    @Override
    int menu() {
        
        int option;
        
        System.out.println("______________________________________________________________________________________________________________");
            System.out.println("\t                         ()                                          ()         ");
            System.out.println("\t                         ()               SELLERS MENU               ()         ");
            System.out.println("\t                  _______()__________________________________________()________ ");
            System.out.println("\t                 [                                                             ]");
            System.out.println("\t                 [           PRESS       TO                                    ]");
            System.out.println("\t                 [_____                                                   _____]");
            System.out.println("\t                 [ ]___        1        Add Your Items                    ___[ ]");
            System.out.println("\t                 [ ]___        2        See Items                         ___[ ]");
            System.out.println("\t                 [ ]___        3        Delete Your Items                 ___[ ]");
            System.out.println("\t                 [ ]___        4        Sort Items                        ___[ ]");
            System.out.println("\t                 [ ]___        5        Search For Items                  ___[ ]");
            System.out.println("\t                 [ ]___        6        Edit Your Items                   ___[ ]");
            System.out.println("\t                 [ ]___        7        See Your Generated Slip           ___[ ]");
            System.out.println("\t                 [ ]___        8        Offer Discount                    ___[ ]");
            System.out.println("\t                 [ ]___        9        Employee Of The Day               ___[ ]");
            System.out.println("\t                 [ ]___        10       View Your Profile                 ___[ ]");
            System.out.println("\t                 [ ]___        0        L O G   O U T                     ___[ ]");
            System.out.println("\t                 [_____________________________________________________________]");
            System.out.println("                                                                   ");
            System.out.println("                                                                   ");
	System.out.println("______________________________________________________________________________________________________________");
        
        System.out.print("\n\t\t Plz enter a choice (" + name + "):  ");
	option = SegnoGebeya.validateNumber();
        
        return option;
        
    }
    
    @Override
    void display() {
        System.out.printf("%-25s\t%-15s\t%-9d\t%-12d\t%-20s\t%-12d\n", super.name, super.userType, super.id, super.addr.houseNum, super.addr.subCity, soldItems);
    }

    @Override
    void Profile() {
        PrivateDisplay();
        
        Scanner cin = new Scanner(System.in);
        Scanner cin_2 = new Scanner(System.in);
        
        System.out.print("\nMake Changes To Your Account?? (yes /no)  > ");
        
        if(cin.next().equalsIgnoreCase("yes")){
            int x = SegnoGebeya.getProfileEditType();
            String newSpec = "";
            
            switch(x){
                case 1:
                    System.out.print("\nEnter in new Name  > ");
                    newSpec = SegnoGebeya.validateString();
                    if(SegnoGebeya.validateDuplicateNameAndTypeAndPassword(newSpec, this.password, this.userType))  //if this returns true, then it means the user existed
                        System.out.println("\n\n Sorry, Name Modification Failed because Another User Existed With Your Kind Of Specification.\n");
                    else{                                                                                           //the user doesn't exit so we can modify
                        this.name = newSpec;
                        System.out.println("\n\n Name Was Successfully Modified.\n");
                    }
                    break;
                case 2:
                    System.out.print("\nEnter in new Password  > ");
                    newSpec = cin_2.nextLine();
                    
                    if(SegnoGebeya.validateDuplicateNameAndTypeAndPassword(this.name, newSpec, this.userType))  //if this returns true, then it means the user existed
                        System.out.println("\n\n Sorry, Name Modification Failed because Another User Existed With Your Kind Of Specification.\n");
                    else{                                                                                       //the user doesn't exit so we can modify
                        this.password = newSpec;
                        System.out.println("\n\n Password Was Successfully Modified.\n");
                    }
                    break;
            }
        }
    }
}
