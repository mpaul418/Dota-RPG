package spells;

import classes.Character;

public class StiflingDagger extends ActiveSpell
{

	public StiflingDagger(Character c)
	{
		super("Stifling Dagger", "Launch a dagger at an enemy, dealing a percentage of your damage as pure damage.", 10, 1, 2, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Character target)
	{
		int pure_damage;
		pure_damage = (int)Math.round((double)this.CHARACTER.getDamage() / 2.0);
		this.castWithTargetMessage(target);
		target.changeHP(-pure_damage);
		System.out.println(target + " took " + pure_damage + " damage./n");
	}

}
