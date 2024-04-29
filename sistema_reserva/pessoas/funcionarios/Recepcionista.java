package sistema_reserva.pessoas.funcionarios;

import java.util.Queue;

import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.Pessoa;

public class Recepcionista extends Pessoa {
    private int salario;
    private Queue<Pessoa> filaEspera;

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

    public void receberChave(Quarto quarto) {
        quarto.setChaveNaRecepcao(true);
        System.out.println("Recepcionista " + getNome() + " recebeu a chave do quarto " + quarto.getNumero() + ".");
    }

    public void adicionarFilaEspera(Pessoa pessoa) {
        filaEspera.add(pessoa);
        System.out.println("Pessoa " + pessoa.getNome() + " adicionada à fila de espera.");
    }

    public Pessoa proximaDaFilaEspera() {
        return filaEspera.poll();
    }

    public boolean haPessoasNaFilaEspera() {
        return !filaEspera.isEmpty();
    }

    public void atenderFilaEspera() {
    
    }
}

