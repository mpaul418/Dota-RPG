package src;

public class PhantomAssassin extends Player
{

	public PhantomAssassin(String initName)
	{
		super(100, 75, 45, 80, 10, 3, 5, initName);
		HP = 75;
		mana = 75;
		damage = 45;
		attack = 80;
		defense = 10;
		character_class = 3;
		magicDefense = .15;		
	}

}