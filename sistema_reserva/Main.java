package sistema_reserva;

import sistema_reserva.Hotel.Hotel;
import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.PossivelHospede;
import sistema_reserva.pessoas.funcionarios.Camareira;
import sistema_reserva.pessoas.funcionarios.Recepcionista;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(2, 3, 10);

        hotel.getRecepcionistas().add(new Recepcionista("Ana", 30, "123456789", 2000));
        hotel.getRecepcionistas().add(new Recepcionista("Carlos", 25, "987654321", 1800));

        hotel.getCamareiras().add(new Camareira("Maria", 35, "111222333", 1500));
        hotel.getCamareiras().add(new Camareira("Joana", 40, "444555666", 1600));
        hotel.getCamareiras().add(new Camareira("Pedro", 28, "777888999", 1400));

        for (int i = 1; i <= 10; i++) {
            hotel.getQuartos().add(new Quarto(i, "", true));
        }

        hotel.getRecepcionistas().get(1).atenderFilaEspera(hotel.getQuartos());
    }
}
