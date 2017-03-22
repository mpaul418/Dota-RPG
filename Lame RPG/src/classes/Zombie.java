package classes;

public class Zombie extends Monster
{

	public Zombie()
	{
		super(100, 100, 20, 45, 15, 0.15, "Zombie", 2, 50);
	}

	public Zombie(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName,
			int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}

}
