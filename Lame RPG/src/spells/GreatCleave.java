package spells;

import buffs.AttackBuff;
import classes.Character;

public class GreatCleave extends PassiveSpell
{

	public GreatCleave(Character c)
	{
		super("Great Cleave", "Passively deal 50% of your damage to two random enemies other than the targeted one when you attack.", 0, 1, -1, c);
	}
//TODO
	@Override
	public void addToSpellbook(Character c)
	{
		super.addToSpellbook(c);
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " cleave.",
				this.CHARACTER, -1, 0, 0, 0, 0, 2, 0.5));
	}
}
