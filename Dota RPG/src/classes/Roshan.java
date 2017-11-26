package classes;

import spells.Slam;
import spells.Spell;
import spells.SummonZombies;
import src.Game;

public class Roshan extends Monster
{
	
	Spell slam, summon_zombies;
	
	public Roshan()
	{
		super(2500, 1000, 250, 69, 50, 0.5, "Roshan", 5, 0);
		slam = new Slam(this);
		summon_zombies = new SummonZombies(this);
	}

	public Roshan(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName,
			int lvl, int dth_xp)
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName, lvl, dth_xp);
		slam = new Slam(this);
		summon_zombies = new SummonZombies(this);
	}
	
	@Override
	public void die()
	{
		super.die();
		
		Game.map[Game.current_row][Game.current_column] -= 3;  // since Roshan's number is 4, this sets it to 0 after his death (if no other monsters are summoned)
		Game.game_over = true;
	}
}