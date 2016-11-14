package src;

public abstract class ActiveSpell extends Spell
{

	public ActiveSpell(String tempname, String dsc, int manacost, int level_rq, int cd, Character c, boolean targetable)
	{
		super(tempname, dsc, manacost, level_rq, cd, c, targetable);
		// TODO Auto-generated constructor stub
	}
	
	public void incorrectCastWithoutTarget()
	{
		System.out.println("You need a target for this spell! "
				+ "\nSpell Name: " + this.NAME 
				+ "\nSpell Owner: " + this.CHARACTER + ".");
		//THIS SPELL SHOULD HAVE A TARGET
	}
	public void incorrectCastWithTarget()
	{
		System.out.println("This spell should not have a target!");
		//THIS SPELL SHOULD NOT HAVE A TARGET
		cast();	
	}
	public void castWithTargetMessage(Character c)
	{
		System.out.println(this.CHARACTER + " cast " + this + " on " + c + ".");
	}
	public void castWithoutTargetMessage()
	{
		System.out.println(this.CHARACTER + " cast " + this + ".");
	}

	public abstract void cast();
	public abstract void cast(Character target);
}
