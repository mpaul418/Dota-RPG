package classes;

public class Treant extends Monster
{
	
	public Treant()
	{
		super(350, 0, 20, 35, 10, 0.15, "Treant", 1, 20);
	}

	public Treant(int initHP, int initMana, int initDmg, int initAcc, int initDef,
			double initMagDef, String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}