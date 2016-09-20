package src;

public class Assassin extends Player
{

	public Assassin(String initName)
	{
		super(100, 75, 45, 40, 10, 3, 5, initName);
		HP = 100;
		mana = 75;
		damage = 45;
		attack = 40;
		defense = 10;
		character_class = 3;
		magicDefense = 5;		
	}

}
