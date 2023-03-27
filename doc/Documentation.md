Project Report Software Architecture
1.     Name of the System – Airline Ticket Reservation System
2.     Development Type- New Project Developed from the ground up.
3.     Number of Patterns Implemented-6
4. 	Names of the Patterns implemented.
1)	Flyweight
2)	Composite
2)	Singleton
4) 	Prototype
5) 	Iterator
6)	Observer
7) 	Bridge
5.     Name of the members and Contributions
•	Neel Prajapati
•	Rishabh Singh Rathore
•	Rahul Mittal
•	Manal
•	Oluwasola Adesola
•	Adwoa Serwa Owusu-Donkor

Everyone has worked and contributed equally to the project.
6.     Technologies used-
1)    Frontend- Java
2)    Database- mysql

Objective
The objective of our project is to implement design patterns into an airline reservation system and to demonstrate how by using design patterns we can improve the overall quality of the software.
We aim to achieve the following objectives from our project.
1. 	To implement 6 design patterns in our software
2.     To provide a practical approach to how design patterns can be implemented in a real word scenario.
3.   To demonstrate the benefits of using design patterns in software to decrease code coupling, increase the flexibility of the code and make it adaptable to future changes, modifications, or scalability and reduce the maintenance efforts required.
4.     To access if the above objectives have been achieved or not.

Introduction
The purpose of our project is to implement software design patterns into an airline reservation system and to demonstrate that by implementing these design patterns into our software we can make the software more extendable, decrease coupling within the code and increase the flexibility and modularity of our software.
The software design patterns that we have implemented are- Flyweight, Bride Composite, Prototype, Singleton, Iterator and Observer
1.    Flyweight1- Flyweight allows fitting more objects into the available amount of RAM by sharing common parts of the state.
      a.     Intent - lets you compose objects into tree structures and then work with these structures as if they were individual objects.
      b.     Applicability - Use when you have to implement a tree-like object structure or you want the client code to treat both simple and complex elements uniformly.

2.     Bridge11- The bridge pattern lets us split a large class or a set of closely related classes into two separate hierarchies “abstraction and implementation” which can be developed independently of each other.
a.     Intent - Convert the interface of a class into another interface clients expect. Also known as Wrapper.
b.     Applicability- Use the Adapter pattern when: - you want to use an existing class, and its interface does not match the one you need. - you want to create a reusable class that cooperates with unrelated or unforeseen classes, that is, classes that don't necessarily have compatible interfaces. - (object adapter only) you need to use several existing subclasses, but it’s impractical to adapt their interface by subclassing everyone. An object adapter can adapt the interface of its parent class.
3 Composite1- The composite patterns enable you to compose objects into tree structures and then work with these structures as if they were individual objects.
a.     Intent - lets you compose objects into tree structures and then work with these structures as if they were individual objects.
b.     Applicability - Use when you have to implement a tree-like object structure or you want the client code to treat both simple and complex elements uniformly.
4.     Singleton11– Singleton pattern is used when you need to create new objects and ensure that we have only a single instance of a class throughout an entire application.
a.     Intent - ensure that a class has only one instance, while providing a global access point to this instance.
b.    Applicability - Use the Singleton pattern when: - a class in your program should have just a single instance available to all clients. - When you want stricter control over global variables.
5.     Prototype1– The prototype pattern is a creational design pattern in software development. It is used when the type of objects to create is determined by a prototypical instance, which is cloned to produce new objects.
a.     Intent - Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype.
b.    Applicability - Use the Prototype pattern when: - your code shouldn’t depend on the concrete classes of objects that you need to copy. - you want to reduce the number of subclasses that only differ in the way they initialize their respective objects. The Prototype pattern lets you use a set of prebuilt objects, configured in various ways, as prototypes.
6.     Iterator Pattern1– This Pattern is used to traverse elements of a collection without exposing its underlying representation (list, stack, tree, etc.).
a.     Intent - Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
b.   Applicability -Use the Iterator pattern: - to access an aggregate object's contents without exposing its internal representation. - to support multiple traversals of aggregate objects. - to provide a uniform interface for traversing different aggregate structures (that is, to support polymorphic iteration).
7.     Observer Pattern1– This Pattern defines a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.
a.     Intent - Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
b.   Applicability -Apply the Observer pattern when: -When a change to one object requires changing others, and you don't know how many objects need to be changed. -When an object should be able to notify other objects without making assumptions about who these objects are. In other words, you don’t want these objects tightly coupled.

