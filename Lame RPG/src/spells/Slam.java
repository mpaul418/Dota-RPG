package spells;

import classes.Characters;
import src.Game;

public class Slam extends ActiveSpell
{

	public Slam(Characters c)
	{
		super("Slam", "Slam the ground, dealing 50 + 10 times the turn number as magic damage to the target.", 50, 1, 2, c, true);
		
		max_spell_level = 1;
	}
	
	@Override
	public void cast(Characters target)
	{
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		CHARACTER.dealMagicDamage(50 + (10 * Game.getTurnNumber()), target);
	}
}