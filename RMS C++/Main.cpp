#include<iostream>
#include<cstring>
#include"employee.cpp"
#include "manager.cpp"
#include"stock.cpp"
#include"dish.cpp"
#include <fstream>
using namespace std;

int main()
{
	Manager m1;
	Dish products[10];
	ifstream din("Dcount.txt");
	int dcount=0;
    din>>dcount;

	int choice;
    string password;
    int fcount;
    Stock stk(500,400,300,200,100);

    do
    {


	    cout<<"1) Login As Admin."<<endl;
	    cout<<"2) Login As Customer."<<endl;
	    cout<<"3) Exit"<<endl;
	    cin>>choice;

	    switch(choice)
	    {
	    	case 1:{


				string pass;

				ifstream in("APassword.txt");
				in>>pass;

	    		cout<<"Enter your password: "<<endl;
	    		cin>>password;

	    		while(password!=pass)
	    		{
	    			cout<<"Wrong Password! Enter your password again: "<<endl;
	    			cin>>password;
	    			cout<<endl;
				}
				int achoice;
				do
				{
					cout<<endl<<endl<<"---------------------------Admin's Menu.-----------------------------------"<<endl<<endl;
					cout<<"0) Menu."<<endl;
					cout<<"1) Change Password."<<endl;
					cout<<"2) Logout."<<endl;
					cin>>achoice;
					switch(achoice)
					{
					    case 0:
                                int mcho;
								do
								{

							    cout<<endl<<endl<<"--------------------------------------Menu---------------------------------------"<<endl;
								cout<<"1) Add Product"<<endl;
								cout<<"2) Remove Product"<<endl;
								cout<<"3) Current Products"<<endl;
								cout<<"4) Go Back"<<endl;
								cin>>mcho;


								if(mcho==1)
								{
									products[dcount].AddDish();
									dcount++;
									ofstream out("Dcount.txt");
									out<<dcount;
								}
								else
								{
									if(mcho==2)
									{
										for(int i=0; i<dcount; i++)
										{
											cout<<"Product "<<i+1<<endl;
											products[i].display();
										}
										int del;
										cout<<"Select the dish to remove\n";
										cin>>del;
										products[--del].RemoveDish();
										for(int i=del; i<dcount; i++)
										{
											Dish temp;
											temp=products[i];
											products[i]=products[i+1];
											products[i+1]=temp;
										}
										dcount--;
										ofstream out("Dcount.txt");
										out<<dcount;
									}
									else
									{
										if(mcho==3)
										{
											cout<<endl<<endl<<"---------------------Current Products!----------------------"<<endl;
											for(int i=0; i<dcount; i++)
											{
												cout<<"Product "<<i+1<<":"<<endl;
												products[i].display();
											}
										}
									}
								}

							    }while(mcho!=4);
							    break;
						case 1:{

							string pass;

							cout<<"Enter NEW Password:\n";
							cin>>pass;

							ofstream out("APassword.txt");
							out<<pass;

							cout<<"NEW Password is '"<<pass<<"'.\n";

							break;

							}
					}

					if(achoice==5)
					{
						cout<<endl<<endl;
					}

				}while(achoice!=2);

				break;
			}
			case 2:{

				string pass;

				ifstream in("MPassword.txt");
				in>>pass;

	    		cout<<"Enter your password:"<<endl;
	    		cin>>password;

	    		while(password!=pass)
	    		{
	    			cout<<"Wrong password! Enter your password again: "<<endl;
	    			cin>>password;
				}

				cout<<"-------------------------Welcome Mr. Customer-----------------------------"<<endl;
				int ch;

				do
				{

					cout<<"0) Menu."<<endl;
					cout<<"1) Food Order."<<endl;
					cout<<"2) Change Password."<<endl;
					cout<<"3) Logout."<<endl;
					cin>>ch;

					switch(ch)
					{
						case 1:
						    {

						    for(int i=0; i<dcount; i++)
											{
												cout<<"Product "<<i+1<<":"<<endl;
												products[i].display();
											}


						    int orf,n,total_price;
						    cout<<"Enter the product name to order:";
						    cin>>orf;
						    cout<<"Enter the Number of meal:";
						    cin>>n;
						    total_price = 150*n;
						    cout<<"Total Price:"<<total_price<<endl;
						    break;
						    }


							case 2:{

								string pass;

								cout<<"Enter NEW Password:\n";
								cin>>pass;

								ofstream out("MPassword.txt");
								out<<pass;

								cout<<"NEW Password is '"<<pass<<"'.\n\n";

								break;

								}

					}



				}while(ch!=3);
				break;
			}

		}
	}while(choice!=3);
}
