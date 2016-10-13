package src;

public class DeafeningBlast extends Spell
{

	public DeafeningBlast(Character c)
	{
		super("Deafening Blast", "Send a wave of energy into your enemies, heaving reducing their attack.", 50, 6, 7, c, false);
		
	}

	@Override
	public void cast()
	{
		int attack_reduction;
		this.castWithoutTargetMessage();
		for(Monster m : Game.monsters)
		{
			attack_reduction = Game.rand.nextInt(31) + 40;
			if(attack_reduction > m.getAttack())
				attack_reduction = m.getAttack();
			
			m.changeAttack(-attack_reduction);
			
			System.out.println(m + "'s attack was reduced by " + attack_reduction + ".");
		}
	}

	@Override
	public void cast(Character target)
	{
		this.incorrectCastWithTarget();
	}

}
