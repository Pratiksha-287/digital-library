package com.example.digital_library.entities;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@Builder
@Table(name="book")
@NoArgsConstructor
@AllArgsConstructor
public class BookOutputEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id",nullable = false)
    private long id;
    @Column(name="name",length=100,nullable = false)
    private String name;
    @Column(name="author",length=100,nullable = false)
    private String author;
    @Column(name="description")
    private String description;
    @Column(name="published_date")
    private Instant publishedDate;
    @Column(name="updated_at")
    private Instant updatedAt;
    @Column(name="created_at")
    private Instant createdAt;


}
