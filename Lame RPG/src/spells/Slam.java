package spells;

import buffs.StunBuff;
import classes.Characters;

public class Slam extends ActiveSpell
{

	public Slam(Characters c)
	{
		super("Slam", "Slam the ground, dealing 25 + 6 times the turn number as magic damage to the target.", manacost, level_rq, cd, c, targetable);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void cast(Characters target)
	{
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		CHARACTER.dealMagicDamage(25, target);
		//TODO target.buffs.add(new StunBuff(this.NAME, this.NAME + " stun", target, 1));
	}
}
