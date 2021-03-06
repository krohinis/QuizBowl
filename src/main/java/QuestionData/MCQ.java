package QuestionData;

import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created by synerzip on 20/2/17.
 */
public class MCQ {

   // String keys;
    private String question, answer;
    private String[] choices;
    private int points;

    public MCQ(String question, String answer, String[] choices, int points) {
        this.question = question;
        this.answer = answer;
        this.choices = choices;
        this.points = points;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String[] getChoices() {
        return this.choices;
    }

    public int getPoints() {
        return this.points;
    }
}
