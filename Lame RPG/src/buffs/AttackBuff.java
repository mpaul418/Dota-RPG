package buffs;

import java.util.Random;

import classes.Characters;
import src.Game;

public class AttackBuff extends Buff
{
	int mana_burn, crit_chance, cleave_enemies_hit;
	double mana_burn_multiplier, crit_modifier, cleave_percentage;
	Random r = new Random();
	
	public AttackBuff(String n, String dsc, Characters c, int d, int manaburn, double burnmultiplier, 
			int critchance, double critmodifier, int cleaveenemieshit, double cleavepercentage)
	{
		super(n, dsc, c, d);
		
		mana_burn = manaburn;
		mana_burn_multiplier = burnmultiplier;
		crit_chance = critchance;
		crit_modifier = critmodifier;
		cleave_enemies_hit = cleaveenemieshit;
		cleave_percentage = cleavepercentage;
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

	public void applyManaBurn(Characters c)
	{
		if(mana_burn > 0)
		{
			if(c.getMana() > 0)
			{
				int mana_to_burn, damage_to_deal;
				if(c.getMana() - mana_burn > 0)
					mana_to_burn = mana_burn;
				else
					mana_to_burn = c.getMana();
				
				damage_to_deal = (int) Math.round((mana_burn_multiplier) * mana_to_burn);
				
				c.changeMana(-mana_to_burn);
				c.damage(c, damage_to_deal);
				
				System.out.println(this.CHARACTER + " burned " + mana_to_burn + " of " 
								   + c + "'s mana and dealt " + damage_to_deal + " damage.");
			}
		}
	}
	
	public void applyCleave(Characters c, int damage)
	{
		if(cleave_enemies_hit > 0)
		{
			int enemies_to_cleave = cleave_enemies_hit;
			int i = 0;
			while(enemies_to_cleave > 0 && i < Game.monsters.size())
			{
				if(Game.monsters.get(i) != c && Game.monsters.get(i).isAlive())
				{
					this.CHARACTER.damage(Game.monsters.get(i), (int) Math.round(cleave_percentage * damage));
					System.out.println(this.CHARACTER + " cleaved " + Game.monsters.get(i) + " for " + ((int) Math.round(cleave_percentage * damage)) + " damage.");
				}
				
				i++;
			}
		}
	}
}