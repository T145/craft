package T145.craft.sys;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang3.StringUtils;

import T145.craft.api.IKeyword;
import T145.craft.err.FileFormatException;
import T145.craft.err.UnknownKeywordException;

public class CraftQueue {

	public static final String FILE_EXTENSION = ".craft";

	private String filePath;
	private List<IKeyword> keywordDB = new ArrayList<IKeyword>();
	private Queue<IKeyword> keywords = new LinkedList<IKeyword>();
	private int minId;

	public CraftQueue(String filePath) throws FileNotFoundException, IOException, UnknownKeywordException, FileFormatException {
		this.filePath = filePath;
	}

	public CraftQueue register(IKeyword keyword) {
		keywordDB.add(keyword);
		return this;
	}

	public CraftQueue unregister(IKeyword keyword) {
		keywordDB.remove(keyword);
		return this;
	}

	public boolean isValid() {
		return !filePath.isEmpty() && filePath.substring(filePath.length() - FILE_EXTENSION.length(), filePath.length()).equals(FILE_EXTENSION);
	}

	private IKeyword getSanitizedKeyword(String s) throws UnknownKeywordException {
		if (!s.isEmpty()) {
			String temp = StringUtils.EMPTY; // copy string which we edit
			String props = StringUtils.substringBetween(s, "{", "}");

			for (IKeyword keyword : keywordDB) {
				if (s.startsWith(keyword.getWord())) {
					IKeyword match = keyword.copy();
					temp = s.substring(match.getWord().length(), s.length()).replaceAll("\\{.*?\\} ?", "");
					match.setName(temp.replaceAll("\\P{L}+", ""));
					temp = temp.replaceAll("\\D+", ""); // we're not using it anymore
					match.setId(temp.isEmpty() ? minId++ : Integer.parseInt(temp));
					match.setProperties(props == null ? StringUtils.EMPTY : props);
					return match;
				}
			}
		}

		// case 7
		throw new UnknownKeywordException();
	}

	/*
	 * Format cases:
	 * case 1: ignore if line is empty
	 * case 2: ignore if line starts w/ comment
	 * case 3: ignore comments after code
	 * case 4: properly formatted; add(person)[1]{name:Greg,age:42,height:5'4"}
	 * case 5: improperly formatted w/out whitespace: addperson1{name:Greg,age:42,height:5'4"}
	 * case 6: lingering comments that need to be removed up to first unique char or keyword
	 * case 7: no valid keyword
	 * case 8: other syntax errors (throw new exception)
	 */
	public void readKeywordsFromFile() throws FileNotFoundException, IOException, UnknownKeywordException, FileFormatException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = StringUtils.EMPTY;

			while ((line = reader.readLine()) != null) {
				line = line.replaceAll("\t", " ").replaceAll(" ", "");

				// cases 1 & 2; it's too easy to fix
				if (!line.isEmpty() && !(line.charAt(0) == '#' || line.charAt(0) == '#')) {
					if (line.contains("#")) { // case 3
						line = line.substring(0, line.indexOf('#'));
					}

					// cases 4-6
					IKeyword keyword = getSanitizedKeyword(line);

					// case 8
					if (keyword.isValid()) {
						keywords.add(keyword);
					} else {
						// TODO: Add support for multi-line data entry
						// TODO: Add intelligent error reports ( i.e. don't do this, do this)
						throw new FileFormatException();
					}
				}
			}
		}
	}

	public void execute() {
		while (!keywords.isEmpty()) {
			System.out.println(keywords.remove());
		}
	}
}