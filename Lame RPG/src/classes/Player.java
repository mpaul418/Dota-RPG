package classes;

import java.util.ArrayList;

import spells.ActiveSpell;
import spells.CoupDeGrace;
import spells.DeafeningBlast;
import spells.GodsStrength;
import spells.ManaVoid;
import spells.PassiveSpell;
import spells.Spell;
import src.Game;

public abstract class Player extends Characters 
{
	/*Character Classes:
	 * 1- AntiMage
	 * 2- Warrior
	 * 3- Assassin
	 * 4- Wizard*/
	
	public int character_class, gold, xp, xp_level_rq;
	
	public Player(int initHP, int initMana, int initDmg, int initAcc, int initDef, int playerClass, double initMagDef, String initName) 
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName);
		character_class = playerClass;
		xp = 0;
		xp_level_rq = 100;
		
		Game.players.add(this);
	}
	
	public abstract String getCharClass();
	
	public void changeXP(int death_xp)
	{
		xp += death_xp;
		System.out.println(this + " gained " + death_xp + " experience points.");
		if(xp >= xp_level_rq)
			levelUp();
		else
			System.out.println(this + " needs " + (xp_level_rq - xp) + " more experience points to level up.\n");
	}
	
	private void levelUp()
	{
		int levelup_points = 3;
		
		this.level++;
		xp -= xp_level_rq; // starts your xp from 0 again every time you level up- ex. 105 xp --> 5 xp
		xp_level_rq += ((25 * level) + (5 * (level - 1) * (level - 1))); // xp increases by 5(x - 1)^2 + 25x every level. 1:100, 2: 155, 3: 250, 4: 395, etc TODO i think this is not scaling correctly
		
		System.out.println("---------------------------------------------------\n\n" + this + " just leveled up!!"
				+  "\nHP increased by " + (int)Math.round(0.25 * defaultHP)
				+ ".\nMana increased by " + (int)Math.round(0.25 * defaultMana)
				+ ".\nDamage increased by " + (int)Math.round(0.25 * defaultDamage) + ".");
		
		HP += (int)Math.round(0.25 * defaultHP);
		mana += (int)Math.round(0.25 * defaultMana);
		damage += (int)Math.round(0.25 * defaultDamage);
		
		defaultHP = (int)Math.round(1.25 * defaultHP);
		defaultMana = (int)Math.round(1.25 * defaultMana);
		defaultDamage = (int)Math.round(1.25 * defaultDamage);
		
		int temp1 = 0;
		while(temp1 < unlearned_spells.size())
		{
			if(this.level >= unlearned_spells.get(temp1).getLevelRequirement())
			{
				unlearned_spells.get(temp1).addToSpellbook();
			}
			else
				temp1++;
		}
		
		while(checkForSpellsToLevel() && levelup_points > 0)
		{
			System.out.println(levelup_points + " levelup points to spend. Which spell would you like to level?");
			
			int temp2 = 0;
			ArrayList<Spell> levelable_spells = new ArrayList<Spell>();
			
			for(Spell s : spellbook) // lists each spell that can be leveled
			{
				if(s.getLevel() < s.getMaxLevel())
				{
					if(!(s instanceof CoupDeGrace || s instanceof DeafeningBlast || s instanceof ManaVoid || s instanceof GodsStrength)) // if not an ultimate
					{
						levelable_spells.add(s);
						temp2++;
						System.out.println(temp2 + ": " + s + " (currently level " + s.getLevel() + ")- " + s.DESCRIPTION + ".");
					}
					else if(s.getLevel() < ((this.getLevel() - 1))) // ultimates can only be leveled once per level starting at level 3
					{
						levelable_spells.add(s);
						temp2++;
						System.out.println(temp2 + ": " + s + " (currently level " + s.getLevel() + ")- " + s.DESCRIPTION + ".");
					}
				}
			}
			
			int level_choice = Game.getNumberFrom(1, levelable_spells.size());
			levelable_spells.get(level_choice - 1).levelUp();
			
			levelup_points--;
		}
		
		System.out.println("---------------------------------------------------");
	}
	
	private boolean checkForSpellsToLevel()
	{
		for(Spell s : spellbook)
		{
			if(s.getLevel() < s.getMaxLevel())
			{
				if(s instanceof CoupDeGrace || s instanceof DeafeningBlast || s instanceof ManaVoid || s instanceof GodsStrength) // if the ability is an ultimate
				{
					if(s.getLevel() < ((this.getLevel() - 1)))	// can only be leveled once per level starting at level 3
						return true;
				}
				else // if it is not an ultimate ability
					return true;
			}
		}
		return false;
	}
	
	@Override
	public void takeTurn()
	{
		int choice, target;
		boolean turn_taken = this.isStunned();
		
		if(turn_taken)
		{
			System.out.println(this + " is stunned.");
			refreshStuns();
		}

		while(!turn_taken)
		{
			turn_taken = false;
			
			reduceCooldowns();
			refreshDebuffs();

			Game.displayHPandMana();

			System.out.println("It is " + this + "'s turn.\n");
			
			System.out.println("What would you like to do?");
			System.out.println("1: Attack"
						   + "\n2: Abilities"
						   + "\n3: View Buffs and Debuffs"
						   + "\n4: Hunker Down");
			choice = Game.getNumberFrom(1, 4);

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
					int spell_choice_index, spells;
					spells = spellbook.size();
							
					if(allSpellsUncastable())
						System.out.println("You cannot cast any spells. Go back.");
					else
						System.out.println("Which spell would you like to cast?");

					System.out.println("1: Go back.");

					Game.listSpells(this);
					
					spell_choice_index = Game.getNumberFrom(1, spells + 1) - 2;
					while(spell_choice_index > -1 && (spellbook.get(spell_choice_index) instanceof PassiveSpell ||
						 (spellbook.get(spell_choice_index) instanceof ActiveSpell && !spellbook.get(spell_choice_index).isCastable())))
					 {
							if(spellbook.get(spell_choice_index) instanceof PassiveSpell)
								System.out.println("You cannot cast a passive spell.");
							else
								System.out.println("That spell is not castable right now.");

							System.out.println("Which spell would you like to cast?");
							System.out.println("0: Go back.");

							Game.listSpells(this);

							spell_choice_index = Game.getNumberFrom(1, spells + 1) - 2;
					 }

					if(spell_choice_index >= 0)
					{
						if(spellbook.get(spell_choice_index).isTargeted())
						{
							System.out.println("Cast " + spellbook.get(spell_choice_index).NAME + " on what?");
							System.out.println("1: Cancel spell cast.");

							for(int i = 0; i < Game.monsters.size(); i++)
							{
								System.out.println((i + 2) + ": " + Game.monsters.get(i)
										+"(" + Game.monsters.get(i).getHP() + "/" + Game.monsters.get(i).getDefaultHP() + ")");
							}
							target = Game.getNumberFrom(1, Game.monsters.size() + 1) - 2;
							if(target >= 0)	// if the spell cast is not cancelled
							{
								((ActiveSpell) spellbook.get(spell_choice_index)).cast(Game.monsters.get(target));
								turn_taken = true;
							}
							else
								turn_taken = false;
						}
						else
						{
							((ActiveSpell) spellbook.get(spell_choice_index)).cast();
							turn_taken = true;
						}
					}

					break;
				}
				case 3:
				{
					this.printAllBuffs();
					for(Characters m : Game.monsters)
						m.printAllBuffs();

					break;
				}
				case 4:
				{
					hunkerDown();
					turn_taken = true;
					
					break;
				}
			}
		}
	}
	
	@Override
	public void die()
	{
		super.die();
		Game.players.remove(this);
		if(this == Game.main_player)
			Game.game_over = true;
	}

	public void restoreToFull()
	{
		HP = getDefaultHP();		// sets player's health back to maximum
		mana = getDefaultMana();
		hunker_downs_used = 0;
		
		int i = 0;
		int size = buffs.size();
		while(i < size)		// removes all non-temporary buffs TODO come up with a way to do this that doesnt use buffs.size() twice
		{
			if(buffs.get(i).getDuration() > 0)
			{
				while(buffs.size() > 0 && buffs.get(i).getDuration() > 0)		//FIXME double check this
					buffs.get(i).decreaseDurationNoNotifier();
				size--;
			}
			else
				i++;				// it stays in the same index unless the buff is permanent
		}
		
		for(Spell s : spellbook)				// resets all cooldowns of your spells
			while(s.getCurrentCooldown() > 0)
				s.changeCurrentCooldown(-1);
	}
}