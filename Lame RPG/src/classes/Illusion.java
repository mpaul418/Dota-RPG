package classes;

import buffs.AttackBuff;
import buffs.AttackedBuff;
import buffs.Buff;
import buffs.StatBuff;
import src.Game;

public class Illusion extends Characters
{

	public Illusion(Characters target)
	{
		super(target.getDefaultHP(), target.getDefaultMana(), target.getDefaultDamage(), target.getDefaultAccuracy(), 
				target.getDefaultDefense(), target.getDefaultMagicDefense(), target.getName() + " Illusion");
		
		if(Game.players.contains(target))
			Game.players.add(this);
		else if(Game.monsters.contains(target))
			Game.monsters.add(this);
		
		for(Buff b : target.buffs) // gives the illusions the same passives as the target
		{
			if(b.getDuration() == -1) //if passive
			{
				if(b instanceof AttackBuff)
				{
					this.buffs.add(new AttackBuff(b.getName(), b.getDescription(), this, -1, ((AttackBuff) b).getManaBurn(), 
							((AttackBuff) b).getManaBurnMultiplier(), ((AttackBuff) b).getCritChance(), ((AttackBuff) b).getCritModifier(),
							((AttackBuff) b).getEnemiesCleaved(), ((AttackBuff) b).getCleavePercentage()));
				}
				else if(b instanceof AttackedBuff) 	//TODO
				{
					
				}
				else if(b instanceof StatBuff)		//TODO
				{
					
				}
			}
		}
	}

	@Override
	public void takeTurn()
	{
		// TODO Auto-generated method stub

	}

}
