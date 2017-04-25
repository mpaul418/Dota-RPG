package spells;

import buffs.StatBuff;
import classes.Characters;

public class SpellShield extends PassiveSpell //TODO rework this spell to actually be useful- potentially into Manta Style???
{

	public SpellShield(Characters c)
	{
		super("Spell Shield", "Gain a 33/44/55/66% magic resistance bonus.", 0, 1, -1, c);
	}
	
	@Override
	public void addToSpellbook()
	{
		super.addToSpellbook();
		this.CHARACTER.buffs.add(new StatBuff(this.NAME, "Gaining 22% magic resist.",
				this.CHARACTER, -1, 4, 0.33));
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
					((StatBuff) CHARACTER.buffs.get(i)).deletThisNoNotifier();
					
					if(spell_level == 2)
						this.CHARACTER.buffs.add(new StatBuff(this.NAME, "Gaining 44% magic resist.",
								this.CHARACTER, -1, 4, 0.44));
					else if(spell_level == 3)
						this.CHARACTER.buffs.add(new StatBuff(this.NAME, "Gaining 66% magic resist.",
								this.CHARACTER, -1, 4, 0.55));
					else if(spell_level == 4)
						this.CHARACTER.buffs.add(new StatBuff(this.NAME, "Gaining 66% magic resist.",
								this.CHARACTER, -1, 4, 0.66));
					
					break;
				}
			}
		}
		
		return level_up;
	}
}
