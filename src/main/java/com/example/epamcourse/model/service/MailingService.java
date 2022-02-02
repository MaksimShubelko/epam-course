package com.example.epamcourse.model.service;

import com.example.epamcourse.model.exception.ServiceException;

public interface MailingService {

    boolean sendMessage(String messageText, String messageSubject, String sendTo) throws ServiceException;
}
