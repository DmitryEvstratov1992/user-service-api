package com.citizen.userserviceapi.model.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import liquibase.util.StringUtil;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Entity(name = "users")
@Accessors(chain = true)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "USERS_SEQ", allocationSize = 10)
    private Long id;

    @Getter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @ElementCollection
    @CollectionTable(name = "user_dogs", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Long> dogIds;

    @Getter
    @Column(name = "organization_id")
    private Long organizationId;

    public User setFirstName(String firstName) {
        if (StringUtil.isEmpty(firstName)) {
            return this;
        }
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName) {
        if (StringUtil.isEmpty(lastName)) {
            return this;
        }
        this.lastName = lastName;
        return this;
    }

    public User setDogIds(Set<Long> dogIds) {
        if (CollectionUtils.isEmpty(dogIds)) {
            return this;
        }
        this.dogIds = dogIds;
        return this;
    }

    public User setOrganizationId(Long organizationId) {
        if (organizationId == null) {
            return this;
        }
        this.organizationId = organizationId;
        return this;
    }
}
