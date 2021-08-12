package com.pt.postman.sync;

import com.pt.postman.state.ComposerState;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static com.pt.postman.logging.LoggingService._info;
import static com.pt.postman.logging.LoggingService._severe;

/**
 * @author nate-pt
 * @date 2021/8/12 14:15
 * @Since 1.8
 * @Description 内存数据库管理器
 */
class SQLiteManager implements DataManager {
    /**
     * jdgc 链接
     */
    private Connection conn;
    /**
     * sql处理器
     */
    private PreparedStatement statement;


    public SQLiteManager(){
        try {
            String configPath = "conf/";
            File configFolder = new File(configPath);
            // 没有该目录的话进行创建
            if (!configFolder.exists()) {
                configFolder.mkdir();
                _info("创建config文件夹", LocalDateTime.now());
            }

            // 创建sqlite 数据库链接
            conn = DriverManager.getConnection("jdbc:sqlite:config/history.sqlite");
            _create_database();
        } catch (SQLException throwables) {
            _severe("sqlite数据库初始化失败...",throwables,LocalDateTime.now());
        }


    }

    /**
     * 创建数据库
     */
    private void _create_database() throws SQLException {
        for (String query : Queries.CREATE_TABLES) {
            statement = conn.prepareStatement(query);
            statement.execute();
        }
    }


    @Override
    public void saveState(ComposerState state) throws Exception{

    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public List<ComposerState> getHistory() throws Exception {
        return null;
    }

    @Override
    public ComposerState getLastAdded() {
        return null;
    }

    /**
     * 默认执行sql
     */
    private static  class Queries{
        /**
         * 默认创建的表
         */
        private static final String [] CREATE_TABLES = {
                "CREATE TABLE IF NOT EXISTS Requests(ID INTEGER PRIMARY KEY, Type TEXT NOT NULL, Target TEXT NOT NULL, AuthMethod TEXT, Date TEXT NOT NULL)",
                "CREATE TABLE IF NOT EXISTS RequestContentMap(RequestID INTEGER, ContentType TEXT NOT NULL, FOREIGN KEY(RequestID) REFERENCES Requests(ID))",
                "CREATE TABLE IF NOT EXISTS Bodies(RequestID INTEGER, Type TEXT NOT NULL CHECK(Type IN ('application/json', 'application/xml', 'text/html', 'text/plain')), Body TEXT NOT NULL, FOREIGN KEY(RequestID) REFERENCES Requests(ID))",
                "CREATE TABLE IF NOT EXISTS FilePaths(RequestID INTEGER, Path TEXT NOT NULL, FOREIGN KEY(RequestID) REFERENCES Requests(ID))",
                "CREATE TABLE IF NOT EXISTS Tuples(RequestID INTEGER, Type TEXT NOT NULL CHECK(Type IN ('Header', 'Param', 'URLString', 'FormString', 'File')), Key TEXT NOT NULL, Value TEXT NOT NULL, Checked INTEGER CHECK (Checked IN (0, 1)), FOREIGN KEY(RequestID) REFERENCES Requests(ID))",
                "CREATE TABLE IF NOT EXISTS SimpleAuthCredentials(RequestID INTEGER, Type TEXT NOT NULL, Username TEXT NOT NULL, Password TEXT NOT NULL, Enabled INTEGER CHECK (Enabled IN (1, 0)), FOREIGN KEY(RequestID) REFERENCES Requests(ID))"
        };
    }
}
