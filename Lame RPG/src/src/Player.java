package src;

public class Player extends Character 
{
	/*Character Classes:
	 * 1- AntiMage
	 * 2- Warrior
	 * 3- Assassin
	 * 4- Wizard
	*/
	protected int character_class;
	protected int gold;
	public Player(int initHP, int initMana, int initDmg, int initAtk, int initDef, int playerClass, int initMagDef, String initName) 
	{
		super(initHP, initMana, initDmg, initAtk, initDef, initMagDef, initName);
		character_class = playerClass;
		gold = 0;
	}
	public int getCharClass()
	{
		return character_class;
	}
	public void levelUp()
	{
		//TODO
	}

}