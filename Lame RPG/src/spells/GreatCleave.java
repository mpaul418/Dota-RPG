package spells;

import buffs.AttackBuff;
import classes.Characters;

public class GreatCleave extends PassiveSpell
{

	public GreatCleave(Characters c)
	{
		super("Great Cleave", "Deal 50% of your damage to two random enemies other than your attack target", 0, 1, -1, c);
	}

	@Override
	public void addToSpellbook(Characters c)
	{
		super.addToSpellbook(c);
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " cleave.",
				this.CHARACTER, -1, 0, 0, 0, 0, 2, 0.5));
	}
}
