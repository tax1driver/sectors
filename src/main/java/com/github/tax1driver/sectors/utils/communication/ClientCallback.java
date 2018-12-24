package com.github.tax1driver.sectors.utils.communication;

import java.nio.ByteBuffer;

public interface ClientCallback {
    void onPacketReceive(ByteBuffer received);
}
