package com.cs3.rsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Calculations {

	public static int inverse(int num, int mod){//finds inverse of num with respect to mod, returns 0 is there is no inverse
		
		Stack<Euclidean> stack = new Stack<Euclidean>();
		
		Euclidean temp;
		
		temp = new Euclidean(num, mod);
		
		stack.push(temp);
		
		while(stack.peek().getC() != 1){//creating the equations
			
			temp = new Euclidean(stack.peek().getC(), stack.peek().getB());
			
			if(temp.getC() == 0){
				return 0;
			}
			
			stack.push(temp);
			
			
		}
		
		temp = Euclidean.clone(stack.peek());
		
		while(stack.size() > 1){//solving for the inverse
			int k = temp.getK();
			int k2 = temp.getK2();
			stack.pop();
			
			temp.setK(k2+(k*stack.peek().getK()));
			temp.setK2(k*stack.peek().getK2());
			
		}
		
		int inverse = temp.getK();
		
		if(inverse > 0){//if negative the smallest positive inverse will be found
			return inverse;
		}else{
			
			inverse += (((int) Math.floor(inverse/mod)+1)*mod);
			return inverse;
		}
		
		
		
		
	}
	
	public static int coPrime(int number){//generate a number coprime with 'number'
		int coPrime = 2;
		boolean found = false;
		
		while(found == false){
			if(gdc(coPrime, number) == 1){
				return coPrime;
			}else{
				coPrime++;
			}
		}
		
		return 0;
		
		
	}
	
	public static int gdc(int num1, int num2){//find greatest common denominator between num1 and num2
		int temp;
		
		while(num2 != 0){
			temp = num1;
			num1 = num2;
			num2 = temp%num1;
		}
		return num1;
	}
	
	public static long pow(long m, long d, long mod){//using modulus to keep the number small enough (m^d)
		for(int x = 0; x < d-1; x++){
			m*=m;
			m%=mod;
		}
		
		return m;
	}
	
	public static int prime() throws FileNotFoundException{//returns prime number > 1000000000
		
		File infile = new File("primes.txt");
		
		Scanner scan = new Scanner(infile);
		
		
		int count = 0;
		int random = (int) (Math.random() * (200));
		
		while(scan.hasNextInt()){
			
			if(count == random){
				return scan.nextInt();
			}
			scan.nextInt();
			count++;
		}
		System.out.println(count);
		
		return 10003351;
		
	}
	
}
