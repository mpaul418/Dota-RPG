package spells;

import classes.Characters;
import src.Game;

public class ManaVoid extends ActiveSpell
{
	double mana_multiplier = 0.9;
	
	public ManaVoid(Characters c)
	{
		super("Mana Void", "After bringing enemies to their knees, Anti-Mage punishes them for their use of the arcane arts. "
			+ "Deal 0.9/1.2/1.5 magic damage to all enemies for each of an enemy's missing mana", 40, 2, 10, c, true);
		max_spell_level = 3;
	}

	@Override
	public void cast(Characters target)
	{
		int mana_missing = target.getDefaultMana() - target.getMana();
		
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
	
		for(Characters m : Game.monsters)
		{
			if(spell_level == 1)
				CHARACTER.dealMagicDamage((int) Math.round(mana_multiplier * mana_missing), m);
		}
	}
	
	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
			mana_multiplier += 0.3;
		
		return level_up;
	}
}