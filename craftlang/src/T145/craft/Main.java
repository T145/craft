package T145.craft;

import java.io.FileNotFoundException;
import java.io.IOException;

import T145.craft.err.FileFormatException;
import T145.craft.err.UnknownKeywordException;
import T145.craft.keywords.KeywordAdd;
import T145.craft.sys.CraftStack;

public class Main {

	public static void main(String[] args) {
		try {
			CraftStack systemStack = new CraftStack("D:\\Users\\taylo\\eclipse-workspace\\craft\\scripts\\add_test.craft");
			systemStack.register(new KeywordAdd());
			systemStack.readKeywordsFromFile();
			systemStack.execute();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnknownKeywordException e) {
			e.printStackTrace();
		} catch (FileFormatException e) {
			e.printStackTrace();
		}
	}
}