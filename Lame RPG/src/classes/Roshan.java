package classes;

import spells.Slam;
import spells.Spell;
import spells.SummonZombies;

public class Roshan extends Monster
{
	
	Spell slam, summon_zombies;
	
	public Roshan()
	{
		super(1750, 1000, 135, 69, 50, 0.55, "Roshan", 5, 0);
		slam = new Slam(this);
		summon_zombies = new SummonZombies(this);
	}

	public Roshan(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName,
			int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
		slam = new Slam(this);
	}
}