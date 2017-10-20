package T145.craft;

import java.io.FileNotFoundException;
import java.io.IOException;

import T145.craft.keywords.KeywordAdd;
import T145.craft.lib.KeywordDB;
import T145.craft.lib.KeywordHolder;

public class Main {

	public static void main(String[] args) {
		try {
			KeywordHolder holder = new KeywordHolder(new KeywordDB().add(new KeywordAdd()), "C:\\Users\\taylo\\Desktop\\craftlang\\test.craft");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}