package com.cs3.rsa;

public class Contact {

	String name;
	
	//Their public key for encrypting msgs to them
	
	int pub1 = 0;//e
	
	int pub2 = 0;//pq
	
	
	//Private key for decrypting their msgs
	
	int priv1 = 0;//d
	int priv2 = 0;//original pq
	

	Contact(String name, int e, int pq, int d, int ogpq){
		
		setName(name);
		setPub1(e);
		setPub2(pq);
		setPriv1(d);
		setPriv2(ogpq);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPub1() {
		return pub1;
	}

	public void setPub1(int pub1) {
		this.pub1 = pub1;
	}

	public int getPub2() {
		return pub2;
	}

	public void setPub2(int pub2) {
		this.pub2 = pub2;
	}

	public int getPriv1() {
		return priv1;
	}

	public void setPriv1(int priv) {
		this.priv1 = priv;
	}
	public int getPriv2() {
		return priv2;
	}
	public void setPriv2(int priv2) {
		this.priv2 = priv2;
	}
}
