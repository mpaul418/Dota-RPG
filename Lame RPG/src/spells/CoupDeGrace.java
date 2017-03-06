package spells;

import buffs.AttackBuff;
import classes.Characters;

public class CoupDeGrace extends PassiveSpell
{

	public CoupDeGrace(Characters c)
	{
		super("Coup de Grâce", "Gain a 15% chance to land a critical hit, dealing 175/225/275% damage", 0, 2, -1, c);
		max_spell_level = 3;
	}

	@Override
	public void addToSpellbook()
	{
		super.addToSpellbook();
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " crit chance.",
				this.CHARACTER, -1, 0, 0, 15, 1.75, 0, 0.0));
	}
	
	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			for(int i = 0; i < this.CHARACTER.buffs.size(); i++)
			{
				if(CHARACTER.buffs.get(i).getName() == this.NAME)
				{
					CHARACTER.buffs.get(i).deletThisNoNotifier();
					
					if(spell_level == 2)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " crit chance.",
								this.CHARACTER, -1, 0, 0, 15, 2.25, 0, 0.0));
					else if(spell_level == 3)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " crit chance.",
								this.CHARACTER, -1, 0, 0, 15, 2.75, 0, 0.0));
					
					System.out.println("This(coup de grace) is working!!! (delete this)");
					break;
				}
			}
		}
		
		return level_up;
	}
}