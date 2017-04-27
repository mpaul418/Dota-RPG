package spells;

import classes.Characters;
import classes.Illusion;

public class MantaStyle extends ActiveSpell
{
	int illusions_created = 1;
	
	public MantaStyle(Characters c)
	{
		super("Manta Style", "Create 1/2/3/4 illusion(s) of yourself that expire in 4 turns", 15, 1, 7, c, false); 
	}

	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		for(int i = 1; i <= illusions_created; i++)
		{
			new Illusion(CHARACTER);
		}
		System.out.println();
	}
	
	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			illusions_created++;
		}
		
		return level_up;
	}
}
