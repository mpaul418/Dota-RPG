package classes;

public class VhoulAssassin extends Monster
{

	public VhoulAssassin(String name)
	{
		super(200, 25, 40, 125, 7, 0.12, "Vhoul Assassin", 1, 30);
	}

	public VhoulAssassin(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}