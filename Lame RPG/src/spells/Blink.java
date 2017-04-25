package spells;

import buffs.StatBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class Blink extends ActiveSpell
{
	int defense_reduction = 12;
	
	public Blink(Characters c)
	{
		super("Blink", "Teleport and disorient your enemies, reducing your enemies' defence by 12/16/20/24 for 4 turns", 10, 1, 6, c, false);
	}

	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		for(Monster m : Game.monsters)
		{
			m.buffs.add(new StatBuff(NAME, "Defense reduced by " + defense_reduction, m, 4, 3, -defense_reduction));
			System.out.println(m.getName() + "'s defense was reduced by " + defense_reduction + "!");
		}
		
		System.out.println();
	}
	
	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
			defense_reduction += 4;
		
		return level_up;
	}
}