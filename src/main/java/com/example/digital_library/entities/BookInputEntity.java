package com.example.digital_library.entities;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.time.Instant;

@Data
@Builder
@With
public class BookInputEntity {
    private long id;

    @NotBlank(message="name is mendatory")
    private String name;

    @NotBlank(message="name is mendatory")
    private String author;
    private String description;
    private Instant publishedDate;
}
