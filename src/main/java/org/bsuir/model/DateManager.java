package org.bsuir.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateManager {
    private Date date;

    public DateManager(Date date) {
        this.date = date;
    }

    public DateManager(String date) throws ParseException {
        DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
        this.date = (Date) dateLabelFormatter.stringToValue(date);
    }

    @Override
    public String toString() {
        if (date == null)
            return "";

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return new DateLabelFormatter().valueToString(calendar);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
