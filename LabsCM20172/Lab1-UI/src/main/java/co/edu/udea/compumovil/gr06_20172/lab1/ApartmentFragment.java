package co.edu.udea.compumovil.gr06_20172.lab1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class ApartmentFragment extends ListFragment {
    DbHelper dbHelper;
    SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        dbHelper =new DbHelper(getActivity().getBaseContext());
        ListarApartamentos();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    public void ListarApartamentos(){//operacion para listar sitios, muestra en el activity los sitios guardados
        ArrayList<String> apa = new ArrayList(),tipo = new ArrayList(),valor = new ArrayList(),direccion= new ArrayList(),ids = new ArrayList(), area = new ArrayList(), desc = new ArrayList();
        ArrayList picture = new ArrayList();
        boolean control=false;
        db= dbHelper.getReadableDatabase();
        Cursor test=db.rawQuery("select * from "+StatusContract.TABLE_APARTMENT+" order by "+ StatusContract.Column_Apartment.NAME, null);
        if (test.moveToFirst()) {
            do{
                ids.add(test.getString(0));
                direccion.add(test.getString(1));
                apa.add(test.getString(2));
                tipo.add(test.getString(3));
                valor.add(test.getString(4));
                area.add(test.getString(5));
                desc.add(test.getString(6));
                picture.add(test.getBlob(7));
            }while(test.moveToNext());
            control=true;
        } else{
            Toast.makeText(getActivity().getBaseContext(),"Sin apartamentos",Toast.LENGTH_LONG).show();
        }
        db.close();
        if(control) {
            ArrayList aList=new ArrayList();
            for (int i = 0; i < ids.size(); i++) {
                HashMap<String, Object> hm = new HashMap<String, Object>();
                hm.put("lug", "Nombre: " + apa.get(i));
                hm.put("tipo", "Tipo: " + tipo.get(i));
                hm.put("desc", "Descripción : " + desc.get(i));
                hm.put("area", "Area : " + area.get(i));
                hm.put("valor", "Valor : " + valor.get(i));
                hm.put("picture", BitmapFactory.decodeByteArray((byte[]) picture.get(i), 0, ((byte[]) picture.get(i)).length));
                aList.add(hm);
            }
            String from[];
            int to[];
            from = new String[]{"apa","tipo", "desc", "area", "valor", "picture"};
            to = new int[]{R.id.NombreApartamento, R.id.desc, R.id.area, R.id.valor, R.id.picture};
            ExtendedSimpleAdapter adapter = new ExtendedSimpleAdapter(getActivity().getBaseContext(), aList, R.layout.fragment_apartment_list, from, to);
            setListAdapter(adapter);
        }
    }
}
