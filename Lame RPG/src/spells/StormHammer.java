package spells;

import buffs.StunBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class StormHammer extends ActiveSpell
{
	int spell_damage = 15;
	
	public StormHammer(Characters c)
	{
		super("Storm Hammer", "Unleash a magical gauntlet that deals 15/20/25/30 magic damage and stuns 1/2/all/all enemy units for 2 turns", 20, 1, 4, c, true);
	}

	@Override
	public void cast(Characters target)
	{
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		if(spell_level == 1)
		{
			CHARACTER.dealMagicDamage(spell_damage, target);
			target.buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", target, 2));
		}
		else if(spell_level == 2)
		{
			int index = -1;
			for(int i = 0; i < Game.monsters.size(); i++)
			{
				if(Game.monsters.get(i) == target)
				{
					index = i;
					break;
				}
			}
			
			if(index == 0) // the storm hammer should hit the target and the monster directly to the left(right if it is at index 0)
			{
				CHARACTER.dealMagicDamage(spell_damage, target);
				target.buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", target, 2));

				if(Game.monsters.get(1) != null)
				{
					CHARACTER.dealMagicDamage(spell_damage, Game.monsters.get(1));
					Game.monsters.get(1).buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", Game.monsters.get(1), 2));
				}
			}
			else if(index > 0)
			{
				CHARACTER.dealMagicDamage(spell_damage, Game.monsters.get(index - 1));
				Game.monsters.get(index - 1).buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", Game.monsters.get(0), 2));
				
				CHARACTER.dealMagicDamage(spell_damage, target);
				target.buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", target, 2));

			}
		}
		else if(spell_level >= 3)
			for(Monster m : Game.monsters)
			{
				CHARACTER.dealMagicDamage(spell_damage, m);
				m.buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", m, 2));
			}
	}
	
	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			spell_damage += 5;
		}
		
		return level_up;
	}
}