package spells;

import classes.Characters;
import classes.Player;

public abstract class Spell
{
	public final String NAME;
	public final String DESCRIPTION;
	public final int MANA_COST;
	public final int LEVEL_REQUIREMENT;
	public final Characters CHARACTER;
	public final boolean TARGETED;
	public int max_cooldown;
	public int current_cooldown = 0;
	public int spell_level;
	public int max_spell_level = 4; // this can be overridden
	
	public Spell(String tempname, String dsc, int manacost, int level_rq, int cd, Characters c, boolean targetable)
	{
		NAME = tempname;
		DESCRIPTION = dsc;
		MANA_COST = manacost;
		LEVEL_REQUIREMENT = level_rq;
		max_cooldown = cd;
		CHARACTER = c;
		TARGETED = targetable;
		spell_level = 1;
		
		if(CHARACTER.getLevel() >= LEVEL_REQUIREMENT)
		{
			if(CHARACTER instanceof Player)
				this.addToSpellbook();
			else
				this.addToSpellbookNoNotifer();
		}
		else
			CHARACTER.unlearned_spells.add(this);
	}
	public boolean isTargeted()
	{
		return TARGETED;
	}
	public int getManaCost()
	{
		return MANA_COST;
	}
	public int getLevelRequirement()
	{
		return LEVEL_REQUIREMENT;
	}
	public int getCurrentCooldown()
	{
		return current_cooldown;
	}
	public int getLevel()
	{
		return spell_level;
	}
	public int getMaxLevel()
	{
		return max_spell_level;
	}
	public void changeCurrentCooldown(int i)
	{
		current_cooldown = current_cooldown + i;
	}
	public String toString()
	{
		return NAME;
	}
	
	public abstract boolean isCastable();
	
	public boolean onCooldown() // only use this if spell is in the spellbook
	{
		if(current_cooldown == 0)
			return false;
		else
			return true;
	}
	
	public void addToSpellbook()
	{
		CHARACTER.spellbook.add(this);
		CHARACTER.unlearned_spells.remove(this);
		if(CHARACTER instanceof Player)
			System.out.println(CHARACTER + " just learned " + this + ".");
	}
	
	public void addToSpellbookNoNotifer()
	{
		CHARACTER.spellbook.add(this);
		CHARACTER.unlearned_spells.remove(this);
	}
	
	public void beforeSpellCast()
	{
		CHARACTER.changeMana(-MANA_COST);
		current_cooldown = max_cooldown;
	}
	
	public boolean levelUp()
	{
		if(spell_level < max_spell_level)
		{
			spell_level++;
			if(CHARACTER instanceof Player)
				System.out.println(this + " is now level " + spell_level + ".\n");
			
			return true;
		}
		else
			return false;
	}
}