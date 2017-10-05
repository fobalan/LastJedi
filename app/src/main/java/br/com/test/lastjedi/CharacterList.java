package br.com.test.lastjedi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.test.lastjedi.helper.CharacterListHelper;

public class CharacterList extends AppCompatActivity {

    private CharacterListHelper characterListHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        characterListHelper = new CharacterListHelper(this);
    }
}
