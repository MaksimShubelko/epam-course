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
import java.io.IOException;
import java.util.Properties;

/**
 * class MailingServiceImpl
 *
 * @author M.Shubelko
 */
public class MailingServiceImpl implements MailingService {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance
     */
    private static final MailingService instance = new MailingServiceImpl();

    /**
     * The private constructor
     */
    private MailingServiceImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static MailingService getInstance() {
        return instance;
    }


    /**
     * The sending of message
     *
     * @param messageText the message text
     * @param messageTitle the message title
     * @param sendTo the recipient's email
     * @throws ServiceException the service exception
     */
    public void sendMessage(String messageText, String messageTitle, String sendTo) throws ServiceException {
        final String password = "utdcxpekipbsumsx";
        try {
            Session session = createSession();
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject(messageTitle);
            message.setText(messageText);
            Transport transport = session.getTransport();
            transport.connect(null, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException | IOException e) {
            logger.log(Level.ERROR, "Error when sent email", e);
            throw new ServiceException("Error when sent email", e);
        }

    }

    /**
     * The creation of session for sender
     *
     * @return session the session
     * @throws IOException the IOE exception
     */
    public Session createSession() throws IOException {
        FilePropertyReader filePropertyReader = new FilePropertyReaderImpl();
        Properties props = System.getProperties();
        props.load(filePropertyReader.fileReadProperty());
        return Session.getDefaultInstance(props);
    }

}
