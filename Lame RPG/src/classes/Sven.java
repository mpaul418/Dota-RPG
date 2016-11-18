package classes;

import spells.GodsStrength;
import spells.Spell;
import spells.StormHammer;
import spells.Warcry;

public class Sven extends Player
{
	Spell storm_hammer = new StormHammer(this);
	Spell warcry = new Warcry(this);
	Spell gods_strength = new GodsStrength(this);
	
	public Sven(String initName) //TODO finish initializing and adding spells to spellbook
	{
		super(200, 50, 25, 75, 15, 2, .10, initName); 
		//HP = 200;
		//mana = 50;
		//damage = 25;
		//attack = 75;
		//defense = 25;
		//character_class = 2;
		//magicDefense = .10;	
		spellbook.add(storm_hammer);
		spellbook.add(warcry);
		spellbook.add(gods_strength);
	}

}