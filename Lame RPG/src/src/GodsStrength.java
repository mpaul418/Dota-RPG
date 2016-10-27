package src;

public class GodsStrength extends Spell
{

	public GodsStrength(Character c)
	{
		super("God's Strength", "Channel your rogue strength, gaining bonus damage for 3 turns.", 20, 6, 8, c, false);
	}

	@Override
	public void cast()
	{
		this.castWithoutTargetMessage();
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, "Dealing extra damage.",
				this.CHARACTER, 3, 1, this.CHARACTER.getDefaultDamage()));
	}

	@Override
	public void cast(Character target)
	{
		this.incorrectCastWithTarget();
	}

}
