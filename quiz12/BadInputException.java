package quiz12;

import java.io.IOException;

/**
   This class reports bad input data.
*/
public class BadInputException extends IOException
{
   public BadInputException() {}
   public BadInputException(String message)
   {
      super(message);
   }
}
