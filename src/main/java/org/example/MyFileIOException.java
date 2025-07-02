package org.example;

import java.io.IOException;

class MyFileIOException extends IOException {
    public MyFileIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
