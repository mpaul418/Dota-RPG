 package buffs;

import java.util.ArrayList;

import classes.Characters;

public abstract class Buff
{
	String name, desc;
	final Characters CHARACTER;
	int duration;
	
	public Buff(String n, String dsc, Characters c, int d)
	{
		name = n;
		desc = dsc;
		CHARACTER = c;
		duration = d;
		//if(d >= 0)			  	// if the buff is not passive
			//duration++;		 	// the 1 is to account for the turn spent casting the buff
		
		//TODO maybe decrease buff durations at start of turn?? or maybe stat buffs should get their durations increased by 1
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return desc;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public void decreaseDuration()
	{
		if(duration >= 1)
			duration--;
		if(duration == 0)
			deletThis();					//when buffs are infinite (duration == -1), they are never deleted or altered
	}
	
	public void decreaseDurationNoNotifier()
	{
		if(duration >= 1)
			duration--;
		if(duration == 0)
			deletThisNoNotifier();	
	}

	public void decreaseDuration(ArrayList<Buff> deleted_buffs) 
	{
		if(duration >= 1)
			duration--;
		if(duration == 0)
			deleted_buffs.add(this);		//when buffs are infinite (duration == -1), they are never deleted or altered
	}
	
	public void deletThis()
	{
		CHARACTER.buffs.remove(this);
		System.out.println(CHARACTER + "'s " + name + " buff wore off.");
	}
	
	public void deletThisNoNotifier()
	{
		CHARACTER.buffs.remove(this);
	}
	
	public String toString()
	{
		if(duration > 0) //if the buff is not passive(infinite)
			return name + "-" + desc + "(Lasting " + duration + " more turn(s).)";
		else
			return name + "-" + desc + "(Passive effect.)";
	}
}
