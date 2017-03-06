package spells;

import classes.Characters;

public class StiflingDagger extends ActiveSpell
{
	int base_damage = 4;
	double damage_multiplier = 0.60;
	
	public StiflingDagger(Characters c)
	{
		super("Stifling Dagger", "Launch a dagger at an enemy, dealing 4/6/8/10 + 60/85/110/135% of your damage as pure damage", 10, 1, 2, c, true);
	}

	@Override
	public void cast(Characters target)
	{
		int pure_damage;
		pure_damage = (int)Math.round(damage_multiplier * (double)this.CHARACTER.getDamage());
		
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		target.changeHP(-pure_damage);
		System.out.println(target + " took " + pure_damage + " pure damage.\n");
	}

	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			base_damage += 2;
			damage_multiplier += 0.25;
		}
		
		return level_up;
	}
}