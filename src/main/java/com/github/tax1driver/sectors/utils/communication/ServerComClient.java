package com.github.tax1driver.sectors.utils.communication;


import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;

public class ServerComClient extends Thread {
    private SocketChannel channel;
    private InetSocketAddress src;
    private final ByteBuffer channelBuffer;

    private ClientCallback callback;

    public ServerComClient(InetSocketAddress src, ClientCallback callback) throws IOException {
        this.src = src;

        this.channel = SocketChannel.open(src);
        Socket socket = channel.socket();
        this.channel.configureBlocking(false);

        this.channelBuffer = ByteBuffer.allocate(4 * 1024);
        this.callback = callback;
    }

    public void send(byte[] buffer) {
        synchronized (channelBuffer) {
            try {
                channelBuffer.put(buffer);
                channel.write(channelBuffer);
                channelBuffer.clear();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while(!this.isInterrupted()) {
            try {
                synchronized (channelBuffer) {
                    channel.read(channelBuffer);
                    callback.onPacketReceive(channelBuffer);
                    channelBuffer.clear();
                }

                Thread.sleep(5);
            } catch(IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


}
