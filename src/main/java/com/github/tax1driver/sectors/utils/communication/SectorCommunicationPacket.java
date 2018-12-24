package com.github.tax1driver.sectors.utils.communication;

import com.github.tax1driver.sectors.utils.communication.packets.GenericPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class SectorCommunicationPacket {
    public PacketType packetID;
    public GenericPacket packetData;

    public SectorCommunicationPacket(PacketType packetID, GenericPacket packetData) {
        this.packetID = packetID;
        this.packetData = packetData;
    }

    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(SectorCommunicationPacket.class, new PacketDeserializer())
            .create();

    public static SectorCommunicationPacket fromBuffer(ByteBuffer buffer) {
        try {
            String stringRepresentation = new String(buffer.array(), "UTF-8");
            return gson.fromJson(stringRepresentation, SectorCommunicationPacket.class);
        } catch(UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        return null;
    }


}
