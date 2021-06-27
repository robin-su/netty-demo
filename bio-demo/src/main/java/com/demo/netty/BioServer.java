package com.demo.netty;

import javax.net.ssl.SSLServerSocket;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BioServer {

    public static void main(String[] args) throws IOException {

        //线程池机制
        // 思路：s
        // 1.创建一个线程池
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(1,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                new ThreadPoolExecutor.DiscardPolicy());

        // 2.如果有客户端连接，就创建一个线程，与之通信（单独写一个方法）
        ServerSocket server = new ServerSocket(6666);

        System.out.println("socket server is start up!!!");

        while (true) {

            // 监听6666端口等待连接，该方法是阻塞的
            System.out.println("等待客户端连接");
            final Socket socket = server.accept();
            System.out.println("连接到一个客户端");
            executorPool.execute(() -> {
                handle(socket);
            });

        }


    }

    private static void handle(Socket socket) {
        try {
            System.out.println(String.format("客户端线程ID：%s,名字为：%s",
                    Thread.currentThread().getId(),
                    Thread.currentThread().getName()));

            byte[] bytes = new byte[1024];

            // 获取socket 输入流
            InputStream inputStream = socket.getInputStream();

            //循环的读取客户端发送的数据
            while (true) {
                int read = inputStream.read(bytes);
                if ( read != -1) {
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("关闭线程%s的client的连接",Thread.currentThread().getId()));
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
