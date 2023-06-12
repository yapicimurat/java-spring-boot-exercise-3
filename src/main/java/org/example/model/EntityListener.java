package org.example.model;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class EntityListener {

    @PrePersist
    public void prePersist(Object entity) {
        if(entity instanceof BaseModel) {
            ((BaseModel)entity).setCreatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if(entity instanceof BaseModel) {
            ((BaseModel)entity).setUpdatedAt(LocalDateTime.now());
        }
    }
}
