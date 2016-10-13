package src;

public class Sven extends Player
{
	 
	public Sven(String initName)
	{
		super(200, 50, 25, 75, 15, 2, 10, initName);
		HP = 200;
		mana = 50;
		damage = 25;
		attack = 75;
		defense = 25;
		character_class = 2;
		magicDefense = .10;		
	}

}