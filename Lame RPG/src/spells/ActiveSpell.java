package spells;

import classes.Characters;

public abstract class ActiveSpell extends Spell
{

	public ActiveSpell(String tempname, String dsc, int manacost, int level_rq, int cd, Characters c, boolean targetable)
	{
		super(tempname, dsc, manacost, level_rq, cd, c, targetable);
	}

	public void incorrectCastWithoutTarget()
	{
		System.out.println("You need a target for this spell! "
				+ "\nSpell Name: " + this.NAME 
				+ "\nSpell Owner: " + this.CHARACTER + ".");
		//THIS SPELL SHOULD HAVE A TARGET
	}

	public void incorrectCastWithTarget()
	{
		System.out.println("This spell should not have a target!");
		//THIS SPELL SHOULD NOT HAVE A TARGET
		cast();
	}

	public void castWithTargetMessage(Characters c)
	{
		System.out.println(this.CHARACTER + " cast " + this + " on " + c + ".");
	}

	public void castWithoutTargetMessage()
	{
		System.out.println(this.CHARACTER + " cast " + this + ".");
	}

	public boolean isCastable()
	{
		if(CHARACTER.getLevel() >= LEVEL_REQUIREMENT && CHARACTER.getMana() >= MANA_COST && current_cooldown == 0)
			return true;
		else
			return false;
	}

	public void cast()
	{
		incorrectCastWithoutTarget();
	}
	public void cast(Characters target)
	{
		incorrectCastWithTarget();
	}
}
