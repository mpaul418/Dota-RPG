package classes;

public class RangedCreep extends Monster
{

	public RangedCreep()
	{
		super(100, 100, 40, 90, 10, 0.25, "Ranged Creep", 1, 50);
	}

	public RangedCreep(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}

}
