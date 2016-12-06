package spells;

import classes.Characters;

public class StiflingDagger extends ActiveSpell
{

	public StiflingDagger(Characters c)
	{
		super("Stifling Dagger", "Launch a dagger at an enemy, dealing 75% of your damage as pure damage", 10, 1, 2, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Characters target)
	{
		int pure_damage;
		pure_damage = (int)Math.round((double)this.CHARACTER.getDamage() * 3.0 / 4.0);
		this.castWithTargetMessage(target);
		target.changeHP(-pure_damage);
		System.out.println(target + " took " + pure_damage + " pure damage.\n");
	}

}
