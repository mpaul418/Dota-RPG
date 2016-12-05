package spells;

import buffs.StatBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class Blink extends ActiveSpell
{
	public Blink(Characters c)
	{
		super("Blink", "Teleport and disorient your enemies, reducing your enemies' defence by 40", 10, 1, 2, c, false);
	}

	@Override
	public void cast() //FIXME somehow, casting blink when there is a buff already on enemies creates a passive buff for them and says "[player]'s buff wore off"
	{
		this.castWithoutTargetMessage();
		for(Monster m : Game.monsters)
		{
			m.buffs.add(new StatBuff(NAME, DESCRIPTION, CHARACTER, 3, 3, -40));
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
