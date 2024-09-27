package entity;

import java.util.List;

import enums.AccommodationStyles;
import enums.PriceLevel;

public class Hotel extends Place {
	
	
	private Integer starRating;
	private List<AccommodationStyles> accommodationStylesList;
	
	public Hotel(String serialNumber, String placeName, String description, PriceLevel priceLevel, String landMark,
			String cityCode, Integer starRating, List<AccommodationStyles> accommodationStylesList) {
		super(serialNumber, placeName, description, priceLevel, landMark, cityCode);
		this.starRating = starRating;
		this.accommodationStylesList = accommodationStylesList;
	}

	public Integer getStarRating() {
		return starRating;
	}

	public void setStarRating(Integer starRating) {
		this.starRating = starRating;
	}

	public List<AccommodationStyles> getAccommodationStylesList() {
		return accommodationStylesList;
	}

	public void setAccommodationStylesList(List<AccommodationStyles> AccommodationStylesList) {
		this.accommodationStylesList = AccommodationStylesList;
	}

	@Override
	public String toString() {
		return super.toString() + "starRating:" + starRating + ", AccommodationStyles:" + accommodationStylesList + "\n";
	}
	
	public String getHotelDetails() {
		return this.toString();
	}

	
	
	
	
	
}
