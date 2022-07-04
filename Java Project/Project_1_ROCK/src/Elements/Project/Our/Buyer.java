package Elements.Project.Our;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import static Elements.Project.Our.Store.new_arrival_items;
import static Elements.Project.Our.SegnoGebeya.ItemsList;
import static Elements.Project.Our.SegnoGebeya.deleteSoldItems;
import static Elements.Project.Our.SegnoGebeya.retunrSpecificSeller;
import static Elements.Project.Our.SegnoGebeya.usersBills;
import static Elements.Project.Our.Seller.*;

public class Buyer extends Users{
    
    Scanner input = new Scanner(System.in);
    
    public static int noofBuyers;
    public static ArrayList<Store> discountedItemsList = new ArrayList<>();
    public final static double[] DELIVERY_FEE = {10.00, 2.50, 3.50, 4.50, 25.00, 6.90, 4.50, 9.00, 7.00, 5.50};
    
    Buyer(String name, int creditNum, Address addr, String userType, double initialAmount, String password) {
        super(name, creditNum, ++noofBuyers, addr, userType, initialAmount, password);                                 //unique Buyer Id
    }
    
    public void seeItems(boolean again){
        Scanner cin = new Scanner(System.in);
        Seller.seeAllTheItems(false);                                  //added (Seller.)  jst to avoid name conflict
        if(!(ItemsList.isEmpty()) && again == true){
            System.out.print("Want To Buy?? (yes / no) : ");
            if(cin.next().equalsIgnoreCase("yes"))
                buyItems(this, true);
        }
    }
    
    public void buyItems(Users current, boolean dontShow){
        
        Store tempItem;
        if(ItemsList.isEmpty()){
            System.out.println("\n\t\t\t\t\t\tEmpty List Of Items!!\n");
            return;
        }
        Scanner cin = new Scanner(System.in);
        int prodcode, quantity;
        
        if(!dontShow)
            seeItems(false);
        
        System.out.printf("\nYour Money Amount  > %.2f\n", current.initialAmount);
        
        System.out.print("\nProduct code  > ");
        prodcode = SegnoGebeya.validateNumber();
        
        tempItem = isValidProductNum(prodcode);
        
        if(tempItem != null){                               //if the item 2 B Bought is Found
            
            System.out.print("\nQuantity  > ");
            quantity = SegnoGebeya.validateNumber();
            
            if(quantity > tempItem.quantity)
                System.out.println("\nCan't Perform This Transaction. The Quantity You Asked 4 Doesn't exit.\n");
            else if( (( (tempItem.persentOfDiscount > 0.0 && quantity <= tempItem.discountQuantity) ? tempItem.discountPrice : tempItem.price) * quantity) + (tempItem.VAT * (( (tempItem.persentOfDiscount > 0.0 && quantity <= tempItem.discountQuantity) ? tempItem.discountPrice : tempItem.price) * quantity)) + getFeeOfDelivey() > current.initialAmount)
                System.out.println("\nCan't Perform This Transaction. Recharge Your Account Balance\n");
            else{
                if(tempItem.persentOfDiscount > 0.0 && quantity <= tempItem.discountQuantity){                                     //if the item has a discount;
                    subtractFromBuyerAccount(this, tempItem.discountPrice, quantity, tempItem.VAT, getFeeOfDelivey(), tempItem.employeeId, tempItem.itemName);
                    addToSellerAccount(tempItem.employeeId, tempItem.discountPrice, quantity, tempItem.itemName);
                }else{
                    subtractFromBuyerAccount(this, tempItem.price, quantity, tempItem.VAT, getFeeOfDelivey(), tempItem.employeeId, tempItem.itemName);
                    addToSellerAccount(tempItem.employeeId, tempItem.price, quantity, tempItem.itemName);
                }
                
                tempItem.quantity = tempItem.quantity - quantity;
                if(tempItem.quantity == 0)
                    deleteSoldItems(tempItem.productCode);
                
                System.out.printf("\nYour Money Amount  > %.2f\n", current.initialAmount);
            }
        }else                                                                   //The item was not found
            System.out.println("\nThe Product Code " + prodcode + " was not Found in The Items List.\n");
    }
    
    public void subtractFromBuyerAccount(Users currentBuyer, double itemPrice, int itemQuantity, double Vat, double deliveryFee, int employeeId, String itemName) {
        Users tempSeller = retunrSpecificSeller(employeeId);
        currentBuyer.initialAmount = currentBuyer.initialAmount - ((itemPrice * itemQuantity) + ((itemPrice * itemQuantity) * Vat) + deliveryFee);
        usersBills.add(new PaySlip(tempSeller.name, currentBuyer.id, currentBuyer.name, currentBuyer.creditNum, currentBuyer.userType, itemName, itemQuantity, itemPrice, deliveryFee));
        System.out.println("\n\tTransaction Successful!! Your Pay Slip Has Been Generated.");               //Now a buyer can see his generated slip, w/c is very interesting;
        System.out.println("\n\tThanks For Choosing Our Site And Please Support Us By Sharing This Site.");
    }
    
