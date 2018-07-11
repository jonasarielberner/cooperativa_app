package com.cooperativa.model.datasource.database;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class DateWrapper {
    private
    SimpleDateFormat simpleDateFormat;

    @Inject
    public DateWrapper(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
        //constructor constructor
    }

    public String getCurrentDateFormatted() {
        return simpleDateFormat.format(new Date());
    }

    public String format(Date date) {
        return simpleDateFormat.format(date);
    }
}