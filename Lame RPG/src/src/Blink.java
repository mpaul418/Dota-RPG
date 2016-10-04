package src;

public class Blink extends Spell
{
	public Blink(Character c)
	{
		super("Blink", "Teleport and disorient your enemies, reducing your enemies' defence by 40.", 10, 1, 2, c, false);
		// TODO Auto-generated constructor stub
	}

	public void cast()
	{
		for(Monster m : Game.monsters)
		{
			m.changeDefense(40);
			System.out.println(m.getName() + "'s defense was reduced by 40!");
		}
		CHARACTER.changeMana(-MANA_COST);
		System.out.println();
		// TODO Auto-generated method stub
	}
	public void cast(Character target)
	{
		cast();	
	}

}
