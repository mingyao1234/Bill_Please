package sg.edu.rp.c346.id21023135.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editTextInputAmount);
        numPax = findViewById(R.id.editTextInputNumPax);
        totalBill = findViewById(R.id.textViewTotal);
        eachPays = findViewById(R.id.textViewEachPay);
        svs = findViewById(R.id.tbtnSvs);
        gst = findViewById(R.id.tbtnGst);
        split = findViewById(R.id.btnSplit);
        reset = findViewById(R.id.btnReset);
        discount = findViewById(R.id.editTextInputDiscount);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().trim().length() != 0 && numPax.getText().toString().trim().length() != 0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }
                    totalBill.setText("@string/totalbill" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(numPax.getText().toString());
                    if (numPerson != 1) {
                        eachPays.setText("@string/eachpays" + String.format("%.2f", newAmt / numPerson));
                    } else {
                        eachPays.setText("@string/eachpays" + newAmt);
                    }
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });
    }
}