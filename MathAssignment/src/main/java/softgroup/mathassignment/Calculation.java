/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softgroup.mathassignment;

import java.math.BigInteger;

/**
 *
 * @author Prize
 */
public class Calculation 
{
    private BigInteger p;
    private BigInteger g;
    private BigInteger personalKey;
    
    public Calculation(BigInteger p, BigInteger g, BigInteger personalKey)
    {
        this.p = p;
        this.g = g;
        this.personalKey = personalKey;
    }
    
    public BigInteger getPublicKey()
    {
        return g.modPow(personalKey, p);
    }
    
    public BigInteger cipherKey(BigInteger key)
    {
        return key.modPow(personalKey, p);
    }    
}
