/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.core.distribution;

import java.util.Random;
import org.apache.commons.math3.distribution.WeibullDistribution;

/**
 *
 * @author lite
 */
public class Weibull {
    static Random r = new Random();
    static double alp = 0.764;
    static double lam = 8.25;
    public Weibull() {
        
    }
    
    
    public static String getSimulation() {
        
        WeibullDistribution distribution = new WeibullDistribution(alp, lam);
        Double number;
        String s ="";
        for(int i=0;i<100;i++){
            number = 2+distribution.sample();
            s = s.concat(number.intValue()+"\n");
        }
        return s;
    }
    
}


