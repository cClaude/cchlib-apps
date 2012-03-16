package cnamts.synchrony.port;

import org.apache.log4j.Logger;

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
    public void startServices()
    {
        logger.info( "Reserve instance: " + instanceNumber );

        final int portBase    = computeFirstPort( instanceNumber );
        final int portBaseMax = computeFirstPort( instanceNumber + 1 );

        logger.info( String.format( "Instance range : [%d-%d]", portBase, portBaseMax ) );

        for( int port = portBase; port<portBaseMax; port++ ) {
            startService( port );
            sleep();
            }
    }

    private static int computeFirstPort( int instanceNumber )
    {
        return 20000 + (100 * instanceNumber);
    }

    public static void sleep( final long sleepMillis )
    {
        try { Thread.sleep( sleepMillis ); }
        catch( InterruptedException ignore ) {}
    }

    private void sleep()
    {
        sleep( sleepMillis );
    }

    private void startService( int serverPort )
    {
        new Thread( new ServerWorker( serverPort ) ).start();
    }
}
