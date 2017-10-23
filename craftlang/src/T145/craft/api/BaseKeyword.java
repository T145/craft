package T145.craft.api;

public abstract class BaseKeyword implements IKeyword {

	private String word;
	private String name;
	private String properties;
	private int id = -1;

	public BaseKeyword(String word) {
		this.word = word;
	}

	@Override
	public String getWord() {
		return word;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getProperties() {
		return properties;
	}

	@Override
	public void setProperties(String props) {
		this.properties = props;
	}

	@Override
	public String toString() {
		String out = word + "(" + name + ")" + "[" + id + "]";

		if (!properties.isEmpty()) {
			out += "{" + properties + "}";
		}

		return out;
	}

	@Override
	public boolean isValid() {
		return word != null && name != null && properties != null;
	}
}