package softgroup.mathassignment;

/**
 *
 * @author Prize
 */

public class Decrypt
{
    public static String decrypt(String input, String keyStr)
    {
        StringBuilder output = new StringBuilder();

        keyStr = keyStr.replaceAll("[^0-9]", "");
        if (keyStr.isEmpty()) {
            keyStr = "0";
        }

        int keyIndex = 0;
        int keyLength = keyStr.length();

        for (char character : input.toCharArray())
        {
            if (Character.isLetter(character))
            {
                int shift = Character.getNumericValue(keyStr.charAt(keyIndex % keyLength));
                
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                // Subtract the shift instead of adding it
                char result = (char) ((character - base - shift + 26) % 26 + base);
                output.append(result);
                
                keyIndex++;
            }
            else
            {
                output.append(character);
            }
        }
        return output.toString();
    }
}