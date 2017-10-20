package T145.craft.lib;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import T145.craft.api.IKeyword;

public class KeywordDB {

	private List<IKeyword> keywords;
	private int maxLength;

	public KeywordDB() {
		this.keywords = new ArrayList<IKeyword>();
	}

	public KeywordDB add(IKeyword keyword) {
		keywords.add(keyword);

		int length = keyword.getWord().length();

		if (length > maxLength) {
			maxLength = length;
		}

		return this;
	}

	public KeywordDB remove(IKeyword keyword) {
		keywords.remove(keyword);
		return this;
	}

	public KeywordDB removeLast() {
		keywords.remove(keywords.size() - 1);
		return this;
	}

	public boolean isEmpty() {
		return keywords.isEmpty();
	}

	// @Nullable
	public IKeyword getMappedKeyword(String s) {
		if (!s.isEmpty()) {
			String temp = ""; // copy string which we edit
			String props = StringUtils.substringBetween(s, "{", "}");

			System.out.println(s);
			System.out.println(props);

			for (IKeyword keyword : keywords) {
				String word = keyword.getWord();

				if (s.startsWith(word)) {
					temp = s.substring(word.length(), s.length()).replaceAll("\\{.*?\\} ?", "");
					System.out.println(temp);
					keyword.setName(temp.replaceAll("\\P{L}+", ""));
					temp = temp.replaceAll("\\D+", ""); // we're not using it anymore
					keyword.setId(temp.isEmpty() ? keyword.getId() + 1 : Integer.parseInt(temp));
					keyword.setProperties(props);
					return keyword;
				}
			}
		}

		return null;
	}

	// @Nullable
	public IKeyword getKeywordFromWord(String s) {
		if (!s.isEmpty()) {
			for (IKeyword keyword : keywords) {
				String word = keyword.getWord();

				if (word.compareTo(s) == 0) {
					return keyword;
				}
			}
		}

		return null;
	}
}