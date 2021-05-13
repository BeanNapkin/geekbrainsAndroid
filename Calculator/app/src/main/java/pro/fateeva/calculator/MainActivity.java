package pro.fateeva.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator = new Calculator();
    private static final String CALCULATOR_PARAM = "HorizontalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button dotButton = (Button) findViewById(R.id.buttonDot);

        List<Button> buttonArrayList = new ArrayList<>();
        buttonArrayList.add((Button) findViewById(R.id.button0));
        buttonArrayList.add((Button) findViewById(R.id.button1));
        buttonArrayList.add((Button) findViewById(R.id.button2));
        buttonArrayList.add((Button) findViewById(R.id.button3));
        buttonArrayList.add((Button) findViewById(R.id.button4));
        buttonArrayList.add((Button) findViewById(R.id.button5));
        buttonArrayList.add((Button) findViewById(R.id.button6));
        buttonArrayList.add((Button) findViewById(R.id.button7));
        buttonArrayList.add((Button) findViewById(R.id.button8));
        buttonArrayList.add((Button) findViewById(R.id.button9));
        buttonArrayList.add((Button) findViewById(R.id.buttonPlus));
        buttonArrayList.add((Button) findViewById(R.id.buttonMinus));
        buttonArrayList.add((Button) findViewById(R.id.buttonDeleteLast));
        buttonArrayList.add((Button) findViewById(R.id.buttonDeleteAll));
        buttonArrayList.add(dotButton);
        buttonArrayList.add((Button) findViewById(R.id.buttonPercent));
        buttonArrayList.add((Button) findViewById(R.id.buttonEqual));
        buttonArrayList.add((Button) findViewById(R.id.buttonDivision));
        buttonArrayList.add((Button) findViewById(R.id.buttonMultiplication));

        final TextView textView = findViewById(R.id.textView);

        dotButton.setText(String.valueOf(calculator.getDecimalSeparator()));

        View.OnClickListener OnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button0:
                        calculator.clickOnButton(0);
                        break;
                    case R.id.button1:
                        calculator.clickOnButton(1);
                        break;
                    case R.id.button2:
                        calculator.clickOnButton(2);
                        break;
                    case R.id.button3:
                        calculator.clickOnButton(3);
                        break;
                    case R.id.button4:
                        calculator.clickOnButton(4);
                        break;
                    case R.id.button5:
                        calculator.clickOnButton(5);
                        break;
                    case R.id.button6:
                        calculator.clickOnButton(6);
                        break;
                    case R.id.button7:
                        calculator.clickOnButton(7);
                        break;
                    case R.id.button8:
                        calculator.clickOnButton(8);
                        break;
                    case R.id.button9:
                        calculator.clickOnButton(9);
                        break;
                    case R.id.buttonDot:
                        calculator.clickOnButtonDot();
                        break;
                    case R.id.buttonPlus:
                        calculator.clickOnButtonPlus();
                        break;
                    case R.id.buttonMinus:
                        calculator.clickOnButtonMinus();
                        break;
                    case R.id.buttonDivision:
                        calculator.clickOnButtonDivision();
                        break;
                    case R.id.buttonDeleteAll:
                        calculator.clickOnButtonDeleteAll();
                        break;
                    case R.id.buttonDeleteLast:
                        calculator.clickOnButtonDeleteLast();
                        break;
                    case R.id.buttonEqual:
                        calculator.clickOnEqual();
                        break;
                    case R.id.buttonMultiplication:
                        calculator.clickOnButtonMultiplication();
                        break;
                    case R.id.buttonPercent:
                        calculator.clickOnButtonPercent();
                        break;
                }
                textView.setText(calculator.getTextForView());
            }
        };

        for (int i = 0; i < buttonArrayList.size(); i++) {
            buttonArrayList.get(i).setOnClickListener(OnClickListener);
        }

        findViewById(R.id.buttonChooseTheme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ChooseTheme.class));
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CALCULATOR_PARAM, calculator);
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final TextView textView = findViewById(R.id.textView);
        calculator = (Calculator) savedInstanceState.getSerializable(CALCULATOR_PARAM);
        textView.setText(calculator.getTextForView());
    }

}