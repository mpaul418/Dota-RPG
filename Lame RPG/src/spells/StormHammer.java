package spells;

import buffs.StunBuff;
import classes.Characters;
import classes.Monster;
import src.Game;

public class StormHammer extends ActiveSpell
{

	public StormHammer(Characters c)
	{
		super("Storm Hammer", "Unleash a magical gauntlet that deals magic damage and stuns 1/2/all enemy units for 2 turns", 20, 2, 5, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Characters target)
	{
		this.castWithTargetMessage(target);
		
		if(CHARACTER.getLevel() == 2)
		{
			
			CHARACTER.dealMagicDamage(25, target);
			target.buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", target, 2));
		}
		else if(CHARACTER.getLevel() == 3)
		{
			if(Game.monsters.get(0) == target) //TODO make the storm hammer hit the target and the monster directly to the left(right if it is at index 0)
			{
				CHARACTER.dealMagicDamage(25, target);
				target.buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", target, 2));

				if(Game.monsters.get(1) != null)
				{
					CHARACTER.dealMagicDamage(25, Game.monsters.get(1));
					Game.monsters.get(1).buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", Game.monsters.get(1), 2));
				}
			}
			else
			{
				CHARACTER.dealMagicDamage(25, Game.monsters.get(0));
				Game.monsters.get(0).buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", Game.monsters.get(0), 2));
				
				CHARACTER.dealMagicDamage(25, target);
				target.buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", target, 2));

			}
		}
		else if(CHARACTER.getLevel() >= 4)
			for(Monster m : Game.monsters)
			{
				CHARACTER.dealMagicDamage(25, m);
				m.buffs.add(new StunBuff(this.NAME, "Storm Hammer Stun", m, 2));
			}
	}

}
