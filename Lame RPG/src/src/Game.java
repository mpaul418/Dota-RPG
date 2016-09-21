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
	static ArrayList<Monster> monsters = new ArrayList<Monster>();
	static boolean game_over = false;
	
	public static void main(String[] args)
	{
		options.add(1);
		options.add(2);
		options.add(3);
		options.add(4);
		setName();
		setClass();
		makeDungeon();
		while(!game_over)
		{
			if(player.isAlive())
				chooseRoom();
			if(player.isAlive())
				battle();
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
		System.out.println("What class do you want to be?");
		System.out.println("1- Anti-Mage: A balanced fighter who specializes against spellcasters."
						+"\n2- Warrior: A melee fighter who has high physical defense and attack."
						+"\n3- Assassin: A glass cannon whose attack is very high, but at the expense of poor health."
						+"\n4- Wizard: A mage who has both damaging and utility spells.");
		
		player_class = getNumberFrom(1, 4); 
		
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
		showRoomChoices();
		do
		{
			while(!s.hasNextInt())
			{
				System.out.println("Not a valid choice. Please input correctly.");
				showRoomChoices();
				s.next();
			}
			tempint = s.nextInt();
			if(!options.contains(tempint))
			{
				System.out.println("You can't go that way!");
				showRoomChoices();
			}
		}while(!options.contains(tempint));
		choice = tempint;
		
		if(choice == 1)
			current_column += 1;
		else if(choice == 2)
			current_row += 1;
		else if(choice == 3)
			current_column -= 1;
		else if(choice == 4)
			current_row -= 1;
			
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
		else
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
			System.out.println("1: Go right.");
		if(options.contains(2))
			System.out.println("2: Go down.");
		if(options.contains(3))
			System.out.println("3: Go left.");
		if(options.contains(4))
			System.out.println("4: Go up.");
		//finish removing options for each possibility		
	}
	private static void battle()
	{
		boolean battle_over = false;
		for(int i = 0; i < map[current_row][current_column]; i++)
		{
			monsters.add(new Monster((rand.nextInt(201) + 100), (rand.nextInt(71) + 40), (rand.nextInt(31) + 5), 
									 (rand.nextInt(31) + 5), (rand.nextInt(21) + 5), 1, rand.nextInt(26), "Monster " + i));
			System.out.println("What de heck! It's a(n) " + monsters.get(i).name + "!!");
		}
		while(player.isAlive() || battle_over)
		{
			takePlayerTurn();
			if(!battle_over)
				for(Monster m: monsters)
					takeMonsterTurn(m);
		}
	}
	private static void takePlayerTurn()
	{
		int choice, target;
		System.out.println("What would you like to do?");
		System.out.println("1: Attack"
					   + "\n2: Abilities"
					   + "\n3: Use an Item"
					   + "\n4: Hunker Down");
		choice = getNumberFrom(1, 4);
		
		switch(choice)
		{
			case 1: 
			{
				System.out.println("What would you like to attack?");
				for(int i = 0; i < monsters.size(); i++)
				{
					System.out.println((i + 1) + ": " + monsters.get(i).getName()
							+"(" + monsters.get(i).getHP() + "/" + monsters.get(i).getMaxHP() + ")");
				}
				target = getNumberFrom(1, monsters.size());
				player.attack(monsters.get(target));
			}
			case 2:
			{
				//TODO
			}
			case 3:
			{
				//TODO
			}
			case 4:
			{
				//TODO
			}
		}
	}
	
	private static void takeMonsterTurn(Monster m)
	{
		// TODO Auto-generated method stub
	}
	
	private static int getNumberFrom(int start, int end)
	{
		int tempint = -1;
		do
		{
			while(!s.hasNextInt())
			{
				System.out.println("Not a valid input. Please choose correctly.");
				s.next();
			}
			tempint = s.nextInt();
			if(tempint < start || tempint > end)
				System.out.println("Not a valid number. Please input correctly.");
		}while(tempint < start || tempint > end);
		
		return tempint;
	}

	
}
