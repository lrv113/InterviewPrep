package Recursion;

import java.time.LocalDateTime;

// Very Important Note : Auxillary space (specially function call ) can be removed by smart compiler if TAIL RECURSION 
// So Tail Recursion is fast. --> function call is at last and wont return back to previous function.
// this file stores basic question to practice recursion.


public class Test {

	public static void main(String[] args) {
		// n and print log base 2 of n using recursion
		System.out.println(funToLog(32));
		
		// if we need to print log base 5
		// Base Condition dhyan se dekho
		System.out.println(funToLogBaseN(25, 5));
		
		// binary representation of a number using recursion
		funToConvertNtoBinary(4);
		
		System.out.println();
		
		// decimal to any base 
		funToConvertNtoAnyBase(4, 3);
		
		System.out.println();
		// print n to 1 using recursion
		
		
		// wanted to see the difference between speed of Tail and non tail recursion, kuch khas dikha nhi
		System.out.println(LocalDateTime. now());
		funcToPrintNtoOneTail(1000);
		System.out.println(LocalDateTime. now());
		funcToPrintNtoOneNotTail(1000);
		System.out.println(LocalDateTime. now());
		
		
	}
	
	public static void funcToPrintNtoOneNotTail(int n) {
		if(n == 0)
			return;
		// System.out.print(n);
		funcToPrintNtoOneNotTail(n-1);
		n+=2;
	}
	
	// TC = O(n)  ; T(n) = T(n-1) + O(1) back substitue 
	public static void funcToPrintNtoOneTail(int n) {
		if(n == 0)
			return;
		// System.out.print(n);
		funcToPrintNtoOneTail(n-1);
	}
	
	// TC = log n ; T (n) = T (n/base) + O (1) from master's theorem
	public static void funToConvertNtoAnyBase(int n, int base) {
		// see below beauty
		// base condition mandatory to remember
		if(n==0)
			return;
		
		// this is what we do
		funToConvertNtoBinary(n/base);   
		System.out.print(n%base);	
	}
	
	// TC = log n ; T (n) = T (n/2) + O (1) from master's theorem
	public static void funToConvertNtoBinary(int n) {
		// see below beauty
		// base condition mandatory to remember
		if(n==0)
			return;
		
		// this is what we do
		funToConvertNtoBinary(n/2);   
		System.out.print(n%2);	
	}
	
	public static int funToLogBaseN(int n, int base) {
		if(n < 5)
			return 0;
		return 1+funToLogBaseN(n/5, base);
	}
	
	
	public static int funToLog(int n) {
		if(n==1)
			return 0;
		return 1+funToLog(n/2);
	}	

}
