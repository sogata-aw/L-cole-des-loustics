package fr.iut.androidprojet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

import fr.iut.androidprojet.model.Addition;
import fr.iut.androidprojet.model.Question;

public class QuestionsActivity extends AppCompatActivity {

    private TextView compteurView;
    private TextView questionView;
    private ArrayList<TextView> reponsesView = new ArrayList<>();
    private ArrayList<CardView> cardsView = new ArrayList<>();
    private Button precedentView;
    private Button suivantView;

    private ArrayList<Question> questions;
    private ArrayList<Integer> alreadySelected = new ArrayList<>();
    private int randomQuestion;
    private Random random = new Random();

    private int nbFois = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_questions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        compteurView = findViewById(R.id.compteurQ);
        questionView = findViewById(R.id.question);
        precedentView = findViewById(R.id.precedentQ);
        suivantView = findViewById(R.id.suivantQ);

        for(int i=1; i < 4;i++){
            int resId = getResources().getIdentifier("text"+i, "id", getPackageName());
            reponsesView.add(findViewById(resId));
            resId = getResources().getIdentifier("card"+i, "id", getPackageName());
            cardsView.add(findViewById(resId));

        }


       questions = Question.initialize();

        for(CardView cardView : cardsView){
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetCardViews();
                    TextView reponseView = cardView.findViewById(R.id.text);
                    questions.get(randomQuestion).setReponseUtilisateur(reponseView.getText().toString());
                    cardView.setCardBackgroundColor(Color.parseColor("#E0E0E0"));

                    cardView.setCardElevation(8f);
                }
            });
        }

        precedentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbFois--;
                updateView();
            }
        });

        suivantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nbFois == 10){
                    Intent intent = new Intent(QuestionsActivity.this, ResultatQActivity.class);
                    intent.putExtra("nbReponsesJustes",Question.getQuestionsJuste(questions, alreadySelected));
                    startActivity(intent);
                }else {
                    nbFois++;
                    updateView();
                }
            }
        });

        updateView();

    }

    private void updateView() {
        resetCardViews();
        compteurView.setText(nbFois + "/10");

        do{
            randomQuestion = random.nextInt(25);
        }while (alreadySelected.contains(randomQuestion));
        alreadySelected.add(randomQuestion);

        Question question = questions.get(randomQuestion);
        questionView.setText(question.getQuestion());

        int randomCard = random.nextInt(2) + 1;

        TextView textCard = cardsView.get(randomCard).findViewById(R.id.text);
        textCard.setText(question.getBonneReponse());

        int reponseAMettre = 1;

        for(int i = 0; i < 3; i++){
            if(i != randomCard){
                TextView textFalse = cardsView.get(i).findViewById(R.id.text);
                if(reponseAMettre == 1){
                    textFalse.setText(question.getMauvaisesReponse1());
                    reponseAMettre++;
                }else{
                    textFalse.setText(question.getMauvaisesReponse2());
                }
            }
        }

        // Gérer les boutons
        precedentView.setEnabled(nbFois > 1);
        if (nbFois == 10) {
            suivantView.setText("Valider");
            suivantView.setTextColor(Color.RED);
        } else {
            suivantView.setText("Suivant");
            suivantView.setTextColor(Color.BLACK);
        }
    }

    private void resetCardViews() {
        for (CardView cardView : cardsView) {
            cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF")); // Remettre la couleur de fond à son état initial
            cardView.setCardElevation(4f); // Remettre l'élévation par défaut
        }
    }
}