Details of how patterns are implemented
1. Problem Statement: Singleton Design Pattern
   While working on the system we encountered a problem that multiple flights, airports and database connection objects were being created multiple times. This was not an efficient method and we wanted to have a collection of objects that were instantiated and were to be passed in the program depending upon need. Therefore we had to design a solution in which the objects are created only once and passed on whenever they are required.
   Solution Implemented
   We used the Singelton Design pattern to solve our problem. The Singleton patterns allow us to create objects once and to be passed in the program as per the need. We used the Singleton Pattern to create a database connection object. We also created a collection of fights and airports using a singleton pattern so that they are instantiated once and moved in the program as per need.
   Implementation
   For flight, we created FlightCollection.java.
   For Airport, we created AirportDataCollection..java
   For Airlines, we created AirlineCollection.java
   We used Singleton Pattern to create these collections and saved the objects of flights, airports and airlines in these collections. So that whenever an object from one of these collections is required the collection will first check if the object is present or not. If present it will return the object, if not then it will create a new object and then return it to the class which requested that object.  Likewise, the DB connection object is also created once and the connection object is saved in the collection. When a connection is requested by a class then the collection is searched for an existing connection object. If there is no connection object present then we will establish a DB connection and send the connection object to the requested class.
2. Problem Statement: //Prototype Pattern
   While working on the implementation of the system we encountered a problem, each time we had to create a new flight we had to create a new object of flight from the start. The entire flight structure is the same for all flights except for identifying features. This method was not effective.
   Solution Implemented
   To solve the above problem we made use of the Prototype pattern. Whenever a new flight is created this will clone the existing flight with different identifiers.
   Implementation
   We created a class for creating flights named Flights.java. In this class, we create the flights and made the class implement clonable interface present in the java internal library. Which works as a Prototype implementation. This way whenever a new flight is needed the clone method is called and it creates a clone of the flight with different identifiers.
3. Problem Statement: //Iterator Pattern
   As we had mentioned above that we have used the collections of objects created through Singleton Patterns for Airports, flights and airlines therefore we had to have a functionality to iterate through the objects in some way. One way is that in the client code, we could fetch the objects using for or while loop but this breaks the principle of coding to an interface rather than an implementation which makes it tightly coupled and also exposes our code and collection type to the client. Therefore we had to find a way to iterate through the objects with minimum couples and without exposing our collection type to the client.
   Solution Implemented
   For the above reasons, we made use of the Iterator Pattern. By using the iterator pattern we can solve the above-mentioned problems and i.e making the class and collection decoupled and also hiding our implementation logic from the client.

   Implementation
   For the implementation of this, we created an interface AirlineIterator.java and a concrete implementation class AirlineCollnIterator.java. In a similar way, we created an iterator interface and concrete class that implemented that interface for Airports, Flights. Whenever the client requests an objects all he knows is, if there is a next object or not. The client is in no way aware of what is the type of collection and how objects are being fetched.
