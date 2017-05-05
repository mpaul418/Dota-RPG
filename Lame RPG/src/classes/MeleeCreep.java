package classes;

public class MeleeCreep extends Monster
{
	
	public MeleeCreep()
	{
		super(500, 0, 55, 65, 20, 0.0, "Melee Creep", 1, 35);
	}

	public MeleeCreep(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}