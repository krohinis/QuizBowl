package controller;

import QuestionData.MCQ;
import QuestionData.Question;
import QuestionData.SA;
import QuestionData.TF;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by synerzip on 20/2/17.
 */
public class QuizController {

    Logger logger=Logger.getLogger(QuizController.class);
    private final String tf = "TF";
    private final String mc = "MC";
    private final String sa = "SA";
    public ArrayList<Question> obj = new ArrayList<>();
    public boolean[] randoms=null;
    private BufferedReader br;
    private ArrayList<Question> data = new ArrayList<>();

    public QuizController(String filename) throws Exception {
        File file = new File(getClass().getClassLoader().getResource(filename+".txt").getFile());
        FileReader fr = new FileReader(file);
        br = new BufferedReader(fr);
        obj = openFile();
        logger.info("File Opened Successfully");
        randoms = new boolean[obj.size()];
    }

    public int getQuestionCount() {
        return this.obj.size();
    }

    public Question getRandomQuestion() {
        Random random = new Random();
        Question object = null;
        int flag = 0;
        do {
            int temp = random.nextInt(obj.size());
            if (randoms[temp] == false) {
                object = obj.get(temp);
                randoms[temp] = true;
                break;
            } else
                flag = 1;
        } while (flag == 1);
        return object;
    }

    public ArrayList<Question> openFile() throws Exception {
        int number = Integer.parseInt(br.readLine());
        while (number-- > 0) {
            String[] choice = br.readLine().split(" ");
            int points = Integer.parseInt(choice[1]);
            String question = br.readLine();
            logger.info("Question : "+question);
            Question obj;
            switch (choice[0]) {
                case tf:
                    String answertf = br.readLine().toUpperCase();
                    logger.info("Answer : "+answertf);
                    TF tfstore = new TF(question, answertf, points);
                    obj = new Question(tfstore, tf);
                    data.add(obj);
                    break;
                case mc:
                    int ChoiceNumber = Integer.parseInt(br.readLine());
                    String[] choices = new String[ChoiceNumber];
                    int i = 0;
                    char ch='A';
                    while (ChoiceNumber-- > 0) {
                            choices[i] = ch+") "+br.readLine();
                            ch++;
                            i++;
                    }
                    String answerMCQ = br.readLine().toUpperCase();
                    logger.info("Answer : "+answerMCQ);
                    MCQ mcq = new MCQ(question, answerMCQ, choices, points);
                    obj = new Question(mcq, mc);
                    data.add(obj);
                    break;
                case sa:
                    String answerSA = br.readLine().toUpperCase();
                    logger.info("Answer : "+answerSA);
                    SA sastore = new SA(question, answerSA, points);
                    obj = new Question(sastore, sa);
                    data.add(obj);
                    break;
                default:
                    System.out.println("Wrong Choice.");
            }
        }
        return data;
    }
}
