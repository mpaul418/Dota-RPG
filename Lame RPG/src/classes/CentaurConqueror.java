package classes;

import spells.HoofStomp;
import spells.Spell;

public class CentaurConqueror extends Monster
{
	Spell hoof_stomp;
	
	public CentaurConqueror(String name)
	{
		super(550, 100, 30, 95, 40, 0.50, "Centaur Conqueror", 3, 250);
		hoof_stomp = new HoofStomp(this);
	}

	public CentaurConqueror(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
		hoof_stomp = new HoofStomp(this);
	}
}