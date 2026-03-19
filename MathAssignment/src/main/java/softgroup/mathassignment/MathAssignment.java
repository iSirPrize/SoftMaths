/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package softgroup.mathassignment;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Prize
 */
public class MathAssignment 
{

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Public p: ");
        BigInteger p = new BigInteger(sc.next());
        
        System.out.print("\nPublic g: ");
        BigInteger g = new BigInteger(sc.next());
        
        System.out.print("\nIntput personal key: ");
        BigInteger personalKey = new BigInteger(sc.next());
        Calculation keyCalc = new Calculation(p, g, personalKey);
        
        //get public key to share with confidant
        BigInteger shareKey = keyCalc.getPublicKey();
        
        System.out.print("\nPublic key is: " + shareKey);
        
        System.out.print("\nInput public key recieved from confidant: ");
        BigInteger recievedKey = new BigInteger(sc.next());
        sc.close();
        
        System.out.print("\nCipher key result: " + keyCalc.cipherKey(recievedKey));
    }
}
