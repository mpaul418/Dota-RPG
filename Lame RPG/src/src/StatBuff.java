package src;

public class StatBuff extends Buff
{
	int stat, int_modifier;
	double modifier_amount;
	
	public StatBuff(String n, String dsc, Character c, int d, int s, double a)
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
				CHARACTER.changeAttack(int_modifier);
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
			case 6:
			{
				CHARACTER.changeCritChance(int_modifier);
				break;
			}
			case 7:
			{
				CHARACTER.changeDefaultCritChance(int_modifier);
				CHARACTER.changeCritChance(int_modifier);
				break;
			}
			case 8:
			{
				CHARACTER.changeDefaultCritModifier(modifier_amount);
				CHARACTER.changeCritModifier(modifier_amount);
				break;
			}
			case 9:
			{
				CHARACTER.changeCritModifier(modifier_amount);
				break;
			}
			case 10:
			{
				CHARACTER.changeDefaultDodgeChance(int_modifier);		
				CHARACTER.changeDodgeChance(int_modifier);
				break;
			}
			case 11:
			{
				CHARACTER.changeDodgeChance(int_modifier);
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
					CHARACTER.changeAttack(-int_modifier);
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
				case 6:
				{
					CHARACTER.changeCritChance(-int_modifier);
					break;
				}
				case 7:
				{
					CHARACTER.changeDefaultCritChance(-int_modifier);
					CHARACTER.changeCritChance(-int_modifier);
					break;
				}
				case 8:
				{
					CHARACTER.changeDefaultCritModifier(-modifier_amount);
					CHARACTER.changeCritModifier(-modifier_amount);
					break;
				}
				case 9:
				{
					CHARACTER.changeCritModifier(-modifier_amount);
					break;
				}
				case 10:
				{
					CHARACTER.changeDefaultDodgeChance(-int_modifier);		
					CHARACTER.changeDodgeChance(-int_modifier);
					break;
				}
				case 11:
				{
					CHARACTER.changeDodgeChance(-int_modifier);
					break;
				}
			}
		}	
		super.deletThis();	
	}
}
