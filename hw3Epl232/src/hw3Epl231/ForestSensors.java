package hw3Epl231;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ForestSensors {

	public static void main(String[] args) {
		// int d = Integer.parseInt(args[1]);
		int d = 1000;
		Graph g = readGraphFromFile("input2.txt", d);
		ArrayList<Edge<Vertex>> mst = null;
		Graph minimumGraph = null;
		Scanner user = new Scanner(System.in);
		while (true) {
			int option = getOption(user);
			switch (option) {
			case 1:
				mst = g.prim();
				System.out.println("\nMimimum spanning tree calculated!");
				break;
			case 2:
				if (checkNullMST(mst))
					break;
				System.out.println("\nThe minimun spanning tree:");
				g.printMinimumSpanningTree(mst);
				break;
			case 3:
				Vertex newNode = readVertexFromUser();
				g.add(newNode, d);
				// need to be added to minimum tree
			case 4:
				String id = readIdFromUser();
				// delete node from graph and minimum tree lol
				break;

			case 5:
				if (checkNullMST(mst))
					break;
				String idForTemperature = readIdFromUser();
				if (fireStationCheck(g, idForTemperature) == false)
					break;
				minimumGraph = g.CreatGraphFromEdgesList(mst);
				int temp = minimumGraph.getMaxTempForVertex(idForTemperature);
				System.out.println("\nMaximum temperature that firestation with ID " + idForTemperature + " found is: "
						+ temp + "!");
				break;

			case 6:
				if (mst == null)
					saveGraphInFile(g, "input1.txt");
				else
					saveGraphInFile(minimumGraph, "input1.txt");
				System.out.println("\nGraph saved back in file!");
				System.out.println("Program Terminated!");
				return;
			}
		}
	}

	private static boolean checkNullMST(ArrayList<Edge<Vertex>> mst) {
		if (mst == null) {
			System.out.println("\nMust calculate the minimum spanning tree first!");
			return true;
		}
		return false;
	}

	private static int getOption(Scanner user) {

		int option = 0;
		// READNG FROM USER
		System.out.println("\nPlease select an option:");
		System.out.println("1 - Calculate minimum spanning tree!");
		System.out.println("2 - Print the minimum spanning tree (BFS)!");
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
				String str1 = reader.next();
				int x = Integer.parseInt(str1.substring(1, str1.length() - 1));
				String str2 = reader.next();
				int y = Integer.parseInt(str2.substring(0, str2.length() - 1));
				int temp = reader.nextInt();
				Vertex node = new Vertex(x, y, id, id.charAt(0) == '0', temp);
				g.add(node, d);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return g;
	}

	private static Vertex readVertexFromUser() {
		Scanner user = new Scanner(System.in);
		System.out.println("Enter x coordinate: ");
		int x = user.nextInt();
		System.out.println("Enter y coordinate: ");
		int y = user.nextInt();
		System.out.println("Enter ID:");
		String id = user.next();
		System.out.println("Enter temperature: ");
		int temp = user.nextInt();
		Vertex node = new Vertex(x, y, id, id.charAt(0) == '0', temp);
		user.close();
		return node;
	}

	private static String readIdFromUser() {
		Scanner user = new Scanner(System.in);
		System.out.print("Enter ID of Vertex: ");
		String id = user.next();
		return id;
	}

	private static void saveGraphInFile(Graph g, String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			String str = g.toString();
			writer.write(str);
			writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static boolean fireStationCheck(Graph g, String ID) {
		if (!ID.startsWith("0")) {
			System.out.println("\nThe given ID is not a fire station ID!");
			return false;
		}
		if (!g.vertexExists(ID)) {
			System.out.println("\nFire station does not exist in minimum spanning tree!");
			return false;
		}
		return true;

	}

}
