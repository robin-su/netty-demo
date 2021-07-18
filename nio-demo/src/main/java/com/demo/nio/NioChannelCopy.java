package com.demo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioChannelCopy {

    private static final String SOURCE_PATH = "/Users/suyb/Documents/itWork/devworkspace/private/netty-demo/nio-demo/data/fileSourceCopy.txt";
    private static final String TARGET_PATH = "/Users/suyb/Documents/itWork/devworkspace/private/netty-demo/nio-demo/data/fileSourceTarget.txt";

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(SOURCE_PATH);
        FileChannel sourceChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(TARGET_PATH);
        FileChannel targetChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            byteBuffer.clear();
            int read = sourceChannel.read(byteBuffer);
            System.out.println("read="+read);
            if(-1 == read) {
                break;
            }
            byteBuffer.flip();
            targetChannel.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

}
