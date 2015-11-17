package com.ipovselite;

import java.util.List;

public interface ShopDAO {
	public Shop get(int id);
	public List<Shop> getAllShops();
	public List<Shop> search(SearchParameters sp);
}
