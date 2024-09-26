package io.javabrains.javacollections;

/*
Generate a Person class with the member variables firstName, lastName, age, lastModifiedDate.
Generate equals and hashcode methods using member variables firstName, lastName, age
Compare two instances of Person that have the same data
*/

import java.util.Date;
import java.util.Objects;

public class EqualsAndHashCode {

    public static void main(String[] args) {
        Person p1 = new Person("Aayush","Patni",25,new Date());
        Person p2 = new Person("Aayush","Patni",25,new Date());
        Person p3 = new Person("Aayush","Patni",24,new Date());
        // below print true
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode() == p2.hashCode());
        // below print false
        System.out.println(p1.equals(p3));
        System.out.println(p1.hashCode() == p3.hashCode());
    }
}

class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Date lastModifiedDate;

    public Person(String firstName, String lastName, int age, Date lastModifiedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        // check reference equality
        if (this == o) {
            return true;
        }
        // check nullity and class equality
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        // check object equality
        Person other = (Person) o;
        return this.age == other.age && Objects.equals(this.firstName, other.firstName) && Objects.equals(this.lastName, other.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }
}
