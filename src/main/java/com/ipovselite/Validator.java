package com.ipovselite;

import java.util.Map;

public interface Validator <T> {
	public Map<String, String> validate(T arg);
}
