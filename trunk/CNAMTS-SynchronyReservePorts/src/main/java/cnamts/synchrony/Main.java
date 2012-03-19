package cnamts.synchrony;

import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import cnamts.synchrony.port.LockInstancePorts;

/**
 *
 */
public class Main
{
    private final static Logger logger = Logger.getLogger( Main.class );

    // Milliseconds between launching two services
    private static final long SLEEP_MS = 100;

    public final static void main(String[] args)
    {
        if( args.length == 0 ) {
            printHelp();
            }

        // Use an HashSet to avoid doubles !
        Set<Integer> instanceNumbers = new TreeSet<Integer>();

        for(int i =0; i<args.length; i++ ) {
            try {
                int num = Integer.parseInt( args[ i ] );

                if( num < 0 || num > 9 ) {
                    System.err.println(
                        "Instance number must be a value in [0..9]"
                        );
                    printHelp();
                    }

                instanceNumbers.add( num );
                }
            catch( NumberFormatException e ) {
                System.err.println( "Bad number: " + args[ i ] );
                printHelp();
                }
            }

        for( int instanceNumber : instanceNumbers ) {
            new LockInstancePorts(instanceNumber, SLEEP_MS).startServices();
            }

        LockInstancePorts.sleep( SLEEP_MS * 10 );

        logger.info( "Reservation for " + instanceNumbers.size() + " instance(s). Done." );

        System.out.println( "CTRL-C to leave." );
    }

    private static void printHelp()
    {
        System.err.println( "Usage: java -jar " + Main.class.getName() + " [instanceNumber]+" );
        System.exit( -1 );
    }

}
