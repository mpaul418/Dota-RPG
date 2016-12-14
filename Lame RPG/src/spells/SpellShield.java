package spells;

import buffs.StatBuff;
import classes.Characters;

public class SpellShield extends PassiveSpell
{

	public SpellShield(Characters c)
	{
		super("Spell Shield", "Gain a 66% magic resistance bonus", 0, 3, -1, c);
	}
	
	@Override
	public void addToSpellbook(Characters c)
	{
		super.addToSpellbook(c);
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " magic resist.",
				this.CHARACTER, -1, 5, 66.0));
	}

}
