package com.googlecode.cchlib.apps.reserveports.port.clientsupport;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.log4j.Logger;

/**
 * Fake client support
 */
public class ClientWorker implements Runnable
{
    private final static Logger logger = Logger.getLogger( ClientWorker.class );
    private Socket client;
    private int serverPort;

    /**
     * Create a ClientWorker
     *
     * @param serverPort Service port
     * @param socket     Socket for current exchange
     */
    public ClientWorker( final int serverPort, final Socket socket )
    {
        this.serverPort = serverPort;
        this.client = socket;
    }

    /**
     * Handle hand check
     */
    //@Override
    public void run()
    {
        logger.info( "Handle hand check for: " + serverPort );

        BufferedReader in = null;

        try {
            in  = new BufferedReader( new InputStreamReader( client.getInputStream() ) );

            PrintWriter out = null;

            try{
                out = new PrintWriter(client.getOutputStream(), true);

                traceInOut( in, out );
                }
            catch( IOException e ) {
                logger.warn( "Can not get output stream for service: " + serverPort, e );
                }
            finally {
                silentClose( out );
                }
            }
        catch( IOException e ) {
            logger.warn( "Can not get input stream for service: " + serverPort, e );
            }
        finally {
            silentClose( in );
            }
    }

    private void silentClose( Closeable c )
    {
        try {
            c.close();
            }
        catch( IOException ignore) {}
    }

    private void traceInOut(
        final BufferedReader    in,
        final PrintWriter       out
        )
    {
        while( true ) {
            try{
                String line = in.readLine();

                // Send data back to client
                out.println(line);

                // Append data to log
                logger.info("received from: " + serverPort + " * " + line);
                }
            catch( IOException e ) {
                logger.warn( "Read/Write failed", e );
                }
        }
    }

}
