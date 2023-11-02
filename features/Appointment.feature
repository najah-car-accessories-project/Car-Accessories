Feature: Manage Appointment for Installation

Scenario Outline: the Customer has successfully added an appointment for installation
	Given the Customer is logged in 
	When the Customer select add appointment option 
	And the Customer chooses <Date> of the appointment in DD/MM/YYYY format 
	And the Customer chooses <Time> of the appointment 
	Then appointment will be added and recorded 
	And "Appointment added successfully." message will appear 
	Examples: 
		|Date	  		|Time|
		|"20/12/2023"  	|"9"| 
		
Scenario Outline: the Customer failed to add appointment for installation
	Given the Customer is logged in 
	When the Customer select add appointment option 
	And the Customer chooses <Date> of the appointment in DD/MM/YYYY format 
	And the Customer chooses <Time> of the appointment 
	But selected date and time are reserved by another Customer 
	Then appointment will not be added 
	And "Selected date and time are reserved by another Customer." message will appear 
	Examples: 
		|Date	  		|Time|
		|"14/06/2024"  	|"2"|		
		
Scenario Outline: the Customer has successfully changed his appointment for installation 
	Given the Customer is logged in 
	When the Customer select change appointment option 
	And the Customer selects the appointment he wants to change "1" 
	And the Customer chooses a new <Date> of the appointment in DD/MM/YYYY format 
	And the Customer chooses a new <Time> of the appointment 
	Then appointment will be edited and recorded 
	And "Appointment has been changed successfully." message will appear 
	Examples: 
		|Date	  		|Time|
		|"18/12/2023"  	|"3"|
		
Scenario Outline: the Customer failed to change his appointment for installation 
	Given the Customer is logged in 
	When the Customer select change appointment option 
	And the Customer selects the appointment he wants to change "1" 
	And the Customer chooses a new <Date> of the appointment in DD/MM/YYYY format 
	And the Customer chooses a new <Time> of the appointment 
	But selected date and time are reserved by another Customer 
	Then appointment will not be changed 
	And "Selected date and time are reserved by another Customer." message will appear 
	Examples: 
		|Date	  		|Time|
		|"14/06/2024"  	|"2"| 
		
Scenario: the Customer has successfully deleted his appointment 
	Given the Customer is logged in 
	When the Customer select delete appointment option 
	And the Customer selects the appointment he wants to delete "1" 
	Then appointment will be deleted 
	And "Appointment has been deleted successfully." message will appear 
	
	