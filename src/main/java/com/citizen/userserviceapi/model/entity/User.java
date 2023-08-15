package com.citizen.userserviceapi.model.entity;

import liquibase.util.StringUtil;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.CollectionUtils;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "users")
@Accessors(chain = true)
@Getter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "USERS_SEQ", allocationSize = 10)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Fetch(value = FetchMode.JOIN)
    @ElementCollection
    @CollectionTable(name = "user_dogs", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Long> dogIds;

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
