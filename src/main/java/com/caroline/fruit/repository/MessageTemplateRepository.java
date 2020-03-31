package com.caroline.fruit.repository;

import com.caroline.fruit.model.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {

    MessageTemplate findByFormatKey(String key);
}
