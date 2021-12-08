package com.example.mycms.repository;

import com.example.mycms.entity.Notification;
import com.example.mycms.entity.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByStatus(NotificationStatus notificationStatus);


    List<Notification> findAllByStatusNot(NotificationStatus notificationStatus);
}
