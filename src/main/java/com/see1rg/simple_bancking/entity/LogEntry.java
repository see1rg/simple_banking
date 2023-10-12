package com.see1rg.simple_bancking.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "log_entry")
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date timestamp;
    @Column(name = "log_level")
    private String logLevel;
    private String message;

    public LogEntry() {
    }

    public LogEntry(Date timestamp, String logLevel, String message) {
        this.timestamp = timestamp;
        this.logLevel = logLevel;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
