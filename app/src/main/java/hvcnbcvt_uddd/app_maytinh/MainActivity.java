package hvcnbcvt_uddd.app_maytinh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String  mDisplay = "";
    private static TextView txt_result;
    private static float mResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        txt_result = (TextView) findViewById(R.id.txt_result);
        int[] idButton = {R.id.btn_0,R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4,R.id.btn_5,R.id.btn_6,R.id.btn_7,R.id.btn_8
                ,R.id.btn_9,R.id.btn_sum,R.id.btn_sub,R.id.btn_left,R.id.btn_div,R.id.btn_result,R.id.btn_mul,R.id.btn_C
                ,R.id.btn_AC,R.id.btn_right};
        for (int id:idButton)
        {
            View view = (View) findViewById(id);
            view.setOnClickListener(this);
        }
    }

    private void show(View view)
    {
        if(mDisplay.equals("")) {
                mDisplay += String.valueOf((int)mResult);
        }
        mDisplay += ((Button)view).getText().toString();
        txt_result.setText(mDisplay);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btn_sum:
                show(view);
                break;
            case R.id.btn_sub:
                show(view);
                break;
            case R.id.btn_mul:
                show(view);
                break;
            case R.id.btn_div:
                show(view);
                break;
            case R.id.btn_result:
                try {
                    mResult = hvcnbcvt_uddd.app_maytinh.Calculation.main(mDisplay);
                    Log.d("Bug",mDisplay+"="+mResult);
                    txt_result.setText(String.valueOf(mResult));
                    mDisplay = "";
                } catch (Exception e){
                    txt_result.setText("0");
                }
                break;
            case R.id.btn_C:
                delete();
                break;
            case R.id.btn_AC:
                deleteAll();
                break;
            default:
                mDisplay += ((Button)view).getText().toString();
                txt_result.setText(mDisplay);
        }
    }

    private void deleteAll() {
        mDisplay="";
        mResult=0;
        txt_result.setText(mDisplay);
    }

    private void delete() {
        if (mDisplay.length() == 0) {
            return;
        }
        mDisplay = mDisplay.substring(0,mDisplay.length() - 1);
        txt_result.setText(mDisplay);
    }

}
