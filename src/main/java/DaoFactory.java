public class DaoFactory {
    private static Ads adsDao;
    private static Config config = new Config();


    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDAO(config);
        }
        return adsDao;
    }
}
