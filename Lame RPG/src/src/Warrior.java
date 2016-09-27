package src;

public class Warrior extends Player
{
	 
	public Warrior(String initName)
	{
		super(200, 50, 25, 45, 15, 2, 10, initName);
		HP = 200;
		mana = 50;
		damage = 25;
		attack = 45;
		defense = 25;
		character_class = 2;
		magicDefense = 10;		
	}

}