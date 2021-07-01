package com.demo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NioChannleCopyWithTransfrom {

    private static final String SOURCE_PATH = "/Users/suyb/Documents/itWork/devworkspace/private/netty-demo/nio-demo/data/kaola.pic";
    private static final String TARGET_PATH = "/Users/suyb/Documents/itWork/devworkspace/private/netty-demo/nio-demo/data/kaolaTarget.pic";

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(SOURCE_PATH);
        FileOutputStream fileOutputStream = new FileOutputStream(TARGET_PATH);

        FileChannel source = fileInputStream.getChannel();
        FileChannel target = fileOutputStream.getChannel();
        target.transferFrom(source,0,source.size());

        source.close();
        target.close();
        fileInputStream.close();
        fileOutputStream.close();


    }



}
