/*************************
******Libraries USED******
**************************/
#include <iostream>
#include <string.h>
#include <conio.h>
#include <vector>
#include <stdlib.h>

using namespace std;

void capitalize(string&);

/****************************
******THE PRODUCT CLASS******
*****************************/

class product{
private:
    unsigned int quantity;
    float ucost,tcost,tax,profit;
    string name,code,category;
public:
    void view(){
        cout<<name<<'\t'<<code<<'\t'<<category<<'\t'<<ucost<<'\t'<<quantity<<'\t'<<tcost<<'\t'<<tax<<'\t'<<profit<<endl;
    }

    friend void input(vector<product>&);
    friend void search(vector<product>&);
    friend void sort(vector<product>&);
};

/*******************************
******FUNCTION DECLERATION******
********************************/

void Mmenu();
void display(vector<product>&);

/***************************
******THE MAIN PROGRAM******
****************************/

int main(){
    vector<product> pro;
    system("color 3F");
    bool exit=false;
    do{
        Mmenu();
        switch(getch()){
        case '1': input(pro); break;
        case '2': search(pro); break;
        case '3': display(pro); break;
        default:
            exit = true;
        }
    }while(!exit);

}

/******************************
******FUNCTION DEFINITION******
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
    system("cls");
    cout<<"Press...\n";
    cout<<"  1.For Registering a Product.\n";
    cout<<"  2.For Searching a Product.\n";
    cout<<"  3.To See All.\n";
    cout<<"Any to Exit.\n";
}
void input(vector<product>& pro){
    int x = (int)pro.size();
    pro.push_back(product());
    system("cls");
    cout<<"New Product...\n";
    cout<<"Name: "; cin>>pro[x].name; capitalize(pro[x].name);
    cout<<"Code: "; cin>>pro[x].code; capitalize(pro[x].code);
    cout<<"Category: "; cin>>pro[x].category; capitalize(pro[x].category);
    cout<<"Ucost: ";    cin>>pro[x].ucost;
    cout<<"Quantity: "; cin>>pro[x].quantity;
    pro[x].tcost = pro[x].ucost*pro[x].quantity;
    pro[x].tax = pro[x].tcost*0.15;
    pro[x].profit = pro[x].tcost-pro[x].tax;
    cout<<"Registration Successful.\n";
    getch();
}
void display(vector<product>& pro){
    system("cls");
    bool exist = false;bool one_t = false;
    for(int i=0;i<(int)pro.size();i++){
        if(!one_t) cout<<"Name   Code   Category   Ucost   Quantity   Revenue   Tax   Profit"<<endl;
        pro[i].view();
        exist = true; one_t = true;
    }
    if(!exist) cout<<"Nothing Found. Please Register To SEE Something.";
    getch();
}
void search(vector<product>& pro){
    system("cls");
    string code;bool one = false;
    bool exist = false;
    cout<<"Code: "; cin>>code; capitalize(code);
    for(int i=0;i<(int)pro.size();i++){
        if(pro[i].code == code){
            if(!one) cout<<"Name   Code   Category   Ucost   Quantity   Revenue   Tax   Profit"<<endl;
            pro[i].view();
            one = true;
        }
        exist = true;
    }
    if(!one && exist) cout<<"\nCode Not Found.\n";
    if(!exist) {
        system("cls");
        cout<<"\n\n\nNo Registered Products. Please Register to SEE Something.\n";
    }
    getch();

}
