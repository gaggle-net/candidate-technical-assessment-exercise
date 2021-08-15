package com.polymathus.gaggle.persist;

import java.sql.Connection;

/**
 * The Database interface.
 */
public interface Database {
    public abstract Connection getConnection();
}
