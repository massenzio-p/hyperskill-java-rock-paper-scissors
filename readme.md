# Hyperskills Academy project: Rock-Paper-Scissors

## Stage 1/5: Unfair computer
Description
Rock paper scissors is a popular hand game. Two players simultaneously form one of three shapes with their hands, and then, depending on the shapes, one player wins — rock beats scissors, paper wins over rock, scissors defeats paper.
The game is well-known for fair win-conditions between equal options.

Let's start with an unfair version! :)

Write a program that reads input that states which option users have chosen. After this, your program must make users lose! That is, your program should always select an option that defeats the one picked by users. Once it finds this option, output the line Sorry, but the computer chose <option>, where <option> is the name of the option that the program's picked depending on the user's input.
For example, if users enter rock, the program should print Sorry, but the computer chose paper and so on.

Pay attention to the format of the output. It should follow the one in the example, including capital letters and punctuation. Do not print additional strings.

Just think about it: in this stage, the computer has to win every time.

if users choose paper, the computer chooses scissors (the computer wins);

if users choose scissors, the computer chooses rock (the computer wins);

if users choose rock, the computer chooses paper (the computer wins).

Objectives
Your program should:

Take input from the user;

Find an option that wins over the user's option;

Output the line: Sorry, but the computer chose <option>.

Examples
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Example 1:

> scissors
Sorry, but the computer chose rock
Example 2:

> paper
Sorry, but the computer chose scissors