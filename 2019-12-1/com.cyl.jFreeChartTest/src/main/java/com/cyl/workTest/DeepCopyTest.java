package com.cyl.workTest;

public class DeepCopyTest implements Cloneable{
	
	private String temp1;
	
	
	public Object clone(){
		Object object = null;
		try {
			object = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return object;
	}


	public DeepCopyTest(String temp1) {
		super();
		this.temp1 = temp1;
	}


	public DeepCopyTest() {
		super();
	}


	public String getTemp1() {
		return temp1;
	}


	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	
	
	
}
