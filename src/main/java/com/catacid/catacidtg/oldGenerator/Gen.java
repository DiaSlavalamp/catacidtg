package com.catacid.catacidtg.oldGenerator;

import com.catacid.catacidtg.oldGenerator.CycleList;
import com.catacid.catacidtg.oldGenerator.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Gen {

    Word word;
    Word cursorWord;

    CycleList cycleList;

    String output = "";

    CycleList<CycleList> cycleLists = new CycleList(new ArrayList());

    Map<String, String> wordMap = new HashMap<String, String>();

    int randomColor = 0;

    int nextWordCount = 0;

    boolean vicluchitPredlogi = true;

    boolean debug = false;//это включить

    Gen(Word word) {
        this.word = word;
        cursorWord = word.getNext();
        output += word;
        cycleList = word.cycleList;
        cycleLists.add(cycleList);
    }

    void generate3(int count) {
        word = new Word(cycleList, new Random().nextInt(cycleList.toArray().length));
        output = "";
        output+= word;
// for (int i = 0; i < count; i++) {
        generate2(count);
// }
    }
//====================2021========================================================

    private Word getNextWord2021() {
        Word word = this.word.getNext();
        if(word.cycleList == cursorWord.cycleList) {
            cursorWord = word.getNext();
        }
        output += " " + word;
        nextWordCount++;
        System.out.println("getNextWord success "+ word);


        if(vicluchitPredlogi) {
            if (word.word.length() <= 3) {
                getNextWord();
            }
        }


        return word;
    }

    void generate2021(int count, String word, String text) {
        String wordS = "";
        try {

            // for (int i = 0; i < count; i++) {
           // Word word = getNextWord2021();
            String threeChar = word.toString().substring(0, 6);


            wordS += threeChar;

            String textFindDiapazon = text;
            String targetWord = threeChar;
            for (int ii = 0; ii < 900; ii++) {
                int index = textFindDiapazon.indexOf(targetWord.substring(3, 6));
                if(index >= 4) {
                    String nwtext = textFindDiapazon.substring(index - 3);
                    String nextWord = nwtext.substring(0, 9);
                    if ((nextWord.substring(0, 6)).equals(targetWord)) {
                        textFindDiapazon = textFindDiapazon.substring(index + 3);
                    } else {
                        wordS += nextWord.substring(6, 9);
                        targetWord = nextWord.substring(3, 9);
                    }
                }else {
                    targetWord = text.substring(text.indexOf(targetWord)).substring(3,9);
                    wordS +=targetWord.substring(3,6);
                }
            }

            //System.out.println(wordS);

//            genNextWordCombine();
//            nextCycleList();
//            getNextWord();
            //  }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(wordS);
        }
    }
//===================2021==============================================================

    void generate22(int count) {
        for (int i = 0; i < count; i++) {
            getNextWord();
            genNextWordCombine();
            nextCycleList();
            getNextWord();
        }
    }

    void generate2(int count) {
        for (int i = 0; i < count; i++) {
            genNextWordCombine();
            nextCycleList();
        }
    }

    void generate1(int count){
        for(int i =0; i<count;i++) {
            getNextWord();
            getNextWord();
            getNextWord();
            getNextWord();
            getNextWord();
            System.out.println(cycleList.toArray().length);
// cycleList = cycleLists.getNext();
// cursorWord = new Word(cycleList,0);
            nextCycleList();
//cycleList = cycleLists.getNext();
            System.out.println(cycleList.toArray().length);
            genNextWordCombine();
            getNextWord();
            getNextWord();
            getNextWord();
            getNextWord();
            getNextWord();
            getNextWord();
            getNextWord();
        }
// genNextWord();
// genNextWord();
// genNextWord();
// for(int q = 0; q<cycleLists.toArray().length;q++) {
// for (int i = 0; i < cycleList.toArray().length - 1; i++) {
// if (genSteep()) {
// break;
// } else {
// //getNextWord();
// }
// }
// }
    }

    boolean genNextWordCombine(){
        for(int i =0; i<=100;i++) {
            if (genNextWord()) {
                System.out.println("genNextWordCombine success " + word);
                System.out.println(output);
                return true;
            } else {
                getNextWord();
                System.out.println("genNextWordCombine false ");
//genNextWordCombine();

            }

        }
        System.out.println("genNextWordCombine false ");
        return false;
    }

    boolean genNextWord() {
        for(int q = 1; q<cycleLists.toArray().length;q++) {
            for (int i = 0; cursorWord.index <= cycleList.toArray().length - 2; i++) {
                if (genSteep() == true) {
                    nextWordCount = 0;
// cycleList = cycleLists.get(q);
// cursorWord = new Word(cycleList,0);
                    System.out.println("genNextWord success list"+ cursorWord.cycleList.toArray().length+ " q " + q + " "+ word);
                    return true;
// break;
                }
            }
            System.out.println("genNextWord false list " + q);
//break;
//if(q>=cycleLists.toArray().length-1) q=0;
// cycleList = cycleLists.getNext();
// cursorWord = new Word(cycleList,0);
            nextCycleList();
            System.out.println("genNextWord cycleLists.get" + cursorWord.cycleList.toArray().length+ " q " + (q+1));

        }
        System.out.println("genNextWord all lists false ");
// cycleList = cycleLists.getNext();
// cursorWord = new Word(cycleList,0);
        nextCycleList();
        return false;
// getNextWord();
    }

    void getNextWord() {
        word = word.getNext();
        if(word.cycleList == cursorWord.cycleList) {
            cursorWord = word.getNext();
        }
        output += " " + word;
        nextWordCount++;
        System.out.println("getNextWord success "+ word);


        if(vicluchitPredlogi) {
            if (word.word.length() <= 3) {
                getNextWord();
            }
        }



    }

    boolean genSteep() {
        if ((word.toString()).equals((cursorWord.toString()))) {
//word.word = word.word + "(\033[0m)"+"\033["+ 4 +"m ";
            Word bw = word;
            Word bc = cursorWord;
//word.getPerveous().word = word.word + "(\033[0m)"+"\033["+ 4 +"m ";
            word = cursorWord.getNext();
            cursorWord = word.getNext().getNext();



            int r = getRandomColor();

            System.out.println("genSteep random "+ r);
//output += " "+word+"\033["+ r +"m";
            String sss = " ";
            sss += cursorWord.cycleList.name + bc.getPerveous() + bc +bc.getNext() ;
            if(!wordMap.containsValue(sss)){
                wordMap.put(sss, sss);
                if(debug) {
                    output += "\033[1;91m " +
// "\033["+ r +"m " +
                            "[" + cursorWord.cycleList.name
                            + " nextWordCount "
                            + nextWordCount + " r=" + r + "] "
                            + "\033[0m" + "\033[" + r + "m "
                            + bc.getPerveous() + " \033[4;" + r
                            + "m" + bc + "\033[0m" + "\033["
                            + r + "m " + bc.getNext();
                    System.out.println("genSteep success " + cursorWord.cycleList.toArray().length + word);
                }else {
                    output += "\033[0m" + "\033[" + r + "m " + bc.getNext();
                }
                return true;
            }
            return false;

        }else {
            cursorWord = cursorWord.getNext();
            System.out.println("genSteep false "+ cursorWord.cycleList.toArray().length + word);
            return false;
        }
    }

    void addCycleList(CycleList cycleList){
        cycleLists.add(cycleList);
    }

    void nextCycleList(){
//cursorWord.cycleList.actualPosition = cursorWord.index;
        cursorWord = cursorWord.getNext();
        cycleList = cursorWord.cycleList;
        cycleList.actualPosition = cursorWord.index;
        cycleList = cycleLists.getNext();
//cursorWord = new Word(cycleList,0);
//cycleList.getNext();
        cursorWord = new Word(cycleList,cycleList.actualPosition);
        System.out.println("cycleList,cycleList.actualPosition " + cycleList.toArray().length + " poz "+ cycleList.actualPosition);
    }

    int getRandomColor(){

        Random random = new Random();
        int r = random.nextInt(8)+30;
        while(r == randomColor | r == randomColor-1 | r == randomColor+1 | r == randomColor-2| r == randomColor+2){
            r = random.nextInt(8)+30;
//r = getRandomColor();
        }
        this.randomColor = r;
        return r;
    }

}