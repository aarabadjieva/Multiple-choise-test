import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/* ============================================================================================
  Creating the GUI Panel
 =========================================================================================== */
public class Test extends JFrame implements ActionListener {


    int queNum;
    int correctAnswers;
    boolean isCorrectLastAnswer;
    JLabel label;
    JRadioButton[] options;
    ButtonGroup buttonGroup;
    JButton nextButton;
    JButton prevButton;
    JButton resultButton;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;


    public Test() {
        queNum = 1;
        correctAnswers = 0;
        isCorrectLastAnswer = false;
        label = new JLabel();
        options = new JRadioButton[5];
        buttonGroup = new ButtonGroup();
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        resultButton = new JButton("Result");
        resultButton.setVisible(false);
        prevButton.setVisible(false);
        questionRepository = new QuestionRepository();
        answerRepository = new AnswerRepository();
        setTitle("Java Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setVisible(true);
        setLayout(null);
        add(label);
        add(nextButton);
        add(prevButton);
        add(resultButton);
        for (int i = 0, j = 80; i < options.length; i++, j += 30) {
            options[i] = new JRadioButton();
            if (i < 4) {
                options[i].setBounds(50, j, 150, 20);
                add(options[i]);
            }
            buttonGroup.add(options[i]);
        }
        setQA();
        label.setBounds(30, 40, 450, 20);
        prevButton.setBounds(100, 240, 100, 30);
        nextButton.setBounds(250, 240, 100, 30);
        resultButton.setBounds(400, 240, 100, 30);
        nextButton.addActionListener(this);
        prevButton.addActionListener(this);
        resultButton.addActionListener(this);
    }

    /* ============================================================================================
          Adding Action Listener to change screen when Next Button is pressed
         =========================================================================================== */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (checkAnswer()) {
                correctAnswers++;
                isCorrectLastAnswer = true;
            } else {
                isCorrectLastAnswer = false;
            }
            queNum++;
            setQA();
        }

        if (e.getSource() == prevButton) {
            nextButton.setVisible(true);
            resultButton.setVisible(false);
            queNum--;
            if (isCorrectLastAnswer) {
                correctAnswers--;
            }
            setQA();
        }

        if (e.getSource() == resultButton) {
            if (checkAnswer()) {
                correctAnswers++;
            }
            String message;
            String[] options = {"I will try again!",
                    "No, I give up"};
            if (correctAnswers < questionRepository.size() / 2) {
                message = String.format("Correct answers %d/%d\nYour result is poor.\nTry one more time!"
                        , correctAnswers, questionRepository.size());
            } else if (correctAnswers < questionRepository.size()) {
                message = String.format("Correct answers %d/%d\nYour result is great.\nStill you can do better!"
                        , correctAnswers, questionRepository.size());
            } else {
                message = String.format("Correct answers %d/%d\nCongratulations!\nYou are a master!"
                        , correctAnswers, questionRepository.size());
                options = new String[]{"Cheers, mate!"};
            }


            final JOptionPane optionPane = new JOptionPane(
                    message,
                    JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);
            final JDialog dialog = new JDialog(this,
                    "Your result",
                    true);
            dialog.setContentPane(optionPane);
            dialog.setDefaultCloseOperation(
                    JDialog.DO_NOTHING_ON_CLOSE);
            dialog.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });
            optionPane.addPropertyChangeListener(
                    e1 -> {
                        String prop = e1.getPropertyName();
                        if (dialog.isVisible()
                                && (e1.getSource() == optionPane)
                                && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                            dialog.setVisible(false);
                        }
                    });
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);

            String value = (String) optionPane.getValue();
            if (value.equals(options[0])) {
                if (correctAnswers<questionRepository.size()){
                    new Test();
                }else {
                    System.exit(0);
                }
            } else if (value.equals(options[1])) {
                System.exit(0);
            }
            optionPane.isShowing();
        }
    }

    public static void main(String[] args) {
        new Test();
    }


    void setQA() {
        options[4].setSelected(true);
        label.setText(this.questionRepository.getQuestion(queNum));
        List<String> answers = this.answerRepository.getAnswersForQuestionNumber(queNum);
        for (int i = 0; i < options.length - 1; i++) {
            options[i].setText(answers.get(i));
        }
        prevButton.setVisible(queNum > 1);
        if (queNum == questionRepository.size()) {
            nextButton.setVisible(false);
            resultButton.setVisible(true);
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

