package spells;

import buffs.StatBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class DeafeningBlast extends ActiveSpell
{

	public DeafeningBlast(Characters c)
	{
		super("Deafening Blast", "Send a wave of energy into your enemies, heavily reducing their attack", 50, 6, 7, c, false);
		
	}

	@Override
	public void cast()
	{
		int attack_reduction;
		this.castWithoutTargetMessage();
		for(Monster m : Game.monsters)
		{
			attack_reduction = Game.rand.nextInt(31) + 40;
			if(attack_reduction > m.getAttack())
				attack_reduction = m.getAttack();
			
			m.buffs.add(new StatBuff(this.NAME, this.NAME + " attack debuff.", m, 2, 2, -attack_reduction));
			
			System.out.println(m + "'s attack was reduced by " + attack_reduction + ".");
		}
		
		this.afterSpellCast();
	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();
	}

}
