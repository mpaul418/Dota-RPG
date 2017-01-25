package classes;

import java.util.Random;
import src.Game;

public class Monster extends Characters 
{
	static Random rand = new Random(); 
	protected int death_xp;
	
	public Monster(String name)
	{
		super(rand.nextInt(Game.player.getDefaultHP() + 1) + 50, (rand.nextInt(91) + 20 * Game.player.getLevel()), (rand.nextInt(26) + 5 * Game.player.getLevel() + 5), 
		 (rand.nextInt(101) + 7 * Game.player.getLevel()), (rand.nextInt(21) + 5 * Game.player.getLevel()), (double) (rand.nextInt(26) / 100.0), name);
		
		level = Game.player.getLevel();
		death_xp = 25 * this.getLevel();
	}
	public Monster(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName, int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName);

		level = lvl;
		death_xp = dth_xp;
	}
	
	@Override
	public void die()
	{
		super.die();
		Game.monsters.remove(this);
		Game.player.changeXP(death_xp);
		
		Game.map[Game.current_row][Game.current_column] -= 1;  // removes the monster from the room so if player reenters this room, more monsters will not spawn.
	}
}