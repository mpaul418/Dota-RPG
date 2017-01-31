package spells;

import buffs.AttackBuff;
import classes.Characters;

public class ManaBreak extends PassiveSpell
{

	public ManaBreak(Characters c)
	{
		super("Mana Break", "Burn 15/20/25 mana with each attack, dealing 60% of mana burned as damage", 0, 2, -1, c);
	}

	@Override
	public void addToSpellbook()
	{
		super.addToSpellbook();
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " mana burn.",
			this.CHARACTER, -1, 15, 0.6, 0, 0, 0, 0));
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
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " mana burn.",
								this.CHARACTER, -1, 20, 0.6, 0, 0, 0, 0));
					else if(spell_level == 3)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " mana burn.",
							this.CHARACTER, -1, 25, 0.6, 0, 0, 0, 0));
					
					System.out.println("This(mana break) is working!!! (delete this)");
					break;
				}
			}
		}
		
		return level_up;
	}
}