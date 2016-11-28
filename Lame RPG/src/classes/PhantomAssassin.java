package classes;

import spells.Blur;
import spells.CoupDeGrace;
import spells.PhantomStrike;
import spells.Spell;
import spells.StiflingDagger;

public class PhantomAssassin extends Player
{
	Spell stifling_dagger = new StiflingDagger(this);
	Spell phantom_strike = new PhantomStrike(this);
	Spell blur = new Blur(this);
	Spell coup_de_grace = new CoupDeGrace(this);
	
	public PhantomAssassin(String initName)
	{
		super(100, 75, 45, 80, 10, 3, .15, initName);
		stifling_dagger.addToSpellbook(this);
		phantom_strike.addToSpellbook(this);
		blur.addToSpellbook(this);
		unlearned_spells.add(coup_de_grace);
	}
	
	public PhantomAssassin(int nHP, int nMana, int nDmg, int nAtk, int nDfs, double nMgDef, String initName)
	{
		super(nHP, nMana, nDmg, nAtk, nDfs, 1, nMgDef, initName);
		stifling_dagger.addToSpellbook(this);
		phantom_strike.addToSpellbook(this);
		blur.addToSpellbook(this);
		unlearned_spells.add(coup_de_grace);
	}
}