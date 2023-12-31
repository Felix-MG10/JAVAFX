package com.example.ii.tools;

import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notification {

    public static void NotifSucces(String titre, String message){
        NotificationType type = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titre);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(5));
    }

    public static void NotifError(String titre, String message){
        NotificationType type = NotificationType.ERROR;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titre);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(5));
    }

    public static void NotifWarning(String titre, String message){
        NotificationType type = NotificationType.WARNING;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(titre);
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(5));
    }
}
