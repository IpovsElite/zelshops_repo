package zelshops;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ipovselite.Geotagging;
import com.ipovselite.Shop;

import junit.framework.TestCase;

public class GeotaggingTest extends TestCase {
	@Test
	public void testModelShouldReturnShopsInTheRadius() {
		//GIVEN
		double radius = 1.3;
		double curLat = 55.997450;
		double curLng = 37.211385;
		List<Shop> initShopList = new ArrayList<Shop>();
		double lat = 55.986362, lng = 37.212003;
		double x = lat;
		double y = lng;
		double stepX = -0.001;
		double stepY = 0.00006;
		while (x > lat-0.01 && y < lng + 0.0006) {
			Shop s = new Shop();
			s.setLat(x);
			s.setLng(y);
			initShopList.add(s);
			x+=stepX;
			y+=stepY;
		}
		//WHEN
		List<Shop> resShopList = Geotagging.GetNearestShops(curLat, curLng, radius, initShopList);
		//THEN
		for (Shop s: resShopList) {
			System.out.println(s.getDistance());
		}
		System.out.println(resShopList.get(resShopList.size()-1).getDistance());
		System.out.println(initShopList.size());
		System.out.println(resShopList.size());
		//assertEquals(initShopList.size()-resShopList.size()>=resShopList.size(), true);
		assertEquals(Math.abs(resShopList.get(resShopList.size()-1).getDistance()-radius*1000)<=100, true);
	
	}
}
