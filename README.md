# AirlineReservationSystem_testing
Monash FIT5171 Assignment 1&2 Test Plan and Unit/Integration Testing on Airline Reservation System
## Project Description
A flight booking system is a system that automates the flight booking process to help book flights online. 
It is essential to have a reliable and efficient booking system for an airline company. At the same time, it is critical to ensure that the booking system provides comprehensive information so that customer experience can be improved. One of the key aspects for the business is to secure reliable and efficient customer service. However, it is difficult to deliver a quality software solution without testing playing a major role in it.
In this assignment, you will need to thoroughly test the essential functionality of the flight reservation system. In general, you will test and extend components (Java classes and methods) of the code baseusing the TDD approach
## Test Suite Development
You will apply a combination of appropriate unit and integration testing techniques to thoroughly test the extended code and present your test suite as a part of your test plan report in a tabular format. You can create separate tables for presenting unit and integration test cases per class.Validation: At some stage, certain attributes of some classes need to be validated in different ways. The following are a minimal set of validation that should be part of your test suite. You should always generate errors or throw exceptions where appropriate.

Airplane 

When some attribute of the airplane is being set, the following unit testing conditions need to be satisfied.
1. Ensure all fields/details for an airplane like airplaneID, businessSitsNumber, crewSitsNumber, etc. are tested.
2. Seat number must be in the range [1, 300].
   
Flight

When a flight is being added to the system, following conditions must be met.
1. All fields are required.
2. Date must be in DD/MM/YY format.
3. Time must be in HH:MM:SS format.
4. Ensure the same flight is not already in the system.
   
Person
      
When a person is being created or returned, test following:
1. All fields of a Person class are required to create a person.
2. The gender field has following options ‘Woman’, ‘Man’, ’Non-binary | gender diverse’, ‘Prefer not to say’ and ‘Other
3. The first name and last name should not start with a number or symbol and can contain only lower-case and upper-case alphabet letters.
   
Passenger

When a passenger is being created or returned, test following:
1. All fields of a passenger are required. 
2. Phone numbers follow a pattern. Within Australia, mobile phone numbers begin with 04 or 05 – the 
Australian national trunk code" 0, plus the mobile indicator 4 or 5, then followed by eight digits. This is generally written as 04XX XXX XXX within Australia or as +61 4XX XXX XXX for an international audience.
3. The email follows a valid pattern “abc@domain.com”.
4. The passport number should not be more than 9 characters long.
5. When a passenger is being added, it must include the passenger’s first name, last name, age, and gender following the person who is becoming a passenger
   
Ticket

When a ticket is being created, it needs to satisfy the following conditions.
1. Values for the ticket status must be ‘True’ or ‘False’ for the booked and available tickets respectively.
2. Discount is always applied based on the age category of the passenger.
3. Price is always applied to a ticket.
4. The price and service tax are valid values (integer or real numbers etc.)
5. The service tax is always applied when a ticket is sold.
6. Ticker class receives valid information of flight and passenger.

TicketSystem 

1. When choosing a ticket, a valid city is used.
2. If a passenger chooses an already booked ticket it should display an error message.
3. Appropriate checks have been implemented to validate passenger information
4. Appropriate checks have been implemented to validate flight information
5. Appropriate checks have been implemented to validate ticket information
6. A correct value is displayed to the passenger when buying a ticket.

TicketCollection

1. Whenever a ticket is being added to the TicketCollection, it must be validated.
2. When trying to get a ticket, the correct ticket is returned.
FlightCollection
1. When adding a flight into the system, test if it conforms with the requirement as a flight and Flight Collection.
2. Valid city names must be used.
3. When trying to get flight information, a valid flight is returned.

## Testing and System Improvement
Unit testing. each unit in the system against the test suite. write test classes/methods for each unit, perform automated testing, and improve the unit to pass the test suite.
Integration testing.test the system under integration testing using the bottom-up/top-down integration approach or a combination of them where appropriate.

# FIT5171 Project Assignment 2 Software Quality Monitoring & Improvement

## 1. Project description

With functionality developed and tested in Assignment 1 for the flight reservation system, we will focus on measuring and assessing the quality of the code base and the test suite in this Assignment 2.
Besides the environments and libraries used in the previous assignment, we will use one more library, PIT(http://pitest.org/), for mutation testing. Moreover, we will use the ISO/IEC 25010 International Standard - Systems and Software Engineering - Systems and Software Quality Requirements and Evaluation (SQuaRE) - System and Software Quality Models (https://iso25000.com/index.php/en/iso-25000-standards/iso-25010. The PDF version of 
ISO/IEC 25010 is available on Moodle.) to analyse the quality of your code base.

## 2. Problem description

You will make use of your improved code base from Assignment 1 and will measure, analyse, comment on and improve the quality of the code base. Additionally, you will perform automated mutation testing on the code base.2.1 Code Quality Analysis & Improvement & Critique 
In this part, you will use ISO/IEC 25010 to measure, monitor and analyse the quality of your code base from Assignment 1. Specifically, you will need to complete the following tasks.
1. Successfully build your improved/extended code base to make sure all the test cases pass without errors or assertion failures.
2. Observe the quality of the code based on the ISO/IEC 25010 Software Quality Model. 
Analyse the code base in terms of the following aspects:
● Functional Completeness
● Functional Correctness
● Functional Appropriateness
● Time Behaviour
● Resource Utilisation
● Capacity
● User Error Protection
● Accessibility
● Fault Tolerance
3. Informed by the above analysis, improve the quality of the code base.
4. For each of the above aspects, you will need to describe the following in your report.
● Discuss the reasonableness of the quality metrics employed by ISO/IEC 25010 Software Quality Model.
● Identify parts of the code (or test) that require significant improvements.

### 2.2 Mutation Testing via PIT

Mutation testing is a technique to assess the quality of the test suite, by making small syntactic changes to the actual code and observing the behavioural changes of the test suite in response to these changes.
You will perform automated mutation testing. Specifically, you will need to complete the following tasks.
1. Use the PIT library (http://pitest.org/) to perform automated mutation testing on the code base,
2. Observe the issues identified by PIT (mutation testing), and discuss the quality of the test suite, and
3. Improve the quality of the test suite based on the observations you make.
Please refer to the PIT website (http://pitest.org/quickstart/) for more details on the setup and configuration of PIT. For example, you can configure which part (classes or packages) of the code base can be mutated and which tests can be executed. You can also configure which mutation operators can be applied.
