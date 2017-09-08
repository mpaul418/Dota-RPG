package spells;

import classes.Characters;
import classes.Illusion;

public class MantaStyle extends ActiveSpell
{
	int illusion_duration = 2;
	
	public MantaStyle(Characters c)
	{
		super("Manta Style", "Create 2 illusions of yourself that expire in 2/3/4/5 turns", 20, 1, 7, c, false); 
	}

	@Override
	public void cast()
	{
		this.beforeSpellCast();
		this.castWithoutTargetMessage();
		
		for(int i = 1; i <= 2; i++)
		{
			new Illusion(CHARACTER, illusion_duration);
		}
		System.out.println();
	}
	
	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			illusion_duration++;
		}
		
		return level_up;
	}
}
