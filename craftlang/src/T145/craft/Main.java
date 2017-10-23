package T145.craft;

import java.io.FileNotFoundException;
import java.io.IOException;

import T145.craft.err.FileFormatException;
import T145.craft.err.UnknownKeywordException;
import T145.craft.sys.CraftQueue;

public class Main {

	public static void main(String[] args) {
		try {
			CraftQueue workbench = new CraftQueue("D:\\Users\\taylo\\eclipse-workspace\\craft\\scripts\\add_test.craft");
			workbench.register("add");
			workbench.readKeywordsFromFile();
			workbench.execute();
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