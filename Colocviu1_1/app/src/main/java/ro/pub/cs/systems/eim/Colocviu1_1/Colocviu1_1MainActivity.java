package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Colocviu1_1MainActivity extends AppCompatActivity {
    private String buttonPresses = "";
    private TextView historyTextView;
    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;

    private int noOfPressedButtons = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.secondary_activity_button:
                    break;
                default:
                    buttonPresses += ((Button)view).getText().toString() + ", ";
                    historyTextView.setText(buttonPresses);
                    noOfPressedButtons++;
                    Toast.makeText(view.getContext(), "Number of button presses " + noOfPressedButtons, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);

        historyTextView = (TextView)findViewById(R.id.history_text_view);
        northButton = (Button) findViewById(R.id.north_button);
        southButton = (Button) findViewById(R.id.south_button);
        eastButton = (Button) findViewById(R.id.east_button);
        westButton = (Button) findViewById(R.id.west_button);

        northButton.setOnClickListener(buttonClickListener);
        southButton.setOnClickListener(buttonClickListener);
        eastButton.setOnClickListener(buttonClickListener);
        westButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.NO_OF_BUTTON_PRESSES)) {
                noOfPressedButtons = savedInstanceState.getInt(Constants.NO_OF_BUTTON_PRESSES);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.NO_OF_BUTTON_PRESSES, noOfPressedButtons);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.NO_OF_BUTTON_PRESSES)) {
            noOfPressedButtons = savedInstanceState.getInt(Constants.NO_OF_BUTTON_PRESSES);
        }
    }
}