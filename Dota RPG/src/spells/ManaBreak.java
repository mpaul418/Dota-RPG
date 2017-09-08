package spells;

import buffs.AttackBuff;
import classes.Characters;

public class ManaBreak extends PassiveSpell
{

	public ManaBreak(Characters c)
	{
		super("Mana Break", "Burn 28/40/52/64 mana with each attack, dealing 60% of mana burned as damage", 0, 1, -1, c);
	}

	@Override
	public void addToSpellbook()
	{
		super.addToSpellbook();
		new AttackBuff(this.NAME, "Burning 28 mana per hit.",
			this.CHARACTER, -1, 28, 0.6, 0, 0, 0, 0);
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
						new AttackBuff(this.NAME, "Burning 40 mana per hit.",
								this.CHARACTER, -1, 40, 0.6, 0, 0, 0, 0);
					else if(spell_level == 3)
						new AttackBuff(this.NAME, "Burning 52 mana per hit.",
							this.CHARACTER, -1, 52, 0.6, 0, 0, 0, 0);
					else if(spell_level == 4)
						new AttackBuff(this.NAME, "Burning 64 mana per hit.",
							this.CHARACTER, -1, 64, 0.6, 0, 0, 0, 0);
					
					break;
				}
			}
		}
		
		return level_up;
	}
}