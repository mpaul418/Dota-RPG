package src;
public class Invoker extends Player
{
	Spell sun_strike = new SunStrike(this);
	Spell emp = new EMP(this);

	public Invoker(String initName)
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