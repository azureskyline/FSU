# DiningPhilosophers with Monitor Implementation

The dining philosopher’s problem
This concept is associated with the classic concurrency problem dealing with synchronization. In the problem there are five philosophers who are concerned with thinking, becoming hungry and eating. The problem is that although each philosopher has one chopstick, they all have to eat with a shared chopstick without risking starvation. And so the process would be for one philosopher to pick up the chopstick and then eat, then put down or return the chopstick to let other philosophers eat. This project was done in Java.

### The Monitor solution – server class
The use of the monitor is to observe all the states of the philosopher and make sure that every one of the philosophers (zenyattas) will be able to eat. 

For this the project uses the lock, reentrantlock and condition classes in java 8 to achieve making the monitor class (for the project’s purpose is called the server).

### Philosophers class
This class’s job is to constantly make the philosopher do three actions which is:
Think		hungry		eat 
At the beginning of the program the philosopher is set to think.

### Driver class
This class initializes the philosophers and the monitor-server. It constantly loops to allow infinite executions.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

You can create the jar file via:
1. Navigating to the folder where the .java is located.
2. C:\mywork> jar cvfe DiningPhilosophers.jar DiningPhilosophers *.class
3. Execute using java –jar DiningPhilosophers.jar

Please execute jar file using:
```
java –jar myShell.jar.
```

##An Example of the Output is:
```
Philosopher 4 is HUNGRY
Philosopher 4 is EATING
Philosopher 4 is THINKING

Philosopher 3 is HUNGRY
Philosopher 3 is EATING
Philosopher 3 is THINKING

Philosopher 0 is HUNGRY
Philosopher 0 is EATING
Philosopher 0 is THINKING

Philosopher 2 is HUNGRY
Philosopher 2 is EATING
Philosopher 2 is THINKING

Philosopher 1 is HUNGRY
Philosopher 1 is EATING
Philosopher 1 is THINKING

Philosopher 4 is HUNGRY
Philosopher 4 is EATING
Philosopher 4 is THINKING
```

*Note: All Philosophers must be able to eat. Starvation should not be an issue.

## Authors
* **S. Kennedy** - *Professor for COSC 460 Operating Systems; project concept*
* **Catherine Austria** - *Student ; codework*
