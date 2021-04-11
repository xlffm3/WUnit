# WUnit

WUnit is a simple re-coded JUnit inspired
by [JUnit A Cook's Tour](http://junit.sourceforge.net/doc/cookstour/cookstour.htm).

The purpose of recoding JUnit is to understand OOP. WUnit scans and gets all the test class objects located
under [test package](https://github.com/xlffm3/WUnit/tree/master/src/main/java/test) using reflection. WUnit then
invokes all the methods where @Test annotation is present and prints log according to each result.

I also recoded several chaining methods from AssertJ library to assert simple test cases.

* ``assertThatCode()``
* ``isInstanceOf()``
* ``hasMessage()``
* ``doesNotThrowAnyException()``

<br>

---
