package com.example.tests.test;

import com.example.tests.model.ContestData;
import com.example.tests.model.ProjectData;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ContestPublicationTests  extends TestBase {

    @Test
    public void testContestPublication() throws InterruptedException {

        //Установка даты начала и окончания проведения опроса
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = new GregorianCalendar();
        String date_start = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.MONTH, +1); //Увеличение месяца на +1
        String date_end = dateFormat.format(calendar.getTime());

        app.getNavigationHelper().gotoContestListPage();
        app.getContestHelper().initContestCreation();


        app.getContestHelper().fillContestForm(new ContestData("Тестовый конкурс (для публикации)", "Муниципальный", "Новосибирская", "Новосибирск", date_start, date_end, "1", "1", "Благо"));
        app.getContestHelper().submitContestCreation();

        app.getNavigationHelper().gotoContestListPage();
        app.getProjectHelper().initProjectCreationPublish();
        app.getProjectHelper().fillProjectForm(new ProjectData("Проект 1", "Описание тестового проекта 1", "Благоустройство", date_start, date_end, "1000000"));
        app.getProjectHelper().submitProjectCreation();
        //app.getPollHelper().waitPollCreation();
        app.getNavigationHelper().gotoContestListPage();
        app.getProjectHelper().initProjectCreationPublish();
        app.getProjectHelper().fillProjectForm(new ProjectData("Проект 2", "Описание тестового проекта 2", "Благоустройство", date_start, date_end, "2000000"));
        app.getProjectHelper().submitProjectCreation();
        Thread.sleep(2000);

        app.getNavigationHelper().gotoContestListPage();
        app.getContestHelper().selectContest();
        app.getContestHelper().publishContest();

    }


}


