package src;

import java.util.ArrayList;
import java.util.Random;

import classes.AntiMage;
import classes.Characters;
import classes.Invoker;
import classes.Monster;
import classes.PhantomAssassin;
import classes.Player;
import classes.Sven;
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
	private static int map[][] = new int[5][5];
	private static int current_row = 0;
	private static int current_column = 0;
	private static int turn_number = 0;
	public  static Random rand = new Random();
	private static ArrayList<Integer> options = new ArrayList<Integer>();
	public  static ArrayList<Monster> monsters = new ArrayList<Monster>();
	public  static ArrayList<Characters> characters = new ArrayList<Characters>();

	private static boolean game_over = false;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args)
	{
		setName();
		setHero();
		makeDungeon();
		while(!game_over)
		{
			if(player.isAlive())
				chooseRoom();
			if(player.isAlive())
				battle();
		}
		
		System.out.println("Game over! Maybe next time :(");
	}
	
	private static void setHero()
	{
		System.out.println("What hero do you want to be?");
		System.out.println("1- Anti-Mage: A balanced melee fighter who specializes against spellcasters."
						+"\n2- Sven: A melee fighter who has high physical defense and attack."
						+"\n3- Phantom Assassin: A glass cannon whose attack is very high, but at the expense of poor health."
						+"\n4- Invoker: A mage who has both damaging and utility spells.");

		player_hero = getNumberFrom(1, 4);
		//TODO add levels for each skill/ability
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
				System.out.println("You can't go that way!");
		}while(!options.contains(tempint));
		choice = tempint;

		if(choice == 1)
			current_row += 1;			// go right
		else if(choice == 2)
			current_column += 1;		// go down
		else if(choice == 3)
			current_row -= 1;			// go left
		else if(choice == 4)
			current_column -= 1;		// go up
	}
	private static void showRoomChoices()
	{
		options = new ArrayList<Integer>();
		
		for(@SuppressWarnings("unused") int integer : options) //are these two lines unnecessary??
			options.remove(0);
		
		options.add(1);
		options.add(2);
		options.add(3);
		options.add(4);
		
		if(current_row == 0)					// when you cannot go left
			options.remove((Integer) 3);
		else if(current_row == 4) 				// when you cannot go right
			options.remove((Integer) 1);
		if(current_column == 0) 				// when you cannot go up
			options.remove((Integer) 4);
		else if(current_column == 4) 			// when you cannot go down
				options.remove((Integer) 2);
		
		if(options.contains(1))
			System.out.println("1: Go right.");
		if(options.contains(2))
			System.out.println("2: Go down.");
		if(options.contains(3))
			System.out.println("3: Go left.");
		if(options.contains(4))
			System.out.println("4: Go up.");
	}
	//TODO does going back and forth into rooms recreate monsters?
	private static void battle()
	{
		characters.add(player);
		
		for(int i = 0; i < map[current_row][current_column]; i++)
		{
			monsters.add(new Monster("Monster " + (i+1)));
			System.out.println("What de heck! It's a(n) " + monsters.get(i) + "!!");
		}
		System.out.println();

		for(Monster m : monsters)
			characters.add(m);

		turn_number = 0;
		
		while(!battleOver())
		{
			turn_number++;
			System.out.println("------------------- Turn " + turn_number + " -------------------\n");
			
			if(!battleOver())
				takePlayerTurn();
			
			checkForDeaths();
			
			if(!battleOver())
				for(Monster m: monsters)
				{
					if(m.isAlive()) //FIXME is this redundant?? i think monsters get removed from this array when they die
						takeMonsterTurn(m);
					else
						System.out.println(m + " is dead. you coded something wrong."); //this should never actually print
				}
			
			checkForDeaths();
		}
		
		System.out.println("------------------- Battle Over -------------------\n");

		while(characters.size() > 0)// removes all characters from this array at the end of the battle
			characters.remove(0);
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
		if(!player.isAlive() || monsters.size() == 0) //this used to call allMonstersDead(), i changed it
			return true;
		else
			return false;
	}

	/*private static boolean allMonstersDead()//FIXME this must be recoded because of checkForDeaths() removing monsters from the array
	{
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
	}*/

	private static void takePlayerTurn()
	{
		int choice, target;
		boolean turn_taken = player.isStunned();
		
		player.refreshDebuffsAndReduceCooldowns();
		
		if(turn_taken)
			System.out.println(player + " is stunned.");

		while(!turn_taken)
		{
			turn_taken = false;

			displayHPandMana();

			System.out.println("What would you like to do?");
			System.out.println("1: Attack"
						   + "\n2: Abilities"
						   + "\n3: View Buffs and Debuffs"
						   + "\n4: Hunker Down");
			choice = getNumberFrom(1, 4);

			switch(choice)
			{
				case 1:
				{
					System.out.println("\nWhat would you like to attack?");
					for(int i = 0; i < monsters.size(); i++)
					{
						System.out.println((i + 1) + ": " + monsters.get(i)
								+"(" + monsters.get(i).getHP() + "/" + monsters.get(i).getDefaultHP() + ")");
					}
					target = getNumberFrom(1, monsters.size()) - 1;
					player.attack(monsters.get(target));
					turn_taken = true;
					break;
				}
				case 2:
				{
					int spell_choice_index, spells;
					spells = player.spellbook.size();
							
					if(player.allSpellsUncastable())
						System.out.println("You cannot cast any spells. Go back.");
					else
						System.out.println("Which spell would you like to cast?");

					System.out.println("0: Go back.");

					listSpells(player);
					
					spell_choice_index = getNumberFrom(0, spells) - 1;
					while(spell_choice_index > -1 && (player.spellbook.get(spell_choice_index) instanceof PassiveSpell ||
						 (player.spellbook.get(spell_choice_index) instanceof ActiveSpell && !player.spellbook.get(spell_choice_index).isCastable())))
					 {
							if(player.spellbook.get(spell_choice_index) instanceof PassiveSpell)
								System.out.println("You cannot cast a passive spell.");
							else
								System.out.println("That spell is not castable right now.");

							System.out.println("Which spell would you like to cast?");
							System.out.println("0: Go back.");

							listSpells(player);

							spell_choice_index = getNumberFrom(0, spells) - 1;
					 }

					if(spell_choice_index >= 0)
					{
						if(player.spellbook.get(spell_choice_index).isTargeted())
						{
							System.out.println("Cast " + player.spellbook.get(spell_choice_index).NAME + " on what?");
							System.out.println("0: Cancel spell cast.");

							for(int i = 0; i < monsters.size(); i++)
							{
								System.out.println((i + 1) + ": " + monsters.get(i)
										+"(" + monsters.get(i).getHP() + "/" + monsters.get(i).getDefaultHP() + ")");
							}
							target = getNumberFrom(0, monsters.size()) - 1;
							if(target >= 0)//if the spell cast is not cancelled
							{
								((ActiveSpell) player.spellbook.get(spell_choice_index)).cast(monsters.get(target));
								turn_taken = true;
							}
							else
								turn_taken = false;
						}
						else
						{
							((ActiveSpell) player.spellbook.get(spell_choice_index)).cast();
							turn_taken = true;
						}
					}
					else
						turn_taken = false;

					break;
				}
				case 3:
				{
					int buffs_to_view;

					System.out.println("Whose buffs would you like to view?");
					System.out.println("0: Go back.");
					System.out.println("1: View all buffs.");
					System.out.println("2: " + player + "'s buffs.");

					for(int i = 0; i < monsters.size(); i++)
						System.out.println((i + 3) + ": " + monsters.get(i) + "'s buffs.");

					buffs_to_view = getNumberFrom(0, monsters.size() + 2);
					if(buffs_to_view == 1)
					{
						player.printAllBuffs();
						for(Monster m : monsters)
							m.printAllBuffs();
					}
					else if(buffs_to_view == 2)
						player.printAllBuffs();
					else if(buffs_to_view > 2)
						monsters.get(buffs_to_view - 3).printAllBuffs();

					break;
				}
				case 4:
				{
					player.hunkerDown();
					break;
				}
			}
		}
	}

	private static void takeMonsterTurn(Monster m)
	{
		m.refreshDebuffsAndReduceCooldowns();
		
		if(!m.isStunned())
		{
			int temp = rand.nextInt(100) + 1;
			if(temp <= 70) // 70% chance
				m.attack(player);
			else if(temp <= 85 && !m.allSpellsUncastable()) // 15% chance
			{
				// make an arraylist for castable spells, then randomly pick one to cast. if targeted, select a random target
				ArrayList<ActiveSpell> castable_spells = new ArrayList<ActiveSpell>();
				int spell_index;
				for(Spell s : m.spellbook) // get the castable spells
				{
					if(s.isCastable())
						castable_spells.add((ActiveSpell) s);
				}
				
				spell_index = rand.nextInt(castable_spells.size() + 1); // picks a random castable spell
				
				if(castable_spells.get(spell_index).isTargeted()) //casts the spell
					castable_spells.get(spell_index).cast(player);
				else
					castable_spells.get(spell_index).cast();
				
				for(@SuppressWarnings("unused") ActiveSpell s : castable_spells) //clear the arraylist
					castable_spells.remove(0);
			}
			else // also 15% chance
				m.hunkerDown();
		}
		else
			System.out.println(m + " is stunned.");
	}

	private static void listSpells(Characters c)
	{
		int spells = 0;

		for(Spell s : c.spellbook)
		{
				spells++;

				System.out.print(spells + ": " + s.NAME + "- " + s.DESCRIPTION + ".");

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
	private static void displayHPandMana()
	{
		System.out.println(player + "'s HP: " + player.getHP() + "/" + player.getDefaultHP()
		+  "   Mana: " + player.getMana() + "/" + player.getDefaultMana());
		for(Monster m : monsters)
			System.out.println(m + "'s HP: " + m.getHP() + "/" + m.getDefaultHP()
			+  "   Mana: " + m.getMana() + "/" + m.getDefaultMana());
		System.out.println();
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
}