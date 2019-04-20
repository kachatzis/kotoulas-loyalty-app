package com.google.zxing.client.result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public final class CalendarParsedResult extends ParsedResult {
    private static final Pattern DATE_TIME = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");
    private static final Pattern RFC2445_DURATION = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");
    private static final long[] RFC2445_DURATION_FIELD_UNITS = new long[]{604800000, 86400000, 3600000, 60000, 1000};
    private final String[] attendees;
    private final String description;
    private final Date end;
    private final boolean endAllDay;
    private final double latitude;
    private final String location;
    private final double longitude;
    private final String organizer;
    private final Date start;
    private final boolean startAllDay;
    private final String summary;

    public CalendarParsedResult(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr, String str7, double d, double d2) {
        super(ParsedResultType.CALENDAR);
        this.summary = str;
        try {
            r1.start = parseDate(str2);
            if (str3 == null) {
                Date date;
                long parseDurationMS = parseDurationMS(str4);
                if (parseDurationMS < 0) {
                    date = null;
                } else {
                    date = new Date(r1.start.getTime() + parseDurationMS);
                }
                r1.end = date;
            } else {
                try {
                    r1.end = parseDate(str3);
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e.toString());
                }
            }
            boolean z = false;
            r1.startAllDay = str2.length() == 8;
            if (str3 != null && str3.length() == 8) {
                z = true;
            }
            r1.endAllDay = z;
            r1.location = str5;
            r1.organizer = str6;
            r1.attendees = strArr;
            r1.description = str7;
            r1.latitude = d;
            r1.longitude = d2;
        } catch (ParseException e2) {
            throw new IllegalArgumentException(e2.toString());
        }
    }

    public String getSummary() {
        return this.summary;
    }

    public Date getStart() {
        return this.start;
    }

    public boolean isStartAllDay() {
        return this.startAllDay;
    }

    public Date getEnd() {
        return this.end;
    }

    public boolean isEndAllDay() {
        return this.endAllDay;
    }

    public String getLocation() {
        return this.location;
    }

    public String getOrganizer() {
        return this.organizer;
    }

    public String[] getAttendees() {
        return this.attendees;
    }

    public String getDescription() {
        return this.description;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getDisplayResult() {
        StringBuilder stringBuilder = new StringBuilder(100);
        ParsedResult.maybeAppend(this.summary, stringBuilder);
        ParsedResult.maybeAppend(format(this.startAllDay, this.start), stringBuilder);
        ParsedResult.maybeAppend(format(this.endAllDay, this.end), stringBuilder);
        ParsedResult.maybeAppend(this.location, stringBuilder);
        ParsedResult.maybeAppend(this.organizer, stringBuilder);
        ParsedResult.maybeAppend(this.attendees, stringBuilder);
        ParsedResult.maybeAppend(this.description, stringBuilder);
        return stringBuilder.toString();
    }

    private static Date parseDate(String str) throws ParseException {
        if (!DATE_TIME.matcher(str).matches()) {
            throw new ParseException(str, 0);
        } else if (str.length() == 8) {
            return buildDateFormat().parse(str);
        } else {
            if (str.length() == 16 && str.charAt(15) == 'Z') {
                str = buildDateTimeFormat().parse(str.substring(0, 15));
                Calendar gregorianCalendar = new GregorianCalendar();
                long time = str.getTime() + ((long) gregorianCalendar.get(15));
                gregorianCalendar.setTime(new Date(time));
                str = new Date(time + ((long) gregorianCalendar.get(16)));
            } else {
                str = buildDateTimeFormat().parse(str);
            }
            return str;
        }
    }

    private static String format(boolean z, Date date) {
        if (date == null) {
            return false;
        }
        if (z) {
            z = DateFormat.getDateInstance(2);
        } else {
            z = DateFormat.getDateTimeInstance(2, 2);
        }
        return z.format(date);
    }

    private static long parseDurationMS(CharSequence charSequence) {
        if (charSequence == null) {
            return -1;
        }
        charSequence = RFC2445_DURATION.matcher(charSequence);
        if (!charSequence.matches()) {
            return -1;
        }
        long j = 0;
        int i = 0;
        while (i < RFC2445_DURATION_FIELD_UNITS.length) {
            int i2 = i + 1;
            String group = charSequence.group(i2);
            if (group != null) {
                j += RFC2445_DURATION_FIELD_UNITS[i] * ((long) Integer.parseInt(group));
            }
            i = i2;
        }
        return j;
    }

    private static DateFormat buildDateFormat() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    private static DateFormat buildDateTimeFormat() {
        return new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH);
    }
}
