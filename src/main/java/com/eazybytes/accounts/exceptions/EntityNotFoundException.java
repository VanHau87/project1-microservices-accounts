package com.eazybytes.accounts.exceptions;

import com.eazybytes.accounts.utils.MessageUtils;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String entityName;
    private final String entityId;
    
    public EntityNotFoundException(String entityName, Long id) {
        super(MessageUtils.getMessage("entity.notfound.id", entityName, id));
        this.entityName = entityName;
        this.entityId = id.toString();
    }

    public EntityNotFoundException(String entityName, String value) {
        super(MessageUtils.getMessage("entity.notfound.value", entityName, value));
        this.entityName = entityName;
        this.entityId = value;
    }
}
