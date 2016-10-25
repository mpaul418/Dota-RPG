package src;

public class StatBuff extends Buff
{
	public StatBuff(String n, String dsc, Character c, int d)
	{
		super(n, dsc, c, d); 
		// TODO Auto-generated constructor stub
	}
//TODO find a way to pass the variable 
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

}
