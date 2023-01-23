package com.catacid.catacidtg.oldGenerator;



import java.io.File;
import java.util.ArrayList;

public class textr {


//    public static void main(String[] args)  {
//
//     //   ObedinitTXTfayly.run();
//
//        try {
//        //generateOld();//генерировать по старому методу
//        //generateNew("начале",new String(Files.readAllBytes(Paths.get("C:\\Users\\YobaEtoYa\\IdeaProjects\\ImageDrawer-master\\src\\main\\resources\\base\\book библия.txt"))));
//
//
////        generateNew("тория ",new String(Files.readAllBytes(Paths.get("C:\\Users\\YobaEtoYa\\IdeaProjects\\ImageDrawer-master\\src\\main\\resources\\base\\Haydegger Martin Bytie i vremya.txt"))));
//
//
//            generateOne(100);
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//    }





    public static String generateOne(int seed, int count){
        Gen gen = null;
        try {
        CycleList<String> clist = CycleList.CycleListt("C:\\Users\\YobaEtoYa\\IdeaProjects\\catacidtg\\content\\Мир животных.txt");
            gen = new Gen(new Word(clist, seed));
            gen.addCycleList(clist);
            gen.generate22(count);//20
            //System.out.println(gen.output);
            return gen.output;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           // System.out.println(gen.output);
            return gen.output;
        }
    }





    public static void generateNew(String word, String text){
        try {
            Gen gen = new Gen(new Word(CycleList.CycleListt("C:\\Users\\YobaEtoYa\\IdeaProjects\\ImageDrawer-master\\src\\main\\resources\\base\\book библия.txt"), 0));
//            gen.addCycleList(CycleList.CycleListt("C:\\Users\\YobaEtoYa\\IdeaProjects\\ImageDrawer-master\\src\\main\\resources\\base\\4.txt"));
//            gen.addCycleList(clist3);
//            gen.addCycleList(clist4);
//            gen.addCycleList(clist5);
            gen.generate2021(10,word, text);
            System.out.println(gen.output);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void generateOld(){
        try {
            int count = 1;


            String s = "some";

            ArrayList<String> sa = new ArrayList<String>();
            sa.add("s");
            sa.add("s");
            sa.add("s");
            sa.add("s");
            sa.add("s");
            sa.add("s");


            CycleList<String> clist2 = CycleList.CycleListt("C:\\Users\\YobaEtoYa\\IdeaProjects\\ImageDrawer-master\\src\\main\\resources\\base\\4.txt");
            CycleList<String> clist = CycleList.CycleListt("C:\\Users\\YobaEtoYa\\IdeaProjects\\ImageDrawer-master\\src\\main\\resources\\base\\5.txt");
            CycleList<String> clist3 = CycleList.CycleListt("C:\\Users\\YobaEtoYa\\IdeaProjects\\ImageDrawer-master\\src\\main\\resources\\base\\book библия.txt");
            CycleList<String> clist4 = CycleList.CycleListt("C:\\Users\\YobaEtoYa\\IdeaProjects\\ImageDrawer-master\\src\\main\\resources\\base\\Haydegger Martin Bytie i vremya.txt");
            CycleList<String> clist5 = CycleList.CycleListt("C:\\Users\\YobaEtoYa\\IdeaProjects\\ImageDrawer-master\\src\\main\\resources\\base\\4.txt");


            File myFolder = new File("base");
            File[] files = myFolder.listFiles();

            ArrayList<CycleList> listlist = new ArrayList();
            listlist.add(clist);
            listlist.add(clist2);
            // listlist.add(clist3);

            String fword = clist2.getNext();
            String sword = clist2.getNext();
            String out = fword + " " + sword + " ";
            fword = sword;
            Word saveWord = new Word(clist2, 0);

//for(int q = 0; q < 50; q++){
//        for (int i = 0; i < 100; i++) {
//            int r = new Random().nextInt(2);
//            //System.out.print(r);
//            String checkWord = (String) (listlist.get(1)).getNext();
//            if (fword.equals(checkWord)) {
//                saveWord = new Word(clist2,clist2.actualPosition);
//                String nextWord = (String) (listlist.get(1)).getNext();
//                out += (checkWord + " " + nextWord + " ");
//                fword = nextWord;
//            }
//        }
//        out += saveWord.getNext().toString() + " ";
//        saveWord = saveWord.getNext();
//        fword = (String) (listlist.get(1)).getNext();


            Gen gen = new Gen(new Word(clist, 0));
            gen.addCycleList(clist);
            gen.addCycleList(clist3);
            gen.addCycleList(clist4);
            gen.addCycleList(clist5);
            gen.generate22(40);
//        for (int i = 0; i < 250; i++) {
//            gen.genNextWord();
//        }

            System.out.println(gen.output);
            //  System.out.print(out);

//        Iterator<String> iter = sa.iterator();
//        int actualCount = 0;
//        while (actualCount<=count) {
//            while (iter.hasNext()) {
//                iter.next();
//            }
//        }

//    }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
