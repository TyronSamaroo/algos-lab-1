// You have a heist getaway sack with a capacity, and n items in front of you
// with sizes and worths. Figure out the maximum value you could
// get with the items.

// You are encouraged to make helper functions!
// Tyron Samaroo

public class Robbery {

	// Using DP: Get the maximum value with capacity C and n items

	// Need this helper function
int n;

	public int maximizeRobWorthRecur(
		int capacity,
		int[] sizes,
		int[] worths,
		int n
	) {
		// fill in here, change the return
		/*
		* If my bag cant hold anything I have no value
		* If theres no items no matter size of my bag I will have no value
		* */
		//Base Case
			if(capacity == 0 || n == 0)
			{
				return 0;
			}
		// If an item cannot fit in my bag. Go to the next item that can fit
			if(sizes[n- 1] > capacity)
				return maximizeRobWorthRecur(capacity,sizes,worths, n-1);
		// If all the sizes less than capacity
		// If item can fit means below capacity that item will be added.
		// The remaining capacity will be updated and the next size will be checked.
		// It will find the max of the n-1 item with another item
			else return max(worths[n-1] + maximizeRobWorthRecur(capacity-sizes[n-1],sizes,worths, n-1),
				maximizeRobWorthRecur(capacity,sizes,worths, n-1));
		}

	static int max(int a, int b)
	{
		return (a > b)? a : b;
	}

	static int maximizeRobWorthBottomUp(
		int capacity,
		int[] sizes,
		int[] worths,
		int n
	) {
		int i,j;
		//Give correct size of table
		int[][] DPTable = new int[n+1][capacity+1];
		for ( i = 0; i <= n; i++)
		{
			for (j = 0; j <=capacity ; j++)
			{
				if( i == 0 || j == 0)
					DPTable[i][j] = 0;
				else if (sizes[i-1] <= j)
					DPTable[i][j] = max(worths[i-1] + DPTable[i-1][j-sizes[i-1]], DPTable[i-1][j]);
				else
					DPTable[i][j] = DPTable[i-1][j];
			}
		}
		// fill in here, change the return
		return DPTable[n][capacity];
	}

/**
* Bonus: figure out which items exactly
* Takes in a DP DPTable
* Returns an int array of the individual worths of the items you took
*/
	public int[] takeRobInventory(int[][] DPTable) {
		// fill in here, change the return
		return new int[DPTable.length];
	}

	public static void main(String[] args) {
		Robbery r = new Robbery();
		int bagCapacity = 40;
		int[] itemSizes = {2, 25, 6, 13, 1, 15, 8, 5, 17, 4};
		int[] itemWorths = {35, 120, 900, 344, 29, 64, 67, 95, 33, 10};
		int n = itemSizes.length;

		int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity, itemSizes, itemWorths,n);
		System.out.println("Max worth of the bag: " + maxWorthRecur);
		int maxWorthBottomUp = r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths,n);
		System.out.println("Max worth of the bag: " + maxWorthBottomUp);


		// Bonus: Fill in the helper method takeRobInventory that could help you
		//figure out which items go into the bag that make it max worth. Feel free
		//to change up the parameters and returned types + values of the helper
		// methods above.
		// int[] itemsPicked = r.takeRobInventory();
		// System.out.println("The items we take are worth:")
		// for (int i = 0; i < results.length; i++) {
		// 	System.out.print(results[i] + " ");
	}
}
