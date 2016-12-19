package spells;

import classes.Characters;
import classes.Monster;
import src.Game;

public class ManaVoid extends ActiveSpell
{

	public ManaVoid(Characters c)
	{
		super("Mana Void", "After bringing enemies to their knees, Anti-Mage punishes them for their use of the arcane arts. "
			+ "Deal 0.6/0.8/1.0 magic damage to all enemies for each of an enemy's missing mana", 40, 4, 10, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Characters target)
	{
		int mana_missing = target.getDefaultMana() - target.getMana();
		this.castWithTargetMessage(target);
	
		for(Monster m : Game.monsters)
		{
			if(spell_level == 1)
				CHARACTER.dealMagicDamage((int) Math.round(0.6 * mana_missing), m);
			else if(spell_level == 2)
				CHARACTER.dealMagicDamage((int) Math.round(0.8 * mana_missing), m);
			else if(spell_level >= 3)
				CHARACTER.dealMagicDamage(mana_missing, m);
		}
	}
}