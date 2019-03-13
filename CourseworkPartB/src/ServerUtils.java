public class ServerUtils {
	private static final String EOM = "37c0c590-1ab0-4153-87ae-db2b2cdf1ccf"; //The magic string that signifies End Of Message. generated using UUID.randomUUID(). Probably overkill for our needs.
	
	public static String getEOM() {
		return EOM;
	}
}
