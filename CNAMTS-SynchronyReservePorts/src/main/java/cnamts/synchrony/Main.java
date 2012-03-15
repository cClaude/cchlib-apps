package cnamts.synchrony;

import org.apache.log4j.Logger;
import cnamts.synchrony.port.LockInstancePorts;

/**
 *
 */
public class Main
{
    private final static Logger logger = Logger.getLogger( Main.class );
    private static final long SLEEP_MS = 100;

    public final static void main(String[] args)
    {
        if( args.length == 0 ) {
            printHelp();
            }

        int[] instanceNumbers = new int[args.length];

        for(int i =0; i<args.length; i++ ) {
            try {
                instanceNumbers[ i ] = Integer.parseInt( args[ i ] );
                }
            catch( NumberFormatException e ) {
                System.err.println( "Bad number: " + args[ i ] );
                printHelp();
                }
            }

        for( int instanceNumber : instanceNumbers ) {
            new LockInstancePorts(instanceNumber, SLEEP_MS).reserveInstance();
            }

        try { Thread.sleep( 1000 ); }
        catch( InterruptedException ignore ) {}

        logger.info( "Reservation for " + instanceNumbers.length + " instance(s). Done." );

        System.out.println( "CTRL-C to leave." );
    }

    private static void printHelp()
    {
        System.err.println( "Usage: java -jar " + Main.class.getName() + " [instanceNumber]+" );
        System.exit( -1 );
    }
}
