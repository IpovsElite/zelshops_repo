package com.ipovselite;

import java.util.ArrayList;
import java.util.List;

public class SpecLocal implements ISpecService{
	private String[] specArray={"Еда","Здоровье","Одежда","Электроника","Спорт"};
	public List<String> getSpecList() {
		// TODO Auto-generated method stub
		List<String> specList = new ArrayList<String>();
		for (String s: specArray) {
			specList.add(s);
		}
		return specList;
	}

}
