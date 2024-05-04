package sistema_reserva.Hotel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import sistema_reserva.pessoas.Familia;
import sistema_reserva.pessoas.Hospede;
import sistema_reserva.pessoas.PossivelHospede;
import sistema_reserva.pessoas.funcionarios.Camareira;
import sistema_reserva.pessoas.funcionarios.Recepcionista;

public class Hotel {
    List<Recepcionista> recepcionistas;
    private List<Camareira> camareiras;
    private List<Quarto> quartos;
    private static Lock lock;
    private Queue<PossivelHospede> filaEspera;
    private List<Hospede> hospedes;
    String name = "Hotel Arizona";

    //A priori, vamos usar familias como sendo array de hospedes para que o conjunto de pessoas da familia sejam uma coisa só
    private List<Familia> hospedesFamilia;

    public Hotel(int numRecepcionistas, int numCamareiras, int numQuartos) {
        recepcionistas = new ArrayList<>();
        camareiras = new ArrayList<>();
        quartos = new ArrayList<>();
        hospedes = new ArrayList<>();
        lock = new ReentrantLock();
        filaEspera = new LinkedList<>();
        addRecepcionista(numRecepcionistas);
        addCamareiras(numCamareiras);
    }

    void addRecepcionista(int numRecepcionistas){

        String[] names = {"Ana", "Carlos", "Maria", "Joana", "Tais"};
        Integer[] idades = {40, 20, 25, 27, 21};
        String[] cpf = {"111-111-111-11", "222-222-222-22", "333-333-333-33", "444-444-444-44", "555-555-555-55"};
        Integer[] salario = {2000, 1100, 1250, 2100, 1700};

        for (int i = 0; i < numRecepcionistas; i++){
            this.recepcionistas.add(new Recepcionista(names[i], idades[i], cpf[i], salario[i], this));
        }

    
    }

    void addCamareiras(int numCamareiras){

        String[] names = {"Dona Maria", "Alceu", "Ana", "Alcileia", "Joana", "Gabriela", "Zendaia", "Duda", "roberto" ,"Marcos"};
        Integer[] idades = {41, 59, 31, 40, 32, 19, 23, 24, 29, 50};
        String[] cpf = {"121-121-121-12", "232-232-232-23", "343-343-343-34", "555-555-555-55", "666-666-666-66", "777-777-777-77", "888-888-888-88", "999-999-999-99", "101-101-101-11", "222-222-222-22"};
        Integer[] salario = {2100, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 1200};

        for (int i = 0; i < numCamareiras; i++){
            this.camareiras.add(new Camareira(names[i], idades[i], cpf[i], salario[i]));    
        }
    }

    public List<Recepcionista> getRecepcionistas() {
        return this.recepcionistas;
    }

    public List<Camareira> getCamareiras() {
        return this.camareiras;
    }

    public List<Quarto> getQuartos() {
        return this.quartos;
    }

    public List<Hospede> getHospede() {
        return this.hospedes;
    }

    public Quarto getQuartoDisponivel() {
        lock.lock();
        try {
            for (Quarto quarto : quartos) {
                if (quarto.isDisponivel()) {
                    return quarto;
                }
            }
            return null; 
        } finally {
            lock.unlock();
        }
    }

    public void liberarQuarto(Quarto quarto) {
        lock.lock();
        try {
            quarto.setDisponivel(true);
        } finally {
            lock.unlock();
        }
    }

    public void tentarAlugarQuarto(Familia possivelFamilia, List<Quarto> quartos, Integer quantidadeDePessoas) {
        
        if (haQuartosVagos()) {
            System.out.println("Pessoa " + pessoa.getNome() + " conseguiu alugar um quarto.");

            possivelFamilia.resetTentativas(); 

            //Aloca um quarto disponível
            Quarto quarto = getQuartoDisponivel();

            //Lógica para alugar um quarto para uma família
            quarto.adicionarHospede(possivelFamilia);
           
        } else {
            if (possivelFamilia.getTentativas() < 2) {
                adicionarFilaEspera(possivelFamilia);
                System.out.println("Não há quartos vagos. Pessoa " + pessoa.getNome() + " adicionada à fila de espera.");
                possivelFamilia.incrementarTentativas(); 
            } else {
                possivelFamilia.reclamarEIrEmbora(); 
            }
        }
    }

    //Por enquanto vamos tentar fazer funcionar apenas com menos de quatro pessoas
    public Familia verificarPesssoasPorQuarto(Familia familia,Integer quantidadePessoas, Integer quartoNumero){

        if (quantidadePessoas <= 4){
            //Pega cada possível hospede e adiciona em um array de familia
            for(int i = 0; i < quantidadePessoas; i++){
                PossivelHospede possivelHospede = familia.getPossiveisHospedes();
                Hospede hospede = new Hospede(possivelHospede.getNome(), possivelHospede.getIdade(), possivelHospede.getCpf(), quartoNumero);
                familia.addHospede(possiveisHospedes.get(i));
            }
            
            return familia;
        }else{

        }
    }   

    public boolean adicionarFilaEspera(Familia familia) {
        lock.lock();
        try {
            if (haQuartosVagos()) {
                return true;
            } else {
                filaEspera.add(pessoa);
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void atenderFilaEspera() {
        lock.lock();
        try {
            while (!filaEspera.isEmpty()) {
                PossivelHospede pessoa = filaEspera.poll();
                Quarto quarto = getQuartoDisponivel();
                if (quarto != null) {
                    System.out.println("Pessoa " + pessoa.getNome() + " conseguiu alugar um quarto.");
                } else {
                    pessoa.reclamarEIrEmbora();
                }
            }
            System.out.println("Não há mais pessoas na fila de espera.");
        } finally {
            lock.unlock();
        }
    }

    private boolean haQuartosVagos() {
        for (Quarto quarto : quartos) {
            if (quarto.isDisponivel()) {
                return true;
            }
        }
        return false;
    }

    public Recepcionista getRecepcionistaDisponivel() {
        lock.lock();
        try {
            for (Recepcionista recepcionista : recepcionistas) {
                if (!recepcionista.isOcupada()) {
                    return recepcionista;
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public PossivelHospede getProximoHospedeNaFila() {
        lock.lock();
        try {
            return filaEspera.poll();
        } finally {
            lock.unlock();
        }
    }

    public Quarto getQuartoPorHospede(Hospede hospede) {
        lock.lock();
        try {
            for (Quarto quarto : quartos) {
                if (quarto.getHospedes() != null && quarto.getHospedes().equals(this.hospede)) {
                    return quarto;
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }
    
}
