package src;

public class GhostWalk extends ActiveSpell
{

	public GhostWalk(Character c)
	{
		super("Ghost Walk", "Manipulate the elements around you and become invisible. Increase defense by 70.", 45, 1, 9, c, false);
	}

	@Override
	public void cast()
	{
		this.CHARACTER.changeDefense(70);
		System.out.println(this.CHARACTER + "'s defense increased by 70.");
	}

	@Override
	public void cast(Character target)
	{
		this.incorrectCastWithTarget();

	}

}
