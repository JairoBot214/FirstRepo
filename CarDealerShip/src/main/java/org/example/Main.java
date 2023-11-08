package org.example;

//Overall, nice job! You're going to need save functionality eventually
//And there was a comment I put about the UserInterface's constructor
//but overall this is good work and you should be proud.
public class Main {
    public static void main(String[] args) {
        Dealership dealership = DealershipFileManager.getDealership();
        //Small thing, but what's to stop me from putting a dealership
        //without any vehicles into the user interface?
                UserInterface userInterface = new UserInterface(dealership);

                userInterface.display();
            }
        }