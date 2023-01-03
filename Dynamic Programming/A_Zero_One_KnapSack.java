import java.util.Arrays;

// Problem : https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
// awesome problem to start with
public class A_Zero_One_KnapSack {
	
	static int[][] dp = new int[1001][1001]; // 1001 from constraints bas ease to access isly static
	
	// input is : weight and value arrays and capacity of knapsack
	public static void main(String[] args) {
		
		int[] value = {1, 2, 3};
		int[] weight = {4, 5, 1};
		int capacity = 4;
		int n = 3;
		
		// Recursion
		int profit = knapsackRecursive (weight, value, capacity, n);	
		System.out.println(profit);
		
		// Memoize or Top Down.
		// isme kuch nhi krna hota h kuki, keval Redundant Recursive call ko bachana h (if any)
		// Array kaise bnaye mtlb size kaise le, see changing params in Recursive Code
		// for this problem we have n and capacity of knapsack
		// always take +1 size of array, i.e. dp [n+1] [capacity+1], arrays ka index 0 se start hota h
		// static bnaaoge to bhut asan hoga access krna, see constraints on n and capacity and max size ki array bana do
		// e.g. n <= 1000 and capacity <= 1000
		
		//*****STEP 1********// ------ make a dp array and initalise it with some garbage value for differentiating
		// Below code won't work as Arrays.fill work on 1D arrays
		// Arrays.fill(dp, -1); // it will help to decide a cell is precomputed or not.
		
		// Method 1
		/*
		for(int i=0;i<1001;i++) {
			for(int j=0; j<1001;j++) {
				dp[i][j] = -1;
			}
		}
		*/
		
		// Method 2
		for(int[] arr : dp) {
			Arrays.fill(arr, -1);
		}
		
		//*****STEP 2********// ----- see code 
		profit = knapsackMemoized(weight, value, capacity, n);
		System.out.println(profit);
		
		// Bottom up : Good in sense, no recursive call.
		// Recursion, easy to comprehend, prone to StackOverflow for large inputs so sometimes, we really need iterative code.
		// there comes alternative, Tabulation, an awesome concept, will eliminate all the Recursive call.
		// with help of Recursive code we will try to make this code.
		
		// Step 1 : initialize the dp array [this is different than memoization], see base condition of Recursion
		// n==0 and capacity ==0 i.e. first row and first column ko fill in krna h 
		for(int i=0;i<n;i++) {
			dp[i][0] = 0;
		}
		
		for(int i=0;i<capacity;i++) {
			dp[i][0] = 0;
		}
		
		// Step 2 : fill the rest of matrix  [Recursive code ko change krenge dhyan se dekna]
		/*
		if(weight[n-1] <= capacity) {
			return Math.max(value[n-1] + knapsackRecursive(weight, value, capacity-weight[n-1], n-1), 
					knapsackRecursive(weight, value, capacity, n-1));
		}
		return knapsackRecursive(weight, value, capacity, n-1);
		*/
		
		// See below beautiful code
		for(int i=1;i<n;i++) {
			for(int j=1;j<capacity;j++) {
				if(weight[i-1] <= j) {
					dp[i][j] = Math.max ( (value[i-1] + dp[i-1][j-weight[i-1]]) , dp[i-1][j]);
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		System.out.print(dp[n][capacity]);
		
	}
	
	public static int knapsackRecursive(int[] weight, int[] value, int capacity, int n) {
		if(n== 0 || capacity== 0) // no items or no left capacity of knapsack
			return 0;  // no further val/profit can be generated
		if(weight[n-1] <= capacity) {
			return Math.max(value[n-1] + knapsackRecursive(weight, value, capacity-weight[n-1], n-1), 
					knapsackRecursive(weight, value, capacity, n-1));
		}
		return knapsackRecursive(weight, value, capacity, n-1);
	}
	
	
	public static int knapsackMemoized(int[] weight, int[] value, int capacity, int n) {
		if(n== 0 || capacity== 0) // no items or no left capacity of knapsack
			return 0;  // no further val can be generated
		
		// piece added.
		if(dp[n][capacity]!=-1)
			return dp[n][capacity];
		
		if(weight[n-1] <= capacity) {
			return dp[n][capacity] = Math.max(value[n-1] + knapsackRecursive(weight, value, capacity-weight[n-1], n-1), 
					knapsackRecursive(weight, value, capacity, n-1));
		}
		return dp[n][capacity] = knapsackRecursive(weight, value, capacity, n-1);
	}
	
	
}
