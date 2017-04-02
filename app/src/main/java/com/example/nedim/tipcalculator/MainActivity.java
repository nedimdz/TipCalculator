package com.example.nedim.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;
    @BindView(R.id.tvBillTotalResult)
    TextView tvBillTotalResult;

    float percanteage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float totalBillAmount = 0;

    final float REGULAR_TIP_PERCENTE = 10;
    final float DEFAULT_TIP_PERCENTE = 15;
    final float EXCELLENT_TIP_PERCENTE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if(savedInstanceState != null)
        //{
        //    finalBillAmount = Float.valueOf(savedInstanceState.getString("finalAmount"));
        //}

        ButterKnife.bind(this);
        setTipValues();
    }

    private void setTipValues() {
        tvTipPercent.setText(getString(R.string.main_msg_tippercent, percanteage));
        tvTipAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        tvBillTotalResult.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percanteage = REGULAR_TIP_PERCENTE;
                break;
            case R.id.ibGoodService:
                percanteage = DEFAULT_TIP_PERCENTE;
                break;
            case R.id.ibExcellentService:
                percanteage = EXCELLENT_TIP_PERCENTE;
                break;
        }
        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {
        if (percanteage == 0)
            percanteage = DEFAULT_TIP_PERCENTE;

        if(!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());
        else
            totalBillAmount = 0;

        tipTotal = (totalBillAmount * percanteage)/100;
        finalBillAmount = totalBillAmount + tipTotal;
    }

    // Spašavanje vrijednosti


    //@Override
    //protected void onSaveInstanceState(Bundle outState) {
    //    outState.putString("finalAmount", tvBillTotalResult.getText().toString());
    // super.onSaveInstanceState(outState);
    //}
}
