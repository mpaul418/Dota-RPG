package src;
import java.util.Random;

public class Character 
{	
	private Random r = new Random();
	protected String name;
	protected int HP, maxHP;
	protected int mana, maxMana;
	protected int level;
	protected int damage, maxDamage;
	protected int attack, maxAttack;
	protected int defense, maxDefense;
	protected double magicDefense, maxMagicDefense;
	protected int[] stats;
	protected boolean alive;
	protected int damage_dealt = 0;

	//0- str; 1- dex; 2- int; 3-spd; 4-luck;??????
	
	public Character(int initHP, int initMana, int initDmg, int initAtk, int initDef, double initMagDef, String initName)
	{
		maxHP = initHP;
		HP = initHP;
		maxMana = initMana;
		mana = initMana;
		level = 1;
		maxDamage = initDmg;
		damage = initDmg;
		maxAttack = initAtk;
		attack = initAtk;
		maxDefense = initDef;	
		defense = initDef;
		maxMagicDefense = initMagDef;
		magicDefense = initMagDef;
		name = initName;
	}
	public int getHP()
	{
		return HP;
	}
	public int getMaxHP()
	{
		return maxHP;
	}
	public int getMana()
	{
		return mana;
	}
	public int getMaxMana()
	{
		return maxMana;
	}
	public int getLevel()
	{
		return level;
	}
	public int getDamage()
	{
		return damage;
	}
	public int getMaxDamage()
	{
		return maxDamage;
	}
	public int getAttack()
	{
		return attack;
	}
	public int getMaxAttack()
	{
		return maxAttack;
	}
	public int getDefense()
	{
		return defense;
	}
	public int getMaxDefense()
	{
		return maxDefense;
	}
	public double getMagicDefense()
	{
		return magicDefense;
	}
	public double getMaxMagicDefense()
	{
		return maxMagicDefense;
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
		if(mana > maxMana)
			mana = maxMana;
	}
	public void changeMaxMana(int amount)
	{
		maxMana += amount;
		mana += amount;
	}
	public void changeHP(int amount)
	{
		HP += amount;
		if(HP > maxHP)
			HP = maxHP;
	}
	public void changeMaxHP(int amount)
	{
		maxHP += amount;
		HP += amount;
	}
	public void changeDamage(int amount)
	{
		damage += amount;
		if(damage > maxDamage)
			damage = maxDamage;
	}
	public void changeMaxDamage(int amount)
	{
		maxDamage += amount;
		damage += amount;
	}
	public void changeDefense(int amount)
	{
		defense += amount;
		if(defense > maxDefense)
			defense = maxDefense;
	}
	public void changeMaxDefense(int amount)
	{
		maxDefense += amount;
		defense += amount;
	}
	public void changeMagicDefense(int amount)
	{
		magicDefense += amount;
		if(magicDefense > maxMagicDefense)
			magicDefense = maxMagicDefense;
	}
	public void changeMaxMagicDefense(int amount)
	{
		maxMagicDefense += amount;
		magicDefense += amount;
	}
	public void changeAttack(int amount)
	{
		attack += amount;
		if(attack > maxAttack)
			attack = maxAttack;
	}
	public void changeMaxAttack(int amount)
	{
		maxAttack += amount;
		attack += amount;
	}
	public boolean isAlive()
	{
		if(getHP() > 0)
			alive = true;
		else
			alive = false;
		return alive;
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
		int temp, damage_done;
		temp = 90 + r.nextInt(attack + 1);
		if(temp >= 100)
		{
			int currentDefense = r.nextInt(c.getDefense() + 1);
			if(damage > currentDefense)
			{
				damage_done = damage - currentDefense;
				damage(c, damage_done);
				System.out.println(this.getName() + " attacked " + c.getName() + " and dealt " + damage_done + " damage!!"
							     + " (Defense roll: " + currentDefense + "/" + c.getMaxDefense() + ")\n");
			}
			else
			{
				damage_done = 1;
				damage(c, damage_done);
				System.out.println(this.getName() + " grazed " + c.getName() + " and dealt " + damage_done + " damage.\n"
								 + " (Defense roll: " + currentDefense + "/" + c.getMaxDefense() + ")\n");
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