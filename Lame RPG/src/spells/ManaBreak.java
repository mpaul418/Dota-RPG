package spells;

import buffs.AttackBuff;
import classes.Characters;

public class ManaBreak extends PassiveSpell
{

	public ManaBreak(Characters c)
	{
		super("Mana Break", "Burn 15/20/25/35 mana with each attack, dealing 60% of mana burned as damage", 0, 1, -1, c);
	}

	@Override
	public void addToSpellbook()
	{
		super.addToSpellbook();
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "Burning 15 mana per hit.",
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
					CHARACTER.buffs.get(i).deletThisNoNotifier();
					
					if(spell_level == 2)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "Burning 20 mana per hit.",
								this.CHARACTER, -1, 20, 0.6, 0, 0, 0, 0));
					else if(spell_level == 3)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "Burning 25 mana per hit.",
							this.CHARACTER, -1, 25, 0.6, 0, 0, 0, 0));
					else if(spell_level == 4)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "Burning 35 mana per hit.",
							this.CHARACTER, -1, 35, 0.6, 0, 0, 0, 0));
					
					break;
				}
			}
		}
		
		return level_up;
	}
}