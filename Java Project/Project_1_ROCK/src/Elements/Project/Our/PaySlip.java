package Elements.Project.Our;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PaySlip extends Users{
    DateFormat date = new SimpleDateFormat("dd / MM / yyyy");
    String date_2 = computerDate();
    
    String sellerName, itemName;
    double itemPrice, Vat, DeliveryFee;
    int    itemQuantitiy;
    
    DecimalFormat twoDecimalDigits = new DecimalFormat( "0.00" );
    
    public PaySlip(String sellerName, int sellerOrBuyerId, String name, int creditNum, String userType, String itemName, int quantity, double Unitprice, double deliveryFee) {
        super(name, creditNum, sellerOrBuyerId, null, userType, 0.0, null);
        this.sellerName    = sellerName;
        this.itemPrice     = Unitprice;
        this.itemName      = itemName;
        this.itemQuantitiy = quantity;
        this.Vat = 0.15;
        this.DeliveryFee = deliveryFee;
    }
    
    @Override
    public String toString(){
        
        String printable = "";
        
        if(userType.equals(SegnoGebeya.BUYER_TYPE))
            printable +=    "\t ________________________________________________________________________________________________\n" + 
                            "\t[                                                                                                ]\n" +
                            "\t[                                 Pay Slip From SegnoGebeya.com                                  ]\n" +
                            "\t[_____                                                                                      _____]\n" +
                            "\t[ ]___                                                           Date : " + date_2 +   "      ___[ ]\n" +
                            "\t[ ]___     User Name      : " + super.name + trailingSpaces(super.name) +
                            "\t[ ]___     User Type      : " + super.userType + trailingSpaces(super.userType) +
                            "\t[ ]___     Cr.Card Number : " + super.creditNum + trailingSpaces(String.valueOf(super.creditNum)) +
                            "\t[ ]___     Item Name      : " + itemName + trailingSpaces(itemName) +
                            "\t[ ]___     Item Price     : " + twoDecimalDigits.format(itemPrice) + trailingSpaces(String.valueOf(twoDecimalDigits.format(itemPrice))) +
                            "\t[ ]___     Quantity       : " + itemQuantitiy + trailingSpaces(String.valueOf(itemQuantitiy)) +
                            "\t[ ]___     VAT            : " + twoDecimalDigits.format(Vat * 100) + "%" + trailingSpaces(String.valueOf((twoDecimalDigits.format(Vat * 100) + "%"))) +
                            "\t[ ]___     Delivery Fee   : " + twoDecimalDigits.format(DeliveryFee) + trailingSpaces(String.valueOf(twoDecimalDigits.format((DeliveryFee)))) +
                            "\t[ ]___     Total Price    : " + twoDecimalDigits.format((itemPrice * itemQuantitiy) + ((itemPrice * itemQuantitiy) * Vat) + DeliveryFee) + trailingSpaces(String.valueOf(twoDecimalDigits.format((itemPrice * itemQuantitiy) + ((itemPrice * itemQuantitiy) * Vat) + DeliveryFee))) +
                            "\t[ ]___     From Seller    : " + sellerName + " At SegnoGebeya.com" + trailingSpaces(sellerName + " At SegnoGebeya.com") + 
                            "\t[ ]___                                                                                      _____]\n" +
                            "\t[ ]___                                                       SegnoGebeya.com's Signature    _____]\n" +
                            "\t[________________________________________________________________________________________________]\n";
        
        else
            printable +=    "\t ________________________________________________________________________________________________\n" + 
                            "\t[                                                                                                ]\n" +
                            "\t[                                 Pay Slip From SegnoGebeya.com                                  ]\n" +
                            "\t[_____                                                                                      _____]\n" +
                            "\t[ ]___                                                           Date : " + date_2 +   "      ___[ ]\n" +
                            "\t[ ]___     User Name          : " + super.name + trailingSpaces_2(super.name) +                                               
                            "\t[ ]___     User Type          : " + super.userType + trailingSpaces_2(super.userType) + 
                            "\t[ ]___     Cr.Card Number     : " + super.creditNum + trailingSpaces_2(String.valueOf(super.creditNum)) + 
                            "\t[ ]___     Sold Item Name     : " + itemName + trailingSpaces_2(itemName) + 
                            "\t[ ]___     Sold Item Price    : " + twoDecimalDigits.format(itemPrice) + trailingSpaces_2(String.valueOf(twoDecimalDigits.format(itemPrice))) + 
                            "\t[ ]___     Sold Item Quantity : " + itemQuantitiy + trailingSpaces_2(String.valueOf(itemQuantitiy)) + 
                            "\t[ ]___     Total Amount Added : " + twoDecimalDigits.format((itemPrice * itemQuantitiy)) + trailingSpaces_2(String.valueOf(twoDecimalDigits.format((itemPrice * itemQuantitiy)))) + 
                            "\t[ ]___                                                                                      ___[ ]\n" +
                            "\t[ ]___                                                       SegnoGebeya.com's Signature    ___[ ]\n" +
                            "\t[________________________________________________________________________________________________]\n";
        
        return printable;
    }
    
    public String trailingSpaces(String x){
        String a = "";
        
        for(int r=0; r < 64 - x.length(); r++)
            a += " ";
        
        a += "___[ ]\n";
        
        return a;
    }
    
    public String trailingSpaces_2(String x){
        String a = "";
        
        for(int r=0; r < 60 - x.length(); r++)
            a += " ";
        
        a += "___[ ]\n";
        
        return a;
    }
    
    public String computerDate() {
        Calendar cal = Calendar.getInstance();
        String datee = date.format(cal.getTime());
        return datee;
    }
    
    @Override
    int menu() { return 0; }                                                   //we don't want to implement this method as it is pointless, but we needed other attributes for our payslip from the abstract class!

    @Override
    void display() { }                                                         //we don't want to implement this method as it is pointless but we need other things!

    @Override
    void Profile() { }
}
