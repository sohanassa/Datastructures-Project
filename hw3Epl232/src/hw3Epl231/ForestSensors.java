package hw3Epl231;

import java.io.File;
import java.util.Scanner;

public class ForestSensors {

	public static void main(String[] args) {

		// READNG FROM USER
		while (true) {
			int option = getOption();
			switch (option) {
			case 1:

			}
		}
	}

	private static int getOption() {
		Scanner user = new Scanner(System.in);
		int option = 0;
		// READNG FROM USER
		System.out.println("Please select an option:");
		System.out.println("1 - Calculate minimum spanning tree!");
		System.out.println("2 - Print the minimum spanning tree!");
		System.out.println("3 - Add new vertex!");
		System.out.println("4 - Remove a vertex!");
		System.out.println("5 - Inform fire station about forest temperature!");
		System.out.println("6 - Terminate and save back to file!");
		System.out.print("-> ");
		option = user.nextInt();

		
		while (option < 1 || option > 6) {
			System.out.println("Wrong input, try again!");
			System.out.print("-> ");
			option = user.nextInt();
		}
		user.close();
		return option;
	}

	private static void createMST() {
		// calls function
	}

	private static Graph readGraphFromFile(String fileName, int d) {
		Graph g = new Graph();
		File fileObj = new File(fileName);
		try {
			Scanner reader = new Scanner(fileObj);
			while (reader.hasNextLine()) {
	            String id = reader.next();
	            int x = reader.nextInt();
	            int y =reader.nextInt();
	            int temp = reader.nextInt();
	            GraphNode node = new GraphNode(x,y,id,id.charAt(0) == '0',temp);
	            g.add(node, d);
	        }
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}

















