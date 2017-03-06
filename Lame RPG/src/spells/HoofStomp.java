package spells;

import buffs.StunBuff;
import classes.Characters;

public class HoofStomp extends ActiveSpell
{

	public HoofStomp(Characters c)
	{
		super("Hoof Stomp", "Slam the ground, dealing 15 magic damage and stunning the target for 1 turn.", 50, 1, 3, c, true);
	}

	@Override
	public void cast(Characters target)
	{
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		CHARACTER.dealMagicDamage(15, target);
		target.buffs.add(new StunBuff(this.NAME, this.NAME + " stun", target, 1));
	}
}