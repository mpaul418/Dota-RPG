package buffs;

import java.util.Random;

import classes.Character;

public class AttackBuff extends Buff
{
	int mana_burn, crit_chance;
	double mana_burn_multiplier, crit_modifier;
	Random r = new Random();
	
	public AttackBuff(String n, String dsc, Character c, int d, int manaburn, double burnmultiplier, int critchance, double critmodifier)
	{
		super(n, dsc, c, d);
		
		mana_burn = manaburn;
		mana_burn_multiplier = burnmultiplier;
		crit_chance = critchance;
		crit_modifier = critmodifier;
	}

	public int getManaBurn()
	{
		return mana_burn;
	}
	
	public double getManaBurnMultiplier()
	{
		return mana_burn_multiplier;
	}
	
	public int getCritChance()
	{
		return crit_chance;
	}
	
	public double getCritModifier()
	{
		return crit_modifier;
	}
	public boolean criticalHit()
	{
		if(crit_chance > 0)
		{
			int crit_roll = r.nextInt(100) + 1;
			
			if(crit_chance <= crit_roll)
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public void applyManaBurn(Character c)
	{
		if(mana_burn > 0)
		{
			int mana_to_burn, damage_to_deal;
			if(mana_burn <= c.getMana())
				mana_to_burn = mana_burn;
			else
				mana_to_burn = c.getMana();
			
			damage_to_deal = (int) Math.round(mana_burn_multiplier) * mana_to_burn;
			
			c.changeMana(-mana_to_burn);
			c.damage(c, damage_to_deal);
			
			System.out.println(this.CHARACTER + " burned " + mana_to_burn + " of " 
							   + c + "'s mana and dealt " + damage_to_deal + " damage.");
		}
	}

	//public void applyStunEffect(Character c)
	//{
		// TODO
	//}

	/*@Override
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
		
	}*/
}