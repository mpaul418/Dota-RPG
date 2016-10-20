package src;
public class StormHammer extends Spell
{

	public StormHammer(Character c)
	{
		super("Storm Hammer", "unleash a magical gauntlet that deals magic damage and stuns all enemy units.", 20, 1, 5, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Character target)
	{
		this.castWithTargetMessage(target);
		//TODO add buff class- then apply 1 turn stun + damage to all enemies
	}

}
