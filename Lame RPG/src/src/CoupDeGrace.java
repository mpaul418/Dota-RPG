package src;

public class CoupDeGrace extends PassiveSpell
{

	public CoupDeGrace(Character c)
	{
		//TODO
		super("Coup de Grâce", "Passively gain a 15% chance to land a critical hit, dealing 275% damage.", 0, 6, -1, c);
	}

	@Override
	public void cast()
	{
		// uncastable
	}

	@Override
	public void cast(Character target)
	{
		// uncastable
	}
	
	@Override
	public void addToSpellbook(Player p)
	{
		super.addToSpellbook(p);
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " crit chance bonus.",
				this.CHARACTER, -1, 7, 15));
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " crit damage bonus.",
				this.CHARACTER, -1, 8, 0.75));
	}

}
