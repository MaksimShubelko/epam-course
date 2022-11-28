package com.example.epamcourse.model.pool;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * class ProxyConnection
 *
 * @author M.Shubelko
 */
public class ProxyConnection implements Connection {
    private Connection connection;

    /**
     * The instantiation of a new proxy connection
     *
     * @param connection the connection
     */
    ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * The creation of statement
     *
     * @return the statement
     * @throws SQLException the SQL exception
     */
    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * The preparation of statement
     *
     * @param sql the sql
     * @return the prepared statement
     * @throws SQLException the SQL exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     * The preparation of call
     *
     * @param sql the sql
     * @return the callable statement
     * @throws SQLException the SQL exception
     */
    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    /**
     * The Native SQL
     *
     * @param sql the sql
     * @return the string
     * @throws SQLException the SQL exception
     */
    @Override
    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    /**
     * The setting the auto commit
     *
     * @param autoCommit the new auto commit
     * @throws SQLException the SQL exception
     */
    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    /**
     * The getting the auto commit
     *
     * @return the auto commit
     * @throws SQLException the SQL exception
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    /**
     * The committing
     *
     * @throws SQLException the SQL exception
     */
    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * The rolling back
     *
     * @throws SQLException the SQL exception
     */
    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * The closing
     *
     * @throws SQLException the SQL exception
     */
    @Override
    public void close() throws SQLException {
        if (!getAutoCommit()) {
            connection.setAutoCommit(true);
        }
        ConnectionPool.getInstance().releaseConnection(this);
    }

    /**
     * The real closing
     *
     * @throws SQLException the SQL exception
     */
    void realClose() throws SQLException {
        connection.close();
    }

