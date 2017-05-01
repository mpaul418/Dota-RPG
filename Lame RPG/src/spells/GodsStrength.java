package spells;

import buffs.StatBuff;
import classes.Characters;

public class GodsStrength extends ActiveSpell
{
	double damage_multiplier = 1.00;
	
	public GodsStrength(Characters c)
	{
		super("God's Strength", "Channel your rogue strength, gaining 100/150/200% bonus damage for 3 turns", 20, 2, 8, c, false);
		max_spell_level = 3;
	}

	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		System.out.println();
		
		new StatBuff(this.NAME, "Dealing" + ((int) (damage_multiplier * 100)) + "% extra damage.",
				this.CHARACTER, 4, 1, (int) Math.round(damage_multiplier * this.CHARACTER.getDefaultDamage()));		// duration is 4 because first turn is casting the spell
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