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
		//if(d != -1) // If the buff is not passive, its duration is increased because buffs tick at the start of each turn
			//duration = d + 1;
		//else
			duration = d;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return desc;
	}
	
	public void decreaseDuration()
	{
		if(duration >= 1)
			duration--;
		if(duration == 0)
			deletThis();		//when buffs are infinite (duration == -1), they are never deleted or altered
	}

	public void decreaseDuration(ArrayList<Buff> deleted_buffs) // TODO FIXME not properly deleting buffs
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
	
	public String toString()
	{
		if(duration > 0) //if the buff is not passive(infinite)
			return name + "-" + desc + "(Lasting " + duration + " more turns.)";
		else
			return name + "-" + desc + "(Passive effect.)";
	}
}
