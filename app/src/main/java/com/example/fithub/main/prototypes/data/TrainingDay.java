package com.example.fithub.main.prototypes.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "training_day",
        foreignKeys= {
        @ForeignKey(
                entity = TrainingPlan.class,
                parentColumns = {"trainingPlanId"},
                childColumns = {"trainingPlanId"})
})
public class TrainingDay {

    @PrimaryKey
    private int trainingDayId;

    private int trainingPlanId;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTrainingDayId(int trainingDayId) {
        this.trainingDayId = trainingDayId;
    }

    public int getTrainingPlanId() {
        return trainingPlanId;
    }


    public void setTrainingPlanId(int trainingPlanId) {
        this.trainingPlanId = trainingPlanId;
    }

    public int getTrainingDayId() {
        return trainingDayId;
    }

    public TrainingDay(int trainingDayId, Date date, int trainingPlanId) {
        this.trainingDayId = trainingDayId;
        this.date = date;
        this.trainingPlanId = trainingPlanId;
    }

}
