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
	private final int MAX_ACCURACY = 74;
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
		defaultDamage = initDmg;			//note- if more default stats 
											//should be able to be changed, add numbers for all + change in buff class
		damage = initDmg;					//buff- 1
		defaultAccuracy = initAcc;
		accuracy = initAcc;					//buff- 2
		defaultDefense = initDef;	
		defense = initDef;					//buff- 3
		defaultMagicDefense = initMagDef;
		magicDefense = initMagDef;			//buff- 4
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
	public int getDamageDealt()
	{
		return damage_dealt;
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
		else if(mana < 0)
		{
			amount_changed = mana - amount;
			mana = 0;
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
		if(HP > defaultHP)
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
		int amount_changed = amount;
		
		if(damage + amount < 6)
		{
			amount_changed = damage - 6;
			damage = 6;
		}
		else
			damage += amount;
		
		return amount_changed;
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
		int amount_changed = amount;
		accuracy += amount;
		
		if(accuracy > MAX_ACCURACY) // accuracy can never go above 74 (99% chance to hit)
		{
			amount_changed = MAX_ACCURACY - accuracy;
			accuracy = MAX_ACCURACY;
		}
		
		return amount_changed;
	}
	public int changeDefaultAccuracy(int amount)
	{
		int amount_changed = amount;
		defaultAccuracy += amount;
		accuracy += amount;
		
		if(defaultAccuracy > MAX_ACCURACY) // accuracy can never go above 74 (99% chance to hit)
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
	public int damage(Characters c, int incoming_damage)
	{
		c.HP -= incoming_damage;
		damage_dealt += incoming_damage;
		
		return incoming_damage;
	}
	public int dealPhysicalDamage(Characters c, int incoming_damage)
	{
		int damage_done = 0;
		int current_defense = 0;
		if(c.getDefense() > 0)
			current_defense = r.nextInt(c.getDefense()) + 1;
		else if(c.getDefense() < 0)
			current_defense = -(r.nextInt(-c.getDefense()) + 1); // if defense is less than 0, then damage is amplified
		
		damage_done = incoming_damage - current_defense;
		if(damage_done <= 0)
			damage_done = 1;
		
		damage(c, damage_done);
		
		if(damage_done == 1)
			System.out.print("Graze. ");
		else
			System.out.print("Hit! ");
		
		System.out.println(this + " dealt " + damage_done + " physical damage to " + c + "."
					  + " (Defense roll: " + current_defense + "/" + c.getDefense() + ")"); //FIXME in final version of game, is this necessary to display?
		
		return damage_done;
	}
	public void heal(Characters c, int heal)
	{
		c.HP += heal;
	}
	public void attack(Characters c)
	{
		int temp, damage_done;
		damage_done = 0;
		int damage_roll = (damage - 5) + r.nextInt(11); // each damage roll has a variance of 10 (ex. damage = 40, then damage could be 35 to 45)
		int crit_buff_index = -1;
		boolean critical_hit, attack_evaded;
		attack_evaded = critical_hit = false;
		
		System.out.println(this + " attacked " + c + "!");
		
		temp = r.nextInt(101);
		if(temp >= 75 - accuracy) // checks if attack hits- a 25% chance to hit with 0 accuracy
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
				for(int i = 0; i < buffs.size(); i++)		// checks for a crit with every attack buff that can crit
				{
					if(buffs.get(i) instanceof AttackBuff && ((AttackBuff) (buffs.get(i))).getCritChance() > 0 && !attack_evaded)
					{
						critical_hit = ((AttackBuff) buffs.get(i)).criticalHit();
						if(critical_hit)
						{
							crit_buff_index = i;
							break;
						}
					}
				}
				
				if(critical_hit)
				{
					damage_done = (int)Math.round(((AttackBuff) buffs.get(crit_buff_index)).getCritModifier() * damage_roll);
					if(damage_done > 0)
						System.out.print("Critical "); 	// is either a hit or graze depending on dealPhysicalDamage()
				}
				else
					damage_done = damage_roll;
				
				int dmg_after_reductions = dealPhysicalDamage(c, damage_done);
				
				for(Buff b : buffs)
				{
					if(b instanceof AttackBuff)			// this is where any post-attack triggers are checked
					{
						((AttackBuff) b).applyManaBurn(c);
						((AttackBuff) b).applyCleave(c, dmg_after_reductions);
					}
				}
				
				System.out.println();
			}
			else
				System.out.println(this + "'s attack was evaded by " + c + "!");
		}
		else
		{
			System.out.println(this + "'s attack missed! (Attack roll: " + temp + "; " + (75 - accuracy) + " or higher needed to hit)\n");
		}
	}
	public int dealMagicDamage(int incoming_damage, Characters c)
	{
		int damage_done = (int)(Math.round((1.0 - c.getMagicDefense()) * incoming_damage));
		this.damage(c, damage_done);
		System.out.println(this + " dealt " + damage_done + " magic damage to " + c + ".");
		
		return damage_done;
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
			amount = r.nextInt(16 - (5 * hunker_downs_used)) + 3;
		else
			amount = 2;
		this.buffs.add(new StatBuff("Hunker Down", "Defense increased  by " + amount, this, 2, 3, amount));
		System.out.println(this.getName() + " hunkered down, increasing its defense by " + amount + " for 2 turns.\n");
	}
	
	public void refreshDebuffsAndReduceCooldowns()
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