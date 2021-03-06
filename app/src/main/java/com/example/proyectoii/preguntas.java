package com.example.proyectoii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class preguntas extends AppCompatActivity {
    private TextView tvQuestion, tvScore, tvQuestionNum, tvCounter;
    private RadioGroup radioGroup;
    private RadioButton rb, rb2, rb3, rb4;
    private Button btnSiguiente;

    int totalQ;
    int qCounter = 0;
    int score = 0;

    ColorStateList dfRbColor;
    boolean answered;

    CountDownTimer countDownTimer;

    private ModeloPregunta currentQuestion;
    
    private List <ModeloPregunta> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
//        Intent intent = getIntent();
        questionList = new ArrayList<>();
        tvQuestion = findViewById(R.id.preg);
        tvScore = findViewById(R.id.puntos);
        tvQuestionNum = findViewById(R.id.pregunta);
        tvCounter = findViewById(R.id.counter);

        radioGroup = findViewById(R.id.rGroup);

        rb = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);

        btnSiguiente = findViewById(R.id.btn);

        dfRbColor = rb.getTextColors();

        addQuestions();
        totalQ = questionList.size();
        showNextQuestion();

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answered == false){
                    if(rb.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();
                        countDownTimer.cancel();
                    }else{
                        Toast.makeText(preguntas.this, "Selecciona una opci??n.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo =  radioGroup.indexOfChild(rbSelected) + 1;

        if(answerNo == currentQuestion.getCorrectas()){
            score++;
            tvScore.setText("Puntos: "+ score);
        }

        rb.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch ( currentQuestion.getCorrectas()){
            case 1:
                rb.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }

        if(qCounter < totalQ){
            btnSiguiente.setText("Siguiente");
        }else{
            btnSiguiente.setText("Terminar");
        }

    }

    private void showNextQuestion() {
        radioGroup.clearCheck();
        rb.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);

        if(qCounter<totalQ){
            timer();
            currentQuestion = questionList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb.setText(currentQuestion.getRb());
            rb2.setText(currentQuestion.getRb2());
            rb3.setText(currentQuestion.getRb3());
            rb4.setText(currentQuestion.getRb4());

            qCounter++;
            btnSiguiente.setText("Enviar");
            tvQuestionNum.setText("Pregunta: "+qCounter+"/"+totalQ);
            answered = false;
        }else{
            finish();
        }
    }

    private void timer() {
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l) {
                tvCounter.setText("00:"+ 1/1000);
            }

            @Override
            public void onFinish() {
                showNextQuestion();
            }
        }.start();
    }

    private void addQuestions(){
        questionList.add(new ModeloPregunta("Los veh??culos de transporte selectivo, deben poseer.",
                "Placa ??nica adelante y placa de transporte p??blico en la parte trasera",
                "Las dos placas (??nica y de transporte p??blico) en la parte trasera",
                "Placa ??nica en la parte trasera ",
                "Ninguna de las anteriores",
                2));
        questionList.add(new ModeloPregunta("Usted nunca deber??a adelantar un ciclista",
                "Justo antes de doblar a la derecha ",
                "Justo antes de doblar a la izquierda ",
                "Recien pasada una intersecci??n",
                "En un camino de tierra",
                1));
        questionList.add(new ModeloPregunta("Son prohibiciones en relaci??n con las placas ??nicas de circulaci??n",
                "Remplazar la placa oficial por otra con dise??os diferentes al confeccionado por el estado",
                "Colocar la placa de circulaci??n vehicular en un lugar distinto al establecido, o de tal manera que se dificulte o impida su legibilidad",
                "Transitar con placa vencida",
                "Todas las anteriores",
                4));
        questionList.add(new ModeloPregunta("El informe escrito de lo acontecido en un accidente de tr??nsito, elaborado por el inspector de tr??nsito posee.",
                "Nombre de los lesionados, o de los fallecidos si los hubiere",
                "Descripci??n de los da??os visibles a veh??culos y/o propiedad p??blica y privada ",
                "Relatos de los hechos ocurridos",
                "Todas las anteriores",
                4));
        questionList.add(new ModeloPregunta("Cuando usted sale con su veh??culo despu??s de estar parqueado a la orilla de una cuneta. ??Qu?? debe hacer?",
                "Enciende la se??al, observa hacia adelante y sale con precauci??n",
                "Enciende la se??al, hace uso de los espejos retrovisores, observa hacia atr??s por su hombro, y sale con precauci??n",
                "Enciende la se??al respectiva y sale con su veh??culo",
                "Enciende la se??al y sale con su veh??culo sin ninguna precauci??n",
                2));
        questionList.add(new ModeloPregunta("??Qu?? debe de tener presente y cumplir cuando conduce un veh??culo?",
                "No estacionarse en curva",
                "2-\tAumentar la velocidad de su veh??culo al atravesar la bocacalle ",
                "Conducir durante la noche con luz altas dentro de la ciudad",
                "Dejar el veh??culo estacionado a menos de 5 metros de la esquina, de las intersecciones urbana y de los hidrantes p??blicos",
                1));
        questionList.add(new ModeloPregunta("Para los conductores de los veh??culos es permitido ",
                "Portar licencia de conducir deteriorada",
                "Portar licencia de conducir no adecuada al veh??culo",
                "Portar licencia de conducir vencida, suspendida o cancelada",
                "Ninguna de las anteriores",
                4));
        questionList.add(new ModeloPregunta("La se??al ???puente angosto??? ??Es una se??al?",
                "Preventiva ",
                "Restrictiva ",
                "Informativa",
                "Dispositivo para protecci??n de obras",
                1));
        questionList.add(new ModeloPregunta("Al conductor involucrado en un accidente y en esta de embriaguez o bajo efectos de droga ser?? sancionado con:",
                "multa de B/ 50.00  ",
                "un mes de arresto  ",
                "3 a 5 a??os de c??rcel",
                "Suspenci??n de licencia por un mes.",
                4));
        questionList.add(new ModeloPregunta("La se??al que nos indica el peso permitido en un lugar determinado ??Es una se??al? ",
                "Preventiva ",
                "Restrictiva ",
                "Informativa",
                "Dispositivo para protecci??n de obras",
                2));
    }
}