package com.example.mycms.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String message;

    @Column
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;


    public boolean isRead() {
        return status.equals(NotificationStatus.READ);
    }
}
