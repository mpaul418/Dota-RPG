package spells;

import classes.Characters;

public class Shockwave extends ActiveSpell
{

	public Shockwave(Characters c)
	{
		super("Shockwave", "Send a wave of psychic energy into a unit, dealing 60 magic damage to it.", 50, 1, 6, c, true);
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
		
		CHARACTER.dealMagicDamage(60, target);
		System.out.print(target + " was stunned for one turn.");
	}
}