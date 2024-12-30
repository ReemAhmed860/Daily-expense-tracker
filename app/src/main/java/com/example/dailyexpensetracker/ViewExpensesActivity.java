package com.example.dailyexpensetracker;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ViewExpensesActivity extends AppCompatActivity {

    private RecyclerView rvExpenses;
    private TextView tvNoExpenses;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        rvExpenses = findViewById(R.id.rvExpenses);
        tvNoExpenses = findViewById(R.id.tvNoExpenses);
        dbHelper = new SQLiteHelper(this);

        loadExpenses();
    }

    private void loadExpenses() {
        List<Expense> expenseList = new ArrayList<>();
        Cursor cursor = dbHelper.getAllExpenses();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range") double amount = cursor.getDouble(cursor.getColumnIndex("amount"));

                expenseList.add(new Expense(date, description, amount));
            } while (cursor.moveToNext());
            cursor.close();
        }

        if (expenseList.isEmpty()) {
            tvNoExpenses.setVisibility(View.VISIBLE);
            rvExpenses.setVisibility(View.GONE);
        } else {
            tvNoExpenses.setVisibility(View.GONE);
            rvExpenses.setVisibility(View.VISIBLE);
            rvExpenses.setLayoutManager(new LinearLayoutManager(this));
            rvExpenses.setAdapter(new ExpenseAdapter(expenseList));
        }
    }
}
