package src;

public class Blur extends PassiveSpell
{

	public Blur(Character c)
	{
		super("Blur", "Passively gain a 50% chance to evade physical attacks.", 0, 1, -1, c);
	}

	@Override
	public void cast()
	{
		// not castable
	}

	@Override
	public void cast(Character target)
	{
		// not castable
	}
	
	@Override
	public void addToSpellbook(Player p)
	{
		super.addToSpellbook(p);
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " evasion bonus.",
				this.CHARACTER, -1, 9, 50));
	}

}
