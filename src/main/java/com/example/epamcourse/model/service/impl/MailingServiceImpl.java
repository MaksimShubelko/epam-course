package com.example.epamcourse.model.service.impl;

import com.example.epamcourse.model.exception.ServiceException;
import com.example.epamcourse.model.service.MailingService;
import com.example.epamcourse.util.FilePropertyReader;
import com.example.epamcourse.util.impl.FilePropertyReaderImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MailingServiceImpl implements MailingService {
    private static final MailingService instance = new MailingServiceImpl();
    private static final Logger logger = LogManager.getLogger();

    private MailingServiceImpl() {
    }

    public static MailingService getInstance() {
        return instance;
    }


    public boolean sendMessage(String messageText, String messageSubject, String sendTo) throws ServiceException {
        final String host = "localhost";
        final String password = "utdcxpekipbsumsx";
        boolean isMessageWasSent = false;
        try {
            Session session = createSession();
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject(messageSubject);
            message.setText(messageText);
            Transport transport = session.getTransport();
            transport.connect(null, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            isMessageWasSent = true;
        } catch (MessagingException | IOException e) {
            logger.log(Level.ERROR, "Error when sent email", e);
            throw new ServiceException("Error when sent email", e);
        }

        return isMessageWasSent;
    }

    public Session createSession() throws IOException {
        FilePropertyReader filePropertyReader = new FilePropertyReaderImpl();
        Properties props = System.getProperties();
        props.load(filePropertyReader.fileReadProperty());

        return Session.getDefaultInstance(props);
    }

}
