package spells;

import buffs.StatBuff;
import classes.Characters;
import src.Game;

public class ThunderClap extends ActiveSpell
{

	public ThunderClap(Characters c)
	{
		super("Thunder Clap", "Slam your paws together and create a blast affecting a target that deals 120 magic damage and lowers accuracy by 20 for 2 turns.", 25, 3, 3, c, true);
		
		max_spell_level = 1;
	}

	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		for(Characters c : Game.players)
		{
			CHARACTER.dealMagicDamage(60, c);
			new StatBuff(this.NAME, "Accuracy reduced by 20", c, 3, 2, -20);
		}
	}
}