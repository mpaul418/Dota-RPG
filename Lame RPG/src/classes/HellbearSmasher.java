package classes;

import spells.Spell;
import spells.ThunderClap;

public class HellbearSmasher extends Monster
{

	Spell thunder_clap;
	
	public HellbearSmasher(String name)
	{
		super(400, 75, 50, 105, 30, 0.15, "Hellbear Smasher", 3, 150);
		thunder_clap = new ThunderClap(this);
	}

	public HellbearSmasher(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
		thunder_clap = new ThunderClap(this);
	}
}