package T145.craft.keywords;

import org.apache.commons.lang3.StringUtils;

import T145.craft.api.BaseKeyword;

public class KeywordAdd extends BaseKeyword {

	public KeywordAdd() {
		super("add");
	}

	@Override
	public void execute(Object... objects) {
		String[] s = StringUtils.split(getProperties(), ',');
		
		for (String prop : s) {
			if (prop.startsWith("num")) {
				System.out.println(prop);
			}
		}
	}
}