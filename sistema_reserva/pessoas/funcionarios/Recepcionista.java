package sistema_reserva.pessoas.funcionarios;

import java.util.List;
import sistema_reserva.pessoas.Pessoa;

public class Recepcionista extends Pessoa {
    private int salario;
    private List<Integer> reservas;

    public Recepcionista(String nome, int idade, String cpf, int salario) {
        super(nome, idade, cpf);
        this.salario = salario;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public List<Integer> getReservas() {
        return reservas;
    }

    public void setReservas(List<Integer> reservas) {
        this.reservas = reservas;
    }
}