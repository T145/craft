package T145.craft.api;

public interface IKeyword {

	@Override
	public String toString();

	public String getWord();

	public String getName();

	public void setName(String name);

	public int getId();

	public void setId(int id);

	public String getProperties();

	public void setProperties(String props);

	public boolean isValid();

	public void execute();

	public IKeyword copy();
}