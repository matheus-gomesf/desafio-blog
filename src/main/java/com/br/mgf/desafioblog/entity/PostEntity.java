package com.br.mgf.desafioblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    private String text;

    private String images;

    private String link;

    @ManyToOne(optional = false, targetEntity = UserEntity.class)
    private UserEntity author;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<CommentEntity> comments;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

}
