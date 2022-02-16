package com.example.dentaku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List<型> リスト名 = new ArrayList<型>();
        List<Button> buttonList = new ArrayList<>();
        //ボタンリスト取得 リスト名.add("要素");
        buttonList.add(findViewById(R.id.btZero));
        buttonList.add(findViewById(R.id.btOne));
        buttonList.add(findViewById(R.id.btTwo));
        buttonList.add(findViewById(R.id.btThree));
        buttonList.add(findViewById(R.id.btFour));
        buttonList.add(findViewById(R.id.btFive));
        buttonList.add(findViewById(R.id.btSix));
        buttonList.add(findViewById(R.id.btSeven));
        buttonList.add(findViewById(R.id.btEight));
        buttonList.add(findViewById(R.id.btNine));
        //記号ボタンを取得
        buttonList.add(findViewById(R.id.btAdd));
        buttonList.add(findViewById(R.id.btSubtract));
        buttonList.add(findViewById(R.id.btMultiply));
        buttonList.add(findViewById(R.id.btDivide));
        buttonList.add(findViewById(R.id.btEqual));
        //その他のボタン
        buttonList.add(findViewById(R.id.btClear));
        buttonList.add(findViewById(R.id.btDelete));
        buttonList.add(findViewById(R.id.btPoint));
        buttonList.add(findViewById(R.id.btPlusMinus));
        buttonList.add(findViewById(R.id.btPercent));

        ButtonListener listener = new ButtonListener();

        for(Button button :buttonList){
            button.setOnClickListener(listener);
        }
    }

    //private class クラス名 implements インターフェイス名{}
    private class ButtonListener implements View.OnClickListener{

        private final List<BigDecimal> _numList = new ArrayList<>();
        private final List<Character> _opeList = new ArrayList<>();
        private String _inputValue = "";

        @Override
        public void onClick(View view) {
            TextView tvFormula = findViewById(R.id.tvFormula);

            //ボタン毎の処理を定義
            //クリックされたViewを受け取る,ButtonはViewから派生している
            int btId = view.getId();
            char inputChar;
            if (btId == R.id.btZero) {
                inputChar = '0';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if (btId == R.id.btOne) {
                inputChar = '1';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if (btId == R.id.btTwo) {
                inputChar = '2';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if (btId == R.id.btThree) {
                inputChar = '3';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if (btId == R.id.btFour) {
                inputChar = '4';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if (btId == R.id.btFive) {
                inputChar = '5';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if (btId == R.id.btSix) {
                inputChar = '6';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if (btId == R.id.btSeven) {
                inputChar = '7';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if (btId == R.id.btEight) {
                inputChar = '8';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if (btId == R.id.btNine) {
                inputChar = '9';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            // 記号ボタンの場合
            else if (btId == R.id.btAdd) {
                inputChar = '+';
                if(!(_inputValue.equals(""))) {
                    addList(tvFormula, _inputValue, inputChar);
                }
            }
            else if(btId == R.id.btSubtract) {
                inputChar = '-';
                if(!(_inputValue.equals(""))) {
                    addList(tvFormula, _inputValue, inputChar);
                }
            }
            else if(btId == R.id.btMultiply) {
                inputChar = '×';
                if(!(_inputValue.equals(""))) {
                    addList(tvFormula, _inputValue, inputChar);
                }
            }
            else if(btId == R.id.btDivide) {
                inputChar = '÷';
                if(!(_inputValue.equals(""))) {
                    addList(tvFormula, _inputValue, inputChar);
                }
            }
            else if(btId == R.id.btEqual) {
                inputChar = '=';
                if(!(_inputValue.equals(""))) {
                    addList(tvFormula, _inputValue, inputChar);
                }
                String result = calculate().toString();
                tvFormula.setText(result);
                _inputValue = result;

                _numList.clear();
                _opeList.clear();
            }
            // その他ボタンの場合の処理
            else if(btId == R.id.btClear) {
                tvFormula.setText("");
                _numList.clear();
                _opeList.clear();
                _inputValue="";
            }
            else if(btId == R.id.btDelete) {
                String formulaStr = tvFormula.getText().toString();
                char formulaStrLastChar = formulaStr.charAt(formulaStr.length() - 1);

                if(isFourArithmeticOpe(formulaStrLastChar)) {
                    _opeList.remove(_opeList.size() - 1);
                }
                if(!formulaStr.isEmpty()) {
                    tvFormula.setText(formulaStr.subSequence(0, formulaStr.length() - 1));
                }
                if(!formulaStr.isEmpty()) {
                    _inputValue = _inputValue.substring(0, _inputValue.length() - 1);
                }
            }
            else if(btId == R.id.btPoint) {
                inputChar = '.';
                addTextView(tvFormula, inputChar);
                _inputValue += inputChar;
            }
            else if(btId == R.id.btPlusMinus) {

            }
            else if(btId == R.id.btPercent) {

            }
        }
        private  void addList(TextView tvFormula, String inputValue, char ope) {
            addTextView(tvFormula, ope);
            _numList.add(new BigDecimal(inputValue));
            _opeList.add(ope);
            _inputValue = "";
        }
        private void addTextView(TextView textView, char addStr) {
            String str = String.format("%s" + "%s",textView.getText().toString(),addStr);
            textView.setText(str);
        }
        private BigDecimal calculate() {
            int i = 0;

            while(i < _opeList.size()) {
                if(_opeList.get(i) == '×' | _opeList.get(i) == '÷') {
                    BigDecimal resultMultiDiv = _opeList.get(i) == '×' ? _numList.get(i).multiply(_numList.get(i+1)) : _numList.get(i).divide(_numList.get(i+1),9,BigDecimal.ROUND_DOWN);

                    _numList.set(i, resultMultiDiv);
                    _numList.remove(i+1);
                    _opeList.remove(i);
                    i--;
                }
                else if(_opeList.get(i) == '-') {
                    _opeList.set(i, '+');
                    _numList.set(i+1, _numList.get(i+1).negate());
                }
                i++;
            }
            BigDecimal result = new BigDecimal("0");
            for(BigDecimal j : _numList) {
                result = result.add(j);
            }
            return result;
        }
        private  boolean isFourArithmeticOpe(char c) {
            return (c == '+' | c == '-' | c == '*' | c == '/');
            /*
            *if(c == '+' | c == '-' | c == '*' | c == '/') return true;
            *return false;
            */
        }
    }
}