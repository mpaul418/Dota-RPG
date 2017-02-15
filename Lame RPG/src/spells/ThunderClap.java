package spells;

import buffs.StatBuff;
import classes.Characters;

public class ThunderClap extends ActiveSpell
{

	public ThunderClap(Characters c)
	{
		super("Thunder Clap", "Slam your paws together and create a blast affecting a target that deals 25 magic damage and lowers accuracy by 20 for 2 turns.", 25, 3, 3, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Characters target)
	{
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		CHARACTER.dealMagicDamage(25, target);
		target.buffs.add(new StatBuff(this.NAME, "Accuracy reduced by 20", target, 2, 2, -20));
	}
}