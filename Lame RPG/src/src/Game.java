package src;

import java.util.ArrayList;
import java.util.Random;

import classes.AntiMage;
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
	static String temp_name = "";
	static int player_hero;
	static Player player;
	static int map[][] = new int[5][5];
	static int current_row = 0;
	static int current_column = 0;
	public static Random rand = new Random();
	static ArrayList<Integer> options = new ArrayList<Integer>();
	public static ArrayList<Monster> monsters = new ArrayList<Monster>();
	static boolean game_over = false;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args)
	{
		setHero();
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
	}
	private static void battle()
	{
		for(int i = 0; i < map[current_row][current_column]; i++)
		{
			monsters.add(new Monster((rand.nextInt(player.getDefaultHP() + 1) + 50), (rand.nextInt(71) + 40), (rand.nextInt(31) + 5), 
									 (rand.nextInt(101) + 10), (rand.nextInt(21) + 5), 1, (double) (rand.nextInt(26)) / 100.0, "Monster " + (i+1)));
			System.out.println("What de heck! It's a(n) " + monsters.get(i) + "!!");
		}
		do
		{
			refreshDebuffsAndReduceCooldowns();
			if(!battleOver())
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

	private static void refreshDebuffsAndReduceCooldowns()
	{
		// TODO Add a Debuff/Buff class to contain all buffs with durations, effects, etc.
		// FIXME placeholder until buff class
		
		// Reduce the effects of defense buffs/debuffs each turn until equal to max defense
		if(player.getDefense() < player.getDefaultDefense())
				player.changeDefense(15);
		else if(player.getDefense() > player.getDefaultDefense())
		{
			if((player.getDefense() - 10) < player.getDefaultDefense())
				player.changeDefense(-(player.getDefaultDefense() - player.getDefense()));
			else
				player.changeDefense(-15);
		}
		for(Monster m : monsters)
		{
			if(m.getDefense() < m.getDefaultDefense())
				m.changeDefense(10);
			else if(m.getDefense() > m.getDefaultDefense())
			{
				if((m.getDefense() - 15) < m.getDefaultDefense())
					m.changeDefense(-(m.getDefaultDefense() - m.getDefense()));
				else
					m.changeDefense(-15);
			}
		}
		
		// Reduce each spell's cooldown that is on cooldown
		for(Spell s : player.spellbook)
			if(s.getCurrentCooldown() > 0)
				s.changeCurrentCooldown(-1);
	}

	private static void takePlayerTurn()
	{
		int choice, target;
		boolean turn_taken = player.isStunned();
		if(turn_taken)
			System.out.println(player + " is stunned.");
		
		while(!turn_taken)
		{
			turn_taken = false;
			
			displayHPandMana();
			
			System.out.println("What would you like to do?");
			System.out.println("1: Attack"
						   + "\n2: Abilities"
						   + "\n3: Use an Item"
						   + "\n4: Hunker Down");//TODO add an option to view buffs and debuffs on all characters.
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
					spells = 0;
					//TODO TODO TODO rework this interface - instead, display all spells with cooldowns, & if spell on cd is selected, repeat this choice
					if(!player.allSpellsUncastable())
					{
						System.out.println("Which spell would you like to cast?");
						System.out.println("0: Go back.");
						
						listSpells(player);
						
						spell_choice_index = getNumberFrom(0, spells) - 1;
						while(spell_choice_index >= 0 && (player.spellbook.get(spell_choice_index) instanceof PassiveSpell || 
							 (player.spellbook.get(spell_choice_index) instanceof ActiveSpell && player.spellbook.get(spell_choice_index).isCastable())))
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
						
						//TODO show monsters/targets and input choice to cast on + go back ability
						
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
									((ActiveSpell) player.spellbook.get(spell_choice_index)).cast(monsters.get(target));
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
					}
					else
					{
						System.out.println("You can't cast any spells!\n");
						turn_taken = false;
					}
					
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
	}
	
	private static void listSpells(Player p) //TODO change to character
	{
		int spells = 0;
		
		for(Spell s : p.spellbook)
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
							System.out.print(" On cooldown for " + s.getCurrentCooldown() + " more turns. (Costs " + s.MANA_COST + " mana.");
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

	private static void takeMonsterTurn(Monster m)
	{
		if(!m.isStunned())
		{
			int temp = rand.nextInt(100) + 1;
			if(temp >= 30)
				m.attack(player);
			else
				m.hunkerDown();
		}
		else
			System.out.println(m + " is stunned.");
	}
	
	static int getNumberFrom(int start, int end)
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
		}while(!working);
		
		return tempint;
	}	
}