package src;

public class StunBuff extends Buff
{
	public StunBuff(String n, String dsc, Character c, int d)
	{
		super(n, dsc, c, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyAttackEffect(Character c)
	{
		//does not do anything on attack
	}

	@Override
	public void applyAttackedEffect(Character c)
	{
		//no effect on attacked
	}

	@Override
	public void applyEffectOnTurnStart()
	{
		//no effect on turn start
	}

}
