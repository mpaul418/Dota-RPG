package classes;

public class SatyrBanisher extends Monster
{

	public SatyrBanisher()
	{
		super(125, 65, 45, 150, 18, 0.15, "Satyr Banisher", 1, 35);
	}

	public SatyrBanisher(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}