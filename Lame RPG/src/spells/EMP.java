package spells;

import classes.Characters;
import classes.Monster;
import src.Game;

public class EMP extends ActiveSpell
{
	int mana_burn = 50;
	int mana_restored = 0;
	
	public EMP(Characters c)
	{
		super("EMP", "Drain 50/80/110/140 mana of all enemies, dealing magic damage to each enemy equal to 50% of mana drained and restoring mana to you equal to the total mana drained.", 50, 1, 5, c, false);
	}

	@Override
	public void cast()
	{
		int mana_burned;
		
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		for(Monster m : Game.monsters)
		{
			mana_burned = Math.abs(m.changeMana(-mana_burn));
			mana_restored += mana_burned;
			if(mana_burned > 0)
			{
				System.out.println(this.CHARACTER + " burned " + mana_burned + " of " + m + "'s mana.");
				this.CHARACTER.dealMagicDamage((int) Math.round(0.5 * mana_burned), m);
			}
		}
		
		if(mana_restored > 0)
		{
			CHARACTER.changeMana(mana_restored);
			System.out.println(CHARACTER + " drained " + mana_restored + " mana.");
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
