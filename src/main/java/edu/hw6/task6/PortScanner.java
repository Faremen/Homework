package edu.hw6.task6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PortScanner {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String TABLE_FORMAT = "%-8s %-12s %-10s %s";
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;

    private final static Map<Integer, String> POSSIBLE_SERVICES = new HashMap<>() {{
        put(135, "EPMAP");
        put(137, "Служба имен NetBIOS");
        put(138, "Служба датаграмм NetBIOS");
        put(139, "Служба сеансов NetBIOS");
        put(445, "Microsoft-DS Active Directory");
        put(843, "Adobe Flash");
        put(1900, "Simple Service Discovery Protocol (SSDP)");
        put(3702, "Динамическое обнаружение веб-служб");
        put(5353, "Многоадресный DNS");
        put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
        put(17500, "Dropbox");
        put(27017, "MongoDB");
    }};

    private PortScanner() {}

    public static List<Port> checkPorts(List<Integer> ports) {
        Objects.requireNonNull(ports);

        List<Port> result = new ArrayList<>();

        for (var i : ports) {
            if (i > MAX_PORT || i < MIN_PORT) {
                throw new IllegalArgumentException(String.format("Port must be in range [%d, %d]", MIN_PORT, MAX_PORT));
            } else {
                boolean isTcpPort = isTCPPort(i);
                boolean isUdpPort = isUDPPort(i);

                if (isTcpPort) {
                    result.add(Port.createOccupiedPort(i, Protocol.TCP, POSSIBLE_SERVICES.getOrDefault(i, "Не известно")));
                }

                if (isUdpPort) {
                    result.add(Port.createOccupiedPort(i, Protocol.UDP, POSSIBLE_SERVICES.getOrDefault(i, "Не известно")));
                }

                if (!isTcpPort && !isUdpPort) {
                    result.add(Port.createFreePort(i));
                }
            }
        }

        return result;
    }

    public static void printPortsInfo(List<Port> ports) {
        LOGGER.info(String.format(TABLE_FORMAT, "Порт", "Статус", "Протокол", "Сервис"));

        for (var port : ports) {
            LOGGER.info(String.format(TABLE_FORMAT,
                port.getPort(),
                port.getStatus(),
                port.getProtocol() != null ? port.getProtocol().toString() : "",
                port.getService() != null ? port.getService() : ""));
        }
    }

    private static boolean isTCPPort(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    private static boolean isUDPPort(int port) {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}
