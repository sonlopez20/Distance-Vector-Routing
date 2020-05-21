// Sonia Lopez
// 100153296

package distancevector.routing;

public class Node{
	private int id;
	private int[][] routingTable;
	private int[][] distanceVectorTable;
	public static final int NUM_NODES = 6;
	
	public void node(int id, int[][] nodeInfo)
	{
		int i;																//counter variable
		int j;																//counter variable
		int k;																//counter variable
		int l;																//counter variable
		this.id = id;		
		this.routingTable = new int[NUM_NODES][NUM_NODES];					//initial state
		this.distanceVectorTable = new int[NUM_NODES][NUM_NODES];			//change in table
		
		//initial graphs
		for(i = 0; i < NUM_NODES; i++)
		{
			for(j = 0; j < NUM_NODES; j++)
			{
				if(i == j)
				{
					this.routingTable[i][j] = 0;							//cost of 0 for self shelve
				}
				else
				{
					this.routingTable[i][j] = 16;							//reprensents infinity
				}
			}
		}
		
		for(i = 0; i < nodeInfo.length; i++)								//filling in input file data
		{
			for(j = 0; j < nodeInfo[0].length-1; j++)
			{
				if(id == nodeInfo[i][j])
				{
					k = id - 1;
					
					if(j == 0)
					{
						l = nodeInfo[i][1] - 1;
						this.routingTable[k][l] = nodeInfo[i][2];			//cost is store in index 2 
						this.routingTable[l][k] = nodeInfo[i][2];			//cost is store in index 2 
					}
					if(j == 1)
					{
						l = nodeInfo[i][0] - 1;
						this.routingTable[k][l] = nodeInfo[i][2];			//cost is store in index 2 
						this.routingTable[l][k] = nodeInfo[i][2];			//cost is store in index 2 
					}
				}
			}
		}
		this.distanceVectorTable = this.routingTable;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void printRTable()
	{
		int i;																//counter variable
		int j;																//counter variable
        System.out.println("Routing Table:");
        System.out.println("    1  2  3  4  5  6");
        System.out.println("  ---------------------");
		
        
        for( i = 0; i < this.routingTable.length; i++ )						//loop through array
		{
            System.out.print( i+1 + "| ");
            for( j = 0; j < this.routingTable[i].length; j++ )
			{
                if( this.routingTable[i][j] > 9 )
				{
                    System.out.print(this.routingTable[i][j] + " ");
                }
                else
				{
                    System.out.print(" " + this.routingTable[i][j] + " ");
                }
                
            }
            System.out.println(" |");
        }
        System.out.println("  --------------------");
	}
	
	public void printDTable()										
	{
        int i;																//counter variable
		int j;																//counter variable
        System.out.println("Distance Vector Table:");
        System.out.println("    1  2  3  4  5  6");
        System.out.println("  ---------------------");

        for( i = 0; i < this.distanceVectorTable.length; i++ )				//loop through array
		{
            System.out.print( i+1 + "| ");
            for( j = 0; j < this.distanceVectorTable[i].length; j++ )
			{
                if( this.distanceVectorTable[i][j] > 9 )
				{
                    System.out.print(this.distanceVectorTable[i][j] + " ");
                }
                else
				{
                    System.out.print(" " + this.distanceVectorTable[i][j] + " ");
                }
                
            }
            System.out.println(" |");
        }
        System.out.println("  --------------------");
	}
	
	public int[][] getTable()
	{
		return this.distanceVectorTable;
	}
	
	public boolean updateTable(int[][] neighbor)
	{
		int i;																//counter variable
		int j;																//counter variable
		int update = 0;														//counter variable
		
        for( i = 0; i < this.distanceVectorTable.length; i++ )				//look for better connection
		{
            for( j = 0; j < this.distanceVectorTable[i].length; j++ )
			{	
                if( this.distanceVectorTable[i][j] > neighbor[i][j] && neighbor[i][j] < 16 )
				{
                    this.distanceVectorTable[i][j] = neighbor[i][j];
                    update++;												//keeps track if change occured
                }
            }
        }

        if( update > 0 )
		{		
            return true;													//let main know, so stablity can be checked
        }
        else
		{
            return false;
        }
	}
	
	public int[] getNeighbors()
	{
		int i;																//counter variable
		int[] table;
		table = new int[NUM_NODES];
		
		for(i = 0; i < table.length; i++)
		{
			table[i] = 0;													//initialize everything to 0
		}
		
		for(i = 0; i < this.distanceVectorTable[0].length; i++)
		{
			if(this.distanceVectorTable[this.id-1][i] < 16)					//if connected than cost will not be 16
			{
				table[i] = this.distanceVectorTable[this.id-1][i];			//will return list of routers we can get info from
			}
		}
		return table;
	}
}