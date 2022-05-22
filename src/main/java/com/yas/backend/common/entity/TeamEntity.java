package com.yas.backend.common.entity;

import com.google.common.collect.Lists;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "teams")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_user_count", nullable = false)
    private Long maxUserCount;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne
    private UserEntity owner;

    @OneToMany(mappedBy = "team")
    private List<JoinEntity> joins;

    @ManyToOne
    @JoinColumn(name = "topic")
    private TechStackEntity topic;

    @ManyToMany
    @JoinTable(name = "team_techstack")
    private Set<TechStackEntity> techStacks;

    @OneToOne
    private ScheduleEntity schedule;

    @Column
    private boolean isActive;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public List<JoinEntity> getJoins() {
        if (CollectionUtils.isEmpty(this.joins)) {
            return Lists.newArrayList();
        }

        return this.joins;
    }
}
