package cnamts.synchrony.port;

import java.net.BindException;
import java.net.ServerSocket;
import org.apache.log4j.Logger;
import cnamts.synchrony.port.clientsupport.ClientWorker;

/**
 *
 */
public class ServerWorker implements Runnable
{
    private final static Logger logger = Logger.getLogger( ServerWorker.class );
    private final int serverPort;

    /**
     *
     * @param serverPort
     */
    public ServerWorker(
        final int serverPort
        )
    {
        this.serverPort = serverPort;
    }

    public void run()
    {
        listenSocket( serverPort );
    }

    public static void listenSocket( final int serverPort )
    {
        ServerSocket server;

        try{
            logger.trace( "Listen on port: " + serverPort );
            server = new ServerSocket( serverPort );
            }
        catch( BindException e ) {
            final String msg = "BindException * Could not listen on port: " + serverPort;
            logger.fatal( msg );
            System.err.println( msg );
            return;
            }
        catch( Exception e ) {
            final String msg = "Could not listen on port: " + serverPort;
            logger.fatal( msg, e );
            System.err.println( msg );
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
