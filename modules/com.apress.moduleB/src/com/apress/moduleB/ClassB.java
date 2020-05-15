package com.apress.moduleB;

import com.apress.moduleC.ClassC;

class ClassB {
	
	private String name;
	private ClassC bb = new ClassC();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassB(String name) {
		this.name = name;
	}
}
