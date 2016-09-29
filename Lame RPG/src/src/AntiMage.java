package src;

import java.util.ArrayList;

public class AntiMage extends Player
{
	ArrayList<Spell> spellbook = new ArrayList<Spell>();
	Blink blink = new Blink(this);
	
	public AntiMage(String initName)
	{
		super(150, 60, 30, 45, 10, 1, 50, initName);
		HP = 150;
		mana = 60;
		damage = 30;
		attack = 45;
		defense = 15;
		character_class = 1;
		magicDefense = 50;	
		spellbook.add(blink);
	}
	//FIXME add this to player class?
	public void cast(int spell_choice)
	{
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
		{
			//TODO cast the spell if it is not targeted (ex. Blink)
			//TODO
			//TODO
		}
	}

}