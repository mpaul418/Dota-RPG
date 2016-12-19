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
	protected int attack, defaultAttack;
	protected int defense, defaultDefense;
	protected double magicDefense, defaultMagicDefense;
	protected int critChance, defaultCritChance;
	protected double critModifier, defaultCritModifier;
	protected int dodgeChance, defaultDodgeChance; 
	protected boolean alive;
	protected int damage_dealt = 0;
	public int hunker_downs_used = 0;
	public ArrayList<Spell> spellbook = new ArrayList<Spell>();
	public ArrayList<Spell> unlearned_spells = new ArrayList<Spell>();
	public ArrayList<Buff> buffs = new ArrayList<Buff>();
	
	public Characters(int initHP, int initMana, int initDmg, int initAtk, int initDef, double initMagDef, String initName)
	{
		defaultHP = initHP;
		HP = initHP; 
		defaultMana = initMana;
		mana = initMana;
		level = 1;
		defaultDamage = initDmg;//note- if more default stats 
		//should be able to be changed, add numbers for all + change in buff class
		damage = initDmg;//buff- 1
		defaultAttack = initAtk;
		attack = initAtk;//buff- 2
		defaultDefense = initDef;	
		defense = initDef;//buff- 3
		defaultMagicDefense = initMagDef;
		magicDefense = initMagDef;//buff- 4
		defaultCritChance = 5;
		critChance = 5; //buff- 5
		defaultCritModifier = 2.00;//buff- 6
		critModifier = 2.00; //buff- 7
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
	public int getAttack()
	{
		return attack;
	}
	public int getDefaultAttack()
	{
		return defaultAttack;
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
	public int getCritChance()
	{
		return critChance;
	}
	public int getDefaultCritChance()
	{
		return defaultCritChance;
	}
	public double getCritModifier()
	{
		return critModifier;
	}
	public double getDefaultCritModifier()
	{
		return defaultCritModifier;
	}
	public int getDodgeChance()
	{
		return dodgeChance;
	}
	public int getDefaultDodgeChance()
	{
		return defaultDodgeChance;
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
	public void changeMana(int amount)
	{
		mana += amount;
		if(mana > defaultMana)
			mana = defaultMana;
	}
	public void changeDefaultMana(int amount)
	{
		defaultMana += amount;
		mana += amount;
	}
	public void changeHP(int amount)
	{
		HP += amount;
		if(HP > defaultHP)
			HP = defaultHP;
	}
	public void changeDefaultHP(int amount)
	{
		defaultHP += amount;
		HP += amount;
	}
	public void changeDamage(int amount)
	{
		damage += amount;
		if(damage > defaultDamage)
			damage = defaultDamage;
	}
	public void changeDefaultDamage(int amount)
	{
		defaultDamage += amount;
		damage += amount;
	}
	public void changeDefense(int amount)
	{
		defense += amount;
		if(defense > defaultDefense)
			defense = defaultDefense;
	}
	public void changeDefaultDefense(int amount)
	{
		defaultDefense += amount;
		defense += amount;
	}
	public void changeMagicDefense(int amount)
	{
		magicDefense += amount;
		if(magicDefense > defaultMagicDefense)
			magicDefense = defaultMagicDefense;
	}
	public void changeDefaultMagicDefense(int amount)
	{
		defaultMagicDefense += amount;
		magicDefense += amount;
	}
	public void changeAttack(int amount)
	{
		attack += amount;
		if(attack > defaultAttack)
			attack = defaultAttack;
	}
	public void changeDefaultAttack(int amount)
	{
		defaultAttack += amount;
		attack += amount;
	}
	public void changeCritChance(int amount)
	{
		critChance += amount;
	}
	public void changeDefaultCritChance(int amount)
	{
		defaultCritChance += amount;
		critChance += amount;
	}
	public void changeCritModifier(double amount)
	{
		critModifier += amount;
	}
	public void changeDefaultCritModifier(double amount)
	{
		defaultCritModifier += amount;
		critModifier += amount;
	}
	public void changeDodgeChance(int amount)
	{
		dodgeChance += amount;
	}
	public void changeDefaultDodgeChance(int amount)
	{
		defaultDodgeChance += amount;
		dodgeChance += amount;
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
		
		temp = r.nextInt(attack + 1);
		if(temp >= 10) // checks if attack hits
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
					currentDefense = r.nextInt(c.getDefense() + 1);
				
				
				for(int i = 0; i < buffs.size(); i++) 	//checks for a crit with every attack buff, 
														//can probably be optimized for only attack buffs that CAN crit
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
						System.out.print("Critical hit!! ");
					}
					else
						damage_done = damage - currentDefense;
					
					damage(c, damage_done);
					System.out.println(this + " attacked " + c + " and dealt " + damage_done + " damage."
								     + " (Defense roll: " + currentDefense + "/" + c.getDefaultDefense() + ")");
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
		int damage_done = (int)(Math.round((1 - c.getMagicDefense()) * incoming_damage));
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
		int amount = 15;
		hunker_downs_used++;
		if(hunker_downs_used <= 3)
			amount = r.nextInt(31 - (10 * hunker_downs_used)) + 30;
		this.buffs.add(new StatBuff("Hunker Down", "Defense increased  by " + amount, this, 2, 3, amount));
		System.out.println(this.getName() + " hunkered down, increasing its defense by " + amount + " for 2 turns.\n");
	}
	public void die()
	{
		Game.characters.remove(this);
		System.out.println(this + " died.\n");
	}
}