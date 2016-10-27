package src;

import java.util.ArrayList;

public class Player extends Character 
{
	/*Character Classes:
	 * 1- AntiMage
	 * 2- Warrior
	 * 3- Assassin
	 * 4- Wizard
	*/
	//TODO add spellbook to Character instead to allow monsters to cast spells?
	ArrayList<Spell> spellbook = new ArrayList<Spell>();
	ArrayList<Spell> possible_spells = new ArrayList<Spell>();
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
		
		for(Spell s : possible_spells)
		{
			if(this.level >= s.getLevelRequirement())
			{
				spellbook.add(s);
				possible_spells.remove(s);
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
	/*public void cast(int spell_choice)
	{
		//TODO
		//FIXME
		if(spellbook.get(spell_choice).isTargeted())
		{
			int target = -1;
			
			System.out.println("Who would you like to target?");
			//TODO add a back option
			for(int i = 0; i < Game.monsters.size(); i++)
			{
				System.out.println((i + 1) + ": " + Game.monsters.get(i).getName()
						+"(" + Game.monsters.get(i).getHP() + "/" + Game.monsters.get(i).getMaxHP() + ")");
			}
			target = Game.getNumberFrom(1, Game.monsters.size()) - 1;
			
			spellbook.get(spell_choice).cast(Game.monsters.get(target));
		}
		else
			spellbook.get(spell_choice).cast();
			
	}*/

}