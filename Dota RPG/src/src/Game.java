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
import classes.Illusion;
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
	public  static Player main_player;
	public  static int enemies_killed = 0;
	public  static int map[][] = new int[5][5];
	public  static int current_row = 0;
	public  static int current_column = 0;
	public  static int boss_row  = 0;
	public  static int boss_column = 0;
	private static int turn_number = 0;
	private static int continue_game = 2;
	public  static Random rand = new Random();
	private static ArrayList<Integer> options = new ArrayList<Integer>();
	public  static ArrayList<Characters> players = new ArrayList<Characters>();
	public  static ArrayList<Characters> monsters = new ArrayList<Characters>();
	public  static ArrayList<Characters> characters = new ArrayList<Characters>();
	public  static boolean game_over = false;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args)
	{
		System.out.println("$$$$$$$             $$$$$        $$$$$$$$$$$$$$$       $$$$$$");
		System.out.println("$$$$$$$$          $$$$$$$$$      $$$$$$$$$$$$$$$      $$$$$$$$");
		System.out.println("$$$   $$$        $$$     $$$           $$$           $$$    $$$");
		System.out.println("$$$    $$$      $$$       $$$          $$$          $$$      $$$");
		System.out.println("$$$     $$$    $$$         $$$         $$$         $$$        $$$");
		System.out.println("$$$      $$$  $$$           $$$        $$$        $$$          $$$");
		System.out.println("$$$      $$$  $$$           $$$        $$$        $$$$$$$$$$$$$$$$");
		System.out.println("$$$      $$$  $$$           $$$        $$$        $$$          $$$");
		System.out.println("$$$     $$$    $$$         $$$         $$$        $$$          $$$");
		System.out.println("$$$    $$$      $$$       $$$          $$$        $$$          $$$");
		System.out.println("$$$   $$$        $$$     $$$           $$$        $$$          $$$");
		System.out.println("$$$$$$$$          $$$$$$$$$            $$$        $$$          $$$");
		System.out.println("$$$$$$$             $$$$$              $$$        $$$          $$$"); //TODO add "RPG" to title
		System.out.println("       															  ");
		System.out.println("                                                                  ");
		System.out.println("          $$$$$$$$         $$$$$$$$$$$       $$$$$$$$$$$  ");
		System.out.println("        $$$$$$$$$$$$      $$$$$$$$$$$$$     $$$$$$$$$$$$$ ");
		System.out.println("        $$$        $$     $$$         $$   $$$           ");
		System.out.println("        $$$         $$    $$$         $$  $$$            ");
		System.out.println("        $$$        $$     $$$        $$   $$$            ");
		System.out.println("        $$$       $$      $$$       $$    $$$            ");
		System.out.println("        $$$$$$$$$$$       $$$$$$$$$$$     $$$            ");
		System.out.println("        $$$      $$       $$$             $$$       $$$$ ");
		System.out.println("        $$$        $$     $$$             $$$         $$$");
		System.out.println("        $$$         $$    $$$             $$$         $$$");
		System.out.println("        $$$          $$   $$$              $$$       $$$ ");
		System.out.println("        $$$          $$   $$$               $$$$$$$$$$$  ");
		System.out.println("        $$$          $$   $$$                 $$$$$$$$   ");
		System.out.println(																														);
		System.out.println("                 A Game by Michael McGillicuddy          ");
		System.out.println();

		do
		{
			setName();
			setHero();
			makeDungeon();

			while(!game_over)
			{
				if(main_player.isAlive() && roshanAlive())
				{
					chooseRoom();

					if(map[current_row][current_column] > 0)
						battle();
					else
						System.out.println("This room is empty.");
				}
			}

			if(!main_player.isAlive())
				loseGame();
			else if(!roshanAlive())
				winGame();

			System.out.println("Would you like to embark on your journey again?"
						   + "\n1: Yes"
						   + "\n2: No");
			continue_game = getNumberFrom(1,2);

			if(continue_game == 1)
			{
				game_over = false;
				current_row = 0;
				current_column = 0;
			}
		}while(!game_over);
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
				main_player = new AntiMage(temp_name);
				break;
			}
			case 2:
			{
				main_player = new Sven(temp_name);
				break;
			}
			case 3:
			{
				main_player = new PhantomAssassin(temp_name);
				break;
			}
			case 4:
			{
				main_player = new Invoker(temp_name);
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

		if(main_player != null)
			main_player.setName(temp_name);
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

		boss_row = rand.nextInt(5); // a random row
		//boss_column; // = rand.nextInt(3) + 2; // a random column from 3 to 5
		boss_column = rand.nextInt(1 + boss_row) + (4 - boss_row); // view paint diagram to see possible rooms

		map[boss_row][boss_column] = 4; // the 4 signals the boss battle
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

		if(!characters.contains(main_player))
			characters.add(main_player);

		if(!players.contains(main_player))
			players.add(main_player);

		addMonsters();	// spawns monsters- if level > 1, spawns one monster of player's level and the rest are random monsters from lower levels

		System.out.println();

		while(!battleOver())
		{
			turn_number++;
			System.out.println("------------------- Turn " + turn_number + " -------------------\n");

			for(int i = 0; i < players.size(); i++)
			{
				Characters current_player = players.get(i);

				if(!battleOver())
					players.get(i).takeTurn();

				if(i >= players.size())		// If the character dies on its turn (like an illusion timing out), the array position is not modified
					i--;
				else if(current_player != players.get(i))
					i--;

				checkForDeaths();
			}

			for(int i = 0; i < monsters.size(); i++)
			{
				if(!battleOver())
					monsters.get(i).takeTurn();

				checkForDeaths();
			}
		}

		System.out.println("------------------- Battle Over -------------------\n");

		/*	while(players.size() > 0)	// removes all players from this array at the end of the battle TODO error somewhere in these loops causing infinite loop
			if(players.get(0) != main_player)	// main player cannot be removed from array
				players.remove(0);
		*/
		for(int i = 0; i < players.size(); i++)		// removes all players from this array at the end of the battle that are not the main player (illusions, etc.)
		{
			if(players.get(i) != main_player)		// main player cannot be removed from array
			{
				players.get(i).die();
				i--;
			}
		}

		if(main_player.isAlive())			// if the player wins the battle, player is restored
			main_player.restoreToFull();
		else
		{
			for(int i = 0; i < monsters.size(); i++)		// removes all players from this array at the end of the battle that are not the main player (illusions, etc.)
			{
				monsters.get(i).die();
			}
		}
	}

	private static void addMonsters()
	{
		if(map[current_row][current_column] < 4)
		{
			if(map[current_row][current_column] == 3)
				for(int i = 0; i < map[current_row][current_column]; i++)
				{
					int temp = 0;

					if(i == 0)
						temp = pickMonsterSpawnedOfLevel(main_player.getLevel()); 	//spawns monster of current level
					else
					{
						temp = pickMonsterSpawnedUpTo(main_player.getLevel());
					}

					createMonster(temp);

					System.out.println(monsters.get(monsters.size() - 1) + " entered the fray.");
				}
			else if(map[current_row][current_column] == 2)							// spawns two monsters of current level
				for(int i = 0; i < map[current_row][current_column]; i++)
				{
					int temp = pickMonsterSpawnedOfLevel(main_player.getLevel()); 	//spawns monster of current level

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
		if(lvl > 3)
			lvl = 3;

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
				new Kobold();
				break;
			}
			case 2:
			{
				new Treant();
				break;
			}
			case 3:
			{
				new FellSpirit();
				break;
			}
			case 4:
			{
				new VhoulAssassin();
				break;
			}
			case 5:
			{
				new SatyrBanisher();
				break;
			}
			case 6:
			{
				new MeleeCreep();
				break;
			}
			case 7:
			{
				new RangedCreep();
				break;
			}
			case 8:
			{
				new HillTrollPriest();
				break;
			}
			case 9:
			{
				new CentaurCourser();
				break;
			}
			case 10:
			{
				new Hellbear();
				break;
			}
			case 11:
			{
				new SatyrMindstealer();
				break;
			}
			case 12:
			{
				new HellbearSmasher();
				break;
			}
			case 13:
			{
				new SatyrTormenter();
				break;
			}
			case 14:
			{
				new CentaurConqueror();
				break;
			}
			case 15:
			{
				new Roshan();
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
		{
			if(c instanceof Monster)
				((Monster) c).die();
			else if(c instanceof Player)
				((Player) c).die();
			else if(c instanceof Illusion)
				((Illusion) c).die();
		}
	}

	private static boolean battleOver()
	{
		if(!main_player.isAlive() || monsters.size() == 0) // this used to call allMonstersDead(), i changed it
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

				System.out.print((spells + 1) + ": " + s.NAME + " (Level " + s.spell_level + ")\n\t" + s.DESCRIPTION + ".");

				if(s instanceof ActiveSpell)
				{
					if(s.isCastable())
						System.out.print("\n\tMana Cost: " + s.MANA_COST + ".\n\tCooldown: " + s.max_cooldown + " turns.");

					else
					{
						if(s.onCooldown() && (s.MANA_COST > main_player.getMana()))
							System.out.print("\n\tRequires " + (s.MANA_COST - main_player.getMana()) + " more mana.\n\tOn cooldown for " + s.getCurrentCooldown() + " more turn(s).");
						else if(s.onCooldown())
							System.out.print("\n\tMana Cost: " + s.MANA_COST + ".\n\tOn cooldown for " + s.getCurrentCooldown() + " more turn(s).");
						else
							System.out.print("\n\tRequires " + (s.MANA_COST - main_player.getMana()) + " more mana.\n\tCooldown: " + s.max_cooldown + " turn(s).");
					}
				}
				else if (s instanceof PassiveSpell)
				{
					System.out.print("\n\tPassive effect.");
				}
				System.out.println();
		}
	}
	public static void displayHPandMana()
	{
		for(Characters p : players)
			System.out.println(p + "'s HP: " + p.getHP() + "/" + p.getDefaultHP()
			+  "   Mana: " + p.getMana() + "/" + p.getDefaultMana());
		for(Characters m : monsters)
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
				System.out.println("Please input correctly.");
			}
			catch(NumberFormatException e)
			{
				working = false;
				System.out.println("Please input correctly.");
			}
		}while(!working);

		return tempint;
	}

	private static boolean roshanAlive()
	{
		if(map[boss_row][boss_column] == 0)
		{
			game_over = true;
			return false;
		}
		else
			return true;
	}

	private static void loseGame()
	{
		System.out.println("Game over! Maybe next time :(");
		System.out.println("Player Stats:"
						 + "\n\tTotal damage dealt: " + main_player.getDamageDealt()
						 + "\n\tEnemies killed: " + enemies_killed);
	}

	private static void winGame()
	{
		System.out.println("Congratulations!! You have defeated Roshan and are a true hero.");
		System.out.println("Player Stats:"
						 + "\n\tTotal damage dealt: " + main_player.getDamageDealt()
						 + "\n\tEnemies killed: " + enemies_killed);
	}

	public static int getTurnNumber()
	{
		return turn_number;
	}
}
