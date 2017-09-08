package classes;

import spells.Blink;
import spells.ManaBreak;
import spells.ManaVoid;
import spells.Spell;
import spells.MantaStyle;

public class AntiMage extends Player
{
	Spell mana_break, blink, manta_style, mana_void; 
	
	public AntiMage(String initName)
	{
		super(675, 60, 45, 85, 14, 1, 0.66, initName);
		mana_break = new ManaBreak(this);
		blink = new Blink(this);
		manta_style = new MantaStyle(this);
		mana_void = new ManaVoid(this);
	}
	
	public AntiMage(int nHP, int nMana, int nDmg, int nAcc, int nDfs, double nMgDef, String initName)
	{
		super(nHP, nMana, nDmg, nAcc, nDfs, 1, nMgDef, initName);
		mana_break = new ManaBreak(this);
		blink = new Blink(this);
		manta_style = new MantaStyle(this);
		mana_void = new ManaVoid(this);
	}
	
	@Override
	public String getCharClass()
	{
		return "Anti-Mage";
	}
}