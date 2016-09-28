package src;
import java.util.Random;

class Character 
{	
	private Random r = new Random();
	protected String name;
	protected int HP, maxHP;
	protected int mana, maxMana;
	protected int level;
	protected int damage, maxDamage;
	protected int attack, maxAttack;
	protected int defense, maxDefense;
	protected int magicDefense, maxMagicDefense;
	protected int[] stats;
	protected boolean alive;

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
	public int getMagicDefense()
	{
		return magicDefense;
	}
	public int getMaxMagicDefense()
	{
		return maxMagicDefense;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String s)
	{
		name = s;
	}
	public boolean isAlive()
	{
		if(getHP() > 0)
			alive = true;
		else
			alive = false;
		return alive;
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
		int temp, temp2, damage_done;
		temp = 90 + r.nextInt(attack + 1);
		if(temp >= 100)
		{
			int currentDefense = r.nextInt(c.getDefense() + 1);
			temp2 = r.nextInt(currentDefense + 1);
			if(attack > temp2)
			{
				damage_done = damage - temp2;
				damage(c, damage_done);
			}
			else
			{
				damage_done = 1;
				damage(c, damage_done);
			}
			
			System.out.println(this.getName() + " attacked " + c.getName() + " and dealt " + damage_done + " damage!!\n");
		}
		else
		{
			System.out.println(this.getName() + "'s attack missed!\n");
		}
	}
	public void hunkerDown()
	{
		int amount = r.nextInt(31) + 30;
		this.defense += amount;
		System.out.println(this.getName() + " hunkered down, increasing its defense by " + amount + "!!\n");
	}
}