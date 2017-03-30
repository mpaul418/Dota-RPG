package spells;

import buffs.AttackBuff;
import classes.Characters;

public class GreatCleave extends PassiveSpell
{

	public GreatCleave(Characters c)
	{
		super("Great Cleave", "Deal 30/45/60/75% of your damage to two enemies other than your attack target", 0, 1, -1, c);
	}

	@Override
	public void addToSpellbook()
	{
		super.addToSpellbook();
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "cleaving two random enemies for 30% of damage.",
				this.CHARACTER, -1, 0, 0, 0, 0, 2, 0.30));
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
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "cleaving two random enemies for 45% of damage.",
							this.CHARACTER, -1, 0, 0, 0, 0, 2, 0.45));
					else if(spell_level == 3)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "cleaving two random enemies for 60% of damage.",
							this.CHARACTER, -1, 0, 0, 0, 0, 2, 0.60));
					else if(spell_level == 4)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "cleaving two random enemies for 75% of damage.",
							this.CHARACTER, -1, 0, 0, 0, 0, 2, 0.75));
					
					break;
				}
			}
		}
		
		return level_up;
	}
}