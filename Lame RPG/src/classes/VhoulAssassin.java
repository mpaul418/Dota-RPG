package classes;

public class VhoulAssassin extends Monster
{

	public VhoulAssassin()
	{
		super(115, 25, 80, 45, 7, 0.12, "Vhoul Assassin", 1, 30);
	}

	public VhoulAssassin(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}