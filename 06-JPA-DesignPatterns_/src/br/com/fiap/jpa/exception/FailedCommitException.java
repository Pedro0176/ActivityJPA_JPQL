package br.com.fiap.jpa.exception;

public class FailedCommitException extends Exception{

	public FailedCommitException() {
		super();
		
	}

	public FailedCommitException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public FailedCommitException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public FailedCommitException(String arg0) {
		super(arg0);
		
	}

	public FailedCommitException(Throwable arg0) {
		super(arg0);
		
	}
	
	
	
	

}
