package sistema_reserva;

import java.util.ArrayList;
import java.util.List;

import sistema_reserva.Hotel.Hotel;
import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.Hospede;
import sistema_reserva.pessoas.funcionarios.Camareira;
import sistema_reserva.pessoas.funcionarios.Recepcionista;

public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel(5, 10, 10);
        List<Thread> camareiraThreads = new ArrayList<>();
        List<Thread> recepcionistaThreads = new ArrayList<>();
        List<Thread> hospedeThreads = new ArrayList<>();

        for (Camareira camareira : hotel.getCamareiras()) {
            Thread camareiraThread = new Thread(camareira);
            camareiraThreads.add(camareiraThread);
        }

        for (Recepcionista recepcionista : hotel.getRecepcionistas()) {
            Thread recepcionistaThread = new Thread(recepcionista);
            recepcionistaThreads.add(recepcionistaThread);
        }

        // for (Thread camareiraThread : camareiraThreads) {
        //     camareiraThread.start();
        // }

        // for (Thread recepcionistaThread : recepcionistaThreads) {
        //     recepcionistaThread.start();
        // }

        // for (Thread hospedeThread : hospedeThreads) {
        //     hospedeThread.start();
        // }

    }
}

