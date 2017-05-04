package classes;

import spells.Shockwave;
import spells.Spell;

public class SatyrTormenter extends Monster
{
	Spell shockwave;
	
	public SatyrTormenter()
	{
		super(525, 150, 160, 75, 34, 0.25, "Satyr Tormenter", 3, 200);
		shockwave = new Shockwave(this);
	}

	public SatyrTormenter(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
		shockwave = new Shockwave(this);
	}
}