package com.demo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 将文件中的内容读出，控制台打印
 */
public class NIOFileChannelReader {

    private static final String TARGET_FILE = "/Users/suyb/Documents/istudy/workspace/netty-demo/nio-demo/data/file01.txt";

    public static void main(String[] args) throws IOException {
        //1. 创建文件输入流
        File file = new File(TARGET_FILE);
        FileInputStream fileInputStream = new FileInputStream(file);

        //2.通过fileInputStream获取FileChannel，实际类型为 FileChannelImpl
        FileChannel channel = fileInputStream.getChannel();

        //3.创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //4.将通道中的数据读入到Buffer缓冲区
        channel.read(byteBuffer);

        //5.将缓冲区中的数据转成String
        System.out.println(new String(byteBuffer.array()));
        //6.关闭输入流
        fileInputStream.close();
    }


}
