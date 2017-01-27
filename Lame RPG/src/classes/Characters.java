package classes;
import java.util.ArrayList;
import java.util.Random;

import buffs.AttackBuff;
import buffs.AttackedBuff;
import buffs.Buff;
import buffs.StatBuff;
import buffs.StunBuff;
import spells.Spell;
import src.Game;

public class Characters 
{	
	private Random r = new Random();
	protected String name;
	protected int HP, defaultHP;
	protected int mana, defaultMana;
	protected int level;
	protected int damage, defaultDamage;
	protected int accuracy, defaultAccuracy;
	protected int defense, defaultDefense;
	protected double magicDefense, defaultMagicDefense; 
	protected boolean alive;
	protected int damage_dealt = 0;
	public int hunker_downs_used = 0;
	private final int MAX_ACCURACY = 44;
	public ArrayList<Spell> spellbook = new ArrayList<Spell>();
	public ArrayList<Spell> unlearned_spells = new ArrayList<Spell>();
	public ArrayList<Buff> buffs = new ArrayList<Buff>();
	
	public Characters(int initHP, int initMana, int initDmg, int initAcc, int initDef, double initMagDef, String initName)
	{
		defaultHP = initHP;
		HP = initHP; 
		defaultMana = initMana;
		mana = initMana;
		level = 1;
		defaultDamage = initDmg;//note- if more default stats 
		//should be able to be changed, add numbers for all + change in buff class
		damage = initDmg;//buff- 1
		defaultAccuracy = initAcc;
		accuracy = initAcc;//buff- 2
		defaultDefense = initDef;	
		defense = initDef;//buff- 3
		defaultMagicDefense = initMagDef;
		magicDefense = initMagDef;//buff- 4
		name = initName;
	}
	public int getHP()
	{
		return HP;
	}
	public int getDefaultHP()
	{
		return defaultHP;
	}
	public int getMana()
	{
		return mana;
	}
	public int getDefaultMana()
	{
		return defaultMana;
	}
	public int getLevel()
	{
		return level;
	}
	public int getDamage()
	{
		return damage;
	}
	public int getDefaultDamage()
	{
		return defaultDamage;
	}
	public int getAccuracy()
	{
		return accuracy;
	}
	public int getDefaultAccuracy()
	{
		return defaultAccuracy;
	}
	public int getDefense()
	{
		return defense;
	}
	public int getDefaultDefense()
	{
		return defaultDefense;
	}
	public double getMagicDefense()
	{
		return magicDefense;
	}
	public double getDefaultMagicDefense()
	{
		return defaultMagicDefense;
	}
	public String getName()
	{
		return name;
	}
	public String toString()
	{
		return name;
	}
	public void setName(String s)
	{
		name = s;
	}
	public int changeMana(int amount)
	{
		int amount_changed = amount;
		mana += amount;
		if(mana > defaultMana)
		{
			amount_changed = defaultMana - mana;
			mana = defaultMana;
		}
		
		return amount_changed;
	}
	public int changeDefaultMana(int amount)
	{
		defaultMana += amount;
		mana += amount;
		
		return amount;
	}
	public int changeHP(int amount)
	{
		int amount_changed = amount;
		HP += amount;
		if(HP > defaultMana)
		{
			amount_changed = defaultHP - HP;
			HP = defaultHP;
		}
		
		return amount_changed;
	}
	public int changeDefaultHP(int amount)
	{
		defaultHP += amount;
		HP += amount;
		
		return amount;
	}
	public int changeDamage(int amount)
	{
		damage += amount;
		
		return amount;
	}
	public int changeDefaultDamage(int amount)
	{
		defaultDamage += amount;
		damage += amount;
		
		return amount;
	}
	public int changeDefense(int amount)
	{
		defense += amount;
		
		return amount;
	}
	public int changeDefaultDefense(int amount)
	{
		defaultDefense += amount;
		defense += amount;
		
		return amount;
	}
	public double changeMagicDefense(double amount)
	{
		magicDefense += amount;
		
		return amount;
	}
	public double changeDefaultMagicDefense(double amount)
	{
		defaultMagicDefense += amount;
		magicDefense += amount;
		
		return amount;
	}
	public int changeAccuracy(int amount)
	{
		accuracy += amount;
		
		return amount;
	}
	public int changeDefaultAccuracy(int amount)
	{
		int amount_changed = amount;
		defaultAccuracy += amount;
		accuracy += amount;
		
		if(defaultAccuracy > MAX_ACCURACY) // TODO TODO TODO accuracy can never go above 44 (99% chance to hit) without buffs- make balance changes in all characters!!!
		{
			amount_changed = MAX_ACCURACY - defaultAccuracy;
			defaultAccuracy = MAX_ACCURACY;
		}
		
		return amount_changed;
	}
	public void resetStatsAndBuffs()
	{
		ArrayList<Buff> buffs_being_removed = new ArrayList<Buff>();
		for(Buff b : buffs)
			buffs_being_removed.add(b);
		
		for(int i = buffs_being_removed.size(); i > 0; i--)
		{
			buffs_being_removed.get(0).deletThis();
			buffs_being_removed.remove(0);
		}
		
		HP = defaultHP;
		mana = defaultMana;
		damage = defaultDamage;
		accuracy = defaultAccuracy;
		defense = defaultDefense;
		magicDefense = defaultMagicDefense;
	}
	public boolean isAlive()
	{
		if(getHP() > 0)
			alive = true;
		else
			alive = false;
		return alive;
	}
	public boolean isStunned()
	{
		for(Buff b : this.buffs)
		{
			if(b instanceof StunBuff)
				return true;
		}
		return false;
	}
	//TODO replace method with system for magic/phys damage that calculates it in the method instead of before calling method
	public void damage(Characters c, int incoming_damage)
	{
		c.HP -= incoming_damage;
		damage_dealt += incoming_damage;
	}
	public void heal(Characters c, int heal)
	{
		c.HP += heal;
	}
	public void attack(Characters c)
	{
		int temp, damage_done;
		int crit_buff_index = -1;
		boolean critical_hit, attack_evaded;
		attack_evaded = critical_hit = false;
		
		temp = r.nextInt(101) + accuracy;
		if(temp >= 55) // checks if attack hits- a 55% chance to hit with 0 accuracy
		{
			for(Buff b : c.buffs)
			{
				if(b instanceof AttackedBuff && !attack_evaded)
				{
					attack_evaded = ((AttackedBuff) b).attackEvaded();
					if(attack_evaded)
						break;
				}
			}
			if(!attack_evaded)
			{
				int currentDefense = 0;
				if(c.getDefense() > 0)
					currentDefense = r.nextInt(c.getDefense()) + 1;
				
				 											//checks for a crit with every attack buff, 
				for(int i = 0; i < buffs.size(); i++)		//can probably be optimized for only attack buffs that CAN crit
				{
					if(buffs.get(i) instanceof AttackBuff && !attack_evaded)
					{
						critical_hit = ((AttackBuff) buffs.get(i)).criticalHit();
						if(critical_hit)
						{
							crit_buff_index = i;
							break;
						}
					}
				}
				
				if(damage > currentDefense || critical_hit)
				{
					if(critical_hit)
					{
						damage_done = (int)Math.round(((AttackBuff) buffs.get(crit_buff_index)).getCritModifier() * damage) - currentDefense;
						if(damage_done > 0)
							System.out.print("Critical hit!! ");
						else
						{
							damage_done = 5;
							System.out.println("Critical graze!");
						}
					}
					else
						damage_done = damage - currentDefense;
					
					damage(c, damage_done);
					System.out.println(this + " attacked " + c + " and dealt " + damage_done + " damage."
								     + " (Defense roll: " + currentDefense + "/" + c.getDefense() + ")");
				}
				else
				{
					damage_done = 1;
					damage(c, damage_done);
					System.out.println(this.getName() + " grazed " + c.getName() + " and dealt " + damage_done + " damage.");
				}
				
				for(Buff b : buffs)
				{
					if(b instanceof AttackBuff)
					{
						((AttackBuff) b).applyManaBurn(c);
						((AttackBuff) b).applyCleave(c, damage_done);
						//((AttackBuff) b).applyStunEffect(c);
						//this is where any post-attack triggers are checked
					}
				}
				
				System.out.println();
			}
			else
				System.out.println(this + "'s attack was evaded by " + c + "!");
		}
		else
		{
			System.out.println(this + "'s attack missed! (Attack roll: " + temp + ")\n");
		}
	}
	public void dealMagicDamage(int incoming_damage, Characters c)
	{
		int damage_done = (int)(Math.round((1.0 - c.getMagicDefense()) * incoming_damage));
		this.damage(c, damage_done);
		System.out.println(this + " dealt " + damage_done + " magic damage to " + c + ".");
	}
	
