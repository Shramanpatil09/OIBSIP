package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView questionText;
    RadioGroup radioGroup;
    RadioButton option1, option2, option3, option4;
    Button nextBtn;

    int currentQuestion = 0;
    int score = 0;

    String[] questions = {
            "What is the capital of India?",
            "Which language is used in Android?",
            "Who is the founder of Microsoft?"
    };

    String[][] options = {
            {"Mumbai", "Delhi", "Pune", "Chennai"},
            {"Python", "Java", "C++", "Swift"},
            {"Steve Jobs", "Bill Gates", "Elon Musk", "Mark Zuckerberg"}
    };

    String[] answers = {"Delhi", "Java", "Bill Gates"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);

        loadQuestion();

        nextBtn.setOnClickListener(v -> {

            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selected = findViewById(selectedId);

            if (selected.getText().toString().equals(answers[currentQuestion])) {
                score++;
            }

            currentQuestion++;

            if (currentQuestion < questions.length) {
                loadQuestion();
            } else {
                showResult();
            }
        });
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestion]);

        option1.setText(options[currentQuestion][0]);
        option2.setText(options[currentQuestion][1]);
        option3.setText(options[currentQuestion][2]);
        option4.setText(options[currentQuestion][3]);

        radioGroup.clearCheck();
    }

    private void showResult() {
        Toast.makeText(this, "Score: " + score, Toast.LENGTH_LONG).show();
    }
}