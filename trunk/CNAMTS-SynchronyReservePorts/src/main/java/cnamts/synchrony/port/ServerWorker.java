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
