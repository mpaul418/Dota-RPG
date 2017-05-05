package spells;

import classes.Characters;
import classes.Zombie;

public class SummonZombies extends ActiveSpell
{

	public SummonZombies(Characters c)
	{
		super("Summon Zombies", "Call three zombies forth from the land of the unliving.", 100, 1, 6, c, false);
		
		max_spell_level = 1;
	}
	
	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		System.out.println(CHARACTER + " summoned three zombies!");
		
		new Zombie();
		new Zombie();	
		new Zombie();
	}
}