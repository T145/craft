package T145.craft.lib;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import T145.craft.api.IKeyword;
import T145.craft.err.FileFormatException;
import T145.craft.err.UnknownKeywordException;

public class KeywordHolder {

	public static final String EXTENSION = ".craft";

	private Queue<String> holder;
	private KeywordDB db;
	private String filePath;
	private boolean valid;

	private void readFile() throws FileNotFoundException, IOException {
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "Hello World!";

		while ((line = bufferedReader.readLine()) != null) {
			// System.out.println(line);

			line = line.replaceAll("\t", " ").replaceAll(" ", "");

			// cases 1 & 2; it's too easy to fix
			if (!line.isEmpty() && !(line.charAt(0) == '#' || line.charAt(0) == '#')) {
				if (line.contains("#")) { // case 3
					line = line.substring(0, line.indexOf('#'));
				}

				holder.add(line);
			}
		}

		fileReader.close();
		bufferedReader.close();
	}

	/*
	 * Format cases:
	 * case 1: ignore if line is empty
	 * case 2: ignore if line starts w/ comment
	 * case 3: ignore comments after code
	 * case 4: properly formatted; -add(person)[1]{name:Greg,age:42,height:5'4"}
	 * case 5: improperly formatted w/out whitespace: -addperson1{name:Greg,age:42,height:5'4"}
	 * case 6: lingering comments that need to be removed up to first unique char or keyword
	 * case 7: no valid keyword or other syntax errors (throw new exception)
	 */
	private void formatFile() throws FileFormatException, UnknownKeywordException {
		String min = "";

		while (!holder.isEmpty()) {
			IKeyword keyword = db.getMappedKeyword(holder.remove());

			if (keyword == null) {
				throw new UnknownKeywordException();
			}

			if (keyword.isValid()) {
				min += keyword.toString() + '\n';
			} else { // TODO: Add support for multi-line data entry
				throw new FileFormatException(); // TODO: Add intelligent error reports (don't do this, do this)
			}
		}

		System.out.println(min);
	}

	public KeywordHolder(KeywordDB db, String filePath) throws FileNotFoundException, IOException {
		this.holder = new LinkedList<String>();
		this.db = db;
		this.filePath = filePath;
		this.valid = filePath.substring(filePath.length() - EXTENSION.length(), filePath.length()).equals(EXTENSION);

		readFile();

		try {
			formatFile();
		} catch (FileFormatException e) {
			e.printStackTrace();
		} catch (UnknownKeywordException e) {
			e.printStackTrace();
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public boolean isValid() {
		return valid;
	}
}