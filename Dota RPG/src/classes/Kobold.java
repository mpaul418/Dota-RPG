package classes;

public class Kobold extends Monster
{

	public Kobold()
	{
		super(120, 200, 40, 95, 5, 0.05, "Kobold", 1, 15);
	}

	public Kobold(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName,
			int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}