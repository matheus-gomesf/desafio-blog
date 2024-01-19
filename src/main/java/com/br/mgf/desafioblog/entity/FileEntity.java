package com.br.mgf.desafioblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fileName;

    private String fileType;

    private byte[] fileData;

    @ManyToOne(targetEntity = AlbumEntity.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private AlbumEntity album;

    @ManyToOne(targetEntity = PostEntity.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private PostEntity post;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }
}