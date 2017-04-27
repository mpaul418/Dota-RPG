package spells;

import buffs.StatBuff;
import classes.Characters;

public class GhostWalk extends ActiveSpell
{
	int defense_increase = 20;
	public GhostWalk(Characters c) 
	{
		super("Ghost Walk", "Manipulate the elements around you and become invisible. Increase defense by 20/30/40/50 for 3 turns", 50, 1, 5, c, false);
	}

	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		new StatBuff(NAME, "Defense increased by " + defense_increase, CHARACTER, 3, 3, defense_increase);
		System.out.println(this.CHARACTER + "'s defense increased by " + defense_increase + ".\n");
	}

	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
			defense_increase += 10;
		return level_up;
	}
}