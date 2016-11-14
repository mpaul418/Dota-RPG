package src;

public class ManaBreak extends PassiveSpell
{

	public ManaBreak(Character c)
	{
		//TODO
		super("Mana Break", "Passively burn 25 mana with each attack, dealing 60% of mana burned as damage.", 0, 1, -1, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addToSpellbook(Player p)
	{
		super.addToSpellbook(p);
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " mana burn.",
			this.CHARACTER, -1, 25, 0.6, 0, 0));
	}
}