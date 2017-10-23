package T145.craft.err;

@SuppressWarnings("serial")
public class FileFormatException extends Exception {

	public FileFormatException() {
		this("An entry doesn't match proper keyword format!");
	}

	public FileFormatException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public FileFormatException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public FileFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public FileFormatException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
}