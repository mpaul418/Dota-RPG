package spells;

import buffs.StatBuff;
import classes.Characters;

public class GhostWalk extends ActiveSpell
{
	int defense_increase = 15;
	public GhostWalk(Characters c) //TODO rework this spell w/scaling, etc
	{
		super("Ghost Walk", "Manipulate the elements around you and become invisible. Increase defense by 15/25/35/45 for 2 turns", 25, 1, 5, c, false);
	}

	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		this.CHARACTER.buffs.add(new StatBuff(NAME, "Defense increased by " + defense_increase, CHARACTER, 2, 3, defense_increase));
		System.out.println(this.CHARACTER + "'s defense increased by " + defense_increase + ".");
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