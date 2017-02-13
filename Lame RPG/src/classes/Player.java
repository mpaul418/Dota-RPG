package classes;

import java.util.ArrayList;

import spells.Spell;
import src.Game;

public abstract class Player extends Characters 
{
	/*Character Classes:
	 * 1- AntiMage
	 * 2- Warrior
	 * 3- Assassin
	 * 4- Wizard*/
	
	public int character_class, gold, xp, xp_level_rq;
	
	public Player(int initHP, int initMana, int initDmg, int initAcc, int initDef, int playerClass, double initMagDef, String initName) 
	{
		super(initHP, initMana, initDmg, initAcc, initDef, initMagDef, initName);
		character_class = playerClass;
		gold = 0;
		xp = 0;
		xp_level_rq = 100;
	}
	
	public abstract String getCharClass();
	
	public void changeXP(int death_xp)
	{
		xp += death_xp;
		System.out.println(this + " gained " + death_xp + " experience points.");
		if(xp >= xp_level_rq)
			levelUp();
		else
			System.out.println(this + " needs " + (xp_level_rq - xp) + " more experience points to level up.");
	}
	private void levelUp() // TODO ask the player what skill(s) to level up
	{
		int levelup_points = 3;
		boolean can_level_spells = false;
		
		this.level++;
		xp_level_rq += 25 * level + 5 * (level - 1)^2; // xp increases by 5(x - 1)^2 + 25x every level. 1:100, 2: 155, 3: 250, 4: 395, etc
		
		System.out.println(this + " just leveled up!!"
				+  "\nHP increased by " + (int)Math.round(0.25 * defaultHP)
				+ ".\nMana increased by " + (int)Math.round(0.25 * defaultMana)
				+ ".\nDamage increased by " + (int)Math.round(0.25 * defaultDamage) + ".");

		defaultHP = (int)Math.round(1.25 * defaultHP);
		HP = defaultHP;
		defaultMana = (int)Math.round(1.25 * defaultMana);
		mana = defaultMana;
		defaultDamage = (int)Math.round(1.25 * defaultDamage);
		damage += (int)Math.round(0.25 * defaultDamage);
		
		while(checkForSpellsToLevel() && levelup_points > 0) //TODO implement system where you can choose to level spells
		{
			System.out.println(levelup_points + " levelup points to spend. Which spell would you like to level?");
			 //TODO print spells that can be levelled, then choose one and level it
			levelup_points--;
		}
		//for(Spell s : this.spellbook)
		//	s.levelUp();

		int temp = 0;
		while(temp < unlearned_spells.size())
		{
			if(this.level >= unlearned_spells.get(temp).getLevelRequirement())
			{
				unlearned_spells.get(temp).addToSpellbook();
			}
			else
				temp += 1;
		}
	}
	
	private boolean checkForSpellsToLevel()
	{
		for(Spell s : spellbook)
		{
			if(s.getLevel() < s.getMaxLevel())
				return true;
		}
		return false;
	}
	
	@Override
	public void die()
	{
		super.die();
		Game.game_over = true;
	}
}