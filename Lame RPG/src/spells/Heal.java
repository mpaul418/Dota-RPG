package spells;

import classes.Characters;

public class Heal extends ActiveSpell
{

	public Heal(Characters c)
	{
		super("Heal", "Restore the energy of a target and heal it for 50 HP.", 25, 1, 1, c, true);

	}

	@Override
	public void cast(Characters target)
	{
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		target.changeHP(50);
		if(target.getHP() + 50 > target.getDefaultHP())
			System.out.println(this.CHARACTER + "healed " + target + " for " + (target.getDefaultHP() - target.getHP()) + ".");
		else
			System.out.println(this.CHARACTER + " healed " + target + " for 50.");
	}
}