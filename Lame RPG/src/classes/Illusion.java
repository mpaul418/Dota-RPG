package classes;

import buffs.AttackBuff;
import buffs.AttackedBuff;
import buffs.Buff;
import buffs.StatBuff;
import src.Game;

public class Illusion extends Characters
{
	Buff life_timer;
	
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
					new AttackBuff(b.getName(), b.getDescription(), this, -1, ((AttackBuff) b).getManaBurn(), 
							((AttackBuff) b).getManaBurnMultiplier(), ((AttackBuff) b).getCritChance(), ((AttackBuff) b).getCritModifier(),
							((AttackBuff) b).getEnemiesCleaved(), ((AttackBuff) b).getCleavePercentage());
				}
				else if(b instanceof AttackedBuff)
				{
					new AttackedBuff(b.getName(), b.getDescription(), this, -1, ((AttackedBuff) b).getDodgeChance());
				}
				else if(b instanceof StatBuff)
				{
					new StatBuff(b.getName(), b.getDescription(), this, -1, ((StatBuff) b).getStat(), ((StatBuff) b).getDoubleModifier());
				}
			}
		}
		
		life_timer = new StatBuff("Illusion Duration", "Will die after 4 turns", this, 5, 4, 0 );
	}

	@Override
	public void takeTurn()
	{
		int choice, target;
		boolean turn_taken = this.isStunned();
		
		reduceCooldowns();
		refreshDebuffs();
		
		if(buffs.contains(life_timer))
		{
			if(turn_taken)
			{
				System.out.println(this + " is stunned.");
				refreshStuns();
			}
	
			while(!turn_taken)
			{
				turn_taken = false;
	
				Game.displayHPandMana();
	
				System.out.println("What would you like to do?");
				System.out.println("1: Attack"
							   + "\n2: View Buffs and Debuffs"
							   + "\n3: Hunker Down");
				choice = Game.getNumberFrom(1, 3);
	
				switch(choice)
				{
					case 1:
					{
						System.out.println("\nWhat would you like to attack?");
						System.out.println("1: Go back.");
						for(int i = 0; i < Game.monsters.size(); i++)
						{
							System.out.println((i + 2) + ": " + Game.monsters.get(i)
									+"(" + Game.monsters.get(i).getHP() + "/" + Game.monsters.get(i).getDefaultHP() + ")");
						}
						
						target = Game.getNumberFrom(1, Game.monsters.size() + 1);
						if(target > 1)
						{
							attack(Game.monsters.get(target - 2));
							turn_taken = true;
						}
						
						break;
					}
					case 2:
					{
						this.printAllBuffs();
						for(Characters m : Game.monsters)
							m.printAllBuffs();
	
						break;
					}
					case 3:
					{
						hunkerDown();
						turn_taken = true;
						
						break;
					}
				}
			}
		}
		else
		{
			die();
		}
	}
}
