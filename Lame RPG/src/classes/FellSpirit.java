package classes;

public class FellSpirit extends Monster
{

	public FellSpirit(String name)
	{
		super(150, 15, 32, 65, 10, 0.1, "Fell Spirit", 1, 25);
	}

	public FellSpirit(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}