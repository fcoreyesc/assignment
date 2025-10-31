package com.reyes.assignment.repository.user;

import com.reyes.assignment.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Convert UserEntity to User domain object
     */
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return User.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .build();
    }

    /**
     * Convert User domain object to UserEntity
     */
    public UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }

        return UserEntity.builder()
                .id(domain.getId())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .email(domain.getEmail())
                .phoneNumber(domain.getPhoneNumber())
                .address(domain.getAddress())
                .build();
    }
}