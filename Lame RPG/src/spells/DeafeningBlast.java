package spells;

import buffs.StatBuff;
import classes.Characters;
import src.Game;

public class DeafeningBlast extends ActiveSpell
{
	int magic_damage = 100;
	int accuracy_reduction = 25;
			
	public DeafeningBlast(Characters c)
	{
		super("Deafening Blast", "Send a wave of energy into your enemies, dealing 100/150/200 magic damage and reducing their accuracy by 25/55/85 for 2 turns", 50, 2, 6, c, false);
		max_spell_level = 3; // ultimate abilities only scale to lvl 3
	}

	@Override
	public void cast()
	{	
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		for(Characters m : Game.monsters)
		{
			this.CHARACTER.dealMagicDamage(magic_damage, m);
			
			new StatBuff(this.NAME, "Accuracy reduced by " + accuracy_reduction, m, 3, 2, -accuracy_reduction);
			System.out.println(m + "'s accuracy was reduced by " + accuracy_reduction + ".");
		}
		
		System.out.println();
	}

	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			magic_damage += 50;
			accuracy_reduction += 30;
		}
		return level_up;
	}
}
