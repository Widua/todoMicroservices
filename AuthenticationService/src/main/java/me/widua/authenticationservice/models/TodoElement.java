package me.widua.authenticationservice.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "TodoElement")
public class TodoElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    private String taskName ;
    private String taskDescription ;
    private LocalDateTime taskCreated ;
    private boolean isFinished ;

}
