package com.example.allreserves.telas.cliente.listagem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.allreserves.telas.cliente.listagem.reservas.MinhasReservas;
import com.example.allreserves.telas.cliente.listagem.restaurantes.Restaurantes;


public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0) {
            return new Restaurantes();
        }
        else {
            return new MinhasReservas();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