    public void addToSellerAccount(int employeeId, double itemPrice, int itemQuantity, String itemName) {
        Users tempSeller = retunrSpecificSeller(employeeId);
        tempSeller.initialAmount = tempSeller.initialAmount + (itemPrice * itemQuantity);
        ((Seller)tempSeller).soldItems += itemQuantity;
        usersBills.add(new PaySlip(null, tempSeller.id, tempSeller.name, tempSeller.creditNum, tempSeller.userType, itemName, itemQuantity, itemPrice, 0.00));
    }
    
    public double getFeeOfDelivey(){
        switch(this.addr.subCity){
            case "Addis Ketema":    return DELIVERY_FEE[0];
            case "Akaki Kaliti":    return DELIVERY_FEE[1];
            case "Arada":           return DELIVERY_FEE[2];
            case "Bole":            return DELIVERY_FEE[3];
            case "Gulele":          return DELIVERY_FEE[4];
            case "Kirkos":          return DELIVERY_FEE[5];
            case "Kolfe Keranio":   return DELIVERY_FEE[6];
            case "Lideta":          return DELIVERY_FEE[7];
            case "Nifas Silk Lafto":return DELIVERY_FEE[8];
            case "Yeka":            return DELIVERY_FEE[9];
        }
        return 0;
    }
    
    public void seeAboutItemDescription(){
        
        Scanner cin = new Scanner(System.in);
        
        if(ItemsList.isEmpty()){
            System.out.println("\n\t\t\t\t\t\tEmpty List Of Items!!\n");
            return;
        }
        
        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                         Displaying Item Description");
        System.out.println(    "----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf ("%-25s\t%-20s\t%-9s\t%-55s\t%-25s\n", "ItemName", "ItemType", "ProductCode", "Description", "EmployeeName");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");            
        for(Store st : ItemsList)
            System.out.printf("%-25s\t%-20s\t%-9d\t%-55s\t%-25s\n", st.itemName, st.itemType, st.productCode, st.itemDescription, st.employeeName);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        if(!(ItemsList.isEmpty())){
            System.out.print("Want To Buy?? (yes / no) : ");
            if(cin.next().equalsIgnoreCase("yes"))
                buyItems(this, true);
        }   
    }

    public void searchItems(){
        Scanner cin = new Scanner(System.in);
        SearchForItems();
        
        if(!(ItemsList.isEmpty())){
            System.out.print("Want To Buy?? (yes / no) : ");
            if(cin.next().equalsIgnoreCase("yes"))
                buyItems(this, true);
        }
    }
    
    public void sortItems(){
        Scanner cin = new Scanner(System.in);
        Seller.sortItems();
        
        if(!(ItemsList.isEmpty())){
            System.out.print("Want To Buy?? (yes / no) : ");
            if(cin.next().equalsIgnoreCase("yes"))
                buyItems(this, true);
        }
    }
    
    public void seeHighestDiscounts(){                                      //Will Display One Highest Discounted Item And Any Items Which Are Discounted More Than User Provided Percent
        Scanner cin = new Scanner(System.in);
        boolean anyDiscounted = false, add;
        double percentOfDiscount;
        
        if(ItemsList.isEmpty()){
            System.out.println("\nThe Items List Is Empty.\n");
            return;
        }
        for(Store st : ItemsList)
            if(st.discountPrice != 0.0)
                anyDiscounted = true;                                     //for see highest discounted items;
        
        if(anyDiscounted == false){
            System.out.println("\nThere Are No Items Having A Discount.\n");
            discountedItemsList.clear();
            return;
        }
        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                       Highly Discounted Items List");
        System.out.println(    "----------------------------------------------------------------------------------------------------------------------------------------------");        
        
        System.out.print("\nView Items Which Are Discounted More Than How Much Percent ??  > ");
        percentOfDiscount = SegnoGebeya.validateNumber();
        
        for(int r=0; r<ItemsList.size(); r++){                                   //Searching For Items That Are Discounted Above The User Given Discount Percent;
            add = true;
            if(ItemsList.get(r).persentOfDiscount >= percentOfDiscount ){        
                for (int c = 0; c < discountedItemsList.size(); c++) {
                    if(ItemsList.get(r).productCode == discountedItemsList.get(c).productCode)
                        add = false;
                }
                if(add)
                    discountedItemsList.add(ItemsList.get(r));                      //If Any Item Matched and was not previously added, Then Add It To The DisCounted Items List;
            }
        }
        if(discountedItemsList.isEmpty()){
            System.out.println("\nThere Are No Items In Our List With Such A discount!\n");
            return;
        }
        
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s\t%-25s\t%-12s\t%-9s\t%-10s\t%-25s%-12s%-12s\n", "ItemName", "ItemType", "ProductCode", "Quantity", "UnitPrice", "EmployeeName", "OffPrice", "OffQuantity");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        Collections.sort(discountedItemsList, (Store o1, Store o2) -> Double.valueOf(o2.persentOfDiscount).compareTo(o1.persentOfDiscount));  //Sorting The Discounted Items By PercentOfDiscount Descendingly.
        
        for(int r=0; r<discountedItemsList.size() && discountedItemsList.get(r).persentOfDiscount >= percentOfDiscount; r++)                  //Shows All Items That Are Discounted More Than User Given Percent.
            discountedItemsList.get(r).display();
        
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        if(!(ItemsList.isEmpty())){
            System.out.print("Want To Buy?? (yes / no) : ");
            if(cin.next().equalsIgnoreCase("yes"))
                buyItems(this, true);
        }
    }
    
