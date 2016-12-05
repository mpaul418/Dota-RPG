package spells;

import buffs.StatBuff;
import classes.Characters;

public class PhantomStrike extends ActiveSpell
{

	public PhantomStrike(Characters c)
	{
		super("Phantom Strike", "Jump towards an enemy, attacking them and gaining bonus defense for two turns", 30, 1, 5, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Characters target)
	{
		this.castWithTargetMessage(target);
		this.CHARACTER.buffs.add(new StatBuff(NAME, DESCRIPTION, CHARACTER, 2, 3, 15));
		System.out.println(this.CHARACTER + "'s defense increased by 15.");
		this.CHARACTER.attack(target);
	}

}
