package com.github.tax1driver.sectors.utils.communication;

import com.github.tax1driver.sectors.utils.communication.packets.GenericPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class SectorCommunicationClient implements ClientCallback {
    private ServerComClient client;

    public SectorCommunicationClient(String masterServerAddress, int port) throws IOException {
        client = new ServerComClient(new InetSocketAddress(masterServerAddress, port), this);
        client.start();
    }

    public void sendBinaryData(byte[] buffer) {
        client.send(buffer);
    }

    private static Gson gson = new GsonBuilder().create();

    public void sendPacket(GenericPacket data) {
        PacketType type = PacketType.forClass(data.getClass());
        SectorCommunicationPacket packet = new SectorCommunicationPacket(type, data);

        String stringRepresentation = gson.toJson(packet, SectorCommunicationPacket.class);
        byte[] buf;
        try {
            buf = stringRepresentation.getBytes("UTF-8");
            client.send(buf);
        } catch(UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onPacketReceive(ByteBuffer received) {
        SectorCommunicationPacket packet = SectorCommunicationPacket.fromBuffer(received);
    }
}
