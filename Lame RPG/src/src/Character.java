package src;
import java.util.ArrayList;
import java.util.Random;

public class Character 
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
	protected ArrayList<Buff> buffs = new ArrayList<Buff>();
	
	public Character(int initHP, int initMana, int initDmg, int initAtk, int initDef, double initMagDef, String initName)
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
		defaultDodgeChance = 0;//buff- 8
		dodgeChance = 0;//buff- 9
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
			{
				return false;
			}
		}
		return true;
	}
	//TODO replace method with system for magic/phys damage that calculates it in the method instead of before calling method
	public void damage(Character c, int incoming_damage)
	{
		c.HP -= incoming_damage;
		damage_dealt += incoming_damage;
	}
	public void heal(Character c, int heal)
	{
		c.HP += heal;
	}
	public void attack(Character c)
	{
		int temp, damage_done, crit_roll;
		boolean critical_hit;
		
		temp = 90 + r.nextInt(attack + 1);
		if(temp >= 100)
		{
			crit_roll = r.nextInt(100) + 1;
			if(critChance <= crit_roll)
				critical_hit = true;
			else
				critical_hit = false;
				
			int currentDefense = r.nextInt(c.getDefense() + 1);
			if(damage > currentDefense)
			{
				if(critical_hit)
				{
					damage_done = (int)Math.round(critChance * damage) - currentDefense;
					System.out.print("Critical hit!! ");
				}
				else
					damage_done = damage - currentDefense;
				damage(c, damage_done);
				System.out.println(this.getName() + " attacked " + c.getName() + " and dealt " + damage_done + " damage."
							     + " (Defense roll: " + currentDefense + "/" + c.getDefaultDefense() + ")\n");
			}
			else
			{
				damage_done = 1;
				damage(c, damage_done);
				System.out.println(this.getName() + " grazed " + c.getName() + " and dealt " + damage_done + " damage.\n"
								 + " (Defense roll: " + currentDefense + "/" + c.getDefaultDefense() + ")\n");
			}
		}
		else
		{
			System.out.println(this.getName() + "'s attack missed! (Attack roll: " + temp + ")\n");
		}
	}
	public void dealMagicDamage(int incoming_damage, Character c)
	{
		int damage_done = (int)(Math.round(c.getMagicDefense() * incoming_damage));
		this.damage(c, damage_done);
		System.out.println(this + "dealt " + damage_done + " magic damage to " + c + ".");
	}
	//TODO make hunker down less effective the more times you use it in a battle
	public void hunkerDown()
	{
		int amount = r.nextInt(31) + 30;
		this.changeDefense(amount);
		System.out.println(this.getName() + " hunkered down, increasing its defense by " + amount + "!!\n");
	}
}