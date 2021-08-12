package com.polymathus.gaggle.persist;

import java.sql.Connection;

public interface Database {
    public abstract Connection getConnection();
}
