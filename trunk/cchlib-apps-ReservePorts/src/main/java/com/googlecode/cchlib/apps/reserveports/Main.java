package com.googlecode.cchlib.apps.reserveports;

import java.util.Set;
import java.util.TreeSet;
//import org.apache.log4j.Logger;
import com.googlecode.cchlib.apps.reserveports.port.LockPorts;
import com.googlecode.cchlib.apps.reserveports.port.LockPortsList;
import com.googlecode.cchlib.apps.reserveports.port.LockPortsRange;

/**
 *
 */
public class Main
{
    //private final static Logger logger = Logger.getLogger( Main.class );

    // Milliseconds between launching two services
    private static final long SLEEP_MS = 100;

    public final static void main(String[] args)
    {
        if( args.length == 0 ) {
            printHelp();
            }

        if( args[ 0 ].toLowerCase().equals( "-list" ) ) {
        	if( args.length != 3 ) {
                printHelp();
        		}
        	
            int min = Integer.parseInt( args[ 1 ] );
            int max = Integer.parseInt( args[ 2 ] );
            
            new LockPortsRange( min, max, SLEEP_MS).startServices();
        	}
        else {
            // Use an HashSet to avoid doubles !
            Set<Integer> portsList = new TreeSet<Integer>();

            for( int i =0; i<args.length; i++ ) {
                try {
                    int num = Integer.parseInt( args[ i ] );

                    if( num < 0 || num > 65536 ) {
                        System.err.println(
                            "port number must be a value in [1..65535]"
                            );
                        printHelp();
                        }

                    portsList.add( num );
                    }
                catch( NumberFormatException e ) {
                    System.err.println( "Bad number: " + args[ i ] );
                    printHelp();
                    }
                }
            
            new LockPortsList( portsList, SLEEP_MS).startServices();
        	}

        LockPorts.sleep( SLEEP_MS * 10 );

        System.out.println( "CTRL-C to leave." );
    }

    private static void printHelp()
    {
        System.err.println( "Usage: java -jar " + Main.class.getName() + " [-list [portnumber]+]|[firstport lastport]" );
        System.exit( -1 );
    }

}
