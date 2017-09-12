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
		super(575, 275, 55, 75, 11, 4, .25, initName);
		sun_strike = new SunStrike(this);
		emp = new EMP(this);
		ghost_walk = new GhostWalk(this);
		deafening_blast = new DeafeningBlast(this);
	}

	public Invoker(int nHP, int nMana, int nDmg, int nAcc, int nDfs, double nMgDef, String initName)
	{
		super(nHP, nMana, nDmg, nAcc, nDfs, 1, nMgDef, initName);
		sun_strike = new SunStrike(this);
		emp = new EMP(this);
		ghost_walk = new GhostWalk(this);
		deafening_blast = new DeafeningBlast(this);
	}
	
	@Override
	public String getCharClass()
	{
			return "Invoker";
	}
}