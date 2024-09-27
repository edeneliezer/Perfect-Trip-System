package entity;

import enums.PriceLevel;

public class Place {
	private String serialNumber;
	private String placeName;
	private String description;
	private PriceLevel priceLevel;
	private String landMark;
	private String cityCode;
	
	public Place(String serialNumber, String placeName, String description, PriceLevel priceLevel, String landMark,
			String cityCode) {
		super();
		this.serialNumber = serialNumber;
		this.placeName = placeName;
		this.description = description;
		this.priceLevel = priceLevel;
		this.landMark = landMark;
		this.cityCode = cityCode;
	}


	public String getserialNumber() {
		return serialNumber;
	}

	public void setserialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getplaceName() {
		return placeName;
	}

	public void setplaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PriceLevel getpriceLevel() {
		return priceLevel;
	}


	public void setpriceLevel(PriceLevel priceLevel) {
		this.priceLevel = priceLevel;
	}

	public String getlandMark() {
		return landMark;
	}

	public void setlandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Override
	public String toString() {
		return placeName + ", description:" + description + ", priceLevel:" + priceLevel + ",landMark:"
				+ landMark + ", cityCode:" + cityCode;
	}
	

	
}
