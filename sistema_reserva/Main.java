package sistema_reserva;

import java.util.ArrayList;
import java.util.List;

import sistema_reserva.Hotel.Hotel;
import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.Hospede;
import sistema_reserva.pessoas.PossivelHospede;
import sistema_reserva.pessoas.funcionarios.Camareira;
import sistema_reserva.pessoas.funcionarios.Recepcionista;

public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel(5, 10, 10);
        List<Thread> camareiraThreads = new ArrayList<>();
        List<Thread> recepcionistaThreads = new ArrayList<>();
        List<Thread> hospedeThreads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            PossivelHospede possivelHospede = new PossivelHospede("Possível Hóspede " + (i + 1), 30, "000.000.000-00");
            hotel.adicionarFilaEspera(possivelHospede); 
        }
        
        for (Camareira camareira : hotel.getCamareiras()) {
            Thread camareiraThread = new Thread(camareira);
            camareiraThread.start();
            camareiraThreads.add(camareiraThread);
        }

        for (Recepcionista recepcionista : hotel.getRecepcionistas()) {
            Thread recepcionistaThread = new Thread(recepcionista);
            recepcionistaThread.start();
            recepcionistaThreads.add(recepcionistaThread);
        }

        for (int i = 0; i < 10; i++) {
            Hospede hospede = new Hospede("Hóspede " + (i + 1), 30, "000.000.000-00", 0); 
            Thread hospedeThread = new Thread(hospede);
            hospedeThread.start();
            hospedeThreads.add(hospedeThread);
        }        

    }
}
