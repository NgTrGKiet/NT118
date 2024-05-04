package com.example.lab3_3;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private final TextView mssv;
    private final TextView lop;
    private final TextView hoTen;

    public StudentViewHolder(View itemView) {
        super(itemView);
        mssv = itemView.findViewById(R.id.textViewMSSV);
        lop = itemView.findViewById(R.id.textViewLop);
        hoTen = itemView.findViewById(R.id.textViewHoTen);
    }

    public void bind(final Student student, final int position, final AdapterView.OnItemClickListener listener) {
        hoTen.setText(student.getHoTen());
        lop.setText(student.getLop());
        mssv.setText(student.getMssv());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(null, v, getAbsoluteAdapterPosition(), v.getId());
            }
        });
    }
}
