package spells;

import buffs.StatBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class DeafeningBlast extends ActiveSpell
{
	int magic_damage = 50;
	int accuracy_reduction = 25;
	public DeafeningBlast(Characters c)
	{
		super("Deafening Blast", "Send a wave of energy into your enemies, dealing 50/75/100 magic damage and reducing their accuracy by 25/55/85 for 2 turns", 50, 2, 6, c, false);
	}

	@Override
	public void cast()
	{	
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		for(Monster m : Game.monsters)
		{
			this.CHARACTER.dealMagicDamage(magic_damage, m);
			
			m.buffs.add(new StatBuff(this.NAME, "Accuracy reduced by " + accuracy_reduction, m, 2, 2, -accuracy_reduction));
			System.out.println(m + "'s accuracy was reduced by " + accuracy_reduction + ".");
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
		{
			magic_damage += 25;
			accuracy_reduction += 30;
		}
		return level_up;
	}
}
