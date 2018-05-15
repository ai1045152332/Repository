package com.honghe.recordweb.service.frontend.websocket;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Component
public class FlashPolicyServer {

    private final static Logger logger = Logger.getLogger(FlashPolicyServer.class);
    // If param is passed from the config, the server will listen on this port for connections
    // 843 is the default port by flash player for security policy
    private static final int DEFAULT_POLICY_PORT = 843;

    private static final String DEFAULT_POLICY_FILE = "default_policy.xml";

    private static final int DEFAULT_THREAD_COUNT = 10;
    private static final int DEFAULT_THREAD_IDLE_PERIOD = 10;
    // Read timeout at 10 secs (10000 ms)
    private static final int DEFAULT_READ_TIMEOUT = 10;
    // Maximum queue length for incoming connection indications (a request to connect)
    private static final int MAX_QUEUE_LENGTH = 50;

    // Sleep delay to avoid flooding at 10 ms
    private static final int SLEEP_DELAY = 10;


    // PolicyServer instance variables
    private int policyServerPort;
    private String policyServerRequest;
    private boolean listening;
    private ServerSocket socketServer;
    private String policy;
    private ThreadPoolExecutor threadPool;
    private String policyFile;
    private int threadPoolSizeMin;
    private int threadPoolSizeMax;
    private int threadMaxIdleSeconds;
    private int socketTimeout;


    // The character sequence sent by the Flash Player to request a policy file
    private static final String DEFAULT_POLICY_REQUEST = "<policy-file-request/>";

    private static final int BUFFER_LENGTH = 100;


    //@PostConstruct
    public void run() {
        listening = false;

        loadConfiguration();
        policy = readPolicyFromFile(policyFile);
        threadPool = new ThreadPoolExecutor(threadPoolSizeMin, threadPoolSizeMax,
                threadMaxIdleSeconds, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        new Thread(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }).start();
    }

    /**
     * load config properties file and set instance variables
     */
    private void loadConfiguration() {

        policyFile = DEFAULT_POLICY_FILE;

        policyServerPort = DEFAULT_POLICY_PORT;

        policyServerRequest = DEFAULT_POLICY_REQUEST;

        threadPoolSizeMin = DEFAULT_THREAD_COUNT;

        threadPoolSizeMax = DEFAULT_THREAD_COUNT;

        threadMaxIdleSeconds = DEFAULT_THREAD_IDLE_PERIOD;

        socketTimeout = DEFAULT_READ_TIMEOUT * 1000;

    }

    /**
     * Read policy
     *
     * @param filename
     * @return policy as a String
     */
    private String readPolicyFromFile(String filename) {

        StringBuffer contents = new StringBuffer();

        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    this.getClass().getClassLoader().getResourceAsStream(filename)));
            try {
                String line = null; //not declared within while loop
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
//            ex.printStackTrace();
            logger.error("", ex);
        }

        return contents.toString();
    }

    /**
     * Start listening on specified port and instantiate new SocketConnection
     * thread to serve incoming request
     */
    private void start() {

        try {

            listening = true;

            // Start listening for connections
            socketServer = new ServerSocket(policyServerPort, MAX_QUEUE_LENGTH);


            while (listening) {

                // Wait for a connection and accept it
                Socket socket = socketServer.accept();

                try {


                    // Execute the request handler using thread pool
                    threadPool.execute(new SocketConnection(socket));

                } catch (Exception e) {

                }
                try {
                    // Wait until a new connection is accepted to avoid flooding
                    Thread.sleep(SLEEP_DELAY);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
                // for testing... exit after one connection
                //listening = false;
            }
        } catch (IOException e) {
            logger.error("", e);
        } finally {

            threadPool.shutdownNow();
        }
    }

    //@PreDestroy
    public void close() {
        threadPool.shutdownNow();
    }

    /**
     * Local class SocketConnection
     * For every accepted connection one SocketConnection is created.
     * It waits for the policy file request, returns the policy file and closes the connection immediately
     */
    class SocketConnection implements Runnable {

        private Socket socket;
        private BufferedReader socketIn;
        private PrintWriter socketOut;

        /**
         * Constructor takes the Socket object for this connection
         *
         * @param socket Socket connection to a client created by the PolicyServer main thread
         */
        public SocketConnection(Socket socket) {
            this.socket = socket;
        }

        /**
         * Thread run method waits for the policy request,
         * returns the poilcy file and closes the connection
         */
        public void run() {
            try {
                // initialize socket and readers/writers
                // set a read timeout of 10 secs
                socket.setSoTimeout(socketTimeout);
                socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                socketOut = new PrintWriter(socket.getOutputStream(), true);
                readPolicyRequest();
            } catch (IOException e) {
                logger.error("", e);
            } finally {
                close();
            }

        }

        /**
         * Wait for and read the policy request sent by the Flash Player
         * Return the policy file and close the Socket connection
         */
        private void readPolicyRequest() {
            try {
                // Read the request and compare it to the request string defined in the constants.
                // If the proper _policy request has been sent write out the _policy file
                if (read().startsWith(policyServerRequest))
                    write(policy);
            } catch (Exception e) {
                logger.error("", e);
            }
        }

        /**
         * Read until a zero character is sent or a maximum of 100 character
         *
         * @return The character sequence read
         * @throws IOException
         * @throws EOFException
         * @throws InterruptedIOException
         */
        private String read() throws IOException, EOFException, InterruptedIOException {
            StringBuffer buffer = new StringBuffer();
            int codePoint;
            boolean zeroByteRead = false;


            do {
                codePoint = socketIn.read();
                if (codePoint == 0 || codePoint == -1)
                    zeroByteRead = true;
                else
                    buffer.appendCodePoint(codePoint);
            } while (!zeroByteRead && buffer.length() < FlashPolicyServer.BUFFER_LENGTH);


            return buffer.toString().trim();
        }

        /**
         * Writes a String to the client
         *
         * @param msg Text to be sent to the client (policy file)
         */
        public void write(String msg) {
            socketOut.println(msg + "\u0000");
            socketOut.flush();

        }

        /**
         * Close the Socket connection an set everything to null. Prepared for garbage collection
         */
        public void close() {
            try {
                if (socketOut != null)
                    socketOut.close();
            } catch (Exception e) {
                logger.error("", e);
            }
            try {
                if (socketIn != null)
                    socketIn.close();
            } catch (Exception e) {
                logger.error("", e);
            }
            try {
                if (socket != null)
                    socket.close();
            } catch (Exception e) {
                logger.error("", e);
            }

            socketIn = null;
            socketOut = null;
            socket = null;
        }

    }


}
