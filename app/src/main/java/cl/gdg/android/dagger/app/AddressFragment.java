package cl.gdg.android.dagger.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import cl.gdg.android.dagger.data.GeocodeRepository;
import cl.gdg.android.dagger.data.di.XML;
import cl.gdg.android.dagger.data.model.Address;
import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

import static java.util.Objects.requireNonNull;

public class AddressFragment extends DaggerFragment {
    private TextInputLayout query;
    private RecyclerView recycler;
    private Disposable queryDisposable = Disposables.disposed();
    private boolean xmlMode;

    @Inject
    GeocodeRepository repository;

    @XML
    @Inject
    GeocodeRepository xmlRepository;

    public AddressFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        query = view.findViewById(R.id.query);
        recycler = view.findViewById(R.id.recycler);

        view.findViewById(R.id.search).setOnClickListener($ -> performQuery());
    }

    private void performQuery() {
        String address = requireNonNull(query.getEditText()).getText().toString();

        queryDisposable.dispose();
        queryDisposable = (xmlMode ? xmlRepository : repository).lookup(address)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onQueryResults);
    }

    private void onQueryResults(List<Address> addresses) {
        recycler.setAdapter(new Adapter(addresses));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_address, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.json).setVisible(xmlMode);
        menu.findItem(R.id.xml).setVisible(!xmlMode);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.json:
                xmlMode = false;
                requireNonNull(getActivity()).invalidateOptionsMenu();
                return true;

            case R.id.xml:
                xmlMode = true;
                requireNonNull(getActivity()).invalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();

        queryDisposable.dispose();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        query = null;
    }

    static class Adapter extends RecyclerView.Adapter<Holder> {
        private final List<Address> addresses;

        Adapter(List<Address> addresses) {
            this.addresses = addresses;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_address_item, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.setItem(addresses.get(position));
        }

        @Override
        public int getItemCount() {
            return addresses.size();
        }

    }

    static class Holder extends RecyclerView.ViewHolder {
        private final TextView address;
        private final TextView latitude;
        private final TextView longitude;

        Holder(View view) {
            super(view);

            address = view.findViewById(R.id.address);
            latitude = view.findViewById(R.id.latitude);
            longitude = view.findViewById(R.id.longitude);
        }

        public void setItem(Address item) {
            address.setText(item.address);
            latitude.setText(String.valueOf(item.latitude));
            longitude.setText(String.valueOf(item.longitude));
        }
    }

}
