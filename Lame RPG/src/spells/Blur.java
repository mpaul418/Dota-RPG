package spells;

import buffs.AttackedBuff;
import classes.Characters;

public class Blur extends PassiveSpell
{

	public Blur(Characters c)
	{
		super("Blur", "Gain a 20/30/40/50% chance to evade physical attacks", 0, 1, -1, c);
	}
	
	@Override
	public void addToSpellbook()
	{
		super.addToSpellbook();
		new AttackedBuff(this.NAME, "Gaining 20% evasion.",
				this.CHARACTER, -1, 20);
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
						new AttackedBuff(this.NAME, "Gaining 30% evasion.",
								this.CHARACTER, -1, 30);
					else if(spell_level == 3)
						new AttackedBuff(this.NAME, "Gaining 40% evasion.",
								this.CHARACTER, -1, 40);
					else if(spell_level == 4)
						new AttackedBuff(this.NAME, "Gaining 50% evasion.",
								this.CHARACTER, -1, 50);

					break;
				}
			}
		}
		
		return level_up;
	}
}