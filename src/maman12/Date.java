package maman12;

/**
 * The Date class represents a date in the Gregorian calendar.
 * It provides methods for manipulating and comparing dates.
 *
 * @author Yehonatan Eliezer Reuvenes
 * @version 12/11/2023
 */

public class Date
{
    // Constants
    final static int DAYS_IN_LONG_MONTHS = 31;
    final static int DAYS_IN_SHORT_MONTHS = 30;
    final static int FEBRUARY_DAYS_IN_NORMAL_YEAR = 28;
    final static int FEBRUARY_DAYS_IN_LEAP_YEAR = 29;
    final static int FEBRUARY = 2;

    final static int DEFAULT_DAY = 1;
    final static int DEFAULT_MONTH = 1;
    final static int DEFAULT_YEAR = 2000;

    // Instance variables
    private int _day;
    private int _month;
    private int _year;

    /**
     * Constructs a Date object with the specified day, month, and year.
     * If the given date is invalid, sets the default date (01/01/2000).
     *
     * @param day   The day of the month.
     * @param month The month (1-12).
     * @param year  The year (4 digits).
     */
    public Date(int day, int month, int year){
        if(! isLegal(day,month,year)){
            // the given date is problematic
            // set default date
            this._day = DEFAULT_DAY;
            this._month = DEFAULT_MONTH;
            this._year = DEFAULT_YEAR;
        }
        else {  // the given date is normal
            this._day = day;
            this._month = month;
            this._year = year;
        }
    }
    
    /**
     * Constructs a Date object that is a copy of the specified Date.
     *
     * @param other The Date object to copy.
     */
    public Date (Date other){
        this._day = other.getDay();
        this._month = other.getMonth();
        this._year = other.getYear();
    }

    /**
     * Gets the day of the month.
     *
     * @return The day of the month.
     */
    public int getDay() {
        return _day;
    }

    /**
     * Sets the day of the month if the new day is legal.
     *
     * @param dayToSet The new day to set.
     */
    public void setDay(int dayToSet) {
        if (isLegal(dayToSet, _month,_year))
            this._day = dayToSet;
    }

    /**
     * Gets the month.
     *
     * @return The month (1-12).
     */
    public int getMonth() {
        return _month;
    }

    /**
     * Sets the month if the new month is legal.
     *
     * @param monthToSet The new month to set.
     */
    public void  setMonth(int monthToSet) {
        if (isLegal(_day, monthToSet,_year))
            this._month = monthToSet;
    }

    /**
     * Gets the year.
     *
     * @return The year.
     */
    public int getYear() {
        return _year;
    }

    /**
     * Sets the year if the new year is legal.
     *
     * @param yearToSet The new year to set.
     */
    public void setYear(int yearToSet) {
        if (isLegal(_day, _month,yearToSet))
            this._year = yearToSet;
    }

    /**
     * Checks if the given Date is equal to this Date.
     *
     * @param other The Date to compare.
     * @return True if the Dates are equal, false otherwise.
     */
    public boolean equals (Date other){
        return _year == other._year && _month == other._month && _day == other._day;
    }

    /**
     * Checks if this Date is before the specified Date.
     *
     * @param other The Date to compare.
     * @return True if this Date is before the specified Date, false otherwise.
     */
    public boolean before (Date other){
        if(_year < other._year) // this year is before "other" year
            return true;
        if(_year == other._year){
            if(_month < other._month) // the two years are equals but this month is before "other" month
                return true;
            if (_month == other._month) { // the two years and month are equals
                return _day < other._day; // in this case it returns if this day is before "other" day
            }
            return false; // the two years are equals but "other" month is before this month
        }
        return false; // the "other" year is before this year
    }

    /**
     * Checks if this Date is after the specified Date.
     *
     * @param other The Date to compare.
     * @return True if this Date is after the specified Date, false otherwise.
     */
    public boolean after (Date other){
        return other.before(this); //if other is before this so this is after other
    }

    /**
     * Calculates the absolute difference in days between this Date and the specified Date.
     *
     * @param other The Date to calculate the difference with.
     * @return The absolute difference in days.
     */
    public int difference (Date other){
        return Math.abs(calculateDate(_day,_month,_year) - other.calculateDate(other._day, other._month, other._year));
    }

    /**
     * Returns a string representation of this Date in the format "DD/MM/YYYY".
     *
     * @return The formatted string.
     */
    public String toString(){
        String strDay, strMonth, strYear;
        if(_day < 10)
            strDay = "0" + _day;
        else
            strDay = "" + _day;
        if(_month < 10)
            strMonth = "0" + _month;
        else
            strMonth = "" + _month;
        strYear = "" + _year;

        return strDay + "/" + strMonth + "/" + strYear;
    }

    /**
     * Adds the specified number of years to this Date and returns a new Date object.
     * Adjusts the date if necessary for leap years.
     *
     * @param num The number of years to add.
     * @return The new Date object after adding the years.
     */
    public Date addYearsToDate(int num){
        Date addedYearsDate;
        
        if (_month == FEBRUARY && _day == FEBRUARY_DAYS_IN_LEAP_YEAR && !isLeapYear(_year + num)) // this is leap year with the date 29.2 and the target year is not a leap year
            addedYearsDate = new Date(FEBRUARY_DAYS_IN_NORMAL_YEAR, _month, _year +num);
        else if(_month == FEBRUARY && !isLeapYear(_year) && isLeapYear(_year + num) && _day == FEBRUARY_DAYS_IN_NORMAL_YEAR) // this is not a leap year and the date is 28.2 and the target year is a leap year
            addedYearsDate = new Date(FEBRUARY_DAYS_IN_LEAP_YEAR , _month, _year +num);
        else // there is no problem with the leap year
            addedYearsDate = new Date(_day, _month, _year+num);
            
        return addedYearsDate;
    }

    
    // Private methods

        
    /**
     * Computes the day number since the beginning of the Christian counting of years.
     *
     * @param day   The day of the month.
     * @param month The month (1-12).
     * @param year  The year (4 digits).
     * @return The day number since the beginning of the Christian counting of years.
     */    
    private int calculateDate ( int day, int month, int year){
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    }

    /**
     * Checks if the specified year is a leap year.
     *
     * @param y The year to check.
     * @return True if the year is a leap year, false otherwise.
     */
    private boolean isLeapYear (int y){
        return (y%4==0 && y%100!=0) || (y%400==0) ? true : false;
    }

    /**
     * Checks if the given day, month, and year form a legal date.
     *
     * @param day   The day of the month.
     * @param month The month (1-12).
     * @param year  The year (4 digits).
     * @return True if the date is legal, false otherwise.
     */
    private boolean isLegal(int day, int month, int year){

        if(year < 1000 || year > 9999)// the year is out of range (have 4 digits)
            return false;
            
        if (day < 1) // the day is lower then the minimum value
            return false;
            
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if(day > DAYS_IN_LONG_MONTHS)
                    return false;
                break;
                
            case 4:
            case 6:
            case 9:
            case 11:
                if(day > DAYS_IN_SHORT_MONTHS)
                    return false;
                break;
                
            case 2:
                if((isLeapYear(year) && day > FEBRUARY_DAYS_IN_LEAP_YEAR) ||
                    (!isLeapYear(year) && day > FEBRUARY_DAYS_IN_NORMAL_YEAR))
                    return false;
                break;
                    
            default: // the month is out of range (between 1 and 12)
                return false;
        }
        
        return true;
        
    }
}
