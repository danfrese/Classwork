package quiz12;

import java.io.IOException;

/**
   This class reports bad input data.
*/
public class BadInputException extends IOException
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public BadInputException() {}
   public BadInputException(String message)
   {
      super(message);
   }
}
