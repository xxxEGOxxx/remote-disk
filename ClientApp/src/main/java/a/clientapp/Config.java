package a.clientapp;

import java.io.IOException;
import java.net.Socket;

public class Config {
    static Socket socket;
    private static String hostName;
    private static int port;

    public static void setSocket(String hostName, int port) throws IOException {
        Config.socket = new Socket(hostName, port);
    }

    public static void setPort(int port) {
        Config.port = port;
    }

    public static void setHostName(String hostName) {
        Config.hostName = hostName;
    }

    public static int getPort() {
        return Config.port;
    }

    public static String getHostName() {
        return Config.hostName;
    }

    public static Socket getSocket() {
        return Config.socket;
    }

}
