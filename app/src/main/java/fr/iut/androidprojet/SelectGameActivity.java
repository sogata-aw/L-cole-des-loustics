package fr.iut.androidprojet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SelectGameActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private Button btnAdditions;
    private TextView userView;
    public static final String USER = "rastemax";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        userView = findViewById(R.id.user);
        btnAdditions = findViewById(R.id.btn_additions);

        userView.setText(prefs.getString("user", ""));

        btnAdditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectGameActivity.this, AdditionActivity.class);
                startActivity(intent);
            }
        });
    }
}