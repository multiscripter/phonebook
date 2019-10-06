package phonebook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Class "Entry" realize entity "Entry".
 *
 * @author Multiscripter
 * @version 2019-10-02
 * @since 2018-09-19
 */
@Entity
@Table(name = "phone_book")
@XmlRootElement
public class Entry {
    /**
     * Entry identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Phone number.
     */
    @Column(name = "number")
    private int number;

    /**
     * Last name.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * First name.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Middle name.
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Address.
     */
    @Column(name = "address")
    private String address;

    /**
     * Constructor without parameters.
     */
    public Entry() {
    }

    /**
     * Constructor.
     * @param id identifier.
     * @param number phone number.
     * @param lastName last name.
     * @param firstName first name.
     * @param middleName middle name.
     * @param address address.
     */
    public Entry(Long id, int number, String lastName, String firstName, String middleName, String address) {
        this.id = id;
        this.number = number;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
    }

    /**
     * Gets the address.
     * @return address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Gets the identifier.
     * @return identifier.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Gets the first name.
     * @return first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Gets the last name.
     * @return last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Gets the middle name.
     * @return middle name.
     */
    public String getMiddleName() {
        return this.middleName;
    }

    /**
     * Gets the phone number.
     * @return phone number.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Sets the address.
     * @param address address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the first name.
     * @param firstName first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the identifier.
     * @param id identifier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the last name.
     * @param lastName last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the middle name.
     * @param middleName middle name.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Sets the phone number.
     * @param number phone number.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Checks objects for equality.
     * @param o target object.
     * @return true if objects are equal. Or false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Entry))
            return false;
        Entry entry = (Entry) o;
        return this.number == entry.getNumber() &&
                Objects.equals(this.id, entry.getId()) &&
                Objects.equals(this.lastName, entry.getLastName()) &&
                Objects.equals(this.firstName, entry.getFirstName()) &&
                Objects.equals(this.middleName, entry.getMiddleName()) &&
                Objects.equals(this.address, entry.getAddress());
    }

    /**
     * Gets the hash of the object.
     * @return the hash of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.number, this.lastName, this.firstName, this.middleName, this.address);
    }

    /**
     * Gets a string representation of an object.
     * @return a string representation of an object.
     */
    @Override
    public String toString() {
        return String.format("Entry{id: %d, number: %d, lastName: %s, firstName: %s, middleName: %s, address: %s}", this.id, this.number, this.lastName, this.firstName, this.middleName, this.address);
    }
}
