package src;

public class AttackedBuff extends Buff
{

	public AttackedBuff(String n, String dsc, Character c, int d)
	{
		super(n, dsc, c, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyAttackEffect(Character target, int mana_burn, double burn_multiplier, int crit_chance, double crit_modifier)
	{
		checkForEvasion();

	}

	private void checkForEvasion()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyAttackedEffect(Character c)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void applyEffectOnTurnStart()
	{
		// TODO Auto-generated method stub

	}

}
