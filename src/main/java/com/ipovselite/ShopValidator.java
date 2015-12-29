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
	public String unformat(String formatted) {
		if (validateTelephone(formatted)) {
			char[] init = formatted.toCharArray();
			char[] end = new char[formatted.length()-4];
			end[0] = init[3];
			end[1] = init[4];
			end[2] = init[5];
			for (int i=7; i<init.length;i++) {
				end[i-4] = init[i];
			}
			return new String(end);
		}
		return formatted;
	}
}
