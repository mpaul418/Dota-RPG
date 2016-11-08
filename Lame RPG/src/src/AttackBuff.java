package src;

public class AttackBuff extends Buff
{
	int mana_burn, crit_chance;
	double mana_burn_multiplier, crit_modifier;
	
	public AttackBuff(String n, String dsc, Character c, int d, int manaburn, double burnmultiplier, int critchance, double critmodifier)
	{
		super(n, dsc, c, d);
		
		mana_burn = manaburn;
		mana_burn_multiplier = burnmultiplier;
		crit_chance = critchance;
		crit_modifier = critmodifier;
	}

	@Override
	public void applyAttackEffect(Character target, boolean already_critted)
	{
		int crit_roll;
		if(!already_critted)
		{
			crit_roll = r.nextInt(100) + 1;
			if(crit_chance <= crit_roll)
				critical_hit = true;
			else
				critical_hit = false;
		}
		
	}

	@Override
	public void applyAttackedEffect(Character c)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void applyEffectOnTurnStart()
	{
		// TODO Auto-generated method stub

	}

}
