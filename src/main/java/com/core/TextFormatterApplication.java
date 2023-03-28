package com.core;

import com.core.exceptions.InvalidOperationClassException;
import com.core.exceptions.OperationNotSupportedException;
import com.core.operations.TextOperation;
import com.core.factory.TextOperationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TextFormatterApplication {

    final static Logger log = LogManager.getLogger(TextFormatterApplication.class);
    public static void main(String[] args) {

        if (args == null || args.length == 0 || args.length != 3){
            log.error("Invalid arguments count: " + args.length);
            System.exit(1);
        }

        try{
            TextOperationFactory textOperationFactory = new TextOperationFactory();
            TextOperation textOperation = textOperationFactory.getTextOperationAdapter(args[2]);
            textOperation.runOperation(args[0], args[1]);
        }catch(IOException ex){
            log.error("Failed to read/write file: " + ex.getMessage());
            System.exit(1);
        }catch(OperationNotSupportedException op){
            log.error(op);
            System.exit(1);
        }catch(InvalidOperationClassException in){
            log.error(in);
            System.exit(1);
        }catch(ClassNotFoundException ex){
            log.error("Operation class not found: " + ex.getMessage());
            System.exit(1);
        }catch(Exception ex){
            log.error("Unknown issue: " + ex.getMessage());
            System.exit(1);
        }

        log.info("Output file " + args[1] + " created successfully after " + args[2] + " operation performed.");
        System.exit(0);
    }
}
