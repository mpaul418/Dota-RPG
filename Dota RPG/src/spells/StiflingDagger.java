package spells;

import java.util.Random;

import classes.Characters;

public class StiflingDagger extends ActiveSpell
{
	int base_damage = 8;
	double damage_multiplier = 0.60;
	Random r = new Random();
	
	public StiflingDagger(Characters c)
	{
		super("Stifling Dagger", "Launch a dagger at an enemy, dealing 8/12/16/20 + 60/85/110/135% of your damage as pure damage. Coup de Gr�ce can trigger on this spell", 10, 1, 2, c, true);
	}

	@Override
	public void cast(Characters target)
	{
		int pure_damage;
		
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		pure_damage = (int)Math.round(damage_multiplier * (double)this.CHARACTER.getDamage());
		
		for(Spell s : CHARACTER.spellbook)
			if(s instanceof CoupDeGrace)
			{
				int crit_roll = r.nextInt(101);
				if(crit_roll < 15)
				{
					System.out.print("Critical hit! ");
				
					pure_damage = (int) Math.round(pure_damage * ((CoupDeGrace) s).crit_multiplier);
					
					break;
				}
			}
		
		target.changeHP(-pure_damage);
		System.out.println(target + " took " + pure_damage + " pure damage.\n");
	}

	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			base_damage += 4;
			damage_multiplier += 0.25;
		}
		
		return level_up;
	}
}