package classes;

public class Hellbear extends Monster
{

	public Hellbear()
	{
		super(375, 400, 60, 85, 17, 0.1, "Hellbear", 2, 75);
	}

	public Hellbear(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName,
			int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}