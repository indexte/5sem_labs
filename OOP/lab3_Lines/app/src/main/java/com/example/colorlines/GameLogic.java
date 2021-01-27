package com.example.colorlines;

import android.util.Log;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class GameLogic {
     int score;

    TableLayout tableLayout;
    boolean gameOver=false;
    int colorIndex;
    private  HashSet<Button> availableCells= new HashSet<>(); //this HashSet contains all the available Cells
    int numberOfBallsSuccession=1;
    HashSet <Button> buttonsToExplosion =new HashSet();
    boolean alredycheckReverse=false;

    int [] threeRandomColors;

    public boolean checkSuccessionAllDirections(Button button)
    {
        boolean doExplosion = false;
        for(int direction=0; direction<8; direction++) {
            if(checkSuccessionByDirection(button, direction));// direction ->0=left, 1=left-up, 2=up, 3=up-right, 4=right 5=right-down 6=down 7=down-left ;
            {
                doExplosion = true;
            }
        }
        return doExplosion;
    }

    public boolean checkSuccessionByDirection(Button button, int direction) {
        boolean doExplosion = false;
        int currentLocation = GameManager.idAllCells.get(button);
        Button nearButton= getKeyByValue(GameManager.idAllCells, currentLocation-1);

        Button [] buttonsDirection= {getKeyByValue(GameManager.idAllCells, currentLocation-1), getKeyByValue(GameManager.idAllCells, currentLocation-10),
                getKeyByValue(GameManager.idAllCells, currentLocation-9), getKeyByValue(GameManager.idAllCells, currentLocation-8),
                getKeyByValue(GameManager.idAllCells, currentLocation +1),getKeyByValue(GameManager.idAllCells, currentLocation +10),
                getKeyByValue(GameManager.idAllCells,currentLocation +9), getKeyByValue(GameManager.idAllCells, currentLocation +8)};
        //second way
        for(int i=0; i<buttonsDirection.length; i++)
        {
            if(direction==i)
            {
                nearButton=buttonsDirection[i];
            }
        }
        buttonsToExplosion.add(button);

        checkIfNearButtonIsSameColor(button,nearButton,direction);

        if(numberOfBallsSuccession>1 && alredycheckReverse==false) {
            if (!alredycheckReverse) {
                numberOfBallsSuccession = 1;
            }


            //first way
            if (direction == 0) {//checks also direction 4 {
                alredycheckReverse = true;
                checkIfNearButtonIsSameColor(button, getKeyByValue(GameManager.idAllCells, currentLocation + 1), 4);

            } else if (direction == 1) //checks also direction 5
            {
                alredycheckReverse = true;
                checkIfNearButtonIsSameColor(button, getKeyByValue(GameManager.idAllCells, currentLocation + 10), 5);
            } else if (direction == 2) //up. checks also down(direction 6)
            {
                alredycheckReverse = true;
                checkIfNearButtonIsSameColor(button, getKeyByValue(GameManager.idAllCells, currentLocation + 9), 6);
            }
            else if(direction==3 )
            {
                alredycheckReverse = true;
                checkIfNearButtonIsSameColor(button, getKeyByValue(GameManager.idAllCells, currentLocation + 8), 7);
            }
            else if(direction==4)
            {
                alredycheckReverse = true;
                checkIfNearButtonIsSameColor(button, getKeyByValue(GameManager.idAllCells, currentLocation - 1), 0);
            }
            else if(direction==5)
            {
                alredycheckReverse = true;
                checkIfNearButtonIsSameColor(button, getKeyByValue(GameManager.idAllCells, currentLocation - 10), 1);
            }
            else if(direction==6)
            {
                alredycheckReverse = true;
                checkIfNearButtonIsSameColor(button, getKeyByValue(GameManager.idAllCells, currentLocation - 9), 2);
            }
            else if(direction==7)
            {
                alredycheckReverse = true;
                checkIfNearButtonIsSameColor(button, getKeyByValue(GameManager.idAllCells, currentLocation - 8), 3);
            }
        }
        if(numberOfBallsSuccession>4) //if there is more then 5 balls in the same color in a row
        {
            doExplosion=true;
            numberOfBallsSuccession=1;
            doExplosion(buttonsToExplosion);
            buttonsToExplosion.clear();
            alredycheckReverse=false;
            return doExplosion;
        }
        else
        {
            numberOfBallsSuccession=1;
            doExplosion=false;
            buttonsToExplosion.clear();
            alredycheckReverse=false;
            return doExplosion;
        }
    }

    private void checkIfNearButtonIsSameColor(Button button,Button nearButton, int direction) {

        if(nearButton!=null && button!=null) {  //checks if the nearButton is null/not in the range of 0-80
            if (!getAvailableCells().contains(nearButton))
            {
                try {
                    if (button.getTag().equals(nearButton.getTag())) { //checks if the nearButton and button are the same color
                        numberOfBallsSuccession++; //add plus one to the succession int
                        checkSuccessionByDirection(nearButton, direction); //doing a recursion, in order to check if there one more ball in the same color and direction
                        buttonsToExplosion.add(nearButton); //add the nearbutton to the explosion array
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doExplosion(HashSet<Button> buttonToExplosion)
    {
        int numberOfBalls=0;
        for(Button button: buttonToExplosion) //geting each button from the Hashset and then remove it
        {
            numberOfBalls++;
            removeButton(button);
        }
        score=score+numberOfBalls; //adding a score
        clearAllAnimation();//reset all the animation
    }

    public int getScore() {
        return score;
    }


    public void clearAllAnimation() {
        for (int i = 0; i < 81; i++) {
            //this for loop select each cell/button from the AllButtons list which keeps all the 81 cells.
            getKeyByValue(GameManager.idAllCells,i).clearAnimation();
            //this line clear every button from animation.
        }
    }



    public boolean isGameOver() {
        return gameOver;
    }
    public HashSet<Button> getAvailableCells() {
        return availableCells;
    }
    public void createThreeballs(TableLayout tablelayout) {
        this.tableLayout=tablelayout;
        //creates 3 balls
        for(int i=0; i<3; i++)
        {
            colorIndex=i;
            createBall();
        }
    }

    public void createBall() {
        if (!(availableCells.size() == 0))
        //checks if the availableCells is empty. if yes- > no more room for balls, thus game over.
        {

            Random random = new Random();
            int randomLocation = random.nextInt(GameManager.idAllCells.size()); //DataManager.idAllCells.size()=81. randomLocationcan be (0-80) idnumber

            if (getAvailableCells().contains(getKeyByValue(GameManager.idAllCells, randomLocation)))
            //checks if getAvailableCells contains the random button selected. if yes- > the random location is an empty cell
            // if no-> the random location is NOT a empty cell,
            // thus we need a new random location. we do that by Recursion
            {
                Log.i("getAvailableCells", "inside getAvailableCells");
                //the program will go into side block if, the random location is empty.

                Button button = getKeyByValue(GameManager.idAllCells, randomLocation);//gets the button key from the HashSet idAllCells by the value (randomLocation)



                for(int i=0; i<3; i++)
                {
                    if(i==colorIndex){
                        button.setBackgroundResource(this.threeRandomColors[i]);
                        button.setTag(this.threeRandomColors[i]);
                    }
                }


                getAvailableCells().remove(button); //removes the button from the AvailableCells
                checkSuccessionAllDirections(button);

                if(availableCells.size()==0)
                {
                    gameOver = true;
                }
            } else
            {
                createBall();//need a different location
            }
        } else {
            gameOver = true;
        }
    }

    public void setColorToThreeBalls(int [] threeRandomColors)
    {
        this.threeRandomColors=threeRandomColors;
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }

        }
        return null;
    }
    public void removeButton(Button Button) {
        Button.setTag(null);
        Button.setBackgroundResource(R.drawable.cell);//set the old cell to be a cell drawable. will look like empty to the plyers
        getAvailableCells().add(Button);
    }

}