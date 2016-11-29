package spells;

import classes.Characters;

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
	
	public Spell(String tempname, String dsc, int manacost, int level_rq, int cd, Characters c, boolean targetable)
	{
		NAME = tempname;
		DESCRIPTION = dsc;
		MANA_COST = manacost;
		LEVEL_REQUIREMENT = level_rq;
		max_cooldown = cd;
		CHARACTER = c;
		TARGETED = targetable;
		
		if(CHARACTER.getLevel() >= LEVEL_REQUIREMENT)
			this.addToSpellbook(CHARACTER);
		else
			CHARACTER.unlearned_spells.add(this);//TODO should spells go into the spellbook no matter what and then show on casting screen they are not high enough level?
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
	public void changeCurrentCooldown(int i)
	{
		current_cooldown += i;
	}
	public String toString()
	{
		return NAME;
	}
	public abstract boolean isCastable();
	//{
		//TODO placeholder- need to show in takePlayerTurn() if a spell is correct level but not enough mana
	//}
	public boolean onCooldown()//only use this if spell is in the spellbook
	{
		if(current_cooldown == 0)
			return false;
		else
			return true;
	}
	public void addToSpellbook(Characters c)
	{
		c.spellbook.add(this);
		c.unlearned_spells.remove(this);
		System.out.println(c + " just learned " + this + ".");
	}
	
	public void afterSpellCast()
	{
		CHARACTER.changeMana(-MANA_COST);
		current_cooldown = max_cooldown;
	}
}
