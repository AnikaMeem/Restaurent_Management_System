#include<iostream>
#include<cstring>
#include"dish.h"
#include"stock.h"

Dish::Dish(string n="", float inrate=0)
{
	ifstream in("Dish.txt");

	in>>dish;
	in>>rate;
	productcount=0;
}

void Dish::display()
{
	cout<<"Name: "<<dish<<endl;
	cout<<"Price: "<<rate<<endl;

}

void Dish::AddDish()
{
	cout<<"Enter Name Of The Dish: ";
	cin>>dish;

	cout<<"Enter Price Of The Dish: ";
	cin>>rate;
	productcount++;
	cout<<"---------Dish Added-------"<<endl;
}
void Dish::RemoveDish()
{
	dish="";
	rate=0;
	productcount--;
	cout<<"------Dish Removed-------"<<endl;
}
Dish Dish::operator +(const Dish & rhs)
{
	Dish temp;
	temp.rate=rate+rhs.rate;
	return temp;
}


