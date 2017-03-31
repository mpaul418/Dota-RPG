package classes;

import java.util.ArrayList;
import java.util.Random;

import spells.ActiveSpell;
import spells.Spell;
import src.Game;

public class Monster extends Characters 
{
	static Random rand = new Random(); 
	protected int death_xp;
	
	public Monster(String name)
	{
		super(rand.nextInt(Game.player.getDefaultHP() + 1) + 50, (rand.nextInt(91) + 20 * Game.player.getLevel()), (rand.nextInt(26) + 5 * Game.player.getLevel() + 5), 
		 (rand.nextInt(101) + 7 * Game.player.getLevel()), (rand.nextInt(21) + 5 * Game.player.getLevel()), (double) (rand.nextInt(26) / 100.0), name);
		
		level = Game.player.getLevel();
		death_xp = 25 * this.getLevel();
		
		Game.characters.add(this);
	}
	public Monster(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName);

		level = lvl;
		death_xp = dth_xp;
		
		Game.characters.add(this);
	}
	
	@Override
	public void takeTurn()
	{
		reduceCooldowns();
		
		if(!this.isStunned())
		{
			int temp = rand.nextInt(100) + 1;
			if(temp > 50) // 50% chance
				attack(Game.player);
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
						castable_spells.get(spell_index).cast(Game.player);
					else
						castable_spells.get(spell_index).cast();
					
					while(castable_spells.size() > 0) // clears the arraylist
						castable_spells.remove(0);
				}
				else
					attack(Game.player);
			}
			else // also 15% chance
				hunkerDown();
		}
		else
			System.out.println(this + " is stunned.");
		
		refreshDebuffs();
	}
	
	@Override
	public void die()
	{
		super.die();
		Game.monsters.remove(this);
		if(death_xp > 0)
			Game.player.changeXP(death_xp);
		Game.map[Game.current_row][Game.current_column] -= 1;  // removes the monster from the room so if player reenters this room, more monsters will not spawn.
		Game.enemies_killed++;
	}
}