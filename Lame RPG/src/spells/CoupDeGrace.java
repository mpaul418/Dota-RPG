package spells;

import buffs.AttackBuff;
import classes.Character;
import classes.Player;

public class CoupDeGrace extends PassiveSpell
{

	public CoupDeGrace(Character c)
	{
		super("Coup de Gr�ce", "Passively gain a 15% chance to land a critical hit, dealing 275% damage.", 0, 6, -1, c);
	}

	@Override
	public void addToSpellbook(Player p)
	{
		super.addToSpellbook(p);
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " crit chance bonus.",
				this.CHARACTER, -1, 0, 0, 15, 0.75));//TODO remove crit and dodge bonuses from stat buffs- they are attack/attacked buffs
	}
}
