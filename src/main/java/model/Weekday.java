package model;

public enum Weekday {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int value;

    Weekday(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public static boolean isWeekday(Weekday day) {
        return day != SATURDAY && day != SUNDAY;
    }
}