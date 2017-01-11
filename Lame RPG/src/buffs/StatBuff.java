package buffs;

import classes.Characters;

public class StatBuff extends Buff
{
	int stat, int_modifier;
	double modifier_amount;
	
	public StatBuff(String n, String dsc, Characters c, int d, int s, double a)
	{
		super(n, dsc, c, d); 
		stat = s;
		modifier_amount = a;
		int_modifier = (int)modifier_amount;
		
		switch(stat)
		{
			case 1:
			{
				CHARACTER.changeDamage(int_modifier);
				break;
			}
			case 2:
			{
				CHARACTER.changeAccuracy(int_modifier);
				break;
			}
			case 3:
			{
				CHARACTER.changeDefense(int_modifier);
				break;
			}
			case 4:
			{
				CHARACTER.changeMagicDefense(int_modifier);
				break;
			}
			case 5:
			{
				CHARACTER.changeDefaultMagicDefense(int_modifier);
				CHARACTER.changeMagicDefense(int_modifier);
				break;
			}
		}
	}
	
	@Override
	public void deletThis()
	{
		if(CHARACTER.buffs.contains(this))
		{
			switch(stat)
			{
				case 1:
				{
					CHARACTER.changeDamage(-int_modifier);
					break;
				}
				case 2:
				{
					CHARACTER.changeAccuracy(-int_modifier);
					break;
				}
				case 3:
				{
					CHARACTER.changeDefense(-int_modifier);
					break;
				}
				case 4:
				{
					CHARACTER.changeMagicDefense(-int_modifier);
					break;
				}
				case 5:
				{
					CHARACTER.changeDefaultMagicDefense(-int_modifier);
					CHARACTER.changeMagicDefense(-int_modifier);
					break;
				}
			}
		}	
		super.deletThis();	
	}
}
