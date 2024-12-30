package com.example.dailyexpensetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.tvExpenseDate.setText("Date: " + expense.getDate());
        holder.tvExpenseDescription.setText("Description: " + expense.getDescription());
        holder.tvExpenseAmount.setText("Amount: $" + expense.getAmount());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView tvExpenseDate, tvExpenseDescription, tvExpenseAmount;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExpenseDate = itemView.findViewById(R.id.tvExpenseDate);
            tvExpenseDescription = itemView.findViewById(R.id.tvExpenseDescription);
            tvExpenseAmount = itemView.findViewById(R.id.tvExpenseAmount);
        }
    }
}
