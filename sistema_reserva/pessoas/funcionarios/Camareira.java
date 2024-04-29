package sistema_reserva.pessoas.funcionarios;

import java.util.List;

import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.Pessoa;

public class Camareira extends Pessoa{
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

    public void quartoDisponivelParaLimpeza(Quarto quarto) {
        if (quarto.isChaveNaRecepcao() && quarto.isDisponivel()) {
            System.out.println("Camareira " + getNome() + " está entrando no quarto " + quarto.getNumero() + " para limpeza.");
            limparQuarto(quarto);
        } else {
            System.out.println("Camareira " + getNome() + " não pode entrar no quarto " + quarto.getNumero() + " agora.");
        }
    }

    private void limparQuarto(Quarto quarto) {
        quarto.limparQuarto();
    }
}

