package spells;

import buffs.AttackBuff;
import classes.Characters;

public class CoupDeGrace extends PassiveSpell
{

	public CoupDeGrace(Characters c)
	{
		super("Coup de Gr�ce", "Gain a 15% chance to land a critical hit, dealing 175/225/275% damage", 0, 4, -1, c);
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
					CHARACTER.buffs.get(i).deletThis();
					
					if(spell_level == 2)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " crit chance.",
								this.CHARACTER, -1, 0, 0, 15, 2.25, 0, 0.0));
					else if(spell_level == 3)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " crit chance.",
								this.CHARACTER, -1, 0, 0, 15, 2.75, 0, 0.0));
					
					System.out.println("This(mana break) is working!!! (delete this)");
					break;
				}
			}
		}
		
		return level_up;
	}
}