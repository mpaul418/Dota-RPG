package spells;

import classes.Characters;

public class ThunderClap extends ActiveSpell
{

	public ThunderClap(Characters c)
	{
		super("Thunder Clap", "Slam your paws together and create a blast affecting all enemies that deals 25 magic damage and lowers accuracy by 20.", manacost, level_rq, cd, c, false);
	}

	@Override
	public void cast()
	{
		//TODO
		//TODO
		//TODO

	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();
	}
}