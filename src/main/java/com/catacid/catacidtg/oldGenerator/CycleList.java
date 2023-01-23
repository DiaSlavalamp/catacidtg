package com.catacid.catacidtg.oldGenerator;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CycleList<E> extends ArrayList<E> {
    public String name = "fixit";


    public int actualPosition = 0;

    public E getNext(){

        if(actualPosition<super.toArray().length-1) {
            actualPosition++;
        }else {
            actualPosition = 0;
        }
        return super.get(actualPosition);
    }


    public static CycleList CycleListt(String s) throws IOException {
        File file = new File(s);
        String fa = new String(Files.readAllBytes(Paths.get(s)));
        String[] words = fa.split(" ");
        CycleList faf = new CycleList(Arrays.asList(words));
        //this.add(words);
        //super(Arrays.asList(words));
        //System.out.print(faf.toString());
        return faf;
    }

public CycleList(List list){
        super(list);
}

//    LinkedList a;
//
//    CycleList(){
//     a.
//    }
//    @Override
//    public void getNext(){
//
//    }
}
