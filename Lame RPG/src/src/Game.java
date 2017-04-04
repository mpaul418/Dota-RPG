package src;

import java.util.ArrayList;
import java.util.Random;

import classes.AntiMage;
import classes.CentaurConqueror;
import classes.CentaurCourser;
import classes.Characters;
import classes.FellSpirit;
import classes.Hellbear;
import classes.HellbearSmasher;
import classes.HillTrollPriest;
import classes.Invoker;
import classes.Kobold;
import classes.MeleeCreep;
import classes.Monster;
import classes.PhantomAssassin;
import classes.Player;
import classes.RangedCreep;
import classes.Roshan;
import classes.SatyrBanisher;
import classes.SatyrMindstealer;
import classes.SatyrTormenter;
import classes.Sven;
import classes.Treant;
import classes.VhoulAssassin;
import spells.ActiveSpell;
import spells.PassiveSpell;
import spells.Spell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game
{
	private static String temp_name = "Player";
	private static int player_hero;
	public  static Player player;
	public  static int enemies_killed = 0;
	public  static int map[][] = new int[5][5];
	public  static int current_row = 0;
	public  static int current_column = 0;
	private static int turn_number = 0;
	public  static Random rand = new Random();
	private static ArrayList<Integer> options = new ArrayList<Integer>();
	public  static ArrayList<Monster> monsters = new ArrayList<Monster>();
	public  static ArrayList<Characters> characters = new ArrayList<Characters>();

	public  static boolean game_over = false;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args)
	{
		setName();
		setHero();
		makeDungeon();
		
		while(!game_over)
		{
			if(player.isAlive())
			{
				chooseRoom();
			
				if(map[current_row][current_column] > 0)
					battle();	
				else
					System.out.println("This room is empty.");
			}
		}
		
		System.out.println("Game over! Maybe next time :("
					   + "\nPlayer Stats:"
					   + "\n\tTotal damage dealt: " + player.getDamageDealt()
					   + "\n\tEnemies killed: " + enemies_killed);
	}
	
	private static void setHero()
	{
		System.out.println("What hero do you want to be?");
		System.out.println("1- Anti-Mage: A balanced melee fighter who specializes against spellcasters."
						+"\n2- Sven: A durable melee fighter who has solid physical defense and attack."
						+"\n3- Phantom Assassin: A glass cannon whose physical attack is very high, but at the expense of poor health."
						+"\n4- Invoker: A mage who has both damaging and utility spells.");

		player_hero = getNumberFrom(1, 4);
		
		switch(player_hero)
		{
			case 1:
			{
				player = new AntiMage(temp_name);
				break;
			}
			case 2:
			{
				player = new Sven(temp_name);
				break;
			}
			case 3:
			{
				player = new PhantomAssassin(temp_name);
				break;
			}
			case 4:
			{
				player = new Invoker(temp_name);
				break;
			}
		}
	}
	
	private static void setName()
	{
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
		
		if(player != null)
			player.setName(temp_name);
	}
	
	private static void makeDungeon()
	{
		for(int r = 0; r < map.length; r++)
		{
			for(int c = 0; c < map[0].length; c++)
			{
				if(r != 0 || c !=0) // if the room is not the starting room
					map[r][c] = rand.nextInt(2) + 2;  // each room has 2 to 3 monsters in it
				else
					map[r][c] = 0;
			}
		}
		
		int boss_row = rand.nextInt(5); // a random row
		int boss_column; // = rand.nextInt(3) + 2; // a random column from 3 to 5
		boss_column = rand.nextInt(1 + boss_row) + (4 - boss_row); // view paint diagram to see possible rooms
		
		map[boss_row][boss_column] = 4; // the 4 signals the boss battle
		
		//TODO remove this below- it is just for testing
		map[0][1] = 4;
	}
	
	private static void chooseRoom()
	{
		boolean working = true;
		int tempint = -1;
		int choice = -1;

		displayMap();
		
		System.out.println("Which direction would you like to go?");
		do
		{
			showRoomChoices(options);
			do
			{
				if(!working)
					showRoomChoices(options);
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
				catch(NumberFormatException e)
				{
					working = false;
					System.out.println("Exeception caught! Please input correctly.");
				}
			}while(!working);
			if(!options.contains((Integer) tempint))
				System.out.println("You can't go that way!");
		}while(!options.contains((Integer) tempint));
		choice = tempint;

		if(choice == 1)
			current_column += 1;			// go right
		else if(choice == 2)
			current_row += 1;		// go down
		else if(choice == 3)
			current_column -= 1;			// go left
		else if(choice == 4)
			current_row -= 1;		// go up
	}
	
	private static void displayMap() // creates a picture of the map
	{
		for(int row = 0; row < 5; row++)
		{
			System.out.print("\n\t");
			
			for(int column = 0; column < 5; column++)
			{
				if(current_row == row && current_column == column)
					System.out.print("[X]\t");
				else if(map[row][column] == 0)
					System.out.print("[C]\t");
				else if(map[row][column] == 4) // the boss room
					System.out.print("[B]\t");
				else
					System.out.print("[ ]\t");
			}
			System.out.println();
		}
		System.out.println("\nKey:\n\tX: Your Position\n\tC: Cleared Room\n\tB: Boss Room\n");
	}

	private static void showRoomChoices(ArrayList<Integer> room_options)
	{
		for(int i = room_options.size(); i > 0; i--)
			room_options.remove(0);
		
		room_options.add((Integer) 1);
		room_options.add((Integer) 2);
		room_options.add((Integer) 3);
		room_options.add((Integer) 4);
		
		if(current_column == 0)					// when you cannot go left
			room_options.remove((Integer) 3);
		else if(current_column == 4) 			// when you cannot go right
			room_options.remove((Integer) 1);
		if(current_row == 0) 				// when you cannot go up
			room_options.remove((Integer) 4);
		else if(current_row == 4) 			// when you cannot go down
				room_options.remove((Integer) 2);
		
		if(room_options.contains(1))
			System.out.println("1: Go right.");
		if(room_options.contains(2))
			System.out.println("2: Go down.");
		if(room_options.contains(3))
			System.out.println("3: Go left.");
		if(room_options.contains(4))
			System.out.println("4: Go up.");
	}
	
	private static void battle()
	{
		turn_number = 0;
		
		characters.add(player);
	
		addMonsters();	// spawns monsters- if level > 1, spawns one monster of player's level and the rest are random monsters from lower levels

		System.out.println();
		
		while(!battleOver())
		{
			turn_number++;
			System.out.println("------------------- Turn " + turn_number + " -------------------\n");
			
			player.reduceCooldowns();
			for(Monster m : monsters)
				m.reduceCooldowns();
			
			player.refreshDebuffs();
			for(Monster m : monsters)
				m.refreshDebuffs();
			
			player.takeTurn();
			
			checkForDeaths();
			
			if(!battleOver())
				for(int i = 0; i < monsters.size(); i++)
				{
					monsters.get(i).takeTurn();
				}
			
			checkForDeaths();	
		}
		
		System.out.println("------------------- Battle Over -------------------\n");

		while(characters.size() > 0)	// removes all characters from this array at the end of the battle
			characters.remove(0);
		
		if(player.isAlive())			// if the player wins the battle, player is restored
			player.restoreToFull();
	}
	
	private static void addMonsters()
	{
		if(map[current_row][current_column] < 4)
		{
			for(int i = 0; i < map[current_row][current_column]; i++)
			{
				int temp = 0;
				
				if(i == 0)
					temp = pickMonsterSpawnedOfLevel(player.getLevel()); //spawns monster of current level
				else
				{
					temp = pickMonsterSpawnedUpTo(player.getLevel());
				}
								
				createMonster(temp);
					
				System.out.println(monsters.get(monsters.size() - 1) + " entered the fray.");
			}
		}
		else // if the room number is 4 (if it is the boss battle)
		{
			createMonster(15); // adds Roshan
			
			System.out.println("Roshan looms over you... Prepare for your final battle!");
		}
	}
	
	private static int pickMonsterSpawnedOfLevel(int lvl)
	{
		switch(lvl)
		{
			case 1:
			{
				return rand.nextInt(7) + 1;	 	// there are 7 level one creeps, picks a random one to create
			}
			case 2:
			{
				return rand.nextInt(4) + 8;		// picks a random level 2 creep
			}
			case 3:
			{
				return rand.nextInt(3) + 12;	// picks a random level 3 creep
			}
			default:
			{
				return rand.nextInt(14) + 1;	// picks any random level monster
			}
		}
	}
	
	private static int pickMonsterSpawnedUpTo(int lvl)
	{
		switch(lvl)
		{
			case 1:
			{
				return rand.nextInt(7) + 1;	 	// still makes a level one creep
			}
			case 2:
			{
				return rand.nextInt(7) + 1;		// picks a random level 1 creep
			}
			case 3:
			{
				return rand.nextInt(11) + 1;	// picks up to a random level 2 creep
			}
			default:
			{
				return rand.nextInt(14) + 1; 	// picks any random level monster
			}
		}
	
	}
	
	private static void createMonster(int monster)
	{
		switch(monster) // creates a monster depending on the random int
		{
			case 1:
			{
				monsters.add(new Kobold());
				break;
			}
			case 2:
			{
				monsters.add(new Treant());
				break;
			}
			case 3:
			{
				monsters.add(new FellSpirit());
				break;
			}
			case 4:
			{
				monsters.add(new VhoulAssassin());
				break;
			}
			case 5:
			{
				monsters.add(new SatyrBanisher());
				break;
			}
			case 6:
			{
				monsters.add(new MeleeCreep());
				break;
			}
			case 7:
			{
				monsters.add(new RangedCreep());
				break;
			}
			case 8:
			{
				monsters.add(new HillTrollPriest());
				break;
			}
			case 9:
			{
				monsters.add(new CentaurCourser());
				break;
			}
			case 10:
			{
				monsters.add(new Hellbear());
				break;
			}
			case 11:
			{
				monsters.add(new SatyrMindstealer());
				break;
			}
			case 12:
			{
				monsters.add(new HellbearSmasher());
				break;
			}
			case 13:
			{
				monsters.add(new SatyrTormenter());
				break;
			}
			case 14:
			{
				monsters.add(new CentaurConqueror());
				break;
			}
			case 15:
			{
				monsters.add(new Roshan());
				break;
			}
		}
	}

	private static void checkForDeaths()
	{
		ArrayList<Characters> removed_characters = new ArrayList<Characters>();
		
		for(Characters c : characters)
			if(!c.isAlive())
				removed_characters.add(c);
		for(Characters c : removed_characters)
			c.die();
	}

	private static boolean battleOver()
	{
		if(!player.isAlive() || monsters.size() == 0) // this used to call allMonstersDead(), i changed it
			return true;
		else
			return false;
	}

	public static void listSpells(Characters c)
	{
		int spells = 0;

		for(Spell s : c.spellbook)
		{
				spells++;

				System.out.print(spells + ": " + s.NAME + "(Level " + s.spell_level + ")- " + s.DESCRIPTION + ".");

				if(s instanceof ActiveSpell)
				{
					if(s.isCastable())
						System.out.print(" Mana Cost: " + s.MANA_COST + ". Cooldown: " + s.max_cooldown + " turns.");

					else
					{
						if(s.onCooldown())
							System.out.print(" On cooldown for " + s.getCurrentCooldown() + " more turns. (Costs " + s.MANA_COST + " mana.)");
						else
							System.out.print(" Requires " + (s.MANA_COST - player.getMana()) + " more mana.");
					}
				}
				else if (s instanceof PassiveSpell)
				{
					System.out.print(" Passive effect.");
				}
				System.out.println();
		}
	}
	public static void displayHPandMana()
	{
		System.out.println(player + "'s HP: " + player.getHP() + "/" + player.getDefaultHP()
		+  "   Mana: " + player.getMana() + "/" + player.getDefaultMana());
		for(Monster m : monsters)
			System.out.println(m + "'s HP: " + m.getHP() + "/" + m.getDefaultHP()
			+  "   Mana: " + m.getMana() + "/" + m.getDefaultMana());
		System.out.println();
	}

	public static int getNumberFrom(int start, int end)
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
		{
			greater = start;
			lesser = start;
		}
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
			catch(NumberFormatException e)
			{
				working = false;
				System.out.println("Exception caught! Please input correctly.");
			}
		}while(!working);

		return tempint;
	}

	public static int getTurnNumber()
	{
		return turn_number;
	}
}