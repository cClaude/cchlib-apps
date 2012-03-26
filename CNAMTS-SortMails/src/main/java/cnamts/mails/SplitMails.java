package cnamts.mails;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import com.googlecode.cchlib.io.IOHelper;
import com.googlecode.cchlib.swing.DialogHelper;

/**
 * com.googlecode.cchlib.apps.sample2.Sample2
 */
public class SplitMails
    implements Iterable<InputStream>
{
    public final static String TESTINFILE = "J:/TBProfiles/ImapMail/imap.cn.cnamts-1.fr/Fichier B2 non valide - Erreur technique.sbd/jup60";
    public final static String TESTOUTDIR = "J:/";
    private InputStream thunderbirdMail;

    public SplitMails(
        final File thunderbirdMailFile
        )
        throws FileNotFoundException
    {
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream( thunderbirdMailFile )
                );
        this.thunderbirdMail = new FilterInputStream( in )
        {
            boolean prev_0D = false;
            boolean prev_0A = false;
            @Override
            public int available() throws IOException
            {
                return super.available() > 0 ? 1 : 0;
            }
            @Override
            public void close() throws IOException
            {
                super.close();
            }
            @Override
            public boolean markSupported()
            {
                return false;
            }
            @Override
            public synchronized void mark(int readlimit)
            {
                //super.mark(readlimit);
            }
            @Override
            public synchronized void reset() throws IOException
            {
                //super.reset();
            }
            @Override
            public int read() throws IOException
            {
                // TODO Auto-generated method stub
                return super.read();
            }
            @Override
            public int read(byte[] b, int off, int len) throws IOException
            {
                // TODO Auto-generated method stub
                return super.read(b, off, len);
            }
            @Override
            public int read(byte[] b) throws IOException
            {
                // TODO Auto-generated method stub
                return super.read(b);
            }
            @Override
            public long skip(long n) throws IOException
            {
                // TODO Auto-generated method stub
                return super.skip(n);
            }
        };
    }

    /**
     *
     *
     */
    public static void main(String[] args) throws IOException
    {
        final File outputDirFile = new File( TESTOUTDIR );

        SplitMails instance = new SplitMails(
            new File( TESTINFILE )
            );

        int i = 0;

        for( InputStream in : instance ) {
            OutputStream out = new BufferedOutputStream(
                new FileOutputStream(
                    new File( outputDirFile, Integer.toString( i++ ) )
                    )
                );
            IOHelper.copy(in, out );
            }

        DialogHelper.showMessageExceptionDialog( "OK", new Exception( "OK") );
    }


    @Override
    public Iterator<InputStream> iterator()
    {
        return new Iterator<InputStream>()
            {
                @Override
                public boolean hasNext()
                {
                    return true;
                }
                @Override
                public InputStream next()
                {
                    // TODO Auto-generated method stub
                    return null;
                }
                @Override
                public void remove()
                {
                    throw new UnsupportedOperationException();
                }
            };
    }

}
