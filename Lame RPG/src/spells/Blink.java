package spells;

import buffs.StatBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class Blink extends ActiveSpell
{
	public Blink(Characters c)
	{
		super("Blink", "Teleport and disorient your enemies, reducing your enemies' defence by 30/40/50", 10, 1, 2, c, false);
	}

	@Override
	public void cast()
	{
		this.castWithoutTargetMessage();
		
		int defense_reduction = 30;
		if(this.spell_level == 2)
			defense_reduction = 40;
		else if(this.spell_level >= 3)
			defense_reduction = 50;
		
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
}