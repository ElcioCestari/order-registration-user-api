package com.elciocestari.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "Users")
public class User implements Serializable {
    private ObjectId id;
    private String username;
    private String password;

    private Collection authorities;
}
