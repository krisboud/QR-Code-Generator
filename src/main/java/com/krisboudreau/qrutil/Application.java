package com.krisboudreau.qrutil;

import com.google.zxing.WriterException;
import com.krisboudreau.qrutil.domain.QRCode;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Application Class that will hold the logic to run on Command Line.
 *
 * Created by krisboud(Kris Boudreau) on 2015-12-18.
 */
public class Application {

    @Option(name="-size",usage="The Size of the QR Code")
    private int size = 250;

    @Option(name="-fore-color",usage="Foreground color for QR code image.")
    private String foreColor = "000000";

    @Option(name="-back-color",usage="Background color for QR code image.")
    private String backColor = "FFFFFF";

    @Argument
    private List<String> arguments = new ArrayList<String>();


    public static void main(String[] args) {
        new Application().execute(args);
    }

    public void execute(String[] args) {

        // Parse the Command Line parameters
        CmdLineParser parser = new CmdLineParser(this);
        try {
            // parse the arguments.
            parser.parseArgument(args);

            // you can parse additional arguments if you want.
            // parser.parseArgument("more","args");

            // after parsing arguments, you should check
            // if enough arguments are given.
            if( arguments.isEmpty() )
                throw new CmdLineException(parser,new Throwable("No argument is given"));
        } catch (CmdLineException e) {
            // if there's a problem in the command line,
            // you'll get this exception. this will report
            // an error message.
            System.err.println(e.getMessage());
            System.err.println("java Application [options...] URL");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            return;
        }

        try {
            QRCode qrCode = new QRCode();

            qrCode.setUrl(arguments.get(0));
            qrCode.setHeight(size);
            qrCode.setWidth(size);

            qrCode.setBackgroundColor(
                Integer.valueOf( backColor.substring( 0, 2 ), 16 ),
                Integer.valueOf( backColor.substring( 2, 4 ), 16 ),
                Integer.valueOf( backColor.substring( 4, 6 ), 16 )
            );

            qrCode.setForegroundColor(
                    Integer.valueOf( foreColor.substring( 0, 2 ), 16 ),
                    Integer.valueOf( foreColor.substring( 2, 4 ), 16 ),
                    Integer.valueOf( foreColor.substring( 4, 6 ), 16 )
            );

            qrCode.generateQRCodeImage();

        } catch (WriterException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
