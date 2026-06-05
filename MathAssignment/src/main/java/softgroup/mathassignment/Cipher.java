/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softgroup.mathassignment;

/**
 *
 * @author Prize
 */
public class Cipher 
{
    public static String encrypt(String input, String keyStr)
    {
        StringBuilder output = new StringBuilder();
        
        // check key is digit
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
                char result = (char) ((character - base + shift) % 26 + base);
                output.append(result);
                
                keyIndex++;
            }
            else
            {
                //remove punctuation and whitespace
                output.append(character);
            }
        }
        return output.toString();
    }    
}
