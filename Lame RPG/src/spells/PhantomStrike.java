package spells;

import buffs.StatBuff;
import classes.Characters;

public class PhantomStrike extends ActiveSpell
{
	int defense_increase = 10;
	
	public PhantomStrike(Characters c)
	{
		super("Phantom Strike", "Jump towards an enemy, attacking them and gaining 10/20/30 bonus defense for two turns", 30, 3, 5, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Characters target)
	{
		this.castWithTargetMessage(target);
		this.CHARACTER.buffs.add(new StatBuff(NAME, DESCRIPTION, CHARACTER, 2, 3, defense_increase));
		System.out.println(this.CHARACTER + "'s defense increased by " + defense_increase + ".");
		this.CHARACTER.attack(target);
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