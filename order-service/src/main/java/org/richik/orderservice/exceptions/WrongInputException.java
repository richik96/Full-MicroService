package org.richik.orderservice.exceptions;

import org.apache.logging.log4j.message.Message;

public class WrongInputException extends Exception{
    public WrongInputException(String message) {
        super(message);
    }
}
