package com.demo.nio;

import java.nio.IntBuffer;

public class BasicBuffer {

    public static void main(String[] args) {

        // 创建一个Buffer,大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        // 向缓存中添加数据
        for (int i = 0; i < intBuffer.capacity();i++) {
            intBuffer.put(i * 2);
        }

        //如何从Buffer中读取数据
        //将Buffer进行读写切换！！！
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }

    }

}
