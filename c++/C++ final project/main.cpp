#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cmath>


using namespace std;

int main()
{
    int arraysize=100;
    int num[arraysize], nsize, numremove;
    int c=0;
    int x=0;
    int y=0;
    int e=0;
   
    {
	
 
    
    	 int ex=1;
   while(ex==1)
	
    {
		
        system("cls");
        cout<<"How many numbers do you want?"<<endl;

        cin>>nsize;

        if(nsize < 0)
        {
            cout<<"you entered a negative value.\nThe program will change it to positive\n";
            nsize=abs(nsize);
            cout<<"\n\n";
        }

        if(nsize != 0)
        {
            if(arraysize >= nsize)
            {
                for(int i=0;i < nsize;i++)
                {
                    num[i]=(rand()*time(0))%100;
                }

                for(int i=0;i < nsize;i++)
                {
                    cout<<num[i]<<" ";
                }

                cout<<"\nWhich number you want to remove?\n";

                cin>>numremove;

                for(int i=0;i < nsize;i++)
                {
                    if(num[i] == numremove)
                        x++;
                    else if(num[i] != numremove)
                        c++;
                }


                for(int i=0;i < nsize;i++)
                {
                    if(c==nsize && x==0 && y==0)
                    {
                        cout<<"The number "<<numremove<<" was not found!";
                        y++;
                    }
                    else if(c!=nsize && x!=0)
                    {
                        if(num[i] != numremove)
                        {
                            if(e==0)
                            {
                                cout<<"After the number "<<numremove<<" is removed.\n";
                                e++;

                            }
                            cout<<num[i]<<" ";

                        }
                    }
                }
                if(x!=0)
                    cout<<endl<<"\nThe number '"<<numremove<<"' has been found "<<x<<" times."<<endl;
                cout<<endl<<endl;
            }
            if(arraysize < nsize)
                cout<<"The number you entered is above the limit. Try again later.\n\n\n";
        }

        else if (nsize == 0)
        {
            cout<<"You entered a value of 0.\n\n";
        }

        cout<<"Please enter '1' to run again.\n";
        cin>>ex;
    }
}
}
