public class Person {
    private Name fullName;
    private Address address;

    public Person(Name fullName, Address address) {
        this.fullName = fullName;
        this.address = address;
    }

    public Name getFullName() {
        return fullName;
    }

    public void setFullName(Name fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // check if A direct to Other.
    public boolean isDirectConnection(Person other) {
        return this.getFullName().equals(other.getFullName())
                || this.getAddress().equals(other.getAddress());
    }

}

