package com.dcomm.attendance.Attendance;

import java.io.PrintWriter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/11/13
 * Time: 2:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class TCPConnection extends Thread {
    public static final int SERVERPORT = 4444;
    private boolean running = false;
    private PrintWriter mOut;
    private Socket sock;
    private InetAddress adr;
    private byte [] ipAddress;

    public TCPConnection(InetAddress h_addr, int serv_port)
    {
        sock = null;
        mOut = null;
        ipAddress = new byte[] {(byte)82,(byte)168,(byte)175,(byte)141};
        adr = null;
    }

    public void openConnectionAndSend(String message)
    {
        try{

            adr = InetAddress.getByAddress(ipAddress);
            sock = new Socket(adr,SERVERPORT);
            mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
            mOut.println(message);
            mOut.flush();
            sock.close();
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException ...: "+e);
        } catch (IOException e) {
            System.out.println("IOException ...: "+e);
        }
    }


}
