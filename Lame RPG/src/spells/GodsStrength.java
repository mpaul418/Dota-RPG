package spells;

import buffs.StatBuff;
import classes.Characters;

public class GodsStrength extends ActiveSpell
{

	public GodsStrength(Characters c)
	{
		super("God's Strength", "Channel your rogue strength, gaining bonus damage for 3 turns", 20, 6, 8, c, false);
	}

	@Override
	public void cast()
	{
		this.castWithoutTargetMessage();
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, "Dealing extra damage.",
				this.CHARACTER, 3, 1, this.CHARACTER.getDefaultDamage()));
	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();
	}

}
