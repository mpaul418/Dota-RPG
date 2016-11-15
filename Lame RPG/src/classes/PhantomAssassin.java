package classes;

import spells.Spell;
import spells.StiflingDagger;

public class PhantomAssassin extends Player
{
	Spell stifling_dagger = new StiflingDagger(this);
	
	public PhantomAssassin(String initName)
	{
		super(100, 75, 45, 80, 10, 3, 5, 50, initName); //TODO remove dodge chance from this class and from all characters
		HP = 75;
		mana = 75;
		damage = 45;
		attack = 80;
		defense = 10;
		character_class = 3;
		magicDefense = .15;		
	}

}