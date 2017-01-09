package classes;

public class CentaurCourser extends Monster
{

	public CentaurCourser(String name)
	{
		super(400, 15, 20, 80, 25, 0.30, "Centaur Courser", 2, 65);
	}

	public CentaurCourser(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}