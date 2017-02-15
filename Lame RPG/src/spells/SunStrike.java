package spells;

import classes.Characters;

public class SunStrike extends ActiveSpell
{
	int base_damage = 25;
	int level_multiplier = 4;
	public SunStrike(Characters c)
	{
		super("Sun Strike", "Call down a solar flare on an enemy, dealing 25/40/55 + 4/5/6 times the square of your level as magical damage", 25, 1, 4, c, true);
	}

	@Override
	public void cast()
	{
		this.incorrectCastWithoutTarget();
	}

	@Override
	public void cast(Characters target)
	{
		int magic_damage;
		magic_damage = base_damage + (level_multiplier * this.CHARACTER.getLevel() * this.CHARACTER.getLevel()); // damage is base dmg plus lvlmulti * level^2
		this.beforeSpellCast();
		this.castWithTargetMessage(target);
		
		CHARACTER.dealMagicDamage(magic_damage, target);
	}
	
	@Override
	public boolean levelUp()
	{
		boolean level_up = super.levelUp();
		
		if(level_up)
		{
			base_damage += 15;
			level_multiplier++;
		}
		return level_up;
	}
}