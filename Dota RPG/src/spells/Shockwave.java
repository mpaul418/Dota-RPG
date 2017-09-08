package spells;

import classes.Characters;

public class Shockwave extends ActiveSpell
{

	public Shockwave(Characters c)
	{
		super("Shockwave", "Send a wave of psychic energy into a unit, dealing 200 magic damage to it.", 50, 1, 6, c, true);
		
		max_spell_level = 1;
	}

	@Override
	public void cast(Characters target)
	{
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		CHARACTER.dealMagicDamage(200, target);
	}
}