	public void printAllBuffs()
	{
		System.out.println(this + "'s buffs:");
		if(buffs.size() == 0)
			System.out.println("\t" + this + " has no buffs.");
		else
			for(Buff b : buffs)
				System.out.println("\t" + b);
	}
	
	public boolean allSpellsUncastable()
	{
		int uncastable_spells = 0;
		for(Spell s : spellbook)
		{
			if(!s.isCastable())
				uncastable_spells++;
		}
		
		if(uncastable_spells == spellbook.size())
			return true;
		else
			return false;
	}
	public void hunkerDown()
	{
		int amount;
		hunker_downs_used++;
		if(hunker_downs_used <= 3)
			amount = r.nextInt(16 - (5 * hunker_downs_used)) + 2;
		else
			amount = 2;
		this.buffs.add(new StatBuff("Hunker Down", "Defense increased  by " + amount, this, 2, 3, amount));
		System.out.println(this.getName() + " hunkered down, increasing its defense by " + amount + " for 2 turns.\n");
	}
	
	public void refreshDebuffsAndReduceCooldowns() //FIXME buffs saying they last 1 turn longer than they should?? i moved this from Game so maybe it works now
	{
		ArrayList<Buff> deleted_buffs = new ArrayList<Buff>();
		
			for(Spell s : this.spellbook)
				if(s.getCurrentCooldown() > 0)
					s.changeCurrentCooldown(-1);

			for(Buff b : this.buffs)
				b.decreaseDuration(deleted_buffs);
		
		for(Buff b : deleted_buffs)
		{
			b.deletThis();
		}
		while(deleted_buffs.size() > 0)
			deleted_buffs.remove(0);
	}
	
	public void die()
	{
		Game.characters.remove(this);
		System.out.println(this + " died.\n");
	}
}