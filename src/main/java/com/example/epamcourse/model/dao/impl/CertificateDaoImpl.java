package com.example.epamcourse.model.dao.impl;

import com.example.epamcourse.model.dao.CertificateDao;
import com.example.epamcourse.model.dao.mapper.impl.CertificateResultSetHandler;
import com.example.epamcourse.model.entity.Certificate;
import com.example.epamcourse.model.exception.DaoException;
import com.example.epamcourse.model.exception.TransactionException;
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

    private JdbcTemplate<Certificate> jdbcTemplate;

    private static final String FIND_ALL_CERTIFICATES = """
            SELECT certificate_id, middle_mark
            FROM certificates
            """;

    private static final String FIND_CERTIFICATE_BY_ID = """
            SELECT certificate_id, middle_mark
            FROM certificates
            WHERE certificate_id = ?
            """;

    private static final String DELETE_CERTIFICATE = """
              DELETE FROM certificates
              WHERE 
              (SELECT certificate_id FROM applicants WHERE applicant_id = ?) = certificate_id
            """;

    private static final String ADD_CERTIFICATE = """
            INSERT INTO certificates (middle_mark)
            VALUE (?)
            """;

    private static final String UPDATE_CERTIFICATE = """
            UPDATE certificates SET middle_mark = ?
            WHERE certificate_id = ?
            """;



    private CertificateDaoImpl() {
        this.jdbcTemplate = new JdbcTemplate<>(new CertificateResultSetHandler());
    }

    @Override
    public List<Certificate> findAll() throws DaoException {
        List<Certificate>  certificates = null;
        try {
            certificates = jdbcTemplate.executeSelectQuery(FIND_ALL_CERTIFICATES);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding all certificates {}", e);
            throw new DaoException("Error when finding all certificates", e);
        }

        return certificates;
    }

    @Override
    public Optional<Certificate> findEntityById(Long id) throws DaoException {
        Optional<Certificate> certificate;
        try {
            certificate = jdbcTemplate.executeSelectQueryForObject(FIND_CERTIFICATE_BY_ID, id);
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when finding certificate by id {} {}", id, e);
            throw new DaoException("Error when finding certificate by id " + id, e);
        }

        return certificate;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CERTIFICATE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error when deleting certificate with id {}. {}", id, e);
            throw new DaoException("Error when deleting certificate with id " + id, e);
        }

        return true;
    }

    @Override
    public Long add(Certificate certificate) throws DaoException {
        long certificateId = 0;
        try {
            certificateId = jdbcTemplate.executeInsertQuery(ADD_CERTIFICATE,
                    certificate.getTotalMark());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when adding certificate", e);
            throw new DaoException("Error when adding certificate", e);
        }

        return certificateId;
    }

    @Override
    public boolean update(Certificate certificate) throws DaoException {
        try {
            jdbcTemplate.executeInsertQuery(UPDATE_CERTIFICATE,
                    certificate.getTotalMark(),
                    certificate.getCertificateId());
        } catch (TransactionException e) {
            logger.log(Level.ERROR, "Error when updating certificate", e);
            throw new DaoException("Error when updating certificate", e);
        }

        return true;
    }

    public static CertificateDao getInstance() {
        return instance;
    }
}
