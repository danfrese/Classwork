package quiz21;

/**
   An item with a key and a value.
*/
public class ItemVerbose implements Comparable<ItemVerbose>
{
   private String key;
   private String value;

   /**
      Constructs an Item object.
      @param k the key string
      @param v the value of the item
   */
   public ItemVerbose(String k, String v)
   { 
      key = k;
      value = v;
   }
   
   /**
      Gets the key.
      @return the key
   */
   public String getKey()
   { 
      return key;
   }
   
   /**
      Gets the value.
      @return the value
   */
   public String getValue()
   { 
      return value;
   }

   /**
    * Compare this Item with the other.
    * @return less than 0 if this is less than the other,
    *  0 if this is equal to the other, and greater than
    *   0 if this is greater than the other
    */
   public int compareTo(ItemVerbose other)
   {
      //System.out.printf("compareTo: comparing Item %s with %s\n", this.toString(), other.toString());
      return key.compareTo(other.key);
   }
   
   /**
    * Represent this object as a string.
    * @return String representing this object.
    */
   public String toString()
   {
	   return '[' + key + ", " + value + ']';
   }
}
