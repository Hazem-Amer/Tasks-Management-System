package org.example.tasksmanagementsystem.dtos.tasks;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskResponse {
    public Long id;
    public String title;
    public String description;
    public String status;
    public Long ownerId;
}
