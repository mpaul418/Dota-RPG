package buffs;

import java.util.ArrayList;
import java.util.Random;

import classes.Characters;
import classes.Monster;
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

	public int applyManaBurn(Characters c)
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
				this.CHARACTER.damage(c, damage_to_deal);
				
				System.out.println(this.CHARACTER + " burned " + mana_to_burn + " of " 
								   + c + "'s mana and dealt " + damage_to_deal + " damage.");
				
				return damage_to_deal;
			}
		}
		
		return 0;
	}
	
	public int applyCleave(Characters c, int damage) //TODO make this apply to random targets rather than going in a line starting at index 0
	{
		if(cleave_enemies_hit > 0)
		{
			int enemies_to_cleave = cleave_enemies_hit;
			int cleave_damage = (int) Math.round(cleave_percentage * damage);
			int i = 0;
			
			ArrayList<Monster> cleave_targets = new ArrayList<Monster>();
			
			for(Monster m : Game.monsters)
			{
				if(m != c)
					cleave_targets.add(m);
			}
			while(enemies_to_cleave > 0)
			{
				int target = r.nextInt(cleave_targets.size());
				
				CHARACTER.damage(cleave_targets.get(target), cleave_damage);
				System.out.println(CHARACTER + " cleaved " + cleave_targets.get(target) + " for " + cleave_damage + " damage.");
				cleave_targets.remove(target);
				enemies_to_cleave--;
			}
			//while(enemies_to_cleave > 0 && i < Game.monsters.size())
			//{
			//	if(Game.monsters.get(i) != c)
			//	{
			//		this.CHARACTER.damage(Game.monsters.get(i), cleave_damage);
			//		System.out.println(this.CHARACTER + " cleaved " + Game.monsters.get(i) + " for " + ((int) Math.round(cleave_percentage * damage)) + " damage.");
			//		enemies_to_cleave--;
			//	}
			//	
			//	i++;
			//}
			
			return cleave_damage * i; // cleave times the amount of enemies it hit
		}
		
		return 0;
	}
}