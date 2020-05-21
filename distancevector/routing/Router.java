// Sonia Lopez
// 100153296

package distancevector.routing;

import java.util.*;
import java.io.*;

public class Router{
	
	public static final int NUM_NODES = 6;
	
	// this method will read in the text file
	public static void main(String[]args)
	{
		System.out.println("Sonia Lopez *** 1001523296 *** CSE 4344");
		System.out.println("Distance Vector Routing");
		
		int i;															//counter variable
		int j;															//counter variable
		int cycles = 0;													//count of cycles until it is stable
		String filename;												//will hold filename entered by user
		String choice;													//will hold whether user chose single step;
		Scanner scanner = new Scanner(System.in);         				// gets user input
		
		System.out.print("What file would you like to open: ");
		filename = scanner.nextLine();
		int [][] nodeInfo = read(filename);						// has the info for the links between the routers
		
		Node[] routers = new Node[NUM_NODES];							//router object
		boolean singleStep;												//true means user chose to do single step
		boolean stable = false;
		
		System.out.print("Would you like single step mode? (y/n): ");
		choice = scanner.nextLine();
		
		if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes"))
		{
			singleStep = true;
		}
		else
		{
			singleStep = false;
		}
		
		//Begin main part
		System.out.println("Initializing Routers");
		
		//create a list
		for (i = 0; i < routers.length; i++)
		{
			routers[i] = new Node();										//starting at 1
			routers[i].node(i+1,nodeInfo);
			System.out.println("Initial link state for router: "+(i+1));	//printing what is currently known
			routers[i].printRTable();			
		}
		
		//dif program direction depending on user input
		if(singleStep)
		{
			while(!stable)
			{
				System.out.println("Please press enter to continue.");
				choice = scanner.nextLine();
				stable = share(routers);
				for(i = 0; i < NUM_NODES; i++)
				{
					routers[i].printDTable();
				}
			}
			scanner.close();												//no longer needed will not be getting user input
			System.out.println("The system is now stable.");
			System.out.println("The nodes are not getting any new information.");
		}
		else
		{
			long startTime = System.nanoTime();
			while(!stable)
			{
				stable = share(routers);
				cycles++;
			}
			System.out.println("The system is now stable.");
			System.out.println("The nodes are not getting any new information.");
			long endTime = System.nanoTime();
			long duration = endTime - startTime;
			System.out.println("It took "+ cycles +"cycles and "+(duration/1000000)+" milliseconds to reach a stable staus.");
		}
	}
	
	public static boolean share(Node[] routers)
	{
		int i;																//counter variable
		int j;																//counter variable
		int[] link;															//contains list of how things link
		int update = 0;														//checking to see if things have been update
		boolean stable = true;												
		int[][] table;														//constains information of neighbors
		
		for(i = 0; i < NUM_NODES; i++)
		{
			link = routers[i].getNeighbors();
			
			for(j = 0; j < link.length; j++)
			{
				if(link[j] > 0)
				{
					System.out.println("Router "+(i+1)+" is getting its linked router tables.");
					table = routers[j].getTable();
					stable = routers[i].updateTable(table);
					
					if(stable)
					{
						update++;
					}
					System.out.println("Router "+(i+1)+" updated with router "+(j+1));
				}
			}
		}
		if(update == 0)
		{
			return true; //system updated and is now stable will return as true
		}
		else
		{
			return false; //otherwise
		}
	}
	
	public static int[][] read(String filename)
	{
		int i = 0;																//counter variable
		int j = 0;																//counter variable
		int[][] connectcosts;													//will store information from file
		connectcosts = new int[24][3];											//expected max numbers
		
		try
		{
			File file = new File(filename);
			FileReader fr = new FileReader (file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			while((line = br.readLine()) != null)								//read all lined in from file
			{
				String[] lines = line.split(" ");								//split using delimiter
				for(j = 0; j < 3; j++)
				{
					connectcosts[i][j] = Integer.parseInt(lines[j]);			//save as needed
				}
				i++;
			}
			fr.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return connectcosts;													//return 2D array of ints

	}
}