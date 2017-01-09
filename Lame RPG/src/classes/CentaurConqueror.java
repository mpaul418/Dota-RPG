package classes;

public class CentaurConqueror extends Monster
{

	public CentaurConqueror(String name)
	{
		super(550, 100, 30, 95, 40, 0.50, "Centaur Conqueror", 3, 250);
	}

	public CentaurConqueror(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}