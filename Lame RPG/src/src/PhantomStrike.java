package src;

public class PhantomStrike extends Spell
{

	public PhantomStrike(Character c)
	{
		super("Phantom Strike", "Jump towards an enemy, attacking them and gaining bonus defense.", 30, 1, 5, c, true);
		// TODO Auto-generated constructor stub
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
		this.CHARACTER.changeDefense(15);
		System.out.println(this.CHARACTER + "'s defense increased by 15.");
		this.CHARACTER.attack(target);
	}

}
