package org.example.tasksmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.tasksmanagementsystem.model.enums.Status;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity{
    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.OPEN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    public Task(String title, String description, User owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

}
