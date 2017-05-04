package spells;

import buffs.StunBuff;
import classes.Characters;

public class HoofStomp extends ActiveSpell
{

	public HoofStomp(Characters c)
	{
		super("Hoof Stomp", "Slam the ground, dealing 30 magic damage and stunning the target for 2 turns.", 50, 1, 3, c, true);
	
		max_spell_level = 1;
	}

	@Override
	public void cast(Characters target)
	{
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		CHARACTER.dealMagicDamage(30, target);
		new StunBuff(this.NAME, "Stunned.", target, 2);
	}
}