package com.seed.concurrent.synchronizers;

import java.util.concurrent.* ;

@SuppressWarnings (value="all")

public class DemoExchanger {

		public static void main(String[] args) 
	   {
			
		float DollarToRupeeRate = 62.48f ;
		
		Exchanger exchanger = new Exchanger();

		Dollar  dollars = new Dollar(300) ;
		
		MoneyExchanger American =  new MoneyExchanger(exchanger, dollars);

		MoneyExchanger Bank = new MoneyExchanger(exchanger, new Rupee(dollars , DollarToRupeeRate));

		new Thread(American , "American").start();
		new Thread(Bank , "Bank").start();

	   }

}
