package customExceptions;

public class FileHandlerException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	public FileHandlerException(String errLocation, String errType, String value) {
		
		System.err.print("\nError at " + errLocation + " with " + errType 
							+ ". Value (" + value + ") is invalid.\n");
		
	}
	
	public FileHandlerException(String errLocation, String errType, int value) {
		
		System.err.print("\nError at: " + errLocation + " with " + errType
							+ ". Value (" + value + ") is invalid.\n");
		
	}

}
