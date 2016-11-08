package src;

public abstract class Buff
{
	String name, desc;
	final Character CHARACTER;
	int duration;
	
	public Buff(String n, String dsc, Character c, int d)
	{
		name = n;
		desc = dsc;
		CHARACTER = c;
		duration = d;
	}

	public void decreaseDuration()
	{
		if(duration >= 1)
			duration--;
		else if(duration == 0)
			this.deletThis();		//when buffs are infinite (duration == -1), they are never deleted or altered
	}
	
	public void deletThis()
	{
		CHARACTER.buffs.remove(this);
		System.out.println(CHARACTER + "'s " + name + " buff wore off.");
	}
	
	public String toString()
	{
		return name + "-" + desc + " Lasting " + duration + " more turns.";
	}

	public abstract void applyAttackEffect(Character target, boolean already_critted);// ex. Mana Break
	public abstract void applyAttackedEffect(Character c);// ex. Blur
	public abstract void applyEffectOnTurnStart();// ex. stuns(Storm Hammer)
}
