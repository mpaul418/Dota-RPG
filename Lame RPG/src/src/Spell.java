package src;

public abstract class Spell
{
	final String NAME;
	final String DESCRIPTION;
	final int MANA_COST;
	final int LEVEL_REQUIREMENT;
	final Character CHARACTER;
	final boolean TARGETED;
	int max_cooldown;
	public int current_cooldown = 0;
	
	public Spell(String tempname, String dsc, int manacost, int level_rq, int cd, Character c, boolean targetable)
	{
		NAME = tempname;
		DESCRIPTION = dsc;
		MANA_COST = manacost;
		LEVEL_REQUIREMENT = level_rq;
		max_cooldown = cd;
		CHARACTER = c;
		TARGETED = targetable;
		
		if(CHARACTER.getLevel() >= LEVEL_REQUIREMENT)
			this.addToSpellbook((Player) CHARACTER);
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
	public boolean isCastable()
	{
		//TODO placeholder- need to show in takePlayerTurn() if a spell is correct level but not enough mana
		if(CHARACTER.getLevel() >= LEVEL_REQUIREMENT && CHARACTER.getMana() >= MANA_COST && current_cooldown == 0)
			return true;
		else
			return false;
	}
	public boolean onCooldown()//only use this if spell is in the spellbook
	{
		if(current_cooldown == 0)
			return false;
		else
			return true;
	}
	public void addToSpellbook(Player p)
	{
		p.spellbook.add(this);
		p.unlearned_spells.remove(this);
		System.out.println(p + " just learned " + this + ".");
	}
	
	public void afterSpellCast()
	{
		CHARACTER.changeMana(-MANA_COST);
		current_cooldown = max_cooldown;
	}
}
