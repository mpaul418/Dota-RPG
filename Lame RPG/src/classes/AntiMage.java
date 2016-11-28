package classes;

import spells.Blink;
import spells.ManaBreak;
import spells.ManaVoid;
import spells.Spell;
import spells.SpellShield;

public class AntiMage extends Player
{
	Spell mana_break = new ManaBreak(this);
	Spell blink = new Blink(this);
	Spell spell_shield = new SpellShield(this);
	Spell mana_void = new ManaVoid(this);
	
	public AntiMage(String initName)
	{
		super(150, 60, 30, 75, 15, 1, 0.0, initName);
		//HP = 150;
		//mana = 60;
		//damage = 30;
		//attack = 75;
		//defense = 15;
		//character_class = 1;
		//magicDefense = 10;
		mana_break.addToSpellbook(this);//TODO
		blink.addToSpellbook(this);
		spell_shield.addToSpellbook(this);
		unlearned_spells.add(mana_void);
	}
	
	public AntiMage(int nHP, int nMana, int nDmg, int nAtk, int nDfs, double nMgDef, String initName)
	{
		super(nHP, nMana, nDmg, nAtk, nDfs, 1, nMgDef, initName);
		//HP = nHP;
		//mana = nMana;
		//damage = 30;
		//attack = 75;
		//defense = 15;
		//character_class = 1;
		//magicDefense = 10;
		mana_break.addToSpellbook(this);
		blink.addToSpellbook(this);
		spell_shield.addToSpellbook(this);
		unlearned_spells.add(mana_void);
	}
}