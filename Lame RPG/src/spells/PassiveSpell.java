package spells;

import classes.Characters;

public abstract class PassiveSpell extends Spell
{

	public PassiveSpell(String tempname, String dsc, int manacost, int level_rq, int cd, Characters c)
	{
		super(tempname, dsc, manacost, level_rq, cd, c, false);
	}
	
	public boolean isCastable()
	{
		return false;
	}
}
