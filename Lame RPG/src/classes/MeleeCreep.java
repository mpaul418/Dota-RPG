package classes;

public class MeleeCreep extends Monster
{
	
	public MeleeCreep(String name)
	{
		super(215, 0, 28, 85, 15, 0.0, "Melee Creep", 1, 35);
	}

	public MeleeCreep(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}