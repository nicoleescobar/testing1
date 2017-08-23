package com.example.android.testing1;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class principal extends AppCompatActivity {
    private TextView res;
    private EditText n1, n2;
    private Resources resources;
    private Spinner operaciones;
    private String op[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        res = (TextView)findViewById(R.id.lblresultado);
        n1 = (EditText)findViewById(R.id.txtnum1);
        n2 = (EditText)findViewById(R.id.txtnum2);
        operaciones = (Spinner)findViewById(R.id.cmbOperaciones);
        resources = this.getResources();
        op = resources.getStringArray(R.array.operaciones);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, op);
        operaciones.setAdapter(adapter);
    }

    public boolean validar(){
        int posicion = operaciones.getSelectedItemPosition();
        if(n1.getText().toString().isEmpty()){
            n1.setError(resources.getString(R.string.mensaje_error_uno));
            return false;
        }

        if(n2.getText().toString().isEmpty()){
            n2.setError(resources.getString(R.string.mensaje_error_dos));
            return false;
        }

        if((Integer.parseInt(n2.getText().toString()) == 0 ) && (posicion == 3)){
            Toast.makeText(this, resources.getString(R.string.mensaje_error_3), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void calcular(View v){
        double num1, num2, resultado=0;
        int opcion;
        res.setText("");
        if (validar()) {
            opcion = operaciones.getSelectedItemPosition();
            num1 = Double.parseDouble(n1.getText().toString());
            num2 = Double.parseDouble(n2.getText().toString());

            switch (opcion) {
                case 0 :
                    resultado = num1 + num2;
                    break;
                case 1 :
                    resultado = num1 - num2;
                    break;
                case 2 :
                    resultado = num1 * num2;
                    break;
                case 3 :
                    resultado = num1 / num2;
                    break;
            }

            res.setText("" + String.format("%.2f", resultado));
        }
    }

    public void limpiar(View v){
        res.setText("");
        n1.setText("");
        n2.setText("");
        operaciones.setSelection(0);

        n1.requestFocus();
    }


}
