package classes;

public class Hellbear extends Monster
{

	public Hellbear(String name)
	{
		super(250, 25, 32, 100, 17, 0.1, "Hellbear", 3, 75);
	}

	public Hellbear(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName,
			int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}