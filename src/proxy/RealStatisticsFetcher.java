// File: RealStatisticsFetcher.java
package proxy;

public class RealStatisticsFetcher implements StatisticsFetcher {
    @Override
    public String fetchStats() {
        System.out.println("[Real] Fetching latest statistics from DB...");
        return "Top Courses: Java, Python, AI";
    }
}