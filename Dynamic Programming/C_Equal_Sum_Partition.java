// Problem : Tell an array can be divided into to subsets such that both subset have elements sum equal
public class C_Equal_Sum_Partition {
	
	public static boolean[][] dp = new boolean[1000][1000];
	
	public static void main(String[] args) {
		// Before we proceed let's do some maths.
		// sum(subset1) = sum(subset2) --- given
		// sum(subset1) + sum(subset2) = sum(arr) => sum(subset1) = sum(arr)/2
		
		// inferences from above statement, if sum(arr) = Odd, we cannot divide else we need to find if there exist any
		// subset with sum as sum(arr)/2
		
		// we with little change we reduced the problem to Subset sum only.
		
		// input 
		int[] arr= {15, 1};
		int n = arr.length;
		
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum+=arr[i];
		}
		
		if((sum & 1) == 1)
			System.out.println("Equal sum partition not possible");
		else {
		
		sum /= 2; // Very Very Important 
			
		// initialise the dp array
		for(int i=0;i<n+1;i++) {
			dp[i][0] = true;
		}
		for(int j=1;j<(sum+1);j++) {
			dp[0][j] = false;
		}
		
		// fill the rest of matrix
		for(int i=1;i<(n+1);i++) {
			for(int j=1;j<(sum+1);j++) {
				if(arr[i-1] <= j) {
					dp[i][j] = dp[i-1][j-arr[i-1]] || dp [i-1][j];
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		System.out.println(dp[arr.length][sum]);
		}
		
	}
}
