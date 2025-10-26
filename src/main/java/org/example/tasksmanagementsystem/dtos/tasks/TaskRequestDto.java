package org.example.tasksmanagementsystem.dtos.tasks;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskRequestDto {
    public String title;
    public String description;
}
