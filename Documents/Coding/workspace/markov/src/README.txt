CompSci 201 README for Markov Assignment, Fall 2014

Name: Katrina Zhu
Net ID: kz37

Date of submission: 9/25/2014

Number of hours worked (leave just one of the choices below)

  between 5 and 10

With whom did you collaborate with on this assignment and what
information did you exchange?
I talked with Shelley Vohr about the assignment on the bus. We discussed the nature of the WordNgram class.

What other resources did you use?
I used the recitation powerpoints and the map API's.

What are your thoughts about this assignment?
I thought it was a good way to learn about the nuances of maps and inherited classes.

Timings:

1. How do the timings for the brute force method change if you vary
  A. Order of Model 
	As order increases, time seems to decrease. 
  B. Increasing the length of the text increases the length of time by that factor. 
  For example, increasing the length from 150,000 to 500,000 characters increases the time
  of a run with 200 letters, order 10 from 0.048 to 0.19.
  C. Doubling the number of characters roughly doubles the time it takes to run.
  For example, Alice in Wonderland's order 10 with 200 letters takes 0.048 secs, 
  but Alice in Wonderland's order 10 with 400 letters takes 0.088 secs.
---
2. Do the empirical results match what you would expect? How long do you think
it will take to generate 1600 random characters using an order-5 Markov
model using the brute force algorithm when the Complete Works of William
Shakespeare are used as the training text --- our online copy of this text
contains roughly 5.5 million characters. Justify your answer -- don't test
empirically, use reasoning.

Hawthorne's text is 500,000 characters; when you run it with 1600 characters and order 5, and it takes 1.53.
Shakespeare's text is 11 times as long as Hawthorne's text; therefore, it should take 1.53x11 seconds, or 16.83 seconds.

---
3. Provide timings using your Map/Smart model for both creating the map and
generating 200, 400, 800, and 1600 character random texts with an
order-5 Model and alice.txt.  Provide some explanation for the timings
you observe.

200 map 0.164 secs, then 0.001 secs
400 map 0.156 secs, then 0.002 secs
800 map 0.151 secs, then 0.002 secs
1600 map 0.168 secs, then 0.003 secs

This makes sense. The time it takes to make the maps is substantially longer than the time it takes to create the text, by the nature of maps.
The time to create maps do not vary that much as character count varies, because resulting character count does not affect map creation time.
The time to create the texts increases marginally as the amount of characters needed increases, which makes sense.

---
4. Provide timings for the WordMarkovModel with different hashcode methods. 
Time the method you are given and compare the results that you achieve
with the better hashcode method that you developed.
With the hashcode method given, where everything was given a hashcode of 15, it took 0.017 seconds to compose the map
and print the text using the txt file Obama with 1600 characters and an order of 5.  
With the better hashcode method, it took 0.016 seconds.
The shorter time makes sense; had we used a longer text than Obama, the difference would probably be more apparent. 


---
