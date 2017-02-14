package buffs;

import classes.Characters;

public class StatBuff extends Buff
{
	int stat, int_modifier;
	double modifier_amount, modified_amount;
	
	public StatBuff(String n, String dsc, Characters c, int d, int s, double a)
	{
		super(n, dsc, c, d); 
		stat = s;
		modifier_amount = a;
		modified_amount = modifier_amount;
		int_modifier = (int)modifier_amount;
		
		switch(stat)
		{
			case 1:
			{
				modified_amount = CHARACTER.changeDamage(int_modifier);
				break;
			}
			case 2:
			{
				modified_amount = CHARACTER.changeAccuracy(int_modifier);
				break;
			}
			case 3:
			{
				modified_amount = CHARACTER.changeDefense(int_modifier);
				break;
			}
			case 4:
			{
				modified_amount = CHARACTER.changeMagicDefense(int_modifier);
				break;
			}
			//case 5:
			//{
			//	modified_amount = CHARACTER.changeDefaultMagicDefense(int_modifier);
			//	break;
			//}
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
					CHARACTER.changeDamage((int) -modified_amount);
					break;
				}
				case 2:
				{
					CHARACTER.changeAccuracy((int) -modified_amount);
					break;
				}
				case 3:
				{
					CHARACTER.changeDefense((int) -modified_amount);
					break;
				}
				case 4:
				{
					CHARACTER.changeMagicDefense((int) -modified_amount);
					break;
				}
				//case 5:
				//{
				//	CHARACTER.changeDefaultMagicDefense((int) -modified_amount);
				//	break;
				//}
			}
		}	
		super.deletThis();	
	}
	
	public void deletThisNoNotifier() // for when spells level up and you don't want it to say it has worn off
	{
		if(CHARACTER.buffs.contains(this))
		{
			switch(stat)
			{
				case 1:
				{
					CHARACTER.changeDamage((int) -modified_amount);
					break;
				}
				case 2:
				{
					CHARACTER.changeAccuracy((int) -modified_amount);
					break;
				}
				case 3:
				{
					CHARACTER.changeDefense((int) -modified_amount);
					break;
				}
				case 4:
				{
					CHARACTER.changeMagicDefense((int) -modified_amount);
					break;
				}
			}
		}
		CHARACTER.buffs.remove(this);
	}
}
