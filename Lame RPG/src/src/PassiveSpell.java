package src;

public class PassiveSpell extends Spell
{

	public PassiveSpell(String tempname, String dsc, int manacost, int level_rq, int cd, Character c)
	{
		super(tempname, dsc, manacost, level_rq, cd, c, false);
	}
}
