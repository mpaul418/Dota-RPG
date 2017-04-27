package spells;

import buffs.StatBuff;
import classes.Characters;
import src.Game;

public class Warcry extends ActiveSpell
{
	int defense_increase = 10;
	int defense_decrease = 5;
	
	public Warcry(Characters c)
	{
		super("Warcry", "Inspire yourself and intimidate enemies with a fearsome cry. Increases your defense by 10/15/20/25 and lowers enemies' defense by 5/10/15/20 for 3 turns"
				, 15, 1, 4, c, false);
	}

	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		new StatBuff(NAME, "Defense increased by " + defense_increase, CHARACTER, 3, 3, defense_increase);
		System.out.println(this.CHARACTER + "'s defense increased by " + defense_increase + ".");
		for(Characters m : Game.monsters)
		{
			new StatBuff(NAME, "Defense decreased by " + defense_decrease, m, 3, 3, -defense_decrease);
			System.out.println(m + "'s defense was decreased by " + defense_decrease + ".");
		}
		System.out.println();
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