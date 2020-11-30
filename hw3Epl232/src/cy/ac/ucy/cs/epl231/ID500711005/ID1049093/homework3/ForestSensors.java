package cy.ac.ucy.cs.epl231.ID500711005.ID1049093.homework3;

/**
 * The Forest Sensor class is the driver class. In this class there is a menu that
 * the user can choose between 6 options. First option is to calculate minimum
 * spanning tree. Second option is to print the minimum spanning tree. The third 
 * option it to add new vertex in the minimum spanning tree. Fourth option is to
 * remove a vertex from the minimum spanning tree. Fifth option is to inform a
 * fire station about forest temperature. Sixth option is to terminate the
 * execution of the program and save it back to file. The program takes as input
 * from command line an integer that represents the maximum distance allowed
 * between nodes so they can be considered neighbors, and a string that represents
 * the filename that the graph is going to be read from and saved on at the end.
 *
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ForestSensors {

	/**
	 * Main function of the class. Takes as input from command line an integer that
	 * represents the maximum distance allowed between nodes so they can be
	 * considered neighbors, and a string that represents the filename that the
	 * graph is going to be read from and saved on at the end.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int d = Integer.parseInt(args[1]);
		Graph g = readGraphFromFile(args[0], d);
		ArrayList<Edge<Vertex>> mst = null;
		Graph minimumGraph = null;
		Scanner user = new Scanner(System.in);
		while (true) {
			int option = getOption(user);
			switch (option) {
			case 1:
				if (mst != null || minimumGraph != null) {
					System.out.println("\nMimimum spanning tree already calculated!");
					break;
				}
				mst = g.prim();
				minimumGraph = g.CreatGraphFromEdgesList(mst);
				System.out.println("\nMimimum spanning tree calculated!");
				break;
			case 2:
				if (checkNullMST(mst))
					break;
				System.out.println("\nThe minimun spanning tree:");
				g.printMinimumSpanningTreeEdges(mst);
				break;
			case 3:
				if (checkNullMST(mst))
					break;
				Vertex newNode = readVertexFromUser();
				minimumGraph.add(newNode, d);
				mst = minimumGraph.prim();
				minimumGraph = minimumGraph.CreatGraphFromEdgesList(mst);
				break;
			case 4:
				if (checkNullMST(mst))
					break;
				String id = readIdFromUser(user);
				minimumGraph.removeAndConnect(id, d);
				mst = minimumGraph.prim();
				minimumGraph = minimumGraph.CreatGraphFromEdgesList(mst);
				break;

			case 5:
				if (checkNullMST(mst))
					break;
				String idForTemperature = readIdFromUser(user);
				if (fireStationCheck(g, idForTemperature) == false)
					break;
				int temp = minimumGraph.getMaxTempForVertex(idForTemperature);
				System.out.println("\nMaximum temperature that firestation with ID " + idForTemperature + " found is: "
						+ temp + "!");
				break;

			case 6:
				if (mst == null)
					saveGraphInFile(g, args[0]);
				else
					saveGraphInFile(minimumGraph, args[0]);
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
		System.out.print("Enter x coordinate: ");
		int x = user.nextInt();
		System.out.print("Enter y coordinate: ");
		int y = user.nextInt();
		System.out.print("Enter ID:");
		String id = user.next();
		System.out.print("Enter temperature: ");
		int temp = user.nextInt();
		Vertex node = new Vertex(x, y, id, id.charAt(0) == '0', temp);
		return node;
	}

	private static String readIdFromUser(Scanner user) {
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
