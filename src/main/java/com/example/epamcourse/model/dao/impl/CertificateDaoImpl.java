package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.CertificateDao;
import com.example.epamcourse.model.dao.mapper.impl.CertificateResultSetHandler;
import com.example.epamcourse.model.entity.Certificate;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class CertificateDaoImpl implements CertificateDao {
    private static final Logger logger = LogManager.getLogger();
    private static CertificateDao instance = new CertificateDaoImpl();

    private JdbcTemplates<Certificate> jdbcTemplate;

    private static final String FIND_ALL_CERTIFICATES = """
            SELECT certificate_id, total_mark
            FROM certificates
            """;

    private static final String FIND_CERTIFICATE_BY_ID = """
            SELECT certificate_id, total_mark
            WHERE certificate_id = ?
            """;

    private static final String DELETE_CERTIFICATE = """
              DELETE FROM certificates 
              WHERE certificate_id = ?
            """;

    private static final String ADD_CERTIFICATE = """
            INSERT INTO certificates (certificate_id, total_mark)
            VALUE (?, ?, ?, ?)
            """;

    private static final String UPDATE_CERTIFICATE = """
            UPDATE certificates SET total_mark = ?
            WHERE certificate_id = ?
            """;



    private CertificateDaoImpl() {
        this.jdbcTemplate = new JdbcTemplates<>(new CertificateResultSetHandler());
    }

    @Override
    public List<Certificate> findAll() throws DaoException {
        List<Certificate>  certificates = jdbcTemplate.executeSelectQuery(FIND_ALL_CERTIFICATES);

        return certificates;
    }

    @Override
    public Optional<Certificate> findEntityById(Long id) throws DaoException {
        Optional<Certificate> certificate = jdbcTemplate.executeSelectQueryForObject(FIND_CERTIFICATE_BY_ID, id);

        return certificate;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CERTIFICATE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting certificate with Id {}. {}", id, e.getMessage());
            throw new DaoException("Error when deleting certificate with Id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Certificate certificate) throws DaoException {
        long certificateId = jdbcTemplate.executeInsertQuery(ADD_CERTIFICATE,
                certificate.getTotalMark());

        return certificateId;
    }

    @Override
    public boolean update(Certificate certificate, String hashPassword) throws DaoException {
        jdbcTemplate.executeInsertQuery(UPDATE_CERTIFICATE,
                certificate.getTotalMark(),
                certificate.getCertificateId());

        return true;

    }
}
