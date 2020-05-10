# BeliefEngine
a simple belief engine with string parser

```
-----Menu----------------------
1. Feed new belief
2. check for logical entailment
3. contraction of belief base
4. print belief base
5. Exit
-------------------------------
1
Feed me belief : 
A <-> B
order (between 1 to 10 ) :  

-----Menu----------------------
1. Feed new belief
2. check for logical entailment
3. contraction of belief base
4. print belief base
5. Exit
-------------------------------
1
Feed me belief : 
!B
order (between 1 to 10 ) :  
5
-----Menu----------------------
1. Feed new belief
2. check for logical entailment
3. contraction of belief base
4. print belief base
5. Exit
-------------------------------
1
Feed me belief : 
B
order (between 1 to 10 ) :  
6
inconsistent belief, solve conflict...
Belief{!B, order = 5} is removed 
-----Menu----------------------
1. Feed new belief
2. check for logical entailment
3. contraction of belief base
4. print belief base
5. Exit
-------------------------------
1
Feed me belief : 
! (A <-> B)
order (between 1 to 10 ) :  
5
inconsistent belief, solve conflict...
Belief{! (A <-> B), order = 5} is removed 
-----Menu----------------------
1. Feed new belief
2. check for logical entailment
3. contraction of belief base
4. print belief base
5. Exit
-------------------------------
4
------------print belief base-----------------
Belief{A <-> B, order = 10}
Belief{B, order = 6}

-----Menu----------------------
1. Feed new belief
2. check for logical entailment
3. contraction of belief base
4. print belief base
5. Exit
-------------------------------
2
Feed me belief : 
A
check logical entailment...(((A <-> B) && B) -> A)
A	B	Expression
true	true	true	
true	false	true	
false	true	true	
false	false	true	

-----Menu----------------------
1. Feed new belief
2. check for logical entailment
3. contraction of belief base
4. print belief base
5. Exit
-------------------------------

```
