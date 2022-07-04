/*************************
******Libraries USED******
**************************/

#include <iostream>   //FOR CONSOLE INPUT/OUTPUT.
#include <fstream>    //FOR FILE INPUT/OUTPUT.
#include <string.h>   //FOR USING STRING TYPE.
#include <vector>     //FOR USING VECTOR TEMPLATE.
#include <conio.h>    //FOR USING FUNCTIONS LIKE getch().
#include <windows.h>  //FOR USING WINDOWS SPECIFIC FUNCTIONS.

using namespace std;

/**************************************
******Global Variable Decleration******
***************************************/

int quantity;
string name,code,category;
float ucost,tcost,tax,profit;

/*******************************
******Function Decleration******
********************************/

void Mmenu();        //THE MAIN MENU.
void input();        //FOR INSERTING DETAILS.
void display();      //FOR DISPLAYING ALL DATA STORED.
void search();       //FOR SEARCHING A PRODUCT.
void clear_dbase();  //FOR CLEARING THE WHOLE DATA STORED.
void sorted();

/*************************************
******The Main Program Goes Here******
**************************************/

int main(){
    system("color 3F");
    bool exit=false;
    do{
        Mmenu();
        switch(getch()){
        case '1': input(); break;
        case '2': search(); break;
        case '3': sorted(); break;
        case '5': display(); break;
        case '6': clear_dbase();break;
        default:
            system("title Group 7 - Product Information: GOOD BYE");
            exit=true;
            cout<<"Thank You for Visiting us.";
            Sleep(1000);
            system("cls");
            system("color 0F");
        }
    }while(!exit);
    delete &exit;
}
/***************************
******Needed Structure******
****************************/

struct datam{
    int quantity;
    string name,code,category;
    float ucost,tcost,profit,tax;
};

/******************************
******Function Definition******
*******************************/

void capitalize(string& x){
   for(int i=0;i<(int)x.size();i++){
      if(x[i] >= 'a' && x[i] <= 'z'){
         x[i] += 'A'-'a';
      }
      if(x[i] == '\0') break;
   }
}
void Mmenu(){
    system("title Group 7 - Product Information: Main Menu");
    system("cls");
    cout<<"Press...\n";
    cout<<"  1.For Registering a product.\n";
    cout<<"  2.For Searching a product.\n";
    cout<<"  3.For Sorted Output.\n";
    cout<<"  5.To Display all.\n";
    cout<<"  6.To Clear the Database.\n";
    cout<<"Any Key to Exit.\n\n\n\n";
}
void input(){
    system("title Group 7 - Product Information: Registration");
    system("cls");
    fstream f;
    f.open("aastu.db",ios::app|ios::in|ios::out);
    cout<<"NEW PRODUCT...\n";
    cout<<"Name: ";cin>>name;capitalize(name);
    cout<<"Code: ";cin>>code;capitalize(code);
    cout<<"Category: ";cin>>category;capitalize(category);
    cout<<"Ucost: ";cin>>ucost;
    cout<<"Quantity: ";cin>>quantity;
    tcost = ucost*quantity;
    tax = tcost*0.15;
    profit = tcost-tax;
    f<<name<<'\t'<<code<<'\t'<<category<<'\t'<<ucost<<'\t'<<quantity<<'\t'<<tcost<<'\t'<<tax<<'\t'<<profit<<endl;
    f.close();
    cout<<"Registration Succesful.";
    getch();
}
void display(){
    system("title Group 7 - Product Information: All Products");
    system("cls");
    string line;bool one=false;
    fstream f;
    f.open("aastu.db",ios::app|ios::in|ios::out);
    while(getline(f,line)){
        if(!one) cout<<"Name   Code   Category   Ucost   Quantity   Cost   Tax   Profit"<<endl;
        cout<<line<<endl;
        one = true;
    }
    if(!one) cout<<"\n\n\nNothing Found,Please Register to SEE Something.\n";
    f.close();
    getch();
}
void clear_dbase(){
    system("cls");
    fstream f;
    f.open("aastu.db",ios::out);
    f.close();
    cout<<"Clearing Database";
    for(int i=0;i<4;i++){
        cout<<'.';Sleep(1000);
    }system("cls");
    cout<<"\nDatabase cleared.\n";
    getch();
}
void search(){
    system("cls");
    string srch,line;bool one=false;bool exist = false;
    fstream f;
    f.open("aastu.db",ios::app|ios::in|ios::out);
    cout<<"Enter KeyWord from the product: ";
    cin>>srch;capitalize(srch);
    cout<<"\n\n\n";
    while(getline(f,line)){
        if(line.find(srch)!= line.npos){
            if(!one) cout<<"NAME   CODE   CATEGORY   UCOST   QUANTITY   COST   TAX   PROFIT"<<endl;
            cout<<line<<endl;
            one = true;
        }
        exist = true;
    }
    if(!one && exist) cout<<"Product Not Found.\n";
    if(!exist) { system("cls");
        cout<<"\n\n\nNothing Found, Please Register to SEE Something.\n";
    }

    f.close();
    getch();
}
void sort(vector<string>& mx){
    bool fx = true;
    while(true){
        fx = true;
        for(int i=1;i<(int)mx.size();i++){
            if(mx[i-1]>mx[i]){
                string temp = mx[i-1];
                mx[i-1] = mx[i];
                mx[i] = temp;
                fx = false;
            }
        }
        if(fx) break;
    }
}
void sorted(){
    fstream f1;
    vector<string> dat;
    string line;bool k=false;
    f1.open("aastu.db",ios::in);
    while(getline(f1,line)){
        dat.push_back(line);
        k = true;
    }
    f1.close();
    if(!k){ system("cls");
        cout<<"\n\n\nNothing Found, Please Register to SEE Something.\n";
    }
    else if(k){
        sort(dat);
        for(int i=0;i<(int)dat.size();i++){
            cout<<dat[i]<<endl;
        }
    }
    getch();
}
