package abilities;

public abstract class Spell
{
	final String name;
	final String description;
	final int mana_cost;
	final int level_requirement;
	int cooldown;

	public Spell(String tempname, String dsc, int manacost, int level_rq, int cd)
	{
		name = tempname;
		description = dsc;
		mana_cost = manacost;
		level_requirement = level_rq;
		cooldown = cd;
	}
	public int getManaCost()
	{
		return mana_cost;
	}
	public int getLvlReq()
	{
		return level_requirement;
	}
}
