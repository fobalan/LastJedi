package br.com.test.lastjedi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import br.com.test.lastjedi.adapter.PeopleListAdapter;
import br.com.test.lastjedi.dao.PeopleDAO;
import br.com.test.lastjedi.helper.PeopleListHelper;
import br.com.test.lastjedi.listener.PeopleListViewListener;
import br.com.test.lastjedi.model.People;
import br.com.test.lastjedi.retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleList extends AppCompatActivity implements PeopleListViewListener, View.OnClickListener {

    private static final int PERMISSION_REQUEST = 200;
    private PeopleListHelper peopleListHelper;
    private List<People> list = new ArrayList<>();
    private PeopleListAdapter adapter;
    private PeopleDAO peopleDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);

        peopleDAO = new PeopleDAO(this);
        peopleListHelper = new PeopleListHelper(this);
        peopleListHelper.getFabActionButton().setOnClickListener(this);

        onActionBar();
        onLoadList();
        onConfigureRyclerView();
    }

    private void onLoadList() {
        list = peopleDAO.getList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onLoadList();
    }

    private void onActionBar() {
        setSupportActionBar(peopleListHelper.getToolbar());
    }

    private void onConfigureRyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        peopleListHelper.getRecyclerView().setLayoutManager(layoutManager);
        peopleListHelper.getRecyclerView().setHasFixedSize(true);

        adapter = new PeopleListAdapter(this, list, R.layout.people_list_item);
        adapter.setOnItemClickListener(this);

        peopleListHelper.getRecyclerView().setAdapter(adapter);
    }

    @Override
    public void onViewClick(View v, int position) {

    }

    @Override
    public void onClick(View v) {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setPrompt("Scanneie seu Personagem");
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.setBeepEnabled(false);
            integrator.setBarcodeImageEnabled(true);
            integrator.setOrientationLocked(false);
            integrator.initiateScan();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "QR Code capturado com sucesso", Toast.LENGTH_LONG).show();
                onAddNewCharacter(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void onAddNewCharacter(String characterUrl) {
        final ProgressDialog progress = ProgressDialog.show(this,"Aguarde","Buscando Personagem..",true,true);
        Call<People> call = new RetrofitInitializer(characterUrl)
                .getPeopleService()
                .getPeople();
        call.enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                People newPeople = response.body();
                if(!peopleDAO.checkIfExists(newPeople)) {
                    list.add(newPeople);
                    adapter.notifyItemInserted(list.size() - 1);
                    peopleDAO.insert(newPeople);
                } else {
                    Toast.makeText(getApplicationContext(), "Você já possui esse porsonagem", Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                Toast.makeText(getApplicationContext(), "Falha de comunicação com a API, verificar log",Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
