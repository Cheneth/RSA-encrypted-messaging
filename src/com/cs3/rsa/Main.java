package com.cs3.rsa;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
			LinkedList<Contact> contacts = new LinkedList<Contact>();
			
			boolean repeat = true;
			
			while(repeat){
				int choice = 0;
				
				System.out.println("1. Add Contact");
				System.out.println("2. Exit");
				
				for(int i = 0; i < contacts.size(); i++){
					System.out.printf("%d. %s\n", i+3, contacts.get(i).getName());
				}
				
				choice = errorTrap(1, 2+contacts.size());
				switch(choice){
				case 1:{
					
					int prime1 = Calculations.prime();
					int prime2 = Calculations.prime();
					int pq = prime1*prime2;
					int ogpq = pq;
					int e = Calculations.coPrime((prime1-1)*(prime2-1));
					int d = Calculations.inverse(e, (prime1-1)*(prime2-1));
					
					System.out.print("Please enter the new contact's name: ");
					String name = stringInput();
					System.out.println();
					System.out.println("New contact's public keys (please send theese to your contact):");
					System.out.println("Public key 1: " + e);
					System.out.println("Public key 2: " + pq);
					System.out.println();
					System.out.print("Please enter your received public key 1: ");
					e = errorTrap();
					System.out.println();
					System.out.print("Please enter your received public key 2: ");
					pq = errorTrap();
					System.out.println();
					contacts.push(new Contact(name, e, pq, d, ogpq));
					System.out.printf("Contact \"%s\" added.\n", name);
					break;
				}
				case 2:{
					System.exit(0);
					break;
				}
				
				default:{
					
					Contact temp = contacts.get(choice-3);
					System.out.printf("CONTACT: %s\n", temp.getName());
					System.out.println("1. Send message");
					System.out.println("2. Receive message");
					System.out.println("3. Back to menu");
					choice = errorTrap(1, 3);
					
					switch(choice){
					case 1:{//encrypt
						System.out.println();
						System.out.println("Please type out your message and press the ENTER key when you are done:");
						String input = stringInput();
						
						LinkedList<Long> encrypted = encrypt(input, temp.getPub1(), temp.getPub2());
						
						Iterator iterator = encrypted.iterator();
						
						System.out.println("Encrypted message:");
						
						while(iterator.hasNext()){
							System.out.print(iterator.next() + " ");
						}
						
						System.out.println();
						break;
					}
					case 2:{//decrypt
						System.out.println();
						System.out.println("Please paste the received encrypted message here and press the ENTER key to decrypt:");
						
						LinkedList<Long> encrypted = new LinkedList<Long>();
						String input = stringInput();
						Scanner scan1 = new Scanner(input);
						
						while(scan1.hasNextInt()){
							//build the list of Longs to decrypt
									encrypted.add(scan1.nextLong());
								
						}
						
						LinkedList<Character> message = decrypt(encrypted, temp.getPriv1(), temp.getPriv2());
						
						Iterator iterator2 = message.iterator();
						System.out.println("Decrypted message: ");
						while(iterator2.hasNext()){
							
							System.out.print(iterator2.next());
						}
						System.out.println();
						break;
						
						
					}
					}
				}
			}
		}
			
		
		//System.out.println(Calculations.prime());
		
		int prime1 = Calculations.prime();
		int prime2 = Calculations.prime();
		
		//prime1 = 11;
		//prime2 = 29;
		
		int pq = prime1*prime2;
		
		System.out.println("p: " + prime1);
		System.out.println("q: " + prime2);
		
		int e = Calculations.coPrime((prime1-1)*(prime2-1));
		//e = 13;
		System.out.println("e: " + e);
		System.out.println("(p-1)(q-1): " + ((prime1-1)*(prime2-1)));
		int d = Calculations.inverse(e, (prime1-1)*(prime2-1));
		
		System.out.println("d: " + d);
		
		String input = "Hey, you're a great guy";
		
		LinkedList<Long> encrypted = encrypt(input, e, pq);
		
		Iterator iterator = encrypted.iterator();
		
		while(iterator.hasNext()){
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
		
		LinkedList<Character> message = decrypt(encrypted, d, pq);
		
		Iterator iterator2 = message.iterator();
		
		while(iterator2.hasNext()){
			
			System.out.print(iterator2.next());
		}
		
		//BigInteger big = new BigInteger();
		
		//long m = (long)Math.pow(15, e)%(prime1*prime2);
		
	//	BigInteger E = BigInteger.valueOf(e);
		
	//	BigInteger D = BigInteger.valueOf(d);
	//	
		//BigInteger PQ = BigInteger.valueOf(prime1*prime2);
		
	//	BigInteger M = new BigInteger("15");
		
	//	BigInteger ENCRYPTED = M.modPow(E, PQ);
		
		//System.out.println(ENCRYPTED);
		
		//BigInteger DECRYPTED = ENCRYPTED.modPow(D, PQ);
		
	//	System.out.println(DECRYPTED);
		
		
		
		//long m = Calculations.pow(15, e, (prime1*prime2));
		//System.out.println("m: " + M);
		//System.out.println("NSGGER");
		//System.out.println(Calculations.pow(M, d, (prime1*prime2)));
		
		//Scanner scan = new Scanner(System.in);
		
		//int num = scan.nextInt();
		//int mod = scan.nextInt();
		
		//System.out.println(Calculations.inverse(num, mod));
		
	}
	
	private static int errorTrap(int min, int max){//error trap for numbers with a min and max
		
		int input = 0;
		
		boolean valid = false;
		
		Scanner scan = new Scanner(System.in);
		
		while(valid == false){
			try{
				input = scan.nextInt();
				if(input >= min && input <= max){
					valid = true;
				}
			}catch(Exception exception){
				
			}
			
		}
		
		return input;
		
	}
	
	private static int errorTrap(){//error trap for numbers without a min and max
		
		int input = 0;
		
		boolean valid = false;
		
		Scanner scan = new Scanner(System.in);
		
		while(valid == false){
			try{
				input = scan.nextInt();
				
					valid = true;
				
			}catch(Exception exception){
				
			}
			
		}
		
		return input;
		
	}
	
	private static String stringInput(){
		
		String input = " ";
		
		boolean valid = false;
		
		Scanner scan = new Scanner(System.in);
		
		while(valid == false){
			try{
				input = scan.nextLine();
				valid = true;
			}catch(Exception exception){
				
			}
			
		}
		
		return input;
		
	}
	
	private static LinkedList<Long> encrypt(String input, int e, int pq){//takes in string, outputs a list of chars in ascii format
		LinkedList<Long> list = new LinkedList<Long>();
		
		BigInteger E = BigInteger.valueOf(e);
		BigInteger PQ = BigInteger.valueOf(pq);
		
		
		for(int i = 0; i < input.length(); i++){
			
			BigInteger CHAR = BigInteger.valueOf(input.charAt(i));
			
			BigInteger ENCRYPTED = CHAR.modPow(E, PQ);
			
			list.push(ENCRYPTED.longValue());
		}
		
		return list;
	}

	private static LinkedList<Character> decrypt(LinkedList<Long> list, int d, int pq){//takes in list of encrypted LONGS and outputs a list of chars that contains the message
		LinkedList<Character> message = new LinkedList<Character>();
		
		BigInteger D = BigInteger.valueOf(d);
		BigInteger PQ = BigInteger.valueOf(pq);
		
		Iterator iterator = list.iterator();
		
		while(iterator.hasNext()){
			BigInteger M = BigInteger.valueOf((long) iterator.next());
			
			BigInteger DECRYPTED = M.modPow(D, PQ);
			
			//System.out.println((char)DECRYPTED.intValue());
			
			message.push((char) DECRYPTED.intValue());
			
		}
		
		return message;
		
	}
	
}