    public void SeeNewArrivalItems(){
        Scanner cin = new Scanner(System.in);
        if(ItemsList.isEmpty()){
            System.out.println("\nThe Items List Is Empty.\n");
            return;
        }    
        if(new_arrival_items.isEmpty()){
            System.out.println("\nTill Now There Are No New Arrival Items.\n");
            return;
        }
        System.out.println("\n\n-------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                       New Arrival Items");
        System.out.println(    "-------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s\t%-25s\t%-12s\t%-9s\t%-10s\t%-25s%-12s%-12s\n", "ItemName", "ItemType", "ProductCode", "Quantity", "UnitPrice", "EmployeeName", "OffPrice", "OffQuantity");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        for(int r=0; r<ItemsList.size(); r++)
            for(int c=0; c<new_arrival_items.size(); c++)
                if(ItemsList.get(r).itemName.equalsIgnoreCase(new_arrival_items.get(c)) )           //if the item has never been added to our site, then it is a new arrival item
                    ItemsList.get(r).display();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        if(!(ItemsList.isEmpty())){
            System.out.print("Want To Buy?? (yes / no) : ");
            if(cin.next().equalsIgnoreCase("yes"))
                buyItems(this, true);
        }
    }
    
    @Override
    int menu() {
        
        int option;
        
        System.out.println("______________________________________________________________________________________________________________");
            System.out.println("\t                         ()                                          ()         ");
            System.out.println("\t                         ()                BUYERS MENU               ()         ");	
            System.out.println("\t                  _______()__________________________________________()________ "); 	
            System.out.println("\t                 [                                                             ]");
            System.out.println("\t                 [           PRESS       TO                                    ]");
            System.out.println("\t                 [_____                                                   _____]");
            System.out.println("\t                 [ ]___        1        Buy                               ___[ ]");
            System.out.println("\t                 [ ]___        2        See Items                         ___[ ]");
            System.out.println("\t                 [ ]___        3        See About Item Descriptions       ___[ ]");
            System.out.println("\t                 [ ]___        4        Sort Items                        ___[ ]");
            System.out.println("\t                 [ ]___        5        Search A Specific Item            ___[ ]");
            System.out.println("\t                 [ ]___        6        See Today's Discount              ___[ ]");
            System.out.println("\t                 [ ]___        7        View Your Generated Slip          ___[ ]");
            System.out.println("\t                 [ ]___        8        See New Arrival Items             ___[ ]");
            System.out.println("\t                 [ ]___        9        Employee Of The Day               ___[ ]");
            System.out.println("\t                 [ ]___        10       View Your Profile                 ___[ ]");
            System.out.println("\t                 [ ]___        0        L O G  O U T                      ___[ ]");
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
        System.out.printf("%-25s\t%-15s\t%-9d\t%-9d\t%-25s\t%-9d\t%-15.2f\t%-10s\n", super.name, super.userType, super.id, super.addr.houseNum, super.addr.subCity, super.creditNum, super.initialAmount, super.password);
    }

    @Override
    void Profile() {
        Scanner cin = new Scanner(System.in);
        Scanner cin_2 = new Scanner(System.in);
        
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                       Showing Your Profile");
        System.out.println(    "--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s\t%-15s\t%-9s\t%-9s\t%-25s\t%-9s\t%-15s\t%-10s\n", "Name", "Usertype", "Id", "HouseN", "Subcity", "CreditN", "InitialAmount", "Password");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        this.display();
        
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
