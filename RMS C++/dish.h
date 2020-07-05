#include<iostream>
#include<cstring>
#ifndef DISH_H
#define DISH_H
#include"stock.h"

class Dish
{
	protected:

		float rate;
		string dish;
		int productcount;

	public:

		Dish(string,float);
		void display();

		void AddDish();
		void RemoveDish();
		float getrate();

		Dish operator +(const Dish & rhs);


};

#endif
