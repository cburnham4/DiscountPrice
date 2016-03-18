package letshangllc.discountcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_finalPrice, tv_savings;
    private EditText et_originalPrice, et_discount, et_salesTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_finalPrice = (TextView) findViewById(R.id.tv_finalPrice);
        tv_savings = (TextView) findViewById(R.id.tv_savings);

        et_originalPrice = (EditText) findViewById(R.id.et_price);
        et_discount = (EditText) findViewById(R.id.et_discount);
        et_salesTax = (EditText) findViewById(R.id.et_taxPercent);

        et_originalPrice.addTextChangedListener(new MyTextWatcher());
        et_discount.addTextChangedListener(new MyTextWatcher());
        et_salesTax.addTextChangedListener(new MyTextWatcher());
    }


    public class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String originalPriceString = et_originalPrice.getText().toString();
            String discountString = et_discount.getText().toString();
            String taxString = et_salesTax.getText().toString();
            Log.i("BILL", originalPriceString);
            if(!originalPriceString.isEmpty() && !originalPriceString.equals("")
                    && !discountString.isEmpty() && !discountString.equals("")
                    && !taxString.isEmpty() && !taxString.equals("")){

                try{
                    double cost = Double.parseDouble(originalPriceString);
                    double discount = Double.parseDouble(discountString);
                    double tax = Double.parseDouble(taxString);

                    double savings = cost * (discount/100.0);
                    cost-=savings;
                    double total = cost + cost * (tax/100.0);
                    tv_savings.setText(String.format("%.2f",savings));
                    tv_finalPrice.setText(String.format("%.2f",(0.0+total)));

                } catch (NumberFormatException e){
                    e.printStackTrace();
                }

            }
        }
    }
}
