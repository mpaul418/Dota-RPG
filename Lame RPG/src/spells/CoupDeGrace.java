package spells;

import buffs.AttackBuff;
import classes.Characters;

public class CoupDeGrace extends PassiveSpell
{
	public double crit_multiplier = 1.75;
	
	public CoupDeGrace(Characters c)
	{
		super("Coup de Grâce", "Gain a 15% chance to land a critical hit, dealing 175/225/275% damage", 0, 2, -1, c);
		max_spell_level = 3;
	}

	@Override
	public void addToSpellbook()
	{
		super.addToSpellbook();
		this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "Gaining a 15% chance to deal " + ((int)(Math.round(crit_multiplier * 100))) + "% damage.",
				this.CHARACTER, -1, 0, 0, 15, crit_multiplier, 0, 0.0));
	}
	
	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			crit_multiplier += 0.5;
			
			for(int i = 0; i < this.CHARACTER.buffs.size(); i++)
			{
				if(CHARACTER.buffs.get(i).getName() == this.NAME)
				{
					CHARACTER.buffs.get(i).deletThisNoNotifier();
					
					this.CHARACTER.buffs.add(new AttackBuff(this.NAME, "Gaining a 15% chance to deal " + ((int)(Math.round(crit_multiplier * 100))) + "% damage.",
							this.CHARACTER, -1, 0, 0, 15, crit_multiplier, 0, 0.0));

					break;
				}
			}
		}
		
		return level_up;
	}
}