// Problem Statement : Given an array and a sum, tell how many subsets have given sum
// Think get all the subsets, filter whose sum is 10 -- bruteforce

public class D_Count_Of_Subset_Sum {
	public static void main(String[] args) {
		// Think more
		// A problem we did was, tell if any subset exist with given sum
		// now we need to count all the subsets.
		// Subset sum code is already to explore all possible subsets.
		// let's write the subset sum code
		// input 
		int[] arr = {2,3,5,6,8,10};
		int n=arr.length;
		int sum = 10;
		// for our problem o/p will be an int number.
		int[][] dp = new int[n+1][sum+1];
		// initialisation ---- smallest possible input for n and sum can be 0
		// Think first row and col, bas pehle boolean ab kitne set mil skte
		// all rows ka first col : one set i.e. empty set similarly 1st row socho
		for(int i=0;i<(n+1);i++) {
			dp[i][0] = 1;
		}
		for(int i=1;i<(sum+1);i++) {
			dp[0][i] = 0;
		}
		// **************************
		
		// try to write simple code for subset sum.
		
		for(int i=1;i<(n+1);i++) {
			for(int j=1;j< (sum+1);j++) {
				if(arr[i-1] <= j) {
					// this we used to write jha true humara kaam ho gya, dp[i-1][j-arr[i-1]] || dp[i-1][j];
					// dhyan se socho, bas chota sa change
					dp[i][j] = dp[i-1][j-arr[i-1]] + dp[i-1][j];
					
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[n][sum]);
		
	}
}
