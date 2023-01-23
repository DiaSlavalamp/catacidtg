package com.catacid.catacidtg.oldGenerator;

public class Word {

    public CycleList<String> cycleList;



    public String word;

    public int index;


    public Word(CycleList<String> cycleList, int index) {
        this.cycleList = cycleList;
        this.index = index;
        this.word = cycleList.get(index).toString();
    }

    public Word getNext() {
        if (index < cycleList.toArray().length-1) {
            return new Word(cycleList, index+1);
        } else {
            return new Word(cycleList, 0);
        }

    }

    public Word getPerveous() {
        if (index > 0) {
            return new Word(cycleList, index - 1);
        } else {
            return new Word(cycleList, cycleList.toArray().length - 1);
        }
    }


    @Override
    public String toString() {
        return word;
    }
}
