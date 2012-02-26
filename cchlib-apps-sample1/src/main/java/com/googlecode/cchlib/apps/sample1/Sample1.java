package com.googlecode.cchlib.apps.sample1;

import com.googlecode.cchlib.swing.DialogHelper;

/**
 * com.googlecode.cchlib.apps.sample1.Sample1
 */
public class Sample1
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        DialogHelper.showMessageExceptionDialog( "OK", new Exception( "OK") );
    }

}
