package src;

import java.util.Random;

public class AttackedBuff extends Buff
{
	int dodge_chance;
	Random r = new Random();
	
	public AttackedBuff(String n, String dsc, Character c, int d, int dodge_c)
	{
		super(n, dsc, c, d);
		dodge_chance = dodge_c;
		// TODO Auto-generated constructor stub
	}

	public boolean attackEvaded()
	{
		int temp = r.nextInt(100) + 1;
		if(dodge_chance >= temp)
			return true;
		else
			return false;
	}

}
