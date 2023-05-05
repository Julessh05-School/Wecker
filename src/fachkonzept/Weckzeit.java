package fachkonzept;

public final class Weckzeit extends Uhrzeit
{
	private int minuten;
	private int stunden;
	
	public Weckzeit()
	{
		minuten = 0;
		stunden = 0;
	}
	
	public void inkrementiereMinuten()
	{
		if (minuten < 59)
		{
			minuten++;
		}
		else
		{
			minuten = 0;
		}
	}

	public void inkrementiereStunden()
	{
		if (stunden < 23)
		{
			stunden++;
		}
		else
		{
			stunden = 0;
		}
	}

	public int liesMinuten()
	{
		return minuten;
	}

	public int liesStunden()
	{
		return stunden;
	}
}
