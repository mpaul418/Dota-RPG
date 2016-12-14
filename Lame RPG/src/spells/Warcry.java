package spells;

import buffs.StatBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class Warcry extends ActiveSpell
{
	int defense_increase, defense_decrease;
	
	public Warcry(Characters c)
	{
		super("Warcry", "Inspire yourself and intimidate enemies with a fearsome cry. Increases your defense and lowers enemies' defense"
				, 10, 3, 4, c, false);
	}

	@Override
	public void cast()
	{
		this.castWithoutTargetMessage();
		
		defense_increase = 45 + 5 * CHARACTER.getLevel(); // increase is 5x + 45
		defense_decrease = 25 + 5 * CHARACTER.getLevel(); // decrease is 5x + 25
		
		this.CHARACTER.buffs.add(new StatBuff(NAME, "Defense increased by " + defense_increase, CHARACTER, 2, 3, 45));
		System.out.println(this.CHARACTER + "'s defense increased by " + defense_increase + ".");
		for(Monster m : Game.monsters)
		{
			m.buffs.add(new StatBuff(NAME, "Defense decreased by " + defense_decrease, m, 2, 3, -25));
			System.out.println(m + "'s defense was decreased by " + defense_decrease + ".");
		}
	}

	@Override
	public void cast(Characters target)
	{
		this.incorrectCastWithTarget();
	}

}
