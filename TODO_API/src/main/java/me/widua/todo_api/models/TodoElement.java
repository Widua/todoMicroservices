package me.widua.todo_api.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class TodoElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    private String taskName ;
    private String taskDescription ;
    private LocalDateTime taskCreated ;
    private boolean isFinished ;

}
