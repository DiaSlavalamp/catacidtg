package com.catacid.catacidtg.generator;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Generator {
    int randomColor = 0;
    static String ge;

    static String bv;

    static String kh;

    //книги в длинных String
    private static List<String> books;

    //лист с книгами порезаными на строчки
    private static List<List<String>> contentStringsList;

    public void setContent(List<File> contentList) {

        contentStringsList = new ArrayList<>();
        books = new ArrayList<>();

        for (File file : contentList) {
            try {
                String bookStr = new String(Files.readAllBytes(file.toPath()));

                // String root = System.getProperty("user.dir");
                //conferenceBot.gen.ge = new String(Files.readAllBytes(Paths.get(rootPath + "\\content\\Sorokin_Vladimir_-_Serdca_chetyrex.txt")));
                //  gen.bv = new String(Files.readAllBytes(Paths.get("C:\\Users\\defce\\IdeaProjects\\ConferenceBot\\src\\main\\resources\\киш.txt")));
                //  gen.kh =  new String(Files.readAllBytes(Paths.get("C:\\Users\\defce\\IdeaProjects\\ConferenceBot\\src\\main\\resources\\зс.txt")));

                //String[] array = ge.split("(?<=\\.)(.*)(?=[А-Я])");//между точкой и большой буквой выделяет пробел и по нему режет

                //String[] array = splitBook()
                bookStr = bookStr.replaceAll("\r", "\n");
                bookStr = bookStr.replaceAll("\n\n", "\n");
                bookStr = bookStr.replaceAll("\n", " ");//todo добавить в метод clean
                books.add(bookStr);
                contentStringsList.add(splitBook(bookStr));

                String sd = genBookAnswer("нужно");
                // System.out.println(bookStr);//todo это лог в консоль
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        for(String content : contentList){
        //todo dolzen ves spisok rabotat
        //ischo dlya каждой книги свой регексп нужен мб класс отделньый завести
        //типа аддбук и там помимо стринги еще стринга регекспа
//            ge = content;
//            new String(Files.readAllBytes(Paths.get(rootPath + "\\content\\Sorokin_Vladimir_-_Serdca_chetyrex.txt")));
//
//        }
    }

    //старый метод
    public static String getGreenMessage(String words) {

        String[] wordsArray = words.split(" ");
        String lastWord = wordsArray[wordsArray.length - 1];

        System.out.println(lastWord);
        try {
            String anser;


            String[] ss = ge.split(lastWord);


            if (ss.length < 2) {
                System.out.println("1 не найдено");
                ss = bv.split(lastWord);


                if (ss.length < 2) {
                    System.out.println("2 не найдено");
                    ss = kh.split(lastWord);


                    String[] sss = ss[1].split("\n");
                    anser = lastWord + sss[0];
                    return anser;
                }


                String[] sss = ss[1].split("\n");
                anser = lastWord + sss[0];
                return anser;
            }

            String[] sss = ss[1].split("\n");
            anser = lastWord + sss[0];
            return anser;

        } catch (Exception e) {
            System.out.println("3 не найдено");
            e.printStackTrace();
        }
        return "Дедушка тебя не понимает \uD83C\uDF85";
    }


    public String getBookAnswerMarkovaChain(String inputWord, int resultIterationCount, int wordsTrysCount, boolean ifFailedAttemptNextWordGet, boolean useOneWord) {//добавить сюда енум nextWord nextBook
        String result = "";
        result += inputWord;
        for(int i = 0; i < resultIterationCount; i++) {
            if(useOneWord) {
                result += getMarcova(inputWord, wordsTrysCount, ifFailedAttemptNextWordGet);
            }else {
                result += getMarcova(result, wordsTrysCount, ifFailedAttemptNextWordGet);
            }
        }
        return result;
    }
    //todo если слово не находится то надо брать следующее в тексте просто
    private String getMarcova(String inputWords, int wordsTrysCount, boolean ifFailedAttemptNextWordGet){//файлед атемпт нужно в предыдуший метод

        String result = "";

        String[] wordsArray = inputWords.split(" ");
        String lastWord = wordsArray[wordsArray.length - 1];

        Collections.shuffle(books);
        //режем книги в местах совпадений
        for (int i = 0; i < books.size(); i++) {

            //мешает книги и берет первую

            //ищет совпадение
            String matchs[] = books.get(i).split(lastWord);
            //если не нашел в книге то берет следующую
            if (matchs.length < 2) continue;



            ArrayList<String> matchsList = new ArrayList<String>(Arrays.asList(matchs));
            //убираем первый эллемент, он не является совпадением
            matchsList.remove(0);
            Collections.shuffle(matchsList);//рандом строчек совпадений

            //режем первое попавшееся совпадение по словам
            String firstMatch = matchsList.get(0);
            String[] words = firstMatch.split(" ");

            //добавляем цвет и конкатенируем пока не достигнем лимита или следующей втречи этого слова (нужно фиксануть это и добавить чтобы он начинал добавлять даже после второго совпадения)
            int r = getRandomColor();
            String randomColorPrefix =  "\033[0m" + "\033[" + r + "m ";
            result += randomColorPrefix;
            for (int iii = 0; iii < words.length; iii++) {
                String nextWord = words[iii];


                result += " "+ nextWord;
                if (iii > wordsTrysCount) {
                    return result;
                }
            }
            System.out.println("failed (ПОФИКСИТЬ)");//это совпадение закончилось, слово встречено снова
            return result;

            //  }

        }//todo блен а ведь еще может быть так что он одни и те же слова будет драть по совпадениию если слово не найдется(
        System.out.println("не нашел совпадений ни в одной книге(");
        //рекурсивная попытка которая просто выдает следующее слово идущее в тексте фактически. НО тут другой трайкаунт (1) который означает возвращение следующего слова в данном случе (это костыль)
        result += getMarcova(inputWords, 1, ifFailedAttemptNextWordGet);
        return result;
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

    private ArrayList<String> splitBook(String book) {
//        book.replace("\r", "\n");
//        book.replace("\n\n", "\n");//todo это не работает же!!!!
        //ge.replace("\r","");
        return new ArrayList<>(Arrays.asList(book.split("\n")));
        //return new ArrayList<>(Arrays.asList(ge.split("(?<=\\.)(.*)(?=[А-Я])")));//между точкой и большой буквой выделяет пробел и по нему режет

    }

    private ArrayList<String> getSentences(String word) {
        Collections.shuffle(contentStringsList);//todo это просто рандомайзер, сделать интереснее
        for (int i = 0; i < contentStringsList.size(); i++) {
            List<String> sentenceslist = contentStringsList.get(i);
            Stream<String> stream = sentenceslist.stream().filter((s) -> s.contains(word));
            ArrayList<String> result = (ArrayList<String>) stream.collect(Collectors.toList());
            if (!result.isEmpty()) {
                return result;
            }
        }
        return new ArrayList<>();
    }


    public String genBookAnswer(String word) {
        HashSet<String> result = new HashSet<>();
        String resultStr = "";

        ArrayList<String> match = getSentences(word);

        if (!match.isEmpty()) {
            //ArrayList<String> result = new ArrayList<>();
            Random random = new Random();
//            int r = random.nextInt(match.size()-1);
//            result.add(match.get(r));
//            resultStr+=match.get(r)+"\n";
            for (int i = 0; i < 1; i++) {
                if (match.size() == 1) {
                    String s = match.get(0);
                    // resultStr += (s.substring(s.indexOf(word), s.length() - 1));
                    resultStr += s;
                } else {
                    int rn = random.nextInt(match.size() - 1);
                    if (!result.contains(match.get(rn))) {
                        result.add(match.get(rn));
                        String s = match.get(rn);
                        resultStr += (s.substring(s.indexOf(word), s.length() - 1)) + "\n";
                    }
                }
            }
            return resultStr;

        } else {
            return "Ничиво не нашел(";
        }

//        Гет сентенс внутри исполняет гетсентенсес, получает массив совпадений и выбирает рандомное или даже конкатенирует 2-3 рандомных
//        Типа из списка
//        If список не пуст{
//            r = рандом(size-1)
//            Set.add(r)
//            S+ Get(r)
//            For(5) {
//                Rnext = рандом(size-1)
//                If (!set.cont rnext)
//                Set.add r2
//                S + Гет(рnext
//            }
//        }else if request try(
//                Дедушка
//        )

    }

}
