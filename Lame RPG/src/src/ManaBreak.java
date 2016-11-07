package src;

public class ManaBreak extends PassiveSpell
{

	public ManaBreak(Character c)
	{
		//TODO
		super("Mana Break", "Passively burn 25 mana with each attack, dealing 60% of mana burned as damage.", manacost, level_rq, cd, c, targetable);
		// TODO Auto-generated constructor stub
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
		//super.addToSpellbook(p);
		//this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " magic resist.",
		//		this.CHARACTER, -1, 5, 66));
	}
}