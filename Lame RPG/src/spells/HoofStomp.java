package spells;

import buffs.StunBuff;
import classes.Characters;
import src.Game;

public class HoofStomp extends ActiveSpell
{

	public HoofStomp(Characters c)
	{
		super("Hoof Stomp", "Slam the ground, dealing 15 magic damage and stunning the player for 1 turn.", 50, 1, 3, c, false);
	}

	@Override
	public void cast()
	{
		CHARACTER.dealMagicDamage(15, Game.player);
		Game.player.buffs.add(new StunBuff(this.NAME, this.NAME + " stun", Game.player, 1));
		System.out.print(Game.player + " was stunned for one turn.");
	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();
	}
}