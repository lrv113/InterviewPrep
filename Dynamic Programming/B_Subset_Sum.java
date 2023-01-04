import java.util.Arrays;
// import Utility.Utility; -- I use it for simple functions like printing the arrays
// https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
public class B_Subset_Sum {
	static boolean[][] dp = new boolean [1000][1000];
	public static void main(String[] args) {
		// input ?
		int[] arr = {1, 2, 3};
		int sum = 5;
		// o/p : is there any subset of arr with sum of elements as sum
		// try to analyze the problem, kuch similarity lg rhi h ?? with Knapsack
		// arr ek hi h, choice h and target/capacity/sum bhi h, kuch resemblence to h
		
		// Recursion : Pick and Don't pick approach
		boolean isSubSetPresent = isSubsetPresent(arr, sum, arr.length);
		System.out.println(isSubSetPresent);
		
		// Memoize : Top Down
		// idea : store the computation of Recursive call, kaise pta Overlapping subproblems, small eg pr Recursion execute see.
		// initialize dp array as per constraint, size of DP can be easily derived from Recursion, changing params see
		// below commented code is not needed if we need to initialize our array with false. as by default during creation we have false values only.
		// for true we need to use code of below type.
		/*
		  for(boolean[] a: dp) {
		 
			Arrays.fill(a, false);
		}*/
		
		// call the function
		System.out.println(sum+"  "+arr.length);
		System.out.println(isSubSetPresentMemoized(arr, sum, arr.length));
// 		Utility.print2dArrayWithDimensionsBoolean(dp, arr.length+1, sum+1);
		
		// Best version : Bottom Up (Tabulation)
		for(boolean[] a: dp) { 
			Arrays.fill(a, false);
		}
		
		for(int i=1;i<(sum+1);i++) {
			dp[0][i] = false;
		}
		
		for(int i=0;i<(arr.length+1);i++) {
			dp[i][0] = true;
		}
		
		for(int i=1;i< (arr.length+1); i++) {
			for(int j=1;j<(sum+1);j++) {
				if(arr[i-1] <= j) {
					dp[i][j] = (dp[i-1][j-arr[i-1]] || dp[i-1][j]);
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
// 		Utility.print2dArrayWithDimensionsBoolean(dp, arr.length+1, sum+1);
		
		System.out.println(dp[arr.length][sum]);
	}
	
	public static boolean isSubsetPresent(int[] arr, int sum, int n) {
		// BASE CONDITION :  
		// very easy have a look kaun se params change kroge 
		// unka lowest valid value k liye, for which we know the answer
		// e.g. here size of arr : n and sum are of interest as they will change 
		// smallest valus n and sum can be 0 and 0
		if(n==0 && sum >0) return false;
		if(sum == 0) return true;
	
		// CHOICE DIAGRAM : Recursion
		if(arr[n-1] <= sum) {
			return (isSubsetPresent(arr, sum-arr[n-1], n-1) || isSubsetPresent(arr, sum, n-1)); 
		}else {
			return isSubsetPresent(arr, sum, n-1);
		}
	}
	
	public static boolean isSubSetPresentMemoized(int [] arr, int sum, int n) {
		
		// very interesting execution agr dekho
		// Try with example of {1,2,3}, n=3 and sum = 5
		// Try to see what function calls are happening and what not.
		// || lge hone ki wjh se shortcircuit hoga
		
		if(n==0 && sum > 0) return false;
		if(sum == 0) return true;
		
		if(dp[n][sum]!= false) {
			return dp[n][sum];
		}
		// see if current element (n-1) capable of inclusion
		if(arr[n-1] <= sum) { // bhut dhyan se see n-1
			 System.out.println("function call"+n+"  "+sum);
			 dp[n][sum] = (isSubSetPresentMemoized(arr, sum-arr[n-1], n-1) || isSubSetPresentMemoized(arr, sum, n-1));
			 //System.out.println("for imput "+n+" "+sum+" ===="+dp[n][sum]); 
			 return dp[n][sum];
		}else {
			return dp[n][sum] = isSubSetPresentMemoized(arr, sum, n-1);
		}
	}
}
