package proxy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CachingStatisticsProxy implements StatisticsFetcher {
    private static final Logger logger = Logger.getLogger(CachingStatisticsProxy.class.getName());
    private RealStatisticsFetcher realFetcher;
    private String cachedStats;
    private long lastFetchedTime = 0;
    private static final long CACHE_EXPIRY_MS = 10 * 60 * 1000; // 10 minutes

    @Override
    public String fetchStats() {
        try {
            long now = System.currentTimeMillis();

            if (cachedStats == null || (now - lastFetchedTime) > CACHE_EXPIRY_MS) {
                logger.info("Cache expired or empty. Fetching fresh statistics.");
                realFetcher = new RealStatisticsFetcher();
                cachedStats = realFetcher.fetchStats();
                lastFetchedTime = now;
            } else {
                logger.info("Returning cached statistics.");
            }
            return cachedStats;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch statistics", e);
            return "Statistics unavailable.";
        }
    }
}
