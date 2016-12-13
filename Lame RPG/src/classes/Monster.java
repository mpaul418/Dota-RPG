package classes;

import java.util.Random;
import src.Game;

public class Monster extends Characters 
{
	static Random rand = new Random(); 
	protected int monster_type, death_xp;
	
	public Monster(String name)
	{
		super(rand.nextInt(Game.player.getDefaultHP() + 1) + 50, (rand.nextInt(91) + 20 * Game.player.getLevel()), (rand.nextInt(26) + 5 * Game.player.getLevel() + 5), 
		 (rand.nextInt(101) + 7 * Game.player.getLevel()), (rand.nextInt(21) + 5 * Game.player.getLevel()), (double) (rand.nextInt(26) / 100.0), name);
		
		monster_type = 1; //TODO temporary- prbably not necessary
		level = Game.player.getLevel();
		death_xp = 25 * this.getLevel();
	}
	public Monster(int initHP, int initMana, int initDmg, int initAtk, int initDef, int initMonster_type, double initMagDef, String initName, int lvl)
	{
		super(initHP, initMana, initDmg, initAtk, initDef, initMagDef, initName);
		monster_type = initMonster_type;
		level = lvl;
	}
	
	@Override
	public void die()
	{
		super.die();
		Game.monsters.remove(this);
		Game.player.changeXP(death_xp);
	}
}