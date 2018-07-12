package com.cs3.rsa;

public class Euclidean {

	int m = 0;
	int k = 0;
	int k2 = 1;
	int b = 0; 
	int c = 0;
	
	//form 1: m =  k(b) + c
	//form 2 : c = -k(b) + m
	
	Euclidean(int b, int m){
		this.b = b;
		this.m = m;
		c = m%b;
		k = (-1)*(m-c)/b;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	public int getK2() {
		return k2;
	}

	public void setK2(int k2) {
		this.k2 = k2;
	}

	public static Euclidean clone(Euclidean temp){
		
		
		Euclidean clone = new Euclidean(temp.getB(), temp.getM());
		
		return clone;
	}

	
	
}
