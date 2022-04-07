package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_1SecondaryActivity extends AppCompatActivity {

    private TextView instructionsTextView;
    private Button registerButton;
    private Button cancelButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_button:
                    setResult(Constants.REGISTER_BUTTON_PRESS, null);
                    break;
                case R.id.cancel_button:
                    setResult(Constants.CANCEL_BUTTON_PRESS, null);
                    break;
            }
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_secondary);

        instructionsTextView = (TextView) findViewById(R.id.instructions_text_view);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.INSTRUCTIONS)) {
            String instructions = intent.getStringExtra(Constants.INSTRUCTIONS);
            instructionsTextView.setText(instructions);
        }

        registerButton = (Button) findViewById(R.id.register_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        registerButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}