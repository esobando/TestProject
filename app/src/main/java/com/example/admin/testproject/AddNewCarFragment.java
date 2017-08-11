package com.example.admin.testproject;

import android.content.Context;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddNewCarFragment extends Fragment {


    Car car;
    Button  btnSaveCar;
    EditText etModel;
    EditText etType;
    EditText etYear;
    RecyclerView rvCardList;

    private OnFragmentInteractionListener mListener;

    public AddNewCarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_car, container, false);
        etModel = view.findViewById(R.id.etModel);
        etType = view.findViewById(R.id.etType);
        etYear = view.findViewById(R.id.etYear);
        rvCardList =view.findViewById(R.id.rvCarList);
        btnSaveCar = view.findViewById(R.id.btnSaveCar);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSaveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                car = new Car(etModel.getText().toString().trim(), etType.getText().toString().trim(),
                        etYear.getText().toString().trim());
                DatabaseHelper db = new DatabaseHelper(v.getContext());
                db.saveNewContact(car);
                etModel.setText("");
                etType.setText("");
                etYear.setText("");

                CarRecyclerViewFragment carListView = new CarRecyclerViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.flFrame2, carListView, "AddNewCarFragment").commit();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Car car);
    }
}