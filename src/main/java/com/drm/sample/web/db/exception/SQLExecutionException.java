package com.drm.sample.web.db.exception;

public class SQLExecutionException extends RuntimeException {


    private static final long serialVersionUID = -8842538211185028180L;

    public SQLExecutionException(Exception couse) {
        super(couse);
    }
}
