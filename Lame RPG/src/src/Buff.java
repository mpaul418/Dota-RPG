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
		else
			this.removeThis();			
	}
	
	private void removeThis()
	{
		CHARACTER.buffs.remove(this);		
	}
	
	public String toString()
	{
		return name + "-" + desc + " Lasting " + duration + " more turns.";
	}

	public abstract void applyAttackEffect(Character t);
	
	public abstract void applyEffectOnTurnStart();
}
