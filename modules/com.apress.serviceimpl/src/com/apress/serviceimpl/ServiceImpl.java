package com.apress.serviceimpl;

import com.apress.service.IService;

public class ServiceImpl implements IService {

	@Override
	public void perform() {
		System.out.println("Well Done!");
	}
}
