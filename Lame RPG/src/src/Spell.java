package src;

public abstract class Spell
{
	final String NAME;
	final String DESCRIPTION;
	final int MANA_COST;
	final int LEVEL_REQUIREMENT;
	final Character CHARACTER;
	final boolean TARGETED;
	int cooldown;

	public Spell(String tempname, String dsc, int manacost, int level_rq, int cd, Character c, boolean t)
	{
		NAME = tempname;
		DESCRIPTION = dsc;
		MANA_COST = manacost;
		LEVEL_REQUIREMENT = level_rq;
		cooldown = cd;
		CHARACTER = c;
		TARGETED = t;
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
	public abstract void cast();
	public abstract void cast(Character target);
	//TODO
}
