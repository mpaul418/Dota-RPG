package src;

public class AntiMage extends Player
{
	
	public AntiMage(String initName)
	{
		super(150, 60, 30, 30, 10, 1, 50, initName);
		HP = 150;
		mana = 60;
		damage = 30;
		attack = 30;
		defense = 15;
		character_class = 1;
		magicDefense = 50;		
	}

}
