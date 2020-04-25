import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Test extends JFrame implements ActionListener {


    int queNum;
    JLabel label;
    JRadioButton[] options;
    JButton nextButton;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;


    public Test() {
        queNum = 1;
        label = new JLabel();
        options = new JRadioButton[4];
        nextButton = new JButton("Next");
        questionRepository = new QuestionRepository();
        answerRepository = new AnswerRepository();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setVisible(true);
        setLayout(null);
        add(label);
        add(nextButton);
        for (int i = 0, j=80; i < options.length; i++, j+=30) {
            options[i] = new JRadioButton();
            options[i].setBounds(50, j, 100, 20);
            add(options[i]);
        }
        setQA();
        label.setBounds(30, 40, 450, 20);
        nextButton.setBounds(100, 240, 100, 30);
        nextButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            queNum++;
            setQA();
        }
    }

    public static void main(String[] args) {
        new Test();
    }


    void setQA() {
        label.setText(this.questionRepository.getQuestion(queNum));
        List<String> answers = this.answerRepository.getAnswersForQuestionNumber(queNum);
        for (int i = 0; i < options.length; i++) {
            options[i].setText(answers.get(i));
        }
    }
}

