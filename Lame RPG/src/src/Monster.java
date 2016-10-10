package src;

public class Monster extends Character 
{
	protected int monster_type;
	public Monster(int initHP, int initMana, int initDmg, int initAtk, int initDef, int initMonster_type, double initMagDef, String initName)
	{
		super(initHP, initMana, initDmg, initAtk, initDef, initMagDef, initName);
		monster_type = initMonster_type;
	}

}