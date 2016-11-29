package classes;

import spells.GodsStrength;
import spells.GreatCleave;
import spells.Spell;
import spells.StormHammer;
import spells.Warcry;

public class Sven extends Player
{
	Spell storm_hammer, great_cleave, warcry, gods_strength;
	
	public Sven(String initName)
	{
		super(200, 50, 25, 75, 15, 2, .10, initName); 
		storm_hammer = new StormHammer(this);
		great_cleave = new GreatCleave(this);
		warcry = new Warcry(this);
		gods_strength = new GodsStrength(this);
		//storm_hammer.addToSpellbook(this);
		//great_cleave.addToSpellbook(this);
		//warcry.addToSpellbook(this);
		//unlearned_spells.add(gods_strength);
	}
	
	public Sven(int nHP, int nMana, int nDmg, int nAtk, int nDfs, double nMgDef, String initName)
	{
		super(nHP, nMana, nDmg, nAtk, nDfs, 1, nMgDef, initName);
		storm_hammer = new StormHammer(this);
		great_cleave = new GreatCleave(this);
		warcry = new Warcry(this);
		gods_strength = new GodsStrength(this);
		//storm_hammer.addToSpellbook(this);
		//great_cleave.addToSpellbook(this);
		//warcry.addToSpellbook(this);
		//unlearned_spells.add(gods_strength);
	}
}