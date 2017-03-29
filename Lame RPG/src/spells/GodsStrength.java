package spells;

import buffs.StatBuff;
import classes.Characters;

public class GodsStrength extends ActiveSpell
{
	double damage_multiplier = 0.50;
	
	public GodsStrength(Characters c)
	{
		super("God's Strength", "Channel your rogue strength, gaining 50/100/150% bonus damage for 3 turns", 20, 2, 8, c, false);
		max_spell_level = 3;
	}

	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		System.out.println();
		
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, "Dealing extra damage.",
				this.CHARACTER, 3, 1, (int) Math.round(damage_multiplier * this.CHARACTER.getDefaultDamage())));
	}

	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
			damage_multiplier += 0.50;
		
		return level_up;
	}
}