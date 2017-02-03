/* 
 * CountUseless.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 */
import java.io.*;
/*
 * This program is an O(n^3) algorithm that computes the shortest path
 * using Floyd Warshall's algorithm and counts the number of useless
 * edges.
 * 
 * @author	Vaibhavi Awghate	Section 03
 * @author	Chaitali Kamble		Section 03
 */
public class CountUseless {
	public static int count_Useless = 0; //Variable to count useless edges
	public static void main(String args[]) throws IOException {
		/*
		 * Execution starts from here	
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		int vertices = Integer.parseInt(input[0]);
		int edges = Integer.parseInt(input[1]);
		//Matrix to store the input
		float[][] matrix = new float[vertices][vertices];
		// Initialize the matrix
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (i == j) {
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = (float) Double.POSITIVE_INFINITY;
				}
			}
		}
		// Store the input
		for (int i = 0; i < edges; i++) {
			String edgeline[] = br.readLine().split(" ");
			int v1 = Integer.parseInt(edgeline[0]);
			int v2 = Integer.parseInt(edgeline[1]);
			float edge = Float.parseFloat(edgeline[2]);
			if (v1 == v2 && edge != 0) {
				count_Useless++;
			}
			matrix[v1][v2] = edge;
		}
		System.out.println(CountUselessEdges(matrix, vertices, edges));
	}

	private static int CountUselessEdges(float[][] matrix, int vertices, int edges) {
		/*
		 * This function returns the number of useless edges.
		 * @param	matrix[][], vertices (# of vertices), edges (# of edges)
		 * @return	count_Useless (Total number of useless edges)
		 */
		float[][][] S = new float[vertices][vertices][vertices + 1];
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				S[i][j][0] = matrix[i][j];
			}
		}
		int k = 1;
		for (k = 1; k < vertices + 1; k++) {
			for (int i = 0; i < vertices; i++) {
				for (int j = 0; j < vertices; j++) {
					if (i == j) {
						S[i][j][k] = 0;
					} else {
						S[i][j][k] = Math.min(S[i][j][k - 1], S[i][k - 1][k - 1] + S[k - 1][j][k - 1]);
					}
				}
			}
		}
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (i == j || matrix[i][j] == Double.POSITIVE_INFINITY) {
					continue;
				} else {
					if (S[i][j][k - 1] != matrix[i][j]) {
						count_Useless++;
					}

				}
			}
		}
		return(count_Useless);
	}
}
