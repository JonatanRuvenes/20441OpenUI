package maman12;

/**
 * The Apartment class represents a residential apartment with information
 * such as the number of rooms, area, price, tenant details, and rental dates.
 * It provides methods for creating, accessing, and modifying apartment objects.
 *
 * @author Yehonatan Eliezer Reuvenes
 * @version 12/11/2023
 */
public class Apartment {

    // Constants
    final static int DEFAULT_NO_OF_ROOMS = 3;
    final static double DEFAULT_AREA = 80;
    final static double DEFAULT_PRICE = 5000;
    
    public static final int MAX_RENTAL_DAYS_DIFFERENCE = 90;
    final static int RENTAL_PERIOD_HAS_ENDED = -1;

    // Instance variables
    private int _noOfRooms;
    private double _area;
    private double _price;
    private Person _tenant;
    private Date _rentalStartDate;
    private Date _rentalEndDate;

    /**
     * Constructs an Apartment object with specified parameters.
     * If parameters are invalid, sets default values.
     *
     * @param noOfRooms     The number of rooms in the apartment.
     * @param area          The area of the apartment in square meters.
     * @param price         The rental price of the apartment in NIS.
     * @param tenant        The person who is renting the apartment.
     * @param startDay      The day the apartment is rented.
     * @param startMonth    The month the apartment is rented (1-12).
     * @param startYear     The year the apartment is rented.
     * @param endDay        The day the rental period ends.
     * @param endMonth      The month the rental period ends (1-12).
     * @param endYear       The year the rental period ends.
     */
    public Apartment(int noOfRooms, double area, double price, Person tenant, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
        if (noOfRooms <= 0 )
            this._noOfRooms = DEFAULT_NO_OF_ROOMS;
        else
            this._noOfRooms = noOfRooms;

        if(area <= 0)
            this._area = DEFAULT_AREA;
        else
            this._area = area;

        if(price <= 0)
            this._price = DEFAULT_PRICE;
        else
            this._price = price;

        this._tenant = new Person(tenant);

        this._rentalStartDate = new Date(startDay, startMonth, startYear);
        this._rentalEndDate = new Date(endDay, endMonth, endYear);

        if(!_rentalEndDate.after(_rentalStartDate))
            _rentalEndDate = _rentalStartDate.addYearsToDate(1);
    }

    /**
     * Constructs an Apartment object that is a copy of the specified Apartment.
     *
     * @param other The Apartment object to copy.
     */
    public Apartment(Apartment other){
        this._noOfRooms = other._noOfRooms;
        this._area = other._area;
        this._price = other._price;
        this._tenant = new Person (other._tenant);
        this._rentalStartDate = new Date(other._rentalStartDate);
        this._rentalEndDate = new Date(other._rentalEndDate);
    }
    
    /**
     * Gets the number of rooms in the apartment.
     *
     * @return The number of rooms.
     */
    public int getNoOfRooms() {
        return _noOfRooms;
    }

    /**
     * Gets the area of the apartment.
     *
     * @return The area in square meters.
     */
    public double getArea() {
        return _area;
    }

    /**
     * Gets the rental price of the apartment.
     *
     * @return The rental price in NIS.
     */
    public double getPrice() {
        return _price;
    }

    /**
     * Gets the tenant of the apartment.
     *
     * @return The tenant as a Person object.
     */
    public Person getTenant() {
        return new Person(_tenant);
    }

    /**
     * Gets the start date of the rental period.
     *
     * @return The start date as a Date object.
     */
    public Date getRentalStartDate() {
        return new Date(_rentalStartDate);
    }

    /**
     * Gets the end date of the rental period.
     *
     * @return The end date as a Date object.
     */
    public Date getRentalEndDate() {
        return new Date(_rentalEndDate);
    }

    /**
     * Sets the number of rooms in the apartment if the provided value is greater than 0.
     *
     * @param noOfRooms The new number of rooms to set.
     */
    public void setNoOfRooms(int noOfRooms) {
        if (noOfRooms > 0)
            this._noOfRooms = noOfRooms;
    }

    /**
     * Sets the area of the apartment if the provided value is greater than 0.
     *
     * @param area The new area to set.
     */
    public void setArea(double area) {
        if(area > 0)
            this._area = area;
    }

    /**
     * Sets the rental price of the apartment if the provided value is greater than 0.
     *
     * @param price The new rental price to set.
     */
    public void setPrice(double price) {
        if(price > 0)
            this._price = price;
    }

    /**
     * Sets the tenant of the apartment.
     *
     * @param p The new tenant as a Person object.
     */
    public void setTenant(Person p) {
        this._tenant = new Person(p);
    }

    /**
     * Sets the start date of the rental period if the provided date is before the current end date.
     *
     * @param d The new start date as a Date object.
     */
    public void setRentalStartDate(Date d) {
        if(d.before(_rentalEndDate))
            this._rentalStartDate = new Date(d);
    }

    /**
     * Sets the end date of the rental period if the provided date is after the current start date.
     *
     * @param d The new end date as a Date object.
     */
    public void setRentalEndDate(Date d) {
        if(d.after(this._rentalStartDate))
            this._rentalEndDate = new Date(d);
    }

    /**
     * Returns a string representation of the apartment.
     *
     * @return A formatted string containing apartment details.
     */
    public String toString() {
        return "Number of rooms: " + _noOfRooms +
                "\nArea: " + _area +
                "\nPrice: " + _price + " NIS" +
                "\nTenant name: " + _tenant.getName() +
                "\nRental start date: " + _rentalStartDate.toString() +
                "\nRental end date: " + _rentalEndDate.toString() + "\n";
    }

    /**
     * Checks if the specified apartment is equal to this apartment.
     *
     * @param other The apartment to compare.
     * @return True if the apartments are equal, false otherwise.
     */
    public boolean equals(Apartment other){
        return _noOfRooms == other._noOfRooms &&
                _area == other._area &&
                _price == other._price &&
                _tenant.equals(other._tenant) &&
                _rentalStartDate.equals(other._rentalStartDate) &&
                _rentalEndDate.equals(other._rentalEndDate);
    }

    /**
     * Extends the rental period of the apartment by the specified number of years.
     *
     * @param years The number of years to extend the rental period.
     */
    public void extendRentalPeriod(int years){
        if (years > 0)
            _rentalEndDate = _rentalEndDate.addYearsToDate(years);
    }

    /**
     * Calculates the number of days left in the apartment's rental period.
     *
     * @param d The current date as a Date object.
     * @return The number of days left in the rental period or {@code RentalPeriodHasEnded} if the rental period has ended.
     */
    public int daysLeft(Date d){
        if (d.after(_rentalEndDate))
            return RENTAL_PERIOD_HAS_ENDED;
        return d.difference(_rentalEndDate);
    }

    /**
     * Changes the tenant of the apartment and adjusts the rental details if valid.
     *
     * @param startDate The new start date of the rental period.
     * @param p         The new tenant as a Person object.
     * @param price     The new rental price in NIS.
     * @return True if the change is successful, false otherwise.
     */
    public boolean changeTenant(Date startDate, Person p, double price){
        if(p.compareTo(_tenant) == Person.BIRTHDAY_IS_AFTER_OTHER && price >= _price && (startDate.after(_rentalEndDate) || startDate.difference(_rentalEndDate) <= MAX_RENTAL_DAYS_DIFFERENCE)){
            _tenant = p;
            _price = price;
            _rentalStartDate = new Date(startDate);
            _rentalEndDate = startDate.addYearsToDate(1);
            return true;
        }
        return false;
    }
}
