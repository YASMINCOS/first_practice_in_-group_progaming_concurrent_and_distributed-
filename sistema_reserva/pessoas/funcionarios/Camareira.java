package sistema_reserva.pessoas.funcionarios;

import java.util.List;
import sistema_reserva.pessoas.Pessoa;

public class Camareira extends Pessoa {
    private int salario;
    private List<Integer> quartosLimpos;

    public Camareira(String nome, int idade, String cpf, int salario) {
        super(nome, idade, cpf);
        this.salario = salario;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public List<Integer> getQuartosLimpos() {
        return quartosLimpos;
    }

    public void setQuartosLimpos(List<Integer> quartosLimpos) {
        this.quartosLimpos = quartosLimpos;
    }
}
