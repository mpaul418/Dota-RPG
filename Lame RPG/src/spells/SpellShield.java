package spells;

import buffs.StatBuff;
import classes.Characters;

public class SpellShield extends PassiveSpell
{

	public SpellShield(Characters c)
	{
		super("Spell Shield", "Passively gain a 66% magic resist.", 0, 1, -1, c);
	}
	
	@Override
	public void addToSpellbook(Characters c)
	{
		super.addToSpellbook(c);
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " magic resist.",
				this.CHARACTER, -1, 5, 66.0));
	}

}
