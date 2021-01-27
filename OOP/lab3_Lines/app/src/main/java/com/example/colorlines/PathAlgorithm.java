package com.example.colorlines;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathAlgorithm {
    boolean[] isChecked = new boolean[81]; //array shows cells is checked or unchecked. true - checked , false - unchecked
    int startBtnIndex;
    int finalBtnIndex;
    private GameLogic gameLogic;

    public PathAlgorithm(int startBtnIndex, int finalBtnIndex, GameLogic gameLogic) {
        this.startBtnIndex = startBtnIndex;
        this.finalBtnIndex = finalBtnIndex;
        this.gameLogic = gameLogic;
        Arrays.fill(isChecked, false);
    }

    public boolean checkPath() {
        Button startButton = gameLogic.getKeyByValue(GameManager.idAllCells, startBtnIndex);
        List<GameButton> pathList = getEmptyNeighbourCells((GameButton) startButton);
        while(true){
            for (GameButton game:pathList) {
                if (game.getBtnId() == finalBtnIndex)
                    return true;
            }
            // we haven't reached the destination yet
            ArrayList<GameButton> tempList = new ArrayList<>();
            for (GameButton gb:pathList) {
                tempList.addAll(getEmptyNeighbourCells(gb));

            }
            if (tempList.size() == 0)
                return false;
            pathList=tempList;
        }
    }

    private List<GameButton> getEmptyNeighbourCells(GameButton originBtn) {
        ArrayList<GameButton> results = new ArrayList<>();
        if (checkNeighbourValidity(originBtn.getRightNeighbor())) {
            results.add(originBtn.getRightNeighbor());
        }
        if (checkNeighbourValidity(originBtn.getLeftNeighbor())) {
            results.add(originBtn.getLeftNeighbor());
        }
         if (checkNeighbourValidity(originBtn.getTopNeighbor())) {
            results.add(originBtn.getTopNeighbor());

        }
         if (checkNeighbourValidity(originBtn.getBottomNeighbor())) {
            results.add(originBtn.getBottomNeighbor());

        }
        for (GameButton iter:results)
            isChecked[iter.getBtnId()] = true;
        return results;


    }

    private boolean checkNeighbourValidity(GameButton button) {
        return button != null //checks if the button is null or not
                && !isChecked[button.getBtnId()] ////checks if we already checked the current cell
                && gameLogic.getAvailableCells().contains(button); //checks if the button is empty cell.
    }
}
