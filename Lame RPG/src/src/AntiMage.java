package src;

import java.util.ArrayList;
import abilities.Spell;

public class AntiMage extends Player
{
	ArrayList<Spell> spellbook = new ArrayList<Spell>();
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
		spellbook.add(new Blink());
	}

}