package maman12;

/**
 * The Person class represents an individual with a name, ID, and date of birth.
 * It provides methods for creating, accessing, and comparing person objects.
 *
 * @author Yehonatan Eliezer Reuvenes
 * @version 12/11/2023
 */
public class Person {

    // Constants
    final static String DEFAULT_NAME = "Someone";
    final static String DEFAULT_ID = "Someone";
    final static int ID_LENGTH = 9;
    
    final static int BIRTHDAY_IS_BEFORE_OTHER = 1;
    final static int BIRTHDAY_IS_AFTER_OTHER = -1;
    final static int BIRTHDAYS_ARE_THE_SAME = 0;

    // Instance variables
    private String _name;
    private String _id;
    private Date _dateOfBirth;

    
    /**
     * Constructs a Person object with the specified name, date of birth, and ID.
     * If the name is an empty string, sets the default name.
     * If the ID does not have the required length, sets the default ID.
     *
     * @param name The name of the person.
     * @param day  The day of birth.
     * @param month The month of birth (1-12).
     * @param year The year of birth.
     * @param id The ID of the person.
     */
    public Person(String name, int day, int month, int year, String id) {

        if (name == "")
            this._name = new String(DEFAULT_NAME);
        else
            this._name = new String(name);

        this._dateOfBirth = new Date(day, month, year);

        if (id.length() != ID_LENGTH)
            this._id = new String(DEFAULT_ID);
        else
            this._id = new String(id);
    }

    /**
     * Constructs a Person object that is a copy of the specified Person.
     *
     * @param other The Person object to copy.
     */
    public Person(Person other){
        this._name = new String(other._name);
        this._dateOfBirth = new Date(other._dateOfBirth);
        this._id = new String(other._id);
    }

    /**
     * Gets the name of the person.
     *
     * @return The name of the person.
     */
    public String getName(){
        return new String(_name);
    }
    
    /**
     * Gets the ID of the person.
     *
     * @return The ID of the person.
     */
    public String getId(){
        return new String(_id);
    }
    
    /**
     * Gets the date of birth of the person.
     *
     * @return The date of birth as a Date object.
     */
    public Date getDateOfBirth(){
        return new Date(_dateOfBirth);
    }
    
    /**
     * Sets the name of the person if the new name is not an empty string.
     *
     * @param name The new name to set.
     */
    public void setName (String name){
        if (name != "")
            this._name = new String(name);
    }
    
    /**
     * Sets the ID of the person if the new ID has the required length.
     *
     * @param id The new ID to set.
     */
    public void setId (String id){
        if(id.length() == ID_LENGTH)
            this._id = new String(id);
    }
    
    /**
     * Sets the date of birth of the person.
     *
     * @param d The new date of birth as a Date object.
     */
    public void setDateOfBirth (Date d){
        this._dateOfBirth = new Date(d);
    }

    /**
     * Returns a string representation of the person.
     *
     * @return A formatted string containing the person's name, ID, and date of birth.
     */
    public String toString() {
        return "Name: " + _name + "\nID: " + _id + "\nDate of birth: " +_dateOfBirth;
    }

    /**
     * Checks if the specified person is equal to this person.
     *
     * @param other The person to compare.
     * @return True if the persons are equal, false otherwise.
     */
    public boolean equals(Person other){
        return _id.equals(other._id) && _name.equals(other._name) && _dateOfBirth.equals(other._dateOfBirth);
    }
    
    /**
     * Compares this person's date of birth with another person's date of birth.
     *
     * @param other The person to compare.
     * @return An integer indicating the comparison result.
     *         Returns {@code BIRTHDAY_IS_BEFORE_OTHER} if this person's birthday is before the other person's birthday,
     *         {@code BIRTHDAY_IS_AFTER_OTHER} if this person's birthday is after the other person's birthday,
     *         or {@code BIRTHDAYS_ARE_THE_SAME} if both birthdays are the same.
     */
    public int compareTo(Person other){
        if (_dateOfBirth.before(other._dateOfBirth))
            return BIRTHDAY_IS_BEFORE_OTHER;
        if (_dateOfBirth.after(other._dateOfBirth))
            return BIRTHDAY_IS_AFTER_OTHER ;
        return BIRTHDAYS_ARE_THE_SAME;
    }
}
