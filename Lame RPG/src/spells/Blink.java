package spells;

import buffs.StatBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class Blink extends ActiveSpell
{
	int defense_reduction = 30;
	
	public Blink(Characters c)
	{
		super("Blink", "Teleport and disorient your enemies, reducing your enemies' defence by 30/40/50", 10, 1, 2, c, false);
	}

	@Override
	public void cast()
	{
		this.castWithoutTargetMessage();
		
		for(Monster m : Game.monsters)
		{
			m.buffs.add(new StatBuff(NAME, "Defence reduced by " + defense_reduction, m, 3, 3, -defense_reduction));
			System.out.println(m.getName() + "'s defense was reduced by " + defense_reduction + "!");
		}
		
		this.afterSpellCast();
		
		System.out.println();
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
			defense_reduction += 10;
		
		return level_up;
	}
}