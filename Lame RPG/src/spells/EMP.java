package spells;

import classes.Characters;
import src.Game;

public class EMP extends ActiveSpell
{
	int mana_burn = 50;
	int mana_drained;
	
	public EMP(Characters c)
	{
		super("EMP", "Drain 50/80/110/140 mana from each enemy, dealing magic damage to each enemy equal to 50% of mana drained and restoring mana to you equal to 25% of the total mana drained.", 30, 1, 5, c, false);
	}

	@Override
	public void cast()
	{
		int mana_burned;
		mana_drained = 0;
		
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		for(Characters m : Game.monsters)
		{
			mana_burned = Math.abs(m.changeMana(-mana_burn));
			mana_drained += mana_burned;
			if(mana_burned > 0)
			{
				System.out.println(this.CHARACTER + " burned " + mana_burned + " of " + m + "'s mana.");
				this.CHARACTER.dealMagicDamage((int) Math.round(0.5 * mana_burned), m);
			}
		}
		
		if(mana_drained > 0)
		{
			CHARACTER.changeMana((int) Math.round(0.5 * mana_drained));
			System.out.println(CHARACTER + " drained " + (int) Math.round(0.5 * mana_drained) + " mana.");
		}
	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();
	}

	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
			mana_burn += 30;
		return level_up;
	}
}
