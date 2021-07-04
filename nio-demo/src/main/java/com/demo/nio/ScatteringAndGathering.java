package com.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering：将数据写入到buffer时，可以采用buffer数组，依次写入  [分散]
 * Gathering: 从buffer读取数据时，可以采用buffer数组，依次读
 */
public class ScatteringAndGathering {

    public static void main(String[] args) throws IOException {
        // 使用 ServerSocketChannel 和 SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定端口到socket,并启动
        serverSocketChannel.socket().bind(new InetSocketAddress(7000));
        // 创建ByteBuffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1]  = ByteBuffer.allocate(3);

        //等客户端连接(telnet)
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8; // 假定从客户端接收8个字节
        // 循环的读取
        while(true) {
            int byteRead = 0;
            while(byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);
                byteRead += l; // 累计读取的字节数
                System.out.println("byteRead=" + byteRead);
                // 使用流打印，看看当前的这个buffer的position和limit
                Arrays.asList(byteBuffers)
                        .stream()
                        .map(buffer -> "position=" + buffer.position() + ",limit=" + buffer.limit())
                        .forEach(System.out::println);

            }
            //将所有的buffer进行flip
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

            // 将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            // 将所有的buffer进行clear
            Arrays.asList(byteBuffers).forEach(ByteBuffer::clear);
            System.out.println("byteRead:=" + byteRead + " byteWrite=" + byteWrite + ", messagelength" + messageLength);
        }
    }

}
