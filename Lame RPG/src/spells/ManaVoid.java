package spells;

import classes.Characters;
import classes.Monster;
import src.Game;

public class ManaVoid extends ActiveSpell
{

	public ManaVoid(Characters c)
	{
		super("Mana Void", "After bringing enemies to their knees, Anti-Mage punishes them for their use of the arcane arts. "
			+ "Deal magic damage to all enemies equal to one enemy's missing mana", 40, 6, 10, c, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Characters target)
	{
		// TODO Auto-generated method stub
		int mana_missing = target.getDefaultMana() - target.getMana();
		this.castWithTargetMessage(target);
		for(Monster m : Game.monsters)
		{
			CHARACTER.dealMagicDamage(mana_missing, m);
		}
	}

}
