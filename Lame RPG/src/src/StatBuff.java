package src;

public class StatBuff extends Buff
{
	int stat, modifier_amount;
	
	public StatBuff(String n, String dsc, Character c, int d, int s, int a)
	{
		super(n, dsc, c, d); 
		stat = s;
		switch(stat)
		{
			case 1:
			{
				CHARACTER.changeDamage(modifier_amount);
			}
			case 2:
			{
				CHARACTER.changeAttack(modifier_amount);
			}
			case 3:
			{
				CHARACTER.changeDefense(modifier_amount);
			}
			case 4:
			{
				CHARACTER.changeMagicDefense(modifier_amount);
			}
		}
	}
	@Override
	public void deletThis()
	{
		switch(stat)
		{
			case 1:
			{
				CHARACTER.changeDamage(-modifier_amount);
			}
			case 2:
			{
				CHARACTER.changeAttack(-modifier_amount);
			}
			case 3:
			{
				CHARACTER.changeDefense(-modifier_amount);
			}
			case 4:
			{
				CHARACTER.changeMagicDefense(-modifier_amount);
			}
		}
		super.deletThis();
	}
	@Override
	public void applyAttackEffect(Character t)
	{
		//does not have an attack effect
	}

	@Override
	public void applyEffectOnTurnStart()
	{
		//no effect on turn start
	}
	@Override
	public void applyAttackedEffect(Character c)
	{
		//no effect when attacked
	}

}
