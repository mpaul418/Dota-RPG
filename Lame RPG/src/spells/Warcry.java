package spells;

import buffs.StatBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class Warcry extends ActiveSpell
{
	int defense_increase = 50;
	int defense_decrease = 30;
	
	public Warcry(Characters c)
	{
		super("Warcry", "Inspire yourself and intimidate enemies with a fearsome cry. Increases your defense by 50/55/60 and lowers enemies' defense by 30/35/40"
				, 10, 3, 4, c, false);
	}

	@Override
	public void cast()
	{
		this.castWithoutTargetMessage();
		
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

	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			defense_increase += 5;
			defense_decrease += 5;
		}
		
		return level_up;
	}
}