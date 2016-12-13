package classes;

import spells.Spell;

public abstract class Player extends Characters 
{
	/*Character Classes:
	 * 1- AntiMage
	 * 2- Warrior
	 * 3- Assassin
	 * 4- Wizard*/
	
	public int character_class, gold, xp, xp_level_rq;
	
	public Player(int initHP, int initMana, int initDmg, int initAtk, int initDef, int playerClass, double initMagDef, String initName) 
	{
		super(initHP, initMana, initDmg, initAtk, initDef, initMagDef, initName);
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
	private void levelUp()
	{
		this.level++;
		
		System.out.println(this + " just leveled up!!"
				+ "/nHP increased by " + (int)Math.round(0.25 * defaultHP)
				+ "/nMana increased by " + (int)Math.round(0.25 * defaultMana)
				+ "\nDamage increased by " + (int)Math.round(0.25 * defaultDamage));

		defaultHP = (int)Math.round(1.25 * defaultHP);
		HP = defaultHP;
		defaultMana = (int)Math.round(1.25 * defaultMana);
		mana = defaultMana;
		defaultDamage = (int)Math.round(1.25 * defaultDamage);
		damage += (int)Math.round(0.25 * defaultDamage); //FIXME potential bugs with damage increase bugs?
		
		for(Spell s : unlearned_spells)
		{
			if(this.level >= s.getLevelRequirement())
			{
				spellbook.add(s);
				unlearned_spells.remove(s);
				System.out.println(this + " just learned " + s + ".");
			}
		}
		
		xp_level_rq += 25 * level + 5 * (level - 1)^2; //xp increases by 5(x - 1)^2 + 25x every level. 1:100, 2: 155, 3: 250, 4: 395, etc
	}
}