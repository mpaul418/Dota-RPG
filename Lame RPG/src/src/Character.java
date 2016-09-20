package src;
import java.util.Random;

class Character {
	
	private Random r = new Random();
	protected final String name;
	protected int HP, maxHP;
	protected int mana, maxMana;
	protected int level;
	protected int damage, maxDamage;
	protected int attack, maxAttack;
	protected int defense, maxDefense;
	protected int magicDefense, maxMagicDefense;
	protected int[] stats;
	//0- str; 1- dex; 2- int; 3-spd; 4-luck;??????
	
	public Character(int initHP, int initMana, int initDmg, int initAtk, int initDef, int initMagDef, String initName)
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
	public int getMana()
	{
		return mana;
	}
	public int getLevel()
	{
		return level;
	}
	public int getDamage()
	{
		return damage;
	}
	public int getAttack()
	{
		return attack;
	}
	public int getDefense()
	{
		return defense;
	}
	public String getName()
	{
		return name;
	}
	public boolean isAlive()
	{
		if(getHP() > 0)
			return true;
		else
			return false;
	}
	
	public void damage(Character c, int incoming_damage)
	{
		c.HP -= incoming_damage;
	}
	public void heal(Character c, int heal)
	{
		c.HP += heal;
	}
	public void attack(Character c)
	{
		int temp, temp2;
		temp = 70 + r.nextInt(attack + 1);
		if(temp >= 100)
		{
			int currentDefense = r.nextInt(c.getDefense() + 1);
			temp2 = r.nextInt(currentDefense + 1);
			if(attack > temp2)
			{
				damage(c, damage - temp2);
			}
			else
				damage(c, 1);
		}
		else
		{
			//what happens if the attack misses - TO DO
		}
	}
	
	
}
