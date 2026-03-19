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
    public static String encrypt(String input, int key)
    {
        StringBuilder output = new StringBuilder();
        
        key = (key % 26 + 26) % 26;
        
        for(char character : input.toCharArray())
        {
            if(Character.isLetter(character))
            {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                char result = (char) ((character - base + key) % 26 + base);
                output.append(result);
            }
            else
            {
                //ignore stuff like spaces and pucntuations
                output.append(character);
            }
        }
        return output.toString();
    }    
}
