package classes;

public class SatyrMindstealer extends Monster
{

	public SatyrMindstealer()
	{
		super(270, 450, 100, 85, 21, 0.15, "Satyr Mindstealer", 2, 100);
	}

	public SatyrMindstealer(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef,
			String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
	}
}