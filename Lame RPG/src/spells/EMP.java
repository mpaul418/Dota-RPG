package spells;

import classes.Characters;
import classes.Monster;
import src.Game;

public class EMP extends ActiveSpell
{
	int mana_burn = 50;
	
	public EMP(Characters c)
	{
		super("EMP", "Drain 50/80/110 mana of all enemies and deal magic damage to each enemy equal to 50% of its mana lost.", 50, 1, 5, c, false);
	}

	@Override
	public void cast()
	{
		int mana_burned;
		
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		for(Monster m : Game.monsters)
		{
			mana_burned = m.changeMana(-mana_burn);
			System.out.println(this.CHARACTER + " burned " + mana_burned + " of " + m + "'s mana.");
			
			this.CHARACTER.dealMagicDamage(mana_burned, m);
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
