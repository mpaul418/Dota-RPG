package src;

public class AntiMage extends Player
{
	Spell mana_break = new ManaBreak(this);
	Spell blink = new Blink(this);
	Spell spell_shield = new SpellShield(this);
	Spell mana_void = new ManaVoid(this);
	
	//TODO add a passive ability class, as well as Mana Break passive
	
	public AntiMage(String initName)
	{
		super(150, 60, 30, 75, 10, 1, 50, 0, initName);
		HP = 150;
		mana = 60;
		damage = 30;
		attack = 75;
		defense = 15;
		character_class = 1;
		magicDefense = .50;	
		spellbook.add(blink);
		possible_spells.add(mana_void);
	}
}