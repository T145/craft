package T145.craft;

import java.io.FileNotFoundException;
import java.io.IOException;

import T145.craft.api.IKeyword;
import T145.craft.err.FileFormatException;
import T145.craft.err.UnknownKeywordException;
import T145.craft.keywords.KeywordAdd;
import T145.craft.sys.CraftStack;

public class Main {

	public static void main(String[] args) {
		try {
			CraftStack systemStack = new CraftStack("C:\\Users\\taylo\\Desktop\\GitHub\\craft\\scripts\\add_test.craft");
			IKeyword add = new KeywordAdd();
			systemStack.registerKeyword(add);
			systemStack.readKeywordsFromFile();
			systemStack.run();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownKeywordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}