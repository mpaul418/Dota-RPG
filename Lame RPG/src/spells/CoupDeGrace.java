package spells;

import buffs.AttackBuff;
import classes.Characters;

public class CoupDeGrace extends PassiveSpell
{

	public CoupDeGrace(Characters c)
	{
		super("Coup de Grâce", "Passively gain a 15% chance to land a critical hit, dealing 275% damage.", 0, 6, -1, c);
	}

	@Override
	public void addToSpellbook(Characters c)
	{
		super.addToSpellbook(c);
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " crit chance bonus.",
				this.CHARACTER, -1, 0, 0, 15, 0.75, 0, 0.0));//TODO remove crit and dodge bonuses from stat buffs- they are attack/attacked buffs
	}
}