    /**
     * The checking if connection is closed.
     *
     * @return true, if is closed
     * @throws SQLException the SQL exception
     */
    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    /**
     * The getting of meta data
     *
     * @return the meta data
     * @throws SQLException the SQL exception
     */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    /**
     * The setting read only
     *
     * @param readOnly the read only
     * @throws SQLException the SQL exception
     */
    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    /**
     * The checking if connection is read only
     *
     * @return true, if is read only
     * @throws SQLException the SQL exception
     */
    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    /**
     * The setting the catalog
     *
     * @param catalog the new catalog
     * @throws SQLException the SQL exception
     */
    @Override
    public void setCatalog(String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    /**
     * The getting of the catalog
     *
     * @return the catalog
     * @throws SQLException the SQL exception
     */
    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    /**
     * The setting of transaction isolation
     *
     * @param level the new transaction isolation
     * @throws SQLException the SQL exception
     */
    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    /**
     * The getting of transaction isolation
     *
     * @return the transaction isolation
     * @throws SQLException the SQL exception
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    /**
     * The getting of warnings.
     *
     * @return the warnings
     * @throws SQLException the SQL exception
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    /**
     * The clearing of warnings
     *
     * @throws SQLException the SQL exception
     */
    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    /**
     * The creation of statement
     *
     * @param resultSetType        the result set subjectType
     * @param resultSetConcurrency the result set concurrency
     * @return the statement
     * @throws SQLException the SQL exception
     */
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * The preparing of statement
     *
     * @param sql                  the sql
     * @param resultSetType        the result set subjectType
     * @param resultSetConcurrency the result set concurrency
     * @return the prepared statement
     * @throws SQLException the SQL exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * The preparing of call
     *
     * @param sql                  the sql
     * @param resultSetType        the result set subjectType
     * @param resultSetConcurrency the result set concurrency
     * @return the callable statement
     * @throws SQLException the SQL exception
     */
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * The getting of subjectType map
     *
     * @return the subjectType map
     * @throws SQLException the SQL exception
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    /**
     * The setting of subjectType map
     *
     * @param map the map
     * @throws SQLException the SQL exception
     */
    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    /**
     * The setting of holdability
     *
     * @param holdability the new holdability
     * @throws SQLException the SQL exception
     */
    @Override
    public void setHoldability(int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    /**
     * The getting of holdability
     *
     * @return the holdability
     * @throws SQLException the SQL exception
     */
    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    /**
     * The setting of savepoint
     *
     * @return the savepoint
     * @throws SQLException the SQL exception
     */
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    /**
     * The setting of savepoint
     *
     * @param name the name
     * @return the savepoint
     * @throws SQLException the SQL exception
     */
    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    /**
     * The rolling back
     *
     * @param savepoint the savepoint
     * @throws SQLException the SQL exception
     */
    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    /**
     * The realisation of savepoint
     *
     * @param savepoint the savepoint
     * @throws SQLException the SQL exception
     */
    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    /**
     * The creation of statement
     *
     * @param resultSetType        the result set subjectType
     * @param resultSetConcurrency the result set concurrency
     * @param resultSetHoldability the result set holdability
     * @return the statement
     * @throws SQLException the SQL exception
     */
    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * The preparing of statement
     *
     * @param sql                  the sql
     * @param resultSetType        the result set subjectType
     * @param resultSetConcurrency the result set concurrency
     * @param resultSetHoldability the result set holdability
     * @return the prepared statement
     * @throws SQLException the SQL exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * The preparing of call
     *
     * @param sql                  the sql
     * @param resultSetType        the result set subjectType
     * @param resultSetConcurrency the result set concurrency
     * @param resultSetHoldability the result set holdability
     * @return the callable statement
     * @throws SQLException the SQL exception
     */
    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * The preparing of statement
     *
     * @param sql               the sql
     * @param autoGeneratedKeys the auto generated keys
     * @return the prepared statement
     * @throws SQLException the SQL exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    /**
     * The preparing of statement
     *
     * @param sql           the sql
     * @param columnIndexes the column indexes
     * @return the prepared statement
     * @throws SQLException the SQL exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    /**
     * The preparing of statement
     *
     * @param sql         the sql
     * @param columnNames the column names
     * @return the prepared statement
     * @throws SQLException the SQL exception
     */
    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    /**
     * The creation of clob
     *
     * @return the clob
     * @throws SQLException the SQL exception
     */
    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    /**
     * The creation of blob
     *
     * @return the blob
     * @throws SQLException the SQL exception
     */
    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    /**
     * The creation of N clob
     *
     * @return the n clob
     * @throws SQLException the SQL exception
     */
    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    /**
     * The creation of SQLXML
     *
     * @return the sqlxml
     * @throws SQLException the SQL exception
     */
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    /**
     * The checking of validation
     *
     * @param timeout the timeout
     * @return true, if is valid
     * @throws SQLException the SQL exception
     */
    @Override
    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    /**
     * The setting of client info
     *
     * @param name  the name
     * @param value the value
     * @throws SQLClientInfoException the SQL client info exception
     */
    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    /**
     * The setting of client info
     *
     * @param properties the new client info
     * @throws SQLClientInfoException the SQL client info exception
     */
    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    /**
     * The getting of client info
     *
     * @param name the name
     * @return the client info
     * @throws SQLException the SQL exception
     */
    @Override
    public String getClientInfo(String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    /**
     * The getting of client info
     *
     * @return the client info
     * @throws SQLException the SQL exception
     */
    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    /**
     * The creation of array of
     *
     * @param typeName the subjectType name
     * @param elements the elements
     * @return the array
     * @throws SQLException the SQL exception
     */
    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    /**
     * The creation of struct
     *
     * @param typeName   the subjectType name
     * @param attributes the attributes
     * @return the struct
     * @throws SQLException the SQL exception
     */
    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    /**
     * The setting of schema
     *
     * @param schema the new schema
     * @throws SQLException the SQL exception
     */
    @Override
    public void setSchema(String schema) throws SQLException {
        connection.setSchema(schema);
    }

    /**
     * The getting of the schema
     *
     * @return the schema
     * @throws SQLException the SQL exception
     */
    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    /**
     * The aborting
     *
     * @param executor the executor
     * @throws SQLException the SQL exception
     */
    @Override
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    /**
     * The setting of network timeout
     *
     * @param executor     the executor
     * @param milliseconds the milliseconds
     * @throws SQLException the SQL exception
     */
    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    /**
     * The getting of network timeout
     *
     * @return the network timeout
     * @throws SQLException the SQL exception
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    /**
     * The beginning of request
     *
     * @throws SQLException the SQL exception
     */
    @Override
    public void beginRequest() throws SQLException {
        connection.beginRequest();
    }

    /**
     * The ending of request
     *
     * @throws SQLException the SQL exception
     */
    @Override
    public void endRequest() throws SQLException {
        connection.endRequest();
    }

    /**
     * The setting of sharding key if valid
     *
     * @param shardingKey      the sharding key
     * @param superShardingKey the super sharding key
     * @param timeout          the timeout
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    @Override
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, ShardingKey superShardingKey, int timeout) throws SQLException {
        return connection.setShardingKeyIfValid(shardingKey, superShardingKey, timeout);
    }

    /**
     * The setting of sharding key if valid
     *
     * @param shardingKey the sharding key
     * @param timeout     the timeout
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    @Override
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, int timeout) throws SQLException {
        return connection.setShardingKeyIfValid(shardingKey, timeout);
    }

    /**
     * The setting of sharding key
     *
     * @param shardingKey      the sharding key
     * @param superShardingKey the super sharding key
     * @throws SQLException the SQL exception
     */
    @Override
    public void setShardingKey(ShardingKey shardingKey, ShardingKey superShardingKey) throws SQLException {
        connection.setShardingKey(shardingKey, superShardingKey);
    }

    /**
     * The setting of sharding key
     *
     * @param shardingKey the new sharding key
     * @throws SQLException the SQL exception
     */
    @Override
    public void setShardingKey(ShardingKey shardingKey) throws SQLException {
        connection.setShardingKey(shardingKey);
    }

    /**
     * The unwrapping
     *
     * @param <T>   the generic subjectType
     * @param iface the iface
     * @return the t
     * @throws SQLException the SQL exception
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    /**
     * The checking of wrapper for
     *
     * @param iface the iface
     * @return true, if is wrapper for
     * @throws SQLException the SQL exception
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }
}