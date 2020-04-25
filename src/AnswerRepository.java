import java.util.*;

public class AnswerRepository {

    private Map<Integer, List<String>> answers;

    public AnswerRepository() {
        this.answers = new LinkedHashMap<>();
        setAnswers();
    }

    private void setAnswers() {
        answers.put(1, Arrays.asList("int", "Float", "boolean", "char"));
        answers.put(2, Arrays.asList("Swing", "Applet", "Object", "ActionEvent"));
        answers.put(3, Arrays.asList("swing", "applet", "net", "lang"));
        answers.put(4, Arrays.asList("lang", "Swing", "Applet", "awt"));
        answers.put(5, Arrays.asList("ABC", "DEF", "GHI", "JKL"));
        answers.put(6, Arrays.asList("class", "int", "get", "if"));
        answers.put(7, Arrays.asList("Swing", "ActionPerformed", "ActionEvent", "Button"));
        answers.put(8, Arrays.asList("toString", "finalize", "equals", "getDocument"));
        answers.put(9, Arrays.asList("init", "main", "start", "destroy"));
        answers.put(10, Arrays.asList("JButton", "JList", "JButtonGroup", "JTextArea"));
    }

    public List<String> getAnswersForQuestionNumber(int qNum){
        return answers.get(qNum);
    }
}
