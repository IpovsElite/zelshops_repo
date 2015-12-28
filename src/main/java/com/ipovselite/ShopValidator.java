package com.ipovselite;

import java.util.HashMap;
import java.util.Map;

public class ShopValidator implements Validator<Shop> {

	public Map<String, String> validate(Shop arg) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		if (arg.getName().equals(""))
			map.put("name", "Поле не заполнено.");
		if (arg.getSite().equals(""))
			map.put("site", "Поле не заполнено.");
		if (arg.getAddress().equals(""))
			map.put("address", "Поле не заполнено.");
		if (arg.getTelephone().equals(""))
			map.put("telephone", "Поле не заполнено.");
		if (!validateSite(arg.getSite()) && !arg.getSite().equals(""))
			map.put("site", "Неверный формат URL.");
		String formattedTelephone = format(arg.getTelephone());
		if (!formattedTelephone.equals("")) {
			boolean isValidTelephone = validateTelephone(formattedTelephone);
			if (!isValidTelephone)
				map.put("telephone", "Неверный формат номера телефона.");
			else
				arg.setTelephone(formattedTelephone);
		}
		System.out.println("IN validate(): " + arg.getTelephone()); //
		return map;
	}
	
	private boolean validateSite(String url) {
		if (url.equals("нет"))
			return true;
		return url.matches("(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");
	}
	private boolean validateTelephone(String telephone) {
		return telephone.matches("^((\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
	}
	private String format(String telephone) {
		char[] arr = telephone.replaceAll("\\s","").toCharArray();
		String res = "";
		if (arr.length > 2) {
			res = "+7("+ arr[0] + arr[1] + arr[2] +")";
			for (int i = 0;i < arr.length - 3;i++)
				res += arr[3+i];
			return res;
		}
		return new String(arr);
	}
}
