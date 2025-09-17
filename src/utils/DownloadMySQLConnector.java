package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadMySQLConnector {
    public static void main(String[] args) {
        String url = "https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar";
        String outputPath = "lib/mysql-connector-j-8.0.33.jar";

        try {
            URL website = URI.create(url).toURL();
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(outputPath);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            System.out.println("MySQL Connector downloaded successfully to: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
