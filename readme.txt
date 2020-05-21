READ ME
***********

***********

To run the program:

1. Go to the Folder where files are present.
2. javac distancevector/routing/*.java
3. java distancevector.routing.Router
4. test.txt is provided type into command prompt or use other file

Observations:
Command line GUI implemented due to lack of time to implement animated GUI. Lack of time due to personal matters reason why it is turned in late. My apologies.
I assumed max numbers as they were stated in the pdf file. I also assumed the file would always follow the same format.
The algorithm runs 3 times, running 1 millisecond each time until it reaches a stable state. The stability is determined by whether or not the table has to update by calling on links or looking at neighbors. By tables sharing information to each other and being linked it makes the process run much smoother and quickly. It is quite fascinating to see the algorithm actually in code as opposed to just using it like done in Operation Research IE 3315.

References:
https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
https://www.ida.liu.se/~TDTS06/labs/2013/DV/#Fig3
https://codeburst.io/distance-vector-routing-protocol-with-socket-programming-feaf17571fb1
https://www.geeksforgeeks.org/distance-vector-routing-dvr-protocol/
https://www.javatpoint.com/distance-vector-routing-algorithm
