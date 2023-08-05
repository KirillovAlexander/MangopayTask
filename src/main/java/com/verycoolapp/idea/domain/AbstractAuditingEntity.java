package com.verycoolapp.idea.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

/**
 * An abstract class representing an entity with common creation and modification fields.
 * This class contains information about who and when the entity was created and modified.
 * Other entity classes can inherit from this class to manage common fields.
 */

@Getter
@Setter
public abstract class AbstractAuditingEntity {

    @CreatedBy
    @Field
    private String createdBy;

    @CreatedDate
    @Field
    private Instant createdDate;

    @LastModifiedBy
    @Field
    private String lastModifiedBy;

    @LastModifiedDate
    @Field
    private Instant lastModifiedDate;
}
