package src;

public class PassiveSpell extends Spell
{

	public PassiveSpell(String tempname, String dsc, int manacost, int level_rq, int cd, Character c)
	{
		super(tempname, dsc, manacost, level_rq, cd, c, false);
	}

	@Override
	public void cast()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void cast(Character target)
	{
		// TODO Auto-generated method stub

	}

}
