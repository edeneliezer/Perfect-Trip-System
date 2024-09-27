package entity;

import java.util.ArrayList;
import java.util.List;

import enums.PriceLevel;


public class Restuarant extends Place {

	
	private List<String> kitchenStylesList= new ArrayList<String>();

	public Restuarant(String serialNumber, String placeName, String description, PriceLevel priceLevel, String landMark,
			String cityCode,List<String> kitchenStyles) {
		super(serialNumber, placeName, description, priceLevel, landMark, cityCode);
		this.kitchenStylesList=kitchenStyles;
	}

	public List<String> getkitchenStyles() {
		return kitchenStylesList;
	}

	public void setkitchenStyles(List<String> kitchenStyles) {
		this.kitchenStylesList = kitchenStyles;
	}

	@Override
	public String toString() {
		return super.toString() + "kitchenStyles:" + kitchenStylesList + "\n";
	}
	
	public String getResturantDetails() {
		return this.toString();
	}
	
	
}
