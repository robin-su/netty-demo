package com.demo.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 将字符串写入到文件
 *
 * 字符串 -> FileOutputStream -> channel -> buffer -> write
 *
 */
public class NIOFileChannelWriter {

    private static final String CONTENT = "hello world";
    private static final String TARGET_FILE = "/Users/suyb/Documents/istudy/workspace/netty-demo/nio-demo/data/file01.txt";

    public static void main(String[] args) throws IOException {
        // 1. 获取输出流
        FileOutputStream fileOutputStream = new FileOutputStream(TARGET_FILE);
        // 2. 通过fileOutputStream获取FileChannel,实际为FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //3. 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(CONTENT.length());
        //4. 将内存放入缓冲区
        byteBuffer.put(CONTENT.getBytes());

        //5. 缓冲区flip
        byteBuffer.flip();

        //6. 将byteBuffer数据写入到fileChannel
        fileChannel.write(byteBuffer);

        //7. 关闭输出流
        fileChannel.close();

    }


}
