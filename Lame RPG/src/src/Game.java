package src;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game 
{
	static String temp_name = null;
	static int player_class;
	static Scanner s = new Scanner(System.in);	
	static Player player;
	static int map[][] = new int[5][5];
	static int current_row = 0;
	static int current_column = 0;
	static Random rand = new Random();
	static ArrayList<Integer> options = new ArrayList<Integer>();

	
	public static void main(String[] args)
	{
		options.add(1);
		options.add(2);
		options.add(3);
		options.add(4);
		setName();
		setClass();
		makeDungeon();
		while(player.isAlive())
		{
			chooseRoom();
		}
	}
	private static void setName()
	{
		try
		{
			System.out.println("What is your name?");
			if(s.hasNextLine())
			{	
				temp_name = s.nextLine();
				if(temp_name.length() == 0)
				{
					System.out.println("Not a name.");
					setName();
				}
			}
			else
			{
				System.out.println("Error.");
				setName();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error. Please input correctly.");
			setName();
		}
	}
	private static void setClass()
	{
		int tempint = -1;
		
		System.out.println("What class do you want to be?");
		System.out.println("1- Anti-Mage: A balanced fighter who specializes against spellcasters."
						+"\n2- Warrior: A melee fighter who has high physical defense and attack."
						+"\n3- Assassin: A glass cannon whose attack is very high, but at the expense of poor health."
						+"\n4- Wizard: A mage who has both damaging and utility spells.");
		try
		{
		do
		{
			while(!s.hasNextInt())
			{
				System.out.println("Not a valid input. Please choose correctly.");
				s.next();
			}
			if(!(tempint >= 1 && tempint <= 4))
			{
				System.out.println("Not a valid number. Please input correctly.");
				s.next();
			}
			tempint = s.nextInt();
		}while(!(tempint >= 1 && tempint <= 4));
		}
		catch(Exception e)
		{
			System.out.println("Exception caught!");
			setClass();
			return;
		}
		switch(tempint)
		{
			case 1: player_class = 1;
					break;
			case 2: player_class = 2;
					break;
			case 3: player_class = 3;
					break;
			case 4: player_class = 4;
					break;	
		}
		switch(player_class)
		{
			case 1: player = new AntiMage(temp_name);
			case 2: player = new Warrior(temp_name);
			case 3: player = new Assassin(temp_name);
			case 4: player = new Wizard(temp_name);
		}
	}
	private static void makeDungeon()
	{
		for(int r = 0; r < map.length; r++)
		{
			for(int c = 0; c < map[0].length; c++)
			{
				if(r != 0 || c !=0)
					map[r][c] = rand.nextInt(3) + 1;
				else
					map[r][c] = 0;
			}
		}
	}
	private static void chooseRoom()
	{
		int tempint = -1;
		int choice = -1;
		
		System.out.println("Which direction would you like to go?");
		
		do
		{
			while(!s.hasNextInt())
			{
				System.out.println("Not a valid choice. Please input correctly.");
				showRoomChoices();
				s.next();
			}
			tempint = s.nextInt();
		}while(!options.contains(tempint));
		choice = tempint;
			/*switch(tempint)
			{
				case 1: 
				{
					if(options.contains(2))
						choice = 2;
					break;
				}
				case 2: 
				{
					if(options.contains(2))
						choice = 2;
					break;
				}
				case 3: 
				{
					if(options.contains(3))
						choice = 3;
					break;
				}
				case 4: 
				{
					if(options.contains(4))
						choice = 4;
					break;
				}
			}
			if(choice == -1)
			{
				System.out.println("You cannot go in that direction.");
				chooseRoom();
			}*/
		if(choice == 1)
			current_column += 1;
		else if(choice == 2)
			current_row += 1;
		else if(choice == 3)
			current_column -= 1;
		else if(choice == 4)
			current_row -= 1;
			
	}
		
		/*if(current_row == 0 && current_column == 0)
		{
			//options.add(1);
			//options.add(2);
			System.out.println("1:\tGo right."
							+  "2:\tGo down.");
			choice = s.nextInt();
			while(!s.hasNextInt() || choice < 1 || choice > 2)
			{
				System.out.println("Input a correct option.");
				choice = s.nextInt();
			}
			
		}
		else//todo
		{
			if(current_row == 0)
			{
				//options.add(1);
				//options.add(2);
				//options.add(4);
				System.out.println("1:\tGo right."
								+  "2:\tGo down."
								+  "3:\tGo left");
				choice = s.nextInt();
				while(!s.hasNextInt() || choice < 1 || choice > 3)
				{
					System.out.println("Input a correct option.");
					choice = s.nextInt();
				}
				
			}
			else
			{
				if(current_column == 0)
				{
					//options.add(1);
					//options.add(2);
					System.out.println("1:\tGo right."
									+  "2:\tGo down."
									+  "4:\tGo");
					choice = s.nextInt();
					while(!s.hasNextInt() || choice < 1 || choice >2)
					{
						System.out.println("Input a correct option.");
						choice = s.nextInt();
					}
					
				}
				else
				{
					//if location is not on a wall
				}
			}
		}*/
	
	private static void showRoomChoices()
	{
		if(current_row == 0)
		{
			for(int i = 0; i < options.size(); i++)
				if(options.get(i) == 3 && options.contains(3))
					options.remove(i);
		}
		else
		{
			if(current_row == 4)
			{
				for(int i = 0; i < options.size(); i++)
					if(options.get(i) == 1 && options.contains(1))
						options.remove(i);
			}
		}
		if(current_column == 0)
		{
			for(int i = 0; i < options.size(); i++)
				if(options.get(i) == 4 && options.contains(4))
					options.remove(i);
		}
		else
		{
			if(current_column == 4)
			{
				for(int i = 0; i < options.size(); i++)
					if(options.get(i) == 2 && options.contains(2))
						options.remove(i);
			}
		}
		if(options.contains(1))
			System.out.println("1:\tGo right.");
		if(options.contains(2))
			System.out.println("2:\tGo down.");
		if(options.contains(3))
			System.out.println("3:\tGo left.");
		if(options.contains(4))
			System.out.println("4:\tGo up.");
		//finish removing options for each possibility		
	}
}
