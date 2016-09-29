package src;

public abstract class Spell
{
	final String name;
	final String description;
	final int mana_cost;
	final int level_requirement;
	final Character CHARACTER;
	final boolean targeted;
	int cooldown;

	public Spell(String tempname, String dsc, int manacost, int level_rq, int cd, Character c, boolean t)
	{
		name = tempname;
		description = dsc;
		mana_cost = manacost;
		level_requirement = level_rq;
		cooldown = cd;
		CHARACTER = c;
		targeted = t;
	}
	public boolean isTargeted()
	{
		return targeted;
	}
	public int getManaCost()
	{
		return mana_cost;
	}
	public int getLvlReq()
	{
		return level_requirement;
	}
	public abstract void cast();
	public abstract void cast(Character target);
	//TODO
}
