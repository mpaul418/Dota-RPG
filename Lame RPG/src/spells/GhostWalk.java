package spells;

import buffs.StatBuff;
import classes.Characters;

public class GhostWalk extends ActiveSpell
{

	public GhostWalk(Characters c)
	{
		super("Ghost Walk", "Manipulate the elements around you and become invisible. Increase defense by 70", 45, 2, 9, c, false);
	}

	@Override
	public void cast()
	{
		this.CHARACTER.buffs.add(new StatBuff(NAME, "Defense increased by 70", CHARACTER, 2, 3, 70));
		System.out.println(this.CHARACTER + "'s defense increased by 70.");
	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();

	}

}
