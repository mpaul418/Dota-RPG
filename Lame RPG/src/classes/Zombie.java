package classes;

import buffs.StunBuff;

public class Zombie extends Monster
{

	public Zombie()
	{
		super(100, 100, 20, 45, 15, 0.15, "Zombie", 2, 50);
		
		this.buffs.add(new StunBuff("Summoning Sickness", "Cannot attack for the first turn this enters combat.", this, 1));
	}

	public Zombie(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName,
			int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
		
		this.buffs.add(new StunBuff("Summoning Sickness", "Cannot attack for the first turn this enters combat.", this, 1));
	}
}