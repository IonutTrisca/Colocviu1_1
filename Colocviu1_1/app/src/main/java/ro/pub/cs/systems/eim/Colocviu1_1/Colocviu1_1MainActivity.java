package ro.pub.cs.systems.eim.Colocviu1_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private Button secondaryActivityButton;

    private int noOfPressedButtons = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);
                    intent.putExtra(ro.pub.cs.systems.eim.Colocviu1_1.Constants.INSTRUCTIONS, buttonPresses);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    noOfPressedButtons = 0;
                    buttonPresses = "";
                    historyTextView.setText(buttonPresses);
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
        secondaryActivityButton = (Button) findViewById(R.id.secondary_activity_button);

        northButton.setOnClickListener(buttonClickListener);
        southButton.setOnClickListener(buttonClickListener);
        eastButton.setOnClickListener(buttonClickListener);
        westButton.setOnClickListener(buttonClickListener);
        secondaryActivityButton.setOnClickListener(buttonClickListener);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            String buttonPressed = "";

            if (resultCode == Constants.REGISTER_BUTTON_PRESS) {
                buttonPressed = "Register";
            }

            if (resultCode == Constants.CANCEL_BUTTON_PRESS){
                buttonPressed = "Cancel";
            }

            Toast.makeText(this, "The " + buttonPressed + " button was pressed", Toast.LENGTH_LONG).show();
        }
    }
}