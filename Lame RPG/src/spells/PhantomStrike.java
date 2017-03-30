package spells;

import buffs.StatBuff;
import classes.Characters;

public class PhantomStrike extends ActiveSpell
{
	int defense_increase = 4;
	
	public PhantomStrike(Characters c)
	{
		super("Phantom Strike", "Jump towards an enemy, attacking them and gaining 4/12/20/28 bonus defense for two turns", 30, 1, 5, c, true);
	}

	@Override
	public void cast(Characters target)
	{
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		this.CHARACTER.buffs.add(new StatBuff(NAME, "Gaining " + defense_increase + " bonus defense.", CHARACTER, 2, 3, defense_increase));
		System.out.println(this.CHARACTER + "'s defense increased by " + defense_increase + ".");
		this.CHARACTER.attack(target);
	}
	
	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
			defense_increase += 8;
		return level_up;
	}
}