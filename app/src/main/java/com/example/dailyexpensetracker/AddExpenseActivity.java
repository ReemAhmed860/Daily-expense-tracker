package com.example.dailyexpensetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText etDate, etDescription, etAmount;
    private Button btnSaveExpense;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etDate = findViewById(R.id.etDate);
        etDescription = findViewById(R.id.etDescription);
        etAmount = findViewById(R.id.etAmount);
        btnSaveExpense = findViewById(R.id.btnSaveExpense);
        dbHelper = new SQLiteHelper(this);

        btnSaveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String description = etDescription.getText().toString();
                double amount = Double.parseDouble(etAmount.getText().toString());

                boolean success = dbHelper.insertExpense(date, description, amount);
                if (success) {
                    Toast.makeText(AddExpenseActivity.this, "Expense Added", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddExpenseActivity.this, "Error Adding Expense", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
