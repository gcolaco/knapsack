import java.io.*;
import java.util.*;

class Knapsack {
	
	public static void main(String[] args) {

        	BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
			int w = 0, n = 0;

			//files reading
        	try {
        	String arr [] = new String [5];
        	arr = inReader.readLine().split(" "); 
        	n = Integer.parseInt(arr[0]);
        	w = Integer.parseInt(arr[1]);

        	int val[] = new int[n], 
        		wt[] = new int[n];

        	arr = new String[n];
           	 arr = inReader.readLine().split(" ");
            	for (int i = 0; i < arr.length; i++) 
            		wt[i] = Integer.parseInt(arr[i]);

            	arr = new String[w];
            	arr = inReader.readLine().split(" ");
            	for (int i = 0; i < arr.length; i++) 
            		val[i] = Integer.parseInt(arr[i]);

            	inReader.close();

            	System.out.println("Valor: "+knapsack(val, wt, w));

        	} catch (IOException e) {
            		System.err.println(e.getMessage());
        	}
	}

	/**
	* check value of itens, weight of itens, and return the max value
	* @param val[] 
	* @param wItem[] 
	* @param wKnapsack
	* @return 
	*/

	private static int knapsack(int val[], int wItem[], int wKnapsack){

		int n = wItem.length; 
		int [][] m = new int[n + 1][wKnapsack + 1]; 
		for (int item = 1; item <= n; item++) {
			for (int knapsackWeight = 1; knapsackWeight <= wKnapsack; knapsackWeight++ ) {
				if (wItem[item - 1] <= knapsackWeight)
					m [item][knapsackWeight] = Math.max(val[item - 1] + m[item - 1][ knapsackWeight-wItem[item - 1] ], 
										m[item - 1][knapsackWeight]);
				else
					m [item][knapsackWeight] = m[item - 1][knapsackWeight];
			}
		}

		ArrayList<Integer> selectedItems = new ArrayList<Integer>();
		int colWeightKnapsack = wKnapsack;
		for (int item = n; item >= 1; item--) {
			if (m [item][colWeightKnapsack] != m [item - 1][colWeightKnapsack]){
				selectedItems.add(item);
				colWeightKnapsack -= wItem[item - 1];
			}
		}

		System.out.println("Produtos escolhidos: "+selectedItems);
		return m[n][wKnapsack];
	}
}
