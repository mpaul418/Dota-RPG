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
	ArrayList<Spell> spellbook = new ArrayList<Spell>();
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
		//TODO need a check after battle. also, add exp system
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