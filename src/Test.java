import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/* ============================================================================================
  Creating the GUI Panel
 =========================================================================================== */
public class Test extends JFrame implements ActionListener {


    int queNum;
    int correctAnswers;
    JLabel label;
    JRadioButton[] options;
    ButtonGroup buttonGroup;
    JButton nextButton;
    JButton resultButton;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;


    public Test() {
        queNum = 1;
        correctAnswers = 0;
        label = new JLabel();
        options = new JRadioButton[5];
        buttonGroup = new ButtonGroup();
        nextButton = new JButton("Next");
        resultButton = new JButton("Result");
        resultButton.setEnabled(false);
        questionRepository = new QuestionRepository();
        answerRepository = new AnswerRepository();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setVisible(true);
        setLayout(null);
        add(label);
        add(nextButton);
        add(resultButton);
        for (int i = 0, j = 80; i < options.length; i++, j += 30) {
            options[i] = new JRadioButton();
            if (i<4){
                options[i].setBounds(50, j, 100, 20);
                add(options[i]);
            }
            buttonGroup.add(options[i]);
        }
        setQA();
        label.setBounds(30, 40, 450, 20);
        nextButton.setBounds(100, 240, 100, 30);
        resultButton.setBounds(270, 240, 100, 30);
        nextButton.addActionListener(this);
        resultButton.addActionListener(this);
    }

    /* ============================================================================================
          Adding Action Listener to change screen when Next Button is pressed
         =========================================================================================== */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (checkAnswer()){
                correctAnswers++;
            }
            queNum++;
            setQA();
        }

        if (e.getSource() == resultButton) {
            JOptionPane.showMessageDialog(this, "Correct answers = " + correctAnswers);
        }
    }

    public static void main(String[] args) {
        new Test();
    }


    void setQA() {
        options[4].setSelected(true);
        label.setText(this.questionRepository.getQuestion(queNum));
        List<String> answers = this.answerRepository.getAnswersForQuestionNumber(queNum);
        for (int i = 0; i < options.length-1; i++) {
            options[i].setText(answers.get(i));
        }
        if (queNum == 10) {
            nextButton.setEnabled(false);
            resultButton.setEnabled(true);
        }
    }

    /* ============================================================================================
          Checking if the answer is correct
         =========================================================================================== */
    boolean checkAnswer() {
        switch (queNum) {
            case 1:
            case 7:
            case 9:
                return options[1].isSelected();
            case 2:
            case 6:
            case 10:
                return options[2].isSelected();
            case 3:
            case 8:
                return options[3].isSelected();
            case 4:
            case 5:
                return options[0].isSelected();
            default:
                return false;
        }
    }
}

