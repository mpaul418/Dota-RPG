package src;

import java.util.ArrayList;
import java.util.Random;
//import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game 
{
	static String temp_name = "";
	static int player_class;
	//FIXMEstatic Scanner s = new Scanner(System.in);	
	static Player player;
	static int map[][] = new int[5][5];
	static int current_row = 0;
	static int current_column = 0;
	static Random rand = new Random();
	static ArrayList<Integer> options = new ArrayList<Integer>();
	static ArrayList<Monster> monsters = new ArrayList<Monster>();
	static boolean game_over = false;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args)
	{
		setClass();
		setName();
		makeDungeon();
		while(!game_over)
		{
			if(player.isAlive())
				chooseRoom();
			if(player.isAlive())
				battle();
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
	private static void setName()
	{
		String temp_name = "Player";
		boolean length_okay;
		boolean name_confirmed = false;
		int name_repick = -1;
		do
		{
			do
			{
				length_okay = false;
				try
				{
				System.out.println("What is your name?");
				temp_name = br.readLine();				
				if(temp_name.replaceAll("\\s", "").length() == 0)
					System.out.println("Length of zero. Please input correctly.");
				else
					length_okay = true;
				}
				catch(IOException e)
				{
					System.out.println("Exception caught!");
					length_okay = false;
				}
			}while(!length_okay);
			System.out.println("\nYour name is " + temp_name + ". Is that okay?");
			System.out.println("1: Finalize name."
						+ "   \n2: Re-enter name.");
			name_repick = getNumberFrom(1,2);
			if(name_repick == 1)
				name_confirmed = true;
		}while(!name_confirmed);
		
		player.setName(temp_name);
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
		boolean working = true;
		int tempint = -1;
		int choice = -1;
		
		System.out.println("Which direction would you like to go?");
		do
		{
			showRoomChoices();
			do
			{
				if(!working)
					showRoomChoices();
				working = true;
				try
				{
					tempint = Integer.parseInt(br.readLine());
				}
				catch(IOException e)
				{
					working = false;
					System.out.println("Exeception caught! Please input correctly.");
				}
			}while(!working);
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
	}
	private static void showRoomChoices()
	{
		options = new ArrayList<Integer>();
		options.add(1);
		options.add(2);
		options.add(3);
		options.add(4);
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
		for(int i = 0; i < map[current_row][current_column]; i++)
		{
			monsters.add(new Monster((rand.nextInt(player.getMaxHP() + 1) + 50), (rand.nextInt(71) + 40), (rand.nextInt(31) + 5), 
									 (rand.nextInt(36) + 5), (rand.nextInt(21) + 5), 1, rand.nextInt(26), "Monster " + (i+1)));
			System.out.println("What de heck! It's a(n) " + monsters.get(i).name + "!!");
		}
		do
		{
			refreshDebuffs();
			if(battleOver())
				takePlayerTurn();
			if(!battleOver())
				for(Monster m: monsters)
				{
					if(m.isAlive())
						takeMonsterTurn(m);
				}
		}while(!battleOver());
	}
	private static boolean battleOver()
	{
		if(!player.isAlive() || allMonstersDead())
			return true;
		else
			return false;
	}

	private static boolean allMonstersDead()
	{
		//TODO
		int enemies_dead = 0;
		for(Monster m: monsters)
		{
			if(!m.isAlive())
				enemies_dead++;
		}
		if(enemies_dead == monsters.size())
			return true;
		else
			return false;
	}

	private static void refreshDebuffs()
	{
		// TODO Add a Debuff/Buff class to contain all buffs with durations, effects, etc.
		player.defense = player.getMaxDefense(); // FIXME placeholder until buff class		
	}

	private static void takePlayerTurn()
	{
		int choice, target;
		displayHPandMana();
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
				System.out.println("\nWhat would you like to attack?");
				for(int i = 0; i < monsters.size(); i++)
				{
					System.out.println((i + 1) + ": " + monsters.get(i).getName()
							+"(" + monsters.get(i).getHP() + "/" + monsters.get(i).getMaxHP() + ")");
				}
				target = getNumberFrom(1, monsters.size()) - 1;
				player.attack(monsters.get(target));
				break;
			}
			case 2:
			{
				//TODO- select what spell/ability to cast, then target it if possible
				break;
			}
			case 3:
			{
				//TODO- select what item to use, the target it if possible
				break;
			}
			case 4:
			{
				player.hunkerDown();
				break;
			}
		}
	}
	
	private static void displayHPandMana()
	{
		System.out.println(player.getName() + "'s HP: " + player.getHP() + "/" + player.getMaxHP()
		+  "   Mana: " + player.getMana() + "/" + player.getMaxMana());
		for(Monster m : monsters)
			System.out.println(m.getName() + "'s HP: " + m.getHP() + "/" + m.getMaxHP()
			+  "   Mana: " + m.getMana() + "/" + m.getMaxMana());
		System.out.println();
	}

	private static void takeMonsterTurn(Monster m)
	{
		int temp = rand.nextInt(100) + 1;
		if(temp >= 30)
			m.attack(player);
		else
			m.hunkerDown();
	}
	
	private static int getNumberFrom(int start, int end)
	{
		boolean working = true;
		int tempint = -1;
		int greater, lesser;
		if(start > end)
		{
			greater = start;
			lesser = end;
		}
		else if(end > start)
		{
			greater = end;
			lesser = start;
		}
		else
			return start;
		do
		{
			working = true;
			
			try
			{
				do
				{
					tempint = Integer.parseInt(br.readLine());
					if(tempint < lesser || tempint > greater)
						System.out.println("Not a valid number. Please input correctly.");
				}while(tempint < lesser || tempint > greater);
			}
			catch(IOException e)
			{
				working = false;
				System.out.println("Exception caught! Please input correctly.");
			}
		}while(!working);
		
		return tempint;
	}	
}