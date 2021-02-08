package com.divi.todolist.datamodel;

import java.security.Timestamp;
import java.time.LocalDate;

public class Todoitem {
    private String short_description;
    private String details;
    private LocalDate deadline;

    public Todoitem(String short_description, String details, LocalDate deadline) {
        this.short_description = short_description;
        this.details = details;
        this.deadline = deadline;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

//    @Override
//    public String toString() {
//        return short_description;
//    } // now there is no need as we are directly setting the shortDescription by setting CellFactory
}
