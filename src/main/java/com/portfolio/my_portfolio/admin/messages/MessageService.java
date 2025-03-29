package com.portfolio.my_portfolio.admin.messages;

import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.my_portfolio.admin.messages.MessageRepository.GetMessages;

@Service
public class MessageService {
    
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<GetMessages> getMessages() {
        return messageRepository.getMessages();
    }
}
