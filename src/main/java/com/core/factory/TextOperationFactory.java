package com.core.factory;

import com.core.exceptions.InvalidOperationClassException;
import com.core.exceptions.OperationNotSupportedException;
import com.core.operations.TextOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TextOperationFactory {

    final static Logger log = LogManager.getLogger(TextOperationFactory.class);
     private final static String OPERATION_FILE ="operation.properties";
    public TextOperationFactory() {}

    public static TextOperation getTextOperationAdapter(String operation) throws ClassNotFoundException,
                                                                                 InstantiationException,
                                                                                 IllegalAccessException,
                                                                                 IOException,
                                                                                 OperationNotSupportedException,
                                                                                 InvalidOperationClassException {

        //Load a properties file from classpath
        try(InputStream input = TextOperationFactory.class.getClassLoader().getResourceAsStream(OPERATION_FILE)){
            Properties properties = new Properties();
            properties.load(input);

            if(properties.get(operation) == null){
                throw new OperationNotSupportedException("Operation = " + operation + " not supported");
            };

            Class clazz = Class.forName((String) properties.get(operation));
            Object object = clazz.newInstance();

            if(!(object instanceof TextOperation)){
                throw new InvalidOperationClassException("Invalid operation class = " + properties.get(operation));
            }
            return (TextOperation) object;
        }
    }
}
