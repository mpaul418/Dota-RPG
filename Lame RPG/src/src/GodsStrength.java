package src;

public class GodsStrength extends Spell
{

	public GodsStrength(Character c)
	{
		super("God's Strength", "Channel your rogue strength, gaining bonus attack damage for 3 turns.", 20, 6, 8, c, false);
		this.CHARACTER.buffs.add(new StatBuff(this.NAME + " (damage)", "Dealing extra damage.", this.CHARACTER, 3));
	}

	@Override
	public void cast()
	{
		//TODO once buff system implemented
	}

	@Override
	public void cast(Character target)
	{
		this.incorrectCastWithTarget();
	}

}
