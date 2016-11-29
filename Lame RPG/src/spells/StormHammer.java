package spells;

import buffs.StunBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class StormHammer extends ActiveSpell
{

	public StormHammer(Characters c)
	{
		super("Storm Hammer", "unleash a magical gauntlet that deals magic damage and stuns all enemy units for 2 turns.", 20, 1, 5, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Characters target)
	{
		this.castWithTargetMessage(target);
		for(Monster m : Game.monsters)
		{
			CHARACTER.dealMagicDamage(25, m);
			m.buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", m, 2));
		}
	}

}
