package classes;

import java.util.ArrayList;
import java.util.Random;

import buffs.AttackBuff;
import buffs.AttackedBuff;
import buffs.Buff;
import buffs.StatBuff;
import spells.ActiveSpell;
import spells.Spell;
import src.Game;

public class Illusion extends Characters
{
	Buff life_timer;
	int  life_duration;
	Random rand = new Random();
	
	public Illusion(Characters target)
	{
		super(target.getHP(), target.getMana(), (int) Math.round(0.33 * target.getDefaultDamage()), target.getDefaultAccuracy(), 
				0, 0, target.getName() + " Illusion");
		
		life_duration = 5;		// should be amount of turns + 1 to account for being spawned
		
		Game.characters.add(this);
		
		if(Game.players.contains(target))
			Game.players.add(this);
		else if(Game.monsters.contains(target))
			Game.monsters.add(this);
		
		for(Buff b : target.buffs) // gives the illusions the same passives as the target
		{
			if(b.getDuration() == -1) //if passive
			{
				if(b instanceof AttackBuff)
				{
					new AttackBuff(b.getName(), b.getDescription(), this, -1, ((AttackBuff) b).getManaBurn(), 
							((AttackBuff) b).getManaBurnMultiplier(), ((AttackBuff) b).getCritChance(), ((AttackBuff) b).getCritModifier(),
							((AttackBuff) b).getEnemiesCleaved(), ((AttackBuff) b).getCleavePercentage());
				}
				else if(b instanceof AttackedBuff)
				{
					new AttackedBuff(b.getName(), b.getDescription(), this, -1, ((AttackedBuff) b).getDodgeChance());
				}
				else if(b instanceof StatBuff)
				{
					new StatBuff(b.getName(), b.getDescription(), this, -1, ((StatBuff) b).getStat(), ((StatBuff) b).getDoubleModifier());
				}
			}
		}
		
		life_timer = new StatBuff("Illusion Duration", "Will die after " + (life_duration - 1) + " turns", this, life_duration, 4, 0 );
	}
	
	public Illusion(Characters target, int duration)
	{
		super(target.getDefaultHP(), target.getDefaultMana(), (int) Math.round(0.33 * target.getDefaultDamage()), target.getDefaultAccuracy(), 
				0, 0, target.getName() + " Illusion");
		
		setHP(target.getHP());
		setMana(target.getMana());
		
		life_duration = duration + 1;		// should be amount of turns + 1 to account for being spawned
		
		Game.characters.add(this);
		
		if(Game.players.contains(target))
			Game.players.add(this);
		else if(Game.monsters.contains(target))
			Game.monsters.add(this);
		
		for(Buff b : target.buffs) // gives the illusions the same passives as the target
		{
			if(b.getDuration() == -1) //if passive
			{
				if(b instanceof AttackBuff)
				{
					new AttackBuff(b.getName(), b.getDescription(), this, -1, ((AttackBuff) b).getManaBurn(), 
							((AttackBuff) b).getManaBurnMultiplier(), ((AttackBuff) b).getCritChance(), ((AttackBuff) b).getCritModifier(),
							((AttackBuff) b).getEnemiesCleaved(), ((AttackBuff) b).getCleavePercentage());
				}
				else if(b instanceof AttackedBuff)
				{
					new AttackedBuff(b.getName(), b.getDescription(), this, -1, ((AttackedBuff) b).getDodgeChance());
				}
				else if(b instanceof StatBuff)
				{
					new StatBuff(b.getName(), b.getDescription(), this, -1, ((StatBuff) b).getStat(), ((StatBuff) b).getDoubleModifier());
				}
			}
		}
		
		life_timer = new StatBuff("Illusion Duration", "Will die after " + (life_duration - 1) + " turns", this, life_duration, 4, 0 );
	}

	@Override
	public void takeTurn()
	{
		int choice, target;
		boolean turn_taken = this.isStunned();
		
		reduceCooldowns();
		refreshDebuffs();
		
		if(buffs.contains(life_timer))
		{
			if(Game.players.contains(this))
			{
				if(turn_taken)
				{
					System.out.println(this + " is stunned.");
					refreshStuns();
				}
		
				while(!turn_taken)
				{
					turn_taken = false;
		
					Game.displayHPandMana();
		
					System.out.println("It is " + this + "'s turn.\n");
					
					System.out.println("What would you like to do?");
					System.out.println("1: Attack"
								   + "\n2: View Buffs and Debuffs"
								   + "\n3: Hunker Down");
					choice = Game.getNumberFrom(1, 3);
		
					switch(choice)
					{
						case 1:
						{
							System.out.println("\nWhat would you like to attack?");
							System.out.println("1: Go back.");
							for(int i = 0; i < Game.monsters.size(); i++)
							{
								System.out.println((i + 2) + ": " + Game.monsters.get(i)
										+"(" + Game.monsters.get(i).getHP() + "/" + Game.monsters.get(i).getDefaultHP() + ")");
							}
							
							target = Game.getNumberFrom(1, Game.monsters.size() + 1);
							if(target > 1)
							{
								attack(Game.monsters.get(target - 2));
								turn_taken = true;
							}
							
							break;
						}
						case 2:
						{
							for(Characters c : Game.players)
								c.printAllBuffs();
							for(Characters m : Game.monsters)
								m.printAllBuffs();
		
							break;
						}
						case 3:
						{
							hunkerDown();
							turn_taken = true;
							
							break;
						}
					}
				}
			}
			else if(Game.monsters.contains(this))
			{
				if(!this.isStunned())
				{
					int temp = rand.nextInt(100) + 1;
					if(temp > 50) // 50% chance
						attack(Game.players.get(rand.nextInt(Game.players.size())));		// attacks a random player
					else if(temp > 15) // 35% chance
					{
						if(!allSpellsUncastable())
						{
							// makes an arraylist for castable spells, then randomly pick one to cast. if targeted, selects a random target
							ArrayList<ActiveSpell> castable_spells = new ArrayList<ActiveSpell>();
							int spell_index;
							for(Spell s : spellbook) // gets the castable spells
							{
								if(s.isCastable())
									castable_spells.add((ActiveSpell) s);
							}
							
							spell_index = rand.nextInt(castable_spells.size()); // picks a random castable spell
							
							if(castable_spells.get(spell_index).isTargeted()) //casts the spell
								castable_spells.get(spell_index).cast(Game.main_player);
							else
								castable_spells.get(spell_index).cast();
							
							while(castable_spells.size() > 0) // clears the arraylist
								castable_spells.remove(0);
						}
						else
							attack(Game.players.get(rand.nextInt(Game.players.size())));		// attacks a random player
					}
					else // also 15% chance
						hunkerDown();
				}
				else
				{
					System.out.println(this + " is stunned.");
					refreshStuns();
				}
			}
		}
		else
		{
			die();
		}
	}
	
	@Override
	public void die()
	{
		super.die();
		if(Game.players.contains(this))
			Game.players.remove(this);
		else if(Game.monsters.contains(this))
			Game.monsters.remove(this);
	}
}
