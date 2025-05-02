package com.texas.inventorymanagementsystem.repository;

import com.texas.inventorymanagementsystem.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
}
