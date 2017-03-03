package com.crawl.db.impl;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;

import com.crawl.db.PostgresDBService;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;

public class PostgresDBServiceImpl implements PostgresDBService {

    private ComboPooledDataSource comboPooledDataSource;

    private PreparedStatement insertKeyStatement;

    public PostgresDBServiceImpl(String dbUrl, String dbUser, String dbPw, String driver) throws
            PropertyVetoException, SQLException {
        comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(dbUrl);
        comboPooledDataSource.setUser(dbUser);
        comboPooledDataSource.setPassword(dbPw);
    }

    @Override
    public void store(Page page) {

        if (page.getParseData() instanceof HtmlParseData) {
            try {
            	insertKeyStatement = comboPooledDataSource.getConnection().prepareStatement("insert into webpage values " +
            			"(nextval('id_master_seq'),?,?,?,?)");

                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

                insertKeyStatement.setString(1, htmlParseData.getHtml());
                insertKeyStatement.setString(2, htmlParseData.getText());
                insertKeyStatement.setString(3, page.getWebURL().getURL());
                insertKeyStatement.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));
                insertKeyStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() {
        if (comboPooledDataSource != null) {
            comboPooledDataSource.close();
        }
    }
}
