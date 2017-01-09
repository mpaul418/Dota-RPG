package classes;

public class SatyrTormenter extends Monster
{

	public SatyrTormenter(String name)
	{
		super(350, 150, 80, 120, 34, 0.25, "Satyr Tormenter", 3, 200);
	}

	public SatyrTormenter(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}