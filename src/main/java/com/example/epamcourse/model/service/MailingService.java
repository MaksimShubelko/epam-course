package com.example.epamcourse.model.service;

import com.example.epamcourse.model.exception.ServiceException;

/**
 * interface MailingService
 *
 * @author M.Shubelko
 */
public interface MailingService {

    /**
     * The sending of message
     *
     * @param messageText the message text
     * @param messageSubject the message subject
     * @param sendTo the recipient's email
     * @throws ServiceException the service exception
     */
    void sendMessage(String messageText, String messageSubject, String sendTo) throws ServiceException;
}
