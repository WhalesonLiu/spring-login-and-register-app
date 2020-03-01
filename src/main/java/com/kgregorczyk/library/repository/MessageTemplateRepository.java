package com.kgregorczyk.library.repository;

import com.kgregorczyk.library.model.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {

    MessageTemplate findByFormatKey(String key);
}
