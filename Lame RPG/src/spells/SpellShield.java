package spells;

import buffs.StatBuff;
import classes.Characters;

public class SpellShield extends PassiveSpell
{

	public SpellShield(Characters c)
	{
		super("Spell Shield", "Gain a 22/44/66% magic resistance bonus", 0, 3, -1, c);
	}
	
	@Override
	public void addToSpellbook(Characters c)
	{
		super.addToSpellbook(c);
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " magic resist.",
				this.CHARACTER, -1, 5, 22.0));
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
						this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " magic resist.",
								this.CHARACTER, -1, 5, 44.0));
					else if(spell_level >= 3)
						this.CHARACTER.buffs.add(new StatBuff(this.NAME, this.NAME + " magic resist.",
								this.CHARACTER, -1, 5, 66.0));
					
					System.out.println("This is working!!! (delete this)");
					break;
				}
			}
			return true;
		}
		else return false;
	}
}
