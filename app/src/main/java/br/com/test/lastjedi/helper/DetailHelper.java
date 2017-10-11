package br.com.test.lastjedi.helper;

import android.content.Context;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

import br.com.test.lastjedi.R;
import br.com.test.lastjedi.model.People;

/**
 * Created by Samurai on 10/10/2017.
 */

public class DetailHelper {

    private MaterialEditText heightDetailEditText;
    private MaterialEditText massDetailEditText;
    private MaterialEditText hairColorDetailEditText;
    private MaterialEditText skinColorDetailEditText;
    private MaterialEditText eyeColorDetailEditText;
    private MaterialEditText birthYearDetailEditText;
    private MaterialEditText genderDetailEditText;

    public DetailHelper(View rootView){
        heightDetailEditText = (MaterialEditText) rootView.findViewById(R.id.heightDetailEditText);
        massDetailEditText = (MaterialEditText) rootView.findViewById(R.id.massDetailEditText);
        hairColorDetailEditText = (MaterialEditText) rootView.findViewById(R.id.hairColorDetailEditText);
        skinColorDetailEditText = (MaterialEditText) rootView.findViewById(R.id.skinColorDetailEditText);
        eyeColorDetailEditText = (MaterialEditText) rootView.findViewById(R.id.eyeColorDetailEditText);
        birthYearDetailEditText = (MaterialEditText) rootView.findViewById(R.id.birthYearDetailEditText);
        genderDetailEditText = (MaterialEditText) rootView.findViewById(R.id.genderDetailEditText);
    }

    public void onPopulateFields(People people) {
        heightDetailEditText.setText(String.valueOf(people.getHeight()));
        massDetailEditText.setText(String.valueOf(people.getMass()));
        hairColorDetailEditText.setText(people.getHairColor());
        skinColorDetailEditText.setText(people.getSkinColor());
        eyeColorDetailEditText.setText(people.getEyeColor());
        birthYearDetailEditText.setText(people.getBirthYear());
        genderDetailEditText.setText(people.getGender());
    }
}
