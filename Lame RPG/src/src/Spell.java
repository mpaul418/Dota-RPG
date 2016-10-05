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
	int current_cooldown = 0;

	//FIXME is Character parameter required? dont think so, double check this
	
	public Spell(String tempname, String dsc, int manacost, int level_rq, int cd, Character c, boolean targetable)
	{
		NAME = tempname;
		DESCRIPTION = dsc;
		MANA_COST = manacost;
		LEVEL_REQUIREMENT = level_rq;
		max_cooldown = cd;
		CHARACTER = c;
		TARGETED = targetable;
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
	public boolean isCastable()
	{
		//TODO placeholder- need to show in takePlayerTurn() if a spell is correct level but not enough mana
		if(CHARACTER.getLevel() >= LEVEL_REQUIREMENT && CHARACTER.getMana() >= MANA_COST && current_cooldown == 0)
			return true;
		else
			return false;
	}
	public abstract void cast();
	public abstract void cast(Character target);
	//TODO
}
