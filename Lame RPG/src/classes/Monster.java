package classes;

import java.util.Random;
import src.Game;

public class Monster extends Characters 
{
	static Random rand = new Random(); 
	protected int monster_type;
	
	public Monster(String name)
	{
		super(rand.nextInt(Game.player.getDefaultHP() + 1) + 50, (rand.nextInt(71) + 40), (rand.nextInt(31) + 5), 
		 (rand.nextInt(101) + 10), (rand.nextInt(21) + 5), (double) (rand.nextInt(26) / 100.0), name);
		
		monster_type = 1; //TODO temporary- prbably not necessary
	}
	public Monster(int initHP, int initMana, int initDmg, int initAtk, int initDef, int initMonster_type, double initMagDef, String initName)
	{
		super(initHP, initMana, initDmg, initAtk, initDef, initMagDef, initName);
		monster_type = initMonster_type;
	}

}