4. Problem Statement:  //Composite Pattern
   While we were developing the system we realised the airport and terminal are sort of the same thing serving the same functionality. The airport had an extended functionality of terminals. Therefore, we wanted to have a structure in which the airport and terminal can be treated uniformly, so the client would not have to worry about having different interactions with the airport and terminals.
   If we had to implement a hierarchy of objects where objects have references to their parents i.e. a terminal would have a reference to an airport and if there’s another level such as gates this would have to be implemented as well. This means we would have to create several hierarchies of classes and have complex conditional statements which would make the code complicated and tightly coupled.
   We wanted to be able to dynamically add terminals and gates to airports without having to change the code that individually handles airports, terminals and gates. Therefore we had to design a solution that was extendable so that in future if a terminal has several gates, then all we need to do is to add another hierarchy to the existing one.
   Solution Implemented using Design Pattern
   Therefore to solve the above problems we thought of implementing the  Composite pattern that would solve our purpose.
   Implementation
   We created an abstract class Component.java which both terminals and airports extend. The Airport.java class has a list that contains child component(Terminals). This way terminals can be added to airport object or removed and the client can relate with both terminals and airports the same way. Also, if in the future terminals have several gates then gates are easily added to terminal objects.
5. Problem Statement: //Flyweight Pattern
   When we were creating flight data each time the flight the information common to the flights was being repeated each time. Therefore, we wanted a solution in which all the flight data is saved in a collection and is not created each time a flight is scheduled
   Solution Implemented using Design Pattern
   For this reason, we implemented the flyweight pattern which made us fit more objects on the Flyweight Pattern
   Implementation
   We have created a flight data class that saves the data of flights per date in memory. The common data has been decoupled and stored in flight objects. The reference to common data is being stored in all the new objects that are being created. Thereby saving memory.

6. Problem Statement: //Bridge pattern.
   While booking a flight to somewhere we often get the option to buy some add-ons. But all the add-ons are not available in all the classes. Only certain add-ons are available in certain classes. For this reason, w encountered the problem that either we make a services class and couple it tightly to the Category of Travel Class. This solution made our system tightly coupled and whenever a service was to be added to a category it could lead to making a lot of changes in the entire code. Therefore we had to design a system that should make the system loosely coupled and also be open to the addition of new services or add-ons.
   Solution Implemented using Design Pattern
   For the above reasons, we implemented the Bridge pattern. By using the Bridge pattern, we could make the code less coupled and easy to extend. Any new services can be added at any time with minimal changes to the code.
   Implementation
   All the seat classes inherit an object of additional services and options that they inherit from base constructor that they inherit from their superclass seat_class. This forms a bridge between the seat class objects and additional services and options thereby reducing the number of classes that we have to have in the hierarchy.
7. Problem Statement: //Observer pattern.
   When we were designing our software we realised that each time a user had to search incase a new flight is added or not. Also there was no way to capture that would user be interested in the new flight details. For this purpose, we wanted to design a solution in which the if a user is interested in a flight path based on his flight search history he would be able to get a notification of the same


Solution Implemented using Design Pattern
For the above reasons, we implemented the Observer Pattern which would be listening to the flight additions and user search history and in case user has searched for a particular flight he would be notified of the same.
Implementation
Addition and modification of Flight is done through dbinsert which is the notifier class having methods subscribe and unsubscribe for any additions of subscribers in the future. The observer interface is at the other end of the pattern which has the update method that is implemented by all the subscribers.

Conclusion
In conclusion, the Airline Ticket Reservation System project provided us with a great opportunity to apply our knowledge of design patterns and Java programming. we learned valuable lessons about the importance of selecting the right design patterns for the project requirements, testing and debugging code, and maintaining flexibility and scalability. We were able to develop a less coupled, more extensible, and easy-to-maintain system by adopting the Singleton pattern for database connections, the Iterator pattern for managing collections of flight objects, the composite pattern to create airports and terminals considering the hierarchy they have, prototype for cloning flights instead of creating them from scratch.
Overall, the Airline Ticket Reservation System project provided us with an excellent opportunity to apply our knowledge of design patterns and Java programming. Through this project, we strengthened our skills  and gained valuable insights to improve our future projects.
Thanks & Regards
Project Team-4
