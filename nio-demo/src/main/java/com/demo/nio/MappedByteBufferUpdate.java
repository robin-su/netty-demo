package com.demo.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferUpdate {

    private static final String UPDATE_PATH = "/Users/suyb/Documents/itWork/devworkspace/private/netty-demo/nio-demo/data/fileSourceTarget.txt";

    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile(UPDATE_PATH, "rw");
        FileChannel fileChannel = file.getChannel();

        /**
         * 参数1: FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数2： 0 ： 可以直接修改的起始位置
         * 参数3:  5: 是映射到内存的大小(不是索引位置) ,即将 1.txt 的多少个字节映射到内存
         * 可以直接修改的范围就是 0-5
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');
        mappedByteBuffer.put(5, (byte) 'Y');//IndexOutOfBoundsException

        file.close();
        System.out.println("修改成功~~");
    }

}
