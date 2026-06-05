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
        int mode = 0;
        String sharedKeyStr = "";
        boolean running = true;
        Calculation keyCalc = null;
        
        while(running)
        {
            System.out.print("Welcome to Pacific Secure Networks Ltd Encryption Service");
            System.out.print("\n\nPlease select a service:");
            System.out.println("\n1: Generate Public Key\n2: Generate Shared private Key (User must have generated public key)\n3: Encrypt Communication\n4: Decrypt Communication\n5: Exit");
            String choice = sc.nextLine().trim();
        
            switch(choice)
            {
                case "1":
                    keyCalc = publicKey(sc);
                    break;
                
                case "2":
                    if(keyCalc == null)
                    {
                        while(true)
                        {   
                            System.out.println("No active keys found\nWould you like to generate key (y/n)");
                            String check = sc.nextLine().trim().toLowerCase();
                        
                            if(check.equals("y"))
                            {
                                keyCalc = publicKey(sc);
                                break;
                            }
                            else if(check.equals("n"))
                            {
                                System.out.println("Returning to menu\n");
                                break;
                            }
                            else
                            {
                                System.out.println("Please input 'y' or 'n'");
                            }
                        }                        
                    }
                    
                    if(keyCalc != null)
                    {
                        sharedKeyStr = genSharedPrivKey(sc, keyCalc);
                        System.out.println("cipher key: " + sharedKeyStr);
                    }                    
                    break;
                    
                case "3":                    
                    if(sharedKeyStr.isEmpty())
                    {
                        while(true)
                        {
                            System.out.println("User Shared Private Key not found\nWould you like to input key manually (y/n)");
                            String check = sc.nextLine().trim().toLowerCase();
                            
                            if(check.equals("y"))
                            {
                                System.out.println("Please input Shared Private Key");
                                String userPrivKey = sc.nextLine().trim();
                                
                                try
                                {
                                    new BigInteger(userPrivKey);
                                    sharedKeyStr = userPrivKey;
                                    System.out.println("Private key\n" + sharedKeyStr + "\naccepted");
                                    break;
                                }catch(NumberFormatException e)
                                {
                                    System.out.println("Invalid input. Please enter integer key");
                                }
                            }
                            else if(check.equals("n"))
                            {
                                System.out.println("Returning to menu\n");
                                break;
                            }
                            else
                            {
                                System.out.println("Please input 'y' or 'n'");
                            }
                        }
                    }                    
                    if(!sharedKeyStr.isEmpty())
                    {
                        encryptComs(sc, sharedKeyStr);
                    }
                    break;
                    
                case "4":
                    if(sharedKeyStr.isEmpty())
                    {
                        while(true)
                        {
                            System.out.println("User Shared Private Key not found\nWould you like to input key manually (y/n)");
                            String check = sc.nextLine().trim().toLowerCase();
                            
                            if(check.equals("y"))
                            {
                                System.out.println("Please input Shared Private Key");
                                String userPrivKey = sc.nextLine().trim();
                                
                                try
                                {
                                    new BigInteger(userPrivKey);
                                    sharedKeyStr = userPrivKey;
                                    System.out.println("Private key\n" + sharedKeyStr + "\naccepted");
                                    break;
                                }catch(NumberFormatException e)
                                {
                                    System.out.println("Invalid input. Please enter integer key");
                                }
                            }
                            else if(check.equals("n"))
                            {
                                System.out.println("Returning to menu\n");
                            }
                            else
                            {
                                System.out.println("Please input 'y' or 'n'");
                            }
                        }                        
                    }
                    if(!sharedKeyStr.isEmpty())
                    {
                        decryptComs(sc, sharedKeyStr);
                    }
                    break;
                
                case "5":
                    running = false;
                    System.out.println("\n Thanks for using PSNL Encryption Service");
                    break;
                    
                default:
                    System.out.println("Please input valid response 1-5");
                    break;              
            }
        }

        sc.close();
    }    
    
    public static Calculation publicKey(Scanner sc)
    {
         System.out.print("Public p: ");
        BigInteger p = new BigInteger(sc.next());
        
        System.out.print("\nPublic g: ");
        BigInteger g = new BigInteger(sc.next());
        
        System.out.print("\nIntput personal key: ");
        BigInteger personalKey = new BigInteger(sc.next());
        Calculation key = new Calculation(p, g, personalKey);
        
        //get public key to share with confidant
        BigInteger shareKey = key.getPublicKey();
        
        System.out.print("\nPublic key is: " + shareKey);
        System.out.println();
        System.out.println();
        sc.nextLine();
        
        return key;
    }
    
    public static String genSharedPrivKey(Scanner sc, Calculation key)
    {
        System.out.print("\nInput public key recieved from confidant: ");
        BigInteger recievedKey = new BigInteger(sc.next());
        
        BigInteger fullSharedKey = key.cipherKey(recievedKey);
        System.out.print("\nShared Private Key: " + fullSharedKey);
        System.out.println();
        System.out.println();
        sc.nextLine();
        
        // Return the key with base26 shift
        return fullSharedKey.toString();
    }
    
    public static void encryptComs(Scanner sc, String sharedKeyStr)
    {
        System.out.print("\nInput message to encrypt: ");
        String encryptMsg = sc.nextLine();
        
        String msg = Cipher.encrypt(encryptMsg, sharedKeyStr);
        
        System.out.println("Message encrypted: ");
        System.out.println(msg);
        System.out.println();
        System.out.println();
    }
    
    public static void decryptComs(Scanner sc, String sharedKeyStr)
    {
        System.out.print("\nInput message to decrypt: ");
        String receivedEncryptedMsg = sc.nextLine();

        String decryptedReceivedMsg = Decrypt.decrypt(receivedEncryptedMsg, sharedKeyStr);

        System.out.println("Message decrypted: ");
        System.out.println(decryptedReceivedMsg);
        System.out.println();
        System.out.println();
    }
}
