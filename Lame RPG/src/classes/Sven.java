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
		super(1750, 50, 25, 60, 15, 2, .10, initName); 
		storm_hammer = new StormHammer(this);
		great_cleave = new GreatCleave(this);
		warcry = new Warcry(this);
		gods_strength = new GodsStrength(this);
	}
	
	public Sven(int nHP, int nMana, int nDmg, int nAcc, int nDfs, double nMgDef, String initName)
	{
		super(nHP, nMana, nDmg, nAcc, nDfs, 1, nMgDef, initName);
		storm_hammer = new StormHammer(this);
		great_cleave = new GreatCleave(this);
		warcry = new Warcry(this);
		gods_strength = new GodsStrength(this);
	}
	
	@Override
	public String getCharClass()
	{
			return "Sven";
	}
}