/*************************
******Libraries USED******
**************************/
#include <iostream>
#include <string.h>
#include <conio.h>
#include <vector>
#include<stdlib.h>;

using namespace std;

void capitalize(string&);   //because we're using it in the class

/****************************
******THE PRODUCT CLASS******
*****************************/
class product{
private:
    unsigned int quantity;
    float ucost,tcost,tax,profit;
    string name,code,category;
public:
    friend void input(vector<product>&);

    void view(){
        cout<<name<<'\t'<<code<<'\t'<<category<<'\t'<<ucost<<'\t'<<quantity<<'\t'<<tcost<<'\t'<<tax<<'\t'<<profit<<endl;
    }
    void operator = (product& p){
        quantity = p.quantity;
        ucost = p.ucost;
        tcost = p.tcost;
        tax = p.tax;
        profit = p.profit;

        name = p.name;
        code = p.code;
        category = p.category;
    }

    friend void search(vector<product>);
    friend void sort(vector<product>);

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
        case '3': sort(pro); break;
        case '5': display(pro); getch(); break;
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
    cout<<"  3.For Sorted Output.\n";
    cout<<"  5.To See All.\n";
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
    bool exist = false; bool one_t = false;
    for(int i=0;i<(int)pro.size();i++){
        if(!one_t) cout<<"Name   Code   Category   Ucost   Quantity   Revenue   Tax   Profit"<<endl;
        pro[i].view();
        exist = true; one_t = true;
    }
    if(!exist) cout<<"\n\nNothing Found. Please Register To SEE Something.";
    cout<<"\nAny Key To Main Menu.\n";
}
void search(vector<product> pro){
    system("cls");bool found = false;
    vector<product> Pro_copy;
    if(pro.size()>0){
        string code;
        cout<<"Keyword: "; cin>>code; capitalize(code);
        for(int i=0;i<(int)pro.size();i++){
            if(pro[i].name.find(code)!=pro[i].name.npos || pro[i].code.find(code)!=pro[i].code.npos || pro[i].category.find(code)!=pro[i].category.npos){
                Pro_copy.push_back(pro[i]);
                found = true;
            }
        }
        if(found) display(Pro_copy);
        else if(!found) cout<<"\n\nKey word not found.\n";
    }
    else cout<<"\n\nNothing Found.\nPlease Register To SEE Somthing.";
    getch();
}
void sort(vector<product> pro){
    system("cls");
    if(pro.size()>0){
        bool pass = true;
        cout<<"Name:-\n  1.Asc\n  2.Dsc\n";
        cout<<"Code:-\n  3.Asc\n  4.Dsc\n";
        cout<<"Any key to Raw Table.\n.";
        switch(getch()){
        case '1':
            while(true){
                pass = true;
                for(int i=1;i<(int)pro.size();i++){
                    if(pro[i-1].name > pro[i].name){
                        product temp;
                        temp = pro[i-1];
                        pro[i-1] = pro[i];
                        pro[i] = temp;
                        pass = false;
                    }
                }
                if(pass) break;
            }
            display(pro);
            break;
        case '2':
            while(true){
                pass = true;
                for(int i=1;i<(int)pro.size();i++){
                    if(pro[i-1].name < pro[i].name){
                        product temp;
                        temp = pro[i-1];
                        pro[i-1] = pro[i];
                        pro[i] = temp;
                        pass = false;
                    }
                }
                if(pass) break;
            }
            display(pro);
            break;
        case '3':
            while(true){
                pass = true;
                for(int i=1;i<(int)pro.size();i++){
                    if(pro[i-1].code > pro[i].code){
                        product temp;
                        temp = pro[i-1];
                        pro[i-1] = pro[i];
                        pro[i] = temp;
                        pass = false;
                    }
                }
                if(pass) break;
            }
            display(pro);
            break;
        case '4':
            while(true){
                pass = true;
                for(int i=1;i<(int)pro.size();i++){
                    if(pro[i-1].code < pro[i].code){
                        product temp;
                        temp = pro[i-1];
                        pro[i-1] = pro[i];
                        pro[i] = temp;
                        pass = false;
                    }
                }
                if(pass) break;
            }
            display(pro);
            break;
        default: break;
        }
    }
    else cout<<"\n\nNothing Found.\nPlease Register To SEE Somthing.";
    getch();
}
