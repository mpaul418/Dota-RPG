package spells;

import buffs.AttackedBuff;
import classes.Characters;

public class Blur extends PassiveSpell
{

	public Blur(Characters c)
	{
		super("Blur", "Gain a 20/35/50% chance to evade physical attacks", 0, 1, -1, c);
	}
	
	@Override
	public void addToSpellbook()
	{
		super.addToSpellbook();
		this.CHARACTER.buffs.add(new AttackedBuff(this.NAME, this.NAME + " evasion bonus.",
				this.CHARACTER, -1, 20));
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
						this.CHARACTER.buffs.add(new AttackedBuff(this.NAME, this.NAME + " evasion bonus.",
								this.CHARACTER, -1, 35));
					else if(spell_level >= 3)
						this.CHARACTER.buffs.add(new AttackedBuff(this.NAME, this.NAME + " evasion bonus.",
								this.CHARACTER, -1, 50));
					
					System.out.println("This(blur) is working!!! (delete this)");
					break;
				}
			}
		}
		
		return level_up;
	}
}