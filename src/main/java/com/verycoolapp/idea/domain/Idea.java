package com.verycoolapp.idea.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents an Idea entity.
 * An Idea is a concept or suggestion created by a user.
 */

@Data
@Document(collection = "idea")
public class Idea extends AbstractAuditingEntity {

    @Id
    private String id;

    @Field
    private String title;

    @Field
    private String description;

    @Field
    private String group;
}
