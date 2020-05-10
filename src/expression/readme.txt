when create operator / new Expression 

1. make a class extend suitable expression 
2. update operator such that parser utils will know 
3. update Expression Factory because expression creation must through Expression Factory
4. update CNF class such that it support the operator 

when using getValue function, make sure you connect variable first 
Therefore, if you really need to create new expression in getValue(),
remember use refreshVariable() ; 