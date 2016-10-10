package src;

public class ManaVoid extends Spell
{

	public ManaVoid(Character c)
	{
		super("Mana Void", "After bringing enemies to their knees, Anti-Mage punishes them for their use of the arcane arts. "
			+ "Deal magic damage to all enemies equal to one enemy's missing mana.", 40, 6, 10, c, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cast()
	{
		System.out.println("You need a target for this spell! "
						+ "\nSpell Name: " + this.NAME 
						+ "\nSpell Owner: " + this.CHARACTER + ".");
		//THIS SPELL SHOULD ONLY BE CAST WITH A TARGET
	}

	@Override
	public void cast(Character target)
	{
		// TODO Auto-generated method stub
		int mana_missing = target.getMaxMana() - target.getMana();
		int damage_done;
		System.out.println(this.CHARACTER + " cast Mana Void on " + target + ".");
		for(Monster m : Game.monsters)
		{
			damage_done = (int)(Math.round(target.getMagicDefense() * mana_missing));
			this.CHARACTER.damage(m, damage_done);
			System.out.println(this.CHARACTER + "dealt " + damage_done + " damage to " + m + ".");
		}
	}

}
