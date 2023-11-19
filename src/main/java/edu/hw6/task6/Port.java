package edu.hw6.task6;

import java.util.Objects;

public class Port {
    private final int port;
    private final Status status;
    private final Protocol protocol;
    private final String service;

    protected Port(int port, Status status, Protocol protocol, String service) {
        this.port = port;
        this.status = status;
        this.protocol = protocol;
        this.service = service;
    }

    public static Port createFreePort(int port) {
        return new Port(port, Status.FREE, null, null);
    }

    public static Port createOccupiedPort(int port, Protocol protocol, String service) {
        Objects.requireNonNull(protocol);
        Objects.requireNonNull(service);

        return new Port(port, Status.OCCUPIED, protocol, service);
    }

    public int getPort() {
        return port;
    }

    public Status getStatus() {
        return status;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getService() {
        return service;
    }
}
