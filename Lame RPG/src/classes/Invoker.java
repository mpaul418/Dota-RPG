package classes;

import spells.DeafeningBlast;
import spells.EMP;
import spells.GhostWalk;
import spells.Spell;
import spells.SunStrike;

public class Invoker extends Player
{
	Spell sun_strike, emp, ghost_walk, deafening_blast;
	

	public Invoker(String initName)
	{
		super(95, 120, 10, 65, 20, 4, .25, initName);
		//HP = 95;
		//mana = 120;
		//damage = 10;
		//attack = 65;
		//defense = 20;
		//character_class = 4;
		//magicDefense = .25;		
		sun_strike = new SunStrike(this);
		emp = new EMP(this);
		ghost_walk = new GhostWalk(this);
		deafening_blast = new DeafeningBlast(this);
		//sun_strike.addToSpellbook(this);
		//emp.addToSpellbook(this);
		//ghost_walk.addToSpellbook(this);
		//unlearned_spells.add(deafening_blast);
	}

	public Invoker(int nHP, int nMana, int nDmg, int nAtk, int nDfs, double nMgDef, String initName)
	{
		super(nHP, nMana, nDmg, nAtk, nDfs, 1, nMgDef, initName);
		sun_strike = new SunStrike(this);
		emp = new EMP(this);
		ghost_walk = new GhostWalk(this);
		deafening_blast = new DeafeningBlast(this);
		//sun_strike.addToSpellbook(this);
		//emp.addToSpellbook(this);
		//ghost_walk.addToSpellbook(this);
		//unlearned_spells.add(deafening_blast);
	}
	
	@Override
	public String getCharClass()
	{
			return "Invoker";
	}
}