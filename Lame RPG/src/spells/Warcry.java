package spells;

import buffs.StatBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class Warcry extends ActiveSpell
{

	public Warcry(Characters c)
	{
		super("Warcry", "Inspire yourself and intimidate enemies with a fearsome cry. Increases your defense and lowers enemies' defense"
				, 10, 1, 4, c, false);
	}

	@Override
	public void cast()
	{
		this.castWithoutTargetMessage();
		this.CHARACTER.buffs.add(new StatBuff(NAME, "Warcry defense gain", CHARACTER, 2, 3, 45));
		System.out.println(this.CHARACTER + "'s defense increased by 45.");
		for(Monster m : Game.monsters)
		{
			m.buffs.add(new StatBuff(NAME, "Warcry defense loss", m, 2, 3, -25));
			System.out.println(m + "'s defense was decreased by 25.");
		}
	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();
	}

}
