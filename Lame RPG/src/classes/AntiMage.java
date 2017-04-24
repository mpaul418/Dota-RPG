package classes;

import spells.Blink;
import spells.ManaBreak;
import spells.ManaVoid;
import spells.Spell;
import spells.SpellShield;

public class AntiMage extends Player
{
	Spell mana_break, blink, spell_shield, mana_void; 
	
	public AntiMage(String initName)
	{
		super(675, 60, 34, 60, 14, 1, 0.0, initName);
		mana_break = new ManaBreak(this);
		blink = new Blink(this);
		spell_shield = new SpellShield(this);
		mana_void = new ManaVoid(this);
	}
	
	public AntiMage(int nHP, int nMana, int nDmg, int nAcc, int nDfs, double nMgDef, String initName)
	{
		super(nHP, nMana, nDmg, nAcc, nDfs, 1, nMgDef, initName);
		mana_break = new ManaBreak(this);
		blink = new Blink(this);
		spell_shield = new SpellShield(this);
		mana_void = new ManaVoid(this);
	}
	
	@Override
	public String getCharClass()
	{
		return "Anti-Mage";
	}
}