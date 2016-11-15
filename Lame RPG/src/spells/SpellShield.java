package spells;

import buffs.StatBuff;
import classes.Character;
import classes.Player;

public class SpellShield extends PassiveSpell
{

	public SpellShield(Character c)
	{
		super("Spell Shield", "Passively gain a 66% magic resist.", 0, 1, -1, c);
	}
	
	@Override
	public void addToSpellbook(Player p)
	{
		super.addToSpellbook(p);
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " magic resist.",
				this.CHARACTER, -1, 5, 66.0));
	}

}
