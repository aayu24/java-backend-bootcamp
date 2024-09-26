## Complexity and Comparison

***Comparable\<T>*** - "Natural" ordering. Object class has to implement this interface. This is default method used when doing ```Collections.sort(list)```

```java
public class Person implements Comparable<Person> {
    private String name;
    private int age;
    
    //getters and setters
    
    @Override
    public int compareTo(Person other) {
        //TODO
    }
}
```

**Implementation-1**
```java
public int compareTo(Person other) {
    return this.age - other.age;
}
```
3 cases

* \> 0 : Means this is greater
* ==0 : Means this is equal
* < 0 : Means other is greater

Above implementation has a problem. What is one of the ages is close to the max/min value supported by int.
In that case, above implementation can lead to **Overflow Errors**

**Implementation-2**

Better to use the wrapper class method
```java
public int compareTo(Person other) {
    return Integer.compare(this.age, person.age);
}
```

What if depending on the situation, we want to order Persons differently? Like when doing a roll call,
we want to order base on name. When doing PE, we want to order on height etc.
In above scenario you can use ***Comparator\<T>*** 

It provides a way to create an **external** separate class in order to compare two Objects. Thus, we can create different 
comparators for different situations and use them to sort the objects as necessary.

```java
import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    @Override
    public int compareTo(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
```

To use above way to sort 
```
PersonComparator personComparator = new PersonComparator();
Collections.sort(list,personComparator);
```