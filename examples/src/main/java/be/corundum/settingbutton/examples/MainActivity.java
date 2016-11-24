package be.corundum.settingbutton.examples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import be.corundum.settingbuttons.SettingButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SettingButton helloWorldButton = (SettingButton) findViewById(R.id.action_hello_world);
        helloWorldButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_hello_world:
                showHelloWorld();
                break;
        }
    }

    private void showHelloWorld() {
        Toast.makeText(this, "Hello world!", Toast.LENGTH_LONG).show();
    }
}
