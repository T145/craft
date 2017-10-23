package T145.craft.keywords;

import T145.craft.api.BaseKeyword;
import T145.craft.api.IKeyword;

public class KeywordAdd extends BaseKeyword {

	public KeywordAdd() {
		super("add");
	}

	@Override
	public IKeyword copy() {
		return new KeywordAdd();
	}

	@Override
	public void execute() {
	}
}