package spells;

import buffs.AttackBuff;
import classes.Characters;

public class GreatCleave extends PassiveSpell
{

	public GreatCleave(Characters c)
	{
		super("Great Cleave", "Deal 30/45/60% of your damage to two random enemies other than your attack target", 0, 1, -1, c);
	}

	@Override
	public void addToSpellbook(Characters c)
	{
		super.addToSpellbook(c);
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " cleave.",
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
					CHARACTER.buffs.get(i).deletThis();
					
					if(spell_level == 2)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " cleave.",
								this.CHARACTER, -1, 0, 0, 0, 0, 2, 0.45));
					else if(spell_level == 3)
						this.CHARACTER.buffs.add(new AttackBuff(this.NAME, this.NAME + " cleave.",
							this.CHARACTER, -1, 0, 0, 0, 0, 2, 0.60));
					
					System.out.println("This is working!!! (delete this)");
					break;
				}
			}
		}
		
		return level_up;
	}
}