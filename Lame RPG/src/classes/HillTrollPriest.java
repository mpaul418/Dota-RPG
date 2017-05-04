package classes;

import spells.Heal;
import spells.Spell;

public class HillTrollPriest extends Monster
{
	Spell heal;
	
	public HillTrollPriest()
	{
		super(190, 125, 50, 40, 5, 0.20, "Hill Troll Priest", 2, 60);
		heal = new Heal(this);
	}

	public HillTrollPriest(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
		heal = new Heal(this);
	}

}
