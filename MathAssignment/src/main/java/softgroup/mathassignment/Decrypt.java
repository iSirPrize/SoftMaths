package softgroup.mathassignment;
public class Decrypt
{
    public static String decrypt(String input, int key)
    {
        StringBuilder output = new StringBuilder();

        key = (key % 26 + 26) % 26;

        for(char character : input.toCharArray())
        {
            if(Character.isLetter(character))
            {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                char result = (char) ((character - base - key + 26) % 26 + base);
                output.append(result);
            }
            else
            {
                output.append(character);
            }
        }
        return output.toString();
    }
}