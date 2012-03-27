package cnamts.mails;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 * com.googlecode.cchlib.apps.sample2.Sample2
 */
public class SplitMails implements Iterable<InputStream>
{
    public final static String TESTINFILE = "C:/Documents and Settings/choisnet-99999/Bureau/jup60/jup60.mails2";
    public final static String TESTOUTDIR = "C:/temp/";
    private final static Logger logger = Logger.getLogger( SplitMails.class );
    private MailLineInputStream thunderbirdMails;

    /**
     *
     * @param thunderbirdMailFile
     * @throws FileNotFoundException
     */
    public SplitMails(
        final File thunderbirdMailFile
        )
        throws FileNotFoundException
    {
        this.thunderbirdMails = new MailLineInputStream(
                new BufferedInputStream(
                    new FileInputStream( thunderbirdMailFile )
                    )
                );
     }

    /**
     *
     *
     */
    public static void main( String[] args ) throws IOException
    {
        final File inFile        = new File( TESTINFILE );
        final File outputDirFile = new File( TESTOUTDIR );

        logger.info( "In File: " + inFile );
        logger.info( "Out Dir: " + outputDirFile );

        SplitMails instance  = new SplitMails( inFile );
        int        mailCount = 0;


        for( InputStream in : instance ) {
            File outFile = new File( outputDirFile, Integer.toString( mailCount++ ) );
            logger.info( "out File: " + outFile );

//            OutputStream out = new BufferedOutputStream(
//                new FileOutputStream( outFile )
//                );

            byte[] buffer = new byte[ 409600 ];
            int    index  = 0;
            int    c;

            while( (c = in.read()) != -1 ) {
                buffer[ index++ ] = (byte)c;
                }
            
            logger.info(
                    String.format(
                        "MSG (%d):",
                        index,
                        new String( buffer, 0, index )
                        )
                    );
            }

        logger.info( "Done : mail count = " + mailCount );
        //DialogHelper.showMessageExceptionDialog( "OK", new Exception( "OK") );
    }

    @Override
    public Iterator<InputStream> iterator()
    {
        return new Iterator<InputStream>()
            {
                private MailInputStream lastMIS = null;

                @Override
                public boolean hasNext()
                {
                    if( lastMIS == null ) {
                        return true;
                        }
                    else {
                        return lastMIS.isEOF();
                        }
                }
                @Override
                public InputStream next()
                {
                    lastMIS = new MailInputStream( thunderbirdMails );

                    return lastMIS;
               }
                @Override
                public void remove()
                {
                    throw new UnsupportedOperationException();
                }
            };
    }
}
