package src;

public class SunStrike extends ActiveSpell
{
	public SunStrike(Character c)
	{
		super("Sun Strike", "Call down a solar flare on an enemy, dealing large magical damage.", 25, 1, 3, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Character target)
	{
		int magic_damage;
		magic_damage = (CHARACTER.getLevel() * CHARACTER.getLevel()) + 3 * CHARACTER.getLevel() + 35;
		this.castWithTargetMessage(target);
		CHARACTER.dealMagicDamage(magic_damage, target);
	}

}
