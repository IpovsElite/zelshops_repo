package com.ipovselite;

import java.util.List;

public interface ShopDAO {
	public Shop get(int id);
	public List<Shop> getAllShops();
	public List<Shop> search(SearchParameters sp);
	public void addShop(Shop shop);
	public void updateColumn(String col,Object val, int id);
	public List<Shop> findByStatus(int status);
	public void delete(int id);
	public void addComment(int shopid, String userid, String comment);
}
