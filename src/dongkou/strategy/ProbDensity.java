/**
 * @author Tobias
 */
package dongkou.strategy;

import dongkou.interfaces.TalosFireStrategy;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Ship;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class ProbDensity implements TalosFireStrategy {

    private boolean hunt;

    private int noOfEnemeyShips;
    private int enemyShipSizeSum;
    private int sizeX;
    private int sizeY;
    private int shotPosX;
    private int shotPosY;
    private String shotPos;
    private List<String> tempPos;
    private List<String> hitPos;
    private List<String> savedHitPos;
    private Map<String, Integer> shotMatrix;
    private Map<String, Integer> freqMatrix;

    public ProbDensity(int boardSizeX, int boardSizeY) {
        hunt = true;
        this.sizeX = boardSizeX;
        this.sizeY = boardSizeY;

        tempPos = new ArrayList();
        hitPos = new ArrayList();
        savedHitPos = new ArrayList();

        shotMatrix = new HashMap();
        freqMatrix = new HashMap();
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        noOfEnemeyShips = enemyShips.getNumberOfShips();
//        System.out.println("No of ships: " + noOfEnemeyShips);
        getEnemyShipSizeSum(enemyShips);

        while (getMaxValueInFreqMatrix() <= 0) {
            if (hunt) {
//                System.out.println("Hunting");
                hunt(enemyShips);
            } else {
//                System.out.println("Attacking");
                attack(enemyShips);

                if (getMaxValueInFreqMatrix() <= 0) {
                    if (hitPos.size() >= 2) {
                        //prob 0! 
                        //take last pos from hitPos and move to savedHitPos.
                        //also remove from hitPos
                        Collections.sort(hitPos);
                        int lastIndex = hitPos.size() - 1;
                        String tempPos = hitPos.get(lastIndex);
                        savedHitPos.add(tempPos);
//                        System.out.println("Adding " + tempPos + " to SavedHitPos and removing from hitPos");
                        hitPos.remove(lastIndex);
                    }
                }
            }
        }

        if (getMaxValueInFreqMatrix() > 0) {
            //fire at pos with max freq
            String pos = getMaxFreqMatrixKey();
            Scanner scan = new Scanner(pos).useDelimiter(",");
            shotPosX = parseInt(scan.next());
            shotPosY = parseInt(scan.next());
            shotPos = shotPosX + "," + shotPosY;
//            System.out.println("<<<<<<Shooting at: " + shotPos + " >>>>>>>>>>>");
            return new Position(shotPosX, shotPosY);

        }

        return new Position(0, 0);
    }

    private int getEnemyShipSizeSum(Fleet enemyShips) {
        enemyShipSizeSum = 0;
        try {
            for (int i = 0; i < enemyShips.getNumberOfShips(); i++) {
                enemyShipSizeSum = enemyShipSizeSum + enemyShips.getShip(i).size();
            }
        } catch (Exception ex) {
//            System.out.println("Something is wrong here");
        }
        return enemyShipSizeSum;
    }

    private void hunt(Fleet enemyShips) {
        /**
         * [--- HUNT MODE ---]. This is the mode used when searching for ships.
         * Run trough each ship and place it on the board in all possible
         * positions. At each possible position, add 1 to frequency. Shoot at
         * coordinate with highest frequency - most likely to be a ship here.
         */
        freqMatrix.clear();
        int shipCurrentCount = enemyShips.getNumberOfShips();
        Ship enemyShip;
        int shipLength; //ex. 5
        int maxVPos; //max vertical pos before outside board
        int maxHPos; //max horizontal pos before outside board;
        for (int i = 0; i < shipCurrentCount; i++) {

            enemyShip = enemyShips.getShip(i);
            shipLength = enemyShip.size();
            maxVPos = sizeY - shipLength;
            maxHPos = sizeX - shipLength;

            //place vertically
            for (int y = 0; y < sizeY; y++) {
                for (int x = 0; x < sizeX; x++) {
                    for (int L = 0; L < shipLength; L++) { //ex. [0,0],[0,1],[0,2],[0,3],[0,4]
                        //if inside board
                        if (y <= maxVPos) {
                            String posKey = x + "," + (y + L);
                            tempPos.add(posKey);
                        }
                    }
                    if (tempPos.size() > 0) {
                        addToFreqMatrix(tempPos);
                    }
                }
            }
            //place horizontally
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    for (int L = 0; L < shipLength; L++) {
                        if (x <= maxHPos) { //if inside board
                            String posKey = (x + L) + "," + y; //ex. [0,0],[1,0],[2,0],[3,0],[4,0]
                            tempPos.add(posKey);
                        }
                    }
                    if (tempPos.size() > 0) {
                        addToFreqMatrix(tempPos);
                    }
                }
            }
        }

    }

    private void attack(Fleet enemyShips) {
        /**
         * [--- ATTACK MODE ---]. There is a hit. Count the number of times the
         * enemy's ships can inhabit that position. The position with the
         * highest count is fired at. If another hit, we count the number of
         * times the enemy's ship can inhabit both positions. If miss we try
         * again. If ship sunk, we go back to search mode.
         */
        freqMatrix.clear();
        Ship enemyShip;
        int shipLength; //ex. 5
        int maxVPos; //max vertical pos before outside board
        int maxHPos; //max horizontal pos before outside board
        int shipCurrentCount = enemyShips.getNumberOfShips();
        String pos = hitPos.get(0);
//        System.out.println("Initial position is: " + pos);
        //        String pos = hitPos.get(hitPos.size() - 1);
        Scanner scan = new Scanner(pos).useDelimiter(",");
        int X = parseInt(scan.next());
        int Y = parseInt(scan.next());

        for (int i = 0; i < shipCurrentCount; i++) {

            enemyShip = enemyShips.getShip(i);
            shipLength = enemyShip.size();
            maxVPos = sizeY - shipLength;
            maxHPos = sizeX - shipLength;

            //place ship vertically
            for (int y = 0; y < sizeY; y++) {
                for (int L = 0; L < shipLength; L++) { //ex. [x,0],[x,1],[x,2],[x,3],[x,4]
                    if (y <= maxVPos) { //if inside board
                        int tempY = y + L;
                        String posKey = X + "," + tempY;
                        tempPos.add(posKey);
                    }
                }
                if (tempPos.size() > 0) {
                    addToFreqMatrix(tempPos);
                }
            }

            //place horizontally
            for (int x = 0; x < sizeX; x++) {
                for (int L = 0; L < shipLength; L++) { //ex. [0,y],[1,y],[2,y],[3,y],[4,y]
                    if (x <= maxHPos) { //if inside board
                        int tempX = x + L;
                        String posKey = tempX + "," + Y;
                        tempPos.add(posKey);
                    }
                }
                if (tempPos.size() > 0) {
                    addToFreqMatrix(tempPos);
                }
            }
            if (freqMatrix.size() < 2) { //TESTING PURPOSES ONLY!!!!!!!
//                System.out.println("FreqMatrix size is:" + freqMatrix.size());
//                System.out.println("At pos" + freqMatrix.keySet());
            }
        }

        //set all hitPos probs to 0
        for (String key : hitPos) {
            freqMatrix.put(key, 0);
        }
        for (String key : savedHitPos) {
            freqMatrix.put(key, 0);
        }
    }

    private void addToFreqMatrix(List<String> inputArray) {
        //if ship is not added because we have placed a shot there already
        boolean canAdd = false;
        if (inputArray != null) {
            canAdd = true;
            for (String key : inputArray) {
                if (shotMatrix.containsKey(key)) {
                    canAdd = false;
                }
            }
        }
        //in hunt-mode
        if (canAdd && hunt) {
            for (String key : inputArray) {
                int newWeight;
                if (freqMatrix.containsKey(key)) {
                    newWeight = freqMatrix.get(key) + 1;
                    freqMatrix.remove(key);
                    freqMatrix.put(key, newWeight);
                } else {
                    newWeight = 1;
                    freqMatrix.put(key, newWeight);
                }
            }
        }
        //in attack-mode
        if (canAdd && !hunt) {
            boolean insideHitPos = false;
            boolean insideSavedHitPos = false;
            List<String> tempList = new ArrayList();

            //checks against savedHitPos
            for (String position : inputArray) {
                if (savedHitPos.contains(position)) {
                    insideSavedHitPos = true;
                    break;
                }
            }

            //checks against hitPos
            if (!insideSavedHitPos) {
                for (String position : inputArray) {
                    if (hitPos.contains(position) /*|| savedHitPos.contains(position)*/) {
                        tempList.add(position);
                    }
                }
            }
            if (tempList.size() == hitPos.size()) {
                insideHitPos = true;
            }
            //-------------------------------------------------
            if (insideHitPos == true) {
                for (String key : inputArray) {
                    int newWeight;
                    if (freqMatrix.containsKey(key)) {
                        newWeight = freqMatrix.get(key) + 1;
                        freqMatrix.remove(key);
                        freqMatrix.put(key, newWeight);
                    } else {
                        newWeight = 1;
                        freqMatrix.put(key, newWeight);
                    }
                }
            }

        }
        tempPos.clear(); //flush the array
    }

    private int getMaxValueInFreqMatrix() {
        int maxValueInMap = 0;
        try {
            maxValueInMap = (Collections.max(freqMatrix.values()));  // This will return max value in the Hashmap
        } catch (Exception ex) {
//            System.out.println("Max value in map is: " + maxValueInMap);
        }
        return maxValueInMap;
    }

    private String getMaxFreqMatrixKey() {
        String pos = "0,0";
        List<String> tempMaxPos = new ArrayList();
        int maxValueInMap = getMaxValueInFreqMatrix();
        Random random = new Random();
        
        for (Entry<String, Integer> entry : freqMatrix.entrySet()) {  // Iterate through hashmap
            if (entry.getValue() == maxValueInMap) {
                tempMaxPos.add(entry.getKey());
            }
        }
        
        if (!hunt) {
            //in attack-mode: never pick a pos that is not right besides a hitPos!
            Scanner scan;
            List<String> tempMaxPosValid = new ArrayList();
            
            for (int i = 0; i < tempMaxPos.size(); i++) {
                String position = tempMaxPos.get(i);
                scan = new Scanner(position).useDelimiter(",");
                int X = parseInt(scan.next());
                int Y = parseInt(scan.next());
                String northPos = X + "," + (Y-1);
                String southPos = X + "," + (Y+1);
                String eastPos = (X+1) + "," + Y;
                String westPos = (X-1) + "," + Y;
                if (hitPos.contains(northPos) || hitPos.contains(southPos) || hitPos.contains(eastPos) || hitPos.contains(westPos)) {
                    tempMaxPosValid.add(position);
                }    
            }
            int randomIndex = random.nextInt(tempMaxPosValid.size());
            pos = tempMaxPosValid.get(randomIndex);
            

        } else {
            
            int randomIndex = random.nextInt(tempMaxPos.size());
            pos = tempMaxPos.get(randomIndex);
        }
        return pos;
    }

    @Override
    public void hitFeedback(boolean hit, Fleet enemyShips) {
        freqMatrix.clear();
        if (hit) {
//            System.out.println("We hit something!");
            hitPos.add(shotPos);
            //engage attack-mode
            hunt = false;

            if (noOfEnemeyShips > enemyShips.getNumberOfShips()) {
                //we sunk a ship
//                System.out.println("We sunk a ship!");
                //check which ship we sank
                int oldEnemyShipSizeSum = enemyShipSizeSum;
                int newEnemyShipSizeSum = getEnemyShipSizeSum(enemyShips);
                int sunkedShipSize = oldEnemyShipSizeSum - newEnemyShipSizeSum;
//                System.out.println("Sunked ship size is: " + sunkedShipSize);
                List<String> tempPosArray = new ArrayList();
                boolean foundShip = false;
                //find "lowest" pos in hitPos! <<<<<<<<<THIS IS PROBLEMATIC. 
                //INSTEAD WE NEED TO FIND THE LAST POSITION WE SHOT WHICH SANK THE SHIP!
                //Put the ship in the last position and go through 4 placement directions. 
                //the first direcion that has positions in either savedHit or hitPos 
                //correspondanding to the shipLength, then we have found ship!
                /*Collections.sort(hitPos);
                 System.out.println(hitPos.get(0));
                 String startPos = hitPos.get(0);
                 Scanner scan = new Scanner(startPos).useDelimiter(",");
                 String startX = scan.next();
                 String startY = scan.next();*/
                String startX = shotPosX + "";
                String startY = shotPosY + "";
//                System.out.println("StartX is: " + startX);
//                System.out.println("StartY is: " + startY);

                //place ship north
//                System.out.println("Placing ship north");
                for (int i = 0; i < sunkedShipSize; i++) {
                    int tempY = parseInt(startY) - i;
                    String posKey = startX + "," + tempY;
                    if (hitPos.contains(posKey) || savedHitPos.contains(posKey)) {
                        tempPosArray.add(posKey);
//                        System.out.println("Adding: " + posKey);
                    }
                }
                if (tempPosArray.size() == sunkedShipSize) {
                    foundShip = true;
//                    System.out.println("Found ship in north line!");
                }
                //place ship south
                if (!foundShip) {
                    tempPosArray.clear();
//                    System.out.println("Placing ship south");
                    for (int i = 0; i < sunkedShipSize; i++) {
                        int tempY = parseInt(startY) + i;
                        String posKey = startX + "," + tempY;
                        if (hitPos.contains(posKey) || savedHitPos.contains(posKey)) {
                            tempPosArray.add(posKey);
//                            System.out.println("Adding: " + posKey);
                        }
                    }
                    if (tempPosArray.size() == sunkedShipSize) {
                        foundShip = true;
//                        System.out.println("Found ship in south line!");
                    }
                }

                //place ship east
                if (!foundShip) {
                    tempPosArray.clear();
//                    System.out.println("Placing ship east");
                    for (int i = 0; i < sunkedShipSize; i++) {
                        int tempX = parseInt(startX) + i;
                        String posKey = tempX + "," + startY;
                        if (hitPos.contains(posKey) || savedHitPos.contains(posKey)) {
                            tempPosArray.add(posKey);
//                            System.out.println("Adding: " + posKey);
                        }
                    }
                    if (tempPosArray.size() == sunkedShipSize) {
                        foundShip = true;
//                        System.out.println("Found ship in east line!");
                    }
                }

                //place ship west
                if (!foundShip) {
                    tempPosArray.clear();
//                    System.out.println("Placing ship west");
                    for (int i = 0; i < sunkedShipSize; i++) {
                        int tempX = parseInt(startX) - i;
                        String posKey = tempX + "," + startY;
                        if (hitPos.contains(posKey) || savedHitPos.contains(posKey)) {
                            tempPosArray.add(posKey);
//                            System.out.println("Adding: " + posKey);
                        }
                    }
                    if (tempPosArray.size() == sunkedShipSize) {
                        foundShip = true;
//                        System.out.println("Found ship in west line!");
                    }
                }

                /*
                 //place ship vertically
                 for (int y = 0; y < sunkedShipSize; y++) {
                 int tempY = y + parseInt(startY);
                 String posKey = startX + "," + tempY;
                 if (hitPos.contains(posKey) || savedHitPos.contains(posKey)) {
                 tempPosArray.add(posKey);
                 }
                 }
                 */
                /*
                 if (!foundShip) {
                 tempPosArray.clear();
                 //place ship horizontally
                 for (int x = 0; x < sunkedShipSize; x++) {
                 int tempX = x + parseInt(startX);
                 String posKey = tempX + "," + startY;
                 if (hitPos.contains(posKey) || savedHitPos.contains(posKey)) {
                 tempPosArray.add(posKey);
                 }
                 }
                 if (tempPosArray.size() == sunkedShipSize) {
                 foundShip = true;
                 //found ship pos!
                 }
                 }
                 */
                if (foundShip) {
                    for (int i = 0; i < tempPosArray.size(); i++) {
                        String tempPos = tempPosArray.get(i);
                        if (hitPos.contains(tempPos)) {
                            hitPos.remove(tempPos);
//                            System.out.println("Removing " + tempPos + " from hitPos");
                            shotMatrix.put(tempPos, 0);
                        }
                        if (savedHitPos.contains(tempPos)) {
                            savedHitPos.remove(tempPos);
//                            System.out.println("Removing " + tempPos + " from savedHitPos");
                            shotMatrix.put(tempPos, 0);
                        }
                    }
//                    System.out.println("The size of savedHitPos is: " + savedHitPos.size());
                    for (int i = 0; i < savedHitPos.size(); i++) {
                        String tempPos = savedHitPos.get(i);
//                        System.out.println("Moving " + tempPos + " from savedHitPos --> hitPos");
                        hitPos.add(tempPos);
//                        savedHitPos.remove(i);
                    }
                    savedHitPos.clear();
                }
//                System.out.println("Size of hitPos is: " + hitPos.size());
            }

//            System.out.println("Size of hitPos is: " + hitPos.size());
//            System.out.println("Size of savedHitPos is: " + savedHitPos.size());
            //exit attack-mode!
            if (hitPos.isEmpty()) {
                hunt = true;
            }
        }

        if (!hit) {
            shotMatrix.put(shotPos, 0);
        }

    }

    @Override
    public void endMatch(int round, int points, int enemyPoints) {
    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
//        System.out.println("<<<<<<<<<<<<<<<<<<<End Round!>>>>>>>>>>>>>>>>>>>>>>>>>");
        tempPos.clear();
        hitPos.clear();
        savedHitPos.clear();
        shotMatrix.clear();
        freqMatrix.clear();
    }

}
