package com.hoangnhm.ultimatealarm.data.model;

import java.util.ArrayList;
import java.util.List;

public class Alarm {

    public enum Days {
        MON(0),
        TUE(1),
        WED(2),
        THU(3),
        FRI(4),
        SAT(5),
        SUN(6);

        private final int dayCode;

        Days(int i) {
            this.dayCode = i;
        }

        public int getDayCode() {
            return dayCode;
        }



        public static Days setDay(int i) {
            switch (i) {
                case 0:
                    return MON;
                case 1:
                    return TUE;
                case 2:
                    return WED;
                case 3:
                    return THU;
                case 4:
                    return FRI;
                case 5:
                    return SAT;
                case 6:
                    return SUN;
                default:
                    return null;
            }
        }

        @Override
        public String toString() {
            return String.valueOf(dayCode);
        }
    }

    public static class Item {

        private String id;
        private String title;
        private String description;
        private String timePoint;
        private List<Days> daysList;
        private String ringtone;

        private Item(String id, String title, String description, String timePoint, List<Days> daysList, String ringtone) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.timePoint = timePoint;
            this.daysList = daysList;
            this.ringtone = ringtone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTimePoint() {
            return timePoint;
        }

        public void setTimePoint(String timePoint) {
            this.timePoint = timePoint;
        }

        public List<Days> getDaysList() {
            return daysList;
        }

        public String getDaysString() {
            if (null == daysList) {
                return null;
            } else {
                return daysList.toString().replaceAll("\\[\\]", "");
            }
        }

        public void setDaysList(List<Days> daysList) {
            this.daysList = daysList;
        }

        public String getRingtone() {
            return ringtone;
        }

        public void setRingtone(String ringtone) {
            this.ringtone = ringtone;
        }

        public static class Builder {

            private String id = "";
            private String title = "";
            private String description = "";
            private String timePoint = "";
            private List<Days> daysList = null;
            private String ringtone = "";

            public Builder setId(String id) {
                this.id = id;
                return this;
            }

            public Builder setTitle(String title) {
                this.title = title;
                return this;
            }

            public Builder setDescription(String description) {
                this.description = description;
                return this;
            }

            public Builder setTimePoint(String timePoint) {
                this.timePoint = timePoint;
                return this;
            }

            public Builder setDaysList(String daysList) {
                if (null == daysList) {
                    return this;
                }
                String[] arr = daysList.split(",");
                for (String anArr : arr) {
                    this.daysList.add(Days.setDay(Integer.parseInt(anArr)));
                }
                return this;
            }

            public Builder setRingtone(String ringtone) {
                this.ringtone = ringtone;
                return this;
            }

            public Item create() {
                return new Item(id, title, description, timePoint, daysList, ringtone);
            }
        }

    }
}
