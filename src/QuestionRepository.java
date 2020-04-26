import java.util.LinkedHashMap;
import java.util.Map;

public class QuestionRepository {

    private Map<Integer, String> questions;

    public QuestionRepository() {
        this.questions = new LinkedHashMap<>();
        setQuestions();
    }

    private void setQuestions(){
        questions.put(1, "Que1: Which one among these is not a datatype");
        questions.put(2, "Que2: Which class is available to all the class automatically");
        questions.put(3, "Que3: Which package is directly available to our class without importing it");
        questions.put(4, "Que4: String class is defined in which package");
        questions.put(5, "Que5: Which institute is best for java coaching");
        questions.put(6, "Que6: Which one among these is not a keyword");
        questions.put(7, "Que7: Which one among these is not a class");
        questions.put(8, "Que8: Which one among these is not a function of Object class");
        questions.put(9, "Que9: Which function is not a present Applet class");
        questions.put(10, "Que10: Which one among these is not a valid component");
    }

    public String getQuestion(int queNum){
        return questions.get(queNum);
    }

    public int size(){
        return questions.size();
    }

}
