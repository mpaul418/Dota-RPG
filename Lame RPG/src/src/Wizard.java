package src;
public class Wizard extends Player
{

	public Wizard(String initName)
	{
		super(95, 120, 10, 65, 20, 4, 25, initName);
		HP = 95;
		mana = 120;
		damage = 10;
		attack = 65;
		defense = 20;
		character_class = 4;
		magicDefense = .25;		
	}

}