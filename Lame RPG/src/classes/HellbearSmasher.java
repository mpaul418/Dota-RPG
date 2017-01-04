package classes;

public class HellbearSmasher extends Monster
{

	public HellbearSmasher(String name)
	{
		super(400, 75, 50, 105, 30, 0.15, "Hellbear Smasher", 3, 150);
	}

	public HellbearSmasher(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}