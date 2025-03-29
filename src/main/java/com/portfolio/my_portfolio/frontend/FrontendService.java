package com.portfolio.my_portfolio.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.my_portfolio.frontend.FrontendController.SendMessage;

@Service
public class FrontendService {

    @Autowired
    private FrontendRepository frontendRepository;
    
    public String showFrontend() {
        return "frontend/index";
    }
    
    public void sendMessage(SendMessage sendMessage) {
        try{
            frontendRepository.sendMessage(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
