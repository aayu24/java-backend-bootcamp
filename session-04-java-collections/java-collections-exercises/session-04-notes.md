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

Above implementation has a problem. What if one of the ages is close to the max/min value supported by int.
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
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
```

To use above way to sort 
```
PersonComparator personComparator = new PersonComparator();
Collections.sort(list,personComparator);
```

## Iterator Pattern
Allows a way to iterate over something without knowing the 
underlying implementation of that thing.
Note that the class over which we want to use the Iterator pattern, must implement the
```Iterable``` interface.

### Fail-fast Iterator
Iterators which are not tolerant to underlying changes. The default iterator for Collections is one such iterator.
For example, what if you change the collection while you are iterating.
Uses an internal modification counter. After every iteration, it checks
whether this modCounter has changed its value. If so, then it fails throwing
a ```ConcurrentModificationException```.

NOTE:

The javadoc states that fail-fast iterators throw <i>ConcurrentModificationException</i> on a **best-effort** basis.
This means that it will try its best to throw an exception in case of any modifications, but it is not guaranteed that
every modification will result in an exception being thrown. Hence, you can't just rely on this exception to verify if
modification has occurred.

Q. What if you want the iterator to work with modifications?

A. Easy, just make the iterator do the modifications instead. Since, it knows where it is in the collection.
eg:- ```iterator.remove()```