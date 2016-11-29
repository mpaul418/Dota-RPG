package spells;

import classes.Characters;
import classes.Monster;
import src.Game;

public class Blink extends ActiveSpell
{
	public Blink(Characters c)
	{
		super("Blink", "Teleport and disorient your enemies, reducing your enemies' defence by 40.", 10, 1, 2, c, false);
	}

	@Override
	public void cast()
	{
		this.castWithoutTargetMessage();
		for(Monster m : Game.monsters)
		{
			m.changeDefense(40);
			System.out.println(m.getName() + "'s defense was reduced by 40!");
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
