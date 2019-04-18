
public class Person {
	private String _status;

	public Person(String initial)
	{
		this._status = initial;
	}

	public Person wear(Wearable wearable)
	{
		this._status += wearable.wear();
		return this;
	}

	public void show()
	{
		System.out.println(_status);
	}

}
