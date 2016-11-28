package spells;

import buffs.AttackBuff;
import classes.Character;

public class ManaBreak extends PassiveSpell
{

	public ManaBreak(Character c)
	{
		super("Mana Break", "Passively burn 25 mana with each attack, dealing 60% of mana burned as damage.", 0, 1, -1, c);
	}

	@Override
	public void addToSpellbook(classes.Character c)
	{
		super.addToSpellbook(c);
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " mana burn.",
			this.CHARACTER, -1, 25, 0.6, 0, 0, 0, 0));
	}
}