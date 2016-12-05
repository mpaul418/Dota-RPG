package spells;

import buffs.AttackBuff;
import classes.Characters;

public class ManaBreak extends PassiveSpell
{

	public ManaBreak(Characters c)
	{
		super("Mana Break", "Burn 25 mana with each attack, dealing 60% of mana burned as damage", 0, 1, -1, c);
	}

	@Override
	public void addToSpellbook(classes.Characters c)
	{
		super.addToSpellbook(c);
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " mana burn.",
			this.CHARACTER, -1, 25, 0.6, 0, 0, 0, 0));
	}
}