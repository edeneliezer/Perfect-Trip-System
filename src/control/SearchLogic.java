package control;

import enums.AccommodationStyles;
import enums.PriceLevel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import entity.Consts;
import entity.Hotel;
import entity.Place;
import entity.Restuarant;

public class SearchLogic {
    private static SearchLogic SearchLogic_Instance;
    private ArrayList<Place> allPlaces; //Will store all fetched places
    private SearchLogic() {
    	 this.allPlaces = getPlaces();
    }

    public static SearchLogic getSearchLogic_Instance() {
        if (SearchLogic_Instance == null)
            SearchLogic_Instance = new SearchLogic();
        return SearchLogic_Instance;
    }


    /**
     * Fetches places, hotels, and restaurants from the database.
     *
     * @return ArrayList of places.
     */
    public ArrayList<Place> getPlaces() {
    	 ArrayList<Place> results = new ArrayList<>();
         try {
             Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
             try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
                 try (PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_PLACE);
                      ResultSet rs = stmt.executeQuery()) {
                     while (rs.next()) {
                         String serialNumber = rs.getString(1);
                         String placeName = rs.getString(2);
                         String description = rs.getString(3);
                         PriceLevel priceLevel = PriceLevel.valueOf(rs.getString(4));
                         String landMark = rs.getString(5);
                         String cityCode = rs.getString(6);
                         Place place = new Place(serialNumber, placeName, description, priceLevel, landMark, cityCode);
                         results.add(place);
                    }
                }

                // Fetch hotels and match them with places
                try (PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_HOTEL);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String hotelSerialNumber = rs.getString(1); 
                        // Find corresponding place by serialNumber
                        Place correspondingPlace = findPlaceBySerialNumber(results, hotelSerialNumber);
                        if (correspondingPlace != null) {
                            String placeName = correspondingPlace.getplaceName();
                            String description = correspondingPlace.getDescription();
                            PriceLevel priceLevel = correspondingPlace.getpriceLevel();
                            String landMark = correspondingPlace.getlandMark();
                            String cityCode = correspondingPlace.getCityCode();
                            Integer starRating = rs.getInt(2); // starRating is the second column
                            List<AccommodationStyles> accommodationStylesList = new ArrayList<>();
                            accommodationStylesList.add(AccommodationStyles.valueOf(rs.getString(3))); //rs.getString(3) contains accommodation type
                            Hotel hotel = new Hotel(hotelSerialNumber, placeName, description, priceLevel, landMark,
                                   cityCode, starRating, accommodationStylesList);
                             results.add(hotel);
                           //Remove "Place" father to provoke duplicates
                             results.remove(correspondingPlace);
                        }
                    }
                }

                // Fetch restaurants and match them with places
                try (PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_RESTAURANT);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String restaurantSerialNumber = rs.getString(1); 
                        // Find corresponding place by serialNumber
                        Place correspondingPlace = findPlaceBySerialNumber(results, restaurantSerialNumber);
                        if (correspondingPlace != null) {                       
                            String placeName = correspondingPlace.getplaceName();
                            String description = correspondingPlace.getDescription();
                            PriceLevel priceLevel = correspondingPlace.getpriceLevel();
                            String landMark = correspondingPlace.getlandMark();
                            String cityCode = correspondingPlace.getCityCode();
                            // Fetch kitchen styles
                            List<String> kitchenStyles = new ArrayList<>();
                            kitchenStyles.add(rs.getString(2)); // getString(2) contains kitchen style
                            Restuarant restaurant = new Restuarant(restaurantSerialNumber, placeName, description, priceLevel, landMark,
                                    cityCode, kitchenStyles);
                            results.add(restaurant);
                            //Remove "Place" father to provoke duplicates
                            results.remove(correspondingPlace);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    // Helper method to find a place by serial number
    private Place findPlaceBySerialNumber(ArrayList<Place> places, String serialNumber) {
        for (Place place : places) {
            if (place.getserialNumber().equals(serialNumber)) {
                return place;
            }
        }
        return null;
    }
    
    /*
     * Returns all unique city codes in our database
     */
    public String[] getUniqueCityCodes() {
        HashSet<String> cityCodesSet = new HashSet<>();

        // Iterate through all places and add unique city codes to HashSet
        for (Place place : getPlaces()) {
            cityCodesSet.add(place.getCityCode());
        }
        // Convert the HashSet to an array of Strings
        String[] cityCodes = cityCodesSet.toArray(new String[0]);

        return cityCodes;
    }
    
    /*
     * Returns all Kitchen Styles in our database
     */
    public String[] getUniqueKitchenStyles() {
        HashSet<String> kitchenStylesSet = new HashSet<>();
        
        // Iterate through all places and add unique kitchen styles to HashSet
        for (Place place : getPlaces()) {
            if (place instanceof Restuarant) {
            	Restuarant restaurant = (Restuarant) place;
                List<String> styles = restaurant.getkitchenStyles();
                for (String style : styles) {
                    kitchenStylesSet.add(style);
                }
            }
        }
        // Convert the HashSet to an array of Strings
        String[] kitchenStyles = kitchenStylesSet.toArray(new String[0]);

        return kitchenStyles;
    }
     
    /* --------------------------------------------SEARCH FILTERS------------------------------------------------ */
    
    

    public ArrayList<Place> searchPlaceFilter(String type, String name, String city, PriceLevel priceLevel, AccommodationStyles accommodationStyle, Integer starRating, String kitchenStyle) {
    	ArrayList<Place> filteredPlaces = new ArrayList<>();

        for (Place place : allPlaces) {	
			if (!type.equals("all")) {
                if ((type.equals("hotel") && !(place instanceof Hotel)) ||
                    (type.equals("restaurant") && !(place instanceof Restuarant))) {
                    continue; // Skip this place if the type does not match
                }
			}
            // Check if the name matches
            if (name != null && !place.getplaceName().toLowerCase().contains(name.toLowerCase())) {
                continue; 
            }

            // Check if the city code matches 
            if (city != null && city !="all" && !place.getCityCode().equalsIgnoreCase(city)) {
                continue; 
            }

            // Check if the price level matches 
            if (priceLevel != null && place.getpriceLevel() != priceLevel) {
                continue; 
            }

            // Check if the accommodation style matches
            if (accommodationStyle != null && place instanceof Hotel) {
                Hotel hotel = (Hotel) place;
                if (!hotel.getAccommodationStylesList().contains(accommodationStyle)) {
                    continue; 
                }
            }

            // Check if the star rating matches 
            if (starRating != null && place instanceof Hotel) {
                Hotel hotel = (Hotel) place;
                if (!starRating.equals(hotel.getStarRating())) {
                    continue; 
                }
            }

         // Check if the kitchen style matches (if provided and place is a restaurant)
            if (kitchenStyle != null && !kitchenStyle.equalsIgnoreCase("all") && place instanceof Restuarant) {
                Restuarant restaurant = (Restuarant) place;              
                if (!restaurant.getkitchenStyles().contains(kitchenStyle.trim())) {
                    continue;
                }
            }


            // If all filters match, add the place to the filtered list
            filteredPlaces.add(place);
        }

        return filteredPlaces;
    }
    
    
    
    
}

