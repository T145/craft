package T145.craft.err;

public class UnknownKeywordException extends Exception {

	public UnknownKeywordException() {
		this("Invalid keyword! Register it first.");
	}

	public UnknownKeywordException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UnknownKeywordException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UnknownKeywordException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public UnknownKeywordException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
}