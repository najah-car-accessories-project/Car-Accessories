Feature: Edit Profile

Scenario Outline: the Customer has successfully changed email
	Given the Customer is logged in 
	When the Customer select edit profile option 
	And the Customer wants to change email and enters valid <newEmail>
	And the Customer enters <password> for confirmation 
	Then the Customer's email will be updated 
	And "Profile updated successfully." message will appear 
	Examples: 
		|newEmail		  		|password|
		|"majd@outlook.com"  	|"majd0567"|
		
Scenario Outline: the Customer has successfully changed password
	Given the Customer is logged in 
	When the Customer select edit profile option 
	And the Customer enters <oldPassword>
	And the Customer enters valid <newPassword>
	Then the Customer's password will be updated 
	And "Profile updated successfully." message will appear 
	Examples: 
		|oldPassword	|newPassword|
		|"majd0567"  	|"majd05672024"|
		
Scenario Outline: the Customer has successfully changed contact number
	Given the Customer is logged in 
	When the Customer select edit profile option 
	And the Customer wants to change contact number and enters valid <newContactNumber>
	And the Customer enters <password> for confirmation 
	Then the Customer's contact number will be updated 
	And "Profile updated successfully." message will appear 
	Examples: 
		|newContactNumber	|password|
		|"0593967849"	  	|"majd0567"|