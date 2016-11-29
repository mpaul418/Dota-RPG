package spells;

import classes.Characters;
import classes.Monster;
import src.Game;

public class Warcry extends ActiveSpell
{

	public Warcry(Characters c)
	{
		super("Warcry", "Inspire yourself and intimidate enemies with a fearsome cry. Increases your defense and lowers enemies' defense."
				, 10, 1, 4, c, false);
	}

	@Override
	public void cast()
	{
		this.castWithoutTargetMessage();
		this.CHARACTER.changeDefense(45);
		System.out.println(this.CHARACTER + "'s defense increased by 45.");
		for(Monster m : Game.monsters)
		{
			m.changeDefense(-25);
			System.out.println(m + "'s defense was decreased by 25.");
		}
		//TODO FIXME once buff system implemented
	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();
	}

}
