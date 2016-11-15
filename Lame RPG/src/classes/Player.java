package classes;

import java.util.ArrayList;

import spells.Spell;

public class Player extends Character 
{
	/*Character Classes:
	 * 1- AntiMage
	 * 2- Warrior
	 * 3- Assassin
	 * 4- Wizard
	*/
	//TODO add spellbook to Character instead to allow monsters to cast spells?
	public ArrayList<Spell> spellbook = new ArrayList<Spell>();
	public ArrayList<Spell> unlearned_spells = new ArrayList<Spell>();
	public int character_class;
	public int gold;
	public Player(int initHP, int initMana, int initDmg, int initAtk, int initDef, int playerClass, int initMagDef, int initDodge, String initName) 
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
		this.level++;
		
		System.out.println(this + " just leveled up!!"
				+ "/nHP increased by " + (int)Math.round(0.25 * defaultHP)
				+ "/nMana increased by " + (int)Math.round(0.25 * defaultMana) + ".");

		defaultHP = (int)Math.round(1.25 * defaultHP);
		HP = defaultHP;
		defaultMana = (int)Math.round(1.25 * defaultMana);
		mana = defaultMana;
		defaultDamage = (int)Math.round(1.25 * defaultDamage);
		damage = defaultDamage;
		
		for(Spell s : unlearned_spells)
		{
			if(this.level >= s.getLevelRequirement())
			{
				spellbook.add(s);
				unlearned_spells.remove(s);
				System.out.println(this + " just learned " + s + ".");
			}
		}
	}
	public boolean allSpellsUncastable()
	{
		int uncastable_spells = 0;
		for(Spell s : spellbook)
		{
			if(!s.isCastable())
				uncastable_spells++;
		}
		if(uncastable_spells == spellbook.size())
			return true;
		else
			return false;
	}
}