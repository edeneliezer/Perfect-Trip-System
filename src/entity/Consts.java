package entity;
import java.io.File;
import java.net.URLDecoder;
public final class Consts {
  private Consts() {
	  throw new AssertionError();
  }
  
  protected static final String DB_FILEPATH= getDBPath();
  public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";

	/*----------------------------------------- HOTEL QUERIES -----------------------------------------*/
	public static final String SQL_SEL_HOTEL = "SELECT * FROM Hotel";
	/*----------------------------------------- PLACE QUERIES -----------------------------------------*/
	public static final String SQL_SEL_PLACE = "SELECT * FROM Place";
	/*----------------------------------------- RESTUARANT QUERIES -----------------------------------------*/
	public static final String SQL_SEL_RESTAURANT = "SELECT * FROM Restuarant";
	/*----------------------------------------- TRIP QUERIES -----------------------------------------*/
	public static final String SQL_SEL_TRIPS = "SELECT * FROM Trip";
	
	
	

  /**
	 * find the correct path of the DB file
   * @return the path of the DB file (from eclipse or with runnable file)
	 */

	/*private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
				decoded = new File(decoded).getParent();
				return decoded + "lib/database/DB_PerfectTrip_EdenVeAmit.accdb";
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
				System.out.println(decoded);
				return decoded + "src/entity/DB_PerfectTrip_EdenVeAmit.accdb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	
	
	public static String getDBPath() {
		try {
//			if(true)
//				return "database/Database_TeamOY.accdb";
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			System.out.println(""+decoded);
			// System.out.println(decoded) - Can help to public check the returned path
			if (decoded.contains("/bin")) {
				//System.out.println("got into else");
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
				System.out.println(decoded);
				return decoded + "src/entity/DB_PerfectTrip_EdenVeAmit.accdb";					
			} else {
				return "database/DB_PerfectTrip_EdenVeAmit.accdb";
			}
		} catch (Exception e) {
			System.out.println("got exception");
			e.printStackTrace();
			return null;
		}
	}

  
}
