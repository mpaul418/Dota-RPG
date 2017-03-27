package classes;

import spells.Blur;
import spells.CoupDeGrace;
import spells.PhantomStrike;
import spells.Spell;
import spells.StiflingDagger;

public class PhantomAssassin extends Player
{
	Spell stifling_dagger, phantom_strike, blur, coup_de_grace;
	
	public PhantomAssassin(String initName)
	{
		super(600, 75, 45, 65, 12, 3, .15, initName);
		stifling_dagger = new StiflingDagger(this);
		phantom_strike = new PhantomStrike(this);
		blur = new Blur(this);
		coup_de_grace = new CoupDeGrace(this);
	}
	
	public PhantomAssassin(int nHP, int nMana, int nDmg, int nAcc, int nDfs, double nMgDef, String initName)
	{
		super(nHP, nMana, nDmg, nAcc, nDfs, 1, nMgDef, initName);
		stifling_dagger = new StiflingDagger(this);
		phantom_strike = new PhantomStrike(this);
		blur = new Blur(this);
		coup_de_grace = new CoupDeGrace(this);
	}
	
	@Override
	public String getCharClass()
	{
			return "Phantom Assassin";
	}
}