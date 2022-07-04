package Elements.Project.Our;

import java.util.ArrayList;

public class Store{
    
    public final  static String[] ALL_ITEM_TYPES = {"Clothings", "Construction & Home", "Cosmetics & Beauty", "Educationals", "Electronics", "Food & Drink", "Furniture", "Jewelry", "Kitchen Materials", "Others"};
    public static ArrayList<String> new_arrival_items = new ArrayList<>();
    public static ArrayList<String> new_arrival_items_types = new ArrayList<>();
    
    public String itemName, itemType, itemDescription, employeeName;
    public int    productCode, quantity, discountQuantity, employeeId;
    public double price, discountPrice, persentOfDiscount;
    public final double VAT = 0.15;

    public Store(String itemName, String itemType, String itemDescription, int productCode, int quantity, double price, String employeeName, int employeeId) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.productCode = productCode;
        this.quantity = quantity;
        this.price = price;
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        
        //checking a New Arrival Item Each time an item is added to STORE
        if(new_arrival_items.isEmpty()){
            new_arrival_items.add(itemName);
            new_arrival_items_types.add(itemType);
        }else{
            boolean wasThere = false;
            for(int r=0; r<new_arrival_items.size(); r++)
                if(new_arrival_items.get(r).equalsIgnoreCase(itemName) && new_arrival_items_types.get(r).equals(itemType)){
                    wasThere = true;
                    break;
                }
            if(!wasThere){                                       //if the item is not found in the new_arrival_items list, add it to the list;
                new_arrival_items.add(itemName);
                new_arrival_items_types.add(itemType);
            }
            else{                                                //if the item has been in the new_arrival_items list, remove it as it is no longer a new arrival item.
                new_arrival_items.remove(itemName);
                new_arrival_items_types.remove(itemType);
            }
        }
    }
    
    public void display() {
        System.out.printf("%-25s\t%-25s\t%-12d\t%-9d\t%-10.2f\t%-25s%-12.2f%-12d\n", itemName, itemType, productCode, quantity, price, employeeName, discountPrice, discountQuantity);
    }
    
}