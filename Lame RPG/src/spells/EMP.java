package spells;

import classes.Characters;
import classes.Monster;
import src.Game;

public class EMP extends ActiveSpell
{

	public EMP(Characters c)
	{
		super("EMP", "Drain the mana of your enemies and deal magic damage to each enemy equal to 50% of its mana lost.", 50, 3, 5, c, false);
	}

	@Override
	public void cast()
	{
		int mana_burned;
		
		this.castWithoutTargetMessage();
		
		for(Monster m : Game.monsters)
		{
			mana_burned = (int) Math.round((double) 0.5 * m.getMana());
			m.changeMana(-mana_burned);
			System.out.println(this.CHARACTER + " burned " + mana_burned + " of " + m + "'s mana.");
			
			this.CHARACTER.dealMagicDamage(mana_burned, m);
		}
	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();
	}

}
