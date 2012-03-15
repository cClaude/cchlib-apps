package cnamts.synchrony.port;

import java.net.BindException;
import java.net.ServerSocket;
import org.apache.log4j.Logger;

import cnamts.synchrony.port.clientsupport.ClientWorker;

/**
 *
 */
public class LockInstancePorts
{
    private final static Logger logger = Logger.getLogger( LockInstancePorts.class );
    private final int instanceNumber;
    private final long sleepMillis;

    /**
     *
     * @param instanceNumber
     * @param sleepMilliSecs
     */
    public LockInstancePorts(
        final int instanceNumber,
        final long sleepMilliSecs
        )
    {
        this.instanceNumber = instanceNumber;
        this.sleepMillis = sleepMilliSecs;
    }

    /**
     *
     */
    public void reserveInstance()
    {
        final int portBase    = 20000 + (100 * instanceNumber);
        final int portBaseMax = 20000 + (100 * (instanceNumber + 1) );

        logger.info( "Reserve instance: " + instanceNumber );

        for( int port = portBase; port<portBaseMax; port++ ) {
            final int serverPort = port;
            new Thread( new Runnable() {
                //@Override
                public void run() {
                    listenSocket( serverPort );
                }} ).start();
            }

        try { Thread.sleep( sleepMillis ); }
        catch( InterruptedException ignore ) {}
    }

    public static void listenSocket( final int serverPort )
    {
        ServerSocket server;

        try{
            logger.info( "Listen on port: " + serverPort );
            server = new ServerSocket( serverPort );
            }
        catch( BindException e ) {
            logger.fatal( "BindException * Could not listen on port: " + serverPort );
            return;
            }
        catch( Exception e ) {
            logger.fatal( "Could not listen on port: " + serverPort, e );
            return;
              }

        while( true ) {
            ClientWorker w;

            try{
                // server.accept returns a client connection
                w = new ClientWorker( serverPort, server.accept() );

                new Thread( w ).start();
                }
            catch( Exception e ) {
                logger.fatal( "Accept failed: " + serverPort, e );
                return;
                }
            }
    }

}
