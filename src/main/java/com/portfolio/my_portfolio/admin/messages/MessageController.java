package com.portfolio.my_portfolio.admin.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin/messages")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getMessages(Model model) {
        logger.info("Fetching all messages for admin dashboard");
        try {
            var messages = messageService.getMessages();
            logger.debug("Retrieved {} messages", messages.size());
            model.addAttribute("messages", messages);
            return "admin/messages";
        } catch (Exception e) {
            logger.error("Error retrieving messages", e);
            model.addAttribute("error", "Failed to load messages. Please try again later.");
            model.addAttribute("messages", java.util.Collections.emptyList());
            return "admin/messages";
        }
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteMessage(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        logger.info("Request to delete message with ID: {}", id);

        if (id == null) {
            logger.warn("Delete message request received with null ID");
            redirectAttributes.addFlashAttribute("error", "Invalid message ID");
            return new RedirectView("/admin/messages");
        }

        try {
            boolean deleted = messageService.deleteMessage(id);
            if (deleted) {
                logger.info("Successfully deleted message with ID: {}", id);
                redirectAttributes.addFlashAttribute("success", "Message deleted successfully");
            } else {
                logger.warn("Failed to delete message with ID: {}. Message not found or already deleted", id);
                redirectAttributes.addFlashAttribute("warning", "Message not found or already deleted");
            }
        } catch (Exception e) {
            logger.error("Error deleting message with ID: {}", id, e);
            redirectAttributes.addFlashAttribute("error", "An error occurred while deleting the message");
        }

        return new RedirectView("/admin/messages");
    }
}